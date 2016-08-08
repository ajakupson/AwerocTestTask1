package com.averoc.task.controllers;

/**
 * Created by de1mos on 5.08.16.
 */

import com.averoc.task.models.Developer;
import com.averoc.task.models.Sprint;
import com.averoc.task.models.Task;
import com.averoc.task.services.DevelopersService;
import com.averoc.task.utils.Utilities;
import com.averoc.task.utils.XmlHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class DevelopersListController {

    private static Logger LOG = Logger.getLogger(DevelopersListController.class);
    private static final Gson gson = new GsonBuilder().serializeNulls().create();

    @Autowired
    private DevelopersService developersService;

    private static final String FILE_NAME = "JavaTestAssignment.xml";

    /**
     * Main test action
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = {"/", "/developers-list"})
    public String developersList(HttpServletRequest request, Model model) {
        LOG.debug("DevelopersListController -> developersList method executed");

        String servletRealPath = Utilities.getServletContextRealPath(request);
        LOG.debug("servletRealPath = " + servletRealPath);

        String fileToRead = servletRealPath + "resources/other/" + FILE_NAME;
        Document xmlData = XmlHelper.readXmlFile(fileToRead);

        List<Developer> developers = developersService.createDevelopersListData(xmlData);
//        String developersDataJson = gson.toJson(developers);
//        model.addAttribute("developersData", developersDataJson);

        Collections.sort(developers);
        model.addAttribute("developersData", developers);

        return "developers-list";
    }

    // getters / setters
    public DevelopersService getDevelopersService() {
        return developersService;
    }
    public void setDevelopersService(DevelopersService developersService) {
        this.developersService = developersService;
    }
}
