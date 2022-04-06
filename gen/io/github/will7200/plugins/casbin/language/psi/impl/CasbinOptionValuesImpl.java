// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbin.language.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import io.github.will7200.plugins.casbin.language.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CasbinOptionValuesImpl extends ASTWrapperPsiElement implements CasbinOptionValues {

  public CasbinOptionValuesImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CasbinVisitor visitor) {
    visitor.visitOptionValues(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CasbinVisitor) accept((CasbinVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public CasbinValueExpression getValueExpression() {
    return findChildByClass(CasbinValueExpression.class);
  }

  @Override
  @Nullable
  public CasbinValueIdentifier getValueIdentifier() {
    return findChildByClass(CasbinValueIdentifier.class);
  }

  @Override
  @Nullable
  public CasbinValueTuple getValueTuple() {
    return findChildByClass(CasbinValueTuple.class);
  }

}
