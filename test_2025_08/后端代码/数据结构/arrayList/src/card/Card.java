package card;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/8/29 8:05
 * @description：
 * @modified By：
 * @version:
 */
public class Card {
    public String suit;
    public int rank;

    public Card(String suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }


    @Override
    public String toString() {
        return "Card{" +
                "suit='" + suit + '\'' +
                ", rank='" + rank + '\'' +
                '}';
    }
}