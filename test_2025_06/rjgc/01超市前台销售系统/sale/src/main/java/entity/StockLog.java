package entity;

import java.util.Date;

public class StockLog {
    private int id;
    private int productId;
    private String changeType; // SALE, SUPPLY, ADJUST
    private int beforeStock;
    private int afterStock;
    private int changeQuantity;
    private int operatorId;
    private Date changeTime;
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public int getBeforeStock() {
        return beforeStock;
    }

    public void setBeforeStock(int beforeStock) {
        this.beforeStock = beforeStock;
    }

    public int getAfterStock() {
        return afterStock;
    }

    public void setAfterStock(int afterStock) {
        this.afterStock = afterStock;
    }

    public int getChangeQuantity() {
        return changeQuantity;
    }

    public void setChangeQuantity(int changeQuantity) {
        this.changeQuantity = changeQuantity;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}