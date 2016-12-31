package logback.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * Logbackサンプル
 */
public class LogbackSample implements Runnable {

    @Override
    public void run() {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("Info log sample");
        logger.error("Error log sample");
        MDC.put("mdc", "[MDC TAG] ");
        logger.info("MDC put");
        MDC.clear();
        logger.info("MDC cleared");
    }

    /**
     * メインメソッド
     * @param args パラメータ
     */
    public static void main(String[] args) {
        new LogbackSample().run();
    }

}
