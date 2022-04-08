package io.github.will7200.plugins.casbin

import com.googlecode.aviator.AviatorEvaluator
import com.googlecode.aviator.exception.CompileExpressionErrorException
import com.googlecode.aviator.exception.ExpressionSyntaxErrorException
import com.googlecode.aviator.runtime.function.LambdaFunction
import com.googlecode.aviator.utils.Env
import io.github.will7200.plugins.casbin.ext.AviatorLambdaCasbinAdapter
import org.casbin.jcasbin.main.Enforcer

interface CasbinCustomFunctionProvider {
    fun getScript(): String

    fun addFunctions(enforcer: Enforcer, script: String) {
        val expression = AviatorEvaluator.compile(script)
        val export = Env()
        expression.execute(export)
        export.entries.forEach {
            when (it.value) {
                is LambdaFunction -> {
                    enforcer.addFunction(it.key, AviatorLambdaCasbinAdapter(it.key, it.value as LambdaFunction))
                }
            }
        }
    }

    fun addFunctions(enforcer: Enforcer) {
        try {
            AviatorEvaluator.validate(this.getScript())
        } catch (exception: ExpressionSyntaxErrorException) {
            return
        } catch (exception: CompileExpressionErrorException) {
            return
        }
        addFunctions(enforcer, this.getScript())
    }


}