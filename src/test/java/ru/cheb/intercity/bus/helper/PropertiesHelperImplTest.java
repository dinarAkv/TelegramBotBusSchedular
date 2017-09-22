package ru.cheb.intercity.bus.helper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertiesHelperImplTest {

    @Autowired
    PropertiesHelper propertiesHelper;

    @Test
    public void getHostTest()
    {
        String testPropertiesFileName = "temp.properties";
        String parKeyTrue = "par1";
        String parValTrue = "25678";
        File tempFile = buildTestPropertiesFile(testPropertiesFileName, parKeyTrue, parValTrue);

        String testPropertyVal = propertiesHelper.getPropByKeyInProperties(testPropertiesFileName, parKeyTrue);

        Assert.assertTrue(testPropertyVal.equals(parValTrue));

        if (tempFile != null){
            tempFile.delete();
        }
    }



    /**
     * Function build "*.properties" file for test purposes.
     * @param fileName - name of test file.
     * @return - temporary file object.
     */
    private File buildTestPropertiesFile(String fileName, String propKey, String propVal)
    {
        String testFileName = new String(fileName);
        File testPropertiesFile = new File(testFileName);


        String parameterStr = new String(propKey + "=" + propVal);

        try (PrintWriter printWriter = new PrintWriter(testPropertiesFile)) {
            printWriter.write(parameterStr);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return testPropertiesFile;
    }

}
