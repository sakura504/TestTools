package com.hq.test.utils;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import ws.schild.jave.MultimediaInfo;
import ws.schild.jave.MultimediaObject;

import java.io.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lichaojie
 * @date 2022/3/17 10:31
 * @ClassName FileUtil
 **/
public class FileUtil {

    private static final List<String> VIDEO_SUFFIXS = Stream.of("mp4", "mov", "avi", "mkv", "m4v", "wmv",
            "asf", "asx", "rm", "rmvb", "3gp", "dat", "flv", "vob").collect(Collectors.toList());

    /**
     * 获取视频时长 * @param file 视频文件
     *
     * @return 时长（秒）
     */
    public static int getVideoDuration(MultipartFile file) {
        //视频时长
        int duration = 0;
        File tempFile = null;

        //判断文件类型
        if (!isVideo(file)){
            return duration;
        }

        try {
            tempFile = File.createTempFile(UUID.randomUUID().toString(), "." + FilenameUtils.getExtension(file.getOriginalFilename()));
            file.transferTo(tempFile);
            MultimediaObject multimediaObject = new MultimediaObject(tempFile);
            MultimediaInfo info = multimediaObject.getInfo();
            duration = (int) Math.ceil((double) info.getDuration() / 1000);
        } catch (Exception e) {
            System.out.println("获取视频时长失败");
        } finally {
            if (tempFile != null) {
                tempFile.delete();
            }
        }
        return duration;
    }

    /**
     * 判断文件是否是视频 * @param file 文件
     *
     * @return 是否是啊视频
     */
    public static boolean isVideo(MultipartFile file) {
        return VIDEO_SUFFIXS.contains(FilenameUtils.getExtension(file.getOriginalFilename()));
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("./src/main/resources/小熊.mp4");
        DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("file",
                MediaType.ALL_VALUE, true, file.getName());

        try (InputStream input = new FileInputStream(file); OutputStream os = fileItem.getOutputStream()) {
            IOUtils.copy(input, os);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid file: " + e, e);
        }

        MultipartFile multi = new CommonsMultipartFile(fileItem);

        System.out.println(getVideoDuration(multi));
    }

}