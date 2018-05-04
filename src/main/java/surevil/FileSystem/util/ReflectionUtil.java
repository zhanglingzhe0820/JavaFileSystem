package surevil.FileSystem.util;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ReflectionUtil {

    //递归获得包括protected属性在内的所有属性
    public static Field[] getAllFields(Class clazz) {
        ArrayList<Class> classArrayList = new ArrayList<>();
        ArrayList<Field> fieldArrayList = new ArrayList<>();
        while (clazz != Object.class) {
            classArrayList.add(clazz);
            clazz = clazz.getSuperclass();
        }
        for (int i = classArrayList.size() - 1; i >= 0; i--) {
            Field[] fields = classArrayList.get(i).getDeclaredFields();
            for (Field field : fields) {
                fieldArrayList.add(field);
            }
        }
        return fieldArrayList.toArray(new Field[fieldArrayList.size()]);
    }

    //根据属性名递归获得包括protected属性在内的所有属性
    public static Field getAllField(Class clazz, String attributeName) throws NoSuchFieldException {
        Field[] fields = getAllFields(clazz);
        for (Field field : fields) {
            if (field.getName().equals(attributeName)) {
                return field;
            }
        }
        throw new NoSuchFieldException();
    }
}
