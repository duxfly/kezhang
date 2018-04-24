package com.lvzheng.service.sealcarve.jebe;

import com.lvzheng.service.sealcarve.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class JEBEServiceTest {

    @Autowired
    private JEBEService jebeService;


    @Test
    public void getEmployerByID() throws Exception {
        Employer emp = jebeService.getEmployerByID(50934608493569L);

        Assert.assertEquals(50934608493569L, emp.getEmpID());
        Assert.assertNotNull(emp.getEmpName());
    }

    @Test
    public void getEmployerByDingTalkCode() throws Exception {
        Employer emp = jebeService.getEmployerByDingTalkCode("021126353523527112");

        Assert.assertEquals(50934608493569L, emp.getEmpID());
        Assert.assertNotNull(emp.getEmpName());
        Assert.assertNotNull(emp.getDingTalkCode());
    }

}