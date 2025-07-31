package service;

import entity.Member;
import entity.MemberLevelRule;
import java.util.List;

public interface MemberService {

    /**
     * 根据卡号查找会员
     */
    Member findByCardNo(String cardNo);

    /**
     * 根据ID查找会员
     */
    Member findById(int id);

    /**
     * 查找所有会员
     */
    List<Member> findAll();

    /**
     * 根据等级查找会员
     */
    List<Member> findByLevel(String level);

    /**
     * 根据状态查找会员
     */
    List<Member> findByStatus(int status);

    /**
     * 添加会员
     */
    boolean addMember(Member member);

    /**
     * 更新会员
     */
    boolean updateMember(Member member);

    /**
     * 删除会员
     */
    boolean deleteMember(int id);

    /**
     * 更新会员积分
     */
    boolean updatePoints(int id, int points);

    /**
     * 更新会员等级
     */
    boolean updateLevel(int id, String level);

    /**
     * 更新会员消费金额
     */
    boolean updateTotalConsumption(int id, java.math.BigDecimal amount);

    /**
     * 检查卡号是否存在
     */
    boolean existsByCardNo(String cardNo);

    /**
     * 会员消费处理
     */
    boolean processConsumption(int memberId, java.math.BigDecimal amount);

    /**
     * 计算会员折扣
     */
    java.math.BigDecimal calculateDiscount(int memberId, java.math.BigDecimal amount);

    /**
     * 计算会员积分
     */
    int calculatePoints(int memberId, java.math.BigDecimal amount);

    /**
     * 检查会员升级
     */
    boolean checkAndUpgradeMember(int memberId);

    /**
     * 获取会员等级规则
     */
    List<MemberLevelRule> getMemberLevelRules();

    /**
     * 根据积分获取会员等级
     */
    String getMemberLevelByPoints(int points);

    /**
     * 根据名称查找会员
     */
    List<Member> searchByName(String name);

    /**
     * 根据电话查找会员
     */
    List<Member> searchByPhone(String phone);

    /**
     * 添加会员积分
     */
    boolean addPoints(int id, int points);

    /**
     * 扣除会员积分
     */
    boolean deductPoints(int id, int points);

    /**
     * 获取下一个会员等级
     */
    String getNextLevel(int id);

    /**
     * 添加会员等级规则
     */
    boolean addLevelRule(MemberLevelRule rule);

    /**
     * 更新会员等级规则
     */
    boolean updateLevelRule(MemberLevelRule rule);

    /**
     * 删除会员等级规则
     */
    boolean deleteLevelRule(int id);

    /**
     * 获取会员总数
     */
    int getTotalCount();

    /**
     * 获取活跃会员数
     */
    int getActiveCount();
}