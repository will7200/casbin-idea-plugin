// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbin.language.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import io.github.will7200.plugins.casbin.language.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CasbinSectionImpl extends CasbinSectionMixin implements CasbinSection {

  public CasbinSectionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CasbinVisitor visitor) {
    visitor.visitSection(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CasbinVisitor) accept((CasbinVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public CasbinHeader getHeader() {
    return findNotNullChildByClass(CasbinHeader.class);
  }

  @Override
  @NotNull
  public List<CasbinProperty> getPropertyList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CasbinProperty.class);
  }

  @Override
  @Nullable
  public CasbinProperty getProperty(@NotNull String propertyName) {
    return CasbinPsiUtils.getProperty(this, propertyName);
  }

}
