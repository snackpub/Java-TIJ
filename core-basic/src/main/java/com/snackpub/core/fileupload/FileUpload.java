package com.snackpub.core.fileupload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author snackpub
 */
@Slf4j
@Controller
@RequestMapping("/upload")
public class FileUpload {

    @GetMapping("/toUpload")
    public String toUpload() {
        log.info("toUpload");
        return "fileUpload.html";
    }

    @PostMapping(value = "/image")
    @ResponseBody
    public String uploadImage(@RequestParam("file2") MultipartFile file, HttpServletRequest request) {
        System.out.println(file);
        return "" + file;
    }
}
