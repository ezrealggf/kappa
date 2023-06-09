package org.ashe.kappa.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SuppressWarnings("all")
public class AnnotationWorld {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        @SuppressWarnings("unused")
        Cat sweetheart = new Cat("sweetheart");

        Cat myCat = new Cat("sweetheart");
        if (myCat.getClass().isAnnotationPresent(VeryImportant.class)) {
            System.out.println("this thing is very important :)");
        } else {
            System.out.println("this thing is not very important :(");
        }

        /*
         * for循环结构
         * for(元素类型 元素变量 ： 集合) {}
         */
        for (Method method : myCat.getClass().getDeclaredMethods()) {
            /*
             * The president was not present at the meeting.     总统没有出席该会议。
             */
            if (method.isAnnotationPresent(RunImmediately.class)) {
                RunImmediately annotation = method.getAnnotation(RunImmediately.class);
                for (int i = 0; i < annotation.times(); i++) {
                    method.invoke(myCat);
                }
            }
        }

        for (Field field : myCat.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(ImportantString.class)) {
                Object objValue = field.get(myCat);
                if (objValue instanceof String) {
                    String str = (String) objValue;
                    System.out.println(str.toUpperCase());
                }
            }
        }
    }
}
