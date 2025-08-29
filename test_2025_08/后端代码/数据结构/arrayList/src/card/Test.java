package card;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/29 8:46
 * @description：
 * @modified By：
 * @version:
 */
public class Test {
    public static void main(String[] args) {
        CardGame cardGame=new CardGame();
        List<Card> cardList = cardGame.buyCard();
        System.out.println(cardList);
        cardGame.shuffle(cardList);
        System.out.println("洗牌： "+cardList);
        List<List<Card>> hands=new ArrayList<>();
        List<Card> hand0=new ArrayList<>();
        List<Card> hand1=new ArrayList<>();
        List<Card> hand2=new ArrayList<>();
    hands.add(hand0);
    hands.add(hand1);
    hands.add(hand2);
        for (int i=0;i<5;i++){
            for (int j=0;j<3;j++){
                    hands.get(j).add(cardList.remove(0));
            }
        }
        System.out.println("A分的牌"+hands.get(0));
        System.out.println("B分的牌"+hands.get(1));
        System.out.println("B分的牌"+hands.get(2));
        System.out.println("剩余的牌："+cardList);
    }
}