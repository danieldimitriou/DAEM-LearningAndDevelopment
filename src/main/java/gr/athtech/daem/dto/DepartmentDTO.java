package gr.athtech.daem.dto;

import lombok.Data;

import java.util.List;

@Data
public class DepartmentDTO {
	private Long id;
	private String name;
	private UserDTO headOfDepartment;
	private List<UserDTO> members;

}
