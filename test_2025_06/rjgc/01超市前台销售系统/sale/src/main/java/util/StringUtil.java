package util;

import java.util.UUID;

public class StringUtil {

    /**
     * 判断字符串是否为空
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 判断字符串是否不为空
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 生成唯一ID
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成销售单号
     */
    public static String generateSaleNo() {
        return "S" + System.currentTimeMillis();
    }

    /**
     * 生成会员卡号
     */
    public static String generateCardNo() {
        return "M" + System.currentTimeMillis();
    }

    /**
     * 格式化金额
     */
    public static String formatMoney(double amount) {
        return String.format("%.2f", amount);
    }

    /**
     * 格式化金额
     */
    public static String formatMoney(java.math.BigDecimal amount) {
        if (amount == null) {
            return "0.00";
        }
        return String.format("%.2f", amount);
    }
}