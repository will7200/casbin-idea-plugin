// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbincsv.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static io.github.will7200.plugins.casbincsv.language.psi.CasbinCSVElementTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import io.github.will7200.plugins.casbincsv.language.psi.*;

public class CasbinCSVRecordImpl extends ASTWrapperPsiElement implements CasbinCSVRecord {

  public CasbinCSVRecordImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CasbinCSVVisitor visitor) {
    visitor.visitRecord(this);
  }

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
