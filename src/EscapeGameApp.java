import org.apache.log4j.Logger;

public class EscapeGameApp {

    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(EscapeGameApp.class);

    public static void main(String[] args) throws CombinationIncorrectException {
        LOGGER.info("App started");

        IHM ihm = new IHM();

        LOGGER.info("App terminated");
    }

}

