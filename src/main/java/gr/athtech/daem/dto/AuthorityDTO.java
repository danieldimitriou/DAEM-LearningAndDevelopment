package gr.athtech.daem.dto;

import gr.athtech.daem.domain.Certification;
import gr.athtech.daem.domain.TypeOfInstitution;
import lombok.Data;

import java.util.List;

@Data
public class AuthorityDTO {

	private String name;
	private TypeOfInstitution awardingBody;
	private List<Certification> certifications;
}
