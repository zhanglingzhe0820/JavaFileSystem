package surevil.FileSystem.util;

import surevil.FileSystem.entity.Entity;
import surevil.FileSystem.entity.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class AnnotationUtil {
    public static String getTableName(Class clazz) {
        Table table = (Table) clazz.getAnnotation(Table.class);
        return table.name();
    }

    public static ArrayList<String> getAllColumnName(Class clazz) {
        ArrayList<String> columns = new ArrayList<>();
        Field[] fields = ReflectionUtil.getAllFields(clazz);
        if (fields != null) {
            for (Field field : fields) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                Column column = field.getAnnotation(Column.class);
                columns.add(column.name());
            }
        }
        return columns;
    }

    public static String getKey(Class<? extends Entity> clazz) {
        String key = "";
        Field[] fields = ReflectionUtil.getAllFields(clazz);
        for (Field field : fields) {
            Id id = field.getAnnotation(Id.class);
            if (id != null) {
                key = field.getName();
                break;
            }
        }
        return key;
    }

    public static boolean isKeyAutoGenerated(Class<? extends Entity> clazz) {
        boolean isKeyAutoGenerated = false;
        Field[] fields = ReflectionUtil.getAllFields(clazz);
        for (Field field : fields) {
            GeneratedValue generatedValue = field.getAnnotation(GeneratedValue.class);
            if (generatedValue != null && generatedValue.strategy() == GenerationType.AUTO) {
                isKeyAutoGenerated = true;
                break;
            }
        }
        return isKeyAutoGenerated;
    }
}
