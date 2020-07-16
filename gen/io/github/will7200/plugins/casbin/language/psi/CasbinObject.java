// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbin.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CasbinObject extends PsiElement {

  @NotNull
  CasbinAttribute getAttribute();

  @NotNull
  CasbinObjectIdentifier getObjectIdentifier();

  @Nullable
  String getName();

}
