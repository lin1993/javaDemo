package com.rei.javaDemo.spring;

import com.rei.javaDemo.model.ParamTestModel;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class ModelInvok {
    public static void main(String[] args) {
        List<ParamTestModel> lt = new LinkedList<>();
        ParamTestModel p1 = new ParamTestModel();
        p1.setName("wkl");
        lt.add(p1);

        List<ParamTestModel> lt2 = lt.stream().map(e->{
            ParamTestModel p2 = new ParamTestModel();
            p2.setName("wkl" + e.getName());
            return p2;
        }).collect(Collectors.toList());

        String a = "a";
        String b = "b";

        a.concat(b);


        Class c = TestInvok.class;
        Field[] fs =  c.getDeclaredFields();

        Method[] m = c.getMethods();
        Method m0 = m[0];
        ParameterizedType t = (ParameterizedType)m0.getGenericReturnType();
        t.getRawType().getTypeName();
        String wai = t.getRawType().getTypeName();
        Type[] typesto = t.getActualTypeArguments();
        // 如果第一个不为空
        ParameterizedType t2 = (ParameterizedType)typesto[0];
        String wai2 = t2.getRawType().getTypeName();

        Type[] types3 = t2.getActualTypeArguments();
        types3.getClass();

        typesto[0].getTypeName();
            fs[0].getAnnotatedType();
        int a2 = 1;
    }
}
