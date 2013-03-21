package pl.kurylek.junktion.test.builders;

import pl.kurylek.junktion.domain.Document;
import pl.kurylek.utils.test.builders.Builder;

public class DocumentBuilder extends Builder<Document> {

    public static DocumentBuilder aDocument() {
	return new DocumentBuilder();
    }

    public DocumentBuilder withId(String id) {
	getBuildedObject().setId(id);
	return this;
    }

    public DocumentBuilder withContent(String content) {
	getBuildedObject().setContent(content);
	return this;
    }
}
