package gr.athtech.daem.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
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
@Table(name = "AREA_OF_STUDY")
@SequenceGenerator(name = "idGenerator", allocationSize = 1)
public class AreaOfStudy extends BaseModel {

	@Column(length = 255, nullable = false, unique = false)
	@NotEmpty
	@NotNull
	private String name;

	@Column(length = 255, nullable = false, unique = false)
	@NotEmpty
	@NotNull
	private String description;

	@ManyToMany(mappedBy = "areasOfStudy")
	private List<Course> courses;
}
