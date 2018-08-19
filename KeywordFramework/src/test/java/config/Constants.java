package config;

public class Constants {
	//This is the list of System Variables
    //Declared as 'public', so that it can be used in other classes of this project
    //Declared as 'static', so that we do not need to instantiate a class object
    //Declared as 'final', so that the value of this variable can be changed
    // 'String' & 'int' are the data type for storing a type of value	
	public static final String URL = "https://www.freecrm.com/index.html";
	public static final String Path_TestData = "C://Users//ravik//selenium-Frameworks//KeywordFramework//src//test//java//dataEngine//DataEngine.xlsx";
	public static final String Path_OR = "C://Users//ravik//selenium-Frameworks//KeywordFramework//src//test//java//config//OR.txt";
	public static final String File_TestData = "DataEngine.xlsx";
 
	//List of Data Sheet Column Numbers
	public static final int Col_TestCaseID = 0;	
	public static final int Col_TestScenarioID =1;
	//This is the new column for 'Page Object'
	public static final int Col_PageObject =4;
	//This column is shifted from 3 to 4
	public static final int Col_ActionKeyword =5;
	//New entry in Constant variable
	public static final int Col_RunMode =2;
	//New constants for column results in test cases and test steps sheets
	public static final int Col_Result =3;
	public static final int Col_DataSet =6;
	public static final int Col_TestStepResult =7;
 
	//To pass 'PASS' or 'FAIL' for test cases and test steps
	public static final String KEYWORD_FAIL = "FAIL";
	public static final String KEYWORD_PASS = "PASS";
	
	//List of Data Engine Excel sheets
	public static final String Sheet_TestSteps = "Test Steps";
	//New entry in Constant variable for test case sheet
    public static final String Sheet_TestCases = "Test Cases";
 
}
