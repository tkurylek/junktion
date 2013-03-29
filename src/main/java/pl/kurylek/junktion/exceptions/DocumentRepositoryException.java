package pl.kurylek.junktion.exceptions;

@SuppressWarnings("serial")
public class DocumentRepositoryException extends RuntimeException {

    public DocumentRepositoryException(String message, Throwable cause) {
	super(message, cause);
    }
}
