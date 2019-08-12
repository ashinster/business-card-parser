package shin.andrew.io.businesscardparser;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import shin.andrew.io.businesscardparser.data.ContactInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BusinessCardParserApplicationTests {

	@Autowired
	private BusinessCardParser parser;

	@Test
	public void sample1(){
		String text = "Foobar Technologies\n" + 
			"Analytic Developer\n" +
			"Lisa Haung\n" +
			"1234 Sentry Road\n" +
			"Columbia, MD 12345\n" +
			"Phone: 410-555-1234\n" +
			"Fax: 410-555-4321\n" +
			"lisa.haung@foobartech.com";

		ContactInfo contactInfo = parser.getContactInfo(text);

		assertEquals("Lisa Haung",contactInfo.getName());
		assertEquals("4105551234",contactInfo.getPhoneNumber());
		assertEquals("lisa.haung@foobartech.com",contactInfo.getEmailAddress());

	}

	@Test
	public void sample2() {
		String text = 
			"Arthur Wilson\n" +
			"Software Engineer\n" +
			"Decision & Security Technologies\n" +
			"ABC Technologies\n" +
			"123 North 11th Street\n" +
			"Suite 229\n" +
			"Arlington, VA 22209\n" +
			"Tel: +1 (703) 555-1259\n" +
			"Fax: +1 (703) 555-1200\n" +
			"awilson@abctech.com\n";

		ContactInfo contactInfo = parser.getContactInfo(text);

		assertEquals("Arthur Wilson", contactInfo.getName());
		assertEquals("17035551259", contactInfo.getPhoneNumber());
		assertEquals("awilson@abctech.com", contactInfo.getEmailAddress());
	}

	@Test
	public void sample3() {
		String text = 
			"ASYMMETRIK LTD\n"+
			"Mike Smith\n"+
			"Senior Software Engineer\n"+
			"(410)555-1234\n"+
			"msmith@asymmetrik.com\n";

		ContactInfo contactInfo = parser.getContactInfo(text);

		assertEquals("Mike Smith", contactInfo.getName());
		assertEquals("4105551234", contactInfo.getPhoneNumber());
		assertEquals("msmith@asymmetrik.com", contactInfo.getEmailAddress());
	}

	@Test
	public void phoneNumberSpaces() throws ClassCastException, ClassNotFoundException, IOException {
		String text = 
			"Bisoromi\n"+
			"Andrew Shin\n"+
			"Applications Developer\n"+
			"+1 222 222 2222\n"+
			"a.a.a@email.com\n";

		ContactInfo contactInfo = parser.getContactInfo(text);

		assertEquals("Andrew Shin", contactInfo.getName());
		assertEquals("12222222222", contactInfo.getPhoneNumber());
		assertEquals("a.a.a@email.com", contactInfo.getEmailAddress());
	}

	@Test
	public void weirdEmail(){
		String text = 
			"Bisoromi\n"+
			"Andrew J Shin\n"+
			"Intermediate Applications Developer\n"+
			"+1 222-222-2222\n"+
			"a.a.a@email.aa.aa.com.ed\n";

		ContactInfo contactInfo = parser.getContactInfo(text);

		assertEquals("Andrew J Shin", contactInfo.getName());
		assertEquals("12222222222", contactInfo.getPhoneNumber());
		assertEquals("a.a.a@email.aa.aa.com.ed", contactInfo.getEmailAddress());
	}

	@Test
	public void noSpacePhoneNumber() {
		String text = 
			"Bisoromi\n"+
			"Andrew J Shin\n"+
			"Intermediate Applications Developer\n"+
			"+12222222222\n"+
			"a.a.a@email.aa.aa.com\n";

		ContactInfo contactInfo = parser.getContactInfo(text);

		assertEquals("Andrew J Shin", contactInfo.getName());
		assertEquals("12222222222", contactInfo.getPhoneNumber());
		assertEquals("a.a.a@email.aa.aa.com", contactInfo.getEmailAddress());
	}

	@Test
	public void twoDigitCountryCodeNoSpaces() {
		String text = 
			"Bisoromi\n"+
			"Andrew J Shin\n"+
			"Intermediate Applications Developer\n"+
			"+992222222222\n"+
			"a.a.a@email.aa.aa.com\n";

		ContactInfo contactInfo = parser.getContactInfo(text);

		assertEquals("Andrew J Shin", contactInfo.getName());
		assertEquals("992222222222", contactInfo.getPhoneNumber());
		assertEquals("a.a.a@email.aa.aa.com", contactInfo.getEmailAddress());
	}
}
