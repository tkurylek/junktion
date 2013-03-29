package pl.kurylek.junktion.repositories;

import static org.fest.assertions.Assertions.assertThat;
import static pl.kurylek.junktion.test.builders.DocumentProfiledBuilder.aStringTheoryBySheldonCooperDocument;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import pl.kurylek.junktion.domain.Document;
import pl.kurylek.junktion.repositories.DocumentRepository;
import pl.kurylek.junktion.test.SolrIntegrationTestBase;

public class DocumentRepositoryTest extends SolrIntegrationTestBase {

    @Autowired
    DocumentRepository documentRepository;

    @Test
    public void shouldFindByContentOrFilenameOrAuthor() throws Exception {
	// given
	Document stringTheory = aStringTheoryBySheldonCooperDocument().build();
	savedInRepository(stringTheory);

	// when
	List<Document> result = documentRepository.findByContentOrFilenameOrAuthor("theory");

	// then
	assertThat(result).containsOnly(stringTheory);
    }
}
