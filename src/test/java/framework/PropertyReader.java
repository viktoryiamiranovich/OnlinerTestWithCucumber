package framework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    protected Properties properties;
    public PropertyReader(){
        properties = new Properties();
    }

    public PropertyReader(String ResourceFileName){
        properties = new Properties ();
        try{
            properties.load(getClass().getClassLoader().getResourceAsStream(ResourceFileName));
        } catch (IOException e){
            e.printStackTrace();}
    }

    public String getProperty(String key){return properties.getProperty(key);}
    /*public static String seleniumPropertyPath = "src/test/resources/config.properties";

    public String getExactProperty(String propertyPath, String propertyName){
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(propertyPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties property = new Properties();
        try {
            property.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return property.getProperty(propertyName);
    }*/

}

//    private Properties properties;
//    static String projectPath = System.getProperty("user.dir");
//
//    public PropertyReader(String propertyFileName) {
//        String fileName = projectPath + "/src/test/resources/" + propertyFileName;
//
//        properties = new Properties();
//
//        BufferedReader reader;
//        try {
//            reader = new BufferedReader(new FileReader(fileName));
//
//            try {
//                properties.load(reader);
//                reader.close();
//            } catch (IOException e) {
//                throw new RuntimeException("Can't read properties");
//            }
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException("Properties file not found at " + fileName);
//        }
//    }
