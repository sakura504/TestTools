//package com.hq.test.utils;
//
//import org.bytedeco.javacpp.opencv_core;
//import org.bytedeco.javacpp.opencv_core.IplImage;
//import org.bytedeco.javacv.FFmpegFrameGrabber;
//import org.bytedeco.javacv.Frame;
//import org.bytedeco.javacv.FrameGrabber.Exception;
//import org.bytedeco.javacv.Java2DFrameConverter;
//import org.bytedeco.javacv.OpenCVFrameConverter;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.*;
//
///**
// * @author xiaoqiang
// * @Description javacv获取视频第一帧图片工具类
// * @date 2019/5/8 10:38
// */
//public class FrameGrabberKit {
//
//    public static void main(String[] args) throws Exception {
//// 视频路径换成自己的http://www.baidu.com/group1/M00/00/9E/rBIuClzSQHaAPClMAA5OVT6OM7w328.mp4
//
//        randomGrabberFFmpegImage(
//                "http://192.168.1.253:61400/down/app/download?fileId=79c647f02d69452d5bd80796ea3a7bcc&appToken=cTO4AEzMyBkjN0CgTN2CETL98JiMiFojIk8lkcl9NXdi9wiI2AUDNiAojI065Wdv6N2Yh5Jye",
//                "111"
//        );
//    }
//
//    /**
//     * @param filePath       视频路径
//     * @param fileName 图片名称
//     * @throws Exception
//     */
//    public static MultipartFile randomGrabberFFmpegImage(String filePath, String fileName)
//            throws Exception {
//
//        FFmpegFrameGrabber ff = FFmpegFrameGrabber.createDefault(filePath);
//        ff.start();
//        String rotate = ff.getVideoMetadata("rotate");
//        Frame f;
//        f = ff.grabImage();
//        IplImage src = null;
//        if (null != rotate && rotate.length() > 1) {
//            OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
//            src = converter.convert(f);
//            f = converter.convert(rotate(src, Integer.valueOf(rotate)));
//        }
//        MultipartFile multipartFile = doExecuteFrame(f, fileName);
//        ff.stop();
//
//        return multipartFile;
//    }
//
//    /*
//     * 旋转角度的
//     */
//    public static IplImage rotate(IplImage src, int angle) {
//        IplImage img = IplImage.create(src.height(), src.width(), src.depth(), src.nChannels());
//        opencv_core.cvTranspose(src, img);
//        opencv_core.cvFlip(img, img, angle);
//        return img;
//    }
//
//    public static MultipartFile doExecuteFrame(Frame f, String fileName){
//        MultipartFile multipartFile = null;
//        if (null == f || null == f.image) {
//            return null;
//        }
//        Java2DFrameConverter converter = new Java2DFrameConverter();
//        BufferedImage bi = converter.getBufferedImage(f);
//        try {
//            //创建一个ByteArrayOutputStream
//            ByteArrayOutputStream os = new ByteArrayOutputStream();
//            //把BufferedImage写入ByteArrayOutputStream
//            ImageIO.write(bi, "jpg", os);
//            //ByteArrayOutputStream转成InputStream
//            InputStream input = new ByteArrayInputStream(os.toByteArray());
//            //InputStream转成MultipartFile
//            multipartFile = new MockMultipartFile(fileName, fileName + ".jpg", "text/plain", input);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return multipartFile;
//    }
//}
