import static org.junit.Assert.assertEquals; 
import static org.mockito.Mockito.times; 
import static org.mockito.Mockito.verify;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
 import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.taskmanager.Task;
import com.taskmanager.TaskManagerController;
import com.taskmanager.TaskManagerService;
import com.taskmanager.User;
import com.taskmanager.UserController;
import com.taskmanager.UserService; 

@RunWith(MockitoJUnitRunner.class) 
public class UserTest  
{ 
	@InjectMocks
    UserController userController;
    
 
    @Mock
    UserService userService; 
      
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
      
    @Test
    public void viewUserTest() throws ParseException 
    { 
    	List<User> userList= new ArrayList<User>();
    	User user1 = new User();
    	user1.setEmployeeId("101");
    	user1.setFirstName("John");
    	user1.setLastName("Smith");
    	User user2 = new User();
    	user2.setEmployeeId("102");
    	user2.setFirstName("Jim");
    	user2.setLastName("John");
    	//taskService.viewTask(101);
    	userList.add(user1);
    	userList.add(user2);
    	Mockito.when(userService.viewUser()).thenReturn(userList);

    	List<User> usrList=userService.viewUser();     
        assertEquals(2, usrList.size());
        verify(userService, times(1)).viewUser();
    } 
    
   } 

