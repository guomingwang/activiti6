package com.activiti6.controller;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wgm
 * @since 2019/7/27 4:53
 */
@RestController
public class EvenConroller {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @RequestMapping("MyNoneStartEvent")
    public int MyNoneStartEvent() {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("MyNoneStartEvent");
        List<ProcessInstance> instances = runtimeService.createProcessInstanceQuery().list();
        return instances.size();
    }

    @RequestMapping("MyTimeStartEvent")
    public int MyTimeStartEvent() {
        List<ProcessInstance> instances = runtimeService.createProcessInstanceQuery().list();
        return instances.size();
    }

    @RequestMapping("MyMessageStartEvent")
    public int MyMessageStartEvent() {
        ProcessInstance instance = runtimeService.startProcessInstanceByMessage("msgName");
        List<ProcessInstance> instances = runtimeService.createProcessInstanceQuery().list();
        return instances.size();
    }

    @RequestMapping("MyErrorStartEvent")
    public int MyErrorStartEvent() {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("MyErrorStartEvent");
        List<ProcessInstance> instances = runtimeService.createProcessInstanceQuery().list();
        return instances.size();
    }

    @RequestMapping("MyNoneEndEvent")
    public int MyNoneEndEvent() {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("MyNoneEndEvent");
        List<ProcessInstance> instances = runtimeService.createProcessInstanceQuery().list();
        return instances.size();
    }

    @RequestMapping("MyMessageCachingEvent")
    public int MyMessageCachingEvent() {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("MyMessageCachingEvent");
        List<ProcessInstance> instances = runtimeService.createProcessInstanceQuery().list();
        return instances.size();
    }

    @RequestMapping("MyMessageCachingEvent2")
    public int MyMessageCachingEvent2() {
        List<Execution> executions = runtimeService.createExecutionQuery().activityId("_4").list();
        for (Execution execution : executions) {
            runtimeService.messageEventReceived("msgName", execution.getId());
        }
        List<ProcessInstance> instances = runtimeService.createProcessInstanceQuery().list();
        return instances.size();
    }

    @RequestMapping("MyTimerCachingEvent")
    public int MyTimerCachingEvent() {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("MyTimerCachingEvent");
        List<ProcessInstance> instances = runtimeService.createProcessInstanceQuery().list();
        return instances.size();
    }

    @RequestMapping("MyErrorEndEvent")
    public int MyErrorEndEvent() {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("MyErrorEndEvent");
        List<ProcessInstance> instances = runtimeService.createProcessInstanceQuery().list();
        return instances.size();
    }

    @RequestMapping("MyErrorBoundaryEvent")
    public int MyErrorBoundaryEvent() {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("MyErrorBoundaryEvent");
        List<ProcessInstance> instances = runtimeService.createProcessInstanceQuery().list();
        return instances.size();
    }

    @RequestMapping("MySignalEvent")
    public int MySignalEvent() {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("MySignalEvent");
        List<ProcessInstance> instances = runtimeService.createProcessInstanceQuery().list();
        return instances.size();
    }

    @RequestMapping("MySignalEvent2")
    public int MySignalEvent2() {
        List<Task> tasks = taskService.createTaskQuery().taskAssignee("zs").list();
        for (Task task : tasks) {
            taskService.complete(task.getId());
        }
        List<ProcessInstance> instances = runtimeService.createProcessInstanceQuery().list();
        return instances.size();
    }

    @RequestMapping("MySignalBoundaryEvent")
    public int MySignalBoundaryEvent() {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("MySignalBoundaryEvent");
        List<ProcessInstance> instances = runtimeService.createProcessInstanceQuery().list();
        return instances.size();
    }

    @RequestMapping("MyTerminateEndEvent")
    public int MyTerminateEndEvent() {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("MyTerminateEndEvent");
        List<ProcessInstance> instances = runtimeService.createProcessInstanceQuery().list();
        return instances.size();
    }

    @RequestMapping("MyTerminateEndEvent2")
    public int MyTerminateEndEvent2() {
        List<Task> tasks = taskService.createTaskQuery().taskAssignee("user1").list();
        for (Task task : tasks) {
            taskService.complete(task.getId());
        }
        List<ProcessInstance> instances = runtimeService.createProcessInstanceQuery().list();
        return instances.size();
    }

    @RequestMapping("MyMessageEvent")
    public int MyMessageEvent() {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("MyMessageEvent");
        List<ProcessInstance> instances = runtimeService.createProcessInstanceQuery().list();
        return instances.size();
    }
}
