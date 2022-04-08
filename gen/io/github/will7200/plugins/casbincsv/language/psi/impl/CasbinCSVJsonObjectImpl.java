// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbincsv.language.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import io.github.will7200.plugins.casbincsv.language.psi.CasbinCSVJsonObject;
import io.github.will7200.plugins.casbincsv.language.psi.CasbinCSVJsonProp;
import io.github.will7200.plugins.casbincsv.language.psi.CasbinCSVVisitor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CasbinCSVJsonObjectImpl extends CasbinCSVJsonImpl implements CasbinCSVJsonObject {

  public CasbinCSVJsonObjectImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull CasbinCSVVisitor visitor) {
    visitor.visitJsonObject(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CasbinCSVVisitor) accept((CasbinCSVVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<CasbinCSVJsonProp> getJsonPropList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CasbinCSVJsonProp.class);
  }

}
