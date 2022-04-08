package io.github.will7200.plugins.casbincsv.language.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import com.intellij.psi.TokenType;
import io.github.will7200.plugins.casbincsv.language.psi.CasbinCSVElementTypes;
import java.util.regex.Pattern;

%%

%{
  public _CasbinCSVLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _CasbinCSVLexer
%implements FlexLexer
%function advance
%type IElementType
%{
    private CasbinCSVValueSeparator myValueSeparator;
    private CasbinCSVEscapeCharacter myEscapeCharacter;

    private static final Pattern ESCAPE_TEXT_PATTERN = Pattern.compile("[,:;|\\t\\r\\n]");

    /**
     * Provide constructor that supports a Project as parameter.
     */
    _CasbinCSVLexer(java.io.Reader in, CasbinCSVValueSeparator valueSeparator, CasbinCSVEscapeCharacter escapeCharacter) {
      this(in);
      myValueSeparator = valueSeparator;
      myEscapeCharacter = escapeCharacter;
    }
%}
%eof{  return;
%eof}
%unicode

EOL=\R
WHITE_SPACE=[\r\t\f\v ]+

COLON=:
BRACE1=\{
BRACE2=\}
BRACK1=\[
BRACK2=\]
STRING=\"[^\"]*\"|'[^']*'
ID=\p{Alpha}\w*
NUMBER=(\+|\-)?\p{Digit}*
TEXT=[^ ,:;|\t\r\n\"\\]+
ESCAPED_TEXT=[,:;|\t\r\n]|\"\"|\\\"
ESCAPE_CHARACTER=\\
COMMA=[,:;|\t]
QUOTE=\"
CRLF=\n
%state AFTER_TEXT
%state ESCAPED_TEXT
%state UNESCAPED_TEXT
%state ESCAPING
%state JSON

%%
<YYINITIAL> {
{BRACE1} {
          yybegin(JSON);
          return CasbinCSVElementTypes.BRACE1;
}
{QUOTE} {
    yybegin(ESCAPED_TEXT);
    return CasbinCSVElementTypes.QUOTE;
}

}

<JSON>
{
{BRACE2} {yybegin(YYINITIAL);return CasbinCSVElementTypes.BRACE2;}
{BRACK1} {return CasbinCSVElementTypes.BRACK1;}
{BRACK2} {return CasbinCSVElementTypes.BRACK2;}
","      {return CasbinCSVElementTypes.COMMA;}
{COLON} {return CasbinCSVElementTypes.COLON;}
{ID} {return CasbinCSVElementTypes.ID;}
{STRING} {return CasbinCSVElementTypes.STRING;}
{NUMBER} {return CasbinCSVElementTypes.NUMBER;}
}

<ESCAPED_TEXT> {QUOTE}
{
    yybegin(AFTER_TEXT);
    return CasbinCSVElementTypes.QUOTE;
}

<YYINITIAL> {TEXT}
{
    yybegin(UNESCAPED_TEXT);
    return CasbinCSVElementTypes.TEXT;
}

<UNESCAPED_TEXT, ESCAPED_TEXT> {TEXT}
{
    return CasbinCSVElementTypes.TEXT;
}

<YYINITIAL, UNESCAPED_TEXT> {ESCAPE_CHARACTER}
{
    String text = yytext().toString();
    if (myEscapeCharacter.getCharacter().equals(text)) {
        return TokenType.BAD_CHARACTER;
    }
    yybegin(UNESCAPED_TEXT);
    return CasbinCSVElementTypes.TEXT;
}

<ESCAPED_TEXT, ESCAPING> {ESCAPE_CHARACTER} {
    String text = yytext().toString();
    if (myEscapeCharacter.getCharacter().equals(text)) {
        switch (yystate()) {
            case ESCAPED_TEXT:
                yybegin(ESCAPING);
                break;
            case ESCAPING:
                yybegin(ESCAPED_TEXT);
                break;
            default:
                throw new RuntimeException("unhandled state: " + yystate());
        }
        return CasbinCSVElementTypes.ESCAPED_TEXT;
    }
    return CasbinCSVElementTypes.TEXT;
}

<ESCAPED_TEXT> {ESCAPED_TEXT}
{
    String text = yytext().toString();
    if (myEscapeCharacter.isEscapedQuote(text)
        || ESCAPE_TEXT_PATTERN.matcher(text).matches()
     ) {
        return CasbinCSVElementTypes.ESCAPED_TEXT;
    }
    if (!text.startsWith(CasbinCSVEscapeCharacter.QUOTE.getCharacter())) {
        yypushback(1);
        return CasbinCSVElementTypes.TEXT;
    }

    return TokenType.BAD_CHARACTER;
}

<YYINITIAL, AFTER_TEXT, UNESCAPED_TEXT> {COMMA}
{
    if (myValueSeparator.isValueSeparator(yytext().toString())) {
        yybegin(YYINITIAL);
        return CasbinCSVElementTypes.COMMA;
    }
    if (yystate() != AFTER_TEXT) {
        yybegin(UNESCAPED_TEXT);
        return CasbinCSVElementTypes.TEXT;
    }
    return TokenType.BAD_CHARACTER;
}

<YYINITIAL, AFTER_TEXT, UNESCAPED_TEXT> {EOL}
{
    yybegin(YYINITIAL);
    return CasbinCSVElementTypes.CRLF;
}
{CRLF}
{
    return CasbinCSVElementTypes.CRLF;
}

{WHITE_SPACE}
{
    return TokenType.WHITE_SPACE;
}

.
{
    return TokenType.BAD_CHARACTER;
}
