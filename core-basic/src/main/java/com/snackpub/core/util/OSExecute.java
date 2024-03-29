//: net/mindview/util/OSExecute.java
// Run an operating system command
// and send the output to the console.
package com.snackpub.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class OSExecute {
  public static void command(String command) {
    String workDir = "F:\\Work\\JavaBasicStudy\\core-basic\\src\\main\\java\\com\\snackpub\\core\\io";
    boolean err = false;
    try {
      Process process =
        new ProcessBuilder(command.split(" ")).directory(new File(workDir)).start();
//      ProcessBuilder processBuilder = new ProcessBuilder();
//      processBuilder.directory(new File(workDir)); // 切换工作目录
//      processBuilder.command()

      BufferedReader results = new BufferedReader(
        new InputStreamReader(process.getInputStream()));
      String s;
      while((s = results.readLine())!= null)
        System.out.println(s);
      BufferedReader errors = new BufferedReader(
        new InputStreamReader(process.getErrorStream(), StandardCharsets.UTF_8 /*Charset.forName("utf-8")*/));

      // Report errors and return nonzero value
      // to calling process if there are problems:
      while((s = errors.readLine())!= null) {
        System.err.println(s);
        err = true;
      }
    } catch(Exception e) {
      // Compensate for Windows 2000, which throws an
      // exception for the default command line:
      if(!command.startsWith("CMD /C"))
        command("CMD /C " + command);
      else
        throw new RuntimeException(e);
    }
    if(err)
      throw new OSExecuteException("Errors executing " +
        command);
  }

  public static void command(String command,String workDir) {
    boolean err = false;
    try {
      Process process =
              new ProcessBuilder(command.split(" ")).directory(new File(workDir)).start();

      BufferedReader results = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
      String s;
      while((s = results.readLine())!= null)
        System.out.println(s);
      BufferedReader errors = new BufferedReader(
              new InputStreamReader(process.getErrorStream(), Charset.forName("gbk")));

      // Report errors and return nonzero value
      // to calling process if there are problems:
      while((s = errors.readLine())!= null) {
        System.err.println(s);
        err = true;
      }
    } catch(Exception e) {
      // Compensate for Windows 2000, which throws an
      // exception for the default command line:
      if(!command.startsWith("CMD /C"))
        command("CMD /C " + command);
      else
        throw new RuntimeException(e);
    }
    if(err)
      throw new OSExecuteException("Errors executing " +
              command);
  }
} ///:~
