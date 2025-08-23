package demo1;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/23 17:56
 * @description：
 * @modified By：
 * @version:
 */
public class ListEmptyException extends RuntimeException{
    public ListEmptyException() {
    }

    public ListEmptyException(String message) {
        super(message);
    }
}