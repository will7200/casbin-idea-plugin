package io.github.will7200.plugins.casbin.language.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import io.github.will7200.plugins.casbin.language.psi.CasbinElementTypes;
import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static io.github.will7200.plugins.casbin.language.psi.CasbinElementTypes.*;

%%

%{
  private int myPreviousState = YYINITIAL;
  public _CasbinLexer() {
    this(null);
  }
%}

%public
%class _CasbinLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

WHITE_SPACE=\s+

LINE_COMMENT=[#;].*

IDENTIFIER=[A-Za-z]?[A-Za-z_0-9]+
ESCAPE = \\[^]
STRING=([^\r\n\"] | {ESCAPE})*
STRING_SINGLE_QUOTE=([^\r\n\'] | {ESCAPE})*
SECTION_IDENTIFER=[A-Za-z_]+
SPACE=[ \t\n\x0B\f\r]+

%state YYHEADER
%state YYQUOTES
%state YYSINGLE_QUOTES

%%

"="                         { return ASSIGN; }
","                         { return COMMA; }
{WHITE_SPACE}               { return WHITE_SPACE; }
{LINE_COMMENT}              { return LINE_COMMENT; }
"("                         { return L_PARATHESIS; }
")"                         { return R_PARATHESIS; }
"."                         { return DOT; }
"."                         { return DOT; }
"&&"                        { return OP_AND; }
"||"                        { return OP_OR; }
"=="                        { return OP_EQUALS; }
"allow"                     { return ALLOW; }
"deny"                      { return DENY; }
"!"                         { return OP_NOT;}
"in"                        { return OP_IN;}

<YYHEADER> {
    "]"                     { yybegin(YYINITIAL); return R_BRACKET; }
  {SECTION_IDENTIFER}      { return SECTION_IDENTIFER; }
}

<YYQUOTES> {
   "\""             {yybegin(YYINITIAL); return CLOSE_QUOTES;}
   {STRING}         { return STRING;}
}

<YYSINGLE_QUOTES> {
    "'"            {yybegin(YYINITIAL); return CLOSE_SINGLE_QUOTES;}
    {STRING_SINGLE_QUOTE}       { return STRING_SINGLE_QUOTE;}
}

<YYINITIAL> {
    "["                       { yybegin(YYHEADER);return L_BRACKET; }
    "]"                       { return R_BRACKET; }
    "\""                      { yybegin(YYQUOTES);return OPEN_QUOTES;}
    "'"                      { yybegin(YYSINGLE_QUOTES);return OPEN_SINGLE_QUOTES;}
    "."                      { return DOT; }
    "="                      { return ASSIGN; }
    ","                      { return COMMA; }
    "&&"                     { return OP_AND; }
    "||"                     { return OP_OR; }
    "=="                     { return OP_EQUALS; }
    "!"                      { return OP_NOT; }
    "allow"                  { return ALLOW; }
    "deny"                   { return DENY; }
    "("                      { return L_PARATHESIS; }
    ")"                      { return R_PARATHESIS; }
    "OP_IN"                  { return OP_IN; }
    "\\\n"                   { return WHITE_SPACE;}
    {IDENTIFIER}             { return IDENTIFIER; }
}

[^]                         { return BAD_CHARACTER; }