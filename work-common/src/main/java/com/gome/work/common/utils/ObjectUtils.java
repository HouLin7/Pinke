package com.gome.work.common.utils;

import android.text.TextUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * Created by liubomin on 2017/7/3.
 */

public class ObjectUtils {


    public static <T> T cast(Object obj, Class<T> type) {
        if (!type.isInstance(obj)) {
            return null;
        }
        return type.cast(obj);
    }

    public static <T> T newInstance(String className) {

        try {
            Class<T> cls = (Class<T>) Class.forName(className);
            return newInstance(cls);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T newInstance(Class<T> cls) {
        return newInstance(cls, null, null);
    }

    public static <T> T newInstance(Class<T> cls, Object... object) {
        return newInstance(cls, null, object);
    }

    public static <T> T newInstance(Class<T> cls, Class<?>[] parameterTypes, Object[] parameters){
        if (parameters == null) {
            parameters = new Object[]{};
        }

        if (parameterTypes != null) {
            try {
                Constructor<T> constructor = cls.getDeclaredConstructor(parameterTypes);
                constructor.setAccessible(true);
                return constructor.newInstance(parameters);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            for (Constructor<?> constructor : cls.getDeclaredConstructors()) {
                try {
                    constructor.setAccessible(true);
                    return (T) constructor.newInstance(parameters);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    /**
     * Get the Field object from an object's class or its all super classes.
     *
     * @param obj  the object to search
     * @param name field name
     * @return Field object
     * @throws NoSuchFieldException no field found with given name
     */
    public static Field getField(Object obj, String name) throws NoSuchFieldException {
        if (obj == null || TextUtils.isEmpty(name)) {
            return null;
        }

        Field field = null;
        NoSuchFieldException exception = null;

        Class<?> type = obj.getClass();
        while (type != null && !type.equals(Object.class)) {
            // loop up to Object, null means a primitive type
            try {
                field = type.getDeclaredField(name);
            } catch (NoSuchFieldException e) {
                // hold the first exception
                if (exception == null) {
                    exception = e;
                }
            }

            if (field != null) {
                // found the field, exit the loop
                break;
            } else {
                // if no such field in current class, then search super class
                type = type.getSuperclass();
            }
        }

        if (field == null && exception != null) {
            // didn't find any field
            throw exception;
        }

        return field;
    }

    /**
     * Get the value of a field
     *
     * @param obj  the object
     * @param name the field name
     * @return the value
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static Object getFieldValue(Object obj, String name) throws NoSuchFieldException, IllegalAccessException {
        Field field = getField(obj, name);
        if (field == null) {
            return null;
        }

        field.setAccessible(true);
        return field.get(obj);
    }

    public static Class<?> getGenericClass(Object obj, Class<?> interfaceType, int index) {
        Type superType = obj.getClass().getGenericSuperclass();
        if (superType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) superType;
            Type actualType = parameterizedType.getActualTypeArguments()[index];
            if (actualType instanceof Class) {
                return (Class<?>) actualType;
            }
        }

        if (interfaceType != null && interfaceType.isInstance(obj)) {
            Type[] types = obj.getClass().getGenericInterfaces();
            for (Type type : types) {
                if (!(type instanceof ParameterizedType)) {
                    continue;
                }
                ParameterizedType paramType = ParameterizedType.class.cast(type);
                if (paramType.getRawType() != interfaceType) {
                    continue;
                }
                Type actualType = paramType.getActualTypeArguments()[index];
                if (actualType instanceof Class) {
                    return (Class<?>) actualType;
                }
            }
        }

        return null;
    }

    public interface MethodFilter {
        boolean accept(Method method);
    }

    private static class MethodWrapper {
        private final Method method;

        MethodWrapper(Method method) {
            this.method = method;
        }

        public boolean equals(Method m) {
            return method.getName().equals(m.getName())
                    && Arrays.asList(method.getParameterTypes()).equals(Arrays.asList(m.getParameterTypes()));
        }

        public int hashCode() {
            return method.getName().hashCode() ^ Arrays.asList(method.getParameterTypes()).hashCode();
        }

        public Method getMethod() {
            return method;
        }
    }
}
