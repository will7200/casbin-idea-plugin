// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbin.language.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import io.github.will7200.plugins.casbin.language.psi.CasbinHeader;
import io.github.will7200.plugins.casbin.language.psi.CasbinSectionName;
import io.github.will7200.plugins.casbin.language.psi.CasbinVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CasbinHeaderImpl extends ASTWrapperPsiElement implements CasbinHeader {

  public CasbinHeaderImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CasbinVisitor visitor) {
    visitor.visitHeader(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CasbinVisitor) accept((CasbinVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public CasbinSectionName getSectionName() {
    return findChildByClass(CasbinSectionName.class);
  }

}
