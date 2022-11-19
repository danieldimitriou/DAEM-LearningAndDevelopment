package gr.athtech.daem.repository;

import gr.athtech.daem.domain.Authority;
import gr.athtech.daem.domain.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long> {

	Optional<Authority> findById(Long aLong);

	Optional<Authority> findByName(String name);

	Optional<Authority> findByCertification(Certification certification);

	void deleteAuthorityById(Long id);
}
