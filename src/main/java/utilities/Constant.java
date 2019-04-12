package utilities;

import utilities.functionalLibrary.CommonMethods;

public class Constant {
	
	public static final String PATH_TESTDATA =  CommonMethods.getRootDirectoyPath() + "//excel//";
	public static final String FILE_TESTDATA = "TestData.xlsx";

	//Test Data Sheet Columns
	public static final int COL_TESTCASENAME = 0;	
	public static final int COL_USRNAME = 1;
	public static final int COL_PASSWORD = 2;
	public static final int Col_IssueDescription = 3;
	public static final int Col_IssueImpact = 4;
}
