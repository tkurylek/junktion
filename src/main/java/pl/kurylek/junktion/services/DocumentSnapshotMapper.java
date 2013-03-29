package pl.kurylek.junktion.services;

import static pl.kurylek.junktion.domain.Document.CONTENT_FIELD;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import pl.kurylek.junktion.domain.Document;
import pl.kurylek.junktion.snapshots.DocumentSnaphot;

@Component
public class DocumentSnapshotMapper {

    public DocumentSnaphot map(Document document,
	    Map<String, Map<String, List<String>>> documentsHighlightings) {

	DocumentSnaphot documentSnaphot = new DocumentSnaphot();
	documentSnaphot.setModified(document.getModified());
	documentSnaphot.setPath(document.getPath());
	documentSnaphot.setFilename(document.getFilename());
	documentSnaphot.setSize(document.getSize());
	documentSnaphot.setAuthor(document.getAuthor());
	Map<String, List<String>> documentHighlighting = documentsHighlightings.get(document
		.getId());
	if (documentHighlighting != null) {
	    documentSnaphot.setHighlights(documentHighlighting.get(CONTENT_FIELD));
	}
	return documentSnaphot;
    }

    public List<DocumentSnaphot> map(List<Document> documents,
	    Map<String, Map<String, List<String>>> documentsHighlightings) {

	List<DocumentSnaphot> documentSnapshots = new LinkedList<>();
	for (Document document : documents) {
	    documentSnapshots.add(map(document, documentsHighlightings));
	}
	return documentSnapshots;
    }
}
