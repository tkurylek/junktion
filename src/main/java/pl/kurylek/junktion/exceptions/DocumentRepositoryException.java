package pl.kurylek.junktion.exceptions;

@SuppressWarnings("serial")
public class DocumentRepositoryException extends RuntimeException {

    public DocumentRepositoryException(String query, Throwable cause) {
	super("Could not query for document matching query \"" + query + "\". Following cause: ",
		cause);
    }
}
