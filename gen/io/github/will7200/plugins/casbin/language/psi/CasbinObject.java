// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbin.language.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface CasbinObject extends PsiElement {

  @NotNull
  CasbinAttribute getAttribute();

  @NotNull
  CasbinObjectIdentifier getObjectIdentifier();

  @NotNull
  List<CasbinSubAttribute> getSubAttributeList();

  @Nullable
  String getName();

}
