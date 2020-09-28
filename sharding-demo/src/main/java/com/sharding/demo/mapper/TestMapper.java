package com.sharding.demo.mapper;

import com.sharding.demo.model.TestParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    /**
     * [新增]
     * @author rei
     * @date 2020/01/15
     **/
    int insert(TestParam tTest);

    /**
     * [刪除]
     * @author rei
     * @date 2020/01/15
     **/
    int delete(int id);

    /**
     * [更新]
     * @author rei
     * @date 2020/01/15
     **/
    int update(TestParam tTest);

    /**
     * [查询] 根据主键 id 查询
     * @author rei
     * @date 2020/01/15
     **/
    TestParam load(int id);

    /**
     * [查询] 分页查询
     * @author rei
     * @date 2020/01/15
     **/
    List<TestParam> pageList(int offSet, int pageSize);

    /**
     * [查询] 分页查询 count
     * @author rei
     * @date 2020/01/15
     **/
    int pageListCount(int offset, int pagesize);
}
