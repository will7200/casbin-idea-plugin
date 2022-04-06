// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbin.language.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import io.github.will7200.plugins.casbin.language.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CasbinSomeValueImpl extends ASTWrapperPsiElement implements CasbinSomeValue {

  public CasbinSomeValueImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CasbinVisitor visitor) {
    visitor.visitSomeValue(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CasbinVisitor) accept((CasbinVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public CasbinObject getObject() {
    return findChildByClass(CasbinObject.class);
  }

  @Override
  @Nullable
  public CasbinStrings getStrings() {
    return findChildByClass(CasbinStrings.class);
  }

  @Override
  @Nullable
  public CasbinTuple getTuple() {
    return findChildByClass(CasbinTuple.class);
  }

}
