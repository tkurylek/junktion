package pl.kurylek.junktion.test.builders;

import java.util.Date;

import pl.kurylek.junktion.domain.Document;
import pl.kurylek.utils.builder.Builder;

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
	getBuiltObject().setId(id);
	return this;
    }

    public DocumentBuilder withContent(String content) {
	getBuiltObject().setContent(content);
	return this;
    }

    public DocumentBuilder withAuthor(String author) {
	getBuiltObject().setAuthor(author);
	return this;
    }

    public DocumentBuilder withPath(String path) {
	getBuiltObject().setPath(path);
	return this;
    }

    public DocumentBuilder withFilename(String filename) {
	getBuiltObject().setFilename(filename);
	return this;
    }

    public DocumentBuilder withSize(Long size) {
	getBuiltObject().setSize(size);
	return this;
    }

    public DocumentBuilder withTitle(String title) {
	getBuiltObject().setTitle(title);
	return this;
    }

    public DocumentBuilder withModified(Date date) {
	getBuiltObject().setModified(date);
	return this;
    }
}