// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbincsv.language.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import io.github.will7200.plugins.casbincsv.language.psi.impl.*;

public interface CasbinCSVElementTypes {

  IElementType FIELD = new CasbinCSVElementType("FIELD");
  IElementType JSON = new CasbinCSVElementType("JSON");
  IElementType JSON_ARRAY = new CasbinCSVElementType("JSON_ARRAY");
  IElementType JSON_NAME = new CasbinCSVElementType("JSON_NAME");
  IElementType JSON_OBJECT = new CasbinCSVElementType("JSON_OBJECT");
  IElementType JSON_PROP = new CasbinCSVElementType("JSON_PROP");
  IElementType JSON_VALUE = new CasbinCSVElementType("JSON_VALUE");
  IElementType RECORD = new CasbinCSVElementType("RECORD");

  IElementType BRACE1 = new CasbinCSVTokenType("{");
  IElementType BRACE2 = new CasbinCSVTokenType("}");
  IElementType BRACK1 = new CasbinCSVTokenType("[");
  IElementType BRACK2 = new CasbinCSVTokenType("]");
  IElementType COLON = new CasbinCSVTokenType(":");
  IElementType COMMA = new CasbinCSVTokenType("COMMA");
  IElementType CRLF = new CasbinCSVTokenType("CRLF");
  IElementType ESCAPED_TEXT = new CasbinCSVTokenType("ESCAPED_TEXT");
  IElementType ESCAPE_CHARACTER = new CasbinCSVTokenType("ESCAPE_CHARACTER");
  IElementType ID = new CasbinCSVTokenType("ID");
  IElementType NUMBER = new CasbinCSVTokenType("NUMBER");
  IElementType QUOTE = new CasbinCSVTokenType("QUOTE");
  IElementType STRING = new CasbinCSVTokenType("STRING");
  IElementType TEXT = new CasbinCSVTokenType("TEXT");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == FIELD) {
        return new CasbinCSVFieldImpl(node);
      }
      else if (type == JSON) {
        return new CasbinCSVJsonImpl(node);
      }
      else if (type == JSON_ARRAY) {
        return new CasbinCSVJsonArrayImpl(node);
      }
      else if (type == JSON_NAME) {
        return new CasbinCSVJsonNameImpl(node);
      }
      else if (type == JSON_OBJECT) {
        return new CasbinCSVJsonObjectImpl(node);
      }
      else if (type == JSON_PROP) {
        return new CasbinCSVJsonPropImpl(node);
      }
      else if (type == JSON_VALUE) {
        return new CasbinCSVJsonValueImpl(node);
      }
      else if (type == RECORD) {
        return new CasbinCSVRecordImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
