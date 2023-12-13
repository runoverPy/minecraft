// Generated from /home/eric/Documents/coding/IdeaProjects/Minecraft/core/src/game/core/binary/bvt/parserV1/BinaryValueTree.g4 by ANTLR 4.13.1
package game.core.binary.bvt.parserV1;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class BinaryValueTreeParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, BIN_ARRAY=48, HEX_ARRAY=49, STRING=50, WHOLE_NUMBER=51, 
		BYTE_NUMBER=52, SHORT_NUMBER=53, INT_NUMBER=54, LONG_NUMBER=55, POINT_NUMBER=56, 
		FLOAT_NUMBER=57, DOUBLE_NUMBER=58, IDENT=59, COMMENT=60, EOL=61, WS=62;
	public static final int
		RULE_file = 0, RULE_head = 1, RULE_setting = 2, RULE_typeset = 3, RULE_typedef = 4, 
		RULE_enumdef = 5, RULE_coerce = 6, RULE_value = 7, RULE_literal = 8, RULE_tuple = 9, 
		RULE_array = 10, RULE_binArray = 11, RULE_byteArray = 12, RULE_shortArray = 13, 
		RULE_intArray = 14, RULE_longArray = 15, RULE_floatArray = 16, RULE_doubleArray = 17, 
		RULE_object = 18, RULE_kvpair = 19, RULE_ident = 20, RULE_number = 21, 
		RULE_wholeNumber = 22, RULE_byteNumber = 23, RULE_shortNumber = 24, RULE_intNumber = 25, 
		RULE_longNumber = 26, RULE_pointNumber = 27, RULE_floatNumber = 28, RULE_doubleNumber = 29, 
		RULE_string = 30;
	private static String[] makeRuleNames() {
		return new String[] {
			"file", "head", "setting", "typeset", "typedef", "enumdef", "coerce", 
			"value", "literal", "tuple", "array", "binArray", "byteArray", "shortArray", 
			"intArray", "longArray", "floatArray", "doubleArray", "object", "kvpair", 
			"ident", "number", "wholeNumber", "byteNumber", "shortNumber", "intNumber", 
			"longNumber", "pointNumber", "floatNumber", "doubleNumber", "string"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'#['", "']'", "'itype('", "'BYTE'", "'SHORT'", "'INT'", "'LONG'", 
			"'i8'", "'i16'", "'i32'", "'i64'", "')'", "'ftype('", "'FLOAT'", "'DOUBLE'", 
			"'f32'", "'f64'", "'type'", "'{'", "'}'", "'enum'", "','", "'coerce('", 
			"'true'", "'false'", "'null'", "'['", "'('", "'B['", "'i8['", "'B'", 
			"':'", "'S['", "'i16['", "'S'", "'I['", "'i32['", "'I'", "'L['", "'i64['", 
			"'L'", "'F['", "'f32['", "'F'", "'D['", "'f64['", "'D'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"BIN_ARRAY", "HEX_ARRAY", "STRING", "WHOLE_NUMBER", "BYTE_NUMBER", "SHORT_NUMBER", 
			"INT_NUMBER", "LONG_NUMBER", "POINT_NUMBER", "FLOAT_NUMBER", "DOUBLE_NUMBER", 
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
		public TerminalNode EOF() { return getToken(BinaryValueTreeParser.EOF, 0); }
		public HeadContext head() {
			return getRuleContext(HeadContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
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
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(62);
				head();
				}
			}

			setState(66);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 576299907909419008L) != 0)) {
				{
				setState(65);
				value();
				}
			}

			setState(68);
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
			enterOuterAlt(_localctx, 1);
			{
			setState(74); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(70);
				match(T__0);
				setState(71);
				setting();
				setState(72);
				match(T__1);
				}
				}
				setState(76); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__0 );
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
			setState(82);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
			case T__12:
				enterOuterAlt(_localctx, 1);
				{
				setState(78);
				typeset();
				}
				break;
			case T__17:
				enterOuterAlt(_localctx, 2);
				{
				setState(79);
				typedef();
				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 3);
				{
				setState(80);
				enumdef();
				}
				break;
			case T__22:
				enterOuterAlt(_localctx, 4);
				{
				setState(81);
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
			setState(90);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(84);
				match(T__2);
				setState(85);
				((TypesetContext)_localctx).type = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4080L) != 0)) ) {
					((TypesetContext)_localctx).type = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(86);
				match(T__11);
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 2);
				{
				setState(87);
				match(T__12);
				setState(88);
				((TypesetContext)_localctx).type = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 245760L) != 0)) ) {
					((TypesetContext)_localctx).type = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(89);
				match(T__11);
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
			setState(92);
			match(T__17);
			setState(93);
			((TypedefContext)_localctx).name = match(IDENT);
			setState(94);
			match(T__18);
			setState(95);
			match(T__19);
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
			setState(97);
			match(T__20);
			setState(98);
			((EnumdefContext)_localctx).name = match(IDENT);
			setState(99);
			match(T__18);
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENT) {
				{
				setState(100);
				match(IDENT);
				setState(105);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(101);
						match(T__21);
						setState(102);
						match(IDENT);
						}
						} 
					}
					setState(107);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
				}
				}
			}

			setState(111);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__21) {
				{
				setState(110);
				match(T__21);
				}
			}

			setState(113);
			match(T__19);
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
			setState(115);
			match(T__22);
			setState(116);
			_la = _input.LA(1);
			if ( !(_la==T__23 || _la==T__24) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(117);
			match(T__11);
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
			setState(125);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(119);
				literal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(120);
				array();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(121);
				tuple();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(122);
				object();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(123);
				number();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(124);
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

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_literal);
		try {
			setState(130);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__25:
				_localctx = new NullContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(127);
				match(T__25);
				}
				break;
			case T__24:
				_localctx = new FalseContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(128);
				match(T__24);
				}
				break;
			case T__23:
				_localctx = new TrueContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(129);
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
			setState(164);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(132);
				match(T__26);
				setState(133);
				match(T__1);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(134);
				match(T__26);
				setState(135);
				value();
				setState(140);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(136);
						match(T__21);
						setState(137);
						value();
						}
						} 
					}
					setState(142);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				}
				setState(144);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__21) {
					{
					setState(143);
					match(T__21);
					}
				}

				setState(146);
				match(T__1);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(148);
				match(T__27);
				setState(149);
				match(T__11);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(150);
				match(T__27);
				setState(151);
				value();
				setState(156);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(152);
						match(T__21);
						setState(153);
						value();
						}
						} 
					}
					setState(158);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
				}
				setState(160);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__21) {
					{
					setState(159);
					match(T__21);
					}
				}

				setState(162);
				match(T__11);
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
		public BinArrayContext binArray() {
			return getRuleContext(BinArrayContext.class,0);
		}
		public ByteArrayContext byteArray() {
			return getRuleContext(ByteArrayContext.class,0);
		}
		public ShortArrayContext shortArray() {
			return getRuleContext(ShortArrayContext.class,0);
		}
		public IntArrayContext intArray() {
			return getRuleContext(IntArrayContext.class,0);
		}
		public LongArrayContext longArray() {
			return getRuleContext(LongArrayContext.class,0);
		}
		public FloatArrayContext floatArray() {
			return getRuleContext(FloatArrayContext.class,0);
		}
		public DoubleArrayContext doubleArray() {
			return getRuleContext(DoubleArrayContext.class,0);
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
			setState(173);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(166);
				binArray();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(167);
				byteArray();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(168);
				shortArray();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(169);
				intArray();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(170);
				longArray();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(171);
				floatArray();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(172);
				doubleArray();
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
	public static class BinArrayContext extends ParserRuleContext {
		public TerminalNode BIN_ARRAY() { return getToken(BinaryValueTreeParser.BIN_ARRAY, 0); }
		public TerminalNode HEX_ARRAY() { return getToken(BinaryValueTreeParser.HEX_ARRAY, 0); }
		public BinArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binArray; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterBinArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitBinArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitBinArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinArrayContext binArray() throws RecognitionException {
		BinArrayContext _localctx = new BinArrayContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_binArray);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
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
	public static class ByteArrayContext extends ParserRuleContext {
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public ByteArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_byteArray; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterByteArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitByteArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitByteArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ByteArrayContext byteArray() throws RecognitionException {
		ByteArrayContext _localctx = new ByteArrayContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_byteArray);
		int _la;
		try {
			int _alt;
			setState(209);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__28:
			case T__29:
				enterOuterAlt(_localctx, 1);
				{
				setState(177);
				_la = _input.LA(1);
				if ( !(_la==T__28 || _la==T__29) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(186);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 574208952489738240L) != 0)) {
					{
					setState(178);
					number();
					setState(183);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(179);
							match(T__21);
							setState(180);
							number();
							}
							} 
						}
						setState(185);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
					}
					}
				}

				setState(189);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__21) {
					{
					setState(188);
					match(T__21);
					}
				}

				setState(191);
				match(T__1);
				}
				break;
			case T__26:
				enterOuterAlt(_localctx, 2);
				{
				setState(192);
				match(T__26);
				setState(193);
				_la = _input.LA(1);
				if ( !(_la==T__7 || _la==T__30) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(194);
				match(T__31);
				setState(203);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 574208952489738240L) != 0)) {
					{
					setState(195);
					number();
					setState(200);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(196);
							match(T__21);
							setState(197);
							number();
							}
							} 
						}
						setState(202);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
					}
					}
				}

				setState(206);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__21) {
					{
					setState(205);
					match(T__21);
					}
				}

				setState(208);
				match(T__1);
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
	public static class ShortArrayContext extends ParserRuleContext {
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public ShortArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shortArray; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterShortArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitShortArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitShortArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShortArrayContext shortArray() throws RecognitionException {
		ShortArrayContext _localctx = new ShortArrayContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_shortArray);
		int _la;
		try {
			int _alt;
			setState(243);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__32:
			case T__33:
				enterOuterAlt(_localctx, 1);
				{
				setState(211);
				_la = _input.LA(1);
				if ( !(_la==T__32 || _la==T__33) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(220);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 574208952489738240L) != 0)) {
					{
					setState(212);
					number();
					setState(217);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(213);
							match(T__21);
							setState(214);
							number();
							}
							} 
						}
						setState(219);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
					}
					}
				}

				setState(223);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__21) {
					{
					setState(222);
					match(T__21);
					}
				}

				setState(225);
				match(T__1);
				}
				break;
			case T__26:
				enterOuterAlt(_localctx, 2);
				{
				setState(226);
				match(T__26);
				setState(227);
				_la = _input.LA(1);
				if ( !(_la==T__8 || _la==T__34) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(228);
				match(T__31);
				setState(237);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 574208952489738240L) != 0)) {
					{
					setState(229);
					number();
					setState(234);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(230);
							match(T__21);
							setState(231);
							number();
							}
							} 
						}
						setState(236);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
					}
					}
				}

				setState(240);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__21) {
					{
					setState(239);
					match(T__21);
					}
				}

				setState(242);
				match(T__1);
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
	public static class IntArrayContext extends ParserRuleContext {
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public IntArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intArray; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterIntArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitIntArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitIntArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntArrayContext intArray() throws RecognitionException {
		IntArrayContext _localctx = new IntArrayContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_intArray);
		int _la;
		try {
			int _alt;
			setState(277);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__35:
			case T__36:
				enterOuterAlt(_localctx, 1);
				{
				setState(245);
				_la = _input.LA(1);
				if ( !(_la==T__35 || _la==T__36) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(254);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 574208952489738240L) != 0)) {
					{
					setState(246);
					number();
					setState(251);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(247);
							match(T__21);
							setState(248);
							number();
							}
							} 
						}
						setState(253);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
					}
					}
				}

				setState(257);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__21) {
					{
					setState(256);
					match(T__21);
					}
				}

				setState(259);
				match(T__1);
				}
				break;
			case T__26:
				enterOuterAlt(_localctx, 2);
				{
				setState(260);
				match(T__26);
				setState(261);
				_la = _input.LA(1);
				if ( !(_la==T__9 || _la==T__37) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(262);
				match(T__31);
				setState(271);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 574208952489738240L) != 0)) {
					{
					setState(263);
					number();
					setState(268);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(264);
							match(T__21);
							setState(265);
							number();
							}
							} 
						}
						setState(270);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
					}
					}
				}

				setState(274);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__21) {
					{
					setState(273);
					match(T__21);
					}
				}

				setState(276);
				match(T__1);
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
	public static class LongArrayContext extends ParserRuleContext {
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public LongArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_longArray; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterLongArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitLongArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitLongArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LongArrayContext longArray() throws RecognitionException {
		LongArrayContext _localctx = new LongArrayContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_longArray);
		int _la;
		try {
			int _alt;
			setState(311);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__38:
			case T__39:
				enterOuterAlt(_localctx, 1);
				{
				setState(279);
				_la = _input.LA(1);
				if ( !(_la==T__38 || _la==T__39) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(288);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 574208952489738240L) != 0)) {
					{
					setState(280);
					number();
					setState(285);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(281);
							match(T__21);
							setState(282);
							number();
							}
							} 
						}
						setState(287);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
					}
					}
				}

				setState(291);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__21) {
					{
					setState(290);
					match(T__21);
					}
				}

				setState(293);
				match(T__1);
				}
				break;
			case T__26:
				enterOuterAlt(_localctx, 2);
				{
				setState(294);
				match(T__26);
				setState(295);
				_la = _input.LA(1);
				if ( !(_la==T__10 || _la==T__40) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(296);
				match(T__31);
				setState(305);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 574208952489738240L) != 0)) {
					{
					setState(297);
					number();
					setState(302);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(298);
							match(T__21);
							setState(299);
							number();
							}
							} 
						}
						setState(304);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
					}
					}
				}

				setState(308);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__21) {
					{
					setState(307);
					match(T__21);
					}
				}

				setState(310);
				match(T__1);
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
	public static class FloatArrayContext extends ParserRuleContext {
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public FloatArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_floatArray; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterFloatArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitFloatArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitFloatArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FloatArrayContext floatArray() throws RecognitionException {
		FloatArrayContext _localctx = new FloatArrayContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_floatArray);
		int _la;
		try {
			int _alt;
			setState(345);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__41:
			case T__42:
				enterOuterAlt(_localctx, 1);
				{
				setState(313);
				_la = _input.LA(1);
				if ( !(_la==T__41 || _la==T__42) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(322);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 574208952489738240L) != 0)) {
					{
					setState(314);
					number();
					setState(319);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,44,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(315);
							match(T__21);
							setState(316);
							number();
							}
							} 
						}
						setState(321);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,44,_ctx);
					}
					}
				}

				setState(325);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__21) {
					{
					setState(324);
					match(T__21);
					}
				}

				setState(327);
				match(T__1);
				}
				break;
			case T__26:
				enterOuterAlt(_localctx, 2);
				{
				setState(328);
				match(T__26);
				setState(329);
				_la = _input.LA(1);
				if ( !(_la==T__15 || _la==T__43) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(330);
				match(T__31);
				setState(339);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 574208952489738240L) != 0)) {
					{
					setState(331);
					number();
					setState(336);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,47,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(332);
							match(T__21);
							setState(333);
							number();
							}
							} 
						}
						setState(338);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,47,_ctx);
					}
					}
				}

				setState(342);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__21) {
					{
					setState(341);
					match(T__21);
					}
				}

				setState(344);
				match(T__1);
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
	public static class DoubleArrayContext extends ParserRuleContext {
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public DoubleArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doubleArray; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterDoubleArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitDoubleArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitDoubleArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DoubleArrayContext doubleArray() throws RecognitionException {
		DoubleArrayContext _localctx = new DoubleArrayContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_doubleArray);
		int _la;
		try {
			int _alt;
			setState(379);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__44:
			case T__45:
				enterOuterAlt(_localctx, 1);
				{
				setState(347);
				_la = _input.LA(1);
				if ( !(_la==T__44 || _la==T__45) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(356);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 574208952489738240L) != 0)) {
					{
					setState(348);
					number();
					setState(353);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,51,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(349);
							match(T__21);
							setState(350);
							number();
							}
							} 
						}
						setState(355);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,51,_ctx);
					}
					}
				}

				setState(359);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__21) {
					{
					setState(358);
					match(T__21);
					}
				}

				setState(361);
				match(T__1);
				}
				break;
			case T__26:
				enterOuterAlt(_localctx, 2);
				{
				setState(362);
				match(T__26);
				setState(363);
				_la = _input.LA(1);
				if ( !(_la==T__16 || _la==T__46) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(364);
				match(T__31);
				setState(373);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 574208952489738240L) != 0)) {
					{
					setState(365);
					number();
					setState(370);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(366);
							match(T__21);
							setState(367);
							number();
							}
							} 
						}
						setState(372);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
					}
					}
				}

				setState(376);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__21) {
					{
					setState(375);
					match(T__21);
					}
				}

				setState(378);
				match(T__1);
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
			setState(381);
			match(T__18);
			setState(390);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STRING || _la==IDENT) {
				{
				setState(382);
				kvpair();
				setState(387);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,58,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(383);
						match(T__21);
						setState(384);
						kvpair();
						}
						} 
					}
					setState(389);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,58,_ctx);
				}
				}
			}

			setState(393);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__21) {
				{
				setState(392);
				match(T__21);
				}
			}

			setState(395);
			match(T__19);
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
			setState(397);
			ident();
			setState(398);
			match(T__31);
			setState(399);
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
			setState(401);
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
		public WholeNumberContext wholeNumber() {
			return getRuleContext(WholeNumberContext.class,0);
		}
		public ByteNumberContext byteNumber() {
			return getRuleContext(ByteNumberContext.class,0);
		}
		public ShortNumberContext shortNumber() {
			return getRuleContext(ShortNumberContext.class,0);
		}
		public IntNumberContext intNumber() {
			return getRuleContext(IntNumberContext.class,0);
		}
		public LongNumberContext longNumber() {
			return getRuleContext(LongNumberContext.class,0);
		}
		public PointNumberContext pointNumber() {
			return getRuleContext(PointNumberContext.class,0);
		}
		public FloatNumberContext floatNumber() {
			return getRuleContext(FloatNumberContext.class,0);
		}
		public DoubleNumberContext doubleNumber() {
			return getRuleContext(DoubleNumberContext.class,0);
		}
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
			setState(411);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WHOLE_NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(403);
				wholeNumber();
				}
				break;
			case BYTE_NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(404);
				byteNumber();
				}
				break;
			case SHORT_NUMBER:
				enterOuterAlt(_localctx, 3);
				{
				setState(405);
				shortNumber();
				}
				break;
			case INT_NUMBER:
				enterOuterAlt(_localctx, 4);
				{
				setState(406);
				intNumber();
				}
				break;
			case LONG_NUMBER:
				enterOuterAlt(_localctx, 5);
				{
				setState(407);
				longNumber();
				}
				break;
			case POINT_NUMBER:
				enterOuterAlt(_localctx, 6);
				{
				setState(408);
				pointNumber();
				}
				break;
			case FLOAT_NUMBER:
				enterOuterAlt(_localctx, 7);
				{
				setState(409);
				floatNumber();
				}
				break;
			case DOUBLE_NUMBER:
				enterOuterAlt(_localctx, 8);
				{
				setState(410);
				doubleNumber();
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
	public static class WholeNumberContext extends ParserRuleContext {
		public TerminalNode WHOLE_NUMBER() { return getToken(BinaryValueTreeParser.WHOLE_NUMBER, 0); }
		public WholeNumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wholeNumber; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterWholeNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitWholeNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitWholeNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WholeNumberContext wholeNumber() throws RecognitionException {
		WholeNumberContext _localctx = new WholeNumberContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_wholeNumber);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(413);
			match(WHOLE_NUMBER);
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
	public static class ByteNumberContext extends ParserRuleContext {
		public TerminalNode BYTE_NUMBER() { return getToken(BinaryValueTreeParser.BYTE_NUMBER, 0); }
		public ByteNumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_byteNumber; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterByteNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitByteNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitByteNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ByteNumberContext byteNumber() throws RecognitionException {
		ByteNumberContext _localctx = new ByteNumberContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_byteNumber);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(415);
			match(BYTE_NUMBER);
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
	public static class ShortNumberContext extends ParserRuleContext {
		public TerminalNode SHORT_NUMBER() { return getToken(BinaryValueTreeParser.SHORT_NUMBER, 0); }
		public ShortNumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shortNumber; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterShortNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitShortNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitShortNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShortNumberContext shortNumber() throws RecognitionException {
		ShortNumberContext _localctx = new ShortNumberContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_shortNumber);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(417);
			match(SHORT_NUMBER);
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
	public static class IntNumberContext extends ParserRuleContext {
		public TerminalNode INT_NUMBER() { return getToken(BinaryValueTreeParser.INT_NUMBER, 0); }
		public IntNumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intNumber; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterIntNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitIntNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitIntNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntNumberContext intNumber() throws RecognitionException {
		IntNumberContext _localctx = new IntNumberContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_intNumber);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(419);
			match(INT_NUMBER);
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
	public static class LongNumberContext extends ParserRuleContext {
		public TerminalNode LONG_NUMBER() { return getToken(BinaryValueTreeParser.LONG_NUMBER, 0); }
		public LongNumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_longNumber; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterLongNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitLongNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitLongNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LongNumberContext longNumber() throws RecognitionException {
		LongNumberContext _localctx = new LongNumberContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_longNumber);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(421);
			match(LONG_NUMBER);
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
	public static class PointNumberContext extends ParserRuleContext {
		public TerminalNode POINT_NUMBER() { return getToken(BinaryValueTreeParser.POINT_NUMBER, 0); }
		public PointNumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pointNumber; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterPointNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitPointNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitPointNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PointNumberContext pointNumber() throws RecognitionException {
		PointNumberContext _localctx = new PointNumberContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_pointNumber);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(423);
			match(POINT_NUMBER);
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
	public static class FloatNumberContext extends ParserRuleContext {
		public TerminalNode FLOAT_NUMBER() { return getToken(BinaryValueTreeParser.FLOAT_NUMBER, 0); }
		public FloatNumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_floatNumber; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterFloatNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitFloatNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitFloatNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FloatNumberContext floatNumber() throws RecognitionException {
		FloatNumberContext _localctx = new FloatNumberContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_floatNumber);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(425);
			match(FLOAT_NUMBER);
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
	public static class DoubleNumberContext extends ParserRuleContext {
		public TerminalNode DOUBLE_NUMBER() { return getToken(BinaryValueTreeParser.DOUBLE_NUMBER, 0); }
		public DoubleNumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doubleNumber; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).enterDoubleNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BinaryValueTreeListener ) ((BinaryValueTreeListener)listener).exitDoubleNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BinaryValueTreeVisitor ) return ((BinaryValueTreeVisitor<? extends T>)visitor).visitDoubleNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DoubleNumberContext doubleNumber() throws RecognitionException {
		DoubleNumberContext _localctx = new DoubleNumberContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_doubleNumber);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(427);
			match(DOUBLE_NUMBER);
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
		enterRule(_localctx, 60, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(429);
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
		"\u0004\u0001>\u01b0\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0001\u0000\u0003\u0000@\b\u0000\u0001\u0000\u0003\u0000C\b\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0004"+
		"\u0001K\b\u0001\u000b\u0001\f\u0001L\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0003\u0002S\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003[\b\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005h\b\u0005"+
		"\n\u0005\f\u0005k\t\u0005\u0003\u0005m\b\u0005\u0001\u0005\u0003\u0005"+
		"p\b\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0003\u0007~\b\u0007\u0001\b\u0001\b\u0001\b\u0003\b\u0083"+
		"\b\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0005\t\u008b\b\t"+
		"\n\t\f\t\u008e\t\t\u0001\t\u0003\t\u0091\b\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0005\t\u009b\b\t\n\t\f\t\u009e\t\t"+
		"\u0001\t\u0003\t\u00a1\b\t\u0001\t\u0001\t\u0003\t\u00a5\b\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u00ae\b\n\u0001\u000b"+
		"\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0005\f\u00b6\b\f\n\f\f\f"+
		"\u00b9\t\f\u0003\f\u00bb\b\f\u0001\f\u0003\f\u00be\b\f\u0001\f\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0005\f\u00c7\b\f\n\f\f\f\u00ca"+
		"\t\f\u0003\f\u00cc\b\f\u0001\f\u0003\f\u00cf\b\f\u0001\f\u0003\f\u00d2"+
		"\b\f\u0001\r\u0001\r\u0001\r\u0001\r\u0005\r\u00d8\b\r\n\r\f\r\u00db\t"+
		"\r\u0003\r\u00dd\b\r\u0001\r\u0003\r\u00e0\b\r\u0001\r\u0001\r\u0001\r"+
		"\u0001\r\u0001\r\u0001\r\u0001\r\u0005\r\u00e9\b\r\n\r\f\r\u00ec\t\r\u0003"+
		"\r\u00ee\b\r\u0001\r\u0003\r\u00f1\b\r\u0001\r\u0003\r\u00f4\b\r\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u00fa\b\u000e\n"+
		"\u000e\f\u000e\u00fd\t\u000e\u0003\u000e\u00ff\b\u000e\u0001\u000e\u0003"+
		"\u000e\u0102\b\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u010b\b\u000e\n\u000e\f\u000e"+
		"\u010e\t\u000e\u0003\u000e\u0110\b\u000e\u0001\u000e\u0003\u000e\u0113"+
		"\b\u000e\u0001\u000e\u0003\u000e\u0116\b\u000e\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0005\u000f\u011c\b\u000f\n\u000f\f\u000f\u011f"+
		"\t\u000f\u0003\u000f\u0121\b\u000f\u0001\u000f\u0003\u000f\u0124\b\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0005\u000f\u012d\b\u000f\n\u000f\f\u000f\u0130\t\u000f\u0003"+
		"\u000f\u0132\b\u000f\u0001\u000f\u0003\u000f\u0135\b\u000f\u0001\u000f"+
		"\u0003\u000f\u0138\b\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0005\u0010\u013e\b\u0010\n\u0010\f\u0010\u0141\t\u0010\u0003\u0010\u0143"+
		"\b\u0010\u0001\u0010\u0003\u0010\u0146\b\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010"+
		"\u014f\b\u0010\n\u0010\f\u0010\u0152\t\u0010\u0003\u0010\u0154\b\u0010"+
		"\u0001\u0010\u0003\u0010\u0157\b\u0010\u0001\u0010\u0003\u0010\u015a\b"+
		"\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0005\u0011\u0160"+
		"\b\u0011\n\u0011\f\u0011\u0163\t\u0011\u0003\u0011\u0165\b\u0011\u0001"+
		"\u0011\u0003\u0011\u0168\b\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0005\u0011\u0171\b\u0011\n"+
		"\u0011\f\u0011\u0174\t\u0011\u0003\u0011\u0176\b\u0011\u0001\u0011\u0003"+
		"\u0011\u0179\b\u0011\u0001\u0011\u0003\u0011\u017c\b\u0011\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0005\u0012\u0182\b\u0012\n\u0012"+
		"\f\u0012\u0185\t\u0012\u0003\u0012\u0187\b\u0012\u0001\u0012\u0003\u0012"+
		"\u018a\b\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015"+
		"\u019c\b\u0015\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0018"+
		"\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001b"+
		"\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0000\u0000\u001f\u0000\u0002\u0004\u0006\b\n"+
		"\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.0246"+
		"8:<\u0000\u0011\u0001\u0000\u0004\u000b\u0001\u0000\u000e\u0011\u0001"+
		"\u0000\u0018\u0019\u0001\u000001\u0001\u0000\u001d\u001e\u0002\u0000\b"+
		"\b\u001f\u001f\u0001\u0000!\"\u0002\u0000\t\t##\u0001\u0000$%\u0002\u0000"+
		"\n\n&&\u0001\u0000\'(\u0002\u0000\u000b\u000b))\u0001\u0000*+\u0002\u0000"+
		"\u0010\u0010,,\u0001\u0000-.\u0002\u0000\u0011\u0011//\u0002\u000022;"+
		";\u01e2\u0000?\u0001\u0000\u0000\u0000\u0002J\u0001\u0000\u0000\u0000"+
		"\u0004R\u0001\u0000\u0000\u0000\u0006Z\u0001\u0000\u0000\u0000\b\\\u0001"+
		"\u0000\u0000\u0000\na\u0001\u0000\u0000\u0000\fs\u0001\u0000\u0000\u0000"+
		"\u000e}\u0001\u0000\u0000\u0000\u0010\u0082\u0001\u0000\u0000\u0000\u0012"+
		"\u00a4\u0001\u0000\u0000\u0000\u0014\u00ad\u0001\u0000\u0000\u0000\u0016"+
		"\u00af\u0001\u0000\u0000\u0000\u0018\u00d1\u0001\u0000\u0000\u0000\u001a"+
		"\u00f3\u0001\u0000\u0000\u0000\u001c\u0115\u0001\u0000\u0000\u0000\u001e"+
		"\u0137\u0001\u0000\u0000\u0000 \u0159\u0001\u0000\u0000\u0000\"\u017b"+
		"\u0001\u0000\u0000\u0000$\u017d\u0001\u0000\u0000\u0000&\u018d\u0001\u0000"+
		"\u0000\u0000(\u0191\u0001\u0000\u0000\u0000*\u019b\u0001\u0000\u0000\u0000"+
		",\u019d\u0001\u0000\u0000\u0000.\u019f\u0001\u0000\u0000\u00000\u01a1"+
		"\u0001\u0000\u0000\u00002\u01a3\u0001\u0000\u0000\u00004\u01a5\u0001\u0000"+
		"\u0000\u00006\u01a7\u0001\u0000\u0000\u00008\u01a9\u0001\u0000\u0000\u0000"+
		":\u01ab\u0001\u0000\u0000\u0000<\u01ad\u0001\u0000\u0000\u0000>@\u0003"+
		"\u0002\u0001\u0000?>\u0001\u0000\u0000\u0000?@\u0001\u0000\u0000\u0000"+
		"@B\u0001\u0000\u0000\u0000AC\u0003\u000e\u0007\u0000BA\u0001\u0000\u0000"+
		"\u0000BC\u0001\u0000\u0000\u0000CD\u0001\u0000\u0000\u0000DE\u0005\u0000"+
		"\u0000\u0001E\u0001\u0001\u0000\u0000\u0000FG\u0005\u0001\u0000\u0000"+
		"GH\u0003\u0004\u0002\u0000HI\u0005\u0002\u0000\u0000IK\u0001\u0000\u0000"+
		"\u0000JF\u0001\u0000\u0000\u0000KL\u0001\u0000\u0000\u0000LJ\u0001\u0000"+
		"\u0000\u0000LM\u0001\u0000\u0000\u0000M\u0003\u0001\u0000\u0000\u0000"+
		"NS\u0003\u0006\u0003\u0000OS\u0003\b\u0004\u0000PS\u0003\n\u0005\u0000"+
		"QS\u0003\f\u0006\u0000RN\u0001\u0000\u0000\u0000RO\u0001\u0000\u0000\u0000"+
		"RP\u0001\u0000\u0000\u0000RQ\u0001\u0000\u0000\u0000S\u0005\u0001\u0000"+
		"\u0000\u0000TU\u0005\u0003\u0000\u0000UV\u0007\u0000\u0000\u0000V[\u0005"+
		"\f\u0000\u0000WX\u0005\r\u0000\u0000XY\u0007\u0001\u0000\u0000Y[\u0005"+
		"\f\u0000\u0000ZT\u0001\u0000\u0000\u0000ZW\u0001\u0000\u0000\u0000[\u0007"+
		"\u0001\u0000\u0000\u0000\\]\u0005\u0012\u0000\u0000]^\u0005;\u0000\u0000"+
		"^_\u0005\u0013\u0000\u0000_`\u0005\u0014\u0000\u0000`\t\u0001\u0000\u0000"+
		"\u0000ab\u0005\u0015\u0000\u0000bc\u0005;\u0000\u0000cl\u0005\u0013\u0000"+
		"\u0000di\u0005;\u0000\u0000ef\u0005\u0016\u0000\u0000fh\u0005;\u0000\u0000"+
		"ge\u0001\u0000\u0000\u0000hk\u0001\u0000\u0000\u0000ig\u0001\u0000\u0000"+
		"\u0000ij\u0001\u0000\u0000\u0000jm\u0001\u0000\u0000\u0000ki\u0001\u0000"+
		"\u0000\u0000ld\u0001\u0000\u0000\u0000lm\u0001\u0000\u0000\u0000mo\u0001"+
		"\u0000\u0000\u0000np\u0005\u0016\u0000\u0000on\u0001\u0000\u0000\u0000"+
		"op\u0001\u0000\u0000\u0000pq\u0001\u0000\u0000\u0000qr\u0005\u0014\u0000"+
		"\u0000r\u000b\u0001\u0000\u0000\u0000st\u0005\u0017\u0000\u0000tu\u0007"+
		"\u0002\u0000\u0000uv\u0005\f\u0000\u0000v\r\u0001\u0000\u0000\u0000w~"+
		"\u0003\u0010\b\u0000x~\u0003\u0014\n\u0000y~\u0003\u0012\t\u0000z~\u0003"+
		"$\u0012\u0000{~\u0003*\u0015\u0000|~\u0003<\u001e\u0000}w\u0001\u0000"+
		"\u0000\u0000}x\u0001\u0000\u0000\u0000}y\u0001\u0000\u0000\u0000}z\u0001"+
		"\u0000\u0000\u0000}{\u0001\u0000\u0000\u0000}|\u0001\u0000\u0000\u0000"+
		"~\u000f\u0001\u0000\u0000\u0000\u007f\u0083\u0005\u001a\u0000\u0000\u0080"+
		"\u0083\u0005\u0019\u0000\u0000\u0081\u0083\u0005\u0018\u0000\u0000\u0082"+
		"\u007f\u0001\u0000\u0000\u0000\u0082\u0080\u0001\u0000\u0000\u0000\u0082"+
		"\u0081\u0001\u0000\u0000\u0000\u0083\u0011\u0001\u0000\u0000\u0000\u0084"+
		"\u0085\u0005\u001b\u0000\u0000\u0085\u00a5\u0005\u0002\u0000\u0000\u0086"+
		"\u0087\u0005\u001b\u0000\u0000\u0087\u008c\u0003\u000e\u0007\u0000\u0088"+
		"\u0089\u0005\u0016\u0000\u0000\u0089\u008b\u0003\u000e\u0007\u0000\u008a"+
		"\u0088\u0001\u0000\u0000\u0000\u008b\u008e\u0001\u0000\u0000\u0000\u008c"+
		"\u008a\u0001\u0000\u0000\u0000\u008c\u008d\u0001\u0000\u0000\u0000\u008d"+
		"\u0090\u0001\u0000\u0000\u0000\u008e\u008c\u0001\u0000\u0000\u0000\u008f"+
		"\u0091\u0005\u0016\u0000\u0000\u0090\u008f\u0001\u0000\u0000\u0000\u0090"+
		"\u0091\u0001\u0000\u0000\u0000\u0091\u0092\u0001\u0000\u0000\u0000\u0092"+
		"\u0093\u0005\u0002\u0000\u0000\u0093\u00a5\u0001\u0000\u0000\u0000\u0094"+
		"\u0095\u0005\u001c\u0000\u0000\u0095\u00a5\u0005\f\u0000\u0000\u0096\u0097"+
		"\u0005\u001c\u0000\u0000\u0097\u009c\u0003\u000e\u0007\u0000\u0098\u0099"+
		"\u0005\u0016\u0000\u0000\u0099\u009b\u0003\u000e\u0007\u0000\u009a\u0098"+
		"\u0001\u0000\u0000\u0000\u009b\u009e\u0001\u0000\u0000\u0000\u009c\u009a"+
		"\u0001\u0000\u0000\u0000\u009c\u009d\u0001\u0000\u0000\u0000\u009d\u00a0"+
		"\u0001\u0000\u0000\u0000\u009e\u009c\u0001\u0000\u0000\u0000\u009f\u00a1"+
		"\u0005\u0016\u0000\u0000\u00a0\u009f\u0001\u0000\u0000\u0000\u00a0\u00a1"+
		"\u0001\u0000\u0000\u0000\u00a1\u00a2\u0001\u0000\u0000\u0000\u00a2\u00a3"+
		"\u0005\f\u0000\u0000\u00a3\u00a5\u0001\u0000\u0000\u0000\u00a4\u0084\u0001"+
		"\u0000\u0000\u0000\u00a4\u0086\u0001\u0000\u0000\u0000\u00a4\u0094\u0001"+
		"\u0000\u0000\u0000\u00a4\u0096\u0001\u0000\u0000\u0000\u00a5\u0013\u0001"+
		"\u0000\u0000\u0000\u00a6\u00ae\u0003\u0016\u000b\u0000\u00a7\u00ae\u0003"+
		"\u0018\f\u0000\u00a8\u00ae\u0003\u001a\r\u0000\u00a9\u00ae\u0003\u001c"+
		"\u000e\u0000\u00aa\u00ae\u0003\u001e\u000f\u0000\u00ab\u00ae\u0003 \u0010"+
		"\u0000\u00ac\u00ae\u0003\"\u0011\u0000\u00ad\u00a6\u0001\u0000\u0000\u0000"+
		"\u00ad\u00a7\u0001\u0000\u0000\u0000\u00ad\u00a8\u0001\u0000\u0000\u0000"+
		"\u00ad\u00a9\u0001\u0000\u0000\u0000\u00ad\u00aa\u0001\u0000\u0000\u0000"+
		"\u00ad\u00ab\u0001\u0000\u0000\u0000\u00ad\u00ac\u0001\u0000\u0000\u0000"+
		"\u00ae\u0015\u0001\u0000\u0000\u0000\u00af\u00b0\u0007\u0003\u0000\u0000"+
		"\u00b0\u0017\u0001\u0000\u0000\u0000\u00b1\u00ba\u0007\u0004\u0000\u0000"+
		"\u00b2\u00b7\u0003*\u0015\u0000\u00b3\u00b4\u0005\u0016\u0000\u0000\u00b4"+
		"\u00b6\u0003*\u0015\u0000\u00b5\u00b3\u0001\u0000\u0000\u0000\u00b6\u00b9"+
		"\u0001\u0000\u0000\u0000\u00b7\u00b5\u0001\u0000\u0000\u0000\u00b7\u00b8"+
		"\u0001\u0000\u0000\u0000\u00b8\u00bb\u0001\u0000\u0000\u0000\u00b9\u00b7"+
		"\u0001\u0000\u0000\u0000\u00ba\u00b2\u0001\u0000\u0000\u0000\u00ba\u00bb"+
		"\u0001\u0000\u0000\u0000\u00bb\u00bd\u0001\u0000\u0000\u0000\u00bc\u00be"+
		"\u0005\u0016\u0000\u0000\u00bd\u00bc\u0001\u0000\u0000\u0000\u00bd\u00be"+
		"\u0001\u0000\u0000\u0000\u00be\u00bf\u0001\u0000\u0000\u0000\u00bf\u00d2"+
		"\u0005\u0002\u0000\u0000\u00c0\u00c1\u0005\u001b\u0000\u0000\u00c1\u00c2"+
		"\u0007\u0005\u0000\u0000\u00c2\u00cb\u0005 \u0000\u0000\u00c3\u00c8\u0003"+
		"*\u0015\u0000\u00c4\u00c5\u0005\u0016\u0000\u0000\u00c5\u00c7\u0003*\u0015"+
		"\u0000\u00c6\u00c4\u0001\u0000\u0000\u0000\u00c7\u00ca\u0001\u0000\u0000"+
		"\u0000\u00c8\u00c6\u0001\u0000\u0000\u0000\u00c8\u00c9\u0001\u0000\u0000"+
		"\u0000\u00c9\u00cc\u0001\u0000\u0000\u0000\u00ca\u00c8\u0001\u0000\u0000"+
		"\u0000\u00cb\u00c3\u0001\u0000\u0000\u0000\u00cb\u00cc\u0001\u0000\u0000"+
		"\u0000\u00cc\u00ce\u0001\u0000\u0000\u0000\u00cd\u00cf\u0005\u0016\u0000"+
		"\u0000\u00ce\u00cd\u0001\u0000\u0000\u0000\u00ce\u00cf\u0001\u0000\u0000"+
		"\u0000\u00cf\u00d0\u0001\u0000\u0000\u0000\u00d0\u00d2\u0005\u0002\u0000"+
		"\u0000\u00d1\u00b1\u0001\u0000\u0000\u0000\u00d1\u00c0\u0001\u0000\u0000"+
		"\u0000\u00d2\u0019\u0001\u0000\u0000\u0000\u00d3\u00dc\u0007\u0006\u0000"+
		"\u0000\u00d4\u00d9\u0003*\u0015\u0000\u00d5\u00d6\u0005\u0016\u0000\u0000"+
		"\u00d6\u00d8\u0003*\u0015\u0000\u00d7\u00d5\u0001\u0000\u0000\u0000\u00d8"+
		"\u00db\u0001\u0000\u0000\u0000\u00d9\u00d7\u0001\u0000\u0000\u0000\u00d9"+
		"\u00da\u0001\u0000\u0000\u0000\u00da\u00dd\u0001\u0000\u0000\u0000\u00db"+
		"\u00d9\u0001\u0000\u0000\u0000\u00dc\u00d4\u0001\u0000\u0000\u0000\u00dc"+
		"\u00dd\u0001\u0000\u0000\u0000\u00dd\u00df\u0001\u0000\u0000\u0000\u00de"+
		"\u00e0\u0005\u0016\u0000\u0000\u00df\u00de\u0001\u0000\u0000\u0000\u00df"+
		"\u00e0\u0001\u0000\u0000\u0000\u00e0\u00e1\u0001\u0000\u0000\u0000\u00e1"+
		"\u00f4\u0005\u0002\u0000\u0000\u00e2\u00e3\u0005\u001b\u0000\u0000\u00e3"+
		"\u00e4\u0007\u0007\u0000\u0000\u00e4\u00ed\u0005 \u0000\u0000\u00e5\u00ea"+
		"\u0003*\u0015\u0000\u00e6\u00e7\u0005\u0016\u0000\u0000\u00e7\u00e9\u0003"+
		"*\u0015\u0000\u00e8\u00e6\u0001\u0000\u0000\u0000\u00e9\u00ec\u0001\u0000"+
		"\u0000\u0000\u00ea\u00e8\u0001\u0000\u0000\u0000\u00ea\u00eb\u0001\u0000"+
		"\u0000\u0000\u00eb\u00ee\u0001\u0000\u0000\u0000\u00ec\u00ea\u0001\u0000"+
		"\u0000\u0000\u00ed\u00e5\u0001\u0000\u0000\u0000\u00ed\u00ee\u0001\u0000"+
		"\u0000\u0000\u00ee\u00f0\u0001\u0000\u0000\u0000\u00ef\u00f1\u0005\u0016"+
		"\u0000\u0000\u00f0\u00ef\u0001\u0000\u0000\u0000\u00f0\u00f1\u0001\u0000"+
		"\u0000\u0000\u00f1\u00f2\u0001\u0000\u0000\u0000\u00f2\u00f4\u0005\u0002"+
		"\u0000\u0000\u00f3\u00d3\u0001\u0000\u0000\u0000\u00f3\u00e2\u0001\u0000"+
		"\u0000\u0000\u00f4\u001b\u0001\u0000\u0000\u0000\u00f5\u00fe\u0007\b\u0000"+
		"\u0000\u00f6\u00fb\u0003*\u0015\u0000\u00f7\u00f8\u0005\u0016\u0000\u0000"+
		"\u00f8\u00fa\u0003*\u0015\u0000\u00f9\u00f7\u0001\u0000\u0000\u0000\u00fa"+
		"\u00fd\u0001\u0000\u0000\u0000\u00fb\u00f9\u0001\u0000\u0000\u0000\u00fb"+
		"\u00fc\u0001\u0000\u0000\u0000\u00fc\u00ff\u0001\u0000\u0000\u0000\u00fd"+
		"\u00fb\u0001\u0000\u0000\u0000\u00fe\u00f6\u0001\u0000\u0000\u0000\u00fe"+
		"\u00ff\u0001\u0000\u0000\u0000\u00ff\u0101\u0001\u0000\u0000\u0000\u0100"+
		"\u0102\u0005\u0016\u0000\u0000\u0101\u0100\u0001\u0000\u0000\u0000\u0101"+
		"\u0102\u0001\u0000\u0000\u0000\u0102\u0103\u0001\u0000\u0000\u0000\u0103"+
		"\u0116\u0005\u0002\u0000\u0000\u0104\u0105\u0005\u001b\u0000\u0000\u0105"+
		"\u0106\u0007\t\u0000\u0000\u0106\u010f\u0005 \u0000\u0000\u0107\u010c"+
		"\u0003*\u0015\u0000\u0108\u0109\u0005\u0016\u0000\u0000\u0109\u010b\u0003"+
		"*\u0015\u0000\u010a\u0108\u0001\u0000\u0000\u0000\u010b\u010e\u0001\u0000"+
		"\u0000\u0000\u010c\u010a\u0001\u0000\u0000\u0000\u010c\u010d\u0001\u0000"+
		"\u0000\u0000\u010d\u0110\u0001\u0000\u0000\u0000\u010e\u010c\u0001\u0000"+
		"\u0000\u0000\u010f\u0107\u0001\u0000\u0000\u0000\u010f\u0110\u0001\u0000"+
		"\u0000\u0000\u0110\u0112\u0001\u0000\u0000\u0000\u0111\u0113\u0005\u0016"+
		"\u0000\u0000\u0112\u0111\u0001\u0000\u0000\u0000\u0112\u0113\u0001\u0000"+
		"\u0000\u0000\u0113\u0114\u0001\u0000\u0000\u0000\u0114\u0116\u0005\u0002"+
		"\u0000\u0000\u0115\u00f5\u0001\u0000\u0000\u0000\u0115\u0104\u0001\u0000"+
		"\u0000\u0000\u0116\u001d\u0001\u0000\u0000\u0000\u0117\u0120\u0007\n\u0000"+
		"\u0000\u0118\u011d\u0003*\u0015\u0000\u0119\u011a\u0005\u0016\u0000\u0000"+
		"\u011a\u011c\u0003*\u0015\u0000\u011b\u0119\u0001\u0000\u0000\u0000\u011c"+
		"\u011f\u0001\u0000\u0000\u0000\u011d\u011b\u0001\u0000\u0000\u0000\u011d"+
		"\u011e\u0001\u0000\u0000\u0000\u011e\u0121\u0001\u0000\u0000\u0000\u011f"+
		"\u011d\u0001\u0000\u0000\u0000\u0120\u0118\u0001\u0000\u0000\u0000\u0120"+
		"\u0121\u0001\u0000\u0000\u0000\u0121\u0123\u0001\u0000\u0000\u0000\u0122"+
		"\u0124\u0005\u0016\u0000\u0000\u0123\u0122\u0001\u0000\u0000\u0000\u0123"+
		"\u0124\u0001\u0000\u0000\u0000\u0124\u0125\u0001\u0000\u0000\u0000\u0125"+
		"\u0138\u0005\u0002\u0000\u0000\u0126\u0127\u0005\u001b\u0000\u0000\u0127"+
		"\u0128\u0007\u000b\u0000\u0000\u0128\u0131\u0005 \u0000\u0000\u0129\u012e"+
		"\u0003*\u0015\u0000\u012a\u012b\u0005\u0016\u0000\u0000\u012b\u012d\u0003"+
		"*\u0015\u0000\u012c\u012a\u0001\u0000\u0000\u0000\u012d\u0130\u0001\u0000"+
		"\u0000\u0000\u012e\u012c\u0001\u0000\u0000\u0000\u012e\u012f\u0001\u0000"+
		"\u0000\u0000\u012f\u0132\u0001\u0000\u0000\u0000\u0130\u012e\u0001\u0000"+
		"\u0000\u0000\u0131\u0129\u0001\u0000\u0000\u0000\u0131\u0132\u0001\u0000"+
		"\u0000\u0000\u0132\u0134\u0001\u0000\u0000\u0000\u0133\u0135\u0005\u0016"+
		"\u0000\u0000\u0134\u0133\u0001\u0000\u0000\u0000\u0134\u0135\u0001\u0000"+
		"\u0000\u0000\u0135\u0136\u0001\u0000\u0000\u0000\u0136\u0138\u0005\u0002"+
		"\u0000\u0000\u0137\u0117\u0001\u0000\u0000\u0000\u0137\u0126\u0001\u0000"+
		"\u0000\u0000\u0138\u001f\u0001\u0000\u0000\u0000\u0139\u0142\u0007\f\u0000"+
		"\u0000\u013a\u013f\u0003*\u0015\u0000\u013b\u013c\u0005\u0016\u0000\u0000"+
		"\u013c\u013e\u0003*\u0015\u0000\u013d\u013b\u0001\u0000\u0000\u0000\u013e"+
		"\u0141\u0001\u0000\u0000\u0000\u013f\u013d\u0001\u0000\u0000\u0000\u013f"+
		"\u0140\u0001\u0000\u0000\u0000\u0140\u0143\u0001\u0000\u0000\u0000\u0141"+
		"\u013f\u0001\u0000\u0000\u0000\u0142\u013a\u0001\u0000\u0000\u0000\u0142"+
		"\u0143\u0001\u0000\u0000\u0000\u0143\u0145\u0001\u0000\u0000\u0000\u0144"+
		"\u0146\u0005\u0016\u0000\u0000\u0145\u0144\u0001\u0000\u0000\u0000\u0145"+
		"\u0146\u0001\u0000\u0000\u0000\u0146\u0147\u0001\u0000\u0000\u0000\u0147"+
		"\u015a\u0005\u0002\u0000\u0000\u0148\u0149\u0005\u001b\u0000\u0000\u0149"+
		"\u014a\u0007\r\u0000\u0000\u014a\u0153\u0005 \u0000\u0000\u014b\u0150"+
		"\u0003*\u0015\u0000\u014c\u014d\u0005\u0016\u0000\u0000\u014d\u014f\u0003"+
		"*\u0015\u0000\u014e\u014c\u0001\u0000\u0000\u0000\u014f\u0152\u0001\u0000"+
		"\u0000\u0000\u0150\u014e\u0001\u0000\u0000\u0000\u0150\u0151\u0001\u0000"+
		"\u0000\u0000\u0151\u0154\u0001\u0000\u0000\u0000\u0152\u0150\u0001\u0000"+
		"\u0000\u0000\u0153\u014b\u0001\u0000\u0000\u0000\u0153\u0154\u0001\u0000"+
		"\u0000\u0000\u0154\u0156\u0001\u0000\u0000\u0000\u0155\u0157\u0005\u0016"+
		"\u0000\u0000\u0156\u0155\u0001\u0000\u0000\u0000\u0156\u0157\u0001\u0000"+
		"\u0000\u0000\u0157\u0158\u0001\u0000\u0000\u0000\u0158\u015a\u0005\u0002"+
		"\u0000\u0000\u0159\u0139\u0001\u0000\u0000\u0000\u0159\u0148\u0001\u0000"+
		"\u0000\u0000\u015a!\u0001\u0000\u0000\u0000\u015b\u0164\u0007\u000e\u0000"+
		"\u0000\u015c\u0161\u0003*\u0015\u0000\u015d\u015e\u0005\u0016\u0000\u0000"+
		"\u015e\u0160\u0003*\u0015\u0000\u015f\u015d\u0001\u0000\u0000\u0000\u0160"+
		"\u0163\u0001\u0000\u0000\u0000\u0161\u015f\u0001\u0000\u0000\u0000\u0161"+
		"\u0162\u0001\u0000\u0000\u0000\u0162\u0165\u0001\u0000\u0000\u0000\u0163"+
		"\u0161\u0001\u0000\u0000\u0000\u0164\u015c\u0001\u0000\u0000\u0000\u0164"+
		"\u0165\u0001\u0000\u0000\u0000\u0165\u0167\u0001\u0000\u0000\u0000\u0166"+
		"\u0168\u0005\u0016\u0000\u0000\u0167\u0166\u0001\u0000\u0000\u0000\u0167"+
		"\u0168\u0001\u0000\u0000\u0000\u0168\u0169\u0001\u0000\u0000\u0000\u0169"+
		"\u017c\u0005\u0002\u0000\u0000\u016a\u016b\u0005\u001b\u0000\u0000\u016b"+
		"\u016c\u0007\u000f\u0000\u0000\u016c\u0175\u0005 \u0000\u0000\u016d\u0172"+
		"\u0003*\u0015\u0000\u016e\u016f\u0005\u0016\u0000\u0000\u016f\u0171\u0003"+
		"*\u0015\u0000\u0170\u016e\u0001\u0000\u0000\u0000\u0171\u0174\u0001\u0000"+
		"\u0000\u0000\u0172\u0170\u0001\u0000\u0000\u0000\u0172\u0173\u0001\u0000"+
		"\u0000\u0000\u0173\u0176\u0001\u0000\u0000\u0000\u0174\u0172\u0001\u0000"+
		"\u0000\u0000\u0175\u016d\u0001\u0000\u0000\u0000\u0175\u0176\u0001\u0000"+
		"\u0000\u0000\u0176\u0178\u0001\u0000\u0000\u0000\u0177\u0179\u0005\u0016"+
		"\u0000\u0000\u0178\u0177\u0001\u0000\u0000\u0000\u0178\u0179\u0001\u0000"+
		"\u0000\u0000\u0179\u017a\u0001\u0000\u0000\u0000\u017a\u017c\u0005\u0002"+
		"\u0000\u0000\u017b\u015b\u0001\u0000\u0000\u0000\u017b\u016a\u0001\u0000"+
		"\u0000\u0000\u017c#\u0001\u0000\u0000\u0000\u017d\u0186\u0005\u0013\u0000"+
		"\u0000\u017e\u0183\u0003&\u0013\u0000\u017f\u0180\u0005\u0016\u0000\u0000"+
		"\u0180\u0182\u0003&\u0013\u0000\u0181\u017f\u0001\u0000\u0000\u0000\u0182"+
		"\u0185\u0001\u0000\u0000\u0000\u0183\u0181\u0001\u0000\u0000\u0000\u0183"+
		"\u0184\u0001\u0000\u0000\u0000\u0184\u0187\u0001\u0000\u0000\u0000\u0185"+
		"\u0183\u0001\u0000\u0000\u0000\u0186\u017e\u0001\u0000\u0000\u0000\u0186"+
		"\u0187\u0001\u0000\u0000\u0000\u0187\u0189\u0001\u0000\u0000\u0000\u0188"+
		"\u018a\u0005\u0016\u0000\u0000\u0189\u0188\u0001\u0000\u0000\u0000\u0189"+
		"\u018a\u0001\u0000\u0000\u0000\u018a\u018b\u0001\u0000\u0000\u0000\u018b"+
		"\u018c\u0005\u0014\u0000\u0000\u018c%\u0001\u0000\u0000\u0000\u018d\u018e"+
		"\u0003(\u0014\u0000\u018e\u018f\u0005 \u0000\u0000\u018f\u0190\u0003\u000e"+
		"\u0007\u0000\u0190\'\u0001\u0000\u0000\u0000\u0191\u0192\u0007\u0010\u0000"+
		"\u0000\u0192)\u0001\u0000\u0000\u0000\u0193\u019c\u0003,\u0016\u0000\u0194"+
		"\u019c\u0003.\u0017\u0000\u0195\u019c\u00030\u0018\u0000\u0196\u019c\u0003"+
		"2\u0019\u0000\u0197\u019c\u00034\u001a\u0000\u0198\u019c\u00036\u001b"+
		"\u0000\u0199\u019c\u00038\u001c\u0000\u019a\u019c\u0003:\u001d\u0000\u019b"+
		"\u0193\u0001\u0000\u0000\u0000\u019b\u0194\u0001\u0000\u0000\u0000\u019b"+
		"\u0195\u0001\u0000\u0000\u0000\u019b\u0196\u0001\u0000\u0000\u0000\u019b"+
		"\u0197\u0001\u0000\u0000\u0000\u019b\u0198\u0001\u0000\u0000\u0000\u019b"+
		"\u0199\u0001\u0000\u0000\u0000\u019b\u019a\u0001\u0000\u0000\u0000\u019c"+
		"+\u0001\u0000\u0000\u0000\u019d\u019e\u00053\u0000\u0000\u019e-\u0001"+
		"\u0000\u0000\u0000\u019f\u01a0\u00054\u0000\u0000\u01a0/\u0001\u0000\u0000"+
		"\u0000\u01a1\u01a2\u00055\u0000\u0000\u01a21\u0001\u0000\u0000\u0000\u01a3"+
		"\u01a4\u00056\u0000\u0000\u01a43\u0001\u0000\u0000\u0000\u01a5\u01a6\u0005"+
		"7\u0000\u0000\u01a65\u0001\u0000\u0000\u0000\u01a7\u01a8\u00058\u0000"+
		"\u0000\u01a87\u0001\u0000\u0000\u0000\u01a9\u01aa\u00059\u0000\u0000\u01aa"+
		"9\u0001\u0000\u0000\u0000\u01ab\u01ac\u0005:\u0000\u0000\u01ac;\u0001"+
		"\u0000\u0000\u0000\u01ad\u01ae\u00052\u0000\u0000\u01ae=\u0001\u0000\u0000"+
		"\u0000>?BLRZilo}\u0082\u008c\u0090\u009c\u00a0\u00a4\u00ad\u00b7\u00ba"+
		"\u00bd\u00c8\u00cb\u00ce\u00d1\u00d9\u00dc\u00df\u00ea\u00ed\u00f0\u00f3"+
		"\u00fb\u00fe\u0101\u010c\u010f\u0112\u0115\u011d\u0120\u0123\u012e\u0131"+
		"\u0134\u0137\u013f\u0142\u0145\u0150\u0153\u0156\u0159\u0161\u0164\u0167"+
		"\u0172\u0175\u0178\u017b\u0183\u0186\u0189\u019b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}