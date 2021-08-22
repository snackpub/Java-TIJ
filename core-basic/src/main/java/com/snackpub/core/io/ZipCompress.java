package com.snackpub.core.io;//: io/ZipCompress.java
// Uses Zip compression to compress any
// number of files given on the command line.
// {Args: ZipCompress.java}

import java.util.zip.*;
import java.io.*;
import java.util.*;

import static com.snackpub.core.util.Print.print;

/**
 * 用zip进行多文件保存
 */
public class ZipCompress {
    public static void main(String[] args)
            throws IOException {
        FileOutputStream f = new FileOutputStream("test.zip");
        // Checksum类来计算和校验文件的校验和的方法，一共有2中Checksum类型：Adler32（它快一些）和CRC32(它慢一些，但更准确)
        // 为了读取和校验，我们必须拥有对与之相关联的checksum对象的访问权限。
        CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
        ZipOutputStream zos = new ZipOutputStream(csum);
        BufferedOutputStream out = new BufferedOutputStream(zos);
        // 往zip写注释，但是并未提供恢复的功能
        zos.setComment("A test of Java Zipping");
        // No corresponding getComment(), though.
        for (String arg : args) {
            print("Writing file " + arg);
            BufferedReader in = new BufferedReader(new FileReader(arg));
            // 对于每个要加入压缩档案的文件，都必须调用putNextEntry，将其传递给一个ZipEntry对象
            // ZipEntry是一个功能很广泛的接口，尽管zip提供了设置密码，java的zip类库不支持设置密码
            zos.putNextEntry(new ZipEntry(arg));
            int c;
            while ((c = in.read()) != -1)
                out.write(c);
            in.close();
            out.flush();
        }
        out.close();
        // Checksum valid only after the file has been closed!
        print("Checksum: " + csum.getChecksum().getValue());
        // Now extract the files:
        print("Reading file");
        FileInputStream fi = new FileInputStream("test.zip");
        CheckedInputStream csumi =
                new CheckedInputStream(fi, new Adler32());
        ZipInputStream in2 = new ZipInputStream(csumi);
        BufferedInputStream bis = new BufferedInputStream(in2);
        ZipEntry ze;
        while ((ze = in2.getNextEntry()) != null) {
            print("Reading file " + ze);
            int x;
            while ((x = bis.read()) != -1)
                System.out.write(x);
        }
        if (args.length == 1)
            print("Checksum: " + csumi.getChecksum().getValue());
        bis.close();
        // Alternative way to open and read Zip files:
        ZipFile zf = new ZipFile("test.zip");
        Enumeration e = zf.entries();
        while (e.hasMoreElements()) {
            ZipEntry ze2 = (ZipEntry) e.nextElement();
            print("File: " + ze2);
            // ... and extract the data as before
        }
        /* if(args.length == 1) */
    }
} /* (Execute to see output) *///:~
