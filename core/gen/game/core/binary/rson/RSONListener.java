// Generated from /home/eric/IdeaProjects/lutum/core/src/game/core/binary/rson/RSON.g4 by ANTLR 4.12.0
package game.core.binary.rson;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RSONParser}.
 */
public interface RSONListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RSONParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(RSONParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link RSONParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(RSONParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link RSONParser#anno}.
	 * @param ctx the parse tree
	 */
	void enterAnno(RSONParser.AnnoContext ctx);
	/**
	 * Exit a parse tree produced by {@link RSONParser#anno}.
	 * @param ctx the parse tree
	 */
	void exitAnno(RSONParser.AnnoContext ctx);
	/**
	 * Enter a parse tree produced by {@link RSONParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(RSONParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link RSONParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(RSONParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link RSONParser#bool}.
	 * @param ctx the parse tree
	 */
	void enterBool(RSONParser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by {@link RSONParser#bool}.
	 * @param ctx the parse tree
	 */
	void exitBool(RSONParser.BoolContext ctx);
	/**
	 * Enter a parse tree produced by {@link RSONParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(RSONParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link RSONParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(RSONParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link RSONParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(RSONParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link RSONParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(RSONParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link RSONParser#struct}.
	 * @param ctx the parse tree
	 */
	void enterStruct(RSONParser.StructContext ctx);
	/**
	 * Exit a parse tree produced by {@link RSONParser#struct}.
	 * @param ctx the parse tree
	 */
	void exitStruct(RSONParser.StructContext ctx);
	/**
	 * Enter a parse tree produced by {@link RSONParser#tuple}.
	 * @param ctx the parse tree
	 */
	void enterTuple(RSONParser.TupleContext ctx);
	/**
	 * Exit a parse tree produced by {@link RSONParser#tuple}.
	 * @param ctx the parse tree
	 */
	void exitTuple(RSONParser.TupleContext ctx);
	/**
	 * Enter a parse tree produced by {@link RSONParser#array}.
	 * @param ctx the parse tree
	 */
	void enterArray(RSONParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link RSONParser#array}.
	 * @param ctx the parse tree
	 */
	void exitArray(RSONParser.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link RSONParser#object}.
	 * @param ctx the parse tree
	 */
	void enterObject(RSONParser.ObjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link RSONParser#object}.
	 * @param ctx the parse tree
	 */
	void exitObject(RSONParser.ObjectContext ctx);
	/**
	 * Enter a parse tree produced by {@link RSONParser#field}.
	 * @param ctx the parse tree
	 */
	void enterField(RSONParser.FieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link RSONParser#field}.
	 * @param ctx the parse tree
	 */
	void exitField(RSONParser.FieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link RSONParser#ident}.
	 * @param ctx the parse tree
	 */
	void enterIdent(RSONParser.IdentContext ctx);
	/**
	 * Exit a parse tree produced by {@link RSONParser#ident}.
	 * @param ctx the parse tree
	 */
	void exitIdent(RSONParser.IdentContext ctx);
	/**
	 * Enter a parse tree produced by {@link RSONParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(RSONParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link RSONParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(RSONParser.TypeContext ctx);
}