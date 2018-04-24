package com.lvzheng.service.sealcarve.controller;

import com.lvzheng.service.sealcarve.service.SealCarveTaskService;
import com.lvzheng.service.sealcarve.util.JSONResult;
import com.lvzheng.service.sealcarve.util.ResultUtil;
import com.lvzheng.service.sealcarve.vo.input.EnterpriseInfoVO;
import com.lvzheng.service.sealcarve.vo.input.SealCarveTaskCreateVO;
import com.lvzheng.service.sealcarve.vo.input.SealCarveTaskQueryVO;
import com.lvzheng.service.sealcarve.vo.input.SealCarveTaskRemarkVO;
import com.lvzheng.service.sealcarve.vo.input.SealCarveTaskStatusVO;
import com.lvzheng.service.sealcarve.vo.output.SealCarveTaskDetailResult;
import com.lvzheng.service.sealcarve.vo.output.SealCarveTaskResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/sealcarve")
public class SealCarveTaskController {

    private static final Logger logger = LoggerFactory.getLogger(SealCarveTaskController.class);

    @Autowired
    private SealCarveTaskService sealCarveTaskService;


    /**
     * POST 创建刻章任务
     * @param vo SealCarveTaskCreateVO
     * @param br BindingResult
     * @return 任务ID
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public JSONResult<Long> create(@RequestBody @Valid SealCarveTaskCreateVO vo, BindingResult br) {
        if (br.hasErrors()) {
            logger.error("create", br.getFieldError().getDefaultMessage());
            return ResultUtil.error(-1, br.getFieldError().getDefaultMessage());
        }

        long id = sealCarveTaskService.addSealCarveTask(vo);
        return ResultUtil.success(id);
    }
    
    /**
     * 获取刻章任务列表
     * @param query SealCarveTaskQueryVO
     * @param br BindingResult
     * @return 刻章任务列表
     */
    @RequestMapping("/get_task_by_page")
    public JSONResult<SealCarveTaskResult> getTaskByPage(@RequestBody @Valid SealCarveTaskQueryVO query, BindingResult br) {
        long empID = 0;
        if (br.hasErrors()) {
            logger.error("get_task_by_page", br.getFieldError().getDefaultMessage());
            return ResultUtil.error(-1, br.getFieldError().getDefaultMessage());
        }

        SealCarveTaskResult result = sealCarveTaskService.getSealCarveTask(empID, query);
        return ResultUtil.success(result);
    }
    
    /**
     * 跟据刻章任务ID，获取任务详情（支持批量）
     * @param taskID 任务ID
     * @param enterpriseID 企业ID
     * @return 刻章任务详情
     */
    @RequestMapping("/get_task_detail")
    public JSONResult<SealCarveTaskDetailResult> getTaskDetail(long taskID, long enterpriseID) {
        SealCarveTaskDetailResult result = sealCarveTaskService.getTaskDetail(taskID, enterpriseID);
        return ResultUtil.success(result);
    }
    
    /**
     * 更新任务状态
     * @param status SealCarveTaskStatusVO
     * @return 更新记录数
     */
    @RequestMapping(value = "/update_task_status", method = RequestMethod.POST)
    public JSONResult<Integer> updateTaskStatus(@RequestBody SealCarveTaskStatusVO status) {
        long empID = 0;
        int count = sealCarveTaskService.updateTaskStatus(empID, status.getTaskIds(), status.getStatus());

        if (count <= 0) {
            return ResultUtil.error(-1, "更新失败");
        }
        return ResultUtil.success(count);
    }
    
    /**
     * 添加备注
     * @param remark SealCarveTaskRemarkVO
     * @param br BindingResult
     * @return 备注ID
     */
    @RequestMapping(value = "/add_remark", method = RequestMethod.POST)
    public JSONResult<Long> addRemark(@RequestBody @Valid SealCarveTaskRemarkVO remark, BindingResult br) {
        if (br.hasErrors()) {
            logger.error("add_remark", br.getFieldError().getDefaultMessage());
            return ResultUtil.error(-1, br.getFieldError().getDefaultMessage());
        }

        long empID = 0;
        long remarkID = sealCarveTaskService.addTaskRemark(empID, remark);
        return ResultUtil.success(remarkID);
    }
    
    /**
     * 补充信息保存，其中的公司信息为同步到企业库
     * @param info EnterpriseInfoVO
     * @param br BindingResult
     * @return 更新成功记录数
     */
    @RequestMapping(value = "/update_enterprise_info", method = RequestMethod.POST)
    public JSONResult<Integer> updateEnterpriseInfo(@RequestBody @Valid EnterpriseInfoVO info, BindingResult br) {
        if (br.hasErrors()) {
            logger.error("add_remark", br.getFieldError().getDefaultMessage());
            return ResultUtil.error(-1, br.getFieldError().getDefaultMessage());
        }

        long empID = 0;
        int count = sealCarveTaskService.updateEnterpriseInfo(empID, info);
        return ResultUtil.success(count);
    }

    /**
     * 获取公司信息
     * @param taskID 任务ID
     * @return 公司信息
     */
    @RequestMapping(value = "/get_enterprise_info")
    public JSONResult<EnterpriseInfoVO> getEnterpriseInfo(long taskID) {
        EnterpriseInfoVO vo = sealCarveTaskService.getEnterpriseInfo(taskID);
        return ResultUtil.success(vo);
    }

}
