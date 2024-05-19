package loggerUtility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtility {

    //trebuie sa definesc o instanta de logger
    //trebuie sa definesc o metoda care sa porneasca un test
    //---opreasca un test
    //metoda de info
    //metoda de error

    private static final Logger logger = LogManager.getLogger();

    public static void startTestCase(String testName) {
        logger.info("****** Execution started: " + testName + " ******");
    }

    public static void finishTestCase(String testName) {
        logger.info("****** Execution finished: " + testName + " ******");
    }

    public static void infoTest(String message) {
        logger.info(message);
    }

    public static void errorTest(String message) {
        logger.error(message);
    }
}
