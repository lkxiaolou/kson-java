package com.kson.core;

import com.alibaba.fastjson.JSON;
import com.kson.core.annotations.Get;
import com.kson.core.annotations.Post;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author lkxiaolou
 */
public class ControllerParam {

    private Method method;

    private HttpServletRequest request;

    private Object[] params;

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public ControllerParam(Method method, HttpServletRequest request) {
        this.method = method;
        this.request = request;
    }

    public ControllerParam parseParams() {

        Parameter[] parameters = method.getParameters();
        if (parameters.length == 0) {
            return this;
        }
        int i = 0;
        params = new Object[parameters.length];

        for (Parameter parameter : parameters) {
            if (parameter.getDeclaredAnnotation(Get.class) != null) {
                params[i++] = castGetParam(request.getParameter(parameter.getDeclaredAnnotation(Get.class).value()),
                        parameter.getType());

            } else if (parameter.getDeclaredAnnotation(Post.class) != null) {
                params[i++] = parsePostJsonParam(parameter.getType());
            } else {
                params[i++] = null;
            }
        }
        return this;
    }

    public <T> T parsePostJsonParam(Class<T> targetClass) {
        if (!"application/json".equals(request.getContentType())) {
            return null;
        }
        try {
            InputStream inputStream = request.getInputStream();
            return JSON.parseObject(inputStream, targetClass);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private <T> T castGetParam(String param, Class<T> targetClass) {

        if (param == null) {
            return (T) param;
        }

        if (targetClass == String.class) {
            return (T) param;
        } else if (targetClass == int.class) {
            return (T) param;
        } else if (targetClass == Integer.class) {
            return (T) Integer.valueOf(param);
        }
        return null;
    }

}
