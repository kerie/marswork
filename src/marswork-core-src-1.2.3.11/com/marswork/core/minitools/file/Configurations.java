/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package com.marswork.core.minitools.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.AccessControlException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.marswork.core.minitools.cache.ItemCache;
import com.marswork.core.minitools.cache.ItemCache.ValueBuilder;

/**
 * Properties文件读取类
 * 
 * @author Nordy.SU
 * @since 2011-05-01
 */
public final class Configurations {

    private static final Logger logger = Logger.getLogger(Configurations.class);

    private static boolean DALVIK = false;
    static {
        try {
            // Android platform should have dalvik.system.VMRuntime in the classpath.
            // @see http://developer.android.com/reference/dalvik/system/VMRuntime.html
            Class.forName("dalvik.system.VMRuntime");
            DALVIK = true;
        }
        catch (ClassNotFoundException cnfe) {
            DALVIK = false;
        }
    }

    /**
     * Properties Cache
     */
    private static final ItemCache<String, Properties> properties = new ItemCache<String, Properties>(
            new ValueBuilder<String, Properties>() {

                /**
                 * 初始化配置信息，
                 * classpath:/key.properties，WEB-INF/key.properties
                 */
                @Override
                public Properties build(String key) {
                    logger.debug(" Building property...");
                    Properties prop = new Properties();
                    String fileName = key + ".properties";
                    if (loadProperties(prop, "." + File.separatorChar +
                                             fileName)) {
                        logger.debug(" Property built: " + prop);
                        return prop;
                    }

                    if (loadProperties(prop, Configurations.class
                            .getResourceAsStream(File.separatorChar +
                                                 "WEB-INF" +
                                                 File.separatorChar + fileName))) {
                        logger.debug(" Property built: " + prop);
                        return prop;
                    }
                    if (loadProperties(
                            prop,
                            Configurations.class.getResourceAsStream("/" +
                                                                     fileName))) {
                        logger.debug(" Property built: " + prop);
                        return prop;
                    }
                    logger.fatal(
                            "WARNING: Could not load " + key +
                                    ".properties. All properties will be fallbackValue. ",
                            new Throwable());
                    return prop;

                }
            });

    /**
     * 从Property中取一个Boolean值。
     * 
     * @param fileName
     * @param key
     * @param fallbackValue
     * @return
     */
    public static boolean getBooleanProperty(String fileName, String key,
            boolean fallbackValue) {
        return Boolean.parseBoolean(getStringProperty(fileName, key,
                String.valueOf(fallbackValue)));
    }

    /**
     * 从Property中取一个Double值。
     * 
     * @param fileName
     * @param key
     * @param fallbackValue
     * @return
     */
    public static double getDoubleProperty(String fileName, String key,
            double fallbackValue) {
        return Double.parseDouble(getStringProperty(fileName, key,
                String.valueOf(fallbackValue)));
    }

    /**
     * @param fileName
     * @param key
     * @param fallbackValue
     * @return
     */
    public static int getIntProperty(String fileName, String key,
            int fallbackValue) {

        return Integer.parseInt(getStringProperty(fileName, key,
                String.valueOf(fallbackValue)));

    }

    /**
     * @param fileName
     * @param key
     * @param fallbackValue
     * @return
     */
    public static long getLongProperty(String fileName, String key,
            long fallbackValue) {
        return Long.parseLong(getStringProperty(fileName, key,
                String.valueOf(fallbackValue)));
    }

    public static Properties getProperties(String fileName) {
        return properties.get(fileName);
    }

    /**
     * @param fileName
     * @param key
     * @param fallbackValue
     * @return
     */
    public static short getShortProperty(String fileName, String key,
            short fallbackValue) {
        return Short.parseShort(getStringProperty(fileName, key,
                String.valueOf(fallbackValue)));
    }

    /**
     * 先读取系统环境变量, fileName.properties会覆盖系统变量，
     * 
     * @param fileName
     * @param key
     * @param fallbackValue
     * @return
     */
    public static String getStringProperty(Properties property, String key,
            String fallbackValue) {
        String value = null;
        try {
            value = System.getProperty(key, fallbackValue);
        }
        catch (AccessControlException ace) {
            // Unsigned applet cannot access System properties
            value = fallbackValue;
        }

        value = property.getProperty(key) != null ? property.getProperty(key) : value;
        if (null == value) {
            String fallback = property.getProperty(key + ".fallback");
            if (null != fallback) {
                value = getStringProperty(property, fallback, null);
            }
        }
        return replace(property, value);
    }

    /**
     * 先读取系统环境变量, fileName.properties会覆盖系统变量，
     * 
     * @param fileName
     * @param key
     * @param fallbackValue
     * @return
     */
    public static String getStringProperty(String fileName, String key) {
        return getStringProperty(fileName, key, null);
    }

    /**
     * 先读取系统环境变量, fileName.properties会覆盖系统变量，
     * 
     * @param fileName
     * @param key
     * @param fallbackValue
     * @return
     */
    public static String getStringProperty(String fileName, String key,
            String fallbackValue) {
        return getStringProperty(properties.get(fileName), key, fallbackValue);
    }

    public static boolean isDalvik() {
        return DALVIK;
    }

    private static boolean loadProperties(Properties props, InputStream is) {
        try {
            logger.debug("Loading Properties from InputStream: " + is);
            props.load(is);
            return true;
        }
        catch (Exception ignore) {}
        return false;
    }

    private static boolean loadProperties(Properties props, String path) {
        try {
            File file = new File(path);
            if (file.exists() && file.isFile()) {
                logger.debug("Loading Properties from file: " + path);
                props.load(new FileInputStream(file));
                return true;
            }
        }
        catch (Exception ignore) {}
        return false;
    }

    /**
     * 将Properties值中的{name}替换为name对应的值
     * 
     * @param property
     * @param value
     * @return
     */
    private static String replace(Properties property, String value) {
        if (null == value) {
            return value;
        }
        String newValue = value;
        int openBrace = 0;
        if (-1 != (openBrace = value.indexOf("{", openBrace))) {
            int closeBrace = value.indexOf("}", openBrace);
            if (closeBrace > openBrace + 1) {
                String name = value.substring(openBrace + 1, closeBrace);
                if (name.length() > 0) {
                    newValue = value.substring(0, openBrace) +
                               getStringProperty(property, name, null) +
                               value.substring(closeBrace + 1);

                }
            }
        }
        if (newValue.equals(value)) {
            return value;
        } else {
            return replace(property, newValue);
        }
    }
}
