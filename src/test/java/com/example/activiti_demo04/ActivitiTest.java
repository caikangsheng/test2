package com.example.activiti_demo04;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author caikangsheng
 * @date 2019/7/8 14:15
 */
public class ActivitiTest {


    /**
     * 获得核心对象--流程引擎
     */

    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    /**
     * 流程定义部署
     */
    @Test
    public void deploy() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment().addClasspathResource("activiti/leave.bpmn")
                .addClasspathResource("activiti/leave.png")
                .deploy();
        System.out.println("部署id：" + deploy.getId());
        System.out.println("部署名称：" + deploy.getName());
    }

    /**
     * 启动流程定义
     */
    @Test
    public void processStart() {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leaveProcess");
        System.out.println("流程实例id：" + processInstance.getId());
        System.out.println("流程定义id：" + processInstance.getProcessDefinitionId());
    }

    /**
     * 查询流程定义
     */
    @Test
    public void query() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionVersion().asc().list();
        for (ProcessDefinition processDefinition : list) {
            System.out.println("流程定义id：" + processDefinition.getId());
            System.out.println("流程定义名称：" + processDefinition.getName());
        }

    }

    /**
     * 删除流程定义
     */
    @Test
    public void delete() {
        //1.获得流程定义的key  leaveProcess
        String processKey = "leaveProcess";
        //2.先用key获得流程定义，查询出所有的版本
        RepositoryService repositoryService = processEngine.getRepositoryService();
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processKey).list();
        System.out.println("流程定义的数量："+list.size());
        //3.遍历，获得每个流程定义部署的id
        for (ProcessDefinition processDefinition : list) {
            String deploymentId = processDefinition.getDeploymentId();
            //4.删除部署
            processEngine.getRepositoryService().deleteDeployment(deploymentId,true);
        }
        System.out.println("删除完成！");

    }

    /**
     * 查询个人任务
     */
    @Test
    public void queryTask() {
        String assignee = "lisi";
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery().taskAssignee(assignee).list();
        for (Task task : list) {
            System.out.println("任务id：" + task.getId());
            System.out.println("任务名称：" + task.getName());
            System.out.println("处理人：" + task.getAssignee());
            System.out.println("任务的创建时间：" + task.getCreateTime());
            System.out.println("流程实例id:" + task.getProcessInstanceId());
            System.out.println("执行对象id：" + task.getExecutionId());
            System.out.println("流程定义id：" + task.getProcessDefinitionId());
        }

    }

    /**
     * 完成个人任务
     */
    @Test
    public void complete() {
        TaskService taskService = processEngine.getTaskService();
//        taskService.complete("2504");
//        taskService.complete("5002");
        taskService.complete("7502");
        System.out.println("任务完成");

    }
}
