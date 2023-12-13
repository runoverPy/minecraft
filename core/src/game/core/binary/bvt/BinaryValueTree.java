package game.core.binary.bvt;

import game.core.binary.bvt.types.*;
import game.core.binary.bvt.parserV1.*;
import org.antlr.v4.runtime.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BinaryValueTree {
    public static BinaryValueTree fromString(String tree) {
        return null;
    }

    public static String toString(BinaryValueTree tree) {
        return null;
    }

    public static BinaryValueTree fromJSON(Object object) throws ClassCastException {
        int i = 13;
        return null;
    }

    public static Object toJSON(BinaryValueTree tree) {
        return null;
    }

    public static void writeTree(BinaryValueTree tree, DataOutputStream stream) {

    }

    public static BinaryValueTree readTree(DataInputStream stream) {
        return null;
    }

    public static void main(String[] args) {
        CharStream stream = CharStreams.fromString(
          """
            #[itype(i32)]
            #[enum Feeling {GOOD, BAD}]
            {
                value: 1234.5678_f64,
                'foo': 1234f64,
                pi: 3.14159265359f64,
                'magic_number': 0xCAFE_BABE,
                array: [i32: 0xCAFE_BABE, 42069],
                tuplebracks: [
                    0, 1, 2, 3
                ]
            }
            """);
        BinaryValueTreeLexer lexer = new BinaryValueTreeLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        BinaryValueTreeParser parser = new BinaryValueTreeParser(tokens);

        BinaryValueTreeParser.FileContext file = parser.file();
        BinaryValueTreeParser.ValueContext context = file.value();
        BinaryValueTreeBaseVisitor<BVTValue> visitor = new BinaryValueTreeBaseVisitor<>() {
            private String tryStripQuotes(String s) {
                Pattern stringPattern = Pattern.compile("^['\"](.*)['\"]$");
                Matcher matcher = stringPattern.matcher(s);
                if (!matcher.matches()) return s;
                return matcher.group(1);
            }

            @Override
            public BVTValue visitFile(BinaryValueTreeParser.FileContext ctx) {
                return ctx.value() != null ? ctx.value().accept(this) : new BVTNull();
            }

            @Override
            public BVTValue visitNull(BinaryValueTreeParser.NullContext ctx) {
                return new BVTNull();
            }

            @Override
            public BVTValue visitFalse(BinaryValueTreeParser.FalseContext ctx) {
                return new BVTBool(false);
            }

            @Override
            public BVTValue visitTrue(BinaryValueTreeParser.TrueContext ctx) {
                return new BVTBool(true);
            }

            @Override
            public BVTValue visitBinArray(BinaryValueTreeParser.BinArrayContext ctx) {
                if (ctx.BIN_ARRAY() != null) {
                    return BVTBoolArray.fromBinArray(ctx.BIN_ARRAY().getText());
                }
                if (ctx.HEX_ARRAY() != null) {
                    return BVTBoolArray.fromHexArray(ctx.HEX_ARRAY().getText());
                }
                throw new AssertionError();
            }

            @Override
            public BVTValue visitByteArray(BinaryValueTreeParser.ByteArrayContext ctx) {
                int count = ctx.number().size();
                byte[] values = new byte[count];
                NumberParser parser = new NumberParser();
                NumberParser.NumberVisitor visitor = parser.createVisitor();
                for (int i = 0; i < count; i++) {
                    values[i] = ctx.number().get(i).accept(visitor).byteValue();
                }
                return new BVTByteArray(values);
            }

            @Override
            public BVTValue visitShortArray(BinaryValueTreeParser.ShortArrayContext ctx) {
                int count = ctx.number().size();
                short[] values = new short[count];
                NumberParser parser = new NumberParser();
                NumberParser.NumberVisitor visitor = parser.createVisitor();
                for (int i = 0; i < count; i++) {
                    values[i] = ctx.number().get(i).accept(visitor).shortValue();
                }
                return new BVTShortArray(values);
            }

            @Override
            public BVTValue visitIntArray(BinaryValueTreeParser.IntArrayContext ctx) {
                int count = ctx.number().size();
                int[] values = new int[count];
                NumberParser parser = new NumberParser();
                NumberParser.NumberVisitor visitor = parser.createVisitor();
                for (int i = 0; i < count; i++) {
                    values[i] = ctx.number().get(i).accept(visitor).intValue();
                }
                return new BVTIntArray(values);
            }

            @Override
            public BVTValue visitLongArray(BinaryValueTreeParser.LongArrayContext ctx) {
                int count = ctx.number().size();
                long[] values = new long[count];
                NumberParser parser = new NumberParser();
                NumberParser.NumberVisitor visitor = parser.createVisitor();
                for (int i = 0; i < count; i++) {
                    values[i] = ctx.number().get(i).accept(visitor).longValue();
                }
                return new BVTLongArray(values);
            }

            @Override
            public BVTValue visitFloatArray(BinaryValueTreeParser.FloatArrayContext ctx) {
                int count = ctx.number().size();
                float[] values = new float[count];
                NumberParser parser = new NumberParser();
                NumberParser.NumberVisitor visitor = parser.createVisitor();
                for (int i = 0; i < count; i++) {
                    values[i] = ctx.number().get(i).accept(visitor).floatValue();
                }
                return new BVTFloatArray(values);
            }

            @Override
            public BVTValue visitDoubleArray(BinaryValueTreeParser.DoubleArrayContext ctx) {
                int count = ctx.number().size();
                double[] values = new double[count];
                NumberParser parser = new NumberParser();
                NumberParser.NumberVisitor visitor = parser.createVisitor();
                for (int i = 0; i < count; i++) {
                    values[i] = ctx.number().get(i).accept(visitor).doubleValue();
                }
                return new BVTDoubleArray(values);
            }

            @Override
            public BVTValue visitTuple(BinaryValueTreeParser.TupleContext ctx) {
                BVTTuple array = new BVTTuple();
                for (BinaryValueTreeParser.ValueContext valueCtx : ctx.value()) {
                    array.getValues().add(valueCtx.accept(this));
                }
                return array;
            }

            @Override
            public BVTValue visitObject(BinaryValueTreeParser.ObjectContext ctx) {
                BVTObject object = new BVTObject();
                for (BinaryValueTreeParser.KvpairContext kvCtx : ctx.kvpair()) {
                    object.getValues().put(
                      tryStripQuotes(kvCtx.ident().getText()),
                      kvCtx.value().accept(this)
                    );
                }
                return object;
            }

            @Override
            public BVTValue visitNumber(BinaryValueTreeParser.NumberContext ctx) {
                NumberParser parser = new NumberParser();
                Number number = ctx.accept(parser.createVisitor());
                return BVTValue.wrapNumber(number);
            }

            @Override
            public BVTValue visitString(BinaryValueTreeParser.StringContext ctx) {
                return new BVTString(tryStripQuotes(ctx.STRING().getText()));
            }
        };
        BVTValue value = context.accept(visitor);
        System.out.println(value.prettyPrint(0));
    }
}
