// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbin.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class CasbinVisitor extends PsiElementVisitor {

  public void visitExpr(@NotNull CasbinExpr o) {
    visitPsiElement(o);
  }

  public void visitAttribute(@NotNull CasbinAttribute o) {
    visitPsiElement(o);
  }

  public void visitEquality(@NotNull CasbinEquality o) {
    visitPsiElement(o);
  }

  public void visitFlatKey(@NotNull CasbinFlatKey o) {
    visitPsiElement(o);
  }

  public void visitHeader(@NotNull CasbinHeader o) {
    visitPsiElement(o);
  }

  public void visitLiteralExpr(@NotNull CasbinLiteralExpr o) {
    visitExpr(o);
  }

  public void visitObjectIdentifier(@NotNull CasbinObjectIdentifier o) {
    visitPsiElement(o);
  }

  public void visitOptionFunction(@NotNull CasbinOptionFunction o) {
    visitPsiElement(o);
  }

  public void visitOptionValueExpression(@NotNull CasbinOptionValueExpression o) {
    visitPsiElement(o);
  }

  public void visitOptionValueIdentifier(@NotNull CasbinOptionValueIdentifier o) {
    visitPsiElement(o);
  }

  public void visitOptionValueList(@NotNull CasbinOptionValueList o) {
    visitPsiElement(o);
  }

  public void visitOptionValues(@NotNull CasbinOptionValues o) {
    visitPsiElement(o);
  }

  public void visitParenExpr(@NotNull CasbinParenExpr o) {
    visitExpr(o);
  }

  public void visitProperty(@NotNull CasbinProperty o) {
    visitPsiElement(o);
  }

  public void visitRelationalExpr(@NotNull CasbinRelationalExpr o) {
    visitExpr(o);
  }

  public void visitSection(@NotNull CasbinSection o) {
    visitPsiElement(o);
  }

  public void visitSectionName(@NotNull CasbinSectionName o) {
    visitPsiElement(o);
  }

  public void visitSomeValue(@NotNull CasbinSomeValue o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
