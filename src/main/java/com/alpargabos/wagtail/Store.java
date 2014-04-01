package com.alpargabos.wagtail;

import twitter4j.auth.AccessToken;

import java.io.*;
import java.util.Properties;

public class Store {


    public static AccessToken getPersistedAccessToken(){
        Properties props = readPersistedData();
        if (props.get("token") != null && props.get("secret") != null) {
            return new AccessToken(props.get("token").toString(), props.get("secret").toString());
        }
        return null;
    }

    private static Properties readPersistedData() {
        Properties prop = new Properties();
        File f = new File("config.properties");
        if (f.exists() && !f.isDirectory()) {
            InputStream input = null;
            try {
                input = new FileInputStream("config.properties");
                prop.load(input);
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return prop;
    }


    public static void persistsData(AccessToken accessToken) {
        Properties prop = new Properties();
        OutputStream output = null;
        try {
            output = new FileOutputStream("config.properties");
            prop.setProperty("id", String.valueOf(accessToken.getUserId()));
            prop.setProperty("token", accessToken.getToken());
            prop.setProperty("secret", accessToken.getTokenSecret());
            prop.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
