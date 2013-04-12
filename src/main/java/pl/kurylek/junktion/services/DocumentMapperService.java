package pl.kurylek.junktion.services;

import static pl.kurylek.junktion.domain.Document.CONTENT_FIELD;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.kurylek.junktion.domain.Document;
import pl.kurylek.junktion.snapshots.DocumentSnaphot;
import pl.kurylek.utils.mapper.AbstractMapper;
import pl.kurylek.utils.mapper.MappingStrategy;

@Service
public class DocumentMapperService extends AbstractMapper<Document, DocumentSnaphot> {

    @Autowired
    private HighlightAwareHtmlEscaperService highlightAwareHtmlEscaperService;

    @Autowired
    private DateFormatterService dateFormatterService;

    private final MappingStrategy<Document, DocumentSnaphot> mappingStrategy = new MappingStrategy<Document, DocumentSnaphot>() {

	@Override
	public DocumentSnaphot map(Document document) {
	    DocumentSnaphot documentSnaphot = new DocumentSnaphot();
	    documentSnaphot.setTitle(highlightAwareHtmlEscaperService.escape(document.getTitle()));
	    documentSnaphot.setModified(dateFormatterService.format(document.getModified()));
	    documentSnaphot.setPath(highlightAwareHtmlEscaperService.escape(document.getPath()));
	    documentSnaphot.setFilename(highlightAwareHtmlEscaperService.escape(document.getFilename()));
	    documentSnaphot.setSize(document.getSize());
	    documentSnaphot.setAuthor(highlightAwareHtmlEscaperService.escape(document.getAuthor()));
	    return documentSnaphot;
	}

	@Override
	public Document mapReversely(DocumentSnaphot documentSnaphot) {
	    Document document = new Document();
	    document.setId(documentSnaphot.getPath());
	    document.setTitle(documentSnaphot.getTitle());
	    document.setModified(dateFormatterService.getDate(documentSnaphot.getModified()));
	    document.setPath(documentSnaphot.getPath());
	    document.setFilename(documentSnaphot.getFilename());
	    document.setSize(documentSnaphot.getSize());
	    document.setAuthor(documentSnaphot.getAuthor());
	    return document;
	}
    };

    @Override
    protected MappingStrategy<Document, DocumentSnaphot> getMappingStrategy() {
	return mappingStrategy;
    }

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
	DocumentSnaphot documentSnaphot = map(document);
	setHighlightingWhenNotNull(documentSnaphot, documentsHighlightings.get(document.getId()));
	return documentSnaphot;
    }

    private void setHighlightingWhenNotNull(DocumentSnaphot documentSnaphot,
	    Map<String, List<String>> documentHighlighting) {
	if (documentHighlighting != null && documentHighlighting.get(CONTENT_FIELD) != null) {
	    for (String highlight : documentHighlighting.get(CONTENT_FIELD)) {
		documentSnaphot.addHighlight(highlightAwareHtmlEscaperService
			.escapeRespectingHighlighting(highlight));
	    }
	}
    }
}