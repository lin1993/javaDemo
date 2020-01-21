package com.rei.javaDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 零碎测试的入口main函数
 */
public class RunTest {

    public static void main(String[] args) {
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
        if(allListSize % 4000 != 0 ){
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
    private static List<String> page(List<String> sour, int pageSize, int pageNum) {
        List<String> res = sour.stream().skip(pageSize*(pageNum-1)).limit(pageSize).collect(Collectors.toList());
        return res;
    }
}
