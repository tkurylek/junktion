package pl.kurylek.junktion.test.builders;

import static pl.kurylek.junktion.test.builders.DocumentBuilder.aDocument;

import java.util.Date;

import pl.kurylek.utils.builder.Builder;

public class DocumentProfiledBuilder extends Builder<DocumentBuilder> {

    public static final Date DEFAULT_MODIFIED = new Date(1L);
    public static final String DEFAULT_CONTENT = "Default lorem ispum";
    public static final long DEFUALT_SIZE = 1000L;
    public static final String DEFAULT_PATH = "/home/user/default-doc.docx";
    public static final String DEFUALT_USER = "user";
    public static final String DEFAULT_TITLE = "Default document";
    public static final String DEFAULT_ID = "default-doc.docx";

    public static final Date STRING_THEORY_MODIFIED = DEFAULT_MODIFIED;
    public static final String STRING_THEORY_TITLE = "The String Theory";
    public static final long STRING_THEORY_SIZE = 1337L;
    public static final String STRING_THEORY_CONTENT = "The theory states that the matter is built from strings.";
    public static final String STRING_THEORY_LOCATION = "/home/sheldon/Documents/research/string-theory.docx";
    public static final String STRING_THEORY_AUTHOR = "Dr Sheldon Cooper";
    public static final String STRING_THEORY_ID = "string-theory.docx";

    public static DocumentBuilder aStringTheoryBySheldonCooperDocument() {
	return aDocument().withId(STRING_THEORY_ID).withTitle(STRING_THEORY_TITLE)
		.withAuthor(STRING_THEORY_AUTHOR).withFilename(STRING_THEORY_ID)
		.withPath(STRING_THEORY_LOCATION).withSize(STRING_THEORY_SIZE)
		.withContent(STRING_THEORY_CONTENT).withModified(STRING_THEORY_MODIFIED);
    }

    public static DocumentBuilder aDefaultDocument() {
	return aDocument().withId(DEFAULT_ID).withTitle(DEFAULT_TITLE).withAuthor(DEFUALT_USER)
		.withFilename(DEFAULT_ID).withPath(DEFAULT_PATH).withSize(DEFUALT_SIZE)
		.withContent(DEFAULT_CONTENT).withModified(DEFAULT_MODIFIED);
    }
}