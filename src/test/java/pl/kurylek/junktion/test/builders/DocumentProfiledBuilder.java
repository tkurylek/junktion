package pl.kurylek.junktion.test.builders;

import static pl.kurylek.junktion.test.builders.DocumentBuilder.aDocument;
import pl.kurylek.utils.test.builders.Builder;

public class DocumentProfiledBuilder extends Builder<DocumentBuilder> {

    public static final String MISSION_DOCUMENT_CONTENT = "Mission obejectives: Kill Red Scull.";
    public static final String MISSION_DOCUMENT_ID = "dangerousMission.pdf";

    public static DocumentBuilder aMissionDocument() {
	return aDocument().withId(MISSION_DOCUMENT_ID).withContent(MISSION_DOCUMENT_CONTENT);
    }
}
