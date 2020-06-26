// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbin.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import io.github.will7200.plugins.casbin.language.psi.impl.*;

public interface CasbinElementTypes {

  IElementType ATTRIBUTE = new CasbinElementType("ATTRIBUTE");
  IElementType EQUALITY = new CasbinElementType("EQUALITY");
  IElementType EXPR = new CasbinElementType("EXPR");
  IElementType FLAT_KEY = new CasbinElementType("FLAT_KEY");
  IElementType HEADER = new CasbinElementType("HEADER");
  IElementType LITERAL_EXPR = new CasbinElementType("LITERAL_EXPR");
  IElementType OBJECT_IDENTIFIER = new CasbinElementType("OBJECT_IDENTIFIER");
  IElementType OPTION_FUNCTION = new CasbinElementType("OPTION_FUNCTION");
  IElementType OPTION_VALUES = new CasbinElementType("OPTION_VALUES");
  IElementType OPTION_VALUE_EXPRESSION = new CasbinElementType("OPTION_VALUE_EXPRESSION");
  IElementType OPTION_VALUE_IDENTIFIER = new CasbinElementType("OPTION_VALUE_IDENTIFIER");
  IElementType OPTION_VALUE_LIST = new CasbinElementType("OPTION_VALUE_LIST");
  IElementType PAREN_EXPR = new CasbinElementType("PAREN_EXPR");
  IElementType PROPERTY = new CasbinElementType("PROPERTY");
  IElementType RELATIONAL_EXPR = new CasbinElementType("RELATIONAL_EXPR");
  IElementType SECTION = new CasbinElementType("SECTION");
  IElementType SECTION_NAME = new CasbinElementType("SECTION_NAME");
  IElementType SOME_VALUE = new CasbinElementType("SOME_VALUE");

  IElementType ALLOW = new CasbinTokenType("allow");
  IElementType ASSIGN = new CasbinTokenType("=");
  IElementType COMMA = new CasbinTokenType(",");
  IElementType DENY = new CasbinTokenType("deny");
  IElementType DOT = new CasbinTokenType(".");
  IElementType IDENTIFIER = new CasbinTokenType("IDENTIFIER");
  IElementType LINE_COMMENT = new CasbinTokenType("LINE_COMMENT");
  IElementType L_BRACKET = new CasbinTokenType("[");
  IElementType L_PARATHESIS = new CasbinTokenType("(");
  IElementType OPTION = new CasbinTokenType("option");
  IElementType OP_AND = new CasbinTokenType("&&");
  IElementType OP_EQUALS = new CasbinTokenType("==");
  IElementType OP_OR = new CasbinTokenType("||");
  IElementType R_BRACKET = new CasbinTokenType("]");
  IElementType R_PARATHESIS = new CasbinTokenType(")");
  IElementType SECTION_IDENTIFER = new CasbinTokenType("SECTION_IDENTIFER");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ATTRIBUTE) {
        return new CasbinAttributeImpl(node);
      }
      else if (type == EQUALITY) {
        return new CasbinEqualityImpl(node);
      }
      else if (type == FLAT_KEY) {
        return new CasbinFlatKeyImpl(node);
      }
      else if (type == HEADER) {
        return new CasbinHeaderImpl(node);
      }
      else if (type == LITERAL_EXPR) {
        return new CasbinLiteralExprImpl(node);
      }
      else if (type == OBJECT_IDENTIFIER) {
        return new CasbinObjectIdentifierImpl(node);
      }
      else if (type == OPTION_FUNCTION) {
        return new CasbinOptionFunctionImpl(node);
      }
      else if (type == OPTION_VALUES) {
        return new CasbinOptionValuesImpl(node);
      }
      else if (type == OPTION_VALUE_EXPRESSION) {
        return new CasbinOptionValueExpressionImpl(node);
      }
      else if (type == OPTION_VALUE_IDENTIFIER) {
        return new CasbinOptionValueIdentifierImpl(node);
      }
      else if (type == OPTION_VALUE_LIST) {
        return new CasbinOptionValueListImpl(node);
      }
      else if (type == PAREN_EXPR) {
        return new CasbinParenExprImpl(node);
      }
      else if (type == PROPERTY) {
        return new CasbinPropertyImpl(node);
      }
      else if (type == RELATIONAL_EXPR) {
        return new CasbinRelationalExprImpl(node);
      }
      else if (type == SECTION) {
        return new CasbinSectionImpl(node);
      }
      else if (type == SECTION_NAME) {
        return new CasbinSectionNameImpl(node);
      }
      else if (type == SOME_VALUE) {
        return new CasbinSomeValueImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
