// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbin.language.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import io.github.will7200.plugins.casbin.language.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CasbinFunctionSignatureImpl extends ASTWrapperPsiElement implements CasbinFunctionSignature {

  public CasbinFunctionSignatureImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CasbinVisitor visitor) {
    visitor.visitFunctionSignature(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CasbinVisitor) accept((CasbinVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public CasbinEquality getEquality() {
    return findChildByClass(CasbinEquality.class);
  }

  @Override
  @Nullable
  public CasbinFunction getFunction() {
    return findChildByClass(CasbinFunction.class);
  }

  @Override
  @Nullable
  public CasbinParameters getParameters() {
    return findChildByClass(CasbinParameters.class);
  }

}
