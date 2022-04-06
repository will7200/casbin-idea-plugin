// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbin.language.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.LightPsiParser;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

import static io.github.will7200.plugins.casbin.language.parser.CasbinParserUtil.*;
import static io.github.will7200.plugins.casbin.language.psi.CasbinElementTypes.*;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class CasbinParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, EXTENDS_SETS_);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return casbinModel(b, l + 1);
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(EXPR, LITERAL_EXPR, PAREN_EXPR, RELATIONAL_EXPR),
  };

  /* ********************************************************** */
  // IDENTIFIER | "_"
  public static boolean attribute(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ATTRIBUTE, "<attribute>");
    r = consumeToken(b, IDENTIFIER);
    if (!r) r = consumeToken(b, "_");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER | "_"
  public static boolean attribute_definition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute_definition")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ATTRIBUTE_DEFINITION, "<attribute definition>");
    r = consumeToken(b, IDENTIFIER);
    if (!r) r = consumeToken(b, "_");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // section*
  static boolean casbinModel(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "casbinModel")) return false;
    while (true) {
      int c = current_position_(b);
      if (!section(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "casbinModel", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // some_value (OP_EQUALS | OP_IN) some_value
  public static boolean equality(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "equality")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EQUALITY, "<equality>");
    r = some_value(b, l + 1);
    r = r && equality_1(b, l + 1);
    r = r && some_value(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // OP_EQUALS | OP_IN
  private static boolean equality_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "equality_1")) return false;
    boolean r;
    r = consumeToken(b, OP_EQUALS);
    if (!r) r = consumeToken(b, OP_IN);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean flat_key(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "flat_key")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, FLAT_KEY, r);
    return r;
  }

  /* ********************************************************** */
  // [OP_NOT] function_name L_PARATHESIS function_signature R_PARATHESIS
  public static boolean function(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function")) return false;
    if (!nextTokenIs(b, "<function>", IDENTIFIER, OP_NOT)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION, "<function>");
    r = function_0(b, l + 1);
    r = r && function_name(b, l + 1);
    r = r && consumeToken(b, L_PARATHESIS);
    r = r && function_signature(b, l + 1);
    r = r && consumeToken(b, R_PARATHESIS);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [OP_NOT]
  private static boolean function_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_0")) return false;
    consumeToken(b, OP_NOT);
    return true;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean function_name(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_name")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, FUNCTION_NAME, r);
    return r;
  }

  /* ********************************************************** */
  // function | equality | parameters
  public static boolean function_signature(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_signature")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_SIGNATURE, "<function signature>");
    r = function(b, l + 1);
    if (!r) r = equality(b, l + 1);
    if (!r) r = parameters(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // L_BRACKET section_name R_BRACKET
  public static boolean header(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "header")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, HEADER, "<header>");
    r = consumeToken(b, L_BRACKET);
    p = r; // pin = 1
    r = r && report_error_(b, section_name(b, l + 1));
    r = p && consumeToken(b, R_BRACKET) && r;
    exit_section_(b, l, m, r, p, CasbinParser::not_next_entry);
    return r || p;
  }

  /* ********************************************************** */
  // flat_key
  static boolean key(PsiBuilder b, int l) {
    return flat_key(b, l + 1);
  }

  /* ********************************************************** */
  // !header
  static boolean not_header(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "not_header")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !header(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // !(header | value)
  static boolean not_next_entry(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "not_next_entry")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !not_next_entry_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // header | value
  private static boolean not_next_entry_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "not_next_entry_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = header(b, l + 1);
    if (!r) r = value(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // object_identifier DOT attribute (DOT sub_attribute)*
  public static boolean object(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = object_identifier(b, l + 1);
    r = r && consumeToken(b, DOT);
    r = r && attribute(b, l + 1);
    r = r && object_3(b, l + 1);
    exit_section_(b, m, OBJECT, r);
    return r;
  }

  // (DOT sub_attribute)*
  private static boolean object_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!object_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "object_3", c)) break;
    }
    return true;
  }

  // DOT sub_attribute
  private static boolean object_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOT);
    r = r && sub_attribute(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean object_identifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "object_identifier")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, OBJECT_IDENTIFIER, r);
    return r;
  }

  /* ********************************************************** */
  // value_tuple | value_expression | value_identifier
  public static boolean option_values(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "option_values")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OPTION_VALUES, "<option values>");
    r = value_tuple(b, l + 1);
    if (!r) r = value_expression(b, l + 1);
    if (!r) r = value_identifier(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (some_value (COMMA some_value)+) | some_value
  public static boolean parameters(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameters")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PARAMETERS, "<parameters>");
    r = parameters_0(b, l + 1);
    if (!r) r = some_value(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // some_value (COMMA some_value)+
  private static boolean parameters_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameters_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = some_value(b, l + 1);
    r = r && parameters_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA some_value)+
  private static boolean parameters_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameters_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = parameters_0_1_0(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!parameters_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "parameters_0_1", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMA some_value
  private static boolean parameters_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameters_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && some_value(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // '(' [ !')' Expr (',' Expr) * ] ')'
  static boolean parenConstruct(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parenConstruct")) return false;
    if (!nextTokenIs(b, L_PARATHESIS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_PARATHESIS);
    r = r && parenConstruct_1(b, l + 1);
    r = r && consumeToken(b, R_PARATHESIS);
    exit_section_(b, m, null, r);
    return r;
  }

  // [ !')' Expr (',' Expr) * ]
  private static boolean parenConstruct_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parenConstruct_1")) return false;
    parenConstruct_1_0(b, l + 1);
    return true;
  }

  // !')' Expr (',' Expr) *
  private static boolean parenConstruct_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parenConstruct_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = parenConstruct_1_0_0(b, l + 1);
    r = r && Expr(b, l + 1, -1);
    r = r && parenConstruct_1_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // !')'
  private static boolean parenConstruct_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parenConstruct_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !consumeToken(b, R_PARATHESIS);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (',' Expr) *
  private static boolean parenConstruct_1_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parenConstruct_1_0_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!parenConstruct_1_0_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "parenConstruct_1_0_2", c)) break;
    }
    return true;
  }

  // ',' Expr
  private static boolean parenConstruct_1_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parenConstruct_1_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && Expr(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // key ASSIGN value
  public static boolean property(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, PROPERTY, "<property>");
    r = key(b, l + 1);
    r = r && consumeToken(b, ASSIGN);
    p = r; // pin = 2
    r = r && value(b, l + 1);
    exit_section_(b, l, m, r, p, CasbinParser::not_next_entry);
    return r || p;
  }

  /* ********************************************************** */
  // OP_AND | OP_OR
  static boolean relOp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "relOp")) return false;
    if (!nextTokenIs(b, "", OP_AND, OP_OR)) return false;
    boolean r;
    r = consumeToken(b, OP_AND);
    if (!r) r = consumeToken(b, OP_OR);
    return r;
  }

  /* ********************************************************** */
  // header property*
  public static boolean section(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "section")) return false;
    if (!nextTokenIs(b, L_BRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = header(b, l + 1);
    r = r && section_1(b, l + 1);
    exit_section_(b, m, SECTION, r);
    return r;
  }

  // property*
  private static boolean section_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "section_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!property(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "section_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // SECTION_IDENTIFER
  public static boolean section_name(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "section_name")) return false;
    if (!nextTokenIs(b, SECTION_IDENTIFER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SECTION_IDENTIFER);
    exit_section_(b, m, SECTION_NAME, r);
    return r;
  }

  /* ********************************************************** */
  // object | ALLOW | DENY | strings | tuple
  public static boolean some_value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "some_value")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SOME_VALUE, "<some value>");
    r = object(b, l + 1);
    if (!r) r = consumeToken(b, ALLOW);
    if (!r) r = consumeToken(b, DENY);
    if (!r) r = strings(b, l + 1);
    if (!r) r = tuple(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // OPEN_QUOTES STRING CLOSE_QUOTES
  public static boolean string_double_quotes(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "string_double_quotes")) return false;
    if (!nextTokenIs(b, OPEN_QUOTES)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, OPEN_QUOTES, STRING, CLOSE_QUOTES);
    exit_section_(b, m, STRING_DOUBLE_QUOTES, r);
    return r;
  }

  /* ********************************************************** */
  // OPEN_SINGLE_QUOTES STRING_SINGLE_QUOTE CLOSE_SINGLE_QUOTES
  public static boolean string_single_quotes(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "string_single_quotes")) return false;
    if (!nextTokenIs(b, OPEN_SINGLE_QUOTES)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, OPEN_SINGLE_QUOTES, STRING_SINGLE_QUOTE, CLOSE_SINGLE_QUOTES);
    exit_section_(b, m, STRING_SINGLE_QUOTES, r);
    return r;
  }

  /* ********************************************************** */
  // string_double_quotes | string_single_quotes
  public static boolean strings(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "strings")) return false;
    if (!nextTokenIs(b, "<strings>", OPEN_QUOTES, OPEN_SINGLE_QUOTES)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STRINGS, "<strings>");
    r = string_double_quotes(b, l + 1);
    if (!r) r = string_single_quotes(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER | "_"
  public static boolean sub_attribute(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "sub_attribute")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SUB_ATTRIBUTE, "<sub attribute>");
    r = consumeToken(b, IDENTIFIER);
    if (!r) r = consumeToken(b, "_");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // L_PARATHESIS ((strings (COMMA strings)+) | strings) R_PARATHESIS
  public static boolean tuple(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tuple")) return false;
    if (!nextTokenIs(b, L_PARATHESIS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_PARATHESIS);
    r = r && tuple_1(b, l + 1);
    r = r && consumeToken(b, R_PARATHESIS);
    exit_section_(b, m, TUPLE, r);
    return r;
  }

  // (strings (COMMA strings)+) | strings
  private static boolean tuple_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tuple_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = tuple_1_0(b, l + 1);
    if (!r) r = strings(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // strings (COMMA strings)+
  private static boolean tuple_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tuple_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = strings(b, l + 1);
    r = r && tuple_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA strings)+
  private static boolean tuple_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tuple_1_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = tuple_1_0_1_0(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!tuple_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "tuple_1_0_1", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMA strings
  private static boolean tuple_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tuple_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && strings(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // option_values
  static boolean value(PsiBuilder b, int l) {
    return option_values(b, l + 1);
  }

  /* ********************************************************** */
  // Expr
  public static boolean value_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_expression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VALUE_EXPRESSION, "<value expression>");
    r = Expr(b, l + 1, -1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean value_identifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_identifier")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, VALUE_IDENTIFIER, r);
    return r;
  }

  /* ********************************************************** */
  // attribute_definition (COMMA attribute_definition)+
  public static boolean value_tuple(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_tuple")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VALUE_TUPLE, "<value tuple>");
    r = attribute_definition(b, l + 1);
    r = r && value_tuple_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA attribute_definition)+
  private static boolean value_tuple_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_tuple_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = value_tuple_1_0(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!value_tuple_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "value_tuple_1", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMA attribute_definition
  private static boolean value_tuple_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_tuple_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && attribute_definition(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Expression root: Expr
  // Operator priority table:
  // 0: BINARY(relationalExpr)
  // 1: ATOM(literalExpr) ATOM(parenExpr)
  public static boolean Expr(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "Expr")) return false;
    addVariant(b, "<expr>");
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<expr>");
    r = literalExpr(b, l + 1);
    if (!r) r = parenExpr(b, l + 1);
    p = r;
    r = r && Expr_0(b, l + 1, g);
    exit_section_(b, l, m, null, r, p, null);
    return r || p;
  }

  public static boolean Expr_0(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "Expr_0")) return false;
    boolean r = true;
    while (true) {
      Marker m = enter_section_(b, l, _LEFT_, null);
      if (g < 0 && relOp(b, l + 1)) {
        r = Expr(b, l, 0);
        exit_section_(b, l, m, RELATIONAL_EXPR, r, true, null);
      }
      else {
        exit_section_(b, l, m, null, false, false, null);
        break;
      }
    }
    return r;
  }

  // equality | function | some_value
  public static boolean literalExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literalExpr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LITERAL_EXPR, "<literal expr>");
    r = equality(b, l + 1);
    if (!r) r = function(b, l + 1);
    if (!r) r = some_value(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // parenConstruct
  public static boolean parenExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parenExpr")) return false;
    if (!nextTokenIsSmart(b, L_PARATHESIS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = parenConstruct(b, l + 1);
    exit_section_(b, m, PAREN_EXPR, r);
    return r;
  }

}
