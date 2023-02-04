package gr.athtech.daem.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

	@JsonIgnore
	@ManyToMany(mappedBy = "certifications")
	private List<User> holders;

	@JsonBackReference(value = "course-certification")
	@OneToOne(mappedBy = "certification")
	private Course course;
}
