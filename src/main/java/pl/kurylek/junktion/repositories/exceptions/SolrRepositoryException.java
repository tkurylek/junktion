package pl.kurylek.junktion.repositories.exceptions;

@SuppressWarnings("serial")
public class SolrRepositoryException extends RuntimeException {

    public SolrRepositoryException(Throwable cause) {
	super("Fatal server error with following cause: ", cause);
    }
}
