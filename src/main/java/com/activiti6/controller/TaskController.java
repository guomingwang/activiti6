package com.activiti6.controller;

import com.activiti6.bean.Person;
import com.activiti6.service.UserTaskService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wgm
 * @since 2019/7/28 2:34
 */
@RestController
public class TaskController {

    @Autowired
    private IdentityService identityService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @RequestMapping("MyUserTask")
    public void MyUserTask() {
        Group groupA = identityService.createGroupQuery().groupId("GroupA").singleResult();
        if (groupA == null) {
            identityService.saveGroup(identityService.newGroup("GroupA"));
            identityService.saveUser(identityService.newUser("userD"));
            identityService.saveUser(identityService.newUser("userE"));
            identityService.createMembership("userD", "GroupA");
            identityService.createMembership("userE", "GroupA");
        }
        groupA = identityService.createGroupQuery().groupId("GroupB").singleResult();
        if (groupA == null) {
            identityService.saveGroup(identityService.newGroup("GroupB"));
            identityService.saveUser(identityService.newUser("userF"));
            identityService.createMembership("userF", "GroupB");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("userTaskService", new UserTaskService());
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("MyUserTask", map);
        /*List<Task> tasks = taskService.createTaskQuery().taskUnassigned().list();
        for (Task task : tasks) {
            taskService.claim(task.getId(), "userA");
            taskService.delegateTask(task.getId(), "userG");
            taskService.delegateTask(task.getId(), "userA");
        }*/
        List<Task> userA = taskService.createTaskQuery().taskCandidateOrAssigned("userA").list();
        System.out.println(userA);
        List<Task> userB = taskService.createTaskQuery().taskCandidateOrAssigned("userB").list();
        System.out.println(userB);
        List<Task> userC = taskService.createTaskQuery().taskCandidateOrAssigned("userC").list();
        System.out.println(userC);
        List<Task> userD = taskService.createTaskQuery().taskCandidateOrAssigned("userD").list();
        System.out.println(userD);
        List<Task> userE = taskService.createTaskQuery().taskCandidateOrAssigned("userE").list();
        System.out.println(userE);
        List<Task> userF = taskService.createTaskQuery().taskCandidateOrAssigned("userF").list();
        System.out.println(userF);
        List<Task> userG = taskService.createTaskQuery().taskCandidateOrAssigned("userG").list();
        System.out.println(userG);
        List<Task> tasks = taskService.createTaskQuery().taskUnassigned().list();
    }

    @RequestMapping("MyReceiveTask")
    public void MyReceiveTask() {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("MyReceiveTask");
        List<Execution> executions = runtimeService.createExecutionQuery().processInstanceId(instance.getId()).onlyChildExecutions().list();
        for (Execution e : executions) {
            runtimeService.trigger(e.getId());
        }
    }

    @RequestMapping("MyCallActivitySubprocess")
    public void MyCallActivitySubprocess() {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("MyCallActivitySubprocess");
    }

    @RequestMapping("MyEventGateWay")
    public int MyEventGateWay() {
        List<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("b");
        strings.add("c");
        Map<String, Object> map = new HashMap<>();
        map.put("myData", strings);
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("MyEventGateWay", map);
        List<Execution> executions = runtimeService.createExecutionQuery().activityId("_4").list();
        for (Execution execution : executions) {
            runtimeService.messageEventReceived("msgA", execution.getId());
        }
        List<ProcessInstance> instances = runtimeService.createProcessInstanceQuery().list();
        return instances.size();
    }

    @RequestMapping("MyManualTask")
    public void MyManualTask() {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("MyManualTask");
    }

    @RequestMapping("MyMailTask")
    public void MyMailTask() {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("MyMailTask");
    }

    @RequestMapping("Drools")
    public void Drools() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession("ksession-rules");

        // 定义一个事实对象
        Person person = new Person();
        person.setName("Crazyit");
        // 向StatefulKnowledgeSession中加入事实
        kSession.insert(person);
        // 匹配规则
        kSession.fireAllRules();
        // 关闭当前session的资源
        kSession.dispose();
    }
}
