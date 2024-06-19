package com.surveymaster;

import org.renjin.script.RenjinScriptEngine;
import org.renjin.script.RenjinScriptEngineFactory;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RIntegration {
    public static void main(String[] args) {
        RenjinScriptEngineFactory factory = new RenjinScriptEngineFactory();
        RenjinScriptEngine engine = factory.getScriptEngine();

        try {
            String script = new String(Files.readAllBytes(Paths.get("src/main/resources/scripts/master.R")));
            engine.eval(script);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
