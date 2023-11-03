package game.core.binary.bvt;

import game.core.binary.bvt.parser.BinaryValueTreeBaseVisitor;
import game.core.binary.bvt.parser.BinaryValueTreeLexer;
import game.core.binary.bvt.parser.BinaryValueTreeParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
            #[itype(i32), enum Feeling {GOOD, BAD}] {
                value: 1234.5678_f64,
                'foo': 1234f64,
                pi: 3.14159265359f64,
                'magic_number': 0xCAFE_BABE,
                array: [0xCAFE_BABE, 42069]i32,
                tuplebracks: [
                    0, 1, 2, 3
                ]
            }""");
        BinaryValueTreeLexer lexer = new BinaryValueTreeLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        BinaryValueTreeParser parser = new BinaryValueTreeParser(tokens);
        ParseTreeWalker walker = new ParseTreeWalker();
//        walker.walk(new BinaryValueTreeBaseListener() {
//            int indent;
//            Stack<Consumer<BVTValue>> consumers = new Stack<>();
//
//            @Override
//            public void enterKvpair(BinaryValueTreeParser.KvpairContext ctx) {
//                super.enterKvpair(ctx);
//                System.out.println("    ".repeat(indent) + "IDENT: " + ctx.ident().getText());
//            }
//
//            @Override
//            public void enterBinarray(BinaryValueTreeParser.BinarrayContext ctx) {
//                super.enterBinarray(ctx);
//            }
//
//            @Override
//            public void enterValue(BinaryValueTreeParser.ValueContext ctx) {
//                super.enterValue(ctx);
//                System.out.println("    ".repeat(indent) + "value:" + ctx.getAltNumber());
//            }
//
//            @Override
//            public void exitValue(BinaryValueTreeParser.ValueContext ctx) {
//                super.enterValue(ctx);
//                System.out.println("    ".repeat(indent) + "/value");
//            }
//
//            @Override
//            public void enterString(BinaryValueTreeParser.StringContext ctx) {
//                super.enterString(ctx);
//                System.out.println("    ".repeat(indent) + ctx.STRING());
//            }
//
//            @Override
//            public void enterNumber(BinaryValueTreeParser.NumberContext ctx) {
//                super.enterNumber(ctx);
//                System.out.println("    ".repeat(indent) + ctx.getText());
//            }
//
//            @Override
//            public void enterObject(BinaryValueTreeParser.ObjectContext ctx) {
//                super.enterObject(ctx);
//            }
//
//            @Override
//            public void enterTrue(BinaryValueTreeParser.TrueContext ctx) {
//                super.enterTrue(ctx);
//            }
//
//            @Override
//            public void exitString(BinaryValueTreeParser.StringContext ctx) {
//                System.out.println("    ".repeat(indent) + "String value: " + ctx.STRING());
//                super.exitString(ctx);
//            }
//
//            @Override
//            public void enterEveryRule(ParserRuleContext ctx) {
//                super.enterEveryRule(ctx);
//                System.out.println("    ".repeat(indent) + "Entering " + parser.getRuleNames()[ctx.getRuleIndex()]);
//                indent++;
//            }
//
//            @Override
//            public void exitEveryRule(ParserRuleContext ctx) {
//                super.exitEveryRule(ctx);
//                indent--;
//                System.out.println("    ".repeat(indent) + "Exiting " + parser.getRuleNames()[ctx.getRuleIndex()]);
//            }
//        }, parser.file());

        parser.reset();

        BinaryValueTreeParser.FileContext file = parser.file();
        System.out.println(file.head());
        BinaryValueTreeParser.ValueContext context = file.value();
        BinaryValueTreeBaseVisitor<BVTValue> visitor = new BinaryValueTreeBaseVisitor<>() {
            private String tryStripQuotes(String s) {
                Pattern stringPattern = Pattern.compile("^['\"](.*)['\"]$");
                Matcher matcher = stringPattern.matcher(s);
                if (!matcher.matches()) return s;
                return matcher.group(1);
            }

            private Number tryParseNumber(String s) {
                return 0;
            }

            public BVTValue visitValue(BinaryValueTreeParser.ValueContext ctx) {
                return super.visitValue(ctx);
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
            public BVTValue visitArray(BinaryValueTreeParser.ArrayContext ctx) {
                return super.visitArray(ctx);
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
                    object.getValues().put(tryStripQuotes(kvCtx.ident().getText()), kvCtx.value().accept(this));
                }
                return object;
            }

            @Override
            public BVTValue visitNumber(BinaryValueTreeParser.NumberContext ctx) {
                return super.visitNumber(ctx);
            }

            @Override
            public BVTValue visitString(BinaryValueTreeParser.StringContext ctx) {
                return new BVTString(tryStripQuotes(ctx.STRING().getText()));
            }
        };
        context.accept(visitor);
    }
}
