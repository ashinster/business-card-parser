package shin.andrew.io.businesscardparser;

import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreEntityMention;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.PropertiesUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BusinessCardParserApplicationTests {

	StanfordCoreNLP pipeline = new StanfordCoreNLP(
		PropertiesUtils.asProperties(
			"annotators", "tokenize,ssplit,pos,lemma,ner", 
			"ssplit.newlineIsSentenceBreak", "always",
			"ner.model", "edu/stanford/nlp/models/ner/english.all.3class.distsim.crf.ser.gz",
			"ner.applyFineGrained", "false",
			"ner.applyNumericClassifiers", "false",
			"tokenize.language", "en"));

	@Test
	public void classifyString() throws ClassCastException, ClassNotFoundException, IOException {
		// String text = "Foobar Technologies\n" + 
		// 	"Analytic Developer\n" +
		// 	"Lisa Haung\n" +
		// 	"1234 Sentry Road\n" +
		// 	"Columbia, MD 12345\n" +
		// 	"Phone: 410-555-1234\n" +
		// 	"Fax: 410-555-4321\n" +
		// 	"lisa.haung@foobartech.com";

		String text = 
			"Arthur Wilson tree\n" +
			"Software Engineer\n" +
			"Decision & Security Technologies\n" +
			"ABC Technologies\n" +
			"123 North 11th Street\n" +
			"Suite 229\n" +
			"Arlington, VA 22209\n" +
			"Tel: +1 (703) 555-1259\n" +
			"Fax: +1 (703) 555-1200\n" +
			"awilson@abctech.com\n";

		// String text = 
		// 	"ASYMMETRIK LTD\n"+
		// 	"Mike Smith\n"+
		// 	"Senior Software Engineer\n"+
		// 	"(410)555-1234\n"+
		// 	"msmith@asymmetrik.com\n";

		// create a document object
		CoreDocument document = new CoreDocument(text);

		// annnotate the document
		pipeline.annotate(document);

		for(CoreEntityMention coreEntityMention: document.entityMentions() ) {
			System.out.print(coreEntityMention.text());
			System.out.print(" - ");
			System.out.println(coreEntityMention.entityType());
		}

		System.out.println();
	}

}
