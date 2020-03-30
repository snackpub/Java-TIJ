package com.snackpub.core.io;

import java.io.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author snakcpub
 */
public class IoTest {

    /**
     * 左移右移运输demo
     */
    public static void bitOpener() {
//         1 = 0000 0001 << 4  = 0001 0000 = 1 * 2^4 =16
//         1 = 0000 0001 << 5  = 0010 0000 = 1 * 2^5 =32
//         1 = 0000 0001 >> 4  = 0000 0000 = 0
//         除0以外的任何数的0次幂都等于1
        System.out.println(1 >> 4);
        System.out.println(1 << 5);
    }

    /**
     * 字节流操作
     */
    public static void inputStreamOpener() {
        // 读写文件
        InputStream fileInputStream = null;
        Reader reader = null;
        String filePath = "E:\\test.txt";
        try {
            fileInputStream = new FileInputStream(filePath);
            reader = new InputStreamReader(fileInputStream, "gbk");
//            BufferedInputStream bis = new BufferedInputStream(fileInputStream);
//            FilterInputStream dis = new DataInputStream(fileInputStream);
//            FilterInputStream pis = new PushbackInputStream(fileInputStream);
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            while (br.read() != -1) {
                String s = br.readLine();
                System.out.println(s);
            }
            int length;
            StringBuffer sb = new StringBuffer();
            while ((length = reader.read()) != -1) {
                sb.append((char) length);
            }
//            System.out.println(sb);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 字节输出流操作
     */
    public static void outputStreamOpener() {
        String filePath = "E:\\test.txt";
        try {
            // 构建文件输出流对象
            OutputStream fileOutputStream = new FileOutputStream(filePath, true);
            // Creates an OutputStreamWriter that uses the named charset.
            Writer writer = new OutputStreamWriter(fileOutputStream, "gbk");

            FileWriter fw = new FileWriter(filePath, true);
            // 该类不仅能够提供缓冲，提高性能，还有写入跨平台的功能 newLine()
            BufferedWriter bufferedWriter = new BufferedWriter(fw);
            bufferedWriter.append("bufferedWriter newLine");
            bufferedWriter.newLine();

            // 写入到缓冲区
            writer.append("追加的数据");
            // 换行
            writer.append("\r\n");
            // 刷新缓存冲,写入到文件,如果下面已经没有写入的内容了,直接close也会写入
            writer.append("English");
            // 换行
            writer.append("\r\n");
//             关闭写入流,同时会把缓冲区内容写入文件,所以上面的注释掉
            writer.close();
//             关闭输出流,释放系统资源
            fileOutputStream.close();

            // 关闭写入流
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {


//        ObjectOutputStreamTest();
//        readerWriteTest();
//        objSeriealizable();
//        readSeriealizable();
    }

    /**
     * 对象序列化
     */
    public static void ObjectOutputStreamTest() {
        String filePath = "E:\\test.txt";
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject("toDay2");
            oos.writeObject(new Date());

            oos.flush();
            oos.close();

            InputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            String today = (String) ois.readObject();
            Date date = (Date) ois.readObject();
            System.out.println(today + " " + date);

            fis.close();
        } catch (IOException | java.lang.ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 字符流不使用close方法的话，则不会输出任何内容，说明字符流用的是缓冲区
     */
    public static void readerWriteTest() {
        String filePath = "E:\\test.txt";
        try {
            // 字符读流
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            int len;
            while ((len = br.read()) != -1) {
                System.out.println((char) len);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对象序列化
     */
    public static void objSerializable() {
        String filePath = "E:\\testModel.bat";
        TestModel testModel = new TestModel();
        testModel.setName("Snackpub");
        testModel.setSex("man");
        testModel.setMoney(BigDecimal.valueOf(0.1));
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(filePath));
            oos.writeObject(testModel);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 反序列化的对象
     */
    public static void readSerializable() {
        String filePath = "E:\\testModel.bat";

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
            TestModel testModel = (TestModel) ois.readObject();
            System.out.println(testModel);
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
