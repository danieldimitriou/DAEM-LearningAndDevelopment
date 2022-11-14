package gr.athtech.daem.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AUTHORITY")
@SequenceGenerator(name = "idGenerator", allocationSize = 1)
public class Authority extends BaseModel {

	@Column(length = 255, nullable = false, unique = true)
	@NotNull
	@NotEmpty
	private String name;

	@ManyToOne(mappedBy = "authority", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private TypeOfInstitution awardingBody;

	@OneToMany(mappedBy = "certificationAuthority", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Certification> certifications;
}
