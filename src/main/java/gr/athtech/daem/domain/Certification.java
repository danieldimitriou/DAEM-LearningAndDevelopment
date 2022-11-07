package gr.athtech.daem.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Certification extends BaseModel {

	private String name;
	private Authority certificationAuthority;
	private AreaOfStudy field;
	private Date expirationDate;
}
