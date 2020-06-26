// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbin.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CasbinProperty extends PsiElement {

  @NotNull
  CasbinFlatKey getFlatKey();

  @Nullable
  CasbinOptionFunction getOptionFunction();

  @Nullable
  CasbinOptionValueIdentifier getOptionValueIdentifier();

  @Nullable
  CasbinOptionValueList getOptionValueList();

  @Nullable
  CasbinOptionValues getOptionValues();

  String getKey();

  String getValue();

}
