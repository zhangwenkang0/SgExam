package edu.numberone.studystar.jdbc;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;

public class ResultSetUtils
{
    /**
     * @author 
     * 用于将ResultSet结果集转化成实体类列表或者List<Map<String,Object>>结果集的工具类，
     * 用于调用存储过程返回游标结果集转换成实体列表或者List<Map<String,Object>>结果集
     * @param <T>
     */
    public static List<Map<String, Object>> toList(ResultSet rs)
    {
        Map<String, Object> record = null;
        List<String> columnNameList = new ArrayList<String>();
        List<Map<String, Object>> recordSet = new ArrayList<Map<String, Object>>();
        try
        {
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
            {
                String columnName = rs.getMetaData().getColumnName(i)
                        .toLowerCase();
                columnNameList.add(columnName);
            }
            while (rs.next())
            {
                record = new HashMap<String, Object>();
                for (String columnName : columnNameList)
                    record.put(columnName, rs.getObject(columnName));

                recordSet.add(record);
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }

        return recordSet;
    }

    /**
     * @author 
     * 用于将ResultSet结果集转化成实体类列表或者List<Map<String,Object>>结果集的工具类，
     * 用于调用存储过程返回游标结果集转换成实体列表或者List<String>结果集
     * @param <T>
     */
    public static List<String> toStringList(ResultSet rs)
    {
        Map<String, Object> record = null;
        List<String> columnNameList = new ArrayList<String>();
        List<String> recordSet = new ArrayList<String>();
        try
        {
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
            {
                String columnName = rs.getMetaData().getColumnName(i)
                        .toLowerCase();
                columnNameList.add(columnName);
            }
            while (rs.next())
            {
                for (String columnName : columnNameList)
                    recordSet.add(rs.getString(columnName));

            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }

        return recordSet;
    }
    
    public static <T> List<T> toList(ResultSet rs, Class<T> classType)
    {
        List<String> columnNameList = new ArrayList<String>();
        List<T> entityList = null;
        try
        {
            entityList = new ArrayList<T>();
            Field[] fields = classType.getDeclaredFields();

            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
            {
                String columnName = rs.getMetaData().getColumnName(i)
                        .toUpperCase();
                columnNameList.add(columnName);
            }
            while (rs.next())
            {
                T entity = classType.newInstance();
                for (Field field : fields)
                {
                    String fieldName = field.getName();
                    // 如果实体类中的fieldName在ResultSet中没有，不索引取值，防止出现异常
                    if (columnNameList.contains(fieldName.toUpperCase()))
                    {
                        if(null!=rs.getObject(fieldName)){
                        if(rs.getObject(fieldName).getClass().toString().equals("class java.sql.Timestamp")){
                        	String time=rs.getObject(fieldName).toString();
                        	 SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        	 java.util.Date sqlDate = simpleDateFormat.parse(time);
                        	 BeanUtils.setProperty(entity, fieldName, simpleDateFormat.format(sqlDate));
                        }else{
                        BeanUtils.setProperty(entity, fieldName, rs
                                .getObject(fieldName));
                        }
                        }
                    }
                }
                entityList.add(entity);
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }

        return entityList;
    }

    public static Object toSingleResult(ResultSet rs)
    {
        Object result = null;
        try
        {
            while (rs.next())
            {
                result = rs.getObject(1);
                break;
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public static List toArrayList(ResultSet rs)
    {
        List arrayList = null;
        try
        {
            arrayList = new ArrayList();
            int iCol = rs.getMetaData().getColumnCount();
            while (rs.next())
            {
                Object[] objArray = new Object[iCol];
                for (int i = 1; i <= iCol; i++)
                {
                    objArray[i - 1] = rs.getObject(i);
                }
                arrayList.add(objArray);
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }

        return arrayList;
    }

    public static Map<String, Object> toHashMap(ResultSet rs)
    {
        List<String> columnNameList = new ArrayList<String>();
        Map<String, Object> record = new HashMap<String, Object>();
        try
        {
            int iRow = 0;
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
            {
                String columnName = rs.getMetaData().getColumnName(i)
                        .toLowerCase();
                columnNameList.add(columnName);
            }
            while (rs.next())
            {
                if (iRow > 1)
                    throw new RuntimeException(
                            "返回Map<String,Object>类型只能存放一条记录！");
                for (String columnName : columnNameList)
                    record.put(columnName, rs.getObject(columnName));
                iRow++;
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }

        return record;
    }

    public static <T> T toBean(ResultSet rs, Class<T> classType)
    {
        T entity = null;
        List<String> ColumnNameList = new ArrayList<String>();
        try
        {
            entity = classType.newInstance();
            Field[] fields = classType.getDeclaredFields();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
            {
                String columnName = rs.getMetaData().getColumnName(i)
                        .toUpperCase();
                //System.out.println("columnName=> " + columnName);
                ColumnNameList.add(columnName);
            }
            while (rs.next())
            {
                for (Field field : fields)
                {
                    String fieldName = field.getName();
                    // 如果实体类中的fieldName在ResultSet中没有，不索引取值，防止出现异常
                    if (ColumnNameList.contains(fieldName.toUpperCase()))
                    {
                       // System.out.println("fieldName=> " + fieldName);
                        BeanUtils.setProperty(entity, fieldName, rs
                                .getObject(fieldName));
                    }
                }
                break;
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }

        return entity;
    }
}
