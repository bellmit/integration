package com.qunjie.model;

import org.springframework.util.FileCopyUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * Author: momo
 * Date: 2018/5/7
 * Description:文件转为二进制
 */
public class BinUtil {

    public static final File FILE = new File("C:\\Users\\Administrator\\Desktop\\群杰.txt");

    public static void main(String[] args){
        fileToBinStr(FILE);
    }

    /**
     * 文件转为二进制数组
     * @param file
     * @return
     */
    public static byte[] fileToBinArray(File file){
        try {
            InputStream fis = new FileInputStream(file);
            byte[] bytes = FileCopyUtils.copyToByteArray(fis);
            return bytes;
        }catch (Exception ex){
            throw new RuntimeException("transform file into bin Array 出错",ex);
        }
    }

    /**
     * 文件转为二进制字符串
     * @param file
     * @return
     */
    public static String fileToBinStr(File file){
        try {
            InputStream fis = new FileInputStream(file);
            byte[] bytes = FileCopyUtils.copyToByteArray(fis);
            BASE64Encoder encoder = new BASE64Encoder();
            java.lang.String str = encoder.encode(bytes);
            System.out.println("str++++++++++++++++++++++"+str);
            return str;
        }catch (Exception ex){
            throw new RuntimeException("transform file into bin String 出错",ex);
        }
    }


    /**
     * 二进制字符串转文件
     * @param bin
     * @param fileName
     * @param parentPath
     * @return
     */
    public static File binToFile(String bin,String fileName,String parentPath){
        try {
            File fout = new File(parentPath,fileName);
            fout.createNewFile();
            byte[] bytes1 = bin.getBytes("ISO-8859-1");
            FileCopyUtils.copy(bytes1,fout);

            //FileOutputStream outs = new FileOutputStream(fout);
            //outs.write(bytes1);
            //outs.flush();
            //outs.close();

            return fout;
        }catch (Exception ex){
            throw new RuntimeException("transform bin into File 出错",ex);
        }
    }

    /**
     * 文件转为二进制数组
     * 等价于fileToBin
     * @param file
     * @return
     */
    public static byte[] getFileToByte(File file) {
        byte[] by = new byte[(int) file.length()];
        try {
            InputStream is = new FileInputStream(file);
            ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
            byte[] bb = new byte[2048];
            int ch;
            ch = is.read(bb);
            while (ch != -1) {
                bytestream.write(bb, 0, ch);
                ch = is.read(bb);
            }
            by = bytestream.toByteArray();
        } catch (Exception ex) {
            throw new RuntimeException("transform file into bin Array 出错",ex);
        }
        return by;
    }

    public void base64Encoder() {
        //文件复制
        String oldFilePath = "C:/OldFilepath/vertica9.pdf";
        String newFilePath = "C:/NewFilePath/vertica9.pdf";
        File fileOld = new File(oldFilePath);
        File fileNew = new File(newFilePath);
        fileNew.deleteOnExit();
        try {
            FileInputStream inputStream = new FileInputStream(fileOld);
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            byte[] by = new byte[oldFilePath.length()];
            byte[] array = new byte[1024];
            int ch=0;
            while ((ch=inputStream.read()) != -1) {
                arrayOutputStream.write(array, 0, ch);
            }
            by = arrayOutputStream.toByteArray();
            BASE64Encoder encoder = new BASE64Encoder();
            String str = encoder.encode(by);
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] newBy = decoder.decodeBuffer(str);
            FileOutputStream outputStream = new FileOutputStream(newFilePath);
            BufferedOutputStream bos = new BufferedOutputStream(outputStream);
            bos.write(newBy);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

