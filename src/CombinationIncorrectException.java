import org.apache.log4j.Logger;

public class CombinationIncorrectException extends Exception {
    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(CombinationIncorrectException.class);

    public CombinationIncorrectException(String s){
        super(s);
    }

}
