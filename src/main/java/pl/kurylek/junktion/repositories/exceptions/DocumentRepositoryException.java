package pl.kurylek.junktion.repositories.exceptions;

@SuppressWarnings("serial")
public class DocumentRepositoryException extends RuntimeException {

    public DocumentRepositoryException(String query, Throwable cause) {
	super("Could not query for document matching query \"" + query
		+ "\". Fatal server error with following cause: ", cause);
    }
}
