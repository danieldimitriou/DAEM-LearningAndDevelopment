package gr.athtech.daem.controller;

import gr.athtech.daem.domain.Department;
import gr.athtech.daem.service.BaseService;
import gr.athtech.daem.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("departments")
@RequiredArgsConstructor
public class DepartmentController extends BaseController<Department> {

	private final DepartmentService departmentService;

	@Override
	protected BaseService<Department> getBaseService() {
		return departmentService;
	}

}
