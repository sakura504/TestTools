package com.hq.test.test;

import java.util.regex.Pattern;

/**
 * @author lichaojie
 * @date 2022/7/4 16:00
 * @ClassName Test
 **/
public class Test {
    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in); // 定义变量存储小明的回答
//        String answer = ""; // 一圈100米，1000米为10圈，即为循环的次数
//        for (int i = 0; i < 10; i++) {
//            System.out.println("跑的是第" + (i + 1) + "圈");
//            System.out.println("还能坚持吗？"); // 获取小明的回答
//            answer = input.next(); // 判断小明的回答是否为y？如果不是，则放弃，跳出循环
//            switch (answer){
//                case "y":
//                    System.out.println("继续");
//                    break;
//                default:
//                    break;
//            }
//            // 循环之后的代码
//            System.out.println("加油！继续！");
//        }


//        TreeMap treeMap = new TreeMap();
//        treeMap.put("jack", "杰克");
//        treeMap.put("tom", "汤姆");
//        treeMap.put("kristina", "克瑞斯");
//        treeMap.put("smith", "斯密斯");
//        //长度相同，key不变，替换value
//        treeMap.put("abc", "帅哥");
//        treeMap.put("abc", "美女");
//
//        System.out.println(treeMap.containsKey("abc"));
//
//        System.out.println(treeMap);

//        Integer a = new Integer(128);
//        Integer c = new Integer(128);
//        Integer b = 127;
//        Integer d = 127;
//        System.out.println(b == d);

        String str = "never";
        boolean matches = Pattern.matches("((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65)[0-9]{4})(([1|2][0-9]{3}[0|1][0-9][0-3][0-9][0-9]{3}[Xx0-9])|([0-9]{2}[0|1][0-9][0-3][0-9][0-9]{3}))", str);
        System.out.println(matches);
    }
}
