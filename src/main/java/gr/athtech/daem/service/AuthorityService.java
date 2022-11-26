package gr.athtech.daem.service;

import gr.athtech.daem.domain.Authority;
import gr.athtech.daem.domain.Certification;

import java.util.List;
import java.util.Optional;

public interface AuthorityService extends BaseService<Authority> {


	Optional<Authority> findById(Long id);

	Optional<Authority> findByName(String name);

	Optional<Authority> findByCertificationsId(Long certificationsId);

	List<Authority> findByAwardingBodyId(Long awardingBodyId);

	void deleteAuthorityById(Long id);

	Authority addCertification(Authority authorityToBeUpdated, Certification certification);

	Authority updateAuthorityName(Authority authorityToBeUpdated, String newName);

	void deleteCertification(Authority authorityToBeUpdated, Certification certificationToBeDeleted);
}
