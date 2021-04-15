package com.qunjie.mysql;

import com.google.common.base.CaseFormat;
import com.qunjie.common.util.ReflectHelperUtils;
import com.qunjie.mysql.args.BaseEntity;
import org.apache.ibatis.jdbc.SQL;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Copyright (C),2020-2021,群杰印章物联网
 * FileName: com.qunjie.mysql.BaseSqlProvider
 *
 * @author whs
 * Date:   2021/2/4  9:58
 * Description:
 * History:
 * &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 * 修改人姓名           修改时间           版本号          描述
 */
public class BaseSqlProvider {

    public String insert(final BaseEntity entity) throws IllegalAccessException{
        return new SQL(){
            {
                INSERT_INTO(entity._getTableName());
                Field[] fields = ReflectHelperUtils.getFields(entity.getClass());
                Object getkeyColumn = entity._getkeyColumn();
                for (Field f : fields){
                    f.setAccessible(true);
                    String name = f.getName();
                    Object value = f.get(entity);
                    if (isBaseType(f.getType()) && value != null){
                        VALUES(getColName(name),"#{"+name+"}");
                    }
                }

            }
        }.toString();
    }

    public String update(final BaseEntity entity) throws IllegalAccessException{
        return new SQL(){{
            String getkeyColumn = entity._getkeyColumn();
            UPDATE(entity._getTableName());
            Field[] fields = ReflectHelperUtils.getFields(entity.getClass());
            for (Field field : fields){
                field.setAccessible(true);
                String fname = field.getName();
                Object fvalue = field.get(entity);
                if (isBaseType(field.getType()) && !fname.equals(getkeyColumn) && fvalue != null){
                    SET(getColName(fname)+" = #{"+fname+"}");
                }
            }
            WHERE(getkeyColumn + "= #{" + getkeyColumn+"}");
        }}.toString();
    }

    public String delete(final BaseEntity entity){
        return new SQL(){{
            String keyColumn = entity._getkeyColumn();
            DELETE_FROM(entity._getTableName());
            WHERE(keyColumn+"= #{" +keyColumn+"}");
        }}.toString();
    }

    private String getColName(String fieldName){
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,fieldName);
    }

    public String select(final BaseEntity entity){
        return new SQL(){{
            String getkeyColumn = entity._getkeyColumn();
            SELECT("*");
            FROM(entity._getTableName());
            WHERE(getkeyColumn + "= #{"+getkeyColumn+"}");
        }}.toString();
    }



    public boolean isBaseType(Class<?> classType){
        if (classType.equals(Integer.class) || classType.equals(Byte.class) || classType.equals(Long.class)
            || classType.equals(Double.class) || classType.equals(Float.class) || classType.equals(Character.class)
            || classType.equals(Short.class) || classType.equals(Boolean.class) || classType.equals(String.class)
            || classType.equals(Date.class) || classType.equals(BigDecimal.class)){
            return true;
        }
        return false;
    }


}
