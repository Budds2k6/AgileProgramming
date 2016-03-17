// JUnit Testing

@Test
// Lecturer adding student manually
public void testAddStudent ()
{
	// Test invalid studentID
	assertFalse("Fail! - Invalid student should not have been added; invalid ID!", addStudentMethod("Bob123"));
	
	// Test valid studentID, and not already registered
	assertTrue("Fail! - Student should have been added; valid entry!", addStudentMethod("ValidID123"));
	
	// Test valid student ID, and is already registered
	assertFalse("Fail! - Student should not have been added; already on list!", addStudentMethod("ValidID123"));
}

@Test
// Obtaining full list of modules
public void testModuleList ()
{
	// Test valid lecturer
	assertArrayEquals("Fail! - Module list should never be empty; every lecturer has modules!", getModuleList("ValidID123"), 0);
}

@Test
// Registration (via scanning) of student
public void testRegister ()
{
	// Test invalid studentID
	assertFalse("Fail! - Invalid student should not have been added; invalid ID!", doRegister("Bob123"));
	
	// Test valid studentID, and not already registered
	assertTrue("Fail! - Student should have been added; valid entry!", doRegister("ValidID123"));
	
	// Test valid student ID, and is already registered
	assertFalse("Fail! - Student should not have been added; already on list!", doRegister("ValidID123"));
}