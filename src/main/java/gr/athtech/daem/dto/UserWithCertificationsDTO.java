package gr.athtech.daem.dto;

import gr.athtech.daem.domain.Certification;
import lombok.Data;

import java.util.List;

@Data
public class UserWithCertificationsDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private List<Certification> certifications;
}
