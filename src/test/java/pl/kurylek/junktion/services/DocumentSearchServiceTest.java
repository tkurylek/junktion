package pl.kurylek.junktion.services;

import static org.fest.assertions.Assertions.assertThat;
import static pl.kurylek.junktion.test.builders.DocumentProfiledBuilder.aStringTheoryBySheldonCooperDocument;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import pl.kurylek.junktion.domain.Document;
import pl.kurylek.junktion.snapshots.DocumentSnaphot;
import pl.kurylek.junktion.test.SolrIntegrationTestBase;

public class DocumentSearchServiceTest extends SolrIntegrationTestBase {

    private static final int FIRST = 0;

    @Autowired
    DocumentSearchService documentSearchService;

    @Autowired
    DocumentSnapshotMapper documentSnapshotMapper;

    @Test
    public void shouldFindByContentOrFilenameOrAuthor() throws Exception {
	// given
	Document stringTheoryDocument = aStringTheoryBySheldonCooperDocument().build();
	savedInRepository(stringTheoryDocument);

	// when
	List<DocumentSnaphot> results = documentSearchService
		.findByContentOrFilenameOrAuthor("theory");

	// then
	DocumentSnaphot firstResult = results.get(FIRST);
	assertThat(firstResult.getFilename()).isEqualTo(stringTheoryDocument.getFilename());
	assertThat(firstResult.getModified()).isEqualTo(stringTheoryDocument.getModified());
	assertThat(firstResult.getSize()).isEqualTo(stringTheoryDocument.getSize());
	assertThat(firstResult.getPath()).isEqualTo(stringTheoryDocument.getPath());
	assertThat(firstResult.getAuthor()).isEqualTo(stringTheoryDocument.getAuthor());
    }
}
