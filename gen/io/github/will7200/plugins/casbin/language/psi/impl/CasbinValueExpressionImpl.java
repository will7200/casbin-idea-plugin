// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbin.language.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import io.github.will7200.plugins.casbin.language.psi.CasbinExpr;
import io.github.will7200.plugins.casbin.language.psi.CasbinValueExpression;
import io.github.will7200.plugins.casbin.language.psi.CasbinVisitor;
import org.jetbrains.annotations.NotNull;

public class CasbinValueExpressionImpl extends ASTWrapperPsiElement implements CasbinValueExpression {

  public CasbinValueExpressionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CasbinVisitor visitor) {
    visitor.visitValueExpression(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CasbinVisitor) accept((CasbinVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public CasbinExpr getExpr() {
    return findNotNullChildByClass(CasbinExpr.class);
  }

}
