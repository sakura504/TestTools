package com.hq.test.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lichaojie
 * @date 2022/3/24 14:27
 * @ClassName NumberUtils
 **/
public class NumberUtils {
    public static final String[] CHINESE_NUMBERS = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    public static final String[] CHINESE_NUMBER_UNIT = {"十", "百", "千", "万"};
    public static final String BLANK_STRING = "";

    public static final String regEx = "[^0-9]";

    public static String arabicToChinese(int number) {
        if (number > 99999 || number < 1) {
            return BLANK_STRING;
        }
        String source = String.valueOf(number);
        String result = BLANK_STRING;
        int length = source.length();
        for (int i = 0; i < length; i++) {
            int chineseNumber = source.charAt(i) - '0';
            int len = result.length();
            if (i == 0 && chineseNumber == 1 && length == 2) {
                result += CHINESE_NUMBER_UNIT[0];
            } else if (i != length - 1 && chineseNumber != 0) {
                result += CHINESE_NUMBERS[chineseNumber] + CHINESE_NUMBER_UNIT[length - i - 2];
            } else if (i == length - 1 && chineseNumber != 0) {
                result += CHINESE_NUMBERS[chineseNumber];
            } else if (i != length - 1 && chineseNumber == 0
                    && !result.substring(len - 1, len).equals(CHINESE_NUMBERS[chineseNumber])) {
                result += CHINESE_NUMBERS[chineseNumber];
            }
        }
        return result;
    }

    private static int chineseNumber2Int(String chineseNumber){
        int result = 0;
        int temp = 1;//存放一个单位的数字如：十万
        int count = 0;//判断是否有chArr
        char[] cnArr = new char[]{'一','二','三','四','五','六','七','八','九'};
        char[] chArr = new char[]{'十','百','千','万','亿'};
        for (int i = 0; i < chineseNumber.length(); i++) {
            boolean b = true;//判断是否是chArr
            char c = chineseNumber.charAt(i);
            for (int j = 0; j < cnArr.length; j++) {//非单位，即数字
                if (c == cnArr[j]) {
                    if(0 != count){//添加下一个单位之前，先把上一个单位值添加到结果中
                        result += temp;
                        temp = 1;
                        count = 0;
                    }
                    // 下标+1，就是对应的值
                    temp = j + 1;
                    b = false;
                    break;
                }
            }
            if(b){//单位{'十','百','千','万','亿'}
                for (int j = 0; j < chArr.length; j++) {
                    if (c == chArr[j]) {
                        switch (j) {
                            case 0:
                                temp *= 10;
                                break;
                            case 1:
                                temp *= 100;
                                break;
                            case 2:
                                temp *= 1000;
                                break;
                            case 3:
                                temp *= 10000;
                                break;
                            case 4:
                                temp *= 100000000;
                                break;
                            default:
                                break;
                        }
                        count++;
                    }
                }
            }
            if (i == chineseNumber.length() - 1) {//遍历到最后一个字符
                result += temp;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> strs = Stream.of("1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1").collect(Collectors.toList());
        List<String> list = handleNumbers(strs);
        System.out.println(list);
    }

    private static List<String> handleNumbers(List<String> strs) {
        List<String> nowStrs = new ArrayList<>();
        for (String oldStr : strs) {
            Pattern pattern = Pattern.compile(regEx);
            Matcher matcher = pattern.matcher(oldStr);
            String numStr = matcher.replaceAll("").trim();

            //若答案包含其他中文字则不做转化
            if (oldStr.length() != numStr.length()){
                nowStrs.add(oldStr);
                continue;
            }

            String nowStr = NumberUtils.arabicToChinese(Integer.parseInt(numStr));
            nowStrs.add(nowStr);
        }

        return nowStrs;
    }
}
