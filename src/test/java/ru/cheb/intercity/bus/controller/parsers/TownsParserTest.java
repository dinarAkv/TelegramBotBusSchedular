package ru.cheb.intercity.bus.controller.parsers;





import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;


public class TownsParserTest {


    @Test
    public void getHostTest()
    {
        String testPropertiesFileName = "temp.properties";
        String parKeyTrue = "par1";
        String parValTrue = "25678";
        File tempFile = buildTestPropertiesFile(testPropertiesFileName, parKeyTrue, parValTrue);

        String methodName = "getStationsWebSiteUrl";

        try {
            Method method = TownsParser.class.getDeclaredMethod(methodName, String.class, String.class);
            method.setAccessible(true);

            TownsParser townsParserTestObj = new TownsParser();
            String testPropertyVal = (String) method.invoke(townsParserTestObj, testPropertiesFileName, parKeyTrue);

            assertTrue(testPropertyVal.equals(parValTrue));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        } finally {
            if (tempFile != null){
                tempFile.delete();
            }
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


    @Test
    public void getTownsSchedulerUrls() {
        try {
            Map<String, String> townsSchedulerUrls = TownsParser.getTownsSchedulerUrls();

            String rightBeginOfUrl = "/passengers/";

            townsSchedulerUrls.entrySet().forEach(entry->{
                String url = entry.getValue();

                assert url.startsWith(rightBeginOfUrl);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}