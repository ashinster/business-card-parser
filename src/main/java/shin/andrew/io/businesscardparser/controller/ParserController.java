package shin.andrew.io.businesscardparser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import shin.andrew.io.businesscardparser.BusinessCardParser;
import shin.andrew.io.businesscardparser.data.ContactInfo;

/**
 * ParserController
 */
@RestController
public class ParserController {

    @Autowired
    BusinessCardParser parser;

    @GetMapping(value="/parse")
    public ContactInfo parseDocument(@RequestBody String document) {
        
        ContactInfo contactInfo = parser.getContactInfo(document);

        return contactInfo;
    }
    
}