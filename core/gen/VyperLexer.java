// Generated from /home/eric/IdeaProjects/lutum/core/src/game/core/console/vyper/Vyper.g4 by ANTLR 4.12.0

package game.core.console.vyper;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class VyperLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, ARGNAME=8, ARGFLAGS=9, 
		STRING=10, DoubleQuoteString=11, SingleQuoteString=12, NUMBER=13, READ=14, 
		WORD=15, PATH=16, RETURN_LOGIC=17, REDIRECT=18, EOL=19, WS=20;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "ARGNAME", "ARGFLAGS", 
			"STRING", "DoubleQuoteString", "SingleQuoteString", "NUMBER", "READ", 
			"WORD", "NAME", "GLOB", "FILE", "PATH", "RETURN_LOGIC", "REDIRECT", "BIN_DIGITS", 
			"OCT_DIGITS", "DEC_DIGITS", "HEX_DIGITS", "ESCAPE", "HEX", "EOL", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'|'", "'&'", "'='", "'$('", "')'", "'--'", null, null, 
			null, null, null, null, null, null, null, null, "'\\u00A3'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, "ARGNAME", "ARGFLAGS", 
			"STRING", "DoubleQuoteString", "SingleQuoteString", "NUMBER", "READ", 
			"WORD", "PATH", "RETURN_LOGIC", "REDIRECT", "EOL", "WS"
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


	boolean file;


	public VyperLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Vyper.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 18:
			PATH_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void PATH_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			System.out.println("PATH");
			break;
		}
	}

	public static final String _serializedATN =
		"\u0004\u0000\u0014\u015c\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a"+
		"\u0002\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0004\u0007P\b\u0007\u000b\u0007\f\u0007Q\u0001\b\u0001\b\u0004\bV\b"+
		"\b\u000b\b\f\bW\u0001\t\u0001\t\u0001\t\u0005\t]\b\t\n\t\f\t`\t\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0005\tf\b\t\n\t\f\ti\t\t\u0001\t\u0003\tl"+
		"\b\t\u0001\n\u0001\n\u0001\n\u0005\nq\b\n\n\n\f\nt\t\n\u0001\n\u0001\n"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0005\u000b{\b\u000b\n\u000b\f\u000b"+
		"~\t\u000b\u0001\u000b\u0001\u000b\u0001\f\u0003\f\u0083\b\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u008d\b\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0003\f\u0093\b\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003"+
		"\f\u00a1\b\f\u0001\f\u0003\f\u00a4\b\f\u0001\f\u0001\f\u0001\f\u0005\f"+
		"\u00a9\b\f\n\f\f\f\u00ac\t\f\u0003\f\u00ae\b\f\u0001\f\u0001\f\u0003\f"+
		"\u00b2\b\f\u0001\f\u0004\f\u00b5\b\f\u000b\f\f\f\u00b6\u0003\f\u00b9\b"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u00c2"+
		"\b\f\u0003\f\u00c4\b\f\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0005\u000e\u00cc\b\u000e\n\u000e\f\u000e\u00cf\t\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0004\u000e\u00d4\b\u000e\u000b\u000e\f"+
		"\u000e\u00d5\u0003\u000e\u00d8\b\u000e\u0001\u000f\u0004\u000f\u00db\b"+
		"\u000f\u000b\u000f\f\u000f\u00dc\u0001\u0010\u0003\u0010\u00e0\b\u0010"+
		"\u0001\u0010\u0001\u0010\u0003\u0010\u00e4\b\u0010\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u00eb\b\u0011\u0001\u0012"+
		"\u0003\u0012\u00ee\b\u0012\u0001\u0012\u0004\u0012\u00f1\b\u0012\u000b"+
		"\u0012\f\u0012\u00f2\u0001\u0012\u0001\u0012\u0004\u0012\u00f7\b\u0012"+
		"\u000b\u0012\f\u0012\u00f8\u0005\u0012\u00fb\b\u0012\n\u0012\f\u0012\u00fe"+
		"\t\u0012\u0001\u0012\u0003\u0012\u0101\b\u0012\u0001\u0012\u0001\u0012"+
		"\u0004\u0012\u0105\b\u0012\u000b\u0012\f\u0012\u0106\u0005\u0012\u0109"+
		"\b\u0012\n\u0012\f\u0012\u010c\t\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0003\u0012\u0111\b\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0005\u0015\u011a\b\u0015\n\u0015"+
		"\f\u0015\u011d\t\u0015\u0003\u0015\u011f\b\u0015\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0005\u0016\u0124\b\u0016\n\u0016\f\u0016\u0127\t\u0016\u0003"+
		"\u0016\u0129\b\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0005\u0017\u012e"+
		"\b\u0017\n\u0017\f\u0017\u0131\t\u0017\u0003\u0017\u0133\b\u0017\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0005\u0018\u0138\b\u0018\n\u0018\f\u0018"+
		"\u013b\t\u0018\u0003\u0018\u013d\b\u0018\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u014c"+
		"\b\u0019\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0003\u001b\u0154\b\u001b\u0001\u001c\u0004\u001c\u0157\b\u001c"+
		"\u000b\u001c\f\u001c\u0158\u0001\u001c\u0001\u001c\u0000\u0000\u001d\u0001"+
		"\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007"+
		"\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d"+
		"\u000f\u001f\u0000!\u0000#\u0000%\u0010\'\u0011)\u0012+\u0000-\u0000/"+
		"\u00001\u00003\u00005\u00007\u00139\u0014\u0001\u0000\u001b\u0002\u0000"+
		"AZaz\u0002\u0000\"\"\\\\\u0002\u0000\'\'\\\\\u0002\u0000++--\u0002\u0000"+
		"BBbb\u0002\u0000OOoo\u0002\u0000DDdd\u0002\u0000XXxx\b\u0000BBIILLSSb"+
		"biillss\u0002\u000009__\u0002\u0000EEee\u0004\u0000DDFFddff\u0005\u0000"+
		"  09AZ__az\u0004\u000009AZ__az\u0005\u0000..09AZ__az\u0002\u0000..~~\u0002"+
		"\u0000&&||\u0001\u000011\u0002\u000001__\u0001\u000017\u0002\u000007_"+
		"_\u0001\u000019\u0003\u000019AFaf\u0004\u000009AF__af\t\u0000\b\n\r\r"+
		"  \"\"\'\'bbnnrrtt\u0003\u000009AFaf\u0002\u0000\t\t  \u0192\u0000\u0001"+
		"\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005"+
		"\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001"+
		"\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000"+
		"\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000"+
		"\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000"+
		"\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000"+
		"\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000"+
		"\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000"+
		"\u0000\u0000)\u0001\u0000\u0000\u0000\u00007\u0001\u0000\u0000\u0000\u0000"+
		"9\u0001\u0000\u0000\u0000\u0001;\u0001\u0000\u0000\u0000\u0003=\u0001"+
		"\u0000\u0000\u0000\u0005?\u0001\u0000\u0000\u0000\u0007A\u0001\u0000\u0000"+
		"\u0000\tC\u0001\u0000\u0000\u0000\u000bF\u0001\u0000\u0000\u0000\rH\u0001"+
		"\u0000\u0000\u0000\u000fK\u0001\u0000\u0000\u0000\u0011S\u0001\u0000\u0000"+
		"\u0000\u0013k\u0001\u0000\u0000\u0000\u0015m\u0001\u0000\u0000\u0000\u0017"+
		"w\u0001\u0000\u0000\u0000\u0019\u00c3\u0001\u0000\u0000\u0000\u001b\u00c5"+
		"\u0001\u0000\u0000\u0000\u001d\u00d7\u0001\u0000\u0000\u0000\u001f\u00da"+
		"\u0001\u0000\u0000\u0000!\u00df\u0001\u0000\u0000\u0000#\u00ea\u0001\u0000"+
		"\u0000\u0000%\u0110\u0001\u0000\u0000\u0000\'\u0112\u0001\u0000\u0000"+
		"\u0000)\u0114\u0001\u0000\u0000\u0000+\u011e\u0001\u0000\u0000\u0000-"+
		"\u0128\u0001\u0000\u0000\u0000/\u0132\u0001\u0000\u0000\u00001\u013c\u0001"+
		"\u0000\u0000\u00003\u013e\u0001\u0000\u0000\u00005\u014d\u0001\u0000\u0000"+
		"\u00007\u0153\u0001\u0000\u0000\u00009\u0156\u0001\u0000\u0000\u0000;"+
		"<\u0005;\u0000\u0000<\u0002\u0001\u0000\u0000\u0000=>\u0005|\u0000\u0000"+
		">\u0004\u0001\u0000\u0000\u0000?@\u0005&\u0000\u0000@\u0006\u0001\u0000"+
		"\u0000\u0000AB\u0005=\u0000\u0000B\b\u0001\u0000\u0000\u0000CD\u0005$"+
		"\u0000\u0000DE\u0005(\u0000\u0000E\n\u0001\u0000\u0000\u0000FG\u0005)"+
		"\u0000\u0000G\f\u0001\u0000\u0000\u0000HI\u0005-\u0000\u0000IJ\u0005-"+
		"\u0000\u0000J\u000e\u0001\u0000\u0000\u0000KL\u0005-\u0000\u0000LM\u0005"+
		"-\u0000\u0000MO\u0001\u0000\u0000\u0000NP\u0007\u0000\u0000\u0000ON\u0001"+
		"\u0000\u0000\u0000PQ\u0001\u0000\u0000\u0000QO\u0001\u0000\u0000\u0000"+
		"QR\u0001\u0000\u0000\u0000R\u0010\u0001\u0000\u0000\u0000SU\u0005-\u0000"+
		"\u0000TV\u0007\u0000\u0000\u0000UT\u0001\u0000\u0000\u0000VW\u0001\u0000"+
		"\u0000\u0000WU\u0001\u0000\u0000\u0000WX\u0001\u0000\u0000\u0000X\u0012"+
		"\u0001\u0000\u0000\u0000Y^\u0005\"\u0000\u0000Z]\b\u0001\u0000\u0000["+
		"]\u00033\u0019\u0000\\Z\u0001\u0000\u0000\u0000\\[\u0001\u0000\u0000\u0000"+
		"]`\u0001\u0000\u0000\u0000^\\\u0001\u0000\u0000\u0000^_\u0001\u0000\u0000"+
		"\u0000_a\u0001\u0000\u0000\u0000`^\u0001\u0000\u0000\u0000al\u0005\"\u0000"+
		"\u0000bg\u0005\'\u0000\u0000cf\b\u0002\u0000\u0000df\u00033\u0019\u0000"+
		"ec\u0001\u0000\u0000\u0000ed\u0001\u0000\u0000\u0000fi\u0001\u0000\u0000"+
		"\u0000ge\u0001\u0000\u0000\u0000gh\u0001\u0000\u0000\u0000hj\u0001\u0000"+
		"\u0000\u0000ig\u0001\u0000\u0000\u0000jl\u0005\'\u0000\u0000kY\u0001\u0000"+
		"\u0000\u0000kb\u0001\u0000\u0000\u0000l\u0014\u0001\u0000\u0000\u0000"+
		"mr\u0005\"\u0000\u0000nq\b\u0001\u0000\u0000oq\u00033\u0019\u0000pn\u0001"+
		"\u0000\u0000\u0000po\u0001\u0000\u0000\u0000qt\u0001\u0000\u0000\u0000"+
		"rp\u0001\u0000\u0000\u0000rs\u0001\u0000\u0000\u0000su\u0001\u0000\u0000"+
		"\u0000tr\u0001\u0000\u0000\u0000uv\u0005\"\u0000\u0000v\u0016\u0001\u0000"+
		"\u0000\u0000w|\u0005\'\u0000\u0000x{\b\u0002\u0000\u0000y{\u00033\u0019"+
		"\u0000zx\u0001\u0000\u0000\u0000zy\u0001\u0000\u0000\u0000{~\u0001\u0000"+
		"\u0000\u0000|z\u0001\u0000\u0000\u0000|}\u0001\u0000\u0000\u0000}\u007f"+
		"\u0001\u0000\u0000\u0000~|\u0001\u0000\u0000\u0000\u007f\u0080\u0005\'"+
		"\u0000\u0000\u0080\u0018\u0001\u0000\u0000\u0000\u0081\u0083\u0007\u0003"+
		"\u0000\u0000\u0082\u0081\u0001\u0000\u0000\u0000\u0082\u0083\u0001\u0000"+
		"\u0000\u0000\u0083\u0092\u0001\u0000\u0000\u0000\u0084\u0085\u00050\u0000"+
		"\u0000\u0085\u0086\u0007\u0004\u0000\u0000\u0086\u0093\u0003+\u0015\u0000"+
		"\u0087\u0088\u00050\u0000\u0000\u0088\u0089\u0007\u0005\u0000\u0000\u0089"+
		"\u0093\u0003-\u0016\u0000\u008a\u008b\u00050\u0000\u0000\u008b\u008d\u0007"+
		"\u0006\u0000\u0000\u008c\u008a\u0001\u0000\u0000\u0000\u008c\u008d\u0001"+
		"\u0000\u0000\u0000\u008d\u008e\u0001\u0000\u0000\u0000\u008e\u0093\u0003"+
		"/\u0017\u0000\u008f\u0090\u00050\u0000\u0000\u0090\u0091\u0007\u0007\u0000"+
		"\u0000\u0091\u0093\u00031\u0018\u0000\u0092\u0084\u0001\u0000\u0000\u0000"+
		"\u0092\u0087\u0001\u0000\u0000\u0000\u0092\u008c\u0001\u0000\u0000\u0000"+
		"\u0092\u008f\u0001\u0000\u0000\u0000\u0093\u00a0\u0001\u0000\u0000\u0000"+
		"\u0094\u00a1\u0007\b\u0000\u0000\u0095\u0096\u0005i\u0000\u0000\u0096"+
		"\u00a1\u00058\u0000\u0000\u0097\u0098\u0005i\u0000\u0000\u0098\u0099\u0005"+
		"1\u0000\u0000\u0099\u00a1\u00056\u0000\u0000\u009a\u009b\u0005i\u0000"+
		"\u0000\u009b\u009c\u00053\u0000\u0000\u009c\u00a1\u00052\u0000\u0000\u009d"+
		"\u009e\u0005i\u0000\u0000\u009e\u009f\u00056\u0000\u0000\u009f\u00a1\u0005"+
		"4\u0000\u0000\u00a0\u0094\u0001\u0000\u0000\u0000\u00a0\u0095\u0001\u0000"+
		"\u0000\u0000\u00a0\u0097\u0001\u0000\u0000\u0000\u00a0\u009a\u0001\u0000"+
		"\u0000\u0000\u00a0\u009d\u0001\u0000\u0000\u0000\u00a0\u00a1\u0001\u0000"+
		"\u0000\u0000\u00a1\u00c4\u0001\u0000\u0000\u0000\u00a2\u00a4\u0007\u0003"+
		"\u0000\u0000\u00a3\u00a2\u0001\u0000\u0000\u0000\u00a3\u00a4\u0001\u0000"+
		"\u0000\u0000\u00a4\u00a5\u0001\u0000\u0000\u0000\u00a5\u00ad\u0003/\u0017"+
		"\u0000\u00a6\u00aa\u0005.\u0000\u0000\u00a7\u00a9\u0007\t\u0000\u0000"+
		"\u00a8\u00a7\u0001\u0000\u0000\u0000\u00a9\u00ac\u0001\u0000\u0000\u0000"+
		"\u00aa\u00a8\u0001\u0000\u0000\u0000\u00aa\u00ab\u0001\u0000\u0000\u0000"+
		"\u00ab\u00ae\u0001\u0000\u0000\u0000\u00ac\u00aa\u0001\u0000\u0000\u0000"+
		"\u00ad\u00a6\u0001\u0000\u0000\u0000\u00ad\u00ae\u0001\u0000\u0000\u0000"+
		"\u00ae\u00b8\u0001\u0000\u0000\u0000\u00af\u00b1\u0007\n\u0000\u0000\u00b0"+
		"\u00b2\u0007\u0003\u0000\u0000\u00b1\u00b0\u0001\u0000\u0000\u0000\u00b1"+
		"\u00b2\u0001\u0000\u0000\u0000\u00b2\u00b4\u0001\u0000\u0000\u0000\u00b3"+
		"\u00b5\u0007\t\u0000\u0000\u00b4\u00b3\u0001\u0000\u0000\u0000\u00b5\u00b6"+
		"\u0001\u0000\u0000\u0000\u00b6\u00b4\u0001\u0000\u0000\u0000\u00b6\u00b7"+
		"\u0001\u0000\u0000\u0000\u00b7\u00b9\u0001\u0000\u0000\u0000\u00b8\u00af"+
		"\u0001\u0000\u0000\u0000\u00b8\u00b9\u0001\u0000\u0000\u0000\u00b9\u00c1"+
		"\u0001\u0000\u0000\u0000\u00ba\u00c2\u0007\u000b\u0000\u0000\u00bb\u00bc"+
		"\u0005f\u0000\u0000\u00bc\u00bd\u00053\u0000\u0000\u00bd\u00c2\u00052"+
		"\u0000\u0000\u00be\u00bf\u0005f\u0000\u0000\u00bf\u00c0\u00056\u0000\u0000"+
		"\u00c0\u00c2\u00054\u0000\u0000\u00c1\u00ba\u0001\u0000\u0000\u0000\u00c1"+
		"\u00bb\u0001\u0000\u0000\u0000\u00c1\u00be\u0001\u0000\u0000\u0000\u00c1"+
		"\u00c2\u0001\u0000\u0000\u0000\u00c2\u00c4\u0001\u0000\u0000\u0000\u00c3"+
		"\u0082\u0001\u0000\u0000\u0000\u00c3\u00a3\u0001\u0000\u0000\u0000\u00c4"+
		"\u001a\u0001\u0000\u0000\u0000\u00c5\u00c6\u0005$\u0000\u0000\u00c6\u00c7"+
		"\u0003\u001d\u000e\u0000\u00c7\u001c\u0001\u0000\u0000\u0000\u00c8\u00cd"+
		"\u0005`\u0000\u0000\u00c9\u00cc\u0007\f\u0000\u0000\u00ca\u00cc\u0003"+
		"3\u0019\u0000\u00cb\u00c9\u0001\u0000\u0000\u0000\u00cb\u00ca\u0001\u0000"+
		"\u0000\u0000\u00cc\u00cf\u0001\u0000\u0000\u0000\u00cd\u00cb\u0001\u0000"+
		"\u0000\u0000\u00cd\u00ce\u0001\u0000\u0000\u0000\u00ce\u00d0\u0001\u0000"+
		"\u0000\u0000\u00cf\u00cd\u0001\u0000\u0000\u0000\u00d0\u00d8\u0005`\u0000"+
		"\u0000\u00d1\u00d4\u0007\r\u0000\u0000\u00d2\u00d4\u00033\u0019\u0000"+
		"\u00d3\u00d1\u0001\u0000\u0000\u0000\u00d3\u00d2\u0001\u0000\u0000\u0000"+
		"\u00d4\u00d5\u0001\u0000\u0000\u0000\u00d5\u00d3\u0001\u0000\u0000\u0000"+
		"\u00d5\u00d6\u0001\u0000\u0000\u0000\u00d6\u00d8\u0001\u0000\u0000\u0000"+
		"\u00d7\u00c8\u0001\u0000\u0000\u0000\u00d7\u00d3\u0001\u0000\u0000\u0000"+
		"\u00d8\u001e\u0001\u0000\u0000\u0000\u00d9\u00db\u0007\u000e\u0000\u0000"+
		"\u00da\u00d9\u0001\u0000\u0000\u0000\u00db\u00dc\u0001\u0000\u0000\u0000"+
		"\u00dc\u00da\u0001\u0000\u0000\u0000\u00dc\u00dd\u0001\u0000\u0000\u0000"+
		"\u00dd \u0001\u0000\u0000\u0000\u00de\u00e0\u0003\u001f\u000f\u0000\u00df"+
		"\u00de\u0001\u0000\u0000\u0000\u00df\u00e0\u0001\u0000\u0000\u0000\u00e0"+
		"\u00e1\u0001\u0000\u0000\u0000\u00e1\u00e3\u0005*\u0000\u0000\u00e2\u00e4"+
		"\u0003\u001f\u000f\u0000\u00e3\u00e2\u0001\u0000\u0000\u0000\u00e3\u00e4"+
		"\u0001\u0000\u0000\u0000\u00e4\"\u0001\u0000\u0000\u0000\u00e5\u00eb\u0003"+
		"\u001f\u000f\u0000\u00e6\u00eb\u0005.\u0000\u0000\u00e7\u00e8\u0005.\u0000"+
		"\u0000\u00e8\u00eb\u0005.\u0000\u0000\u00e9\u00eb\u0003!\u0010\u0000\u00ea"+
		"\u00e5\u0001\u0000\u0000\u0000\u00ea\u00e6\u0001\u0000\u0000\u0000\u00ea"+
		"\u00e7\u0001\u0000\u0000\u0000\u00ea\u00e9\u0001\u0000\u0000\u0000\u00eb"+
		"$\u0001\u0000\u0000\u0000\u00ec\u00ee\u0007\u000f\u0000\u0000\u00ed\u00ec"+
		"\u0001\u0000\u0000\u0000\u00ed\u00ee\u0001\u0000\u0000\u0000\u00ee\u00f0"+
		"\u0001\u0000\u0000\u0000\u00ef\u00f1\u0005/\u0000\u0000\u00f0\u00ef\u0001"+
		"\u0000\u0000\u0000\u00f1\u00f2\u0001\u0000\u0000\u0000\u00f2\u00f0\u0001"+
		"\u0000\u0000\u0000\u00f2\u00f3\u0001\u0000\u0000\u0000\u00f3\u00fc\u0001"+
		"\u0000\u0000\u0000\u00f4\u00f6\u0003#\u0011\u0000\u00f5\u00f7\u0005/\u0000"+
		"\u0000\u00f6\u00f5\u0001\u0000\u0000\u0000\u00f7\u00f8\u0001\u0000\u0000"+
		"\u0000\u00f8\u00f6\u0001\u0000\u0000\u0000\u00f8\u00f9\u0001\u0000\u0000"+
		"\u0000\u00f9\u00fb\u0001\u0000\u0000\u0000\u00fa\u00f4\u0001\u0000\u0000"+
		"\u0000\u00fb\u00fe\u0001\u0000\u0000\u0000\u00fc\u00fa\u0001\u0000\u0000"+
		"\u0000\u00fc\u00fd\u0001\u0000\u0000\u0000\u00fd\u0100\u0001\u0000\u0000"+
		"\u0000\u00fe\u00fc\u0001\u0000\u0000\u0000\u00ff\u0101\u0003#\u0011\u0000"+
		"\u0100\u00ff\u0001\u0000\u0000\u0000\u0100\u0101\u0001\u0000\u0000\u0000"+
		"\u0101\u0111\u0001\u0000\u0000\u0000\u0102\u0104\u0003#\u0011\u0000\u0103"+
		"\u0105\u0005/\u0000\u0000\u0104\u0103\u0001\u0000\u0000\u0000\u0105\u0106"+
		"\u0001\u0000\u0000\u0000\u0106\u0104\u0001\u0000\u0000\u0000\u0106\u0107"+
		"\u0001\u0000\u0000\u0000\u0107\u0109\u0001\u0000\u0000\u0000\u0108\u0102"+
		"\u0001\u0000\u0000\u0000\u0109\u010c\u0001\u0000\u0000\u0000\u010a\u0108"+
		"\u0001\u0000\u0000\u0000\u010a\u010b\u0001\u0000\u0000\u0000\u010b\u010d"+
		"\u0001\u0000\u0000\u0000\u010c\u010a\u0001\u0000\u0000\u0000\u010d\u010e"+
		"\u0003#\u0011\u0000\u010e\u010f\u0006\u0012\u0000\u0000\u010f\u0111\u0001"+
		"\u0000\u0000\u0000\u0110\u00ed\u0001\u0000\u0000\u0000\u0110\u010a\u0001"+
		"\u0000\u0000\u0000\u0111&\u0001\u0000\u0000\u0000\u0112\u0113\u0007\u0010"+
		"\u0000\u0000\u0113(\u0001\u0000\u0000\u0000\u0114\u0115\u0005\u00a3\u0000"+
		"\u0000\u0115*\u0001\u0000\u0000\u0000\u0116\u011f\u00050\u0000\u0000\u0117"+
		"\u011b\u0007\u0011\u0000\u0000\u0118\u011a\u0007\u0012\u0000\u0000\u0119"+
		"\u0118\u0001\u0000\u0000\u0000\u011a\u011d\u0001\u0000\u0000\u0000\u011b"+
		"\u0119\u0001\u0000\u0000\u0000\u011b\u011c\u0001\u0000\u0000\u0000\u011c"+
		"\u011f\u0001\u0000\u0000\u0000\u011d\u011b\u0001\u0000\u0000\u0000\u011e"+
		"\u0116\u0001\u0000\u0000\u0000\u011e\u0117\u0001\u0000\u0000\u0000\u011f"+
		",\u0001\u0000\u0000\u0000\u0120\u0129\u00050\u0000\u0000\u0121\u0125\u0007"+
		"\u0013\u0000\u0000\u0122\u0124\u0007\u0014\u0000\u0000\u0123\u0122\u0001"+
		"\u0000\u0000\u0000\u0124\u0127\u0001\u0000\u0000\u0000\u0125\u0123\u0001"+
		"\u0000\u0000\u0000\u0125\u0126\u0001\u0000\u0000\u0000\u0126\u0129\u0001"+
		"\u0000\u0000\u0000\u0127\u0125\u0001\u0000\u0000\u0000\u0128\u0120\u0001"+
		"\u0000\u0000\u0000\u0128\u0121\u0001\u0000\u0000\u0000\u0129.\u0001\u0000"+
		"\u0000\u0000\u012a\u0133\u00050\u0000\u0000\u012b\u012f\u0007\u0015\u0000"+
		"\u0000\u012c\u012e\u0007\t\u0000\u0000\u012d\u012c\u0001\u0000\u0000\u0000"+
		"\u012e\u0131\u0001\u0000\u0000\u0000\u012f\u012d\u0001\u0000\u0000\u0000"+
		"\u012f\u0130\u0001\u0000\u0000\u0000\u0130\u0133\u0001\u0000\u0000\u0000"+
		"\u0131\u012f\u0001\u0000\u0000\u0000\u0132\u012a\u0001\u0000\u0000\u0000"+
		"\u0132\u012b\u0001\u0000\u0000\u0000\u01330\u0001\u0000\u0000\u0000\u0134"+
		"\u013d\u00050\u0000\u0000\u0135\u0139\u0007\u0016\u0000\u0000\u0136\u0138"+
		"\u0007\u0017\u0000\u0000\u0137\u0136\u0001\u0000\u0000\u0000\u0138\u013b"+
		"\u0001\u0000\u0000\u0000\u0139\u0137\u0001\u0000\u0000\u0000\u0139\u013a"+
		"\u0001\u0000\u0000\u0000\u013a\u013d\u0001\u0000\u0000\u0000\u013b\u0139"+
		"\u0001\u0000\u0000\u0000\u013c\u0134\u0001\u0000\u0000\u0000\u013c\u0135"+
		"\u0001\u0000\u0000\u0000\u013d2\u0001\u0000\u0000\u0000\u013e\u014b\u0005"+
		"\\\u0000\u0000\u013f\u014c\u00050\u0000\u0000\u0140\u0141\u0005x\u0000"+
		"\u0000\u0141\u0142\u00035\u001a\u0000\u0142\u0143\u00035\u001a\u0000\u0143"+
		"\u014c\u0001\u0000\u0000\u0000\u0144\u0145\u0005u\u0000\u0000\u0145\u0146"+
		"\u00035\u001a\u0000\u0146\u0147\u00035\u001a\u0000\u0147\u0148\u00035"+
		"\u001a\u0000\u0148\u0149\u00035\u001a\u0000\u0149\u014c\u0001\u0000\u0000"+
		"\u0000\u014a\u014c\u0007\u0018\u0000\u0000\u014b\u013f\u0001\u0000\u0000"+
		"\u0000\u014b\u0140\u0001\u0000\u0000\u0000\u014b\u0144\u0001\u0000\u0000"+
		"\u0000\u014b\u014a\u0001\u0000\u0000\u0000\u014c4\u0001\u0000\u0000\u0000"+
		"\u014d\u014e\u0007\u0019\u0000\u0000\u014e6\u0001\u0000\u0000\u0000\u014f"+
		"\u0154\u0005\n\u0000\u0000\u0150\u0151\u0005\r\u0000\u0000\u0151\u0154"+
		"\u0005\n\u0000\u0000\u0152\u0154\u0005\r\u0000\u0000\u0153\u014f\u0001"+
		"\u0000\u0000\u0000\u0153\u0150\u0001\u0000\u0000\u0000\u0153\u0152\u0001"+
		"\u0000\u0000\u0000\u01548\u0001\u0000\u0000\u0000\u0155\u0157\u0007\u001a"+
		"\u0000\u0000\u0156\u0155\u0001\u0000\u0000\u0000\u0157\u0158\u0001\u0000"+
		"\u0000\u0000\u0158\u0156\u0001\u0000\u0000\u0000\u0158\u0159\u0001\u0000"+
		"\u0000\u0000\u0159\u015a\u0001\u0000\u0000\u0000\u015a\u015b\u0006\u001c"+
		"\u0001\u0000\u015b:\u0001\u0000\u0000\u00004\u0000QW\\^egkprz|\u0082\u008c"+
		"\u0092\u00a0\u00a3\u00aa\u00ad\u00b1\u00b6\u00b8\u00c1\u00c3\u00cb\u00cd"+
		"\u00d3\u00d5\u00d7\u00dc\u00df\u00e3\u00ea\u00ed\u00f2\u00f8\u00fc\u0100"+
		"\u0106\u010a\u0110\u011b\u011e\u0125\u0128\u012f\u0132\u0139\u013c\u014b"+
		"\u0153\u0158\u0002\u0001\u0012\u0000\u0000\u0001\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}