import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ReadPropertyFile {


    public static void main(String[] args) {

        final Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("/Volumes/Macintosh HD/Users/Mathieu/Documents/Workspace/EscapeGame/src/config.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            System.out.println(prop.getProperty("LENGTH_COMBINATION"));
            System.out.println(prop.getProperty("LIMITED_NUMBER_OF_TRIALS"));
            System.out.println(prop.getProperty("DEVELOPPER_MODE"));


        } catch (final IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


