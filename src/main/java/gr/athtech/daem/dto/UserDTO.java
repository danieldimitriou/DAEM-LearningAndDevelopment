package gr.athtech.daem.dto;

import gr.athtech.daem.domain.Department;
import gr.athtech.daem.domain.Position;
import lombok.Data;

@Data
public class UserDTO {

	private String firstName;
	private String lastName;
	private String email;
	private Position position;
	private Department department;
}
