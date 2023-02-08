package gr.athtech.daem.dto;

import gr.athtech.daem.domain.Authority;
import lombok.Data;

@Data
public class CertificationDTO {

	private String name;
	private Authority certificationAuthority;
	//	private Date expirationDate;
}
