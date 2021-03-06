// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbin.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static io.github.will7200.plugins.casbin.language.psi.CasbinElementTypes.*;
import io.github.will7200.plugins.casbin.language.psi.*;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiNamedElement;
import com.intellij.util.IncorrectOperationException;

public class CasbinSectionImpl extends CasbinSectionMixin implements CasbinSection {

  public CasbinSectionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CasbinVisitor visitor) {
    visitor.visitSection(this);
  }

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
