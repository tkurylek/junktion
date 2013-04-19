package pl.kurylek.junktion.repositories.exceptions;

@SuppressWarnings("serial")
public class SolrRepositoryException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Fatal server error with following cause: ";

    public SolrRepositoryException(Throwable cause) {
	super(ERROR_MESSAGE, cause);
    }

    public SolrRepositoryException(String message, Throwable cause) {
	super(ERROR_MESSAGE + message, cause);
    }
}