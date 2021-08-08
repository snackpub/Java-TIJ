//: net/mindview/util/OSExecute.java
// Run an operating system command
// and send the output to the console.
package com.snackpub.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 不打印标准的I/O流，而是返回List集合
 *
 * @author snackpub
 * @date 2021/8/8
 */
public class OSExecute2 {
  public static void command(String command,String workPath) {
    boolean err = false;
    Map<String, Object> message = new HashMap<>();
    List<String> dataList = new ArrayList<>();
    List<String> errorList = new ArrayList<>();
    try {
      Process process =
        new ProcessBuilder(command.split(" ")).directory(new File(workPath)).start();

      BufferedReader results = new BufferedReader(
        new InputStreamReader(process.getInputStream()));
      String s;
      while((s = results.readLine())!= null)
        // System.out.println(s);
        dataList.add(s);
      BufferedReader errors = new BufferedReader(
        new InputStreamReader(process.getErrorStream(), StandardCharsets.UTF_8 ));

      // Report errors and return nonzero value
      // to calling process if there are problems:
      while((s = errors.readLine())!= null) {
        // System.err.println(s);
        errorList.add(s);
        err = true;
      }
    } catch(Exception e) {
      // Compensate for Windows 2000, which throws an
      // exception for the default command line:
      if(!command.startsWith("CMD /C"))
        command("CMD /C " + command, workPath);
      else
        throw new RuntimeException(e);
    }
    if(err)
      throw new OSExecuteException("Errors executing " +
        command);
  }
} ///:~
