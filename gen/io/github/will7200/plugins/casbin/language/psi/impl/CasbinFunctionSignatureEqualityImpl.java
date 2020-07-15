// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbin.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static io.github.will7200.plugins.casbin.language.psi.CasbinElementTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import io.github.will7200.plugins.casbin.language.psi.*;

public class CasbinFunctionSignatureEqualityImpl extends ASTWrapperPsiElement implements CasbinFunctionSignatureEquality {

  public CasbinFunctionSignatureEqualityImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CasbinVisitor visitor) {
    visitor.visitFunctionSignatureEquality(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CasbinVisitor) accept((CasbinVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public CasbinEquality getEquality() {
    return findNotNullChildByClass(CasbinEquality.class);
  }

  @Override
  @NotNull
  public CasbinFunctionName getFunctionName() {
    return findNotNullChildByClass(CasbinFunctionName.class);
  }

}
