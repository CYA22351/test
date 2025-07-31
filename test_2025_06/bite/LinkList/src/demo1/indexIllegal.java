package demo1;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/6/16 10:51
 * @description：
 * @modified By：
 * @version:
 */
public class indexIllegal extends RuntimeException{
    public indexIllegal(){

    }
    public indexIllegal(String s){
        super(s);
    }
}