package io.gametown.api.api.spec.helpers;

import io.gametown.api.api.spec.;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Olivier Liechti on 24/06/17.
 */
public class Environment {

    private DefaultApiTest api = new DefaultApiTest();

    public Environment() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = properties.getProperty("io.gametown.api");
        api.

    }

    public DefaultApiTest getApi() {
        return api;
    }


}
