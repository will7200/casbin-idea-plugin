package io.github.will7200.plugins.casbin.ext

import com.googlecode.aviator.runtime.function.LambdaFunction
import com.googlecode.aviator.runtime.type.AviatorObject
import org.casbin.jcasbin.util.function.CustomFunction

class AviatorLambdaCasbinAdapter(private val name: String, val function: LambdaFunction) : CustomFunction() {

    override fun getName(): String {
        return name
    }

    override fun call(env: Map<String?, Any?>): AviatorObject? {
        return function.call(env)
    }

    override fun call(env: Map<String?, Any?>, arg1: AviatorObject?): AviatorObject? {
        return function.call(env, arg1)
    }

    override fun call(
        env: Map<String?, Any?>, arg1: AviatorObject?,
        arg2: AviatorObject?
    ): AviatorObject? {
        return function.call(env, arg1, arg2)
    }

    override fun call(
        env: Map<String?, Any?>, arg1: AviatorObject?,
        arg2: AviatorObject?, arg3: AviatorObject?
    ): AviatorObject? {
        return function.call(env, arg1, arg2, arg3)
    }

    override fun call(
        env: Map<String?, Any?>, arg1: AviatorObject?,
        arg2: AviatorObject?, arg3: AviatorObject?, arg4: AviatorObject?
    ): AviatorObject? {
        return function.call(env, arg1, arg2, arg3, arg4)
    }

    override fun call(
        env: Map<String?, Any?>, arg1: AviatorObject?,
        arg2: AviatorObject?, arg3: AviatorObject?, arg4: AviatorObject?,
        arg5: AviatorObject?
    ): AviatorObject? {
        return function.call(env, arg1, arg2, arg3, arg4, arg5)
    }


    override fun call(
        env: Map<String?, Any?>, arg1: AviatorObject?,
        arg2: AviatorObject?, arg3: AviatorObject?, arg4: AviatorObject?,
        arg5: AviatorObject?, arg6: AviatorObject?
    ): AviatorObject? {
        return function.call(env, arg1, arg2, arg3, arg4, arg5, arg6)
    }


    override fun call(
        env: Map<String?, Any?>, arg1: AviatorObject?,
        arg2: AviatorObject?, arg3: AviatorObject?, arg4: AviatorObject?,
        arg5: AviatorObject?, arg6: AviatorObject?, arg7: AviatorObject?
    ): AviatorObject? {
        return function.call(env, arg1, arg2, arg3, arg4, arg5, arg6, arg7)
    }
}