package com.wkl.myclientone.client;

import com.wkl.myclientone.client.fallback.FetchTwoServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@FeignClient(value = "myclient-two" ,fallback = FetchTwoServiceFallback.class)
public interface FetchTwoService {

    @GetMapping("/test/getTest")
    String getClientTwo(Map<String,Object> param);

    @PostMapping("/test/postTest")
    String postTestTwo(Map<String,Object> param);

}
