package gr.athtech.daem.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="CERTIFICATIONS")
@SequenceGenerator(name = "idGenerator",allocationSize = 1)
public class Certification extends BaseModel {

	@Column(length = 100, nullable = false, unique = false)
	@NotEmpty
	@NotNull
	private String name;
	@ManyToOne
	@JoinColumn(name="AUTHORITY_ID")
	private Authority certificationAuthority;

	@Temporal(TemporalType.DATE)
	@NotEmpty
	@NotNull
	private Date expirationDate;

	@ManyToMany(mappedBy = "certifications",fetch = FetchType.LAZY)
	private List<User> holders;

	@OneToOne(mappedBy = "certification")
	private Course course;
}
