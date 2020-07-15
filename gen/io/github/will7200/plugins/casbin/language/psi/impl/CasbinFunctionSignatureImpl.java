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

public class CasbinFunctionSignatureImpl extends ASTWrapperPsiElement implements CasbinFunctionSignature {

  public CasbinFunctionSignatureImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CasbinVisitor visitor) {
    visitor.visitFunctionSignature(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CasbinVisitor) accept((CasbinVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public CasbinFunctionName getFunctionName() {
    return findChildByClass(CasbinFunctionName.class);
  }

  @Override
  @Nullable
  public CasbinFunctionSignature getFunctionSignature() {
    return findChildByClass(CasbinFunctionSignature.class);
  }

  @Override
  @Nullable
  public CasbinFunctionSignatureCall getFunctionSignatureCall() {
    return findChildByClass(CasbinFunctionSignatureCall.class);
  }

  @Override
  @Nullable
  public CasbinFunctionSignatureEquality getFunctionSignatureEquality() {
    return findChildByClass(CasbinFunctionSignatureEquality.class);
  }

}
