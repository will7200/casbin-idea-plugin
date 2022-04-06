// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbin.language.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import io.github.will7200.plugins.casbin.language.psi.CasbinExpr;
import io.github.will7200.plugins.casbin.language.psi.CasbinParenExpr;
import io.github.will7200.plugins.casbin.language.psi.CasbinVisitor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CasbinParenExprImpl extends CasbinExprImpl implements CasbinParenExpr {

  public CasbinParenExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull CasbinVisitor visitor) {
    visitor.visitParenExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CasbinVisitor) accept((CasbinVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<CasbinExpr> getExprList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CasbinExpr.class);
  }

}
