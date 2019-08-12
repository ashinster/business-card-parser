package shin.andrew.io.businesscardparser.data;

/**
 * ContactInfo POJO
 */
public class  ContactInfo {

    private String name;
    private String phoneNumber;
    private String emailAddress;  

    public ContactInfo(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    /**
     * Returns the full name of the individual 
     * @return The full name
     */
    public String getName(){
        return name;
    }

    /**
     * Returns the phone number formatted as a sequence of digits
     * @return The formatted phone number
     */
    public String getPhoneNumber(){
        return phoneNumber;
    }

    /**
     * Returns the email address of the inidividual
     * @return The email address
     */
    public String getEmailAddress(){
        return emailAddress;
    }
    
}