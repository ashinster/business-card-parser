package shin.andrew.io.businesscardparser;

import java.io.IOException;

import org.springframework.stereotype.Component;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import shin.andrew.io.businesscardparser.data.ContactInfo;

/**
 * BusinessCardParser
 */
@Component
public class BusinessCardParser {
    
    private final String emailRegex = "\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}\\b";
    private final String phoneNumberRegex = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]\\d{4}$";

    public BusinessCardParser() throws ClassCastException, ClassNotFoundException, IOException {
    }

    public ContactInfo getContactInfo(String document){
        // parse document here


        return null;
    }

}