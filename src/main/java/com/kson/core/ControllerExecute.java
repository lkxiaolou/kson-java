package com.kson.core;

import java.lang.reflect.Method;

/**
 * @author lkxiaolou
 */
public class ControllerExecute {

    public static ControllerResponse execute(Method method, Object... params)
            throws Exception {
        Object obj = method.getDeclaringClass().newInstance();

        Object response = method.invoke(obj, params);

        if (response instanceof ControllerResponse) {
            return (ControllerResponse) response;
        } else {
            return new ControllerResponse("", response);
        }
    }

}
