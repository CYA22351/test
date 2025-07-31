package Demo4;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/6/11 14:32
 * @description：
 * @modified By：
 * @version:
 */
public class Test {
    public static void main(String[] args) {
        Carddemo carddemo=new Carddemo();
       List<Card> cardList= carddemo.buycard();
        System.out.println(cardList);
        carddemo.Sheffield(cardList);
        System.out.println(cardList);
        List<List<Card>> ret=carddemo.palycard(cardList);
        for (int i=0;i<ret.size();i++){
            System.out.println("第"+(i+1)+"个人的牌: "+ret.get(i));
        }

        System.out.println("剩余的牌为："+cardList);
    }
}