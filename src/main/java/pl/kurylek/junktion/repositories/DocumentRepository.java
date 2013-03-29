package pl.kurylek.junktion.repositories;

import static org.slf4j.LoggerFactory.getLogger;
import static pl.kurylek.junktion.builders.SolrQueryBuilder.aQuery;
import static pl.kurylek.junktion.domain.Document.AUTHOR_FIELD;
import static pl.kurylek.junktion.domain.Document.CONTENT_FIELD;
import static pl.kurylek.junktion.domain.Document.FILENAME_FIELD;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.kurylek.junktion.domain.Document;
import pl.kurylek.junktion.exceptions.DocumentRepositoryException;

@Service
public class DocumentRepository {

    private static final int HIGHLIGHTED_SNIPPETS_QUANTITY = 2;
    final Logger logger = getLogger(getClass());
    @Autowired
    private SolrServer solrServer;

    public List<Document> findByContentOrFilenameOrAuthor(String query) {
	try {
	    QueryResponse queryResponse = solrServer.query(aQuery(query)
		    .withinFields(CONTENT_FIELD, FILENAME_FIELD, AUTHOR_FIELD).withExtendedDisjunctionMax()
		    .withEnabledHighlighting().withHighlightedSnippets(HIGHLIGHTED_SNIPPETS_QUANTITY).build());
	    return toDocumentsList(queryResponse);

	} catch (SolrServerException e) {
	    logger.error(e.getMessage());
	    throw new DocumentRepositoryException("Could not query for document matching \"" + query
		    + "\". Solr server exception: ", e);
	}
    }

    private List<Document> toDocumentsList(QueryResponse queryResponse) {
	return queryResponse.getBeans(Document.class);
    }
}
