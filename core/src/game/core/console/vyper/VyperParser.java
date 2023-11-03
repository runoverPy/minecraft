// Generated from /home/eric/IdeaProjects/lutum/core/src/game/core/console/Vyper.g4 by ANTLR 4.12.0

package game.core.console.vyper;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class VyperParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, ARGNAME=8, ARGFLAGS=9, 
		STRING=10, NUMBER=11, READ=12, WORD=13, RETURN_LOGIC=14, REDIRECT=15, 
		EOL=16, WS=17, IDENT=18, DEDENT=19;
	public static final int
		RULE_file = 0, RULE_cmd = 1, RULE_stmt = 2, RULE_expr = 3, RULE_fn = 4, 
		RULE_posarg = 5, RULE_kwdarg = 6, RULE_value = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"file", "cmd", "stmt", "expr", "fn", "posarg", "kwdarg", "value"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'|'", "'&'", "'='", "'$('", "')'", "'--'", null, null, 
			null, null, null, null, null, "'\\u00A3'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, "ARGNAME", "ARGFLAGS", 
			"STRING", "NUMBER", "READ", "WORD", "RETURN_LOGIC", "REDIRECT", "EOL", 
			"WS", "IDENT", "DEDENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Vyper.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public VyperParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FileContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(VyperParser.EOF, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public List<TerminalNode> EOL() { return getTokens(VyperParser.EOL); }
		public TerminalNode EOL(int i) {
			return getToken(VyperParser.EOL, i);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VyperListener ) ((VyperListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VyperListener ) ((VyperListener)listener).exitFile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VyperVisitor ) return ((VyperVisitor<? extends T>)visitor).visitFile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(17);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WORD) {
				{
				setState(16);
				cmd(0);
				}
			}

			setState(25);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0 || _la==EOL) {
				{
				{
				setState(19);
				_la = _input.LA(1);
				if ( !(_la==T__0 || _la==EOL) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(21);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WORD) {
					{
					setState(20);
					cmd(0);
					}
				}

				}
				}
				setState(27);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(28);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdContext extends ParserRuleContext {
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public FnContext fn() {
			return getRuleContext(FnContext.class,0);
		}
		public List<PosargContext> posarg() {
			return getRuleContexts(PosargContext.class);
		}
		public PosargContext posarg(int i) {
			return getRuleContext(PosargContext.class,i);
		}
		public List<KwdargContext> kwdarg() {
			return getRuleContexts(KwdargContext.class);
		}
		public KwdargContext kwdarg(int i) {
			return getRuleContext(KwdargContext.class,i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VyperListener ) ((VyperListener)listener).enterCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VyperListener ) ((VyperListener)listener).exitCmd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VyperVisitor ) return ((VyperVisitor<? extends T>)visitor).visitCmd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		return cmd(0);
	}

	private CmdContext cmd(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		CmdContext _localctx = new CmdContext(_ctx, _parentState);
		CmdContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_cmd, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(31);
				stmt();
				}
				break;
			case 2:
				{
				setState(32);
				fn();
				setState(37);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						setState(35);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case T__4:
						case STRING:
						case NUMBER:
						case READ:
						case WORD:
							{
							setState(33);
							posarg();
							}
							break;
						case T__6:
						case ARGNAME:
						case ARGFLAGS:
							{
							setState(34);
							kwdarg();
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						} 
					}
					setState(39);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
				}
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(50);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(48);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						_localctx = new CmdContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_cmd);
						setState(42);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(43);
						match(T__1);
						setState(44);
						cmd(3);
						}
						break;
					case 2:
						{
						_localctx = new CmdContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_cmd);
						setState(45);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(46);
						match(T__2);
						setState(47);
						cmd(2);
						}
						break;
					}
					} 
				}
				setState(52);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StmtContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(VyperParser.WORD, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VyperListener ) ((VyperListener)listener).enterStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VyperListener ) ((VyperListener)listener).exitStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VyperVisitor ) return ((VyperVisitor<? extends T>)visitor).visitStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			match(WORD);
			setState(54);
			match(T__3);
			setState(55);
			value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public TerminalNode READ() { return getToken(VyperParser.READ, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VyperListener ) ((VyperListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VyperListener ) ((VyperListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VyperVisitor ) return ((VyperVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_expr);
		int _la;
		try {
			int _alt;
			setState(74);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(57);
				match(T__4);
				setState(58);
				cmd(0);
				setState(65);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(59);
						match(T__0);
						setState(61);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==WORD) {
							{
							setState(60);
							cmd(0);
							}
						}

						}
						} 
					}
					setState(67);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
				}
				setState(69);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(68);
					match(T__0);
					}
				}

				setState(71);
				match(T__5);
				}
				break;
			case READ:
				enterOuterAlt(_localctx, 2);
				{
				setState(73);
				match(READ);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FnContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(VyperParser.WORD, 0); }
		public FnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fn; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VyperListener ) ((VyperListener)listener).enterFn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VyperListener ) ((VyperListener)listener).exitFn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VyperVisitor ) return ((VyperVisitor<? extends T>)visitor).visitFn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FnContext fn() throws RecognitionException {
		FnContext _localctx = new FnContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_fn);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			match(WORD);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PosargContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public PosargContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_posarg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VyperListener ) ((VyperListener)listener).enterPosarg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VyperListener ) ((VyperListener)listener).exitPosarg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VyperVisitor ) return ((VyperVisitor<? extends T>)visitor).visitPosarg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PosargContext posarg() throws RecognitionException {
		PosargContext _localctx = new PosargContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_posarg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class KwdargContext extends ParserRuleContext {
		public TerminalNode ARGNAME() { return getToken(VyperParser.ARGNAME, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TerminalNode ARGFLAGS() { return getToken(VyperParser.ARGFLAGS, 0); }
		public KwdargContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_kwdarg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VyperListener ) ((VyperListener)listener).enterKwdarg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VyperListener ) ((VyperListener)listener).exitKwdarg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VyperVisitor ) return ((VyperVisitor<? extends T>)visitor).visitKwdarg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KwdargContext kwdarg() throws RecognitionException {
		KwdargContext _localctx = new KwdargContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_kwdarg);
		try {
			setState(87);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ARGNAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(80);
				match(ARGNAME);
				setState(83);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
				case 1:
					{
					setState(81);
					match(T__3);
					setState(82);
					value();
					}
					break;
				}
				}
				break;
			case ARGFLAGS:
				enterOuterAlt(_localctx, 2);
				{
				setState(85);
				match(ARGFLAGS);
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 3);
				{
				setState(86);
				match(T__6);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ValueContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(VyperParser.STRING, 0); }
		public TerminalNode WORD() { return getToken(VyperParser.WORD, 0); }
		public TerminalNode NUMBER() { return getToken(VyperParser.NUMBER, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof VyperListener ) ((VyperListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof VyperListener ) ((VyperListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof VyperVisitor ) return ((VyperVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_value);
		try {
			setState(93);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(89);
				match(STRING);
				}
				break;
			case WORD:
				enterOuterAlt(_localctx, 2);
				{
				setState(90);
				match(WORD);
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 3);
				{
				setState(91);
				match(NUMBER);
				}
				break;
			case T__4:
			case READ:
				enterOuterAlt(_localctx, 4);
				{
				setState(92);
				expr();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return cmd_sempred((CmdContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean cmd_sempred(CmdContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0013`\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0001"+
		"\u0000\u0003\u0000\u0012\b\u0000\u0001\u0000\u0001\u0000\u0003\u0000\u0016"+
		"\b\u0000\u0005\u0000\u0018\b\u0000\n\u0000\f\u0000\u001b\t\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0005\u0001$\b\u0001\n\u0001\f\u0001\'\t\u0001\u0003\u0001)\b\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0005\u00011\b\u0001\n\u0001\f\u00014\t\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0003\u0003>\b\u0003\u0005\u0003@\b\u0003\n\u0003\f\u0003C\t\u0003\u0001"+
		"\u0003\u0003\u0003F\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003"+
		"\u0003K\b\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0003\u0006T\b\u0006\u0001\u0006\u0001"+
		"\u0006\u0003\u0006X\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0003\u0007^\b\u0007\u0001\u0007\u0000\u0001\u0002\b\u0000\u0002"+
		"\u0004\u0006\b\n\f\u000e\u0000\u0001\u0002\u0000\u0001\u0001\u0010\u0010"+
		"i\u0000\u0011\u0001\u0000\u0000\u0000\u0002(\u0001\u0000\u0000\u0000\u0004"+
		"5\u0001\u0000\u0000\u0000\u0006J\u0001\u0000\u0000\u0000\bL\u0001\u0000"+
		"\u0000\u0000\nN\u0001\u0000\u0000\u0000\fW\u0001\u0000\u0000\u0000\u000e"+
		"]\u0001\u0000\u0000\u0000\u0010\u0012\u0003\u0002\u0001\u0000\u0011\u0010"+
		"\u0001\u0000\u0000\u0000\u0011\u0012\u0001\u0000\u0000\u0000\u0012\u0019"+
		"\u0001\u0000\u0000\u0000\u0013\u0015\u0007\u0000\u0000\u0000\u0014\u0016"+
		"\u0003\u0002\u0001\u0000\u0015\u0014\u0001\u0000\u0000\u0000\u0015\u0016"+
		"\u0001\u0000\u0000\u0000\u0016\u0018\u0001\u0000\u0000\u0000\u0017\u0013"+
		"\u0001\u0000\u0000\u0000\u0018\u001b\u0001\u0000\u0000\u0000\u0019\u0017"+
		"\u0001\u0000\u0000\u0000\u0019\u001a\u0001\u0000\u0000\u0000\u001a\u001c"+
		"\u0001\u0000\u0000\u0000\u001b\u0019\u0001\u0000\u0000\u0000\u001c\u001d"+
		"\u0005\u0000\u0000\u0001\u001d\u0001\u0001\u0000\u0000\u0000\u001e\u001f"+
		"\u0006\u0001\uffff\uffff\u0000\u001f)\u0003\u0004\u0002\u0000 %\u0003"+
		"\b\u0004\u0000!$\u0003\n\u0005\u0000\"$\u0003\f\u0006\u0000#!\u0001\u0000"+
		"\u0000\u0000#\"\u0001\u0000\u0000\u0000$\'\u0001\u0000\u0000\u0000%#\u0001"+
		"\u0000\u0000\u0000%&\u0001\u0000\u0000\u0000&)\u0001\u0000\u0000\u0000"+
		"\'%\u0001\u0000\u0000\u0000(\u001e\u0001\u0000\u0000\u0000( \u0001\u0000"+
		"\u0000\u0000)2\u0001\u0000\u0000\u0000*+\n\u0002\u0000\u0000+,\u0005\u0002"+
		"\u0000\u0000,1\u0003\u0002\u0001\u0003-.\n\u0001\u0000\u0000./\u0005\u0003"+
		"\u0000\u0000/1\u0003\u0002\u0001\u00020*\u0001\u0000\u0000\u00000-\u0001"+
		"\u0000\u0000\u000014\u0001\u0000\u0000\u000020\u0001\u0000\u0000\u0000"+
		"23\u0001\u0000\u0000\u00003\u0003\u0001\u0000\u0000\u000042\u0001\u0000"+
		"\u0000\u000056\u0005\r\u0000\u000067\u0005\u0004\u0000\u000078\u0003\u000e"+
		"\u0007\u00008\u0005\u0001\u0000\u0000\u00009:\u0005\u0005\u0000\u0000"+
		":A\u0003\u0002\u0001\u0000;=\u0005\u0001\u0000\u0000<>\u0003\u0002\u0001"+
		"\u0000=<\u0001\u0000\u0000\u0000=>\u0001\u0000\u0000\u0000>@\u0001\u0000"+
		"\u0000\u0000?;\u0001\u0000\u0000\u0000@C\u0001\u0000\u0000\u0000A?\u0001"+
		"\u0000\u0000\u0000AB\u0001\u0000\u0000\u0000BE\u0001\u0000\u0000\u0000"+
		"CA\u0001\u0000\u0000\u0000DF\u0005\u0001\u0000\u0000ED\u0001\u0000\u0000"+
		"\u0000EF\u0001\u0000\u0000\u0000FG\u0001\u0000\u0000\u0000GH\u0005\u0006"+
		"\u0000\u0000HK\u0001\u0000\u0000\u0000IK\u0005\f\u0000\u0000J9\u0001\u0000"+
		"\u0000\u0000JI\u0001\u0000\u0000\u0000K\u0007\u0001\u0000\u0000\u0000"+
		"LM\u0005\r\u0000\u0000M\t\u0001\u0000\u0000\u0000NO\u0003\u000e\u0007"+
		"\u0000O\u000b\u0001\u0000\u0000\u0000PS\u0005\b\u0000\u0000QR\u0005\u0004"+
		"\u0000\u0000RT\u0003\u000e\u0007\u0000SQ\u0001\u0000\u0000\u0000ST\u0001"+
		"\u0000\u0000\u0000TX\u0001\u0000\u0000\u0000UX\u0005\t\u0000\u0000VX\u0005"+
		"\u0007\u0000\u0000WP\u0001\u0000\u0000\u0000WU\u0001\u0000\u0000\u0000"+
		"WV\u0001\u0000\u0000\u0000X\r\u0001\u0000\u0000\u0000Y^\u0005\n\u0000"+
		"\u0000Z^\u0005\r\u0000\u0000[^\u0005\u000b\u0000\u0000\\^\u0003\u0006"+
		"\u0003\u0000]Y\u0001\u0000\u0000\u0000]Z\u0001\u0000\u0000\u0000][\u0001"+
		"\u0000\u0000\u0000]\\\u0001\u0000\u0000\u0000^\u000f\u0001\u0000\u0000"+
		"\u0000\u000f\u0011\u0015\u0019#%(02=AEJSW]";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}