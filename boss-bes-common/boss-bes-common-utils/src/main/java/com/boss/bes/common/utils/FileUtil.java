package com.boss.bes.common.utils;

import java.io.*;
import java.math.BigInteger;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * 对文件的常见操作，不包括上传下载
 * @author Song
 * @date 2019/8/13
 */
public final class FileUtil {

    /**
     * Buffer的大小
     */
    private static Integer BUFFER_SIZE = 1024 * 1024 * 10;

    /**
     * MessageDigest 类为应用程序提供信息摘要算法的功能，如 MD5 或 SHA 算法。
     * 信息摘要是安全的单向哈希函数，它接收任意大小的数据，输出固定长度的哈希值。
     * MessageDigest 对象开始被初始化。该对象通过使用 update 方法处理数据。
     * 任何时候都可以调用 reset 方法重置摘要。
     * 一旦所有需要更新的数据都已经被更新了，应该调用 digest 方法之一完成哈希计算。
     * 对于给定数量的更新数据，digest 方法只能被调用一次。digest 被调用后MessageDigest 对象被重新设置成其初始状态
     */
    public static MessageDigest MD5 = null;

    static {
        try {
            MD5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ne) {
            ne.printStackTrace();
        }
    }

    /**
     * 获取文件的md5
     *
     * @param file
     * @return
     */
    public static String fileMd5(File file) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) {
                MD5.update(buffer, 0, length);
            }
            return new BigInteger(1, MD5.digest()).toString(16);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取文件的行数
     *
     * @param file 统计的文件
     * @return 文件行数
     */
    public final static int countLines(File file)  throws IOException {
        FileReader fr = null;
        LineNumberReader lnr = null;
        try  {
            fr =  new FileReader(file);
            lnr =   new LineNumberReader(fr);
            long fileLength = file.length();
            lnr.skip(fileLength);
            return lnr.getLineNumber();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            // closes the stream and releases system resources
            if(fr!=null) {
                fr.close();
            }
            if(lnr!=null) {
                lnr.close();
            }
        }
        return 0;
    }

    /**
     * 以列表的方式获取文件的所有行
     *
     * @param file 需要出来的文件
     * @return 包含所有行的list
     */
    public final static List<String> lines(File file) throws IOException {
        List<String> list = new ArrayList<String>();
        FileReader fr =null;
        BufferedReader br = null;
        try
         {
             fr = new FileReader(file);
             br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(fr != null){
                fr.close();
            }
            if(br != null){
                br.close();
            }
        }
        return list;
    }

    /**
     * 快速清空一个超大的文件
     *
     * @param file 需要处理的文件
     * @return 是否成功
     */
    public final static boolean cleanFile(File file) throws IOException {
        FileWriter fw = null;
        try
         {
            fw = new FileWriter(file);
            fw.write("");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(fw != null){
                fw.close();
            }
        }
        return false;
    }

    /**
     * 获取文件的Mime类型
     *
     * @param file 需要处理的文件
     * @return 返回文件的mime类型
     * @throws IOException
     */
    public final static String mimeType(String file) throws IOException {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        return fileNameMap.getContentTypeFor(file);
    }

    /**
     * 获取文件扩展名
     *
     * @param fileName
     *            文件名
     * @return
     */
    public static String getExtention(String fileName) {
        int pos = fileName.lastIndexOf(".");
        return fileName.substring(pos);
    }

    /**
     * 复制文件
     *
     * @param resourcePath 源文件
     * @param targetPath   目标文件
     * @return 是否成功
     */
    public final static boolean copy(String resourcePath, String targetPath) {
        File file = new File(resourcePath);
        return copy(file, targetPath);
    }

    /**
     * 复制文件
     * 通过该方式复制文件文件越大速度越是明显
     *
     * @param file       需要处理的文件
     * @param targetFile 目标文件
     * @return 是否成功
     */
    public final static boolean copy(File file, String targetFile) {
        try{
            FileInputStream fin = new FileInputStream(file);
            FileOutputStream fout = new FileOutputStream(new File(targetFile));
            FileChannel in = fin.getChannel();
            FileChannel out = fout.getChannel();
            //设定缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
            while (in.read(buffer) != -1) {
                //准备写入，防止其他读取，锁住文件
                buffer.flip();
                out.write(buffer);
                //准备读取。将缓冲区清理完毕，移动文件内部指针
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除一个文件
     *
     * @param file 需要处理的文件
     * @return 是否成功
     */
    public final static boolean deleteFile(File file) {
        return file.delete();
    }

    /**
     * 删除目录
     * @param dir
     * @return
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }


}
