package gr.athtech.daem.dto;

import gr.athtech.daem.domain.Department;
import gr.athtech.daem.domain.Position;
import gr.athtech.daem.domain.Role;
import lombok.Data;

@Data
public class UserDTO {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private Position position;
	private Department department;
	private Role role;
}
