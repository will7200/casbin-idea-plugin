// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbincsv.language.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import io.github.will7200.plugins.casbincsv.language.psi.CasbinCSVField;
import io.github.will7200.plugins.casbincsv.language.psi.CasbinCSVRecord;
import io.github.will7200.plugins.casbincsv.language.psi.CasbinCSVVisitor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CasbinCSVRecordImpl extends ASTWrapperPsiElement implements CasbinCSVRecord {

  public CasbinCSVRecordImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CasbinCSVVisitor visitor) {
    visitor.visitRecord(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CasbinCSVVisitor) accept((CasbinCSVVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<CasbinCSVField> getFieldList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CasbinCSVField.class);
  }

}
