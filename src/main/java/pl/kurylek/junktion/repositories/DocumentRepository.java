package pl.kurylek.junktion.repositories;

import static org.slf4j.LoggerFactory.getLogger;
import static pl.kurylek.junktion.builders.SolrQueryBuilder.aQuery;
import static pl.kurylek.junktion.domain.Document.AUTHOR_FIELD;
import static pl.kurylek.junktion.domain.Document.CONTENT_FIELD;
import static pl.kurylek.junktion.domain.Document.PATH_FIELD;
import static pl.kurylek.junktion.domain.Document.TITLE_FIELD;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentRepository {

    public static final int DEFAULT_QUERY_SKIPPING = 0;
    private static final int TITLE_FIELD_BOOST = 2;
    private static final int PATH_FIELD_BOOST = 3;
    private static final int HIGHLIGHTED_SNIPPETS_QUANTITY = 2;
    final Logger logger = getLogger(getClass());
    @Autowired
    private SolrRepository solrRepository;

    public QueryResponse queryByContentOrAuthorOrTitleOrPath(String phrase) {
	return queryByContentOrAuthorOrTitleOrPath(phrase, DEFAULT_QUERY_SKIPPING);
    }

    public QueryResponse queryByContentOrAuthorOrTitleOrPath(String phrase, int skip) {
	logger.debug("Query for phrase: " + phrase + ", skipping first " + skip + " results.");
	return solrRepository.query(buildQueryByContentOrAuthorOrTitleOrPath(phrase, skip));
    }

    private SolrQuery buildQueryByContentOrAuthorOrTitleOrPath(String phrase, int skip) {
	return aQuery(phrase).withinField(CONTENT_FIELD).orWithinField(AUTHOR_FIELD)
		.orWithinBoostedField(TITLE_FIELD, TITLE_FIELD_BOOST)
		.orWithinBoostedField(PATH_FIELD, PATH_FIELD_BOOST).withExtendedDisjunctionMax()
		.withEnabledHighlighting().wtihSkippingFirst(skip)
		.withNumberOfHighlightedSnippets(HIGHLIGHTED_SNIPPETS_QUANTITY).build();
    }
}