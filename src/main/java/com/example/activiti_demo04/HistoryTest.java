package com.example.activiti_demo04;


import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricTaskInstance;
import org.junit.Test;

import java.util.List;

/**
 * @author caikangsheng
 * @date 2019/7/9 16:07
 */
public class HistoryTest {
   ProcessEngine processEngine= ProcessEngines.getDefaultProcessEngine();
    /**
     * 查询历史
     */
    @Test
    public void queryHistory(){

        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().processInstanceId("27501").list();
        for (HistoricTaskInstance historicTaskInstance : list) {
            System.out.println(historicTaskInstance.getAssignee()+":"+historicTaskInstance.getName());
        }

    }
}
