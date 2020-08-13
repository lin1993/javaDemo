package com.rei.javaDemo.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 汉子转拼音
 */
public class ChineseToSpell {
    /**
     * 获取汉字串拼音首字母，英文字符不变
     * @param chinese 汉字串
     * @return 汉语拼音首字母
     */
    public static String getFirstSpell(String chinese) {
        // 用StringBuffer（字符串缓冲）来接收处理的数据
        StringBuffer sb = new StringBuffer();
        //字符串转换为字截数组
        char[] arr = chinese.toCharArray();
        //创建转换对象
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        //转换类型（大写or小写）
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        //定义中文声调的输出格式
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < arr.length; i++) {
            //判断是否是汉子字符
            if (arr[i] > 128) {
                try {
                    // 提取汉字的首字母
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);
                    if (temp != null) {
                        sb.append(temp[0].charAt(0));
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                // 如果不是汉字字符，直接拼接
                sb.append(arr[i]);
            }
        }
        return sb.toString().replaceAll("\\W", "").trim();
    }
}
