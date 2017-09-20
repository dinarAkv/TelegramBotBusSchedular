package ru.cheb.intercity.bus.helper;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.cheb.intercity.bus.constants.EnvironmentVarConstants;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EnvVarHelperImplTest {

    @Autowired
    EnvVarHelper envVarHelper;

    @Test
    public void getEnvVarTest() {
        String envVar = envVarHelper.getEnvVar(EnvironmentVarConstants.forTest);
        Assert.assertFalse(envVar.isEmpty());
    }

}