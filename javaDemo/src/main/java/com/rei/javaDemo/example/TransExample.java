package com.rei.javaDemo.example;

import com.rei.javaDemo.mapper.TestMapper;
import com.rei.javaDemo.model.TestParam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 事务的测试
 */
@Transactional
@Service
public class TransExample {

    @Resource
    private TestMapper testMapper;

    public void save(){
        TestParam param = new TestParam();
        param.setName("trans1");
        testMapper.insert(param);
        int a = 1/0;
    }
}
