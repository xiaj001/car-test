/*
package com.aisino.feign;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

*/
/**
 * @author: xiajun003
 * @Date: 2018/11/30 15:05
 * @Description:
 *//*

public interface InvocationHandlerFactory {

    InvocationHandler create(Target target, Map<Method, MethodHandler> dispatch);

    */
/**
     * Like {@link InvocationHandler#invoke(Object, java.lang.reflect.Method, Object[])}, except for a
     * single method.
     *//*

    interface MethodHandler {

        Object invoke(Object[] argv) throws Throwable;
    }

    static final class Default implements InvocationHandlerFactory {

        @Override
        public InvocationHandler create(Target target, Map<Method, MethodHandler> dispatch) {
            return new ReflectiveFeign.FeignInvocationHandler(target, dispatch);
        }
    }
}
*/
