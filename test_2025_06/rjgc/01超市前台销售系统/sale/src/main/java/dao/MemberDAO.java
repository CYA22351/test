package dao;

import entity.Member;
import java.util.List;

public interface MemberDAO {

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
     * 根据姓名查找会员
     */
    List<Member> findByName(String name);

    /**
     * 根据手机号查找会员
     */
    List<Member> findByPhone(String phone);

    /**
     * 保存会员
     */
    void save(Member member);

    /**
     * 更新会员
     */
    void update(Member member);

    /**
     * 删除会员
     */
    void delete(int id);

    /**
     * 更新会员积分
     */
    void updatePoints(int id, int points);

    /**
     * 更新会员等级
     */
    void updateLevel(int id, String level);

    /**
     * 更新会员消费金额
     */
    void updateTotalConsumption(int id, java.math.BigDecimal amount);

    /**
     * 检查卡号是否存在
     */
    boolean existsByCardNo(String cardNo);

    /**
     * 获取会员总数
     */
    int getTotalCount();

    /**
     * 获取活跃会员数量
     */
    int getActiveCount();
}