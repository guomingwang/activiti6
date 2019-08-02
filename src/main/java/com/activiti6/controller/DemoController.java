package com.activiti6.controller;

import com.activiti6.bean.Assignee;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class DemoController {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private IdentityService identityService;

    @RequestMapping("")
    public List<String> taskList() {
        List<Task> tasks = taskService.createTaskQuery().orderByTaskCreateTime().desc().list();
        if (tasks.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        List<String> taskNames = new ArrayList<>();
        for (Task task :
                tasks) {
            taskNames.add(task.getAssignee() + ": " + task.getName() + task.getId());
        }
        return taskNames;
    }

    @RequestMapping("event/start/{userId}")
    public List<String> eventStart(@PathVariable("userId") String userId) {
        identityService.setAuthenticatedUserId(userId);
        Map<String, Object> assigneeMap = new HashMap<>();
        Assignee assignee = new Assignee();
        assignee.setUserId(userId);
        assigneeMap.put("assignee", assignee);
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("vacation", assigneeMap);
        return taskList();
    }

    @RequestMapping("task/complete/{userId}/{taskId}/{bool}")
    public List<String> taskComplete(@PathVariable("userId") String userid, @PathVariable("taskId") String taskId,
                                     @PathVariable("bool") String bool) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String assignee = task.getAssignee();
        if (userid.equals(assignee)) {
            Map<String, Object> map = new HashMap<>();
            map.put("bool", bool);
            taskService.complete(taskId, map);
        }
        return taskList();
    }
}
