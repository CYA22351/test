package Demo4;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/6/11 14:25
 * @description：
 * @modified By：
 * @version:
 */
public class Card {
    private  String suit;
    private int rank;

    public Card(String suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "{"+suit+ rank+"}";
    }
}