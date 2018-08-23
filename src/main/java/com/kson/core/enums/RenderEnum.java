package com.kson.core.enums;

import com.kson.core.Renders.JsonRender;
import com.kson.core.Renders.Render;

public enum  RenderEnum {

    /**
     * json渲染器
     */
    JSON_RENDER("json", new JsonRender());

    private String name;
    private Render render;

    RenderEnum(String name, Render render) {
        this.name = name;
        this.render = render;
    }

    public String getName() {
        return name;
    }

    public Render getRender() {
        return render;
    }
}
