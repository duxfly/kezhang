package com.lvzheng.service.sealcarve.jebe;

import com.lvzheng.service.sealcarve.jebe.mapper.customer.CustomerMapper;
import com.lvzheng.service.sealcarve.jebe.mapper.enterprise.EnterpriseMapper;
import com.lvzheng.service.sealcarve.jebe.mapper.lvzheng.EmployerMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class JEBEService {

    @Autowired
    private EmployerMapper employerMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private CustomerMapper customerMapper;


    public Employer getEmployerByDingTalkCode(String code) {
        return employerMapper.getEmployerByDingTalkCode(code);
    }

    public Employer getEmployerByID(long id) {
        return employerMapper.getEmployerByID(id);
    }

    /**
     * 跟据员工集合获得员工map, key:员工ID，value:员工实体
     * @param ids 员工ID
     * @return
     */
    public Map<Long, Employer> getEmployers(List<Long> ids) {
        List<Employer> list = employerMapper.getEmployerByIds(ids);
        Map<Long, Employer> map = new HashMap<>();

        list.forEach(item -> map.put(item.getEmpID(), item));
        return map;
    }

    /**
     * 跟据企业ID，获取企业实体
     * @param enterpriseID 企业ID
     * @return Enterprise
     */
    public Enterprise getEnterpriseByID(long enterpriseID) {
        List<Long> ids = new ArrayList<>();
        ids.add(enterpriseID);

        List<Enterprise> list = enterpriseMapper.getEnterpriseByIds(ids);
        if (list == null || list.size() == 0) {
            return null;
        }

        return list.get(0);
    }

    /**
     * 跟据customerName(t_customer) 获取企业实体
     * @param customerName t_customer表中的跟据customerName
     * @return Enterprise List
     */
    public List<Enterprise> getEnterpriseByCustomerName(String customerName) {
        List<Long> ids = customerMapper.getEnterpriseIdsByCustomerName(customerName);
        return enterpriseMapper.getEnterpriseByIds(ids);
    }

    /**
     * 根据企业ID，获取企业map  key:企业ID  value:企业实体
     * @param ids 企业ID List
     * @return 企业map
     */
    public Map<Long, Enterprise> getEnterpriseByIds(List<Long> ids) {
        List<Enterprise> list = enterpriseMapper.getEnterpriseByIds(ids);
        Map<Long, Enterprise> map = new HashMap<>();

        list.forEach(item -> map.put(item.getEnterpriseID(), item));
        return map;
    }

    /**
     * 更新企业信息:
     * 1. 更新 lv_enterprise 相关字段
     * 2. 更新 lv_enterprise_ext 中的：统一社会识别码，如果存在则更新，不存在则插入
     * 3. 更新 lv_enterprise_person 中的：企业法人信息，如果存在则更新，不存在则插入(lv_enterprise_person, lv_enterprise_role_relation)
     * @param enterprise 企业实体
     * @return 更新成功记录数
     */
    public int updateEnterprise(Enterprise enterprise) {
        Enterprise tmp = getEnterpriseByID(enterprise.getEnterpriseID());
        if (tmp == null) {
            return 0;
        }

        tmp.setName(enterprise.getName());
        tmp.setTransactorType(enterprise.getTransactorType());
        tmp.setTransactorName(enterprise.getTransactorName());
        tmp.setTransactorPhone(enterprise.getTransactorPhone());
        tmp.setModifyUserID(enterprise.getModifyUserID());
        tmp.setModifyUserType("");
        tmp.setModifyDate(new Date());

        // update lv_enterprise
        int count = enterpriseMapper.updateEnterpriseBase(tmp);
        if (count <= 0) {
            return count;
        }

        // update lv_enterprise_ext
        if (StringUtils.isNotEmpty(enterprise.getUnifiedSocialCreditCode())) {
            tmp.setUnifiedSocialCreditCode(enterprise.getUnifiedSocialCreditCode());
            if (tmp.getExtID() > 0) {
                enterpriseMapper.updateEnterpriseEXT(tmp);
            } else {
                enterpriseMapper.insertEnterpriseEXT(tmp);
            }
        }

        // update lv_enterprise_person
        if (StringUtils.isNotEmpty(enterprise.getLegalPersonName())
                || StringUtils.isNotEmpty(enterprise.getLegalPersonTel())
                || StringUtils.isNotEmpty(enterprise.getLegalPersonIDNum())
                || StringUtils.isNotEmpty(enterprise.getLegalPersonResidenceAddress())) {
            tmp.setLegalPersonName(enterprise.getLegalPersonName());
            tmp.setLegalPersonTel(enterprise.getLegalPersonTel());
            tmp.setLegalPersonIDNum(enterprise.getLegalPersonIDNum());
            tmp.setLegalPersonResidenceAddress(enterprise.getLegalPersonResidenceAddress());
            tmp.setLegalPersonIDType(enterprise.getLegalPersonIDType());

            if (tmp.getLegalPersonID() > 0) {
                enterpriseMapper.updateEnterprisePerson(tmp);
            } else {
                enterpriseMapper.insertEnterprisePerson(tmp);
                enterpriseMapper.insertEnterpriseRoleRelation(tmp);
            }
        }

        return count;
    }
}
