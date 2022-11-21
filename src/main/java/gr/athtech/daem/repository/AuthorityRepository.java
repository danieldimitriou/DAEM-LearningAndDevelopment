package gr.athtech.daem.repository;

import gr.athtech.daem.domain.Authority;
import gr.athtech.daem.domain.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long> {

	Optional<Authority> findById(Long id);

	Optional<Authority> findByName(String name);

	//It may be better to input a list of certifications
	Optional<Authority> findByCertificationsId(Long certificationId);

	List<Authority> findByAwardingBodyId(Long awardingBodyId);

	void deleteAuthorityById(Long id);
}
