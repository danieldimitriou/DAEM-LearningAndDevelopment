package gr.athtech.daem.service;

import gr.athtech.daem.domain.Authority;
import gr.athtech.daem.domain.Certification;
import gr.athtech.daem.domain.Course;
import gr.athtech.daem.domain.User;
import gr.athtech.daem.repository.CertificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CertificationServiceImpl extends BaseServiceImpl<Certification> implements CertificationService {

	private final CertificationRepository certificationRepository;

	@Override
	public JpaRepository<Certification, Long> getRepository() {
		return certificationRepository;
	}

	@Override
	public Optional<Certification> findById(final Long id) {
		return Optional.empty();
	}

	@Override
	public List<Certification> findByCertificationAuthority(final Authority authority) {
		return certificationRepository.findByCertificationAuthority(authority);
	}

	@Override
	public List<Certification> findByName(final String name) {
		return certificationRepository.findByName(name);
	}

	@Override
	public List<Certification> findByExpirationDate(final Date date) {
		return certificationRepository.findByExpirationDate(date);
	}

	@Override
	public List<Certification> findByHolders(final List<User> holders) {
		return certificationRepository.findByHoldersIn(holders);
	}

	@Override
	public List<Certification> findByCourse(final Course course) {
		return certificationRepository.findByCourse(course);
	}
}
