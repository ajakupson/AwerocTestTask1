package com.averoc.task.services;

import com.averoc.task.models.Developer;
import com.averoc.task.models.Sprint;
import com.averoc.task.models.Task;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by de1mos on 8.08.16.
 */
@Service
public class DevelopersService {

    /**
     * Data structure - Developer -> Sprint(s) -> Task(s)
     * @param xmlData
     * @return
     */
    public List<Developer> createDevelopersListData(Document xmlData) {
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
     *   List of Sprints, each of them contains list of tasks
     */
    public List<Sprint> createSprintData(Element developerRecord) {
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

    public List<Task> createTaskData(Element sprintRecord) {
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
