package com.snackpub.core.redis.aop.controller;

import com.snackpub.core.redis.aop.annotation.ControllerWebLog;
import com.snackpub.core.redis.aop.annotation.DistributeLock;
import com.snackpub.core.redis.aop.common.model.BaseRequest;
import com.snackpub.core.redis.aop.common.model.BaseResponse;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * Web日志测试
 * @author ganchaoyang
 */
@RestController
@RequestMapping("/weblog")
public class WebLogTestController {

    @GetMapping("/get-test")
    @ControllerWebLog(name = "GET请求测试接口", intoDb = true)
    public String hello(@RequestParam("name") String name){
        return name;
    }

    @PostMapping("/post-test")
    @ControllerWebLog(name = "接口日志POST请求测试", intoDb = true)
    @DistributeLock(key = "post_test_#{baseRequest.channel}", timeout = 10)
    public BaseResponse postTest(@RequestBody @Valid BaseRequest baseRequest, BindingResult bindingResult) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return BaseResponse.addResult();
    }

}
