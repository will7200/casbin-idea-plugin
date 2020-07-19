// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbincsv.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import io.github.will7200.plugins.casbincsv.language.psi.impl.*;

public interface CasbinCSVElementTypes {

  IElementType FIELD = new CasbinCSVElementType("FIELD");
  IElementType RECORD = new CasbinCSVElementType("RECORD");

  IElementType COMMA = new CasbinCSVTokenType("COMMA");
  IElementType CRLF = new CasbinCSVTokenType("CRLF");
  IElementType ESCAPED_TEXT = new CasbinCSVTokenType("ESCAPED_TEXT");
  IElementType ESCAPE_CHARACTER = new CasbinCSVTokenType("ESCAPE_CHARACTER");
  IElementType QUOTE = new CasbinCSVTokenType("QUOTE");
  IElementType TEXT = new CasbinCSVTokenType("TEXT");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == FIELD) {
        return new CasbinCSVFieldImpl(node);
      }
      else if (type == RECORD) {
        return new CasbinCSVRecordImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
