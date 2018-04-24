package com.lvzheng.service.sealcarve.controller;

import com.lvzheng.service.sealcarve.domain.Material;
import com.lvzheng.service.sealcarve.service.MaterialService;
import com.lvzheng.service.sealcarve.util.DingTalkHelper;
import com.lvzheng.service.sealcarve.util.JSONResult;
import com.lvzheng.service.sealcarve.util.QRCodeHelper;
import com.lvzheng.service.sealcarve.util.ResultUtil;
import com.lvzheng.service.sealcarve.vo.input.MaterialClaimVO;
import com.lvzheng.service.sealcarve.vo.input.MaterialQueryVO;
import com.lvzheng.service.sealcarve.vo.output.MaterialResult;
import com.lvzheng.service.sealcarve.vo.output.MyMaterialResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 材料管理Controller
 */
@RestController
@RequestMapping("/materials")
public class MaterialController {

    private static final Logger logger = LoggerFactory.getLogger(MaterialController.class);

    @Autowired
    private MaterialService materialService;

    /**
     * 获取刻章所需材料项，分组返回
     * @return 刻章材料项
     */
    @RequestMapping("/get_seal_carve_material")
    public JSONResult<List<MaterialResult>> getMaterials() {
        Map<String, List<Material>> map = materialService.getMaterialByGroup(1, 2, 3);

        List<MaterialResult> result = new ArrayList<>();
        map.forEach((k, v) -> result.add(new MaterialResult(k, v)));
        return ResultUtil.success(result);
    }

    /**
     * 获得所有材料
     * @return 所有材料项
     */
    @RequestMapping("/get_all_material")
    public JSONResult<List<Material>> getAllMaterials() {
        return ResultUtil.success(materialService.getAllMaterialList());
    }

    /**
     * 认领材料
     * @param vo MaterialClaimVO
     * @param br BindingResult
     * @return
     */
    @RequestMapping(value = "/claim", method = RequestMethod.POST)
    public JSONResult<Long> claim(@RequestBody @Valid MaterialClaimVO vo, BindingResult br) {
        if (br.hasErrors()) {
            logger.error("claim", br.getFieldError().getDefaultMessage());
            return ResultUtil.error(-1, br.getFieldError().getDefaultMessage());
        }

        try {
            long id = materialService.claim(vo);
            if (id <= 0) {
                return ResultUtil.error(-1, "error");
            }

            return ResultUtil.success(id);
        } catch (Exception e) {
            logger.error("claim error", e);
        }

        return ResultUtil.error(-1, "error");
    }

    /**
     * 获取我的材料
     * @param query MaterialQueryVO
     * @param br BindingResult
     * @return
     */
    @RequestMapping(value = "/my_materials", method = RequestMethod.POST)
    public JSONResult<MyMaterialResult> getMyMaterials(@RequestBody @Valid MaterialQueryVO query, BindingResult br) {
        try {
            MyMaterialResult result = materialService.getMyMaterialsFlows(query);
            return ResultUtil.success(result);
        } catch (Exception e) {
            logger.error("getMyMaterials error", e);
        }

        return ResultUtil.error(-1, "error");
    }

    /**
     * 获取二维码（扫描进入材料认领页）
     * @param enterpriseID 企业ID
     * @return base64编码的二维码图片
     */
    @RequestMapping("/qrcode/{enterpriseID:\\d+}")
    public JSONResult<String> getFile(@PathVariable long enterpriseID) {
        try {
            String url = "?enterpriseID" + enterpriseID;
            return ResultUtil.success(QRCodeHelper.createBase64Code(url, 200));
        } catch (Exception e) {
            logger.error("create QR code error", e);
        }

        return ResultUtil.error(-1, "生成二维码错误");
    }

    /**
     * 获取钉钉Config
     * @param url 当前页URL
     * @return DingTalk Config
     */
    @RequestMapping(value = "/get_dingtalk_config")
    public JSONResult<DingTalkHelper.Config> getDingTalkConfig(String url) {
        try {
            DingTalkHelper.Config conf = DingTalkHelper.getConfig(url);
            return ResultUtil.success(conf);
        } catch (Exception e) {
            logger.error("get dingding config error", e);
            return ResultUtil.error(-1, "get dingding config error");
        }
    }
}
