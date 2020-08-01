// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbin.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiNamedElement;
import com.intellij.util.IncorrectOperationException;

public interface CasbinSection extends PsiElement {

  @NotNull
  CasbinHeader getHeader();

  @NotNull
  List<CasbinProperty> getPropertyList();

  //WARNING: getProperties(...) is skipped
  //matching getProperties(CasbinSection, ...)
  //methods are not found in CasbinPsiUtils

  //WARNING: hasProperty(...) is skipped
  //matching hasProperty(CasbinSection, ...)
  //methods are not found in CasbinPsiUtils

  //WARNING: setProperty(...) is skipped
  //matching setProperty(CasbinSection, ...)
  //methods are not found in CasbinPsiUtils

  @Nullable
  CasbinProperty getProperty(@NotNull String propertyName);

  @Nullable
  String getName();

  @NotNull
  PsiNamedElement setName(@NotNull String name) throws IncorrectOperationException;

  @Nullable
  ItemPresentation getPresentation();

}
