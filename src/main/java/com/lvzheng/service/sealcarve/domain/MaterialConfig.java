package com.lvzheng.service.sealcarve.domain;

import java.io.Serializable;

/**
 * 材料分组依赖配置
 */
public class MaterialConfig implements Serializable {

    private static final long serialVersionUID = 1L;


    private int id;

    //分组名
    private String name;

    //依赖的材料ID，以逗号分隔，引用material表的id
    private String dependMaterials;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDependMaterials() {
        return dependMaterials;
    }

    public void setDependMaterials(String dependMaterials) {
        this.dependMaterials = dependMaterials;
    }
}
