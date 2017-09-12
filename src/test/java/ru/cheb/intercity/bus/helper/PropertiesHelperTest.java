package ru.cheb.intercity.bus.helper;

import org.junit.Assert;
import org.junit.Test;
import ru.cheb.intercity.bus.parsers.BusStationsParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertTrue;

public class PropertiesHelperTest {



    @Test
    public void getHostTest()
    {
        String testPropertiesFileName = "temp.properties";
        String parKeyTrue = "par1";
        String parValTrue = "25678";
        File tempFile = buildTestPropertiesFile(testPropertiesFileName, parKeyTrue, parValTrue);

        String testPropertyVal = PropertiesHelper.getPropByKeyInProperties(testPropertiesFileName, parKeyTrue);

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
