// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbin.language.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import io.github.will7200.plugins.casbin.language.psi.CasbinFunction;
import io.github.will7200.plugins.casbin.language.psi.CasbinFunctionName;
import io.github.will7200.plugins.casbin.language.psi.CasbinFunctionSignature;
import io.github.will7200.plugins.casbin.language.psi.CasbinVisitor;
import org.jetbrains.annotations.NotNull;

public class CasbinFunctionImpl extends CasbinFunctionMixin implements CasbinFunction {

  public CasbinFunctionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CasbinVisitor visitor) {
    visitor.visitFunction(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CasbinVisitor) accept((CasbinVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public CasbinFunctionName getFunctionName() {
    return findNotNullChildByClass(CasbinFunctionName.class);
  }

  @Override
  @NotNull
  public CasbinFunctionSignature getFunctionSignature() {
    return findNotNullChildByClass(CasbinFunctionSignature.class);
  }

}
