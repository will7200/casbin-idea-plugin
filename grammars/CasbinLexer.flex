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

IDENTIFIER_LETTER=[A-Za-z][A-Za-z_0-9]*

IDENTIFIER={IDENTIFIER_LETTER}+

SECTION_IDENTIFER=[A-Za-z_]+

%state YYHEADER
%state YYQUOTES

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

<YYHEADER> {
    "]"                     { yybegin(YYINITIAL); return R_BRACKET; }
  {SECTION_IDENTIFER}      { return SECTION_IDENTIFER; }
}

<YYQUOTES> {
   "\""             {yybegin(YYINITIAL); return CLOSE_QUOTES;}
   {IDENTIFIER}     { return IDENTIFIER;}
}

<YYINITIAL> {
  "["                       { yybegin(YYHEADER);return L_BRACKET; }
  "]"                       { return R_BRACKET; }
  "\""                      { yybegin(YYQUOTES);return OPEN_QUOTES;}
  "allow"                     { return ALLOW; }
  "deny"                      { return DENY; }
  {IDENTIFIER}              { return IDENTIFIER; }
}

[^]                         { return BAD_CHARACTER; }