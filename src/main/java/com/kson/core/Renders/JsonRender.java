package com.kson.core.Renders;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class JsonRender implements Render{

    @Override
    public void outPut(Object model, String viewName, HttpServletResponse resp) throws IOException {
        OutputStream outputStream = resp.getOutputStream();
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setHeader("Powered-By", "Kson");
        if (model == null) {
            outputStream.write("".getBytes());
        } else if (model instanceof String) {
            outputStream.write(((String) model).getBytes());
        } else {
            outputStream.write(JSON.toJSONBytes(model));
        }
    }

}
