// Generated from /home/eric/IdeaProjects/lutum/core/src/game/core/binary/parser/BinaryValueTree.g4 by ANTLR 4.12.0

package game.core.binary.bvt.parser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link BinaryValueTreeParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface BinaryValueTreeVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#file}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile(BinaryValueTreeParser.FileContext ctx);
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#head}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHead(BinaryValueTreeParser.HeadContext ctx);
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#setting}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetting(BinaryValueTreeParser.SettingContext ctx);
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#typeset}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeset(BinaryValueTreeParser.TypesetContext ctx);
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#typedef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypedef(BinaryValueTreeParser.TypedefContext ctx);
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#enumdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumdef(BinaryValueTreeParser.EnumdefContext ctx);
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#coerce}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoerce(BinaryValueTreeParser.CoerceContext ctx);
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(BinaryValueTreeParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Null}
	 * labeled alternative in {@link BinaryValueTreeParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNull(BinaryValueTreeParser.NullContext ctx);
	/**
	 * Visit a parse tree produced by the {@code False}
	 * labeled alternative in {@link BinaryValueTreeParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFalse(BinaryValueTreeParser.FalseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code True}
	 * labeled alternative in {@link BinaryValueTreeParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrue(BinaryValueTreeParser.TrueContext ctx);
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#tuple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTuple(BinaryValueTreeParser.TupleContext ctx);
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#array}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray(BinaryValueTreeParser.ArrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#binarray}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinarray(BinaryValueTreeParser.BinarrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#bytearray}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBytearray(BinaryValueTreeParser.BytearrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#shortarray}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShortarray(BinaryValueTreeParser.ShortarrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#intarray}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntarray(BinaryValueTreeParser.IntarrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#longarray}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLongarray(BinaryValueTreeParser.LongarrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#floatarray}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatarray(BinaryValueTreeParser.FloatarrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#doublearray}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoublearray(BinaryValueTreeParser.DoublearrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#object}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObject(BinaryValueTreeParser.ObjectContext ctx);
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#kvpair}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKvpair(BinaryValueTreeParser.KvpairContext ctx);
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#ident}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdent(BinaryValueTreeParser.IdentContext ctx);
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(BinaryValueTreeParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(BinaryValueTreeParser.StringContext ctx);
}