package use_case.EditProfile;

import junit.framework.TestCase;

public class EditProfileOutputDataTest extends TestCase {
    EditProfileOutputData editProfileOutputData = new EditProfileOutputData();
    public void test(){
        assertTrue(editProfileOutputData instanceof  EditProfileOutputData);
    }
}