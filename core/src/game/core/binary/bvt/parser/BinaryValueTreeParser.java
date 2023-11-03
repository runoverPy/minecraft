// Generated from /home/eric/IdeaProjects/lutum/core/src/game/core/binary/parser/BinaryValueTree.g4 by ANTLR 4.12.0

package game.core.binary.bvt.parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class BinaryValueTreeParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, BIN_ARRAY=41, HEX_ARRAY=42, STRING=43, NUMBER=44, 
		IDENT=45, COMMENT=46, EOL=47, WS=48;
	public static final int
		RULE_file = 0, RULE_head = 1, RULE_setting = 2, RULE_typeset = 3, RULE_typedef = 4, 
		RULE_enumdef = 5, RULE_coerce = 6, RULE_value = 7, RULE_literal = 8, RULE_tuple = 9, 
		RULE_array = 10, RULE_binarray = 11, RULE_bytearray = 12, RULE_shortarray = 13, 
		RULE_intarray = 14, RULE_longarray = 15, RULE_floatarray = 16, RULE_doublearray = 17, 
		RULE_object = 18, RULE_kvpair = 19, RULE_ident = 20, RULE_number = 21, 
		RULE_string = 22;
	private static String[] makeRuleNames() {
		return new String[] {
			"file", "head", "setting", "typeset", "typedef", "enumdef", "coerce", 
			"value", "literal", "tuple", "array", "binarray", "bytearray", "shortarray", 
			"intarray", "longarray", "floatarray", "doublearray", "object", "kvpair", 
			"ident", "number", "string"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'#['", "','", "']'", "'itype('", "'BYTE'", "'SHORT'", "'INT'", 
			"'LONG'", "'i8'", "'i16'", "'i32'", "'i64'", "')'", "'ftype('", "'FLOAT'", 
			"'DOUBLE'", "'f32'", "'f64'", "'type'", "'{'", "'}'", "'enum'", "'coerce('", 
			"'true'", "'false'", "'null'", "'['", "']B'", "']i8'", "']S'", "']i16'", 
			"']I'", "']i32'", "']L'", "']i64'", "']F'", "']f32'", "']D'", "']f64'", 
			"':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, "BIN_ARRAY", "HEX_ARRAY", "STRING", "NUMBER", 
			"IDENT", "COMMENT", "EOL", "WS"
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
	public String getGrammarFileName() { return "BinaryValueTree.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public BinaryValueTreeParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FileContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TerminalNode EOF() { return getToken(BinaryValueTreeParser.EOF, 0); }
		public HeadContext head() {
			return getRuleContext(HeadContext.class,0);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitFile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitFile(this);
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
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(46);
				head();
				}
			}

			setState(49);
			value();
			setState(50);
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
	public static class HeadContext extends ParserRuleContext {
		public List<SettingContext> setting() {
			return getRuleContexts(SettingContext.class);
		}
		public SettingContext setting(int i) {
			return getRuleContext(SettingContext.class,i);
		}
		public HeadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_head; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterHead(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitHead(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitHead(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeadContext head() throws RecognitionException {
		HeadContext _localctx = new HeadContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_head);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			match(T__0);
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 13123600L) != 0)) {
				{
				setState(53);
				setting();
				setState(58);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(54);
						match(T__1);
						setState(55);
						setting();
						}
						} 
					}
					setState(60);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				}
				}
			}

			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(63);
				match(T__1);
				}
			}

			setState(66);
			match(T__2);
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
	public static class SettingContext extends ParserRuleContext {
		public TypesetContext typeset() {
			return getRuleContext(TypesetContext.class,0);
		}
		public TypedefContext typedef() {
			return getRuleContext(TypedefContext.class,0);
		}
		public EnumdefContext enumdef() {
			return getRuleContext(EnumdefContext.class,0);
		}
		public CoerceContext coerce() {
			return getRuleContext(CoerceContext.class,0);
		}
		public SettingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setting; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterSetting(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitSetting(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitSetting(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SettingContext setting() throws RecognitionException {
		SettingContext _localctx = new SettingContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_setting);
		try {
			setState(72);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
			case T__13:
				enterOuterAlt(_localctx, 1);
				{
				setState(68);
				typeset();
				}
				break;
			case T__18:
				enterOuterAlt(_localctx, 2);
				{
				setState(69);
				typedef();
				}
				break;
			case T__21:
				enterOuterAlt(_localctx, 3);
				{
				setState(70);
				enumdef();
				}
				break;
			case T__22:
				enterOuterAlt(_localctx, 4);
				{
				setState(71);
				coerce();
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
	public static class TypesetContext extends ParserRuleContext {
		public Token type;
		public TypesetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeset; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterTypeset(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitTypeset(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitTypeset(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypesetContext typeset() throws RecognitionException {
		TypesetContext _localctx = new TypesetContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_typeset);
		int _la;
		try {
			setState(80);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				enterOuterAlt(_localctx, 1);
				{
				setState(74);
				match(T__3);
				setState(75);
				((TypesetContext)_localctx).type = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 8160L) != 0)) ) {
					((TypesetContext)_localctx).type = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(76);
				match(T__12);
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 2);
				{
				setState(77);
				match(T__13);
				setState(78);
				((TypesetContext)_localctx).type = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 491520L) != 0)) ) {
					((TypesetContext)_localctx).type = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(79);
				match(T__12);
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
	public static class TypedefContext extends ParserRuleContext {
		public Token name;
		public TerminalNode IDENT() { return getToken(BinaryValueTreeParser.IDENT, 0); }
		public TypedefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typedef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterTypedef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitTypedef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitTypedef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypedefContext typedef() throws RecognitionException {
		TypedefContext _localctx = new TypedefContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_typedef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(T__18);
			setState(83);
			((TypedefContext)_localctx).name = match(IDENT);
			setState(84);
			match(T__19);
			setState(85);
			match(T__20);
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
	public static class EnumdefContext extends ParserRuleContext {
		public Token name;
		public List<TerminalNode> IDENT() { return getTokens(BinaryValueTreeParser.IDENT); }
		public TerminalNode IDENT(int i) {
			return getToken(BinaryValueTreeParser.IDENT, i);
		}
		public EnumdefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumdef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterEnumdef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitEnumdef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitEnumdef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumdefContext enumdef() throws RecognitionException {
		EnumdefContext _localctx = new EnumdefContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_enumdef);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			match(T__21);
			setState(88);
			((EnumdefContext)_localctx).name = match(IDENT);
			setState(89);
			match(T__19);
			setState(98);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENT) {
				{
				setState(90);
				match(IDENT);
				setState(95);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(91);
						match(T__1);
						setState(92);
						match(IDENT);
						}
						} 
					}
					setState(97);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
				}
				}
			}

			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(100);
				match(T__1);
				}
			}

			setState(103);
			match(T__20);
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
	public static class CoerceContext extends ParserRuleContext {
		public CoerceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_coerce; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterCoerce(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitCoerce(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitCoerce(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CoerceContext coerce() throws RecognitionException {
		CoerceContext _localctx = new CoerceContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_coerce);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			match(T__22);
			setState(106);
			_la = _input.LA(1);
			if ( !(_la==T__23 || _la==T__24) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(107);
			match(T__12);
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
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public TupleContext tuple() {
			return getRuleContext(TupleContext.class,0);
		}
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_value);
		try {
			setState(115);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(109);
				literal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(110);
				array();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(111);
				tuple();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(112);
				object();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(113);
				number();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(114);
				string();
				}
				break;
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
	public static class LiteralContext extends ParserRuleContext {
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
	 
		public LiteralContext() { }
		public void copyFrom(LiteralContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NullContext extends LiteralContext {
		public NullContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterNull(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitNull(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitNull(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TrueContext extends LiteralContext {
		public TrueContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterTrue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitTrue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitTrue(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FalseContext extends LiteralContext {
		public FalseContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterFalse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitFalse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitFalse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_literal);
		try {
			setState(120);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__25:
				_localctx = new NullContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(117);
				match(T__25);
				}
				break;
			case T__24:
				_localctx = new FalseContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(118);
				match(T__24);
				}
				break;
			case T__23:
				_localctx = new TrueContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(119);
				match(T__23);
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
	public static class TupleContext extends ParserRuleContext {
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public TupleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tuple; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterTuple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitTuple(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitTuple(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TupleContext tuple() throws RecognitionException {
		TupleContext _localctx = new TupleContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_tuple);
		int _la;
		try {
			int _alt;
			setState(138);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(122);
				match(T__26);
				setState(123);
				match(T__2);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(124);
				match(T__26);
				setState(125);
				value();
				setState(130);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(126);
						match(T__1);
						setState(127);
						value();
						}
						} 
					}
					setState(132);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
				}
				setState(134);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__1) {
					{
					setState(133);
					match(T__1);
					}
				}

				setState(136);
				match(T__2);
				}
				break;
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
	public static class ArrayContext extends ParserRuleContext {
		public BinarrayContext binarray() {
			return getRuleContext(BinarrayContext.class,0);
		}
		public BytearrayContext bytearray() {
			return getRuleContext(BytearrayContext.class,0);
		}
		public ShortarrayContext shortarray() {
			return getRuleContext(ShortarrayContext.class,0);
		}
		public IntarrayContext intarray() {
			return getRuleContext(IntarrayContext.class,0);
		}
		public LongarrayContext longarray() {
			return getRuleContext(LongarrayContext.class,0);
		}
		public FloatarrayContext floatarray() {
			return getRuleContext(FloatarrayContext.class,0);
		}
		public DoublearrayContext doublearray() {
			return getRuleContext(DoublearrayContext.class,0);
		}
		public ArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayContext array() throws RecognitionException {
		ArrayContext _localctx = new ArrayContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_array);
		try {
			setState(147);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(140);
				binarray();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(141);
				bytearray();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(142);
				shortarray();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(143);
				intarray();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(144);
				longarray();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(145);
				floatarray();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(146);
				doublearray();
				}
				break;
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
	public static class BinarrayContext extends ParserRuleContext {
		public TerminalNode BIN_ARRAY() { return getToken(BinaryValueTreeParser.BIN_ARRAY, 0); }
		public TerminalNode HEX_ARRAY() { return getToken(BinaryValueTreeParser.HEX_ARRAY, 0); }
		public BinarrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binarray; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterBinarray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitBinarray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitBinarray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinarrayContext binarray() throws RecognitionException {
		BinarrayContext _localctx = new BinarrayContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_binarray);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			_la = _input.LA(1);
			if ( !(_la==BIN_ARRAY || _la==HEX_ARRAY) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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
	public static class BytearrayContext extends ParserRuleContext {
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public BytearrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bytearray; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterBytearray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitBytearray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitBytearray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BytearrayContext bytearray() throws RecognitionException {
		BytearrayContext _localctx = new BytearrayContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_bytearray);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			match(T__26);
			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUMBER) {
				{
				setState(152);
				number();
				setState(157);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(153);
						match(T__1);
						setState(154);
						number();
						}
						} 
					}
					setState(159);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
				}
				}
			}

			setState(163);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(162);
				match(T__1);
				}
			}

			setState(165);
			_la = _input.LA(1);
			if ( !(_la==T__27 || _la==T__28) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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
	public static class ShortarrayContext extends ParserRuleContext {
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public ShortarrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shortarray; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterShortarray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitShortarray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitShortarray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShortarrayContext shortarray() throws RecognitionException {
		ShortarrayContext _localctx = new ShortarrayContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_shortarray);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			match(T__26);
			setState(176);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUMBER) {
				{
				setState(168);
				number();
				setState(173);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(169);
						match(T__1);
						setState(170);
						number();
						}
						} 
					}
					setState(175);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
				}
				}
			}

			setState(179);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(178);
				match(T__1);
				}
			}

			setState(181);
			_la = _input.LA(1);
			if ( !(_la==T__29 || _la==T__30) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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
	public static class IntarrayContext extends ParserRuleContext {
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public IntarrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intarray; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterIntarray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitIntarray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitIntarray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntarrayContext intarray() throws RecognitionException {
		IntarrayContext _localctx = new IntarrayContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_intarray);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			match(T__26);
			setState(192);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUMBER) {
				{
				setState(184);
				number();
				setState(189);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(185);
						match(T__1);
						setState(186);
						number();
						}
						} 
					}
					setState(191);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
				}
				}
			}

			setState(195);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(194);
				match(T__1);
				}
			}

			setState(197);
			_la = _input.LA(1);
			if ( !(_la==T__31 || _la==T__32) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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
	public static class LongarrayContext extends ParserRuleContext {
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public LongarrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_longarray; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterLongarray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitLongarray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitLongarray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LongarrayContext longarray() throws RecognitionException {
		LongarrayContext _localctx = new LongarrayContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_longarray);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			match(T__26);
			setState(208);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUMBER) {
				{
				setState(200);
				number();
				setState(205);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(201);
						match(T__1);
						setState(202);
						number();
						}
						} 
					}
					setState(207);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
				}
				}
			}

			setState(211);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(210);
				match(T__1);
				}
			}

			setState(213);
			_la = _input.LA(1);
			if ( !(_la==T__33 || _la==T__34) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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
	public static class FloatarrayContext extends ParserRuleContext {
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public FloatarrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_floatarray; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterFloatarray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitFloatarray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitFloatarray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FloatarrayContext floatarray() throws RecognitionException {
		FloatarrayContext _localctx = new FloatarrayContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_floatarray);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(215);
			match(T__26);
			setState(224);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUMBER) {
				{
				setState(216);
				number();
				setState(221);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(217);
						match(T__1);
						setState(218);
						number();
						}
						} 
					}
					setState(223);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
				}
				}
			}

			setState(227);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(226);
				match(T__1);
				}
			}

			setState(229);
			_la = _input.LA(1);
			if ( !(_la==T__35 || _la==T__36) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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
	public static class DoublearrayContext extends ParserRuleContext {
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public DoublearrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doublearray; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterDoublearray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitDoublearray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitDoublearray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DoublearrayContext doublearray() throws RecognitionException {
		DoublearrayContext _localctx = new DoublearrayContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_doublearray);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			match(T__26);
			setState(240);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUMBER) {
				{
				setState(232);
				number();
				setState(237);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(233);
						match(T__1);
						setState(234);
						number();
						}
						} 
					}
					setState(239);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
				}
				}
			}

			setState(243);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(242);
				match(T__1);
				}
			}

			setState(245);
			_la = _input.LA(1);
			if ( !(_la==T__37 || _la==T__38) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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
	public static class ObjectContext extends ParserRuleContext {
		public List<KvpairContext> kvpair() {
			return getRuleContexts(KvpairContext.class);
		}
		public KvpairContext kvpair(int i) {
			return getRuleContext(KvpairContext.class,i);
		}
		public ObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitObject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitObject(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjectContext object() throws RecognitionException {
		ObjectContext _localctx = new ObjectContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_object);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			match(T__19);
			setState(256);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STRING || _la==IDENT) {
				{
				setState(248);
				kvpair();
				setState(253);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(249);
						match(T__1);
						setState(250);
						kvpair();
						}
						} 
					}
					setState(255);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
				}
				}
			}

			setState(259);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(258);
				match(T__1);
				}
			}

			setState(261);
			match(T__20);
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
	public static class KvpairContext extends ParserRuleContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public KvpairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_kvpair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterKvpair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitKvpair(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitKvpair(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KvpairContext kvpair() throws RecognitionException {
		KvpairContext _localctx = new KvpairContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_kvpair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(263);
			ident();
			setState(264);
			match(T__39);
			setState(265);
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
	public static class IdentContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(BinaryValueTreeParser.IDENT, 0); }
		public TerminalNode STRING() { return getToken(BinaryValueTreeParser.STRING, 0); }
		public IdentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ident; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterIdent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitIdent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitIdent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentContext ident() throws RecognitionException {
		IdentContext _localctx = new IdentContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_ident);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(267);
			_la = _input.LA(1);
			if ( !(_la==STRING || _la==IDENT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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
	public static class NumberContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(BinaryValueTreeParser.NUMBER, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269);
			match(NUMBER);
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
	public static class StringContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(BinaryValueTreeParser.STRING, 0); }
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(271);
			match(STRING);
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

	public static final String _serializedATN =
		"\u0004\u00010\u0112\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0001\u0000\u0003\u00000\b\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0005\u00019\b\u0001\n\u0001\f\u0001<\t\u0001\u0003\u0001>\b\u0001\u0001"+
		"\u0001\u0003\u0001A\b\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0003\u0002I\b\u0002\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003Q\b"+
		"\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005"+
		"\u0005^\b\u0005\n\u0005\f\u0005a\t\u0005\u0003\u0005c\b\u0005\u0001\u0005"+
		"\u0003\u0005f\b\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0003\u0007t\b\u0007\u0001\b\u0001\b\u0001\b"+
		"\u0003\by\b\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0005\t"+
		"\u0081\b\t\n\t\f\t\u0084\t\t\u0001\t\u0003\t\u0087\b\t\u0001\t\u0001\t"+
		"\u0003\t\u008b\b\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0003\n\u0094\b\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0005\f\u009c\b\f\n\f\f\f\u009f\t\f\u0003\f\u00a1\b\f\u0001\f\u0003"+
		"\f\u00a4\b\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0005\r\u00ac"+
		"\b\r\n\r\f\r\u00af\t\r\u0003\r\u00b1\b\r\u0001\r\u0003\r\u00b4\b\r\u0001"+
		"\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000e"+
		"\u00bc\b\u000e\n\u000e\f\u000e\u00bf\t\u000e\u0003\u000e\u00c1\b\u000e"+
		"\u0001\u000e\u0003\u000e\u00c4\b\u000e\u0001\u000e\u0001\u000e\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u00cc\b\u000f\n\u000f"+
		"\f\u000f\u00cf\t\u000f\u0003\u000f\u00d1\b\u000f\u0001\u000f\u0003\u000f"+
		"\u00d4\b\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0005\u0010\u00dc\b\u0010\n\u0010\f\u0010\u00df\t\u0010\u0003"+
		"\u0010\u00e1\b\u0010\u0001\u0010\u0003\u0010\u00e4\b\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0005\u0011"+
		"\u00ec\b\u0011\n\u0011\f\u0011\u00ef\t\u0011\u0003\u0011\u00f1\b\u0011"+
		"\u0001\u0011\u0003\u0011\u00f4\b\u0011\u0001\u0011\u0001\u0011\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0005\u0012\u00fc\b\u0012\n\u0012"+
		"\f\u0012\u00ff\t\u0012\u0003\u0012\u0101\b\u0012\u0001\u0012\u0003\u0012"+
		"\u0104\b\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0000\u0000\u0017\u0000\u0002\u0004\u0006\b\n"+
		"\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,\u0000"+
		"\u000b\u0001\u0000\u0005\f\u0001\u0000\u000f\u0012\u0001\u0000\u0018\u0019"+
		"\u0001\u0000)*\u0001\u0000\u001c\u001d\u0001\u0000\u001e\u001f\u0001\u0000"+
		" !\u0001\u0000\"#\u0001\u0000$%\u0001\u0000&\'\u0002\u0000++--\u012a\u0000"+
		"/\u0001\u0000\u0000\u0000\u00024\u0001\u0000\u0000\u0000\u0004H\u0001"+
		"\u0000\u0000\u0000\u0006P\u0001\u0000\u0000\u0000\bR\u0001\u0000\u0000"+
		"\u0000\nW\u0001\u0000\u0000\u0000\fi\u0001\u0000\u0000\u0000\u000es\u0001"+
		"\u0000\u0000\u0000\u0010x\u0001\u0000\u0000\u0000\u0012\u008a\u0001\u0000"+
		"\u0000\u0000\u0014\u0093\u0001\u0000\u0000\u0000\u0016\u0095\u0001\u0000"+
		"\u0000\u0000\u0018\u0097\u0001\u0000\u0000\u0000\u001a\u00a7\u0001\u0000"+
		"\u0000\u0000\u001c\u00b7\u0001\u0000\u0000\u0000\u001e\u00c7\u0001\u0000"+
		"\u0000\u0000 \u00d7\u0001\u0000\u0000\u0000\"\u00e7\u0001\u0000\u0000"+
		"\u0000$\u00f7\u0001\u0000\u0000\u0000&\u0107\u0001\u0000\u0000\u0000("+
		"\u010b\u0001\u0000\u0000\u0000*\u010d\u0001\u0000\u0000\u0000,\u010f\u0001"+
		"\u0000\u0000\u0000.0\u0003\u0002\u0001\u0000/.\u0001\u0000\u0000\u0000"+
		"/0\u0001\u0000\u0000\u000001\u0001\u0000\u0000\u000012\u0003\u000e\u0007"+
		"\u000023\u0005\u0000\u0000\u00013\u0001\u0001\u0000\u0000\u00004=\u0005"+
		"\u0001\u0000\u00005:\u0003\u0004\u0002\u000067\u0005\u0002\u0000\u0000"+
		"79\u0003\u0004\u0002\u000086\u0001\u0000\u0000\u00009<\u0001\u0000\u0000"+
		"\u0000:8\u0001\u0000\u0000\u0000:;\u0001\u0000\u0000\u0000;>\u0001\u0000"+
		"\u0000\u0000<:\u0001\u0000\u0000\u0000=5\u0001\u0000\u0000\u0000=>\u0001"+
		"\u0000\u0000\u0000>@\u0001\u0000\u0000\u0000?A\u0005\u0002\u0000\u0000"+
		"@?\u0001\u0000\u0000\u0000@A\u0001\u0000\u0000\u0000AB\u0001\u0000\u0000"+
		"\u0000BC\u0005\u0003\u0000\u0000C\u0003\u0001\u0000\u0000\u0000DI\u0003"+
		"\u0006\u0003\u0000EI\u0003\b\u0004\u0000FI\u0003\n\u0005\u0000GI\u0003"+
		"\f\u0006\u0000HD\u0001\u0000\u0000\u0000HE\u0001\u0000\u0000\u0000HF\u0001"+
		"\u0000\u0000\u0000HG\u0001\u0000\u0000\u0000I\u0005\u0001\u0000\u0000"+
		"\u0000JK\u0005\u0004\u0000\u0000KL\u0007\u0000\u0000\u0000LQ\u0005\r\u0000"+
		"\u0000MN\u0005\u000e\u0000\u0000NO\u0007\u0001\u0000\u0000OQ\u0005\r\u0000"+
		"\u0000PJ\u0001\u0000\u0000\u0000PM\u0001\u0000\u0000\u0000Q\u0007\u0001"+
		"\u0000\u0000\u0000RS\u0005\u0013\u0000\u0000ST\u0005-\u0000\u0000TU\u0005"+
		"\u0014\u0000\u0000UV\u0005\u0015\u0000\u0000V\t\u0001\u0000\u0000\u0000"+
		"WX\u0005\u0016\u0000\u0000XY\u0005-\u0000\u0000Yb\u0005\u0014\u0000\u0000"+
		"Z_\u0005-\u0000\u0000[\\\u0005\u0002\u0000\u0000\\^\u0005-\u0000\u0000"+
		"][\u0001\u0000\u0000\u0000^a\u0001\u0000\u0000\u0000_]\u0001\u0000\u0000"+
		"\u0000_`\u0001\u0000\u0000\u0000`c\u0001\u0000\u0000\u0000a_\u0001\u0000"+
		"\u0000\u0000bZ\u0001\u0000\u0000\u0000bc\u0001\u0000\u0000\u0000ce\u0001"+
		"\u0000\u0000\u0000df\u0005\u0002\u0000\u0000ed\u0001\u0000\u0000\u0000"+
		"ef\u0001\u0000\u0000\u0000fg\u0001\u0000\u0000\u0000gh\u0005\u0015\u0000"+
		"\u0000h\u000b\u0001\u0000\u0000\u0000ij\u0005\u0017\u0000\u0000jk\u0007"+
		"\u0002\u0000\u0000kl\u0005\r\u0000\u0000l\r\u0001\u0000\u0000\u0000mt"+
		"\u0003\u0010\b\u0000nt\u0003\u0014\n\u0000ot\u0003\u0012\t\u0000pt\u0003"+
		"$\u0012\u0000qt\u0003*\u0015\u0000rt\u0003,\u0016\u0000sm\u0001\u0000"+
		"\u0000\u0000sn\u0001\u0000\u0000\u0000so\u0001\u0000\u0000\u0000sp\u0001"+
		"\u0000\u0000\u0000sq\u0001\u0000\u0000\u0000sr\u0001\u0000\u0000\u0000"+
		"t\u000f\u0001\u0000\u0000\u0000uy\u0005\u001a\u0000\u0000vy\u0005\u0019"+
		"\u0000\u0000wy\u0005\u0018\u0000\u0000xu\u0001\u0000\u0000\u0000xv\u0001"+
		"\u0000\u0000\u0000xw\u0001\u0000\u0000\u0000y\u0011\u0001\u0000\u0000"+
		"\u0000z{\u0005\u001b\u0000\u0000{\u008b\u0005\u0003\u0000\u0000|}\u0005"+
		"\u001b\u0000\u0000}\u0082\u0003\u000e\u0007\u0000~\u007f\u0005\u0002\u0000"+
		"\u0000\u007f\u0081\u0003\u000e\u0007\u0000\u0080~\u0001\u0000\u0000\u0000"+
		"\u0081\u0084\u0001\u0000\u0000\u0000\u0082\u0080\u0001\u0000\u0000\u0000"+
		"\u0082\u0083\u0001\u0000\u0000\u0000\u0083\u0086\u0001\u0000\u0000\u0000"+
		"\u0084\u0082\u0001\u0000\u0000\u0000\u0085\u0087\u0005\u0002\u0000\u0000"+
		"\u0086\u0085\u0001\u0000\u0000\u0000\u0086\u0087\u0001\u0000\u0000\u0000"+
		"\u0087\u0088\u0001\u0000\u0000\u0000\u0088\u0089\u0005\u0003\u0000\u0000"+
		"\u0089\u008b\u0001\u0000\u0000\u0000\u008az\u0001\u0000\u0000\u0000\u008a"+
		"|\u0001\u0000\u0000\u0000\u008b\u0013\u0001\u0000\u0000\u0000\u008c\u0094"+
		"\u0003\u0016\u000b\u0000\u008d\u0094\u0003\u0018\f\u0000\u008e\u0094\u0003"+
		"\u001a\r\u0000\u008f\u0094\u0003\u001c\u000e\u0000\u0090\u0094\u0003\u001e"+
		"\u000f\u0000\u0091\u0094\u0003 \u0010\u0000\u0092\u0094\u0003\"\u0011"+
		"\u0000\u0093\u008c\u0001\u0000\u0000\u0000\u0093\u008d\u0001\u0000\u0000"+
		"\u0000\u0093\u008e\u0001\u0000\u0000\u0000\u0093\u008f\u0001\u0000\u0000"+
		"\u0000\u0093\u0090\u0001\u0000\u0000\u0000\u0093\u0091\u0001\u0000\u0000"+
		"\u0000\u0093\u0092\u0001\u0000\u0000\u0000\u0094\u0015\u0001\u0000\u0000"+
		"\u0000\u0095\u0096\u0007\u0003\u0000\u0000\u0096\u0017\u0001\u0000\u0000"+
		"\u0000\u0097\u00a0\u0005\u001b\u0000\u0000\u0098\u009d\u0003*\u0015\u0000"+
		"\u0099\u009a\u0005\u0002\u0000\u0000\u009a\u009c\u0003*\u0015\u0000\u009b"+
		"\u0099\u0001\u0000\u0000\u0000\u009c\u009f\u0001\u0000\u0000\u0000\u009d"+
		"\u009b\u0001\u0000\u0000\u0000\u009d\u009e\u0001\u0000\u0000\u0000\u009e"+
		"\u00a1\u0001\u0000\u0000\u0000\u009f\u009d\u0001\u0000\u0000\u0000\u00a0"+
		"\u0098\u0001\u0000\u0000\u0000\u00a0\u00a1\u0001\u0000\u0000\u0000\u00a1"+
		"\u00a3\u0001\u0000\u0000\u0000\u00a2\u00a4\u0005\u0002\u0000\u0000\u00a3"+
		"\u00a2\u0001\u0000\u0000\u0000\u00a3\u00a4\u0001\u0000\u0000\u0000\u00a4"+
		"\u00a5\u0001\u0000\u0000\u0000\u00a5\u00a6\u0007\u0004\u0000\u0000\u00a6"+
		"\u0019\u0001\u0000\u0000\u0000\u00a7\u00b0\u0005\u001b\u0000\u0000\u00a8"+
		"\u00ad\u0003*\u0015\u0000\u00a9\u00aa\u0005\u0002\u0000\u0000\u00aa\u00ac"+
		"\u0003*\u0015\u0000\u00ab\u00a9\u0001\u0000\u0000\u0000\u00ac\u00af\u0001"+
		"\u0000\u0000\u0000\u00ad\u00ab\u0001\u0000\u0000\u0000\u00ad\u00ae\u0001"+
		"\u0000\u0000\u0000\u00ae\u00b1\u0001\u0000\u0000\u0000\u00af\u00ad\u0001"+
		"\u0000\u0000\u0000\u00b0\u00a8\u0001\u0000\u0000\u0000\u00b0\u00b1\u0001"+
		"\u0000\u0000\u0000\u00b1\u00b3\u0001\u0000\u0000\u0000\u00b2\u00b4\u0005"+
		"\u0002\u0000\u0000\u00b3\u00b2\u0001\u0000\u0000\u0000\u00b3\u00b4\u0001"+
		"\u0000\u0000\u0000\u00b4\u00b5\u0001\u0000\u0000\u0000\u00b5\u00b6\u0007"+
		"\u0005\u0000\u0000\u00b6\u001b\u0001\u0000\u0000\u0000\u00b7\u00c0\u0005"+
		"\u001b\u0000\u0000\u00b8\u00bd\u0003*\u0015\u0000\u00b9\u00ba\u0005\u0002"+
		"\u0000\u0000\u00ba\u00bc\u0003*\u0015\u0000\u00bb\u00b9\u0001\u0000\u0000"+
		"\u0000\u00bc\u00bf\u0001\u0000\u0000\u0000\u00bd\u00bb\u0001\u0000\u0000"+
		"\u0000\u00bd\u00be\u0001\u0000\u0000\u0000\u00be\u00c1\u0001\u0000\u0000"+
		"\u0000\u00bf\u00bd\u0001\u0000\u0000\u0000\u00c0\u00b8\u0001\u0000\u0000"+
		"\u0000\u00c0\u00c1\u0001\u0000\u0000\u0000\u00c1\u00c3\u0001\u0000\u0000"+
		"\u0000\u00c2\u00c4\u0005\u0002\u0000\u0000\u00c3\u00c2\u0001\u0000\u0000"+
		"\u0000\u00c3\u00c4\u0001\u0000\u0000\u0000\u00c4\u00c5\u0001\u0000\u0000"+
		"\u0000\u00c5\u00c6\u0007\u0006\u0000\u0000\u00c6\u001d\u0001\u0000\u0000"+
		"\u0000\u00c7\u00d0\u0005\u001b\u0000\u0000\u00c8\u00cd\u0003*\u0015\u0000"+
		"\u00c9\u00ca\u0005\u0002\u0000\u0000\u00ca\u00cc\u0003*\u0015\u0000\u00cb"+
		"\u00c9\u0001\u0000\u0000\u0000\u00cc\u00cf\u0001\u0000\u0000\u0000\u00cd"+
		"\u00cb\u0001\u0000\u0000\u0000\u00cd\u00ce\u0001\u0000\u0000\u0000\u00ce"+
		"\u00d1\u0001\u0000\u0000\u0000\u00cf\u00cd\u0001\u0000\u0000\u0000\u00d0"+
		"\u00c8\u0001\u0000\u0000\u0000\u00d0\u00d1\u0001\u0000\u0000\u0000\u00d1"+
		"\u00d3\u0001\u0000\u0000\u0000\u00d2\u00d4\u0005\u0002\u0000\u0000\u00d3"+
		"\u00d2\u0001\u0000\u0000\u0000\u00d3\u00d4\u0001\u0000\u0000\u0000\u00d4"+
		"\u00d5\u0001\u0000\u0000\u0000\u00d5\u00d6\u0007\u0007\u0000\u0000\u00d6"+
		"\u001f\u0001\u0000\u0000\u0000\u00d7\u00e0\u0005\u001b\u0000\u0000\u00d8"+
		"\u00dd\u0003*\u0015\u0000\u00d9\u00da\u0005\u0002\u0000\u0000\u00da\u00dc"+
		"\u0003*\u0015\u0000\u00db\u00d9\u0001\u0000\u0000\u0000\u00dc\u00df\u0001"+
		"\u0000\u0000\u0000\u00dd\u00db\u0001\u0000\u0000\u0000\u00dd\u00de\u0001"+
		"\u0000\u0000\u0000\u00de\u00e1\u0001\u0000\u0000\u0000\u00df\u00dd\u0001"+
		"\u0000\u0000\u0000\u00e0\u00d8\u0001\u0000\u0000\u0000\u00e0\u00e1\u0001"+
		"\u0000\u0000\u0000\u00e1\u00e3\u0001\u0000\u0000\u0000\u00e2\u00e4\u0005"+
		"\u0002\u0000\u0000\u00e3\u00e2\u0001\u0000\u0000\u0000\u00e3\u00e4\u0001"+
		"\u0000\u0000\u0000\u00e4\u00e5\u0001\u0000\u0000\u0000\u00e5\u00e6\u0007"+
		"\b\u0000\u0000\u00e6!\u0001\u0000\u0000\u0000\u00e7\u00f0\u0005\u001b"+
		"\u0000\u0000\u00e8\u00ed\u0003*\u0015\u0000\u00e9\u00ea\u0005\u0002\u0000"+
		"\u0000\u00ea\u00ec\u0003*\u0015\u0000\u00eb\u00e9\u0001\u0000\u0000\u0000"+
		"\u00ec\u00ef\u0001\u0000\u0000\u0000\u00ed\u00eb\u0001\u0000\u0000\u0000"+
		"\u00ed\u00ee\u0001\u0000\u0000\u0000\u00ee\u00f1\u0001\u0000\u0000\u0000"+
		"\u00ef\u00ed\u0001\u0000\u0000\u0000\u00f0\u00e8\u0001\u0000\u0000\u0000"+
		"\u00f0\u00f1\u0001\u0000\u0000\u0000\u00f1\u00f3\u0001\u0000\u0000\u0000"+
		"\u00f2\u00f4\u0005\u0002\u0000\u0000\u00f3\u00f2\u0001\u0000\u0000\u0000"+
		"\u00f3\u00f4\u0001\u0000\u0000\u0000\u00f4\u00f5\u0001\u0000\u0000\u0000"+
		"\u00f5\u00f6\u0007\t\u0000\u0000\u00f6#\u0001\u0000\u0000\u0000\u00f7"+
		"\u0100\u0005\u0014\u0000\u0000\u00f8\u00fd\u0003&\u0013\u0000\u00f9\u00fa"+
		"\u0005\u0002\u0000\u0000\u00fa\u00fc\u0003&\u0013\u0000\u00fb\u00f9\u0001"+
		"\u0000\u0000\u0000\u00fc\u00ff\u0001\u0000\u0000\u0000\u00fd\u00fb\u0001"+
		"\u0000\u0000\u0000\u00fd\u00fe\u0001\u0000\u0000\u0000\u00fe\u0101\u0001"+
		"\u0000\u0000\u0000\u00ff\u00fd\u0001\u0000\u0000\u0000\u0100\u00f8\u0001"+
		"\u0000\u0000\u0000\u0100\u0101\u0001\u0000\u0000\u0000\u0101\u0103\u0001"+
		"\u0000\u0000\u0000\u0102\u0104\u0005\u0002\u0000\u0000\u0103\u0102\u0001"+
		"\u0000\u0000\u0000\u0103\u0104\u0001\u0000\u0000\u0000\u0104\u0105\u0001"+
		"\u0000\u0000\u0000\u0105\u0106\u0005\u0015\u0000\u0000\u0106%\u0001\u0000"+
		"\u0000\u0000\u0107\u0108\u0003(\u0014\u0000\u0108\u0109\u0005(\u0000\u0000"+
		"\u0109\u010a\u0003\u000e\u0007\u0000\u010a\'\u0001\u0000\u0000\u0000\u010b"+
		"\u010c\u0007\n\u0000\u0000\u010c)\u0001\u0000\u0000\u0000\u010d\u010e"+
		"\u0005,\u0000\u0000\u010e+\u0001\u0000\u0000\u0000\u010f\u0110\u0005+"+
		"\u0000\u0000\u0110-\u0001\u0000\u0000\u0000$/:=@HP_besx\u0082\u0086\u008a"+
		"\u0093\u009d\u00a0\u00a3\u00ad\u00b0\u00b3\u00bd\u00c0\u00c3\u00cd\u00d0"+
		"\u00d3\u00dd\u00e0\u00e3\u00ed\u00f0\u00f3\u00fd\u0100\u0103";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}