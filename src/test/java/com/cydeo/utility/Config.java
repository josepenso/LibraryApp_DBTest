package com.cydeo.utility;

import java.io.FileInputStream;
import java.util.Properties;

public class Config {
        private static Properties configFile;

        static {

            try {
                String path = "config.properties";
                FileInputStream input = new FileInputStream(path);
                configFile = new Properties();
                configFile.load(input);
                input.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        public static String getProperty(String keyName) {
            return configFile.getProperty(keyName);
        }

    }

