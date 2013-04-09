package pl.kurylek.junktion.test.builders;

import java.util.Date;

import pl.kurylek.junktion.domain.Document;
import pl.kurylek.utils.builders.Builder;

public class DocumentBuilder extends Builder<Document> {

    private static final Long ZERO = 0L;
    private static final String EMPTY = "";

    public static DocumentBuilder aDocument() {
	return new DocumentBuilder();
    }

    public DocumentBuilder empty() {
	return this.withAuthor(EMPTY).withContent(EMPTY).withFilename(EMPTY).withFilename(EMPTY)
		.withId(EMPTY).withPath(EMPTY).withSize(ZERO);
    }

    public DocumentBuilder withId(String id) {
	getBuildedObject().setId(id);
	return this;
    }

    public DocumentBuilder withContent(String content) {
	getBuildedObject().setContent(content);
	return this;
    }

    public DocumentBuilder withAuthor(String author) {
	getBuildedObject().setAuthor(author);
	return this;
    }

    public DocumentBuilder withPath(String path) {
	getBuildedObject().setPath(path);
	return this;
    }

    public DocumentBuilder withFilename(String filename) {
	getBuildedObject().setFilename(filename);
	return this;
    }

    public DocumentBuilder withSize(Long size) {
	getBuildedObject().setSize(size);
	return this;
    }

    public DocumentBuilder withTitle(String title) {
	getBuildedObject().setTitle(title);
	return this;
    }

    public DocumentBuilder withModified(Date date) {
	getBuildedObject().setModified(date);
	return this;
    }
}