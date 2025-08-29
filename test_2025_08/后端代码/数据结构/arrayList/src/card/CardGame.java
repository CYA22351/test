package card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/29 8:34
 * @description：
 * @modified By：
 * @version:
 */
public class CardGame {
    public static final String[] suits={"♣","♥","♠","♦"};
    public List<Card> buyCard(){
        List<Card> cardList=new ArrayList<>();
        for (int i=0;i<4;i++){
            for (int j=1;j<=13;j++){
                String suit=suits[i];
                int rank=j;
                Card card=new Card(suit,rank);
                cardList.add(card);

            }
        }
        return cardList;
    }
    public void shuffle( List<Card> cardList)
    {
        Random random=new Random();
        for (int i=cardList.size()-1;i>0;i--){
            int index=random.nextInt(i);
            swap(cardList,i,index);

        }
    }
    public void swap(List<Card> cardList,int i,int j){
        Card card=cardList.get(i);
        cardList.set(i,cardList.get(j));
        cardList.set(j,card);
    }

}