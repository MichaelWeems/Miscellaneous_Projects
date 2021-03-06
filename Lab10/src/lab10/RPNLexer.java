// Generated from RPN.g4 by ANTLR 4.5.2

  import java.util.*;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RPNLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, INT=13, CHAR=14, AND=15, OR=16, NOT=17, WS=18;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "INT", "CHAR", "AND", "OR", "NOT", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "'+'", "'-'", "'*'", "'/'", "'%'", "'<'", "'<='", "'=='", 
		"'!='", "'>'", "'>='", null, null, "'&&'", "'||'", "'!'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "INT", "CHAR", "AND", "OR", "NOT", "WS"
	};
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


	  /////////////////////////////////////////////////////////////////////////////////////////////
	  // NUMERIC CALCULATOR
	  Stack<Integer> stack = new Stack<Integer>();
	  int stack_size = 0;
	  
	  String op = "\0";
	  
	  void add(int a, int b){
	    int result = a + b;
	    //System.out.println("Added (" + a + ") and (" + b + "), result : " + result );
	    pushInt(result);
		//System.out.println("---------------");
	  }
	  
	  void subtract(int a, int b){
	    int result = a - b;
	    //System.out.println("Subtracted (" + a + ") and (" + b + "), result : " + result );
	    pushInt(result);
		//System.out.println("---------------");
	  }
	  
	  void multiply(int a, int b){
	    int result = a * b;
	    //System.out.println("Multiplied (" + a + ") and (" + b + "), result : " + result );
	    pushInt(result);
		//System.out.println("---------------");
	  }
	  
	  void divide(int a, int b){
	    int result = a / b;
	    //System.out.println("Divided (" + a + ") and (" + b + "), result : " + result );
	    pushInt(result);
		//System.out.println("---------------");
	  }
	  
	  void mod(int a, int b){
	    int result = a % b;
	    //System.out.println("Modded (" + a + ") and (" + b + "), result : " + result );
	    pushInt(result);
		//System.out.println("---------------");
	  }
	  
	  void pushInt(int num){
	    stack.push(num);
		stack_size++;
		//System.out.println("Pushed (" + num + ") to the stack");
	  }
	  
	  void reset_intStack(){
	    stack_size = 0;
		while(!stack.empty()){
		  //System.out.println("Popping: " + stack.pop());
		  stack.pop();
		}
	  }
	  
	  void runOp(){
	    
		if ( stack_size >= 2 ){
		  int b = stack.pop();
		  int a = stack.pop();
		  stack_size -= 2;
		  //System.out.println("---------------");
		  //System.out.println("Popped (" + b + ") and (" + a + ") from the stack");
		  
		  switch (op) {
		    case "+": add(a, b); break;
		    case "-": subtract(a, b); break;
		    case "*": multiply(a, b); break;
		    case "/": divide(a, b); break;
		    case "%": mod(a, b); break;
			case "<":   relation(a,b,op); break;
			case "<=": relation(a,b,op); break;
			case ">":   relation(a,b,op); break;
			case ">=": relation(a,b,op); break;
			case "==": relation(a,b,op); break;
			case "!=":  relation(a,b,op); break;
			case "\0" : System.out.println("FAILURE: no operator set");  break; //terminate();
		    default: System.out.println("FAILURE: incorrect operator (" + op + ")");   break; //terminate();
		  }
		  op = "\0";
		}
		else{
		  System.out.println("INVALID FORMAT: Not enough operands for the operator (" + op + ")");
		   terminate();
		}
	  }
	  
	  void getResult(){
	    if (stack_size == 1){
		  get_stackResult();
		}
		else if (logstack_size == 1) {
		  get_logstackResult();
		}
		else {
		  System.out.println("INVALID FORMAT: Too many/few operands left in final result");
		  terminate();
		}
	  }
	  
	  void get_logstackResult(){
	    //System.out.println("Result: " + logstack.pop());
		System.out.print(logstack.pop() + "; ");
		//System.out.println("\n\n");
		reset_logicalStack();
	  }
	  
	  void get_stackResult(){
	    //System.out.println("Result: " + stack.pop());
	    System.out.print(stack.pop() + "; ");
		//System.out.println("\n\n");
		reset_intStack();
	  }
	  
	  /////////////////////////////////////////////////////////////////////////////////////////////
	  // LOGICAL CALCULATOR
	  
	  Stack<String> logstack = new Stack<String>();
	  int logstack_size = 0;
	  
	  String logOp = "\0";
	  
	  boolean getBool(String bool){
	    boolean res = false;
	    if (bool.equals("true")){ res = true; }
		return res;
	  }
	  
	  void finishBoolExpr(boolean result, String operation, String a, String b){
	    String res = "false";
		if (result){ res = "true"; }

	    //System.out.println(operation + " (" + a + ") and (" + b + "), result : " + res );
	    pushBool(res);
		//System.out.println("---------------");
	  }
	  
	  void and(String a, String b){
	    boolean left = getBool(a);
	    boolean right = getBool(b);
		
		boolean result = left && right;
		
	    finishBoolExpr(result, "Anded", a, b);
	  }
	  
	  void or(String a, String b){
	    boolean left = getBool(a);
	    boolean right = getBool(b);
		
		boolean result = left || right;
		
	    finishBoolExpr(result, "Ored", a, b);
	  }
	  
	  void not(String a){
	    boolean left = getBool(a);
		
		boolean result = !left;
		
		String b = "";
		
	    finishBoolExpr(result, "Notted", a, b);
	  }
	  
	  void relation(int a, int b, String operator){
	  
	    boolean result = false;
		switch (operator) {
		  case "<":  result = a < b;
		    break;
		  case "<=":  result = a <= b;
		    break;
		  case ">":  result = a > b;
		    break;
		  case ">=":  result = a >= b;
		    break;
		  case "==":  result = a == b;
		    break;
		  case "!=":  result = a != b;
		    break;
	      case "\0" : System.out.println("FAILURE: no operator set"); break; //terminate();
		  default: System.out.println("FAILURE: incorrect operator (" + operator + ")"); break; // terminate();
		
		}
		
		
		finishBoolExpr(result, operator, Integer.toString(a), Integer.toString(b));
	  }
	  
	  void pushBool(String bool){
	    logstack.push(bool);
		logstack_size++;
		//System.out.println("Pushed (" + bool + ") to the stack");
	  }
	  
	  void runLogOp(){
	    
		if (logOp.equals("!")) {
		  String a = logstack.pop();
		  logstack_size--;
		  //System.out.println("---------------");
		  //System.out.println("Popped (" + a + ") from the stack");
		  not(a);
		}
		else if ( logstack_size >= 2 ){
		  String b = logstack.pop();
		  String a = logstack.pop();
		  logstack_size -= 2;
		  //System.out.println("---------------");
		  //System.out.println("Popped (" + b + ") and (" + a + ") from the stack");
		  
		  switch (logOp) {
		    case "&&":  and(a, b); break;
		    case "||":   or(a, b); break;
			case "\0" : System.out.println("FAILURE: no operator set"); break; //terminate();
		    default: System.out.println("FAILURE: incorrect operator (" + logOp + ")");   break; //terminate();
		  }
		  logOp = "\0";
		}
		else{
		  System.out.println("INVALID FORMAT: Not enough operands for the operator (" + logOp + ")");
		   terminate();
		}
	  }
	  
	  void reset_logicalStack(){
	    logstack_size = 0;
		while(!logstack.empty()){
		  //System.out.println("Popping: " + logstack.pop());
		  logstack.pop();
		}
	  }
	  
	  void terminate(){
	    System.out.println("TERMINATING EXECUTION");
		System.exit(1);
	  }


	public RPNLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "RPN.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\24\\\b\1\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t"+
		"\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\16\6\16E\n\16"+
		"\r\16\16\16F\3\17\6\17J\n\17\r\17\16\17K\3\20\3\20\3\20\3\21\3\21\3\21"+
		"\3\22\3\22\3\23\6\23W\n\23\r\23\16\23X\3\23\3\23\2\2\24\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\3\2\5\3\2\62;\4\2C\\c|\5\2\13\f\17\17\"\"^\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\3\'\3"+
		"\2\2\2\5)\3\2\2\2\7+\3\2\2\2\t-\3\2\2\2\13/\3\2\2\2\r\61\3\2\2\2\17\63"+
		"\3\2\2\2\21\65\3\2\2\2\238\3\2\2\2\25;\3\2\2\2\27>\3\2\2\2\31@\3\2\2\2"+
		"\33D\3\2\2\2\35I\3\2\2\2\37M\3\2\2\2!P\3\2\2\2#S\3\2\2\2%V\3\2\2\2\'("+
		"\7=\2\2(\4\3\2\2\2)*\7-\2\2*\6\3\2\2\2+,\7/\2\2,\b\3\2\2\2-.\7,\2\2.\n"+
		"\3\2\2\2/\60\7\61\2\2\60\f\3\2\2\2\61\62\7\'\2\2\62\16\3\2\2\2\63\64\7"+
		">\2\2\64\20\3\2\2\2\65\66\7>\2\2\66\67\7?\2\2\67\22\3\2\2\289\7?\2\29"+
		":\7?\2\2:\24\3\2\2\2;<\7#\2\2<=\7?\2\2=\26\3\2\2\2>?\7@\2\2?\30\3\2\2"+
		"\2@A\7@\2\2AB\7?\2\2B\32\3\2\2\2CE\t\2\2\2DC\3\2\2\2EF\3\2\2\2FD\3\2\2"+
		"\2FG\3\2\2\2G\34\3\2\2\2HJ\t\3\2\2IH\3\2\2\2JK\3\2\2\2KI\3\2\2\2KL\3\2"+
		"\2\2L\36\3\2\2\2MN\7(\2\2NO\7(\2\2O \3\2\2\2PQ\7~\2\2QR\7~\2\2R\"\3\2"+
		"\2\2ST\7#\2\2T$\3\2\2\2UW\t\4\2\2VU\3\2\2\2WX\3\2\2\2XV\3\2\2\2XY\3\2"+
		"\2\2YZ\3\2\2\2Z[\b\23\2\2[&\3\2\2\2\6\2FKX\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}