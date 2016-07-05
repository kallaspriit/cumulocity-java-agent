package com.stagnationlab.c8y.example;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public class Config {

    private static final Logger log = LoggerFactory.getLogger(Config.class);

    String tenant;
    String username;
    String password;

    private Config(String tenant, String username, String password) {
        this.tenant = tenant;
        this.username = username;
        this.password = password;
    }

    static Config loadOrCreateConfig() throws IOException {
        try {
            return loadConfig();
        } catch (IOException e) {
            return createConfig();
        }
    }

    private static Config createConfig() throws IOException {
        log.info("config.properties file does not exist, enter the data manually");

        String tenant = askForInput("Enter tenant name:");
        String username = askForInput("Enter username:");
        String password = askForInput("Enter password:");

        Config config = new Config(
                tenant,
                username,
                password
        );

        saveConfig(config);

        return config;
    }

    private static void saveConfig(Config config) throws IOException {
        log.info("saving configuration file");

        Properties prop = new Properties();
        OutputStream output = new FileOutputStream("config.properties");

        prop.setProperty("tenant", config.tenant);
        prop.setProperty("username", config.username);
        prop.setProperty("password", config.password);

        prop.store(output, null);
    }

    private static Config loadConfig() throws IOException {
        log.info("loading configuration file");

        Properties prop = new Properties();
        InputStream input = new FileInputStream("config.properties");

        prop.load(input);

        return new Config(
                prop.getProperty("tenant"),
                prop.getProperty("username"),
                prop.getProperty("password")
        );
    }

    private static String askForInput(String question) {
        System.out.print(question + " ");

        return (new Scanner(System.in)).nextLine();
    }
}
