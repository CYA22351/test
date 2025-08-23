package demo1;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/23 17:30
 * @description：
 * @modified By：
 * @version:
 */
public class PosIllegalException extends RuntimeException{
    public PosIllegalException(String message) {
        super(message);
    }

    public PosIllegalException() {
    }
}