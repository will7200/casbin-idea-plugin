// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbincsv.language.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import io.github.will7200.plugins.casbincsv.language.psi.CasbinCSVJsonName;
import io.github.will7200.plugins.casbincsv.language.psi.CasbinCSVVisitor;
import org.jetbrains.annotations.NotNull;

import static io.github.will7200.plugins.casbincsv.language.psi.CasbinCSVElementTypes.ID;

public class CasbinCSVJsonNameImpl extends ASTWrapperPsiElement implements CasbinCSVJsonName {

  public CasbinCSVJsonNameImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CasbinCSVVisitor visitor) {
    visitor.visitJsonName(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CasbinCSVVisitor) accept((CasbinCSVVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PsiElement getId() {
    return findNotNullChildByType(ID);
  }

}
