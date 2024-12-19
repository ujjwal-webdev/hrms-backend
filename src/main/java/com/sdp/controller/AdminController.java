package com.sdp.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sdp.Enum.EmployeeOrAdmin;
import com.sdp.Enum.LeaveStatus;
import com.sdp.Enum.Role;
import com.sdp.dto.AddEmployeeDto;
import com.sdp.dto.AdminDto;
import com.sdp.dto.AuthenticatedResponseDto;
import com.sdp.dto.DepartmentDto;
import com.sdp.dto.EquipmentDto;
import com.sdp.dto.GetEmployeeDto;
import com.sdp.dto.GroupWorkDto;
import com.sdp.dto.LeaveDto;
import com.sdp.dto.LoginDto;
import com.sdp.dto.UpdatePasswordDto;
import com.sdp.dto.WorkDto;
import com.sdp.exception.DepartmentException;
import com.sdp.exception.EmployeeException;
import com.sdp.exception.EquipmentException;
import com.sdp.exception.LeaveException;
import com.sdp.exception.PasswordException;
import com.sdp.exception.SalaryException;
import com.sdp.exception.WorkException;
import com.sdp.model.Department;
import com.sdp.model.Employee;
import com.sdp.model.Equipment;
import com.sdp.model.Salary;
import com.sdp.model.Work;
import com.sdp.repository.EmployeeRepository;
import com.sdp.service.DepartmentService;
import com.sdp.service.EmployeeService;
import com.sdp.service.EquipmentService;
import com.sdp.service.LeaveService;
import com.sdp.service.SalaryService;
import com.sdp.service.WorkService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@CrossOrigin("*")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private LeaveService leaveService;

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private WorkService workService;
	
	@Autowired
	private EquipmentService equipmentService;
	
	@Autowired
	private SalaryService salaryService;

	/*
	 * HR Login and profile related APIs
	 */

	// register first HR
	@GetMapping("/register")
	public ResponseEntity<String> addAdmin()
	{
		Optional<Employee> opt = employeeRepo.findByUserName("admin1");

		if(!opt.isPresent()) {
			Employee admin = new Employee();
			admin.setName("admin");
			admin.setUserName("admin1");
			admin.setSalary(100000.0);
			admin.setRole(Role.HR);
			admin.setPassword(encoder.encode("admin1"));
			admin.setEmployeeOrAdmin(EmployeeOrAdmin.ADMIN);
			admin.setPhoneNo("+491234567890");	
			employeeRepo.save(admin);
		}
		
		return new ResponseEntity<String>("admin has been registered",HttpStatus.CREATED);
	}

	// HR login API
	@PostMapping("/login")
	public ResponseEntity<AuthenticatedResponseDto> login(@Valid @RequestBody LoginDto loginDto)
	{
		AuthenticatedResponseDto authenticatedResponseDto = employeeService.login(loginDto);

		return new ResponseEntity<AuthenticatedResponseDto>(authenticatedResponseDto, HttpStatus.OK);
	}

	// ViewProfile API
	@GetMapping("/viewProfile")
	public ResponseEntity<AdminDto> viewProfile()
	{
		AdminDto getAdminDto = employeeService.viewProfileAdmin();

		return new ResponseEntity<AdminDto>(getAdminDto, HttpStatus.OK);
	}

	// RegisterHR API
	@PostMapping("/registerAdmin") //admin
	public ResponseEntity<AdminDto> registerAdmin(@RequestBody Employee employee)
	{
		AdminDto getAdminDto = employeeService.registerAdmin(employee);

		return new ResponseEntity<AdminDto>(getAdminDto, HttpStatus.OK);
	}
	
	// UpdatePassword API
	@PutMapping("/updatePassword") //password
	public ResponseEntity<String> updatePassword(@RequestBody UpdatePasswordDto dto) throws PasswordException
	{
		String message = employeeService.updatePassword(dto);
		
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}

	/*
	 * Employee Related APIs
	 */

	// Register Employee by Department ID API
	@PostMapping("/employees/{deptId}")
	public ResponseEntity<AddEmployeeDto> registerEmployeeHandler(@PathVariable("deptId") Integer departmentId,
			@RequestBody Employee employee) throws DepartmentException
	{
		AddEmployeeDto addEmployeeDto = employeeService.addEmployee(departmentId, employee);

		return new ResponseEntity<AddEmployeeDto>(addEmployeeDto, HttpStatus.CREATED);
	}

	// Get Employee by ID API
	@GetMapping("/employees/byEmployeeId/{empId}")
	public ResponseEntity<GetEmployeeDto> getEmployeeByEmpIdHandler(@PathVariable("empId") Integer employeeId)
			throws EmployeeException
	{
		GetEmployeeDto getEmployeeDto = employeeService.getEmployeeByEmpId(employeeId);

		return new ResponseEntity<GetEmployeeDto>(getEmployeeDto, HttpStatus.OK);
	}

	// Get Employee by Username API
	@GetMapping("/employees/byUserName/{userName}")
	public ResponseEntity<GetEmployeeDto> getEmployeeByEmpUserNameHandler(@PathVariable("userName") String userName)
			throws EmployeeException
	{
		GetEmployeeDto getEmployeeDto = employeeService.getEmployeeByEmpUserName(userName);

		return new ResponseEntity<GetEmployeeDto>(getEmployeeDto, HttpStatus.OK);
	}

	// Get all Employees API
	@GetMapping("/employees")
	public ResponseEntity<List<GetEmployeeDto>> getAllEmployeesHandler() throws EmployeeException
	{
		List<GetEmployeeDto> getEmployeeDtos = employeeService.getAllEmployees();

		return new ResponseEntity<List<GetEmployeeDto>>(getEmployeeDtos, HttpStatus.OK);
	}

	// Patch existing employee role API
	@PatchMapping("/employees/setNewRole/{id}/{role}")
	public ResponseEntity<GetEmployeeDto> changeEmployeeRoleHandler(@PathVariable("id") Integer employeeId,
			@PathVariable("role") Role newRole) throws EmployeeException
	{
		GetEmployeeDto getEmployeeDto = employeeService.changeEmployeeRole(employeeId, newRole);

		return new ResponseEntity<GetEmployeeDto>(getEmployeeDto, HttpStatus.OK);
	}

	// Patch existing employee salary API
	@PatchMapping("/employees/setNewSalary/{id}/{salary}/{bonusThatYear}/{benefitPoints}")
	public ResponseEntity<GetEmployeeDto> changeEmployeeSalaryHandler(@PathVariable("id") Integer employeeId,
			@PathVariable("salary") Double newSalary, @PathVariable("bonusThatYear") Double bonusThatYear, @PathVariable("benefitPoints") Double benefitPoints) throws EmployeeException
	{
		GetEmployeeDto getEmployeeDto = employeeService.changeEmployeeSalary(employeeId, newSalary, bonusThatYear, benefitPoints);

		return new ResponseEntity<GetEmployeeDto>(getEmployeeDto, HttpStatus.OK);
	}

	// Get salary_details of employee API
	@GetMapping("/employees/salaryInfo/{empId}")
	public ResponseEntity<List<Salary>> getSalariesByEmployeeIdHandler(@PathVariable("empId") Integer employeeId)
	        throws EmployeeException, SalaryException
	{
	    List<Salary> salaries = salaryService.getSalariesByEmployeeId(employeeId);

	    return new ResponseEntity<List<Salary>>(salaries, HttpStatus.OK);
	}

	// Get equipment_details of employee API
	@GetMapping("/employees/equipmentInfo/{empId}")
	public ResponseEntity<List<Equipment>> getEquipmentsByEmployeeIdHandler(@PathVariable("empId") Integer employeeId)
			throws EmployeeException, EquipmentException
	{
		List<Equipment> equipments = equipmentService.getEquipmentsByEmployeeId(employeeId);

		return new ResponseEntity<List<Equipment>>(equipments, HttpStatus.OK);
	}

	// Patch existing employee department API
	@PatchMapping("/employees/setNewDepartment/{empId}/{deptId}")
	public ResponseEntity<GetEmployeeDto> changeEmployeeDepartmentHandler(@PathVariable("empId") Integer employeeId,
			@PathVariable("deptId") Integer departmentId) throws EmployeeException, DepartmentException
	{
		GetEmployeeDto getEmployeeDto = employeeService.changeEmployeeDepartment(employeeId, departmentId);

		return new ResponseEntity<GetEmployeeDto>(getEmployeeDto, HttpStatus.OK);
	}

	// Remove employee API
	@DeleteMapping("/employees/{empId}")
	public ResponseEntity<GetEmployeeDto> deleteEmployeeHandler(@PathVariable("empId") Integer employeeId)
			throws EmployeeException
	{
		GetEmployeeDto getEmployeeDto = employeeService.deleteEmployee(employeeId);

		return new ResponseEntity<GetEmployeeDto>(getEmployeeDto, HttpStatus.OK);
	}
	
	/*
	 * Equipment Related APIs
	 */

	// Assigning equipment API
	@PostMapping("/equipments/{empId}")
	public ResponseEntity<String> assignEquipment(@PathVariable("empId") Integer empId,@RequestBody Equipment equipment) throws EmployeeException
	{
		String str = equipmentService.assignEquipment(empId, equipment);
		
		return new ResponseEntity<String>(str,HttpStatus.OK);
	}

	// Unassigning equipment API
	@DeleteMapping("/equipments/{equipmentId}")
	public ResponseEntity<EquipmentDto> deleteEquipment(@PathVariable("equipmentId") Integer equipmentId) throws EquipmentException
	{
		EquipmentDto dto  = equipmentService.deleteEquipment(equipmentId);
		
		return new ResponseEntity<EquipmentDto>(dto,HttpStatus.OK);
	}
	
	/*
	 * Department Related APIs
	 */
	
	// Add department API
	@PostMapping("/departments")
	public ResponseEntity<DepartmentDto> addDepartmentHandler(@Valid @RequestBody Department department) throws DepartmentException
	{
		DepartmentDto departmentDto = departmentService.addDepartment(department);
		
		return new ResponseEntity<DepartmentDto>(departmentDto, HttpStatus.CREATED);
	}

	//	Update department name API
	@PutMapping("/departments/{id}")
	public ResponseEntity<DepartmentDto> updateDepartmentNameHandler(@PathVariable("id") Integer deptId,@RequestBody Department department) throws DepartmentException
	{
		DepartmentDto departmentDto = departmentService.updateDepartmentName(deptId, department);
		
		return new ResponseEntity<DepartmentDto>(departmentDto,HttpStatus.OK);
	}
	
	// Delete department API
	@DeleteMapping("/departments/{id}")
	public ResponseEntity<DepartmentDto> deleteDepartmentHandler(@PathVariable("id") Integer deptId) throws DepartmentException
	{
		DepartmentDto departmentDto = departmentService.deleteDepartment(deptId);
		
		return new ResponseEntity<DepartmentDto>(departmentDto,HttpStatus.OK);
	}


	// Get department by ID API
	@GetMapping("/departments/{id}")
	public ResponseEntity<DepartmentDto> getDepartmentByIdHandler(@PathVariable("id") Integer deptId) throws DepartmentException
	{
		DepartmentDto departmentDto = departmentService.getDepartmentById(deptId);
		
		return new ResponseEntity<DepartmentDto>(departmentDto,HttpStatus.OK);
	}

	// Get all departments API
	@GetMapping("/departments")
	public ResponseEntity<List<DepartmentDto>> getAllDepartmentsHandler() throws DepartmentException
	{
		List<DepartmentDto> departments = departmentService.getAllDepartments();
		
		return new ResponseEntity<List<DepartmentDto>>(departments,HttpStatus.OK);
	}

	// Get department by name API
	@GetMapping("/departments/name/{name}")
	public ResponseEntity<DepartmentDto> getDepartmentByNameHandler(@PathVariable("name") String deptName) throws DepartmentException
	{
		DepartmentDto departments = departmentService.getDepartmentByName(deptName);
		
		return new ResponseEntity<DepartmentDto>(departments,HttpStatus.OK);
	}
	
	// Get department name ASC API
	@GetMapping("/departments/sortByNameAsc")
	public ResponseEntity<List<DepartmentDto>> getDepartmentsInAlphabaticalOrderHandler() throws DepartmentException
	{
		List<DepartmentDto> departments = departmentService.getDepartmentsInAlphabaticalOrder();
		
		return new ResponseEntity<List<DepartmentDto>>(departments,HttpStatus.OK);
	}
	
	// Get department name DESC API
	@GetMapping("/departments/sortByNameDesc")
	public ResponseEntity<List<DepartmentDto>> getDepartmentsInReverseAlphabaticalOrderHandler() throws DepartmentException
	{
		List<DepartmentDto> departments = departmentService.getDepartmentsInReverseAlphabaticalOrder();
		
		return new ResponseEntity<List<DepartmentDto>>(departments,HttpStatus.OK);
	}

	/*
	 * Leave Related APIs
	 * */

	// Get all leaves API
	@GetMapping("/allLeaves")
	public ResponseEntity<List<LeaveDto>> getAllLeaves() throws LeaveException
	{
		List<LeaveDto> dtos = leaveService.getAllLeavesHistory();
		
		return new ResponseEntity<List<LeaveDto>>(dtos,HttpStatus.OK);
	}

	// Get all leaves of employee API
	@GetMapping("/allLeaves/{empId}")
	public ResponseEntity<List<LeaveDto>> getLeavesOfParticularEmployee(@PathVariable("empId") Integer empId) throws LeaveException, EmployeeException
	{
		List<LeaveDto> dtos = leaveService.getLeavesOfParticularEmployee(empId);
		
		return new ResponseEntity<List<LeaveDto>>(dtos,HttpStatus.OK);
	}

	// Get pending leaves API
	@GetMapping("/allPendingLeaves")
	public ResponseEntity<List<LeaveDto>> getPendingLeaves() throws LeaveException
	{
		List<LeaveDto> dtos = leaveService.getPendingLeaves();
		
		return new ResponseEntity<List<LeaveDto>>(dtos,HttpStatus.OK);
	}

	// Leave response API
	@PatchMapping("/response/{leaveId}/{status}")
	public ResponseEntity<LeaveDto> responseToLeave(@PathVariable("leaveId") Integer leaveId,@PathVariable("status") LeaveStatus status) throws LeaveException
	{
		LeaveDto dto = leaveService.responseToLeave(leaveId, status);
		
		return new ResponseEntity<LeaveDto>(dto,HttpStatus.OK);
	}
	
	/*
	 * Project related APIs
	 * */

	// Assign Individual Project API
	@PostMapping("/work/{empId}")
	public ResponseEntity<String> assignIndividualWork(@PathVariable("empId") Integer empId,@RequestBody Work work) throws EmployeeException
	{
		String str = workService.assignWork(empId, work);
		
		return new ResponseEntity<String>(str,HttpStatus.OK);
	}

	// Unassign project API
	@DeleteMapping("/work/{workId}")
	public ResponseEntity<WorkDto> deleteWork(@PathVariable("workId") Integer workId) throws WorkException
	{
		WorkDto dto  = workService.deleteWork(workId);
		
		return new ResponseEntity<WorkDto>(dto,HttpStatus.OK);
	}

	// Update Project API
	@PutMapping("/work/{workId}")
	public ResponseEntity<WorkDto> updateWork(@PathVariable("workId") Integer workId,@RequestBody Work work) throws WorkException
	{
		WorkDto dto = workService.updateWork(workId, work);
		
		return new ResponseEntity<WorkDto>(dto,HttpStatus.OK);
	}

	// Assign Group project API
	@PostMapping("/work")
	public ResponseEntity<String> assignGroupWork(@RequestBody GroupWorkDto dto) throws EmployeeException
	{
		String str = workService.groupWork(dto);

		return new ResponseEntity<String>(str,HttpStatus.OK);
	}

	// Get all Projects API
	@GetMapping("/allWorks")
	public ResponseEntity<List<WorkDto>> getAllWorks() throws WorkException
	{
		List<WorkDto> dtos = workService.getAllWorks();
		
		return new ResponseEntity<List<WorkDto>>(dtos,HttpStatus.OK);
	}

	// Get all individual projects API
	@GetMapping("/allIndividualWorks")
	public ResponseEntity<List<WorkDto>> getAllIndividualWorks() throws WorkException
	{
		List<WorkDto> dtos = workService.getAllIndividualWorks();
		
		return new ResponseEntity<List<WorkDto>>(dtos,HttpStatus.OK);
	}

	// Get all group projects API
	@GetMapping("/allGroupWorks")
	public ResponseEntity<List<WorkDto>> getAllGroupWorks() throws WorkException
	{
		List<WorkDto> dtos = workService.getAllGroupWorks();
		
		return new ResponseEntity<List<WorkDto>>(dtos,HttpStatus.OK);
	}

	// Get all pending projects API
	@GetMapping("/allPendingWorks")
	public ResponseEntity<List<WorkDto>> getAllPendingWorks() throws WorkException
	{
		List<WorkDto> dtos = workService.getAllPendingWorks();
		
		return new ResponseEntity<List<WorkDto>>(dtos,HttpStatus.OK);
	}

	// Get all individual pending projects API
	@GetMapping("/allIndividualPendingWorks")
	public ResponseEntity<List<WorkDto>> getAllIndividualPendingWorks() throws WorkException
	{
		List<WorkDto> dtos = workService.getAllIndividualPendingWorks();
		
		return new ResponseEntity<List<WorkDto>>(dtos,HttpStatus.OK);
	}

	// Get all group pending projects API
	@GetMapping("/allGroupPendingWorks")
	public ResponseEntity<List<WorkDto>> getAllGroupPendingWorks() throws WorkException
	{
		List<WorkDto> dtos = workService.getAllGroupPendingWorks();
		
		return new ResponseEntity<List<WorkDto>>(dtos,HttpStatus.OK);
	}

	// Get all completed works projects API
	@GetMapping("/allCompletedWorks")
	public ResponseEntity<List<WorkDto>> getAllCompletedWorks() throws WorkException
	{
		List<WorkDto> dtos = workService.getAllCompletedWorks();
		
		return new ResponseEntity<List<WorkDto>>(dtos,HttpStatus.OK);
	}

	// Get all individual completed projects API
	@GetMapping("/allIndividualCompletedWorks")
	public ResponseEntity<List<WorkDto>> getAllIndividualCompletedWorks() throws WorkException
	{
		List<WorkDto> dtos = workService.getAllIndividualCompletedWorks();
		
		return new ResponseEntity<List<WorkDto>>(dtos,HttpStatus.OK);
	}

	// Get all group completed projects API
	@GetMapping("/allGroupCompletedWorks")
	public ResponseEntity<List<WorkDto>> getAllGroupCompletedWorks() throws WorkException
	{
		List<WorkDto> dtos = workService.getAllGroupCompletedWorks();
		
		return new ResponseEntity<List<WorkDto>>(dtos,HttpStatus.OK);
	}

	// Get all in completed projects API
	@GetMapping("/allInCompletedWorks")
	public ResponseEntity<List<WorkDto>> getAllInCompletedWorks() throws WorkException
	{
		List<WorkDto> dtos = workService.getAllInCompletedWorks();
		
		return new ResponseEntity<List<WorkDto>>(dtos,HttpStatus.OK);
	}

	// Get all individuals in completed projects API
	@GetMapping("/allIndividualInCompletedWorks")
	public ResponseEntity<List<WorkDto>> getAllIndividualInCompletedWorks() throws WorkException
	{
		List<WorkDto> dtos = workService.getAllIndividualInCompletedWorks();
		
		return new ResponseEntity<List<WorkDto>>(dtos,HttpStatus.OK);
	}

	// Get all groups in completed projects API
	@GetMapping("/allGroupInCompletedWorks")
	public ResponseEntity<List<WorkDto>> getAllGroupInCompletedWorks() throws WorkException
	{
		List<WorkDto> dtos = workService.getAllGroupInCompletedWorks();
		
		return new ResponseEntity<List<WorkDto>>(dtos,HttpStatus.OK);
	}
}
