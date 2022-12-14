package com.hq.test.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author lichaojie
 * @date 2022/3/16 9:21
 * @ClassName SensitiveFilterUtil
 **/
@SuppressWarnings({"unchecked", "rawtypes"})
public class SensitiveFilterUtil {

    /**
     * 敏感词集合
     */
    public static HashMap sensitiveWordMap;

    /**
     * 初始化敏感词库,构建DFA算法模型
     */
    public static void initContext() {
        HashSet<String> set = new HashSet<String>();
        try {
            //获取敏感词文件
            Workbook workbook = new XSSFWorkbook(new FileInputStream("./src/main/resources/敏感词库表统计.xlsx"));
            // 获取第一个张表
            Sheet sheet = workbook.getSheetAt(0);
            // 获取每行中的字段
            int lastRowNum = sheet.getLastRowNum();
            for (int j = 1; j <= lastRowNum; j++) {
                Row row = sheet.getRow(j); // 获取行
                if (row == null) {// 略过空行
                    continue;
                } else {
                    Cell cell = row.getCell(3);
                    if(cell!=null) {
                        set.add(cell.getStringCellValue());
                    }
                }
            }
            initSensitiveWordMap(set);
        } catch (Exception e) {
            System.out.println("<<<<<<解析敏感词文件报错！");
            e.printStackTrace();
        }
    }

    /**
     * 初始化敏感词库,构建DFA算法模型
     *
     * @param sensitiveWordSet 敏感词库
     */
    private static void initSensitiveWordMap(Set<String> sensitiveWordSet) {
        //初始化敏感词容器,减少扩容操作
        sensitiveWordMap = new HashMap<String,String>(sensitiveWordSet.size());
        Map<Object,Object> temp;
        Map<Object,Object> newWorMap;
        //遍历sensitiveWordSet
        for(String key:sensitiveWordSet) {
            temp = sensitiveWordMap;
            for (int i = 0; i < key.length(); i++) {
                //转换成char型
                char keyChar = key.charAt(i);
                //库中获取关键字
                Object wordMap = temp.get(keyChar);
                //如果存在该key,直接赋值,用于下一个循环获取
                if (wordMap != null) {
                    temp = (Map) wordMap;
                } else {
                    //不存在则,则构建一个map,同时将isEnd设置为0,因为他不是最后一个
                    newWorMap = new HashMap<>();
                    //不是最后一个
                    newWorMap.put("isEnd", "0");
                    temp.put(keyChar, newWorMap);
                    temp = newWorMap;
                }
                //最后一个
                if (i == key.length() - 1) {
                    temp.put("isEnd", "1");
                }
            }
        }
    }

    /**
     * 判断文字是否包含敏感字符
     * <p>
     * 文本
     * <p>
     * 若包含返回true,否则返回false
     */
    public static boolean contains(String txt) {
        boolean flag = false;
        for (int i = 0; i < txt.length(); i++) {
            int matchFlag = checkSensitiveWord(txt, i); //判断是否包含敏感字符
            if (matchFlag > 0) {//大于0存在,返回true
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 检查文字中是否包含敏感字符,检查规则如下:
     *
     * @param txt
     * @param beginIndex
     * @return 如果存在, 则返回敏感词字符的长度, 不存在返回0
     */
    private static int checkSensitiveWord(String txt, int beginIndex) {
        //敏感词结束标识位:用于敏感词只有1位的情况
        boolean flag = false;
        //匹配标识数默认为0
        int matchFlag = 0;
        char word;
        Map nowMap = sensitiveWordMap;
        for (int i = beginIndex; i < txt.length(); i++) {
            word = txt.charAt(i);
            //获取指定key
            nowMap = (Map) nowMap.get(word);
            if (nowMap != null) {//存在,则判断是否为最后一个
                //找到相应key,匹配标识+1
                matchFlag++;
                //如果为最后一个匹配规则,结束循环,返回匹配标识数
                if ("1".equals(nowMap.get("isEnd"))) {
                    //结束标志位为true
                    flag = true;
                }
            } else {//不存在,直接返回
                break;
            }
        }
        if (matchFlag < 2 || !flag) {//长度必须大于等于1,为词
            matchFlag = 0;
        }
        return matchFlag;
    }

    /**
     * 获取文字中的敏感词
     * <p>
     * txt文字
     */
    public static String getSensitiveWord(String txt) {
        List<String> sensitiveWordList = new ArrayList();
        for (int i = 0; i < txt.length(); i++) {
            //判断是否包含敏感字符
            int length = checkSensitiveWord(txt, i);
            if (length > 0) {//存在,加入list中
                sensitiveWordList.add(txt.substring(i, i + length));
                i = i + length - 1;//减1的原因,是因为for会自增
            }
        }
        for (String str : sensitiveWordList) {
            txt = txt.replace(str, getStars(str.length()));
        }
        return txt;
    }

    public static String getStars(Integer count){
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < count; i++) {
            sb.append("*");
        }
        return sb.toString();
    }


    /**
     * context是要校验的内容。返回结果是list，为空说明没有敏感词
     *
     * @param context
     * @return
     */
    public static String checkTxt(String context) {
        initContext();
        //包含敏感词返回所有敏感词数据
        return getSensitiveWord(context);
    }

    public static void main(String[] args) {
//        System.out.println(System.getProperty("user.dir"));
//        System.out.println(checkTxt("卖淫嫖娼杀人犯法共产党国民党法轮 大法"));
        LocalDate now = LocalDate.now();
        LocalDateTime ldt = now.atStartOfDay();
        System.out.println(ldt);
    }
}
