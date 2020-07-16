// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbin.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static io.github.will7200.plugins.casbin.language.psi.CasbinElementTypes.*;
import io.github.will7200.plugins.casbin.language.psi.*;

public class CasbinObjectImpl extends CasbinObjectMixin implements CasbinObject {

  public CasbinObjectImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CasbinVisitor visitor) {
    visitor.visitObject(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CasbinVisitor) accept((CasbinVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public CasbinAttribute getAttribute() {
    return findNotNullChildByClass(CasbinAttribute.class);
  }

  @Override
  @NotNull
  public CasbinObjectIdentifier getObjectIdentifier() {
    return findNotNullChildByClass(CasbinObjectIdentifier.class);
  }

}
