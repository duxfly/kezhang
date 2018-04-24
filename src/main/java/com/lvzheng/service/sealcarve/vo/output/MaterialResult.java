package com.lvzheng.service.sealcarve.vo.output;

import com.lvzheng.service.sealcarve.domain.Material;

import java.util.List;

public class MaterialResult {

    private String type;

    private List<Material> list;

    /**
     *
     * @param type 分组类型名
     * @param list 材料项集合
     */
    public MaterialResult(String type, List<Material> list) {
        this.type = type;
        this.list = list;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Material> getList() {
        return list;
    }

    public void setList(List<Material> list) {
        this.list = list;
    }
}
