# Sky QA Assignment

## What this does
 This project will run Cucumber test (RestAssured Java) that will test the Articles
 
## Running the Tests

Run the TestRunnner java class which has been created in this below path, 
\src\test\java\runners\TestRunner.java

## Failing Test Scenarios
As per the requirement provided, 
the expected statuscode for below test scripts was 404 
but the actual statuscode returned was 400. So the below tests would fail while running this suite

Perform a POST action using valid endpoint
(path - /tech-test/articles/2 )

Perform a PUT action using valid endpoint
(path -	/tech-test/articles  )

Perform a DELETE action using valid endpoint
(path- 	/tech-test/articles  )

## Resolving SSLHandshakeException

To Resolve "SSLHandshakeException: sun.security.validator.ValidatorException: PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target",
Please follow the below steps to add the certificate,

1. Go to URL in your browser:
-chrome - click on site icon left to address in address bar, select "Certificate" -> "Details" -> "Export" and save in format "Der-encoded binary, single certificate".

2. Now you have file with keystore and you have to add it to your JVM. Determine location of cacerts files, eg. C:\Program Files (x86)\Java\jre1.6.0_22\lib\security\cacerts. 

3. Next import the example.cer file into cacerts in command line (may need administrator command prompt):

keytool -import -alias example -keystore  "C:\Program Files (x86)\Java\jre1.6.0_22\lib\security\cacerts" -file example.cer

You will be asked for password which default is changeit

Restart your IDE.


	
	
	
