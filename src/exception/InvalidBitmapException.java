package exception;

public class InvalidBitmapException extends RuntimeException{
    public InvalidBitmapException(){
        super();
    }
    public InvalidBitmapException(String message){
        super(message);
    }
}