package gr.athtech.daem.domain;

import lombok.Getter;
import lombok.ToString;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.io.Serial;
import java.io.Serializable;

@Getter
@ToString
@MappedSuperclass
public abstract class BaseModel implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idGenerator")
	@Column(updatable = false)
	private Long id;
}
