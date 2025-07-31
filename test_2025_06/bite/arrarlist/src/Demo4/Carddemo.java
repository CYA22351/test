package Demo4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/6/11 14:27
 * @description：
 * @modified By：
 * @version:
 */
public class Carddemo {
    public static final String[] suits={"♥","♠","♣","♦"};
    public List<Card> buycard(){
        List<Card> cardlist=new ArrayList<>();
        for (int i=1;i<=13;i++){
            for (int j=0;j<4;j++){
                int rank=i;
                String suit=suits[j];
                Card card=new Card(suit,rank);
                cardlist.add(card);
            }
        }
        return cardlist;
    }
    //洗牌
    public void Sheffield(List<Card> cardList){
        Random random=new Random();
        for (int i=cardList.size()-1;i>0;i--){
            int index=random.nextInt(i);
            swap(cardList,i,index);
        }
    }
    public void swap(List<Card> cardList,int i,int j){
        Card tmp=cardList.get(i);
        cardList.set(i,cardList.get(j));
        cardList.set(j,tmp);
    }
    //发牌
    public  List<List<Card>>  palycard(List<Card> cardList){
        List<Card> hand0=new ArrayList<>();
        List<Card> hand1=new ArrayList<>();
        List<Card> hand2=new ArrayList<>();
        List<List<Card>> hand=new ArrayList<>();
        hand.add(hand0);
        hand.add(hand1);
        hand.add(hand2);
        for (int i=0;i<5;i++){
            for (int j=0;j<3;j++){
           Card card=cardList.remove(0);
           hand.get(j).add(card);
            }
        }

        return hand;
    }



}