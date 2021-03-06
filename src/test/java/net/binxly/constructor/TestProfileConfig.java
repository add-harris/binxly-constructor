package net.binxly.constructor;

import io.quarkus.test.junit.QuarkusTestProfile;

import java.util.HashMap;
import java.util.Map;

public class TestProfileConfig implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
        var map = new HashMap<String, String>();
        map.put("firebase.auth.active", "false");
        map.put("quarkus.http.cors.origins", "http://localhost:3000");
        return map;
    }

}
