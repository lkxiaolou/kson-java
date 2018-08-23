package com.kson.core;

import com.kson.core.Renders.Render;
import com.kson.core.Renders.RenderFactory;
import com.kson.core.annotations.RenderType;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author lkxiaolou
 */
public class DispatcherServlet extends HttpServlet {

    private static ControllerMap controllerMap = new ControllerMap();

    private static final String CONTROLLERS_SCAN_CONFIG = "controllers-package-scan";
    private static final String DEFAULT_RENDER_NAME = "default-render-name";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        Method method = controllerMap.getController(pathInfo);
        if (method == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        try {

            // 解析get/post参数
            ControllerParam controllerParam = new ControllerParam(method, req).parseParams();

            // 执行逻辑
            ControllerResponse response = ControllerExecute.execute(method, controllerParam.getParams());

            // 获取渲染器
            Render render;
            if (method.getDeclaredAnnotation(RenderType.class) != null) {
                render = method.getDeclaredAnnotation(RenderType.class).value().getRender();
            } else {
                render = RenderFactory.getRender(getServletConfig().getInitParameter(DEFAULT_RENDER_NAME));
            }

            if (render == null) {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return;
            }

            // 渲染输出
            render.outPut(response.getModel(), response.getViewName(), resp);

        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void init() throws ServletException {
        // 需要扫描的包
        String packageName = getServletConfig().getInitParameter(CONTROLLERS_SCAN_CONFIG);
        // 扫描并加载所有的controller
        ClassScan classScan = new ClassScan(packageName);
        try {
            classScan.scanAllClass();
        } catch (Exception e) {

        }
        // 扫描controller中带注解的方法
        controllerMap.findAnnotationControllers(classScan);
    }
}
