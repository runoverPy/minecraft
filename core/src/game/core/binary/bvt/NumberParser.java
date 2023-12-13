package game.core.binary.bvt;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import game.core.binary.bvt.parserV1.*;

public class NumberParser {
    public Number parseWholeNumber(String literal) {
        literal = literal.replaceAll("_", "");
        Number value;
        if (literal.startsWith("0x")) {
            value = new BigInteger(literal.substring(2), 16);
        } else if (literal.startsWith("0o")) {
            value = new BigInteger(literal.substring(2), 8);
        } else if (literal.startsWith("0b")) {
            value = new BigInteger(literal.substring(2), 2);
        } else {
            value = new BigInteger(literal);
        }
        return value.doubleValue();
    }

    public Number parseDecimalNumber(String literal) {
        return new BigDecimal(literal).doubleValue();
    }

    public Byte parseByte(String literal) {
        Pattern pattern = Pattern.compile("(.*?)(?:[bB]|i8)");
        literal = pattern.matcher(literal).group(1);
        try {
            byte value;
            if (literal.startsWith("0x")) {
                value = Byte.parseByte(literal.substring(2), 16);
            } else if (literal.startsWith("0o")) {
                value = Byte.parseByte(literal.substring(2), 8);
            } else if (literal.startsWith("0b")) {
                value = Byte.parseByte(literal.substring(2), 2);
            } else {
                value = Byte.parseByte(literal);
            }
            return value;
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            return null;
        }
    }

    public Short parseShort(String literal) {
        Pattern pattern = Pattern.compile("(.*?)(?:[sS]|i16)");
        literal = pattern.matcher(literal).group(1);
        try {
            short value;
            if (literal.startsWith("0x")) {
                value = Short.parseShort(literal.substring(2), 16);
            } else if (literal.startsWith("0o")) {
                value = Short.parseShort(literal.substring(2), 8);
            } else if (literal.startsWith("0b")) {
                value = Short.parseShort(literal.substring(2), 2);
            } else {
                value = Short.parseShort(literal);
            }
            return value;
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            return null;
        }
    }

    public Integer parseInteger(String literal) {
        Pattern pattern = Pattern.compile("(.*?)(?:[iI]|i32)");
        literal = pattern.matcher(literal).group(1);
        try {
            int value;
            if (literal.startsWith("0x")) {
                value = Integer.parseInt(literal.substring(2), 16);
            } else if (literal.startsWith("0o")) {
                value = Integer.parseInt(literal.substring(2), 8);
            } else if (literal.startsWith("0b")) {
                value = Integer.parseInt(literal.substring(2), 2);
            } else {
                value = Integer.parseInt(literal);
            }
            return value;
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            return null;
        }
    }

    public Long parseLong(String literal) {
        Pattern pattern = Pattern.compile("(.*?)(?:[lL]|i64)");
        literal = pattern.matcher(literal).group(1);
        try {
            long value;
            if (literal.startsWith("0x")) {
                value = Long.parseLong(literal.substring(2), 16);
            } else if (literal.startsWith("0o")) {
                value = Long.parseLong(literal.substring(2), 8);
            } else if (literal.startsWith("0b")) {
                value = Long.parseLong(literal.substring(2), 2);
            } else {
                value = Long.parseLong(literal);
            }
            return value;
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            return null;
        }
    }

    public Float parseFloat(String literal) {
        Pattern pattern = Pattern.compile("(.*?)(?:[fF]|f32)");
        literal = pattern.matcher(literal).group(1);
        try {
            return Float.parseFloat(literal);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            return null;
        }
    }

    public Double parseDouble(String literal) {
        Pattern pattern = Pattern.compile("(.*?)(?:[dD]|f64)");
        Matcher matcher = pattern.matcher(literal);
        matcher.matches();
        literal = matcher.group(1);
        try {
            return Double.parseDouble(literal.replaceAll("_", ""));
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            return null;
        }
    }

    public NumberVisitor createVisitor() {
        return new NumberVisitor();
    }

    public class NumberVisitor extends BinaryValueTreeBaseVisitor<Number> {
        @Override
        public Number visitWholeNumber(BinaryValueTreeParser.WholeNumberContext ctx) {
            return parseWholeNumber(ctx.WHOLE_NUMBER().getText());
        }

        @Override
        public Byte visitByteNumber(BinaryValueTreeParser.ByteNumberContext ctx) {
            return parseByte(ctx.BYTE_NUMBER().getText());
        }

        @Override
        public Short visitShortNumber(BinaryValueTreeParser.ShortNumberContext ctx) {
            return parseShort(ctx.SHORT_NUMBER().getText());
        }

        @Override
        public Integer visitIntNumber(BinaryValueTreeParser.IntNumberContext ctx) {
            return parseInteger(ctx.INT_NUMBER().getText());
        }

        @Override
        public Long visitLongNumber(BinaryValueTreeParser.LongNumberContext ctx) {
            return parseLong(ctx.LONG_NUMBER().getText());
        }

        @Override
        public Number visitPointNumber(BinaryValueTreeParser.PointNumberContext ctx) {
            return parseDecimalNumber(ctx.POINT_NUMBER().getText());
        }

        @Override
        public Float visitFloatNumber(BinaryValueTreeParser.FloatNumberContext ctx) {
            return parseFloat(ctx.FLOAT_NUMBER().getText());
        }

        @Override
        public Double visitDoubleNumber(BinaryValueTreeParser.DoubleNumberContext ctx) {
            return parseDouble(ctx.DOUBLE_NUMBER().getText());
        }
    }
}
