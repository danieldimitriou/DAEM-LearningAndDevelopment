package gr.athtech.daem.service;

import gr.athtech.daem.domain.Authority;
import gr.athtech.daem.domain.Certification;
import gr.athtech.daem.domain.Course;
import gr.athtech.daem.domain.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CertificationService extends BaseService<Certification> {

	Optional<Certification> findById(Long id);

	List<Certification> findByCertificationAuthority(Authority authority);

	List<Certification> findByName(String name);

	List<Certification> findByExpirationDate(Date date);

	List<Certification> findByHolders(List<User> holder);

	List<Certification> findByCourse(Course course);
}
