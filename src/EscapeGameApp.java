import org.apache.log4j.Logger;

public class EscapeGameApp {

    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(EscapeGameApp.class);

    public EscapeGameApp() {

    }

    public static void main(String[] args) {
        LOGGER.info("App started");

        IHM escapeGameApp = new IHM();
        escapeGameApp.runIHM();
        LOGGER.info("App terminated");
    }

}

