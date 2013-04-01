package pl.kurylek.junktion.test.builders;

import static pl.kurylek.junktion.test.builders.DocumentBuilder.aDocument;
import pl.kurylek.utils.test.builders.Builder;

public class DocumentProfiledBuilder extends Builder<DocumentBuilder> {

    private static final String STRING_THEORY_TITLE = "The String Theory";
    public static final long STRING_THEORY_SIZE = 1337L;
    public static final String STRING_THEORY_CONTENT = "The theory states that the matter is built from strings.";
    public static final String STRING_THEORY_LOCATION = "/home/sheldon/Documents/research/string-theory.docx";
    public static final String STRING_THEORY_AUTHOR = "Dr Sheldon Cooper";
    public static final String STRING_THEORY_ID = "string-theory.docx";

    public static DocumentBuilder aStringTheoryBySheldonCooperDocument() {
	return aDocument().withId(STRING_THEORY_ID).withTitle(STRING_THEORY_TITLE)
		.withAuthor(STRING_THEORY_AUTHOR).withFilename(STRING_THEORY_ID)
		.withPath(STRING_THEORY_LOCATION).withSize(STRING_THEORY_SIZE)
		.withContent(STRING_THEORY_CONTENT);
    }
}
