package com.wkl.myclientone.client.fallback;

import com.wkl.myclientone.client.FetchTwoService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FetchTwoServiceFallback implements FetchTwoService {
    @Override
    public String getClientTwo(Map<String, Object> param) {
        return null;
    }

    @Override
    public String postTestTwo(Map<String, Object> param) {
        System.err.println("======================进入了get的熔断=====================");
        return null;
    }
}
