package com.alpargabos.wagtail;

import twitter4j.auth.AccessToken;

import java.io.*;
import java.util.Properties;

public class Store {


    public static final String CONFIG_FILE = "config.properties";
    public static final String TOKEN = "token";
    public static final String SECRET = "secret";

    public AccessToken getPersistedAccessToken(){
        Properties props = readPersistedData();
        if (props.get(TOKEN) != null && props.get(SECRET) != null) {
            return new AccessToken(props.get(TOKEN).toString(), props.get(SECRET).toString());
        }
        return null;
    }

    private Properties readPersistedData() {
        Properties prop = new Properties();
        File f = new File(CONFIG_FILE);
        if (f.exists() && !f.isDirectory()) {
            InputStream input = null;
            try {
                input = new FileInputStream(CONFIG_FILE);
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


    public void persistsData(AccessToken accessToken) {
        Properties prop = new Properties();
        OutputStream output = null;
        try {
            output = new FileOutputStream(CONFIG_FILE);
            prop.setProperty(TOKEN, accessToken.getToken());
            prop.setProperty(SECRET, accessToken.getTokenSecret());
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
