package pl.kurylek.junktion.services;

import java.util.List;

import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.kurylek.junktion.domain.Document;
import pl.kurylek.junktion.dtos.DocumentSnaphot;
import pl.kurylek.junktion.repositories.DocumentRepository;

@Component
public class DocumentSearchService {

    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private DocumentSnapshotMapper documentSnapshotMapper;

    public List<DocumentSnaphot> findByContentOrFilenameOrAuthor(String query) {
	QueryResponse queryResponse = documentRepository.queryByContentOrFilenameOrAuthor(query);
	List<DocumentSnaphot> documentSnapshots = documentSnapshotMapper.map(queryResponse.getBeans(Document.class),
		queryResponse.getHighlighting());
	return documentSnapshots;
    }
}
