package gr.athtech.daem.bootstrap;

import gr.athtech.daem.base.BaseComponent;
import gr.athtech.daem.domain.Department;
import gr.athtech.daem.service.DepartmentService;
import gr.athtech.daem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Profile("generate-departments")
@Order(2)
public class DepartmentSampleContentCreator extends BaseComponent implements CommandLineRunner {

	private final DepartmentService departmentService;
	private final UserService userService;

	@Override
	public void run(final String... args) throws Exception {
		List<Department> departments = List.of(Department.builder().name("C-Level").members(
				userService.findUsersByFirstNameOrLastNameLikeIgnoreCase("stefan", "bordea")).headOfDepartment(
				userService.findByEmail("stefan@stefanbordea.com")).build());

		departmentService.createAll(departments);
	}
}
