package com.neil.api.gateway.utils;

import com.neil.commons.exception.BusinessException;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;

/**
 * 扩展org.apache.commons.beanutils.BeanUtils<br>
 *
 * @author Wesley<br>
 */
public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {

    private static final Logger log = LoggerFactory.getLogger(BeanUtils.class);

    public BeanUtils() {
    }

    /**
     * 将源对象中的值覆盖到目标对象中，仅覆盖源对象中不为NULL值的属性
     *
     * @param dest 目标对象，标准的JavaBean
     * @param orig 源对象，可为Map、标准的JavaBean
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static void applyIf(Object dest, Object orig) throws Exception {
        try {
            if (orig instanceof Map) {
                Iterator names = ((Map) orig).keySet().iterator();
                while (names.hasNext()) {
                    String name = (String) names.next();
                    if (PropertyUtils.isWriteable(dest, name)) {
                        Object value = ((Map) orig).get(name);
                        if (value != null) {
                            PropertyUtils.setSimpleProperty(dest, name, value);
                        }
                    }
                }
            } else {
                java.lang.reflect.Field[] fields = orig.getClass().getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    String name = fields[i].getName();
                    if (PropertyUtils.isReadable(orig, name) && PropertyUtils.isWriteable(dest, name)) {
                        Object value = PropertyUtils.getSimpleProperty(orig, name);
                        if (value != null) {
                            PropertyUtils.setSimpleProperty(dest, name, value);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("将源对象中的值覆盖到目标对象中，仅覆盖源对象中不为NULL值的属性", e);
        }
    }

    /**
     * 将源对象中的值覆盖到目标对象中，仅覆盖源对象中不为NULL值的属性
     *
     * @param orig 源对象，标准的JavaBean
     * @param dest 排除检查的属性，Map
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static boolean checkObjProperty(Object orig, Map dest) throws Exception {
        try {
            java.lang.reflect.Field[] fields = orig.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                String name = fields[i].getName();
                if (!dest.containsKey(name)) {
                    if (PropertyUtils.isReadable(orig, name)) {
                        Object value = PropertyUtils.getSimpleProperty(orig, name);
                        if (value == null) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } catch (Exception e) {
            throw new Exception("将源对象中的值覆盖到目标对象中，仅覆盖源对象中不为NULL值的属性", e);
        }
    }

    // Map --> Bean 2: 利用org.apache.commons.beanutils 工具类实现 Map --> Bean
    public static Object transMap2Bean(Object obj, Map<String, Object> map) throws BusinessException {
        if (map == null) {
            throw new BusinessException("map不存在");
        }
        if (obj == null) {
            throw new BusinessException("obj不存在");
        }
        try {
            BeanUtils.populate(obj, map);
            return obj;
        } catch (Exception e) {
            log.error("transMap2Bean2 Error " + e);
        }
        return null;
    }
}