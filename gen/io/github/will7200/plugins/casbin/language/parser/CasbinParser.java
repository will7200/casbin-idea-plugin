// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbin.language.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static io.github.will7200.plugins.casbin.language.psi.CasbinElementTypes.*;
import static io.github.will7200.plugins.casbin.language.parser.CasbinParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

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
  // some_value OP_EQUALS some_value
  public static boolean equality(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "equality")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EQUALITY, "<equality>");
    r = some_value(b, l + 1);
    r = r && consumeToken(b, OP_EQUALS);
    r = r && some_value(b, l + 1);
    exit_section_(b, l, m, r, false, null);
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
  // [OP_NOT](recursive_function | function_signature)
  public static boolean function(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function")) return false;
    if (!nextTokenIs(b, "<function>", IDENTIFIER, OP_NOT)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION, "<function>");
    r = function_0(b, l + 1);
    r = r && function_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [OP_NOT]
  private static boolean function_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_0")) return false;
    consumeToken(b, OP_NOT);
    return true;
  }

  // recursive_function | function_signature
  private static boolean function_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_1")) return false;
    boolean r;
    r = recursive_function(b, l + 1);
    if (!r) r = function_signature(b, l + 1);
    return r;
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
  // function_signature_call | function_signature_equality | function_name L_PARATHESIS function_signature R_PARATHESIS
  public static boolean function_signature(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_signature")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = function_signature_call(b, l + 1);
    if (!r) r = function_signature_equality(b, l + 1);
    if (!r) r = function_signature_2(b, l + 1);
    exit_section_(b, m, FUNCTION_SIGNATURE, r);
    return r;
  }

  // function_name L_PARATHESIS function_signature R_PARATHESIS
  private static boolean function_signature_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_signature_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = function_name(b, l + 1);
    r = r && consumeToken(b, L_PARATHESIS);
    r = r && function_signature(b, l + 1);
    r = r && consumeToken(b, R_PARATHESIS);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // function_name L_PARATHESIS parameters R_PARATHESIS
  public static boolean function_signature_call(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_signature_call")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = function_name(b, l + 1);
    r = r && consumeToken(b, L_PARATHESIS);
    r = r && parameters(b, l + 1);
    r = r && consumeToken(b, R_PARATHESIS);
    exit_section_(b, m, FUNCTION_SIGNATURE_CALL, r);
    return r;
  }

  /* ********************************************************** */
  // function_name L_PARATHESIS equality R_PARATHESIS
  public static boolean function_signature_equality(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_signature_equality")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = function_name(b, l + 1);
    r = r && consumeToken(b, L_PARATHESIS);
    r = r && equality(b, l + 1);
    r = r && consumeToken(b, R_PARATHESIS);
    exit_section_(b, m, FUNCTION_SIGNATURE_EQUALITY, r);
    return r;
  }

  /* ********************************************************** */
  // L_BRACKET section_name R_BRACKET
  public static boolean header(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "header")) return false;
    if (!nextTokenIs(b, L_BRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, L_BRACKET);
    r = r && section_name(b, l + 1);
    r = r && consumeToken(b, R_BRACKET);
    exit_section_(b, m, HEADER, r);
    return r;
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
  // !(header | option)
  static boolean not_next_entry(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "not_next_entry")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !not_next_entry_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // header | option
  private static boolean not_next_entry_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "not_next_entry_0")) return false;
    boolean r;
    r = header(b, l + 1);
    if (!r) r = consumeToken(b, OPTION);
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
  // Expr
  public static boolean option_value_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "option_value_expression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OPTION_VALUE_EXPRESSION, "<option value expression>");
    r = Expr(b, l + 1, -1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean option_value_identifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "option_value_identifier")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, OPTION_VALUE_IDENTIFIER, r);
    return r;
  }

  /* ********************************************************** */
  // attribute (COMMA attribute)+
  public static boolean option_value_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "option_value_list")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OPTION_VALUE_LIST, "<option value list>");
    r = attribute(b, l + 1);
    r = r && option_value_list_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA attribute)+
  private static boolean option_value_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "option_value_list_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = option_value_list_1_0(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!option_value_list_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "option_value_list_1", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMA attribute
  private static boolean option_value_list_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "option_value_list_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && attribute(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // option_value_list | option_value_expression | option_value_identifier
  public static boolean option_values(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "option_values")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OPTION_VALUES, "<option values>");
    r = option_value_list(b, l + 1);
    if (!r) r = option_value_expression(b, l + 1);
    if (!r) r = option_value_identifier(b, l + 1);
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
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = key(b, l + 1);
    r = r && consumeToken(b, ASSIGN);
    r = r && value(b, l + 1);
    exit_section_(b, m, PROPERTY, r);
    return r;
  }

  /* ********************************************************** */
  // function_name L_PARATHESIS function_signature R_PARATHESIS
  public static boolean recursive_function(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "recursive_function")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = function_name(b, l + 1);
    r = r && consumeToken(b, L_PARATHESIS);
    r = r && function_signature(b, l + 1);
    r = r && consumeToken(b, R_PARATHESIS);
    exit_section_(b, m, RECURSIVE_FUNCTION, r);
    return r;
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
  // object_identifier DOT attribute | ALLOW | DENY | string_value | IDENTIFIER
  public static boolean some_value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "some_value")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SOME_VALUE, "<some value>");
    r = some_value_0(b, l + 1);
    if (!r) r = consumeToken(b, ALLOW);
    if (!r) r = consumeToken(b, DENY);
    if (!r) r = string_value(b, l + 1);
    if (!r) r = consumeToken(b, IDENTIFIER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // object_identifier DOT attribute
  private static boolean some_value_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "some_value_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = object_identifier(b, l + 1);
    r = r && consumeToken(b, DOT);
    r = r && attribute(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // OPEN_QUOTES IDENTIFIER CLOSE_QUOTES
  public static boolean string_value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "string_value")) return false;
    if (!nextTokenIs(b, OPEN_QUOTES)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, OPEN_QUOTES, IDENTIFIER, CLOSE_QUOTES);
    exit_section_(b, m, STRING_VALUE, r);
    return r;
  }

  /* ********************************************************** */
  // option_values
  static boolean value(PsiBuilder b, int l) {
    return option_values(b, l + 1);
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
