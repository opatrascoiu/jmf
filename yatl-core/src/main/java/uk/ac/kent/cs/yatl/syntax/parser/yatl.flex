/**
 * 
 * @author Octavian Patrascoiu
 *
 */

// Usercode Section
package uk.ac.kent.cs.ktl.syntax.parser;

import java_cup.runtime.*;
import uk.ac.kent.cs.kmf.util.*;
	  
%%  
// Options Sections
%unicode
%cup
%line
%column
   
// Declarations Section
%{
	// Debug flag
	protected static boolean lexDebug = false;
	protected void debug(int type) {
		if (lexDebug) {
			log.reportMessage(yyline+":"+yycolumn+" Token "+type+" '"+yytext()+"'");		
		}
	}

	// Output log
	protected ILog log;
	public void setLog(ILog log) {
		this.log = log;
	}
	
	// Create a new java_cup.runtime.Symbol with information about the current token
	protected Symbol symbol(int type) {
		debug(type);
		return new Symbol(type, yyline, yycolumn, new String(yytext()));
	}
	protected Symbol symbol(int type, Object value) {
		debug(type);
		return new Symbol(type, yyline, yycolumn, value);
	}
	
	// Replace escape sequences
	protected String replaceEscapeSequences(String str) {
		String result = "";
		for(int i=0; i<str.length(); i++) {
			// Escape sequence
			char ch = str.charAt(i); 
			if (ch == '\\') {
				if (i+1 < str.length()) {
					char ch1 = str.charAt(i+1); 
					if (ch1 == '\'') result += "'";
					else if (ch1 == 't') result += '\t';
					else if (ch1 == 'r') result += '\r';
					else if (ch1 == 'n') result += '\n';					
				}
			} else {
				result += ch; 
			}
		}
		return result;
	}
%}
   
%eofval{
	return symbol(sym.EOF);
%eofval}


// Macro declarations
lineTerminator 		= \r|\n|\r\n|\n\r
whiteSpace	 		= [ \t\f\n\r]
comment				= {paragraphComment} | {lineComment}
paragraphComment	= "/*" ~"*/"
lineComment			= "--" ~{lineTerminator}

