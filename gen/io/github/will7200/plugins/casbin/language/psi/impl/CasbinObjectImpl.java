// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbin.language.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import io.github.will7200.plugins.casbin.language.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CasbinObjectImpl extends CasbinObjectMixin implements CasbinObject {

  public CasbinObjectImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CasbinVisitor visitor) {
    visitor.visitObject(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CasbinVisitor) accept((CasbinVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public CasbinAttribute getAttribute() {
    return findNotNullChildByClass(CasbinAttribute.class);
  }

  @Override
  @NotNull
  public CasbinObjectIdentifier getObjectIdentifier() {
    return findNotNullChildByClass(CasbinObjectIdentifier.class);
  }

  @Override
  @NotNull
  public List<CasbinSubAttribute> getSubAttributeList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CasbinSubAttribute.class);
  }

}
