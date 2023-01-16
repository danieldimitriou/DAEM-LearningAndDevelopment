package gr.athtech.daem.converter;

import gr.athtech.daem.domain.Department;
import gr.athtech.daem.dto.DepartmentDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class DepartmentConverter {

	private final ModelMapper modelMapper;
	private final UserConverter userConverter;

	public Department convertToEntity(DepartmentDTO departmentDTO) {
		Department department = new Department();
		department.setName(departmentDTO.getName());
		department.setHeadOfDepartment(userConverter.convertToEntity(departmentDTO.getHeadOfDepartment()));
		department.setMembers(userConverter.convertToEntity(departmentDTO.getMembers()));
		return department;
	}


	public DepartmentDTO convertDepartmentToDTO(Department department) {
		DepartmentDTO departmentDTO = new DepartmentDTO();
		departmentDTO.setName(department.getName());
		departmentDTO.setHeadOfDepartment(userConverter.convertToDTO(department.getHeadOfDepartment()));
		departmentDTO.setMembers(userConverter.convertToDTO(department.getMembers()));
		return departmentDTO;
	}

	public List<DepartmentDTO> convertToDTO(List<Department> departments) {
		return departments.stream().map(this::convertDepartmentToDTO).toList();
	}
}
