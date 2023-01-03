package gr.athtech.daem.dto;

import gr.athtech.daem.domain.User;
import lombok.Data;

import java.util.List;

@Data
public class DepartmentDTO {

	private String name;
	private UserDTO headOfDepartment;
	private List<UserDTO> members;

}
