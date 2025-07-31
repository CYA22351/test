import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 璇诲彇鏁磋骞舵寜绌烘牸鍒嗗壊
        String[] parts = sc.nextLine().trim().split("\\s+");

        if (parts.length < 2) {
            System.out.println("Input is invalid. Please provide two binary numbers enclosed in quotes.");
            return;
        }

        String a = parts[0].replace("\"", "");
        String b = parts[1].replace("\"", "");

        BigInteger numA = new BigInteger(a, 2);
        BigInteger numB = new BigInteger(b, 2);
        BigInteger sum = numA.add(numB);

        System.out.println(sum.toString(2));
    }
}
