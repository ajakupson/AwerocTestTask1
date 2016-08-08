package com.averoc.task.controllers;

/**
 * Created by de1mos on 5.08.16.
 */

import com.averoc.task.models.Developer;
import com.averoc.task.models.Sprint;
import com.averoc.task.models.Task;
import com.averoc.task.utils.XmlHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;
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

        String servletRealPath = getServletContextRealPath(request);
        LOG.debug("servletRealPath = " + servletRealPath);

        String fileToRead = servletRealPath + "resources/other/" + FILE_NAME;
        Document xmlData = XmlHelper.readXmlFile(fileToRead);

        List<Developer> developers = createDevelopersListData(xmlData);
//        String developersDataJson = gson.toJson(developers);
//        model.addAttribute("developersData", developersDataJson);

        Collections.sort(developers);
        model.addAttribute("developersData", developers);

        return "developers-list";
    }

    private String getServletContextRealPath(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("/");

        return realPath;
    }

    /**
     * Data structure - Developer -> Sprint(s) -> Task(s)
     * @param xmlData
     * @return
     */
    private List<Developer> createDevelopersListData(Document xmlData) {
        Node developerNode = null;
        Developer developer = null;
        List<Developer> developersList = new ArrayList<Developer>();
        Element developerRecord = null;

        NodeList developersNodes = xmlData.getElementsByTagName("developer");
        for (int index = 0; index < developersNodes.getLength(); index++) {
            developerNode = developersNodes.item(index);

            if (developerNode.getNodeType() == Node.ELEMENT_NODE) {
                developerRecord = (Element) developerNode;

                developer = new Developer();
                developer.setFullName(developerRecord.getElementsByTagName("name").item(0).getTextContent());
                developer.setDeveloperSprints(createSprintData(developerRecord));
                developer.calculateWeightedAverageOfBurnedStoryPointsOverSeveralSprints();

                developersList.add(developer);
            }

        }

        return developersList;
    }

    /*
        List of Sprints, each of them contains list of tasks
     */
    private List<Sprint> createSprintData(Element developerRecord) {
        List<Sprint> developerSprints = new ArrayList<Sprint>();
        Node sprintNode = null;
        Element sprintRecord = null;
        Sprint sprint = null;

        NodeList sprintsNodes = developerRecord.getElementsByTagName("sprint");
        for (int index = 0; index < sprintsNodes.getLength(); index++) {
            sprintNode = sprintsNodes.item(index);

            if (sprintNode.getNodeType() == Node.ELEMENT_NODE) {
                sprintRecord = (Element) sprintNode;

                sprint = new Sprint();
                sprint.setSprintWeekNumber(Integer.valueOf(sprintRecord.getAttribute("week")));
                sprint.setSprintTasks(createTaskData(sprintRecord));

                developerSprints.add(sprint);
            }

        }

        return developerSprints;
    }

    private List<Task> createTaskData(Element sprintRecord) {
        List<Task> sprintTasks = new ArrayList<Task>();
        Node taskNode = null;
        Element taskRecord = null;
        Task task = null;

        NodeList tasksNodes = sprintRecord.getElementsByTagName("task");
        for (int index = 0; index < tasksNodes.getLength(); index++) {
            taskNode = tasksNodes.item(index);

            if (taskNode.getNodeType() == Node.ELEMENT_NODE) {
                taskRecord = (Element) taskNode;
                task = new Task();
                task.setTaskName(taskRecord.getElementsByTagName("name").item(0).getTextContent());
                task.setBurnedPoints(Long.valueOf(taskRecord.getElementsByTagName("burned_points").item(0).getTextContent()));
                sprintTasks.add(task);
            }
        }

        return sprintTasks;
    }
}
