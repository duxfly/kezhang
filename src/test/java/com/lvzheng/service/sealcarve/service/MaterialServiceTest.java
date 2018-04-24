package com.lvzheng.service.sealcarve.service;

import com.lvzheng.service.sealcarve.Application;
import com.lvzheng.service.sealcarve.domain.Material;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class MaterialServiceTest {

    @Autowired
    private MaterialService materialService;


    @Test
    public void getAllMaterial() throws Exception {
        List<Material> list = materialService.getAllMaterialList();
        list.forEach(item -> System.out.println("id:" + item.getId() + " name:" + item.getName()));

        Assert.assertEquals(true, list.size() > 0);
    }

    @Test
    public void getMaterialByGroup() {
        Map<String, List<Material>> map = materialService.getMaterialByGroup(1, 2, 3);
        map.forEach((k, v) -> {
            System.out.println("group:" + k);
            v.forEach(item -> {
                System.out.println(item.getName());
            });
        });

        Assert.assertEquals(true, map.size() == 3);
    }

    @Test
    public void getMaterialFlow() {
//        MaterialQueryVO query = new MaterialQueryVO();
//        query.setPageIndex(1);
//        query.setPageSize(30);
//        query.setDingTalkCode("021126353523527112");
//
//        MyMaterialResult result = materialService.getMyMaterialsFlows(query);
//        result.getItems().forEach(item -> System.out.println(item.getMaterialFlow().getEnterpriseName()));
//        Assert.assertEquals(true, result.getItems().size()>0);
    }

    @Test
    public void claim() throws Exception {
//        MaterialClaimVO vo = new MaterialClaimVO();
//        vo.setEnterpriseID(33217967101698L);
//        vo.setDingTalkCode("");
//        vo.setMaterialIds(new int[]{4,5,6});
//        long id = materialService.claim(vo);

//        Assert.assertEquals(true, id > 0);
    }

}