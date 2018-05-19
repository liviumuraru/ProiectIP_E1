package exceptions;

public class CrawlerException extends Exception {
    public CrawlerException(String errMessage){
        super(errMessage);
    }
}