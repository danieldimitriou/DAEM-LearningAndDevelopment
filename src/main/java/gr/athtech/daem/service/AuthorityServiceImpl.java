package gr.athtech.daem.service;

import gr.athtech.daem.domain.Authority;
import gr.athtech.daem.domain.Certification;
import gr.athtech.daem.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl extends BaseServiceImpl<Authority> implements AuthorityService{

	public final AuthorityRepository authorityRepository;

	@Override
	public JpaRepository<Authority,Long> getRepository(){
		return authorityRepository;
	}

	@Override
	public Optional<Authority> findById(final Long id){
		return authorityRepository.findById(id);
	}

	@Override
	public Optional<Authority> findByName(final String name) {
		return authorityRepository.findByName(name);
	}

	//Given an array of certification ID's, returns a list
	//with all the authorities they were issued by in the same order as the certiifcation ID appear
	// in the given array.
	@Override
	public List<Authority> findByCertificationsId(final Long[] certificationsId) {
		List<Authority> authorities = new ArrayList<Authority>();
		for(long certId:certificationsId) {
				authorities.add(authorityRepository.findByCertificationsId(certId));
		}
		return authorities;
	}

	@Override
	public List<Authority> findByAwardingBodyId(final Long awardingBodyId) {
		return authorityRepository.findByAwardingBodyId(awardingBodyId);
	}
	@Override
	public void deleteAuthorityById(final Long id) {
		authorityRepository.deleteAuthorityById(id);
		logger.debug("{} authority with ID:{} has been deleted.",authorityRepository.findById(id),id);
	}

	@Override
	public Authority addCertification(final Authority authorityToBeUpdated, final Certification certification) {
		authorityToBeUpdated.getCertifications().add(certification);
		return authorityRepository.save(authorityToBeUpdated);
	}

	@Override
	public Authority updateAuthorityName(final Authority authorityToBeUpdated, final String newName) {
		authorityToBeUpdated.setName(newName);
		return authorityRepository.save(authorityToBeUpdated);
	}

	@Override
	public void deleteCertification(final Authority authorityToBeUpdated,
										 final Certification certificationToBeDeleted) {
		authorityToBeUpdated.getCertifications().remove(certificationToBeDeleted);

		logger.debug("Certification: {} deleted from the certifications list for the {} authority",
					 certificationToBeDeleted,authorityToBeUpdated);
	}
}
