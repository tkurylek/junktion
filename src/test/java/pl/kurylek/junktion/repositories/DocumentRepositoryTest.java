package pl.kurylek.junktion.repositories;

import static org.fest.assertions.Assertions.assertThat;
import static pl.kurylek.junktion.test.builders.DocumentBuilder.aDocument;
import static pl.kurylek.junktion.test.builders.DocumentProfiledBuilder.MISSION_DOCUMENT_ID;
import static pl.kurylek.junktion.test.builders.DocumentProfiledBuilder.aMissionDocument;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import pl.kurylek.junktion.domain.Document;
import pl.kurylek.junktion.test.SolrIntegrationTestBase;

public class DocumentRepositoryTest extends SolrIntegrationTestBase {

    @Autowired
    DocumentRepository documentRepository;

    @Test
    public void shouldSaveDocument() {
	// given
	Document missionDocument = aMissionDocument().build();

	// when
	documentRepository.save(missionDocument);

	// then
	assertThat(documentRepository.findOne(MISSION_DOCUMENT_ID)).isEqualTo(missionDocument);
    }

    @Test
    public void shouldFindDocumentWithMatchingContnet() throws Exception {
	// given
	Document laboratoryRaport = aDocument().withId("lab-raport.pdf").withContent("Physics laboratory raport")
		.build();
	Document scholarshipRaport = aDocument().withId("scholarship-raport.pdf").withContent("Scholarship raport")
		.build();
	documentRepository.save(laboratoryRaport);
	documentRepository.save(scholarshipRaport);

	// when
	List<Document> result = documentRepository.findByContent("raport");

	// then
	assertThat(result).as("searching content matches document content").contains(laboratoryRaport,
		scholarshipRaport);
    }
}
