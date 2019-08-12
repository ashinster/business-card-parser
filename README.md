# Asymmetric Programming Challenge #1

This is a solution by Andrew Shin for [Asymmetric](https://asymmetrik.com/programming-challenges/)'s "Business Card OCP"   programming challenge.  

 This project uses the [Stanford CoreNLP library](https://stanfordnlp.github.io/CoreNLP/) in order to perform linguistic analysis on a piece of text. Specifically we will be using the [NER](https://nlp.stanford.edu/software/CRF-NER.html) tool which detects Locations, Persons, and Organizations.

## Running the Application

In order to build the project you must have at least Java 1.8 installed. If you do not have Maven installed you will still be able to build the project using the included `mvnw` Maven Wrapper.  

To run the project locally, run these commands in the project base directory:

	mvn clean install
	mvn spring-boot:run

OR (If no Maven installed)

	./mvnw clean install
	./mvnw spring-boot:run  
  
`mvn spring-boot:run` will spin up a local embedded Tomcat instance. If you wish to shut down the instance type `Ctrl+C`.

The local server will run on port `1337`.

## Using the Application

Once the server is up and running, you can navigate to http://localhost:1337/ to access the web application. 

![The web application should look like this](https://media.discordapp.net/attachments/518289321950707713/610594008229478441/unknown.png?width=770&height=291)
  

Inside the textarea, input the document text.

Example:  
![Example Input](https://cdn.discordapp.com/attachments/518289321950707713/610594492638167048/unknown.png)

Pressing `Submit` will send the data to the backend which will parse the data and output the detected entities.

Example:  
![Example Output](https://cdn.discordapp.com/attachments/518289321950707713/610595247780790272/unknown.png)


### Other test data:

> Bisoromi  
> Andrew Shin  
> Applications Developer  
> +1 222 222 2222  
> a.a.a@email.com  

> Bisoromi   
> Andrew  J  Shin   
> Intermediate  Applications  Developer  
> +1  222-222-2222  
> a.a.a@email.aa.aa.com.ed  

> Bisoromi  
> Andrew  J  Shin  
> Intermediate  Applications  Developer  
> +12222222222  
> a.a.a@email.aa.aa.com  

> Bisoromi  
> Andrew  J  Shin  
> Intermediate  Applications  Developer  
> +992222222222  
> a.a.a@email.aa.aa.com  