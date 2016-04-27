package exception;

public class BitmapOperationOutOfBoundsException extends RuntimeException{
    public BitmapOperationOutOfBoundsException(){
        super();
    }
    public BitmapOperationOutOfBoundsException(String message){
        super(message);
    }
}
