package com.kson.core.Renders;

import com.kson.core.enums.RenderEnum;

public class RenderFactory {

    public static Render getRender(String renderName) {
        for (RenderEnum renderEnum : RenderEnum.values()) {
            if (renderEnum.getName().equals(renderName)) {
                return renderEnum.getRender();
            }
        }
        return null;
    }

}
