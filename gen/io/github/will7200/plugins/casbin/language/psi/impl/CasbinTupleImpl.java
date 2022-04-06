// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbin.language.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import io.github.will7200.plugins.casbin.language.psi.CasbinStrings;
import io.github.will7200.plugins.casbin.language.psi.CasbinTuple;
import io.github.will7200.plugins.casbin.language.psi.CasbinVisitor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CasbinTupleImpl extends ASTWrapperPsiElement implements CasbinTuple {

  public CasbinTupleImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CasbinVisitor visitor) {
    visitor.visitTuple(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CasbinVisitor) accept((CasbinVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<CasbinStrings> getStringsList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CasbinStrings.class);
  }

}
