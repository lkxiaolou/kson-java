package com.kson.core;

/**
 * @author lkxiaolou
 */
public class ControllerResponse {

    public ControllerResponse(String viewName, Object model) {
        this.viewName = viewName;
        this.model = model;
    }

    private String viewName;
    private Object model;

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Object getModel() {
        return model;
    }

    public void setModel(Object model) {
        this.model = model;
    }
}
