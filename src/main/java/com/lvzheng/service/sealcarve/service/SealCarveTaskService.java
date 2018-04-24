package com.lvzheng.service.sealcarve.service;

import com.lvzheng.service.sealcarve.domain.Material;
import com.lvzheng.service.sealcarve.domain.MaterialFlow;
import com.lvzheng.service.sealcarve.domain.SealCarveTask;
import com.lvzheng.service.sealcarve.domain.SealCarveTaskRemark;
import com.lvzheng.service.sealcarve.domain.SealCarveTaskStatus;
import com.lvzheng.service.sealcarve.enumeration.SealCarveTaskStatusEnum;
import com.lvzheng.service.sealcarve.jebe.Employer;
import com.lvzheng.service.sealcarve.jebe.Enterprise;
import com.lvzheng.service.sealcarve.jebe.JEBEService;
import com.lvzheng.service.sealcarve.mapper.SealCarveTaskMapper;
import com.lvzheng.service.sealcarve.vo.input.EnterpriseInfoVO;
import com.lvzheng.service.sealcarve.vo.input.SealCarveTaskCreateVO;
import com.lvzheng.service.sealcarve.vo.input.SealCarveTaskQueryVO;
import com.lvzheng.service.sealcarve.vo.input.SealCarveTaskRemarkVO;
import com.lvzheng.service.sealcarve.vo.output.SealCarveTaskDetailResult;
import com.lvzheng.service.sealcarve.vo.output.SealCarveTaskResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SealCarveTaskService {

    @Autowired
    private SealCarveTaskMapper sealCarveTaskMapper;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private JEBEService jebeService;


    /**
     * 添加刻章任务
     * @param vo SealCarveTaskCreateVO
     * @return
     */
    public long addSealCarveTask(SealCarveTaskCreateVO vo) {
        Enterprise enterprise = jebeService.getEnterpriseByID(vo.getEnterpriseID());
        if (enterprise == null) {
            return 0;
        }

        SealCarveTask task = new SealCarveTask();
        task.setEnterpriseName(enterprise.getName());
        task.setAgentName(enterprise.getTransactorName());
        task.setEnterpriseID(vo.getEnterpriseID());
        task.setMasterTaskID(vo.getMasterTaskID());
        task.setOrderID(vo.getOrderID());
        task.setStatus(SealCarveTaskStatusEnum.Init.getStatus());
        task.setAddTime(new Date());
        task.setAgentName(enterprise.getTransactorName());

        sealCarveTaskMapper.addTask(task);
        return task.getId();
    }

    /**
     * 获取刻章任务列表
     * @param empID 员工ID
     * @param query SealCarveTaskQueryVO
     * @return
     */
    public SealCarveTaskResult getSealCarveTask(long empID, SealCarveTaskQueryVO query) {
        SealCarveTaskResult result = new SealCarveTaskResult();
        String enterpriseName = query.getEnterpriseName();
        if (!StringUtils.isEmpty(enterpriseName)) {
            enterpriseName = "%" + enterpriseName + "%";
        }
        String agentName = query.getAgentName();
        if (!StringUtils.isEmpty(agentName)) {
            agentName = "%" + agentName + "%";
        }
        int offset = (query.getPageIndex() - 1) * query.getPageSize();

        List<SealCarveTask> tasks = sealCarveTaskMapper.getTasks(query.getStatus(), query.getBeginDate(), query.getEndDate(), enterpriseName, agentName, offset, query.getPageSize());
        if (tasks == null || tasks.size() == 0) {
            return result;
        }

        List<Long> enterpriseIds = new ArrayList<>();
        tasks.forEach(item -> enterpriseIds.add(item.getEnterpriseID()));
        Map<Long, Enterprise> enterpriseMap = jebeService.getEnterpriseByIds(enterpriseIds);

        List<SealCarveTaskResult.SealCarveTaskItem> resultItemList = new ArrayList<>();
        tasks.forEach(item -> {
            SealCarveTaskResult.SealCarveTaskItem resultItem = new SealCarveTaskResult.SealCarveTaskItem();

            Enterprise enterprise = enterpriseMap.get(item.getEnterpriseID());
            if (enterprise != null) {
                resultItem.setEnterpriseName(enterprise.getName());
                resultItem.setAgentType(enterprise.getTransactorType() == 1 ? "客户经办人" : "小薇经办人");
                resultItem.setAgentName(enterprise.getTransactorName());
                resultItem.setAgentTel(enterprise.getTransactorPhone());

                //TODO: 通item.getProductID()获取产品名
                resultItem.setProductName("平纹章（套");
            }

            resultItem.setTaskID(item.getId());
            resultItem.setAddTime(item.getAddTime());
            resultItem.setAppointmentTime(item.getAppointmentTime());
            resultItem.setRemark(item.getRemark());
            resultItem.setStatus(SealCarveTaskStatusEnum.fromStatusValue(item.getStatus()).getDescription());

            resultItemList.add(resultItem);
        });

        int totalCount = sealCarveTaskMapper.getTaskCount(query.getStatus(), query.getBeginDate(), query.getEndDate(), enterpriseName, agentName);

        result.setItems(resultItemList);
        result.setButtons(getOPButtons(empID));
        result.setTotalCount(totalCount);
        return result;
    }

    /**
     * 获得任务详情
     * @param taskID 任务ID
     * @param enterpriseID 企业ID
     * @return
     */
    public SealCarveTaskDetailResult getTaskDetail(long taskID, long enterpriseID) {
        SealCarveTaskDetailResult result = new SealCarveTaskDetailResult();
        result.setTaskID(taskID);

        //公司信息
        Enterprise enterprise = jebeService.getEnterpriseByID(enterpriseID);
        if (enterprise != null) {
            EnterpriseInfoVO enterpriseInfoVO = new EnterpriseInfoVO();
            enterpriseInfoVO.setUnitSocietyCode(enterprise.getUnifiedSocialCreditCode());
            enterpriseInfoVO.setLegalPerson(enterprise.getLegalPersonName());
            enterpriseInfoVO.setTel(enterprise.getLegalPersonTel());
            enterpriseInfoVO.setCertificateNum(enterprise.getLegalPersonIDNum());
            enterpriseInfoVO.setCensusRegister(enterprise.getLegalPersonResidenceAddress());

            if (StringUtils.isNotEmpty(enterprise.getLegalPersonIDType())) {
                enterpriseInfoVO.setCertificateType(Integer.parseInt(enterprise.getLegalPersonIDType()));
            }
            result.setEnterpriseInfo(enterpriseInfoVO);
        }


        //材料认领记录
        Map<Integer, Material> materialMap = materialService.getAllMaterialMap();
        List<MaterialFlow> flowList = materialService.getMaterialFlowByEnterpriseID(enterpriseID);
        if (flowList != null && flowList.size() > 0) {
            List<Long> empIds = new ArrayList<>();
            flowList.forEach(item -> empIds.add(item.getEmpID()));
            Map<Long, Employer> employerMap = jebeService.getEmployers(empIds);

            List<SealCarveTaskDetailResult.MaterialLog> materialLogList = new ArrayList<>();

            for (int i = 0; i < flowList.size(); i++) {
                MaterialFlow mflow = flowList.get(i);
                Employer curEmployer = employerMap.get(mflow.getEmpID());
                SealCarveTaskDetailResult.MaterialLog mlog = new SealCarveTaskDetailResult.MaterialLog();
                mlog.setAddTime(mflow.getClaimTime());
                mlog.setTakeOverEmpID(mflow.getEmpID());
                if (curEmployer != null) {
                    mlog.setTakeOverEmpName(curEmployer.getEmpName());
                }

                MaterialFlow lastFlow = i == (flowList.size() - 1) ? null : flowList.get(i + 1);
                if (lastFlow != null) {
                    Employer lastEmployer = employerMap.get(lastFlow.getEmpID());
                    mlog.setHandOverEmpID(lastFlow.getEmpID());
                    if (lastEmployer != null) {
                        mlog.setHandOverEmpName(lastEmployer.getEmpName());
                    }
                }

                String ids = mflow.getMaterialIds();
                if (StringUtils.isNotEmpty(ids)) {
                    List<Material> materialList = new ArrayList<>();
                    String[] idAry = ids.split(",");
                    for (String id : idAry) {
                        Material material = materialMap.get(Integer.parseInt(id));
                        materialList.add(material);
                    }
                    mlog.setMaterials(materialList);
                }

                materialLogList.add(mlog);
            }

            result.setMaterialLog(materialLogList);
        }


        //备注记录
        List<SealCarveTaskRemark> remarks = sealCarveTaskMapper.getAllTaskRemarks(taskID);
        if (remarks != null && remarks.size() > 0) {
            List<Long> empIds = new ArrayList<>();
            remarks.forEach(item -> empIds.add(item.getEmpID()));
            Map<Long, Employer> employerMap = jebeService.getEmployers(empIds);

            List<SealCarveTaskDetailResult.RemarkLog> remarkLogList = new ArrayList<>();
            remarks.forEach(item -> {
                SealCarveTaskDetailResult.RemarkLog remarkLog = new SealCarveTaskDetailResult.RemarkLog();
                remarkLog.setAddTime(item.getAddTime());
                remarkLog.setEmpID(item.getEmpID());
                remarkLog.setRemark(item.getRemark());
                remarkLog.setRemarkID(item.getId());

                Employer employer = employerMap.get(item.getEmpID());
                if (employer != null) {
                    remarkLog.setEmpName(employer.getEmpName());
                }

                remarkLogList.add(remarkLog);
            });

            result.setRemarkLog(remarkLogList);
        }

        return result;
    }


    /**
     * 添加备注
     * @param empID 员工ID
     * @param vo SealCarveTaskRemarkVO
     * @return
     */
    public long addTaskRemark(long empID, SealCarveTaskRemarkVO vo) {
        SealCarveTaskRemark remark = new SealCarveTaskRemark();
        remark.setAddTime(new Date());
        remark.setEmpID(empID);
        remark.setRemark(vo.getRemark());
        remark.setTaskID(vo.getTaskID());

        sealCarveTaskMapper.addTaskRemark(remark);

        //TODO: 往服务单也添加备注

        return remark.getId();
    }

    /**
     * 更新任务状态
     * @param empID 员工ID
     * @param taskIds 任务ID数组
     * @param status 状态值
     * @return
     */
    public int updateTaskStatus(long empID, long[] taskIds, int status) {
        SealCarveTaskStatusEnum statusEnum = SealCarveTaskStatusEnum.fromStatusValue(status);
        if (statusEnum == SealCarveTaskStatusEnum.Unknow) {
            return -1;
        }

        //更新任务状态
        int count = sealCarveTaskMapper.updateTaskStatusMulti(taskIds, status);

        //添加状态记录
        for (long id : taskIds) {
            SealCarveTaskStatus statusEntity = new SealCarveTaskStatus();
            statusEntity.setAddTime(new Date());
            statusEntity.setEmpID(empID);
            statusEntity.setStatus(status);
            statusEntity.setTaskID(id);

            sealCarveTaskMapper.addTaskStatusLog(statusEntity);
        }

        //TODO: 如果是完成状态需要更新"流程"的状态

        return count;
    }

    /**
     * 更新公司信息
     * @param empID 员工ID
     * @param vo EnterpriseInfoVO
     * @return
     */
    public int updateEnterpriseInfo(long empID, EnterpriseInfoVO vo) {
        //更新任务记录的公司信息
        sealCarveTaskMapper.updateTaskAgentName(vo.getTaskID(), vo.getAgentName());

        SealCarveTask task = sealCarveTaskMapper.getTaskByID(vo.getTaskID());
        if (task == null) {
            return 0;
        }

        Enterprise enterprise = new Enterprise();
        enterprise.setTransactorType(vo.getAgentType());
        enterprise.setTransactorName(vo.getAgentName());
        enterprise.setTransactorPhone(vo.getAgentTel());
        enterprise.setUnifiedSocialCreditCode(vo.getUnitSocietyCode());
        enterprise.setLegalPersonName(vo.getLegalPerson());
        enterprise.setLegalPersonTel(vo.getTel());
        enterprise.setLegalPersonIDType(vo.getCertificateType() + "");
        enterprise.setLegalPersonIDNum(vo.getCertificateNum());
        enterprise.setLegalPersonResidenceAddress(vo.getCensusRegister());

        return jebeService.updateEnterprise(enterprise);
    }

    /**
     * 获取公司信息
     * @param taskID 任务ID
     * @return
     */
    public EnterpriseInfoVO getEnterpriseInfo(long taskID) {
        SealCarveTask task = sealCarveTaskMapper.getTaskByID(taskID);
        EnterpriseInfoVO enterpriseInfoVO = new EnterpriseInfoVO();
        if (task == null) {
            return enterpriseInfoVO;
        }

        Enterprise enterprise = jebeService.getEnterpriseByID(task.getEnterpriseID());
        if (enterprise == null) {
            return enterpriseInfoVO;
        }

        enterpriseInfoVO.setTaskID(task.getId());
        enterpriseInfoVO.setUnitSocietyCode(enterprise.getUnifiedSocialCreditCode());
        enterpriseInfoVO.setLegalPerson(enterprise.getLegalPersonName());
        enterpriseInfoVO.setTel(enterprise.getLegalPersonTel());
        enterpriseInfoVO.setCertificateNum(enterprise.getLegalPersonIDNum());
        enterpriseInfoVO.setCensusRegister(enterprise.getLegalPersonResidenceAddress());

        if (StringUtils.isNotEmpty(enterprise.getLegalPersonIDType())) {
            enterpriseInfoVO.setCertificateType(Integer.parseInt(enterprise.getLegalPersonIDType()));
        }

        return enterpriseInfoVO;
    }

    /**
     *
     * @param empID 员工ID
     * @return
     */
    public List<Integer> getOPButtons(long empID) {
        //TODO: 跟据不同的用户判断操作按钮
        List<Integer> buttons = new ArrayList<>();
        buttons.add(1);
        buttons.add(2);
        buttons.add(3);

        return buttons;
    }
}