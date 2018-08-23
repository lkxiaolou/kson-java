package com.kson.core;

import com.kson.core.annotations.RequestMapping;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lkxiaolou
 */
public class ControllerMap {

    private static Map<String, Method> controllerMap = new HashMap<>();

    public void addController(String path, Method method) {
        controllerMap.put(path, method);
    }

    public Method getController(String path) {
        return controllerMap.get(path);
    }

    public void findAnnotationControllers(ClassScan classScan) {
        for (Class clazz : classScan.getClassSet()) {
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (method.getDeclaredAnnotation(RequestMapping.class) != null) {
                    for (String path : method.getDeclaredAnnotation(RequestMapping.class).path()) {
                        addController(path, method);
                    }
                }
            }
        }
    }

}
