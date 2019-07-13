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

@RunWith(MockitoJUnitRunner.class) 
public class TaskManagerTest  
{ 
	@InjectMocks
    TaskManagerController taskManagerController;
    
 
    @Mock
    TaskManagerService taskService; 
      
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
      
    @Test
    public void viewTaskTest() throws ParseException 
    { 
    	List<Task> taskList= new ArrayList<Task>();
    	 SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = dateformat.parse("17/07/2018");
        Date endDate = dateformat.parse("17/12/2018");
    	Task task = new Task();
    	task.setTask("task1");
    	task.setParentTask("parentTask1");
    	task.setPriority(1);
    	task.setStartDate(startDate);
    	task.setEndDate(endDate);
    	//taskService.viewTask(101);
    	taskList.add(task);
    	Mockito.when(taskService.viewTask(101)).thenReturn(taskList);

    	List<Task> tList = taskService.viewTask(101);
        
        assertEquals(1, tList.size());
        verify(taskService, times(1)).viewTask(101);
    } 
    
    @Test
    public void testGetTaskById() throws ParseException 
    { 
    	
    	 SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = dateformat.parse("17/07/2018");
        Date endDate = dateformat.parse("17/12/2018");
    	Task task = new Task();
    	task.setTask("task1");
    	task.setParentTask("parentTask1");
    	task.setPriority(1);
    	task.setStartDate(startDate);
    	task.setEndDate(endDate);
    	
    	Mockito.when(taskService.findById(101)).thenReturn(task);

    	Task tsk= (Task) taskService.findById(101);
    	assertEquals(task,tsk);
        verify(taskService, times(1)).findById(101);
    } 
} 

