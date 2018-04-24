package com.lvzheng.service.sealcarve.vo.input;

public class SealCarveTaskCreateVO {

    //公司ID
    private long enterpriseID;

    //母任务ID
    private long masterTaskID;

    //订单ID
    private long orderID;

    //产品ID
    private long productID;


    public long getEnterpriseID() {
        return enterpriseID;
    }

    public void setEnterpriseID(long enterpriseID) {
        this.enterpriseID = enterpriseID;
    }

    public long getMasterTaskID() {
        return masterTaskID;
    }

    public void setMasterTaskID(long masterTaskID) {
        this.masterTaskID = masterTaskID;
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }
        
}
