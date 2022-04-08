// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbincsv.language.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import io.github.will7200.plugins.casbincsv.language.psi.CasbinCSVField;
import io.github.will7200.plugins.casbincsv.language.psi.CasbinCSVJson;
import io.github.will7200.plugins.casbincsv.language.psi.CasbinCSVVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CasbinCSVFieldImpl extends ASTWrapperPsiElement implements CasbinCSVField {

  public CasbinCSVFieldImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CasbinCSVVisitor visitor) {
    visitor.visitField(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CasbinCSVVisitor) accept((CasbinCSVVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public CasbinCSVJson getJson() {
    return findChildByClass(CasbinCSVJson.class);
  }

}
