// Generated from /home/eric/IdeaProjects/lutum/core/src/game/core/console/Vyper.g4 by ANTLR 4.12.0

package game.core.console.vyper;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link VyperParser}.
 */
public interface VyperListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link VyperParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(VyperParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link VyperParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(VyperParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link VyperParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmd(VyperParser.CmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link VyperParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmd(VyperParser.CmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link VyperParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(VyperParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link VyperParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(VyperParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link VyperParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(VyperParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link VyperParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(VyperParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link VyperParser#fn}.
	 * @param ctx the parse tree
	 */
	void enterFn(VyperParser.FnContext ctx);
	/**
	 * Exit a parse tree produced by {@link VyperParser#fn}.
	 * @param ctx the parse tree
	 */
	void exitFn(VyperParser.FnContext ctx);
	/**
	 * Enter a parse tree produced by {@link VyperParser#posarg}.
	 * @param ctx the parse tree
	 */
	void enterPosarg(VyperParser.PosargContext ctx);
	/**
	 * Exit a parse tree produced by {@link VyperParser#posarg}.
	 * @param ctx the parse tree
	 */
	void exitPosarg(VyperParser.PosargContext ctx);
	/**
	 * Enter a parse tree produced by {@link VyperParser#kwdarg}.
	 * @param ctx the parse tree
	 */
	void enterKwdarg(VyperParser.KwdargContext ctx);
	/**
	 * Exit a parse tree produced by {@link VyperParser#kwdarg}.
	 * @param ctx the parse tree
	 */
	void exitKwdarg(VyperParser.KwdargContext ctx);
	/**
	 * Enter a parse tree produced by {@link VyperParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(VyperParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link VyperParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(VyperParser.ValueContext ctx);
}