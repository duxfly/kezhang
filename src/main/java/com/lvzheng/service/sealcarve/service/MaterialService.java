package com.lvzheng.service.sealcarve.service;

import com.lvzheng.service.sealcarve.domain.Material;
import com.lvzheng.service.sealcarve.domain.MaterialConfig;
import com.lvzheng.service.sealcarve.domain.MaterialFlow;
import com.lvzheng.service.sealcarve.jebe.Employer;
import com.lvzheng.service.sealcarve.jebe.Enterprise;
import com.lvzheng.service.sealcarve.jebe.JEBEService;
import com.lvzheng.service.sealcarve.mapper.MaterialMapper;
import com.lvzheng.service.sealcarve.util.DingTalkHelper;
import com.lvzheng.service.sealcarve.vo.input.MaterialClaimVO;
import com.lvzheng.service.sealcarve.vo.input.MaterialQueryVO;
import com.lvzheng.service.sealcarve.vo.output.MyMaterialResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MaterialService {

    private static final Logger logger = LoggerFactory.getLogger(MaterialService.class);

    @Autowired
    private MaterialMapper materialMapper;

    @Autowired
    private JEBEService jebeService;


    /**
     * 获得所有材料项list
     * @return
     */
    public List<Material> getAllMaterialList() {
        return materialMapper.getAllMaterial();
    }

    /**
     * 获得所有材料项map
     * @return
     */
    public Map<Integer, Material> getAllMaterialMap() {
        List<Material> list =  materialMapper.getAllMaterial();
        return list.stream().collect(Collectors.toMap(Material::getId, entity -> entity));
    }

    /**
     * 分组获得所有材料项
     * @param confIds 配置ID
     * @return
     */
    public Map<String, List<Material>> getMaterialByGroup(int... confIds) {
        List<MaterialConfig> configList = materialMapper.getMaterialConfigByIds(confIds);
        if (configList == null || configList.size() == 0) {
            return null;
        }

        Map<Integer, Material> materialMap = getAllMaterialMap();

        Map<String, List<Material>> map = new HashMap<>();
        configList.forEach(item -> {
            String ids = item.getDependMaterials();
            String[] idAry = ids.split(",");

            List<Material> list = map.get(item.getName());
            if (list == null) {
                list = new ArrayList<>();
            }

            for (String id : idAry) {
                Material material = materialMap.get(Integer.parseInt(id));
                list.add(material);
            }
            map.put(item.getName(), list);
        });

        return map;
    }

    /**
     * 获得我的材料
     * @param query MaterialQueryVO
     * @return
     */
    public MyMaterialResult getMyMaterialsFlows(MaterialQueryVO query) throws Exception {
        System.out.println("getDingTalkCode:" + query.getDingTalkCode());
        String userID = DingTalkHelper.getUserID(query.getDingTalkCode());
        Employer emp = jebeService.getEmployerByDingTalkCode(userID);
        if (emp == null) {
            logger.error("employer is null dingTalkCode:" + query.getDingTalkCode());
            return null;
        }
        long empID = emp.getEmpID();
        System.out.println("empID:" + empID);

        //分页获得记录
        int offset = (query.getPageIndex() - 1) * query.getPageSize();
        String enterpriseName = StringUtils.isEmpty(query.getEnterpriseName()) ? null : "%" + query.getEnterpriseName() + "%";
        System.out.println(enterpriseName);
        List<MaterialFlow> materialFlowList = materialMapper.getMaterialFlow(empID, enterpriseName, offset, query.getPageSize());

        Map<Integer, Material> materialMap = getAllMaterialMap();
        List<MyMaterialResult.MaterialFlowItem> resultItems = new ArrayList<>();
        materialFlowList.forEach(item -> {
            String ids = item.getMaterialIds();
            List<Material> materials = new ArrayList<>();
            if (!StringUtils.isEmpty(ids)) {
                String[] ary = ids.split(",");
                for (int i = 0; i < ary.length; i++) {
                    Material m = materialMap.get(Integer.parseInt(ary[i]));
                    if (m != null) {
                        materials.add(m);
                    }
                }
            }
            resultItems.add(new MyMaterialResult.MaterialFlowItem(item, materials));
        });

        //获得总记录数
        int totalCount = materialMapper.getMaterialFlowCount(empID, enterpriseName);

        MyMaterialResult result = new MyMaterialResult();
        result.setItems(resultItems);
        result.setTotalCount(totalCount);

        return result;
    }

    /**
     * 获得指定公司的所有材料被认领记录
     * @param enterpriseID 企业ID
     * @return
     */
    public List<MaterialFlow> getMaterialFlowByEnterpriseID(long enterpriseID) {
        return materialMapper.getMaterialFlowByEnterpriseID(enterpriseID);
    }

    /**
     * 认领材料
     * @param vo MaterialClaimVO
     * @return
     */
    public long claim(MaterialClaimVO vo) throws Exception {
        String userID = DingTalkHelper.getUserID(vo.getDingTalkCode());
        Employer emp = jebeService.getEmployerByDingTalkCode(userID);
        System.out.println("dingTalkCode:" + vo.getDingTalkCode() + " userID:" + userID);
        if (emp == null) {
            return 0;
        }
        long empID = emp.getEmpID();

        Enterprise ent = jebeService.getEnterpriseByID(vo.getEnterpriseID());
        String enterpriseName = ent.getName();

        MaterialFlow flow = new MaterialFlow();
        flow.setClaimTime(new Date());
        flow.setEnterpriseID(vo.getEnterpriseID());
        flow.setEnterpriseName(enterpriseName);
        flow.setEmpID(empID);
        flow.setMaterialIds(vo.getStrMaterialIds());
        materialMapper.addMaterialFlow(flow);

        return flow.getId();
    }
}

