// Generated from /home/eric/IdeaProjects/lutum/core/src/game/core/console/Vyper.g4 by ANTLR 4.12.0

package game.core.console.vyper;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link VyperParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface VyperVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link VyperParser#file}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile(VyperParser.FileContext ctx);
	/**
	 * Visit a parse tree produced by {@link VyperParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmd(VyperParser.CmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link VyperParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(VyperParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link VyperParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(VyperParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link VyperParser#fn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFn(VyperParser.FnContext ctx);
	/**
	 * Visit a parse tree produced by {@link VyperParser#posarg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPosarg(VyperParser.PosargContext ctx);
	/**
	 * Visit a parse tree produced by {@link VyperParser#kwdarg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKwdarg(VyperParser.KwdargContext ctx);
	/**
	 * Visit a parse tree produced by {@link VyperParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(VyperParser.ValueContext ctx);
}