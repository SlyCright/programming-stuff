package educationalproject.programmingstuff.exeptions;

public class NotEnoughItemsAtStoreException extends RuntimeException{

    public NotEnoughItemsAtStoreException(String errorMessage) {

        super(errorMessage);

    }

}
