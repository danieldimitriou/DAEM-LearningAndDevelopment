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
		return modelMapper.map(departmentDTO, Department.class);
	}


	public DepartmentDTO convertDepartmentToDTO(Department department) {
		DepartmentDTO departmentDTO = new DepartmentDTO();
		departmentDTO.setId(department.getId());
		departmentDTO.setName(department.getName());
		departmentDTO.setHeadOfDepartment(department.getHeadOfDepartment() != null ? userConverter.convertToDTO(
				department.getHeadOfDepartment()) : null);
		departmentDTO.setMembers(
				department.getMembers() != null ? userConverter.convertToDTO(department.getMembers()) : null);
		return departmentDTO;
	}

	public List<DepartmentDTO> convertToDTO(List<Department> departments) {
		return departments.stream().map(this::convertDepartmentToDTO).toList();
	}
}
