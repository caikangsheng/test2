package com.example.activiti_demo04;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

/**
 * @author caikangsheng
 * @date 2019/7/8 14:20
 */
public class ActivitiTest {

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
    }
    /**
     * 查询流程定义
     */
    @Test
    public void query(){


    }
    /**
     * 删除流程定义
     */
    @Test
    public void delete(){

    }
    /**
     * 查询个人任务
     */
    @Test
    public void queryTask(){

    }
    /**
     * 完成个人任务
     */
    @Test
    public void complete(){

    }
}
