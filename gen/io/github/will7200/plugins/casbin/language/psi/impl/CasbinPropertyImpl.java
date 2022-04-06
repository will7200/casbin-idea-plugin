// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbin.language.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import io.github.will7200.plugins.casbin.language.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CasbinPropertyImpl extends CasbinPropertyMixin implements CasbinProperty {

  public CasbinPropertyImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CasbinVisitor visitor) {
    visitor.visitProperty(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CasbinVisitor) accept((CasbinVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public CasbinFlatKey getFlatKey() {
    return findNotNullChildByClass(CasbinFlatKey.class);
  }

  @Override
  @Nullable
  public CasbinOptionValues getOptionValues() {
    return findChildByClass(CasbinOptionValues.class);
  }

  @Override
  @Nullable
  public String getKey() {
    return CasbinPsiUtils.getKey(this);
  }

  @Override
  @Nullable
  public String getValue() {
    return CasbinPsiUtils.getValue(this);
  }

}
