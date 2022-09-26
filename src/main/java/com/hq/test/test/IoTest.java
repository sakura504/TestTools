package com.hq.test.test;

import org.junit.Test;

import java.io.*;

public class IoTest {

    private static String fileUrl = "C:\\Users\\PC\\Desktop\\新建文本文档.txt";

    public static void main(String[] args) throws FileNotFoundException, IOException {
        //writFileTest();

        //readFileTest();

        //test();
    }

    private static void writFileTest() throws FileNotFoundException,
            IOException {
        // 创建文件对象
        File file = new File(fileUrl);
        // 创建文件输出流
        FileOutputStream fos = new FileOutputStream(file,true);
        fos.write("\r\n".getBytes());
        fos.write('g');
        fos.write('z');
        fos.write('i');
        fos.write('t');
        fos.write('c');
        fos.write('a');
        fos.write('s');
        fos.write('t');
        fos.close();
    }

    private static void readFileTest() throws FileNotFoundException,
            IOException {
        // 创建文件对象
        File file = new File(fileUrl);
        // 创建文件输入流
        FileInputStream fis = new FileInputStream(file);
//        // 有对多长，就读多少字节。
//        for (int i = 0; i < file.length(); i++) {
//            System.out.print((char) fis.read());
//        }
        // 字节数组
        byte[] b = new byte[2];
        int i2 = 0;
        //  一次读取一个字节数组
        while ((i2 = fis.read(b)) != -1) {
            System.out.print(new String(b, 0, i2) + " ");// AB CD
        }
        fis.close();
    }

    @Test
    public void test() throws IOException {
        FileInputStream inputStream = new FileInputStream("C:\\Users\\PC\\Desktop\\商城图片\\诸葛连弩.mp4");
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        FileOutputStream outputStream = new FileOutputStream("诸葛连弩.mp4");
        BufferedOutputStream bos = new BufferedOutputStream(outputStream);
        int len;
        byte[] bs = new byte[1024 * 1024];
        // 开始时间
        long begin = System.currentTimeMillis();
        while ((len = bis.read(bs)) != -1) {
            bos.write(bs, 0, len);
        }
        // 用时毫秒
        System.out.println(System.currentTimeMillis() - begin);// 78
        bis.close();
        bos.close();
    }
}
