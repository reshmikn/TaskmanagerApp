import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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

import com.taskmanager.Project;
import com.taskmanager.ProjectController;
import com.taskmanager.ProjectService;
import com.taskmanager.Task;
import com.taskmanager.TaskManagerController;
import com.taskmanager.TaskManagerService; 

@RunWith(MockitoJUnitRunner.class) 
public class ProjectManagerTest  
{ 
	@InjectMocks
    ProjectController projectController;
    
 
    @Mock
    ProjectService projectService; 
      
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
      
    @Test
    public void viewTaskTest() throws ParseException 
    { 
    	List<Project> projectList= new ArrayList<Project>();
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
    	
    	Project project = new Project();
    	project.setProjectName("pname");
    	project.setPriority(1);
    	project.setTasks(taskList);
    	project.setStartDate(startDate);
    	project.setEndDate(endDate);
    	projectList.add(project);
    	Mockito.when(projectService.viewProject()).thenReturn(projectList);

    	List<Project> pList = projectService.viewProject();
        
        assertEquals(1, pList.size());
        verify(projectService, times(1)).viewProject();
    } 
    
	
	  @Test public void testUpdateProject() throws ParseException {
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
		  Project project = new Project();
	    	project.setProjectName("pname");
	    	project.setPriority(1);
	    	project.setTasks(taskList);
	    	project.setStartDate(startDate);
	    	project.setEndDate(endDate);
	
	  
	  Mockito.when(projectService.findById(101)).thenReturn(project);
	  Project prj = (Project) projectService.findById(101);
	  assertEquals(project, prj);
	  Mockito.when(projectService.save(project)).thenReturn(project);
	  Project updatedProject= projectService. save(project);
	  verify(projectService, times(1)).save(project);
	  assertNotNull(updatedProject);
	  }
	 
} 

