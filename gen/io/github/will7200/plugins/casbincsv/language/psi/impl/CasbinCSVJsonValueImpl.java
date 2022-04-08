// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbincsv.language.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import io.github.will7200.plugins.casbincsv.language.psi.CasbinCSVJson;
import io.github.will7200.plugins.casbincsv.language.psi.CasbinCSVJsonValue;
import io.github.will7200.plugins.casbincsv.language.psi.CasbinCSVVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static io.github.will7200.plugins.casbincsv.language.psi.CasbinCSVElementTypes.NUMBER;
import static io.github.will7200.plugins.casbincsv.language.psi.CasbinCSVElementTypes.STRING;

public class CasbinCSVJsonValueImpl extends ASTWrapperPsiElement implements CasbinCSVJsonValue {

  public CasbinCSVJsonValueImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CasbinCSVVisitor visitor) {
    visitor.visitJsonValue(this);
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

  @Override
  @Nullable
  public PsiElement getNumber() {
    return findChildByType(NUMBER);
  }

  @Override
  @Nullable
  public PsiElement getString() {
    return findChildByType(STRING);
  }

}
