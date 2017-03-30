/**
 * Created on 2006-09-08
 */
package com.idealunited.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

public final class FileUtil {
    /**
     * Default constructor.
     *
     */
    private FileUtil() {
    }

    /**
     * 从指定的文件路径中读取文件.
     * @param filePath 文件的全路径名,包括文件名
     * @return String 文件的内容
     * @throws IOException
     */
    public static String read(final String filePath) throws IOException {
        File file = new File(filePath);
        InputStream is = new FileInputStream(file);
        String result = read(is);
        is.close();
        return result;
    }

    /**
     * 从一个输入流中读取文件内容.
     * @param is InputStream object
     * @return String The content in the InputSream.
     * @throws IOException
     */
    public static String read(InputStream is) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(is);
        StringBuffer buf = new StringBuffer();
        int size = 0;
        while ((size = bis.available()) > 0) {
            byte[] temp = new byte[size];
            bis.read(temp);
            buf.append(new String(temp));
        }
        return buf.toString();
    }

    /**
     * 保存字符串到指定的文件.
     * @param filePath 文件路径,包括文件名
     * @param content 文件内容
     * @throws IOException
     */
    public static void save(final String filePath, final String content)
    throws IOException {
        File file = new File(filePath);
        OutputStream os = new FileOutputStream(file);
        save(os, content);
        os.close();
    }

    /**
     * 保存字符串到输出流中.
     * @param os 输出流
     * @param content 内容
     * @throws IOException
     */
    public static void save(final OutputStream os, final String content)
    throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(os);
        bos.write(content.getBytes());
        bos.flush();
    }

    /**
     * 得到java class存放的目录
     * @return String
     */
    public static String getRealClassRootPath() {
        URI uri = null;
        try {
            uri = FileUtil.class.getResource("FileUtil.class").toURI();
        } catch (URISyntaxException e1) {
            //TODO
        }
        String scheme = uri.getScheme();
        String path = uri.getPath();
        int index = path.indexOf("/com/");
        if (index == -1) {
            index = path.indexOf("classes") + "classes".length();
        }
        path = path.substring(0, index);
        return path;
    }
}
