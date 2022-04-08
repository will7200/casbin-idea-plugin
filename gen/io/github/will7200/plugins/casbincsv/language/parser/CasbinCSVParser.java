// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbincsv.language.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.LightPsiParser;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

import static com.intellij.lang.WhitespacesBinders.GREEDY_LEFT_BINDER;
import static com.intellij.lang.WhitespacesBinders.GREEDY_RIGHT_BINDER;
import static io.github.will7200.plugins.casbincsv.language.parser.CasbinCSVParserUtil.*;
import static io.github.will7200.plugins.casbincsv.language.psi.CasbinCSVElementTypes.*;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class CasbinCSVParser implements PsiParser, LightPsiParser {

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
    return casbinCsvFile(b, l + 1);
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(JSON, JSON_ARRAY, JSON_OBJECT),
  };

  /* ********************************************************** */
  // record (CRLF record)* [CRLF]
  static boolean casbinCsvFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "casbinCsvFile")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = record(b, l + 1);
    r = r && casbinCsvFile_1(b, l + 1);
    r = r && casbinCsvFile_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (CRLF record)*
  private static boolean casbinCsvFile_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "casbinCsvFile_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!casbinCsvFile_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "casbinCsvFile_1", c)) break;
    }
    return true;
  }

  // CRLF record
  private static boolean casbinCsvFile_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "casbinCsvFile_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CRLF);
    r = r && record(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [CRLF]
  private static boolean casbinCsvFile_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "casbinCsvFile_2")) return false;
    consumeToken(b, CRLF);
    return true;
  }

  /* ********************************************************** */
  // QUOTE (TEXT | ESCAPE_CHARACTER | ESCAPED_TEXT)* QUOTE
  static boolean escaped(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "escaped")) return false;
    if (!nextTokenIs(b, QUOTE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, QUOTE);
    r = r && escaped_1(b, l + 1);
    r = r && consumeToken(b, QUOTE);
    exit_section_(b, m, null, r);
    return r;
  }

  // (TEXT | ESCAPE_CHARACTER | ESCAPED_TEXT)*
  private static boolean escaped_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "escaped_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!escaped_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "escaped_1", c)) break;
    }
    return true;
  }

  // TEXT | ESCAPE_CHARACTER | ESCAPED_TEXT
  private static boolean escaped_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "escaped_1_0")) return false;
    boolean r;
    r = consumeToken(b, TEXT);
    if (!r) r = consumeToken(b, ESCAPE_CHARACTER);
    if (!r) r = consumeToken(b, ESCAPED_TEXT);
    return r;
  }

  /* ********************************************************** */
  // json | escaped | nonEscaped
  public static boolean field(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "field")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FIELD, "<field>");
    r = json(b, l + 1);
    if (!r) r = escaped(b, l + 1);
    if (!r) r = nonEscaped(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // jsonArray | jsonObject
  public static boolean json(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "json")) return false;
    if (!nextTokenIs(b, "<json>", BRACE1, BRACK1)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, JSON, "<json>");
    r = jsonArray(b, l + 1);
    if (!r) r = jsonObject(b, l + 1);
    register_hook_(b, WS_BINDERS, null, null);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // '[' [!']' jsonItem (!']' ',' jsonItem) *] ']'
  public static boolean jsonArray(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsonArray")) return false;
    if (!nextTokenIs(b, BRACK1)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JSON_ARRAY, null);
    r = consumeToken(b, BRACK1);
    p = r; // pin = 1
    r = r && report_error_(b, jsonArray_1(b, l + 1));
    r = p && consumeToken(b, BRACK2) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // [!']' jsonItem (!']' ',' jsonItem) *]
  private static boolean jsonArray_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsonArray_1")) return false;
    jsonArray_1_0(b, l + 1);
    return true;
  }

  // !']' jsonItem (!']' ',' jsonItem) *
  private static boolean jsonArray_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsonArray_1_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = jsonArray_1_0_0(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, jsonItem(b, l + 1));
    r = p && jsonArray_1_0_2(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // !']'
  private static boolean jsonArray_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsonArray_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !consumeToken(b, BRACK2);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (!']' ',' jsonItem) *
  private static boolean jsonArray_1_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsonArray_1_0_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!jsonArray_1_0_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "jsonArray_1_0_2", c)) break;
    }
    return true;
  }

  // !']' ',' jsonItem
  private static boolean jsonArray_1_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsonArray_1_0_2_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = jsonArray_1_0_2_0_0(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, consumeToken(b, ","));
    r = p && jsonItem(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // !']'
  private static boolean jsonArray_1_0_2_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsonArray_1_0_2_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !consumeToken(b, BRACK2);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // json
  static boolean jsonItem(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsonItem")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_);
    r = json(b, l + 1);
    exit_section_(b, l, m, r, false, CasbinCSVParser::jsonRecover);
    return r;
  }

  /* ********************************************************** */
  // ID
  public static boolean jsonName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsonName")) return false;
    if (!nextTokenIs(b, "<jsonName>", ID)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JSON_NAME, "<jsonName>");
    r = consumeToken(b, ID);
    register_hook_(b, RIGHT_BINDER, GREEDY_RIGHT_BINDER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // '{' [!'}' jsonProp (!'}' COMMA jsonProp) *] '}'
  public static boolean jsonObject(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsonObject")) return false;
    if (!nextTokenIs(b, BRACE1)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JSON_OBJECT, null);
    r = consumeToken(b, BRACE1);
    p = r; // pin = 1
    r = r && report_error_(b, jsonObject_1(b, l + 1));
    r = p && consumeToken(b, BRACE2) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // [!'}' jsonProp (!'}' COMMA jsonProp) *]
  private static boolean jsonObject_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsonObject_1")) return false;
    jsonObject_1_0(b, l + 1);
    return true;
  }

  // !'}' jsonProp (!'}' COMMA jsonProp) *
  private static boolean jsonObject_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsonObject_1_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = jsonObject_1_0_0(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, jsonProp(b, l + 1));
    r = p && jsonObject_1_0_2(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // !'}'
  private static boolean jsonObject_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsonObject_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !consumeToken(b, BRACE2);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (!'}' COMMA jsonProp) *
  private static boolean jsonObject_1_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsonObject_1_0_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!jsonObject_1_0_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "jsonObject_1_0_2", c)) break;
    }
    return true;
  }

  // !'}' COMMA jsonProp
  private static boolean jsonObject_1_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsonObject_1_0_2_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = jsonObject_1_0_2_0_0(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, consumeToken(b, COMMA));
    r = p && jsonProp(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // !'}'
  private static boolean jsonObject_1_0_2_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsonObject_1_0_2_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !consumeToken(b, BRACE2);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // [] jsonName ':' jsonValue
  public static boolean jsonProp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsonProp")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JSON_PROP, "<json prop>");
    r = jsonProp_0(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, jsonName(b, l + 1));
    r = p && report_error_(b, consumeToken(b, COLON)) && r;
    r = p && jsonValue(b, l + 1) && r;
    exit_section_(b, l, m, r, p, CasbinCSVParser::jsonRecover);
    return r || p;
  }

  // []
  private static boolean jsonProp_0(PsiBuilder b, int l) {
    return true;
  }

  /* ********************************************************** */
  // !(',' | ']' | '}' | '[' | '{')
  static boolean jsonRecover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsonRecover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !jsonRecover_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ',' | ']' | '}' | '[' | '{'
  private static boolean jsonRecover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsonRecover_0")) return false;
    boolean r;
    r = consumeToken(b, ",");
    if (!r) r = consumeToken(b, BRACK2);
    if (!r) r = consumeToken(b, BRACE2);
    if (!r) r = consumeToken(b, BRACK1);
    if (!r) r = consumeToken(b, BRACE1);
    return r;
  }

  /* ********************************************************** */
  // STRING | NUMBER | json
  public static boolean jsonValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jsonValue")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JSON_VALUE, "<jsonValue>");
    r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, NUMBER);
    if (!r) r = json(b, l + 1);
    register_hook_(b, LEFT_BINDER, GREEDY_LEFT_BINDER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (TEXT | ESCAPE_CHARACTER)*
  static boolean nonEscaped(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "nonEscaped")) return false;
    while (true) {
      int c = current_position_(b);
      if (!nonEscaped_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "nonEscaped", c)) break;
    }
    return true;
  }

  // TEXT | ESCAPE_CHARACTER
  private static boolean nonEscaped_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "nonEscaped_0")) return false;
    boolean r;
    r = consumeToken(b, TEXT);
    if (!r) r = consumeToken(b, ESCAPE_CHARACTER);
    return r;
  }

  /* ********************************************************** */
  // field (COMMA field)*
  public static boolean record(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "record")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, RECORD, "<record>");
    r = field(b, l + 1);
    r = r && record_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA field)*
  private static boolean record_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "record_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!record_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "record_1", c)) break;
    }
    return true;
  }

  // COMMA field
  private static boolean record_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "record_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && field(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

}
