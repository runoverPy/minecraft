// Generated from /home/eric/Documents/coding/IdeaProjects/Minecraft/core/src/game/core/binary/bvt/parserV1/BinaryValueTree.g4 by ANTLR 4.13.1
package game.core.binary.bvt.parserV1;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link BinaryValueTreeParser}.
 */
public interface BinaryValueTreeListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(BinaryValueTreeParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(BinaryValueTreeParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#head}.
	 * @param ctx the parse tree
	 */
	void enterHead(BinaryValueTreeParser.HeadContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#head}.
	 * @param ctx the parse tree
	 */
	void exitHead(BinaryValueTreeParser.HeadContext ctx);
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#setting}.
	 * @param ctx the parse tree
	 */
	void enterSetting(BinaryValueTreeParser.SettingContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#setting}.
	 * @param ctx the parse tree
	 */
	void exitSetting(BinaryValueTreeParser.SettingContext ctx);
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#typeset}.
	 * @param ctx the parse tree
	 */
	void enterTypeset(BinaryValueTreeParser.TypesetContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#typeset}.
	 * @param ctx the parse tree
	 */
	void exitTypeset(BinaryValueTreeParser.TypesetContext ctx);
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#typedef}.
	 * @param ctx the parse tree
	 */
	void enterTypedef(BinaryValueTreeParser.TypedefContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#typedef}.
	 * @param ctx the parse tree
	 */
	void exitTypedef(BinaryValueTreeParser.TypedefContext ctx);
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#enumdef}.
	 * @param ctx the parse tree
	 */
	void enterEnumdef(BinaryValueTreeParser.EnumdefContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#enumdef}.
	 * @param ctx the parse tree
	 */
	void exitEnumdef(BinaryValueTreeParser.EnumdefContext ctx);
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#coerce}.
	 * @param ctx the parse tree
	 */
	void enterCoerce(BinaryValueTreeParser.CoerceContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#coerce}.
	 * @param ctx the parse tree
	 */
	void exitCoerce(BinaryValueTreeParser.CoerceContext ctx);
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(BinaryValueTreeParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(BinaryValueTreeParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code null}
	 * labeled alternative in {@link BinaryValueTreeParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterNull(BinaryValueTreeParser.NullContext ctx);
	/**
	 * Exit a parse tree produced by the {@code null}
	 * labeled alternative in {@link BinaryValueTreeParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitNull(BinaryValueTreeParser.NullContext ctx);
	/**
	 * Enter a parse tree produced by the {@code false}
	 * labeled alternative in {@link BinaryValueTreeParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterFalse(BinaryValueTreeParser.FalseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code false}
	 * labeled alternative in {@link BinaryValueTreeParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitFalse(BinaryValueTreeParser.FalseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code true}
	 * labeled alternative in {@link BinaryValueTreeParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterTrue(BinaryValueTreeParser.TrueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code true}
	 * labeled alternative in {@link BinaryValueTreeParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitTrue(BinaryValueTreeParser.TrueContext ctx);
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#tuple}.
	 * @param ctx the parse tree
	 */
	void enterTuple(BinaryValueTreeParser.TupleContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#tuple}.
	 * @param ctx the parse tree
	 */
	void exitTuple(BinaryValueTreeParser.TupleContext ctx);
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#array}.
	 * @param ctx the parse tree
	 */
	void enterArray(BinaryValueTreeParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#array}.
	 * @param ctx the parse tree
	 */
	void exitArray(BinaryValueTreeParser.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#binArray}.
	 * @param ctx the parse tree
	 */
	void enterBinArray(BinaryValueTreeParser.BinArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#binArray}.
	 * @param ctx the parse tree
	 */
	void exitBinArray(BinaryValueTreeParser.BinArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#byteArray}.
	 * @param ctx the parse tree
	 */
	void enterByteArray(BinaryValueTreeParser.ByteArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#byteArray}.
	 * @param ctx the parse tree
	 */
	void exitByteArray(BinaryValueTreeParser.ByteArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#shortArray}.
	 * @param ctx the parse tree
	 */
	void enterShortArray(BinaryValueTreeParser.ShortArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#shortArray}.
	 * @param ctx the parse tree
	 */
	void exitShortArray(BinaryValueTreeParser.ShortArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#intArray}.
	 * @param ctx the parse tree
	 */
	void enterIntArray(BinaryValueTreeParser.IntArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#intArray}.
	 * @param ctx the parse tree
	 */
	void exitIntArray(BinaryValueTreeParser.IntArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#longArray}.
	 * @param ctx the parse tree
	 */
	void enterLongArray(BinaryValueTreeParser.LongArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#longArray}.
	 * @param ctx the parse tree
	 */
	void exitLongArray(BinaryValueTreeParser.LongArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#floatArray}.
	 * @param ctx the parse tree
	 */
	void enterFloatArray(BinaryValueTreeParser.FloatArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#floatArray}.
	 * @param ctx the parse tree
	 */
	void exitFloatArray(BinaryValueTreeParser.FloatArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#doubleArray}.
	 * @param ctx the parse tree
	 */
	void enterDoubleArray(BinaryValueTreeParser.DoubleArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#doubleArray}.
	 * @param ctx the parse tree
	 */
	void exitDoubleArray(BinaryValueTreeParser.DoubleArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#object}.
	 * @param ctx the parse tree
	 */
	void enterObject(BinaryValueTreeParser.ObjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#object}.
	 * @param ctx the parse tree
	 */
	void exitObject(BinaryValueTreeParser.ObjectContext ctx);
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#kvpair}.
	 * @param ctx the parse tree
	 */
	void enterKvpair(BinaryValueTreeParser.KvpairContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#kvpair}.
	 * @param ctx the parse tree
	 */
	void exitKvpair(BinaryValueTreeParser.KvpairContext ctx);
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#ident}.
	 * @param ctx the parse tree
	 */
	void enterIdent(BinaryValueTreeParser.IdentContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#ident}.
	 * @param ctx the parse tree
	 */
	void exitIdent(BinaryValueTreeParser.IdentContext ctx);
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(BinaryValueTreeParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(BinaryValueTreeParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#wholeNumber}.
	 * @param ctx the parse tree
	 */
	void enterWholeNumber(BinaryValueTreeParser.WholeNumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#wholeNumber}.
	 * @param ctx the parse tree
	 */
	void exitWholeNumber(BinaryValueTreeParser.WholeNumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#byteNumber}.
	 * @param ctx the parse tree
	 */
	void enterByteNumber(BinaryValueTreeParser.ByteNumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#byteNumber}.
	 * @param ctx the parse tree
	 */
	void exitByteNumber(BinaryValueTreeParser.ByteNumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#shortNumber}.
	 * @param ctx the parse tree
	 */
	void enterShortNumber(BinaryValueTreeParser.ShortNumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#shortNumber}.
	 * @param ctx the parse tree
	 */
	void exitShortNumber(BinaryValueTreeParser.ShortNumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#intNumber}.
	 * @param ctx the parse tree
	 */
	void enterIntNumber(BinaryValueTreeParser.IntNumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#intNumber}.
	 * @param ctx the parse tree
	 */
	void exitIntNumber(BinaryValueTreeParser.IntNumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#longNumber}.
	 * @param ctx the parse tree
	 */
	void enterLongNumber(BinaryValueTreeParser.LongNumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#longNumber}.
	 * @param ctx the parse tree
	 */
	void exitLongNumber(BinaryValueTreeParser.LongNumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#pointNumber}.
	 * @param ctx the parse tree
	 */
	void enterPointNumber(BinaryValueTreeParser.PointNumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#pointNumber}.
	 * @param ctx the parse tree
	 */
	void exitPointNumber(BinaryValueTreeParser.PointNumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#floatNumber}.
	 * @param ctx the parse tree
	 */
	void enterFloatNumber(BinaryValueTreeParser.FloatNumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#floatNumber}.
	 * @param ctx the parse tree
	 */
	void exitFloatNumber(BinaryValueTreeParser.FloatNumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#doubleNumber}.
	 * @param ctx the parse tree
	 */
	void enterDoubleNumber(BinaryValueTreeParser.DoubleNumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#doubleNumber}.
	 * @param ctx the parse tree
	 */
	void exitDoubleNumber(BinaryValueTreeParser.DoubleNumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(BinaryValueTreeParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(BinaryValueTreeParser.StringContext ctx);
}