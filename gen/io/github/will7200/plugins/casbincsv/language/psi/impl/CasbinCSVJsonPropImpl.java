// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbincsv.language.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import io.github.will7200.plugins.casbincsv.language.psi.CasbinCSVJsonName;
import io.github.will7200.plugins.casbincsv.language.psi.CasbinCSVJsonProp;
import io.github.will7200.plugins.casbincsv.language.psi.CasbinCSVJsonValue;
import io.github.will7200.plugins.casbincsv.language.psi.CasbinCSVVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CasbinCSVJsonPropImpl extends ASTWrapperPsiElement implements CasbinCSVJsonProp {

  public CasbinCSVJsonPropImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CasbinCSVVisitor visitor) {
    visitor.visitJsonProp(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CasbinCSVVisitor) accept((CasbinCSVVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public CasbinCSVJsonName getJsonName() {
    return findChildByClass(CasbinCSVJsonName.class);
  }

  @Override
  @Nullable
  public CasbinCSVJsonValue getJsonValue() {
    return findChildByClass(CasbinCSVJsonValue.class);
  }

}