lowerCase			= [a-z]
upperCase			= [A-Z]
digit				= [0-9]
letter				= {lowerCase} | {upperCase} | [_]
alpha				= {letter} | {digit}
integer				= {digit}+
real				= {integer}"\."{integer}[eE][+-]?{integer} | {integer}[eE][+-]?{integer} | {integer}"\."{integer} 
string				= "'" {stringContent} "'"
stringContent		= ( [^'] | \\\' | \\\t | \\\n | \\\r )*
simpleName			= {letter}{alpha}*

%%
// Lexical Rules Section 
   
<YYINITIAL> {  
	{whiteSpace}		{ /* just skip what was found, do nothing */ }   
	{comment}			{ /* just skip what was found, do nothing */ }   

	".."				{ return symbol(sym.DOT_DOT); }
	"::"				{ return symbol(sym.COLON_COLON); }
	"^^"				{ return symbol(sym.UP_UP); }
	"."					{ return symbol(sym.DOT); }
	":="				{ return symbol(sym.ASSIGN); }
	":"					{ return symbol(sym.COLON); }
	"^"					{ return symbol(sym.UP); }
 
	"("					{ return symbol(sym.LEFT_PAR); }
	"["					{ return symbol(sym.LEFT_BRK); }
	"{"					{ return symbol(sym.LEFT_BRA); }
	")"					{ return symbol(sym.RIGHT_PAR); }
	"]"					{ return symbol(sym.RIGHT_BRK); }
	"}"					{ return symbol(sym.RIGHT_BRA); }
	
	","					{ return symbol(sym.COMMA); }
	";"					{ return symbol(sym.SEMICOLON); }
	"|"					{ return symbol(sym.BAR); }
	"@"					{ return symbol(sym.AT); }
	"?"					{ return symbol(sym.QUESTION); }

	{real}				{ return symbol(sym.REAL); }
	{integer}			{ return symbol(sym.INTEGER); }

	"implies"			{ return symbol(sym.IMPLIES); }

	"and"				{ return symbol(sym.AND); }
	"or"				{ return symbol(sym.OR); }
	"xor"				{ return symbol(sym.XOR); }
	
	"="					{ return symbol(sym.EQ); }
	"<>"				{ return symbol(sym.NE); }

	"<="				{ return symbol(sym.LE); }
	">="				{ return symbol(sym.GE); }
	"<"					{ return symbol(sym.LT); }
	">"					{ return symbol(sym.GT); }

	"+"					{ return symbol(sym.PLUS); }
	"->"				{ return symbol(sym.MINUS_GT); }
	"-"					{ return symbol(sym.MINUS); }

	"*"					{ return symbol(sym.TIMES); }
	"/"					{ return symbol(sym.DIVIDE); }
	"div"				{ return symbol(sym.INT_DIVIDE); }
	"mod"				{ return symbol(sym.INT_MOD); }

	"Set"				{ return symbol(sym.SET); }
	"Bag"				{ return symbol(sym.BAG); }
	"Sequence"			{ return symbol(sym.SEQUENCE); }
	"Collection"		{ return symbol(sym.COLLECTION); }
	"OrderedSet"		{ return symbol(sym.ORDERED_SET); }
	"TupleType"			{ return symbol(sym.TUPLE_TYPE); }
	"Tuple"				{ return symbol(sym.TUPLE); }
	"iterate"			{ return symbol(sym.ITERATE); }

	"iff"				{ return symbol(sym.IFSTM); }
	"if"				{ return symbol(sym.IF); }
	"then"				{ return symbol(sym.THEN); }
	"else"				{ return symbol(sym.ELSE); }
	"endif"				{ return symbol(sym.ENDIF); }
	"not"				{ return symbol(sym.NOT); }
	"true"				{ return symbol(sym.TRUE); }
	"false"				{ return symbol(sym.FALSE); }
	"let"				{ return symbol(sym.LET); }
	"package"			{ return symbol(sym.PACKAGE); }
	"endpackage"		{ return symbol(sym.ENDPACKAGE); }
	"context"			{ return symbol(sym.CONTEXT); }
	"init"				{ return symbol(sym.INIT); }
	"derive"			{ return symbol(sym.DERIVE); }
	"inv"				{ return symbol(sym.INV); }
	"def"				{ return symbol(sym.DEF); }
	"pre"				{ return symbol(sym.PRE); }
	"post"				{ return symbol(sym.POST); }
	"body"				{ return symbol(sym.BODY); }
	"in"				{ return symbol(sym.IN); }

	"namespace"			{ return symbol(sym.NAMESPACE); }
	"start"				{ return symbol(sym.START); }
	"import"			{ return symbol(sym.IMPORT); }
	"query"				{ return symbol(sym.QUERY); }
	"transformation"	{ return symbol(sym.TRANSFORMATION); }
	"rule"				{ return symbol(sym.RULE); }
	"while"				{ return symbol(sym.WHILE); }
	"do"				{ return symbol(sym.DO); }
	"foreach"			{ return symbol(sym.FOREACH); }
	"break"				{ return symbol(sym.BREAK); }
	"continue"			{ return symbol(sym.CONTINUE); }
	"match"				{ return symbol(sym.MATCH); }
	"apply"				{ return symbol(sym.APPLY); }
	"track"				{ return symbol(sym.TRACK); }
	"null"				{ return symbol(sym.NULL); }
	"new"				{ return symbol(sym.NEW); }
	"delete"			{ return symbol(sym.DELETE); }

	{simpleName}		{ return symbol(sym.SIMPLE_NAME); }
	{string}			{ return symbol(sym.STRING, replaceEscapeSequences(yytext())); }
}

[^]						{ log.reportError("Illegal character '"+yytext()+"'"); return symbol(sym.BAD);}
