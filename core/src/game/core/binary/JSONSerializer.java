package game.core.binary;

import game.assets.mcui.control.InputField;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

/**
 * json -> value EOF
 * value: Object -> object | array | string | number | 'null | 'false' | 'true'
 */
public class JSONSerializer {
    public static void writeJSON(OutputStream stream, Object object) throws ClassCastException, IOException {
        writeJSON(new DataOutputStream(stream), object);
    }

    public static void writeJSON(DataOutputStream stream, Object object) throws ClassCastException, IOException {
        if (stream == null) throw new NullPointerException();
        class WriteUtil {
            void write(Object object) throws IOException {
                if (object == null) {
                    stream.write(0x01);
                }
                else if (object instanceof JSONObject jsonObject) {
                    stream.write(0x07);
                    stream.writeShort(jsonObject.length());
                    Set<String> keys = jsonObject.keySet();
                    for (String key : keys) {
                        stream.writeUTF(key);
                        write(jsonObject.get(key));
                    }
                } // write object
                else if (object instanceof JSONArray jsonArray) {
                    stream.write(0x06);
                    stream.writeShort(jsonArray.length());
                    for (Object value : jsonArray) {
                        write(value);
                    }
                } // write array
                else if (object instanceof String string) {
                    stream.write(0x0D);
                    stream.writeUTF(string);
                } // write string
                else if (object instanceof Number) {
                    if (object instanceof Byte byteValue) {
                        stream.write(0x08);
                        stream.writeByte(byteValue);
                    } else if (object instanceof Short shortValue) {
                        stream.write(0x09);
                        stream.writeShort(shortValue);
                    } else if (object instanceof Integer intValue) {
                        stream.write(0x0A);
                        stream.writeInt(intValue);
                    } else if (object instanceof Long longValue) {
                        stream.write(0x0B);
                        stream.writeLong(longValue);
                    } else if (object instanceof Float) {
                        stream.write(0x0E);
                        stream.writeFloat((Float) object);
                    } else if (object instanceof Double) {
                        stream.write(0x0F);
                        stream.writeDouble((Double) object);
                    } else throw new ClassCastException("Atomics & BigDecimals arent allowed here!");
                } // write number
                else if (object instanceof Boolean bool)
                    stream.write(!bool ? 0x02 : 0x03);
                else throw new ClassCastException();
            }
        }

        new WriteUtil().write(object);
    }

    public static Object readJSON(InputStream stream) throws IOException {
        return readJSON(new DataInputStream(stream));
    }

    public static Object readJSON(DataInputStream stream) throws IOException {
        class ReadUtil {
            Object read() throws IOException {
                return switch (stream.read()) {
                    case 0x01 -> null;
                    case 0x02 -> false;
                    case 0x03 -> true;
                    case 0x06 -> {
                        int length = stream.readShort();
                        JSONArray array = new JSONArray();
                        for (int i = 0; i < length; i++)
                            array.put(read());
                        yield array;
                    }
                    case 0x07 -> {
                        int length = stream.readShort();
                        JSONObject object = new JSONObject();
                        for (int i = 0; i < length; i++)
                            object.put(stream.readUTF(), read());
                        yield object;
                    }
                    case 0x08 -> stream.readByte();
                    case 0x09 -> stream.readShort();
                    case 0x0A -> stream.readInt();
                    case 0x0B -> stream.readLong();
                    case 0x0D -> stream.readUTF();
                    case 0x0E -> stream.readFloat();
                    case 0x0F -> stream.readDouble();
                    default -> throw new IllegalStateException();
                };
            }

            String readString() throws IOException {
                short length = stream.readShort();
                byte[] string = stream.readNBytes(length);
                return new String(string);
            }
        }
        return new ReadUtil().read();
    }

    public static void main(String[] args) throws IOException {
        StringBuilder builder = new StringBuilder();
        Path outPath = Paths.get("stdout.txt");
        System.err.println(outPath.toAbsolutePath());
        PrintStream out0 = new PrintStream(Files.newOutputStream(outPath));
        OutputStream stream = new OutputStream() {
            @Override
            public void write(int b) {
                builder.append((char) b);
            }
        };
        Object object0 = new JSONArray("[\n" +
          "  {\n" +
          "    \"name\": \"background\",\n" +
          "    \"sources\": {\n" +
          "      \"vs\": \"/shaders/background-vs.glsl\",\n" +
          "      \"fs\": \"/shaders/background-fs.glsl\"\n" +
          "    },\n" +
          "    \"uniform\": [\n" +
          "      \"transform\"\n" +
          "    ]\n" +
          "  }\n" +
          "]");
        System.err.println(object0);
        writeJSON(new DataOutputStream(stream), object0);
        out0.println(builder);
        builder.setLength(0);
        try (
          PipedInputStream inputStream = new PipedInputStream();
          PipedOutputStream outputStream = new PipedOutputStream()) {
            inputStream.connect(outputStream);
            writeJSON(outputStream, object0);
            Object object1 = readJSON(inputStream);
            writeJSON(stream, object1);
            System.err.println(object1);
        }
        out0.println(builder);
    }
}
