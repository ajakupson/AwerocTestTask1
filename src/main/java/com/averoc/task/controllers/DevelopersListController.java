package com.averoc.task.controllers;

/**
 * Created by de1mos on 5.08.16.
 */

import com.averoc.task.utils.XmlHelper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class DevelopersListController {

    private static Logger LOG = Logger.getLogger(DevelopersListController.class);

    private static final String FILE_NAME = "JavaTestAssignment.xml";

    @RequestMapping(value = {"/", "/developers-list"})
    public String developersList(HttpServletRequest request) {
        LOG.debug("DevelopersListController -> developersList method executed");

        String servletRealPath = getServletContextRealPath(request);
        LOG.debug("servletRealPath = " + servletRealPath);

        String fileToRead = servletRealPath + "resources/other/" + FILE_NAME;
        Document xmlData = XmlHelper.readXmlFile(fileToRead);

        return "developers-list";
    }

    private String getServletContextRealPath(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("/");

        return realPath;
    }
}
