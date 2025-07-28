package File;

import java.util.Scanner;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/19 16:30
 * @description：
 * @modified By：
 * @version:
 */
public class Demo {
//    private static int fib(int n){
//        int[] arr=new int[20];
//        arr[1]=1;
//        arr[2]=1;
//        if (n==1||n==2){
//            return 1;
//        }
//
//       for (int i=3;i<=n;i++)
//       {
//           arr[i]=arr[i-1]+arr[i-2];
//       }
//        return arr[n];
//    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
     String st1=scanner.next();
     String st2=scanner.next();
        boolean[] remove = new boolean[256];
        for (char c :st2.toCharArray()) {
            remove[c] = true;
        }
        StringBuilder br=new StringBuilder();
        for (char c : st1.toCharArray()) {
            if (!remove[c]) {
                br.append(c);
            }
        }
        System.out.println(br.toString());
    }
}