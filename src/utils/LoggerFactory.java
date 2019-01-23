package utils;

public class LoggerFactory {

    public static Logger getLogger(Object o){
        Class clazz = o.getClass();
        return new Logger(clazz.getName());
    }
}
