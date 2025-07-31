package entity;

import java.math.BigDecimal;
import java.util.Date;

public class Member {
    private int id;
    private String name;
    private String cardNo;
    private String phone;
    private String level;
    private int points;
    private BigDecimal totalConsumption;
    private int status;
    private Date createTime;
    private Date updateTime;

    public Member() {
    }

    public Member(String name, String cardNo, String phone) {
        this.name = name;
        this.cardNo = cardNo;
        this.phone = phone;
        this.level = "BRONZE";
        this.points = 0;
        this.totalConsumption = BigDecimal.ZERO;
        this.status = 1;
    }

    // Getters and Setters
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

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public BigDecimal getTotalConsumption() {
        return totalConsumption;
    }

    public void setTotalConsumption(BigDecimal totalConsumption) {
        this.totalConsumption = totalConsumption;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", level='" + level + '\'' +
                ", points=" + points +
                ", status=" + status +
                '}';
    }
}