package File;

import java.io.File;
import java.util.Scanner;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/7/15 17:50
 * @description：
 * @modified By：
 * @version:
 */
public class Demo12 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入你要搜索的目录：");
        String root=scanner.next();
        File file=new File(root);
        if (!file.isDirectory()){
            System.out.println("输入的不是目录！");
            return;
        }
        System.out.println("请输入你要删除的关键字");
        String keyword=scanner.next();
        scanDir(file,keyword);

    }
    private static void scanDir(File file,String keyword){
        System.out.println("遍历目录： "+file.getAbsolutePath());

        File[] files=file.listFiles();
        if (files==null){
            System.out.println("目录为空！");
            return;
        }
        for (File file1:files){
            if (file1.isDirectory()){
                scanDir(file1,keyword);
            }
            else {
                dealFile(file1,keyword);
            }
        }
    }
    private static void dealFile(File file,String keyword){
        if (file.getName().contains(keyword)){
            System.out.println("发现文件："+file.getAbsolutePath()+"包含关键字！是否删除？（y/n）");
            Scanner scanner=new Scanner(System.in);
            String put=scanner.next();
            if (put.contains("y")){
                file.delete();
                System.out.println("删除成功");
            }

        }

    }
}
