package com.qunjie.common.util;

import com.esotericsoftware.reflectasm.MethodAccess;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ReflectHelperUtils {

    public static <U> Object getVbyKey(U u, String property) {

        MethodAccess methodAccess = MethodAccess.get(u.getClass());

        String nproperty = "get" + toUpperCaseFirstOne(property);

        int mIndex = methodAccess.getIndex(nproperty);

        Object object = methodAccess.invoke(u, mIndex);

        return object;
    }

    /**
     * 设置普通属性值
     *
     * @param <T>      需要赋值的实体
     * @param t        实体对象
     * @param property 需要赋值的属性
     * @param clazz    实体类型
     * @param value    赋值
     */
    public static void setBasicVbKey(Object obj, String property, Object value) {
        MethodAccess methodAccess = MethodAccess.get(obj.getClass());
        String nproperty = "set" + toUpperCaseFirstOne(property);
        int mIndex = methodAccess.getIndex(nproperty);
        methodAccess.invoke(obj, mIndex, value);
    }

    private static String toUpperCaseFirstOne(String property) {
        if (Character.isUpperCase(property.charAt(0))) {
            return property;
        } else {
            return (new StringBuilder()).append(Character.toUpperCase(property.charAt(0))).append(property.substring(1))
                    .toString();
        }
    }

    /**
     * 获得所有属性（包括父类）
     *
     * @param clazz
     * @return
     */
    public static Field[] getFields(Class<?> clazz) {
        List<Field> fieldsArr = new ArrayList<Field>();
        while (clazz != null) {

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                fieldsArr.add(field);
            }

            clazz = clazz.getSuperclass();
        }
        return fieldsArr.toArray(new Field[fieldsArr.size()]);
    }

    /**
     * Returns the first {@link Field} in the hierarchy for the specified name
     */
    public static Field getField(Class<?> clazz, String name) {
        Field field = null;
        while (clazz != null && field == null) {
            try {
                field = clazz.getDeclaredField(name);
            } catch (Exception e) {
            }
            clazz = clazz.getSuperclass();
        }
        return field;
    }

    /**
     * 获取属性值（包括父类）
     *
     * @param obj
     * @param name
     * @return
     */
    public static Object getFieldValue(Object obj, String name) {
        try {
            Field field = getField(obj.getClass(), name);
            if (field != null) {
                field.setAccessible(true);
                return field.get(obj);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 设置属性值（包括父类）
     *
     * @param obj
     * @param name
     * @param value
     */
    public static void setFieldValue(Object obj, String name, Object value) {
        try {
            Field field = getField(obj.getClass(), name);
// 设置对象的访问权限，保证对private的属性的访问
            if (field != null) {
                field.setAccessible(true);
                field.set(obj, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}