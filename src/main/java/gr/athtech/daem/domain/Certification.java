package gr.athtech.daem.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "CERTIFICATIONS")
@SequenceGenerator(name = "idGenerator", allocationSize = 1)
public class Certification extends BaseModel {

	@Column(length = 100, nullable = false)
	@NotEmpty
	@NotNull
	private String name;
	@ManyToOne
	private Authority certificationAuthority;

	@Temporal(TemporalType.DATE)
	private Date expirationDate;

	@ManyToMany(mappedBy = "certifications")
	private List<User> holders;

	@OneToOne(mappedBy = "certification")
	private Course course;
}
