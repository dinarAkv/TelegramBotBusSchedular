package ru.cheb.intercity.bus.helper;

import org.springframework.stereotype.Component;
import ru.cheb.intercity.bus.constants.EnvironmentVarConstants;

import java.util.Map;

@Component
public class EnvVarHelperImpl implements EnvVarHelper {

    /**
     * Function return environment variable value by its key.
     * @param envKey - key of environment variable
     * @return value of environment variable.
     */
    @Override
    public String getEnvVar(String envKey) {
        Map<String, String> env = System.getenv();
        return env.get(envKey);
    }
}
