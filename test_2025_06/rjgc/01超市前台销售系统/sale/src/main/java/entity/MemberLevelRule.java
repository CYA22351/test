package entity;

import java.math.BigDecimal;

public class MemberLevelRule {
    private int id;
    private String level;
    private String levelName;
    private int minPoints;
    private BigDecimal discountRate;
    private BigDecimal pointsRate;
    private String description;

    public MemberLevelRule() {
    }

    public MemberLevelRule(String level, String levelName, int minPoints, BigDecimal discountRate,
            BigDecimal pointsRate) {
        this.level = level;
        this.levelName = levelName;
        this.minPoints = minPoints;
        this.discountRate = discountRate;
        this.pointsRate = pointsRate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public int getMinPoints() {
        return minPoints;
    }

    public void setMinPoints(int minPoints) {
        this.minPoints = minPoints;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public BigDecimal getPointsRate() {
        return pointsRate;
    }

    public void setPointsRate(BigDecimal pointsRate) {
        this.pointsRate = pointsRate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MemberLevelRule{" +
                "id=" + id +
                ", level='" + level + '\'' +
                ", levelName='" + levelName + '\'' +
                ", minPoints=" + minPoints +
                ", discountRate=" + discountRate +
                ", pointsRate=" + pointsRate +
                '}';
    }
}