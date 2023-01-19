package gr.athtech.daem.controller;

import gr.athtech.daem.converter.DepartmentConverter;
import gr.athtech.daem.domain.Department;
import gr.athtech.daem.dto.DepartmentDTO;
import gr.athtech.daem.service.BaseService;
import gr.athtech.daem.service.DepartmentService;
import gr.athtech.daem.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("departments")
@RequiredArgsConstructor
@CrossOrigin
public class DepartmentController{

	private final DepartmentService departmentService;

	private final DepartmentConverter departmentConverter;

	protected BaseService<Department> getBaseService() {
		return departmentService;
	}

	@GetMapping
	@Transactional
	public ResponseEntity<ApiResponse<List<DepartmentDTO>>> getAllDepartments() {
		final List<DepartmentDTO> departmentDTOList = departmentConverter.convertToDTO(getBaseService().findAll());
		return ResponseEntity.ok(ApiResponse.<List<DepartmentDTO>>builder().data(departmentDTOList).build());
	}

}
