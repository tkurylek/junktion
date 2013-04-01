package pl.kurylek.junktion.loaders;

import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.kurylek.junktion.exceptions.DocumentNotFoundException;
import pl.kurylek.junktion.repositories.DocumentRepository;

@Component
public class DocumentLoader {

    @Autowired
    private DocumentRepository documentRepository;

    public QueryResponse loadByContentOrAuthorOrTitleOrPath(String query) {
	QueryResponse queryResponse = documentRepository.queryByContentOrAuthorOrTitleOrPath(query);
	throwExceptionWhenDocumentWasNotFound(query, queryResponse);
	return queryResponse;
    }

    private void throwExceptionWhenDocumentWasNotFound(String query, QueryResponse queryResponse) {
	if (queryResponse.getResults().size() <= 0) {
	    throw new DocumentNotFoundException(query);
	}
    }
}
