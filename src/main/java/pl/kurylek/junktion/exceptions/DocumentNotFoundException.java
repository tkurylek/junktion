package pl.kurylek.junktion.exceptions;

@SuppressWarnings("serial")
public class DocumentNotFoundException extends RuntimeException {

    public DocumentNotFoundException(String query) {
	super("Could not find any document matching query: \"" + query + "\"");
    }
}
