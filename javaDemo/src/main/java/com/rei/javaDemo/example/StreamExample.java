package com.rei.javaDemo.example;

import com.rei.javaDemo.model.TestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * java 8流处理示例
 */
public class StreamExample {

    private static List<TestParam> list = new ArrayList<>();

    public StreamExample(){
        TestParam p1 = new TestParam();
        p1.setId(1);
        p1.setName("test1");
        p1.setSex("男");
        TestParam p2 = new TestParam();
        p1.setId(1);
        p1.setName("test2");
        p1.setSex("男");
        TestParam p3 = new TestParam();
        p1.setId(1);
        p1.setName("test3");
        p1.setSex("男");
        list.add(p1);
        list.add(p2);
        list.add(p3);
    }

    /**
     * 使用分页的示例，请注意分页的计算部分
     */
    public static void usePage(){
        List<String> stringList = new ArrayList<>();
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");
        stringList.add("4");
        stringList.add("5");
        stringList.add("6");
        stringList.add("7");
        stringList.add("8");
        stringList.add("9");
        stringList.add("10");
        int allListSize = stringList.size();
        int pageNum = allListSize / 3;
        if(allListSize % 3 != 0 ){
            pageNum = pageNum + 1;
        }
        for (int i = 1; i <= pageNum; i++) {
            List<String> strPage = page(stringList,3,i);
            System.out.println("=================当前第["+i+"]页");
            System.out.println(strPage.toString());
        }
    }
    /**
     * 内存分页示例
     * @param sour 要分页的集合
     * @param pageSize 每页条数
     * @param pageNum 页码，最小为1
     * @return 分页过后的数据
     */
    public static List<String> page(List<String> sour, int pageSize, int pageNum) {
        List<String> res = sour.stream().skip(pageSize*(pageNum-1)).limit(pageSize).collect(Collectors.toList());
        return res;
    }

    /**
     * 将一个集合中的某个字段取出来
     * @return
     */
    public static String streamMap(){
        // 这两种方式是等价的
        List<String> list1 = list.stream().map(TestParam::getName).collect(Collectors.toList());
        List<String> list2 = list.stream().map(e -> e.getName()).collect(Collectors.toList());
        return "";
    }

    /**
     * 集合分组
     * @return
     */
    public static void streamMGroup(){
        // 根据某个字段分组，然后可以方便根据分组key取出对应的数据
        Map<String,List<TestParam>> map = list.stream().collect(Collectors.groupingBy(TestParam::getSex));
    }
}
