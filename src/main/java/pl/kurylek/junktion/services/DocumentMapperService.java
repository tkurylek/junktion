package pl.kurylek.junktion.services;

import static pl.kurylek.junktion.domain.Document.CONTENT_FIELD;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import pl.kurylek.junktion.domain.Document;
import pl.kurylek.junktion.snapshots.DocumentSnaphot;

@Service
public class DocumentMapperService {

    public List<DocumentSnaphot> map(List<Document> documents,
	    Map<String, Map<String, List<String>>> documentsHighlightings) {
	List<DocumentSnaphot> documentSnapshots = new LinkedList<>();
	for (Document document : documents) {
	    documentSnapshots.add(map(document, documentsHighlightings));
	}
	return documentSnapshots;
    }

    public DocumentSnaphot map(Document document,
	    Map<String, Map<String, List<String>>> documentsHighlightings) {
	DocumentSnaphot documentSnaphot = new DocumentSnaphot();
	documentSnaphot.setTitle(document.getTitle());
	documentSnaphot.setModified(document.getModified());
	documentSnaphot.setPath(document.getPath());
	documentSnaphot.setFilename(document.getFilename());
	documentSnaphot.setSize(document.getSize());
	documentSnaphot.setAuthor(document.getAuthor());
	setHighlightingWhenNotNull(documentSnaphot, documentsHighlightings.get(document.getId()));
	return documentSnaphot;
    }

    private void setHighlightingWhenNotNull(DocumentSnaphot documentSnaphot,
	    Map<String, List<String>> documentHighlighting) {
	if (documentHighlighting != null && documentHighlighting.get(CONTENT_FIELD) != null) {
	    for (String highlight : documentHighlighting.get(CONTENT_FIELD)) {
		documentSnaphot.addHighlight(highlight);
	    }
	}
    }
}
