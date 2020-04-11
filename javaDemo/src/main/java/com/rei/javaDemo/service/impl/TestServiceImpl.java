package com.rei.javaDemo.service.impl;

import com.rei.javaDemo.mapper.TestMapper;
import com.rei.javaDemo.model.TestParam;
import com.rei.javaDemo.service.TestService;
import com.rei.javaDemo.utils.HttpUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Resource
    private TestMapper testMapper;
    /**
     * 数据库操作的演示
     */
    @Override
    public void dateBaseTest() {
        List<TestParam> list = testMapper.pageList(1,10);
        HttpUtil.sendGet("",null);
        int a = 0;
    }
}
