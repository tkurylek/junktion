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

    public List<DocumentSnaphot> findByContentOrFilenameOrAuthorOrPathOrTitle(String query) {
	QueryResponse queryResponse = documentLoader.loadByContentOrFilenameOrAuthorOrPathOrTitle(query);
	List<DocumentSnaphot> documentSnapshots = documentMapperService.map(
		queryResponse.getBeans(Document.class), queryResponse.getHighlighting());
	return documentSnapshots;
    }
}
