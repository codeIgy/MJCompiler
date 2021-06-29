package rs.ac.bg.etf.pp1;
import rs.ac.bg.etf.pp1.test.*;
import rs.ac.bg.etf.pp1.test.CompilerError.CompilerErrorType;
import java_cup.runtime.Symbol;
import java.util.*;

%%

%{
	public List<CompilerError> errors = new ArrayList();
	
	//ukljucivanje info o poziciji tokena
	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline + 1, yycolumn);
	}
	
	//ukljucivanje info poziciji tokena
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline + 1, yycolumn, value);
	}
	
	private void report_error(String message, int line){
		CompilerError error = new CompilerError(line, message, CompilerErrorType.LEXICAL_ERROR);
		errors.add(error);
	}
%}

%cup
%line
%column

%xstate COMMENT


%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%

" " { }
"\b" { }
"\t" { }
"\r\n" { }
"\f" { }

"program" { return new_symbol(sym.PROG, yytext());}
"print" 	{ return new_symbol(sym.PRINT, yytext()); }
"return" 	{ return new_symbol(sym.RETURN, yytext()); }
"void" 		{ return new_symbol(sym.VOID, yytext()); }
"const" { return new_symbol(sym.CONST, yytext());}
"case" 	{ return new_symbol(sym.CASE, yytext()); }
"continue" 	{ return new_symbol(sym.CONTINUE, yytext()); }
"class" 		{ return new_symbol(sym.CLASS, yytext()); }
"if" { return new_symbol(sym.IF, yytext());}
"new" 	{ return new_symbol(sym.NEW, yytext()); }
"switch" 	{ return new_symbol(sym.SWITCH, yytext()); }
"else" 		{ return new_symbol(sym.ELSE, yytext()); }
"enum" { return new_symbol(sym.ENUM, yytext());}
"read" 	{ return new_symbol(sym.READ, yytext()); }
"while" 	{ return new_symbol(sym.WHILE, yytext()); }
"default" 		{ return new_symbol(sym.DEFAULT, yytext()); }
"extends" 		{ return new_symbol(sym.EXTENDS, yytext()); }
"do" 		{ return new_symbol(sym.DO, yytext()); }
"break" 		{ return new_symbol(sym.BREAK, yytext()); }
"yield" 		{ return new_symbol(sym.YIELD, yytext()); }
"--" { return new_symbol(sym.DEC, yytext());}
"++" { return new_symbol(sym.INC, yytext());}
"+" 		{ return new_symbol(sym.PLUS, yytext()); }
"==" 		{ return new_symbol(sym.EQUAL, yytext()); }
";" 		{ return new_symbol(sym.SEMI, yytext()); }
"," 		{ return new_symbol(sym.COMMA, yytext()); }
"(" 		{ return new_symbol(sym.LPAREN, yytext()); }
")" 		{ return new_symbol(sym.RPAREN, yytext()); }
"{" 		{ return new_symbol(sym.LBRACE, yytext()); }
"}"			{ return new_symbol(sym.RBRACE, yytext()); }
"[" { return new_symbol(sym.LBRACKET, yytext());}
"]" { return new_symbol(sym.RBRACKET, yytext());}
">=" { return new_symbol(sym.GE, yytext());}
"<=" { return new_symbol(sym.LE, yytext());}
"-" { return new_symbol(sym.MINUS, yytext());}
"&&" { return new_symbol(sym.AND, yytext());}
"<" { return new_symbol(sym.LT, yytext());}
"||" { return new_symbol(sym.OR, yytext());}
"/" { return new_symbol(sym.DIV, yytext());}
"=". { return new_symbol(sym.ASSIGN, yytext());}
"*" { return new_symbol(sym.MUL, yytext());}
"!=" { return new_symbol(sym.DIF, yytext());}
"%" { return new_symbol(sym.MOD, yytext());}
":" { return new_symbol(sym.COLON, yytext());}
"?" { return new_symbol(sym.QUESTION, yytext());}
">" { return new_symbol(sym.GT, yytext());}

"//" { yybegin(COMMENT); }
<COMMENT> "\r\n" { yybegin(YYINITIAL); } 
<COMMENT> . { yybegin(COMMENT); }

[0-9]+ { return new_symbol(sym.NUMCONST, new Integer(yytext())); }
"'"."'" { return new_symbol(sym.CHARCONST, new Character(yytext().charAt(1))); }
"true"|"false" { return new_symbol(sym.BOOLCONST, yytext()); }

([a-z]|[A-Z])[a-zA-Z0-9_]* { return new_symbol(sym.IDENT, yytext()); }

. { System.err.println("Leksicka greska (" + yytext() + ") u liniji " + (yyline + 1));
	report_error(yytext(), yyline + 1); }


