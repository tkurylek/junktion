package pl.kurylek.junktion.test.builders;

import java.util.Date;

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

    public DocumentBuilder withAuthor(String author) {
	getBuildedObject().setAuthor(author);
	return this;
    }

    public DocumentBuilder withLastModificationTime(Date modificationTime) {
	getBuildedObject().setModified(modificationTime);
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
}
