// Generated from /home/eric/IdeaProjects/lutum/core/src/game/core/binary/parser/BinaryValueTree.g4 by ANTLR 4.12.0

package game.core.binary.bvt.parser;

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
	 * Enter a parse tree produced by the {@code Null}
	 * labeled alternative in {@link BinaryValueTreeParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterNull(BinaryValueTreeParser.NullContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Null}
	 * labeled alternative in {@link BinaryValueTreeParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitNull(BinaryValueTreeParser.NullContext ctx);
	/**
	 * Enter a parse tree produced by the {@code False}
	 * labeled alternative in {@link BinaryValueTreeParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterFalse(BinaryValueTreeParser.FalseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code False}
	 * labeled alternative in {@link BinaryValueTreeParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitFalse(BinaryValueTreeParser.FalseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code True}
	 * labeled alternative in {@link BinaryValueTreeParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterTrue(BinaryValueTreeParser.TrueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code True}
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
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#binarray}.
	 * @param ctx the parse tree
	 */
	void enterBinarray(BinaryValueTreeParser.BinarrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#binarray}.
	 * @param ctx the parse tree
	 */
	void exitBinarray(BinaryValueTreeParser.BinarrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#bytearray}.
	 * @param ctx the parse tree
	 */
	void enterBytearray(BinaryValueTreeParser.BytearrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#bytearray}.
	 * @param ctx the parse tree
	 */
	void exitBytearray(BinaryValueTreeParser.BytearrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#shortarray}.
	 * @param ctx the parse tree
	 */
	void enterShortarray(BinaryValueTreeParser.ShortarrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#shortarray}.
	 * @param ctx the parse tree
	 */
	void exitShortarray(BinaryValueTreeParser.ShortarrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#intarray}.
	 * @param ctx the parse tree
	 */
	void enterIntarray(BinaryValueTreeParser.IntarrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#intarray}.
	 * @param ctx the parse tree
	 */
	void exitIntarray(BinaryValueTreeParser.IntarrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#longarray}.
	 * @param ctx the parse tree
	 */
	void enterLongarray(BinaryValueTreeParser.LongarrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#longarray}.
	 * @param ctx the parse tree
	 */
	void exitLongarray(BinaryValueTreeParser.LongarrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#floatarray}.
	 * @param ctx the parse tree
	 */
	void enterFloatarray(BinaryValueTreeParser.FloatarrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#floatarray}.
	 * @param ctx the parse tree
	 */
	void exitFloatarray(BinaryValueTreeParser.FloatarrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link BinaryValueTreeParser#doublearray}.
	 * @param ctx the parse tree
	 */
	void enterDoublearray(BinaryValueTreeParser.DoublearrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link BinaryValueTreeParser#doublearray}.
	 * @param ctx the parse tree
	 */
	void exitDoublearray(BinaryValueTreeParser.DoublearrayContext ctx);
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