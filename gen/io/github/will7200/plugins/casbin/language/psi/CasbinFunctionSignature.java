// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbin.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CasbinFunctionSignature extends PsiElement {

  @Nullable
  CasbinFunctionName getFunctionName();

  @Nullable
  CasbinFunctionSignature getFunctionSignature();

  @Nullable
  CasbinFunctionSignatureCall getFunctionSignatureCall();

  @Nullable
  CasbinFunctionSignatureEquality getFunctionSignatureEquality();

}
