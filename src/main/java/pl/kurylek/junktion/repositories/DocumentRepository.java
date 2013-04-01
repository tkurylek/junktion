package pl.kurylek.junktion.repositories;

import static org.slf4j.LoggerFactory.getLogger;
import static pl.kurylek.junktion.builders.SolrQueryBuilder.aQuery;
import static pl.kurylek.junktion.domain.Document.AUTHOR_FIELD;
import static pl.kurylek.junktion.domain.Document.CONTENT_FIELD;
import static pl.kurylek.junktion.domain.Document.PATH_FIELD;
import static pl.kurylek.junktion.domain.Document.TITLE_FIELD;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.kurylek.junktion.exceptions.DocumentRepositoryException;

@Repository
public class DocumentRepository {

    private static final int TITLE_FIELD_BOOST = 2;
    private static final int PATH_FIELD_BOOST = 3;
    private static final int HIGHLIGHTED_SNIPPETS_QUANTITY = 2;
    final Logger logger = getLogger(getClass());
    @Autowired
    private SolrServer solrServer;

    public QueryResponse queryByContentOrAuthorOrTitleOrPath(String phrase) {
	try {
	    SolrQuery query = aQuery(phrase).withinField(CONTENT_FIELD).orWithinField(AUTHOR_FIELD)
		    .orWithinBoostedField(TITLE_FIELD, TITLE_FIELD_BOOST)
		    .orWithinBoostedField(PATH_FIELD, PATH_FIELD_BOOST)
		    .withExtendedDisjunctionMax().withEnabledHighlighting()
		    .withNumberOfHighlightedSnippets(HIGHLIGHTED_SNIPPETS_QUANTITY).build();
	    QueryResponse queryResponse = solrServer.query(query);
	    logger.info("Executing query: " + query);
	    logger.info("Query took: " + queryResponse.getElapsedTime() + " ms");
	    return queryResponse;

	} catch (SolrServerException e) {
	    logger.error(e.getMessage());
	    throw new DocumentRepositoryException(phrase, e);
	}
    }
}
