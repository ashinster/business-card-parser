package shin.andrew.io.businesscardparser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreEntityMention;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.PropertiesUtils;
import shin.andrew.io.businesscardparser.data.ContactInfo;

/**
 * BusinessCardParser
 */
@Component
public class BusinessCardParser {
    private static final Logger logger = LoggerFactory.getLogger("BusinessCardParser.class");

    private final String emailRegex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
    private final String phoneNumberRegex = "\\b((\\+?\\d{1,2}\\s?)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4})$";

    private Pattern emailPattern;
    private Pattern phoneNumberPattern;

    private StanfordCoreNLP pipeline;

    public BusinessCardParser() {
        // Initialize the NLP/NER
        pipeline = new StanfordCoreNLP(PropertiesUtils.asProperties(
            "annotators", "tokenize,ssplit,pos,lemma,ner",
            "ssplit.newlineIsSentenceBreak", "always", 
            "ner.model", "edu/stanford/nlp/models/ner/english.all.3class.distsim.crf.ser.gz", // Only use three classifiers: Location, Person, Organization
            "ner.applyFineGrained", "false",    // Dont dig deeper for NER type, not needed since we are only looking for names.
            "ner.applyNumericClassifiers", "false", // Dont evaluate numbers, not reliable for phone numbers
            "tokenize.language", "en"));

        // Initialize the regex/pattern matcher
        emailPattern = Pattern.compile(emailRegex);
        phoneNumberPattern = Pattern.compile(phoneNumberRegex);
    }

    public ContactInfo getContactInfo(String document) {

        long getContactInfoStartTime = System.currentTimeMillis();

        // Split each line as an element
        String lines[] = document.split("\\r?\\n");

        String name = "";
        String phoneNumber = "";
        String emailAddress = "";

        // Loop over each line and perform NER
        for (String line : lines) {
            // Initialize the document
            CoreDocument coreDocument = new CoreDocument(line);

            // Annote the line
            pipeline.annotate(coreDocument);

            // Check if the line has a PERSON entity mention
            if (coreDocument.entityMentions().size() == 1) {
                CoreEntityMention entityMention = coreDocument.entityMentions().get(0);
                String coreEntityType = entityMention.entityType();

                // See if entity is recognized as a PERSON
                if (coreEntityType.equals("PERSON")) {
                    name = line;
                    logger.debug("Found name: {}", name);
                }

            // Else look for phone numbers and email via Regex
            } else if (coreDocument.entityMentions().isEmpty()) {
                // Get Matcher results from Pattern
                Matcher emailMatcher = emailPattern.matcher(line);
                Matcher phoneNumberMatcher = phoneNumberPattern.matcher(line);

                // Email matching
                if (emailMatcher.find()) { 
                    emailAddress = emailMatcher.group();
                    logger.debug("Found email: {}", emailAddress);
                } 
                
                // Phone number matching
                else if (phoneNumber.isEmpty() && phoneNumberMatcher.find()) { // Phone number matching
                    phoneNumber = phoneNumberMatcher.group().replaceAll("[^\\d.]", ""); // only keep the numbers
                    logger.debug("Found number: {}", phoneNumber);
                }

            } 
        }

        logger.debug("getContactInfo total time: {} ms", System.currentTimeMillis() - getContactInfoStartTime);

        return new ContactInfo(name, phoneNumber, emailAddress);
    }

}