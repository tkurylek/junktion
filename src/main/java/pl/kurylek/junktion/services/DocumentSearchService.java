package pl.kurylek.junktion.services;

import java.util.List;

import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.kurylek.junktion.domain.Document;
import pl.kurylek.junktion.loaders.DocumentLoader;
import pl.kurylek.junktion.snapshots.DocumentSnaphot;

@Service
public class DocumentSearchService {

    @Autowired
    private DocumentLoader documentLoader;
    @Autowired
    private DocumentMapperService documentMapperService;

    public List<DocumentSnaphot> findByContentOrAuthorOrTitleOrPath(String query) {
	return findByContentOrAuthorOrTitleOrPath(query, 0);
    }

    public List<DocumentSnaphot> findByContentOrAuthorOrTitleOrPath(String query, int skip) {
	QueryResponse queryResponse = documentLoader.loadByContentOrAuthorOrTitleOrPath(query, skip);
	List<DocumentSnaphot> documentSnapshots = documentMapperService.map(
		queryResponse.getBeans(Document.class), queryResponse.getHighlighting());
	return documentSnapshots;
    }
}
