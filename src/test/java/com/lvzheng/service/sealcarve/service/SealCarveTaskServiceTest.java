package com.lvzheng.service.sealcarve.service;

import com.lvzheng.service.sealcarve.Application;
import com.lvzheng.service.sealcarve.enumeration.SealCarveTaskStatusEnum;
import com.lvzheng.service.sealcarve.vo.input.SealCarveTaskQueryVO;
import com.lvzheng.service.sealcarve.vo.input.SealCarveTaskRemarkVO;
import com.lvzheng.service.sealcarve.vo.output.SealCarveTaskDetailResult;
import com.lvzheng.service.sealcarve.vo.output.SealCarveTaskResult;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class SealCarveTaskServiceTest {

    @Autowired
    private SealCarveTaskService sealCarveTaskService;

    @Test
    public void addSealCarveTask() throws Exception {
//        long[] ids = new long[] {33217967101698L,33279604659969L,33280389334529L,33281315863809L,33304735014401L,33304737232897L,33304737525761L,33304738477057L,33304738659073L,33304739347969L,33304742624513L,33304746050305L,33304748177921L,33304751026945L,33304752260097L,33304752422145L,33304752467457L,33304752550913L,33304752982017L,33304753180673L,33304754547457L,33304755002625L,33304756246529L,33304757014785L,33304757113601L,33304757121025L,33304757281537L,33304757487361L,33304757521153L,33304758678017L,33304760268033L,33304761714177L,33304762502145L,33365545892353L,33371838741505L,33412854161153L,33416042031105L,33518615030017L,33523827597057L,33543099575553L,33553081518337L,33564682818817L,33585084247041L,33588024763905L,33588618087681L,33593165234433L,33607253277185L,33608721101313L,33674105762561L,33676299155969L};
//
//        for (long eid : ids) {
//            SealCarveTaskCreateVO vo = new SealCarveTaskCreateVO();
//            vo.setEnterpriseID(eid);
//            vo.setMasterTaskID(123L);
//            vo.setOrderID(456L);
//
//            long id = sealCarveTaskService.addSealCarveTask(vo);
//            Assert.assertEquals(true, id > 0);
//        }
    }

    @Test
    public void getSealCarveTask() throws ParseException {
        SealCarveTaskQueryVO query = new SealCarveTaskQueryVO();
        query.setPageIndex(1);
        query.setPageSize(30);
        query.setStatus(-1);
        query.setEnterpriseName("");
        query.setAgentName("");
        query.setEndDate(DateUtils.parseDate("2017-12-20", "yyyy-MM-dd"));
        SealCarveTaskResult result = sealCarveTaskService.getSealCarveTask(0, query);

        result.getItems().forEach(item -> {
            System.out.println(item.getAgentName());
        });
    }

    @Test
    public void getTaskDetail() {
        SealCarveTaskDetailResult result = sealCarveTaskService.getTaskDetail(0, 1);

    }

    @Test
    public void addTaskRemark() {
        SealCarveTaskRemarkVO vo = new SealCarveTaskRemarkVO();
        vo.setTaskID(1);
        vo.setRemark("北京天气很好呀");

        long remarkID = sealCarveTaskService.addTaskRemark(0, vo);
        Assert.assertEquals(true, remarkID>0);
    }

    @Test
    public void updateTaskStatus() {
        long[] taskIds = new long[]{1};
        int count = sealCarveTaskService.updateTaskStatus(0, taskIds, SealCarveTaskStatusEnum.Engraved.getStatus());
        Assert.assertEquals(1, count);
    }
}