package gr.athtech.daem.repository;

import gr.athtech.daem.domain.Authority;
import gr.athtech.daem.domain.Certification;
import gr.athtech.daem.domain.Course;
import gr.athtech.daem.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, Long> {

	Optional<Certification> findById(Long id);

	List<Certification> findByCertificationAuthority(Authority authority);

	List<Certification> findByName(String name);

	List<Certification> findByExpirationDate(Date date);

	List<Certification> findByHolders(List<User> holder);

	List<Certification> findByCourse(Course course);
}
