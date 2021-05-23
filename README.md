Notes:
	- Current test data has more than 1000 end points to test.
	- Current max number of threads 5
	- Batch size of a single thread is 200 requests

Test Suites Names:
	- unittest
	- gojeck

Set Up:
	- Install maven 3 or above
	- Install JDK 8
	- Import as maven project

Instruction To Run:
	- Run from eclipse
		- Run As maven
			- Goals: clean test -DtestSuite=NameOfYourTestSuite eg. unittest or gojeck
			- Above command will run test with default data files
			
			- To Run with different set of files use below command
				- Goals: clean test -DtestSuite=gojeck -Dfile1=somefilePath1.txt -Dfile2=somefilePath2.txt
				
		- Run from command line:
			- Open CMD & navigate to project home
			- Run "mvn clean test -DtestSuite=gojeck" command
			- Above command will run test with default data files
			
			- To Run with different set of files use below command
				- Run "mvn clean test -DtestSuite=gojeck -Dfile1=somefilePath1.txt -Dfile2=somefilePath2.txt"
				
Reporting:
	After completion of test open below path to view the test report:
		- .../gojek-api-assignment/target/surefire-reports/html/index.html
