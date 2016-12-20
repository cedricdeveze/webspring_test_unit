package fr.treeptik.webspring.tests;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import fr.treeptik.webspring.model.Department;
import fr.treeptik.webspring.repositories.DepartmentDAO;
import fr.treeptik.webspring.services.DepartmentService;

public class EmployeeServiceTest {

	private DepartmentService departmentService;
	private DepartmentDAO mockDAO;
	
	private Department department;
	private List<Department> listDepartments;

	
	@Before
	//S'execute juste avant chaque exécution de méthode
	public void setUp() throws Exception {
		department = new Department();
		listDepartments = new ArrayList<>();
		
		mockDAO = createMock(DepartmentDAO.class);
		departmentService = new DepartmentService();
		departmentService.setDAO(mockDAO);
		
	}

	@After
	//S'execute juste après chaque exécution de méthode
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreate() {
		department.setName("Test Department (mock)");
		expect(mockDAO.save(anyObject(Department.class))).andReturn(department);
		replay(mockDAO);
		
		//departmentService.create(department);
		//2 lignes ci-dessous font la même chose que ligne ci-dessus
		Department result = departmentService.create(department);
		Assert.assertEquals(department, result);
		
		verify(mockDAO);
	}
	
	@Test
	public void testFindAll() {		
		expect(mockDAO.findAll()).andReturn(listDepartments); //expect : spécifie une valeur de retour attendue
		replay(mockDAO);	//replay : 
		departmentService.findAll();
		verify(mockDAO); //verify : 
	}

	@Test
	@Ignore //Permet de sauter un test mais on aura dans le rapport qu'il sera sauté (skipped)
	public void testFindById() { fail("Not yet implemented"); }

	@Test
	@Ignore
	public void testFindByName() { fail("Not yet implemented"); }

}
