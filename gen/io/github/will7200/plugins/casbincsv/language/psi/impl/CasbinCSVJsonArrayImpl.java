// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbincsv.language.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import io.github.will7200.plugins.casbincsv.language.psi.CasbinCSVJson;
import io.github.will7200.plugins.casbincsv.language.psi.CasbinCSVJsonArray;
import io.github.will7200.plugins.casbincsv.language.psi.CasbinCSVVisitor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CasbinCSVJsonArrayImpl extends CasbinCSVJsonImpl implements CasbinCSVJsonArray {

  public CasbinCSVJsonArrayImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull CasbinCSVVisitor visitor) {
    visitor.visitJsonArray(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CasbinCSVVisitor) accept((CasbinCSVVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<CasbinCSVJson> getJsonList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CasbinCSVJson.class);
  }

}
