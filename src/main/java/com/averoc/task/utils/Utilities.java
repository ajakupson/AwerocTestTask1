package com.averoc.task.utils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by de1mos on 8.08.16.
 */
public class Utilities {

    public static String getServletContextRealPath(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("/");

        return realPath;
    }
}
