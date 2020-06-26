package io.github.will7200.plugins.casbin.language.psi.impl;

import com.intellij.lang.ASTNode;
import io.github.will7200.plugins.casbin.language.psi.CasbinElementTypes;
import io.github.will7200.plugins.casbin.language.psi.CasbinProperty;
import org.jetbrains.annotations.NotNull;

public final class CasbinPsiImplUtils {
    private CasbinPsiImplUtils() {
    }

    public static String getKey(@NotNull CasbinProperty element) {
        ASTNode keyNode = element.getNode().findChildByType(CasbinElementTypes.IDENTIFIER);
        if (keyNode != null) {
            // IMPORTANT: Convert embedded escaped spaces to simple spaces
            return keyNode.getText().replaceAll("\\\\ ", " ");
        } else {
            return null;
        }
    }
    public static String getValue(@NotNull CasbinProperty element) {
        ASTNode keyNode = element.getNode().findChildByType(CasbinElementTypes.OPTION_VALUE_EXPRESSION);
        if (keyNode != null) {
            // IMPORTANT: Convert embedded escaped spaces to simple spaces
            return keyNode.getText().replaceAll("\\\\ ", " ");
        } else {
            return null;
        }
    }
}
