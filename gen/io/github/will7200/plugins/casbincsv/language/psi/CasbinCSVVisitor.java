// This is a generated file. Not intended for manual editing.
package io.github.will7200.plugins.casbincsv.language.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

public class CasbinCSVVisitor extends PsiElementVisitor {

  public void visitField(@NotNull CasbinCSVField o) {
    visitPsiElement(o);
  }

  public void visitJson(@NotNull CasbinCSVJson o) {
    visitPsiElement(o);
  }

  public void visitJsonArray(@NotNull CasbinCSVJsonArray o) {
    visitJson(o);
  }

  public void visitJsonName(@NotNull CasbinCSVJsonName o) {
    visitPsiElement(o);
  }

  public void visitJsonObject(@NotNull CasbinCSVJsonObject o) {
    visitJson(o);
  }

  public void visitJsonProp(@NotNull CasbinCSVJsonProp o) {
    visitPsiElement(o);
  }

  public void visitJsonValue(@NotNull CasbinCSVJsonValue o) {
    visitPsiElement(o);
  }

  public void visitRecord(@NotNull CasbinCSVRecord o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
