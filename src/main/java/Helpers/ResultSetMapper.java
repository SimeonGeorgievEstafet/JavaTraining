package Helpers;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetMapper<T> {
    @SuppressWarnings("unchecked")
    public List<T> mapResultSetToObjects(ResultSet rs, Class outputClass) {
        List<T> outputList = new ArrayList<>();
        try {
            // make sure ResultSet is not null
            if (rs != null) {
                // check if outputClass has 'Entity' annotation
                if (outputClass.isAnnotationPresent(Entity.class)) {
                    // get the ResultSet metadata
                    ResultSetMetaData rsmd = rs.getMetaData();
                    // get all the attributes of outputClass
                    Field[] fields = outputClass.getDeclaredFields();
                    while (rs.next()) {
                        T bean = (T) outputClass.getDeclaredConstructor().newInstance();
                        for (int _iterator = 0; _iterator < rsmd
                                .getColumnCount(); _iterator++) {
                            // getting the SQL column name
                            String columnName = rsmd
                                    .getColumnName(_iterator + 1);
                            // reading the value of the SQL column
                            Object columnValue = rs.getObject(_iterator + 1);
                            // iterating over outputClass attributes to check if any attribute has 'Column' annotation with matching 'name' value
                            for (Field field : fields) {
                                if (field.isAnnotationPresent(Column.class)) {
                                    Column column = field
                                            .getAnnotation(Column.class);
                                    if (column.name().equalsIgnoreCase(
                                            columnName)
                                            && columnValue != null) {
                                        BeanUtils.setProperty(bean, field
                                                .getName(), columnValue);
                                        break;
                                    }
                                }
                            }
                        }
                        if (outputList == null) {
                            outputList = new ArrayList<T>();
                        }
                        outputList.add(bean);
                    }

                }
            } else {
                return null;
            }
        } catch (IllegalAccessException | InstantiationException | SQLException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return outputList;
    }


    public T mapResultSetToObject(ResultSet rs, Class outputClass) {
        List<T> objects = mapResultSetToObjects(rs, outputClass);
        return objects.get(0);
    }

    public int mapResultSetToInt(ResultSet resultSet) {
        int result = -1;
        try {
            if (resultSet.next())
                result = resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<Integer> mapResultSetToList(ResultSet resultSet) {
        List<Integer> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                result.add(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}

