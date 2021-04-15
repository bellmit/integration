package com.qunjie.mysql.args;

import com.google.common.base.CaseFormat;

import java.lang.reflect.Field;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.mysql.args.BaseEntity
 *
 * @author whs
 * Date:   2021/2/4  9:59
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
public class BaseEntity {

    public String _getkeyColumn(){
        return "indocno";
    }

    public String _getTableName(){
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,this.getClass().getSimpleName());
    }

    public Object _getKeyValue(){
        Field field = getField(this.getClass(),this._getkeyColumn());
        field.setAccessible(true);
        try {
            if (field.get(this) instanceof String){
                return Long.parseLong(String.valueOf(field.get(this)));
            }else {
                return (Long)field.get(this);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void _setKeyValue(Object value){
        Field field = getField(this.getClass(),_getkeyColumn());
        field.setAccessible(true);
        try {
            field.set(this,value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private Field getField(Class<?> aClass, String name) {
        Field field = null;
        while (aClass != null && field == null){
            try {
                field = aClass.getDeclaredField(name);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            aClass = aClass.getSuperclass();
        }
        return field;
    }
}
