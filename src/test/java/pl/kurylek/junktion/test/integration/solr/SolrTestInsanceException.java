package pl.kurylek.junktion.test.integration.solr;

@SuppressWarnings("serial")
public class SolrTestInsanceException extends RuntimeException {

    public SolrTestInsanceException(Throwable cause) {
	super("Could not finish an operation on solr testing instance", cause);
    }
}
