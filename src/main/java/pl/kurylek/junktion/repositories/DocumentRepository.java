package pl.kurylek.junktion.repositories;

import static org.slf4j.LoggerFactory.getLogger;
import static pl.kurylek.junktion.builders.SolrQueryBuilder.aQuery;
import static pl.kurylek.junktion.domain.Document.AUTHOR_FIELD;
import static pl.kurylek.junktion.domain.Document.CONTENT_FIELD;
import static pl.kurylek.junktion.domain.Document.FILENAME_FIELD;
import static pl.kurylek.junktion.domain.Document.PATH_FIELD;
import static pl.kurylek.junktion.domain.Document.TITLE_FIELD;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.kurylek.junktion.exceptions.DocumentRepositoryException;

@Repository
public class DocumentRepository {

    private static final int HIGHLIGHTED_SNIPPETS_QUANTITY = 2;
    final Logger logger = getLogger(getClass());
    @Autowired
    private SolrServer solrServer;

    public QueryResponse queryByContentOrFilenameOrAuthorOrPathOrTitle(String query) {
	try {
	    QueryResponse queryResponse = solrServer.query(aQuery(query)
		    .withinFields(CONTENT_FIELD, FILENAME_FIELD, AUTHOR_FIELD, PATH_FIELD,
			    TITLE_FIELD).withExtendedDisjunctionMax().withEnabledHighlighting()
		    .withHighlightedSnippets(HIGHLIGHTED_SNIPPETS_QUANTITY).build());
	    return queryResponse;

	} catch (SolrServerException e) {
	    logger.error(e.getMessage());
	    throw new DocumentRepositoryException(query, e);
	}
    }
}
