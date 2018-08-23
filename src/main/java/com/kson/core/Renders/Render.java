package com.kson.core.Renders;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Render {

    /**
     * 输出到浏览器
     * @param model
     * @param viewName
     * @param resp
     * @throws IOException
     */
    void outPut(Object model, String viewName, HttpServletResponse resp) throws IOException;

}
