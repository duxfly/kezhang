package com.lvzheng.service.sealcarve.vo.output;

import com.lvzheng.service.sealcarve.domain.Material;
import com.lvzheng.service.sealcarve.domain.MaterialFlow;

import java.util.List;

public class MyMaterialResult {

    private int totalCount;

    private List<MaterialFlowItem> items;


    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<MaterialFlowItem> getItems() {
        return items;
    }

    public void setItems(List<MaterialFlowItem> items) {
        this.items = items;
    }


    public static class MaterialFlowItem {

        private MaterialFlow materialFlow;

        private List<Material> materialItems;


        /**
         *
         * @param materialFlow MaterialFlow
         * @param materialItems 材料项集合
         */
        public MaterialFlowItem(MaterialFlow materialFlow, List<Material> materialItems) {
            this.materialFlow = materialFlow;
            this.materialItems = materialItems;
        }


        public MaterialFlow getMaterialFlow() {
            return materialFlow;
        }

        public void setMaterialFlow(MaterialFlow materialFlow) {
            this.materialFlow = materialFlow;
        }

        public List<Material> getMaterialItems() {
            return materialItems;
        }

        public void setMaterialItems(List<Material> materialItems) {
            this.materialItems = materialItems;
        }
    }
}
