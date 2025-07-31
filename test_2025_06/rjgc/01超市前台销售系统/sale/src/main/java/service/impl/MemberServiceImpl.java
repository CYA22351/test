package service.impl;

import dao.MemberDAO;
import dao.impl.MemberDAOImpl;
import entity.Member;
import entity.MemberLevelRule;
import service.MemberService;
import util.StringUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MemberServiceImpl implements MemberService {

    private MemberDAO memberDAO = new MemberDAOImpl();

    @Override
    public Member findByCardNo(String cardNo) {
        if (StringUtil.isEmpty(cardNo)) {
            return null;
        }
        Member member = memberDAO.findByCardNo(cardNo);
        if (member != null) {
            member.setLevel(mapLevel(member.getLevel()));
        }
        return member;
    }

    @Override
    public Member findById(int id) {
        Member member = memberDAO.findById(id);
        if (member != null) {
            member.setLevel(mapLevel(member.getLevel()));
            return member;
        }
        return null;
    }

    @Override
    public List<Member> findAll() {
        return memberDAO.findAll();
    }

    @Override
    public List<Member> findByLevel(String level) {
        return memberDAO.findByLevel(level);
    }

    @Override
    public List<Member> findByStatus(int status) {
        return memberDAO.findByStatus(status);
    }

    @Override
    public boolean addMember(Member member) {
        if (member == null || StringUtil.isEmpty(member.getName()) ||
                StringUtil.isEmpty(member.getCardNo())) {
            return false;
        }

        if (memberDAO.existsByCardNo(member.getCardNo())) {
            return false;
        }

        try {
            memberDAO.save(member);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateMember(Member member) {
        if (member == null || member.getId() <= 0) {
            return false;
        }

        try {
            memberDAO.update(member);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteMember(int id) {
        if (id <= 0) {
            return false;
        }

        try {
            memberDAO.delete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updatePoints(int id, int points) {
        if (id <= 0 || points < 0) {
            return false;
        }

        try {
            memberDAO.updatePoints(id, points);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateLevel(int id, String level) {
        if (id <= 0 || StringUtil.isEmpty(level)) {
            return false;
        }

        try {
            memberDAO.updateLevel(id, level);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateTotalConsumption(int id, BigDecimal amount) {
        if (id <= 0 || amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            return false;
        }

        try {
            memberDAO.updateTotalConsumption(id, amount);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean existsByCardNo(String cardNo) {
        return memberDAO.existsByCardNo(cardNo);
    }

    @Override
    public boolean processConsumption(int memberId, BigDecimal amount) {
        if (memberId <= 0 || amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }

        Member member = memberDAO.findById(memberId);
        if (member == null || member.getStatus() != 1) {
            return false;
        }

        try {
            // 更新消费金额
            BigDecimal newTotal = member.getTotalConsumption().add(amount);
            memberDAO.updateTotalConsumption(memberId, newTotal);

            // 计算并更新积分
            int newPoints = member.getPoints() + calculatePoints(memberId, amount);
            memberDAO.updatePoints(memberId, newPoints);

            // 检查升级
            checkAndUpgradeMember(memberId);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public BigDecimal calculateDiscount(int memberId, BigDecimal amount) {
        if (memberId <= 0 || amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }

        Member member = memberDAO.findById(memberId);
        if (member == null || member.getStatus() != 1) {
            return BigDecimal.ZERO;
        }

        // 根据会员等级计算折扣
        BigDecimal discountRate = getDiscountRateByLevel(member.getLevel());
        BigDecimal discountAmount = amount.multiply(BigDecimal.ONE.subtract(discountRate));

        return discountAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public int calculatePoints(int memberId, BigDecimal amount) {
        if (memberId <= 0 || amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            return 0;
        }

        Member member = memberDAO.findById(memberId);
        if (member == null || member.getStatus() != 1) {
            return 0;
        }

        // 根据会员等级计算积分倍率
        BigDecimal pointsRate = getPointsRateByLevel(member.getLevel());
        int points = amount.multiply(pointsRate).intValue();

        return points;
    }

    @Override
    public boolean checkAndUpgradeMember(int memberId) {
        Member member = memberDAO.findById(memberId);
        if (member == null) {
            return false;
        }

        String newLevel = getMemberLevelByPoints(member.getPoints());
        if (!newLevel.equals(member.getLevel())) {
            return updateLevel(memberId, newLevel);
        }

        return true;
    }

    @Override
    public List<MemberLevelRule> getMemberLevelRules() {
        // 这里应该从数据库获取，暂时返回硬编码的规则
        List<MemberLevelRule> rules = new ArrayList<>();
        rules.add(new MemberLevelRule("BRONZE", "青铜会员", 0, new BigDecimal("0.95"), new BigDecimal("1.00")));
        rules.add(new MemberLevelRule("SILVER", "白银会员", 1000, new BigDecimal("0.90"), new BigDecimal("1.10")));
        rules.add(new MemberLevelRule("GOLD", "黄金会员", 5000, new BigDecimal("0.85"), new BigDecimal("1.20")));
        rules.add(new MemberLevelRule("PLATINUM", "铂金会员", 10000, new BigDecimal("0.80"), new BigDecimal("1.50")));
        return rules;
    }

    @Override
    public String getMemberLevelByPoints(int points) {
        if (points >= 10000) {
            return "PLATINUM";
        } else if (points >= 5000) {
            return "GOLD";
        } else if (points >= 1000) {
            return "SILVER";
        } else {
            return "BRONZE";
        }
    }

    @Override
    public List<Member> searchByName(String name) {
        if (StringUtil.isEmpty(name)) {
            return new ArrayList<>();
        }
        return memberDAO.findByName(name);
    }

    @Override
    public List<Member> searchByPhone(String phone) {
        if (StringUtil.isEmpty(phone)) {
            return new ArrayList<>();
        }
        return memberDAO.findByPhone(phone);
    }

    @Override
    public boolean addPoints(int id, int points) {
        if (id <= 0 || points <= 0) {
            return false;
        }

        Member member = memberDAO.findById(id);
        if (member == null || member.getStatus() != 1) {
            return false;
        }

        try {
            int newPoints = member.getPoints() + points;
            memberDAO.updatePoints(id, newPoints);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deductPoints(int id, int points) {
        if (id <= 0 || points <= 0) {
            return false;
        }

        Member member = memberDAO.findById(id);
        if (member == null || member.getStatus() != 1) {
            return false;
        }

        if (member.getPoints() < points) {
            return false; // 积分不足
        }

        try {
            int newPoints = member.getPoints() - points;
            memberDAO.updatePoints(id, newPoints);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String getNextLevel(int id) {
        if (id <= 0) {
            return "";
        }

        Member member = memberDAO.findById(id);
        if (member == null) {
            return "";
        }

        String currentLevel = member.getLevel();
        switch (currentLevel) {
            case "BRONZE":
                return "SILVER";
            case "SILVER":
                return "GOLD";
            case "GOLD":
                return "PLATINUM";
            case "PLATINUM":
                return ""; // 已经是最高等级
            default:
                return "BRONZE";
        }
    }

    @Override
    public boolean addLevelRule(MemberLevelRule rule) {
        // 这里应该调用MemberLevelRuleDAO，暂时返回false
        // TODO: 实现会员等级规则管理
        return false;
    }

    @Override
    public boolean updateLevelRule(MemberLevelRule rule) {
        // 这里应该调用MemberLevelRuleDAO，暂时返回false
        // TODO: 实现会员等级规则管理
        return false;
    }

    @Override
    public boolean deleteLevelRule(int id) {
        // 这里应该调用MemberLevelRuleDAO，暂时返回false
        // TODO: 实现会员等级规则管理
        return false;
    }

    @Override
    public int getTotalCount() {
        return memberDAO.getTotalCount();
    }

    @Override
    public int getActiveCount() {
        return memberDAO.getActiveCount();
    }

    private BigDecimal getDiscountRateByLevel(String level) {
        switch (level) {
            case "PLATINUM":
                return new BigDecimal("0.80");
            case "GOLD":
                return new BigDecimal("0.85");
            case "SILVER":
                return new BigDecimal("0.90");
            case "BRONZE":
                return new BigDecimal("0.95");
            default:
                return new BigDecimal("1.00");
        }
    }

    private BigDecimal getPointsRateByLevel(String level) {
        switch (level) {
            case "PLATINUM":
                return new BigDecimal("1.50");
            case "GOLD":
                return new BigDecimal("1.20");
            case "SILVER":
                return new BigDecimal("1.10");
            case "BRONZE":
                return new BigDecimal("1.00");
            default:
                return new BigDecimal("1.00");
        }
    }

    /**
     * 兼容数据库level为中文的情况，统一映射为英文等级
     */
    private String mapLevel(String level) {
        if (level == null)
            return "BRONZE";
        switch (level.trim()) {
            case "青铜会员":
            case "BRONZE":
                return "BRONZE";
            case "白银会员":
            case "SILVER":
                return "SILVER";
            case "黄金会员":
            case "GOLD":
                return "GOLD";
            case "铂金会员":
            case "PLATINUM":
                return "PLATINUM";
            default:
                return "BRONZE";
        }
    }
}