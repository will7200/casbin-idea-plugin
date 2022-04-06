// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbin.language.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import io.github.will7200.plugins.casbin.language.psi.CasbinStringDoubleQuotes;
import io.github.will7200.plugins.casbin.language.psi.CasbinStringSingleQuotes;
import io.github.will7200.plugins.casbin.language.psi.CasbinStrings;
import io.github.will7200.plugins.casbin.language.psi.CasbinVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CasbinStringsImpl extends ASTWrapperPsiElement implements CasbinStrings {

  public CasbinStringsImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CasbinVisitor visitor) {
    visitor.visitStrings(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CasbinVisitor) accept((CasbinVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public CasbinStringDoubleQuotes getStringDoubleQuotes() {
    return findChildByClass(CasbinStringDoubleQuotes.class);
  }

  @Override
  @Nullable
  public CasbinStringSingleQuotes getStringSingleQuotes() {
    return findChildByClass(CasbinStringSingleQuotes.class);
  }

}
