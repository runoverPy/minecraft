// Generated from /home/eric/IdeaProjects/lutum/core/src/game/core/binary/rson/RSON.g4 by ANTLR 4.12.0
package game.core.binary.rson;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link RSONParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface RSONVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link RSONParser#file}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile(RSONParser.FileContext ctx);
	/**
	 * Visit a parse tree produced by {@link RSONParser#anno}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnno(RSONParser.AnnoContext ctx);
	/**
	 * Visit a parse tree produced by {@link RSONParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(RSONParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link RSONParser#bool}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool(RSONParser.BoolContext ctx);
	/**
	 * Visit a parse tree produced by {@link RSONParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(RSONParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link RSONParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(RSONParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by {@link RSONParser#struct}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStruct(RSONParser.StructContext ctx);
	/**
	 * Visit a parse tree produced by {@link RSONParser#tuple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTuple(RSONParser.TupleContext ctx);
	/**
	 * Visit a parse tree produced by {@link RSONParser#array}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray(RSONParser.ArrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link RSONParser#object}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObject(RSONParser.ObjectContext ctx);
	/**
	 * Visit a parse tree produced by {@link RSONParser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField(RSONParser.FieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link RSONParser#ident}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdent(RSONParser.IdentContext ctx);
	/**
	 * Visit a parse tree produced by {@link RSONParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(RSONParser.TypeContext ctx);
}