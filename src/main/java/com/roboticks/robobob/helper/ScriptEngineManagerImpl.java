package com.roboticks.robobob.helper;

import org.springframework.stereotype.Component;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

@Component
public class ScriptEngineManagerImpl implements IScriptEngineManager{

    @Override
    public ScriptEngine getJavaScriptEngine() {
        return new ScriptEngineManager().getEngineByName("JavaScript");
    }
}
