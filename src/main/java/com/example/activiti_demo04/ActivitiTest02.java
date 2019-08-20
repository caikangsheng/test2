package com.example.activiti_demo04;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @author caikangsheng
 * @date 2019/7/8 15:53
 */
public class ActivitiTest02 {
    /**
     * 获得核心对象--流程引擎
     */

    ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
    /**
     * 流程定义部署
     */
    @Test
    public void deploy(){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment().addClasspathResource("activiti/leave.bpmn")
                .addClasspathResource("activiti/leave.png")
                .deploy();
        System.out.println("部署id："+deploy.getId());
        System.out.println("部署名称："+deploy.getName());
    }

    /**
     * 启动流程定义
     */
    @Test
    public void processStart(){
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leaveProcess");
        System.out.println("流程实例Id："+processInstance.getId());
        System.out.println("流程定义Id："+processInstance.getProcessDefinitionId());
    }

    /**
     * 模拟设置和获取流程变量的场景
     */
    public void setVariableScene(){
        //与流程实例,执行对象(正在执行)相关的流程变量
        RuntimeService runtimeService = processEngine.getRuntimeService();
//        runtimeService.setVariable(executionId,variable,value);//表示使用执行对象ID,和流程变量的名称,设置流程变量的值(一次只能设置一个值)
//        runtimeService.setVariables(executionId,variables);//表示使用执行对象ID,和map集合设置流程变量
        //与任务(正在执行)相关的流程变量
        TaskService taskService = processEngine.getTaskService();
//        taskService.setVariable(taskId,variable,value);//表示使用任务对象ID,和流程变量的名称,设置流程变量的值(一次只能设置一个值)
//        taskService.setVariables(taskId,variables);//表示使用任务对象ID,和map集合设置流程变量
        //启动流程定义设置流程变量
//        runtimeService.startProcessInstanceByKey(key,variables)//表示启动流程定义是设置流程变量
        //获得流程变量
//        taskService.getVariable(taskId,variable)//表示通过任务对象id和流程变量名称获取流程变量的值
//        taskService.getVariables(taskId)//表示通过任务对象id获得流程变量的值放置到map集合中
//        taskService.getVariables(taskId,variables)//表示通过任务对象id获得流程变量...
    }

    /**
     * 设置流程变量(正在执行的任务)
     */
    @Test
    public void setVariable(){
        String taskId="32502";
        TaskService taskService = processEngine.getTaskService();
        Person person = new Person();
        person.setId("001");
        person.setUsername("jack");
        person.setPassword("12345");
//        taskService.setVariable(taskId,"请假原因","生病了");
//        taskService.setVariable(taskId,"请假天数",10);
//        taskService.setVariableLocal(taskId,"请假日期",new Date());
//        taskService.setVariableLocal(taskId,"请假人","张三");
        taskService.setVariable(taskId,"人员信息",person);
        System.out.println("设置流程变量完成!");
    }

    /**
     * 获得流程变量
     */
    @Test
    public void getVariable(){
        String taskId="32502";
        TaskService taskService = processEngine.getTaskService();
//        Date variable = (Date) taskService.getVariable(taskId, "请假天数");
        Person person = (Person)taskService.getVariable(taskId, "人员信息");
//        System.out.println("请假天数:"+variable);
        System.out.println("人员信息:"+person.getUsername());
    }

    /**
     * 查询历史流程变量
     */
    @Test
    public void historyVariable(){
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricVariableInstance> list = historyService.createHistoricVariableInstanceQuery()//创建一个历史的流程变量查询对象
                .variableName("请假原因").list();
        for (HistoricVariableInstance hvi : list) {
            System.out.println(hvi.getId()+" "+hvi.getProcessInstanceId()+" "+hvi.getVariableName()+" "+hvi.getVariableTypeName());
        }
//        historyService.createHistoricTaskInstanceQuery().processInstanceId()
    }
    /**
     * 查询个人任务
     */
    @Test
    public void query(){
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery().taskAssignee("lisi").list();
        for (Task task : list) {
            System.out.println("任务id:"+task.getId());
            System.out.println("任务名称:"+task.getName());
            System.out.println("流程定义id:"+task.getProcessDefinitionId());
            System.out.println("创建时间:"+task.getCreateTime());
        }

    }

    /**
     *完成任务
     */
    @Test
    public void complete(){
        String taskId="27504";
        TaskService taskService = processEngine.getTaskService();
        taskService.complete(taskId);
        System.out.println("完成任务");
    }
}
