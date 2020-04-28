package com.snackpub.office.sample;

import lombok.SneakyThrows;

import java.io.File;
import java.net.URL;

/**
 * @author snackpub
 * @date 2020/4/22
 */
public class Sample {

    @SneakyThrows
    public static void main(String[] args) {
        String localDocPaht = "E:\\sample.docx";
        URL resource = Thread.currentThread().getContextClassLoader().getResource(localDocPaht);
        WordUtil wordUtil = new WordUtil(localDocPaht);
        wordUtil.init();
//        File file = new File(resource.getFile());
//        wordUtil.handlerWordFile(file);

    }
}
