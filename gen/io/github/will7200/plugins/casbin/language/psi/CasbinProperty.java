// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbin.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.navigation.ItemPresentation;

public interface CasbinProperty extends PsiElement {

  @NotNull
  CasbinFlatKey getFlatKey();

  @Nullable
  CasbinOptionValues getOptionValues();

  @Nullable
  String getName();

  @Nullable
  String getKey();

  @Nullable
  String getValue();

  @Nullable
  ItemPresentation getPresentation();

}
