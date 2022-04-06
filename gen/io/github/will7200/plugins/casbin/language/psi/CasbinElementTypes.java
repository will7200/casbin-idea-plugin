// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbin.language.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import io.github.will7200.plugins.casbin.language.psi.impl.*;

public interface CasbinElementTypes {

  IElementType ATTRIBUTE = new CasbinElementType("ATTRIBUTE");
  IElementType ATTRIBUTE_DEFINITION = new CasbinElementType("ATTRIBUTE_DEFINITION");
  IElementType EQUALITY = new CasbinElementType("EQUALITY");
  IElementType EXPR = new CasbinElementType("EXPR");
  IElementType FLAT_KEY = new CasbinElementType("FLAT_KEY");
  IElementType FUNCTION = new CasbinElementType("FUNCTION");
  IElementType FUNCTION_NAME = new CasbinElementType("FUNCTION_NAME");
  IElementType FUNCTION_SIGNATURE = new CasbinElementType("FUNCTION_SIGNATURE");
  IElementType HEADER = new CasbinElementType("HEADER");
  IElementType LITERAL_EXPR = new CasbinElementType("LITERAL_EXPR");
  IElementType OBJECT = new CasbinElementType("OBJECT");
  IElementType OBJECT_IDENTIFIER = new CasbinElementType("OBJECT_IDENTIFIER");
  IElementType OPTION_VALUES = new CasbinElementType("OPTION_VALUES");
  IElementType PARAMETERS = new CasbinElementType("PARAMETERS");
  IElementType PAREN_EXPR = new CasbinElementType("PAREN_EXPR");
  IElementType PROPERTY = new CasbinElementType("PROPERTY");
  IElementType RELATIONAL_EXPR = new CasbinElementType("RELATIONAL_EXPR");
  IElementType SECTION = new CasbinElementType("SECTION");
  IElementType SECTION_NAME = new CasbinElementType("SECTION_NAME");
  IElementType SOME_VALUE = new CasbinElementType("SOME_VALUE");
  IElementType STRINGS = new CasbinElementType("STRINGS");
  IElementType STRING_DOUBLE_QUOTES = new CasbinElementType("STRING_DOUBLE_QUOTES");
  IElementType STRING_SINGLE_QUOTES = new CasbinElementType("STRING_SINGLE_QUOTES");
  IElementType SUB_ATTRIBUTE = new CasbinElementType("SUB_ATTRIBUTE");
  IElementType TUPLE = new CasbinElementType("TUPLE");
  IElementType VALUE_EXPRESSION = new CasbinElementType("VALUE_EXPRESSION");
  IElementType VALUE_IDENTIFIER = new CasbinElementType("VALUE_IDENTIFIER");
  IElementType VALUE_TUPLE = new CasbinElementType("VALUE_TUPLE");

  IElementType ALLOW = new CasbinTokenType("allow");
  IElementType ASSIGN = new CasbinTokenType("=");
  IElementType CLOSE_QUOTES = new CasbinTokenType("\"");
  IElementType CLOSE_SINGLE_QUOTES = new CasbinTokenType("'");
  IElementType COMMA = new CasbinTokenType(",");
  IElementType DENY = new CasbinTokenType("deny");
  IElementType DOT = new CasbinTokenType(".");
  IElementType IDENTIFIER = new CasbinTokenType("IDENTIFIER");
  IElementType LINE_COMMENT = new CasbinTokenType("LINE_COMMENT");
  IElementType L_BRACKET = new CasbinTokenType("[");
  IElementType L_PARATHESIS = new CasbinTokenType("(");
  IElementType OPEN_QUOTES = new CasbinTokenType("OPEN_QUOTES");
  IElementType OPEN_SINGLE_QUOTES = new CasbinTokenType("OPEN_SINGLE_QUOTES");
  IElementType OP_AND = new CasbinTokenType("&&");
  IElementType OP_EQUALS = new CasbinTokenType("==");
  IElementType OP_IN = new CasbinTokenType("OP_IN");
  IElementType OP_NOT = new CasbinTokenType("!");
  IElementType OP_OR = new CasbinTokenType("||");
  IElementType R_BRACKET = new CasbinTokenType("]");
  IElementType R_PARATHESIS = new CasbinTokenType(")");
  IElementType SECTION_IDENTIFER = new CasbinTokenType("SECTION_IDENTIFER");
  IElementType STRING = new CasbinTokenType("STRING");
  IElementType STRING_SINGLE_QUOTE = new CasbinTokenType("STRING_SINGLE_QUOTE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ATTRIBUTE) {
        return new CasbinAttributeImpl(node);
      }
      else if (type == ATTRIBUTE_DEFINITION) {
        return new CasbinAttributeDefinitionImpl(node);
      }
      else if (type == EQUALITY) {
        return new CasbinEqualityImpl(node);
      }
      else if (type == FLAT_KEY) {
        return new CasbinFlatKeyImpl(node);
      }
      else if (type == FUNCTION) {
        return new CasbinFunctionImpl(node);
      }
      else if (type == FUNCTION_NAME) {
        return new CasbinFunctionNameImpl(node);
      }
      else if (type == FUNCTION_SIGNATURE) {
        return new CasbinFunctionSignatureImpl(node);
      }
      else if (type == HEADER) {
        return new CasbinHeaderImpl(node);
      }
      else if (type == LITERAL_EXPR) {
        return new CasbinLiteralExprImpl(node);
      }
      else if (type == OBJECT) {
        return new CasbinObjectImpl(node);
      }
      else if (type == OBJECT_IDENTIFIER) {
        return new CasbinObjectIdentifierImpl(node);
      }
      else if (type == OPTION_VALUES) {
        return new CasbinOptionValuesImpl(node);
      }
      else if (type == PARAMETERS) {
        return new CasbinParametersImpl(node);
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
      else if (type == STRINGS) {
        return new CasbinStringsImpl(node);
      }
      else if (type == STRING_DOUBLE_QUOTES) {
        return new CasbinStringDoubleQuotesImpl(node);
      }
      else if (type == STRING_SINGLE_QUOTES) {
        return new CasbinStringSingleQuotesImpl(node);
      }
      else if (type == SUB_ATTRIBUTE) {
        return new CasbinSubAttributeImpl(node);
      }
      else if (type == TUPLE) {
        return new CasbinTupleImpl(node);
      }
      else if (type == VALUE_EXPRESSION) {
        return new CasbinValueExpressionImpl(node);
      }
      else if (type == VALUE_IDENTIFIER) {
        return new CasbinValueIdentifierImpl(node);
      }
      else if (type == VALUE_TUPLE) {
        return new CasbinValueTupleImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
