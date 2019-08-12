package shin.andrew.io.businesscardparser.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import shin.andrew.io.businesscardparser.BusinessCardParser;
import shin.andrew.io.businesscardparser.data.ContactInfo;

/**
 * ParserController
 */
@RestController
public class ParserController {
    private static final Logger logger = LoggerFactory.getLogger("ParserController.class");

    @Autowired
    BusinessCardParser parser;

    @PostMapping(value="/parse")
    public ContactInfo parseDocument(@RequestBody String document) {

        logger.info("Received data: {}", document);
        
        ContactInfo contactInfo = parser.getContactInfo(document);

        return contactInfo;
    }
    
}