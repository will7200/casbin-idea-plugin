// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbin.language.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import io.github.will7200.plugins.casbin.language.psi.CasbinAttributeDefinition;
import io.github.will7200.plugins.casbin.language.psi.CasbinValueTuple;
import io.github.will7200.plugins.casbin.language.psi.CasbinVisitor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CasbinValueTupleImpl extends ASTWrapperPsiElement implements CasbinValueTuple {

  public CasbinValueTupleImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CasbinVisitor visitor) {
    visitor.visitValueTuple(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CasbinVisitor) accept((CasbinVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<CasbinAttributeDefinition> getAttributeDefinitionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CasbinAttributeDefinition.class);
  }

}
