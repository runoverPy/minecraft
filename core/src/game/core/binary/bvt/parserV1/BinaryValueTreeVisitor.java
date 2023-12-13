// Generated from /home/eric/Documents/coding/IdeaProjects/Minecraft/core/src/game/core/binary/bvt/parserV1/BinaryValueTree.g4 by ANTLR 4.13.1
package game.core.binary.bvt.parserV1;
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
	 * Visit a parse tree produced by the {@code null}
	 * labeled alternative in {@link BinaryValueTreeParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNull(BinaryValueTreeParser.NullContext ctx);
	/**
	 * Visit a parse tree produced by the {@code false}
	 * labeled alternative in {@link BinaryValueTreeParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFalse(BinaryValueTreeParser.FalseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code true}
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
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#binArray}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinArray(BinaryValueTreeParser.BinArrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#byteArray}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitByteArray(BinaryValueTreeParser.ByteArrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#shortArray}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShortArray(BinaryValueTreeParser.ShortArrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#intArray}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntArray(BinaryValueTreeParser.IntArrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#longArray}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLongArray(BinaryValueTreeParser.LongArrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#floatArray}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatArray(BinaryValueTreeParser.FloatArrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#doubleArray}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoubleArray(BinaryValueTreeParser.DoubleArrayContext ctx);
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
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#wholeNumber}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWholeNumber(BinaryValueTreeParser.WholeNumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#byteNumber}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitByteNumber(BinaryValueTreeParser.ByteNumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#shortNumber}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShortNumber(BinaryValueTreeParser.ShortNumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#intNumber}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntNumber(BinaryValueTreeParser.IntNumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#longNumber}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLongNumber(BinaryValueTreeParser.LongNumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#pointNumber}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPointNumber(BinaryValueTreeParser.PointNumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#floatNumber}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatNumber(BinaryValueTreeParser.FloatNumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#doubleNumber}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoubleNumber(BinaryValueTreeParser.DoubleNumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link BinaryValueTreeParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(BinaryValueTreeParser.StringContext ctx);
}