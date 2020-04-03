package com.ying.background.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class PropertiesUtil {

    private static Properties properties = null;
    
    private static void load() {
        
        String configure = (String)SpringContextUtil.getBean("configureFile");

        if(StringUtils.isEmpty(configure)){
            log.error("******web app configured file is not configured!!!! do check applicationContext.xml!!!******");
            return;
        }

        String[] array = configure.split("\\:");
        log.info("loaded config file [[" + array[1] + "]]");
        
        if (properties != null){
            return;
        }

        InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream(array[1]);

        Properties props = new Properties();
        try {
            props.load(is);
        } catch (Exception e) {
            props = null;
        }

        properties = props;
        
        log.info("LOADED properties");
    }
    

    public static String getProperties(String key){
        if(properties == null){
            load();
        }
        return String.valueOf(properties.get(key)).trim();
    }
    
}
