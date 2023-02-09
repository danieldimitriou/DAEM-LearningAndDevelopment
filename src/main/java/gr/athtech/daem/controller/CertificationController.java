package gr.athtech.daem.controller;

import gr.athtech.daem.converter.CertificationConverter;
import gr.athtech.daem.domain.Authority;
import gr.athtech.daem.domain.Certification;
import gr.athtech.daem.dto.CertificationDTO;
import gr.athtech.daem.service.AuthorityService;
import gr.athtech.daem.service.BaseService;
import gr.athtech.daem.service.CertificationService;
import gr.athtech.daem.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("certifications")
@CrossOrigin
public class CertificationController {

	private final CertificationService certificationService;

	private final AuthorityService authorityService;

	private final CertificationConverter certificationConverter;

	protected BaseService<Certification> getBaseService() {
		return certificationService;
	}

	@PostMapping("/create")
	public ResponseEntity<ApiResponse<Certification>> createCertification(
			@RequestBody CertificationDTO certificationDTO) {
		Certification newCertification = certificationConverter.convertToEntity(certificationDTO);
		final Authority authority = newCertification.getCertificationAuthority();
		Optional<Authority> existingAuthority = authorityService.findByName(authority.getName());
		if (existingAuthority.isEmpty()) {
			authorityService.create(authority);
		} else {
			newCertification.setCertificationAuthority(existingAuthority.get());
		}
		final List<Certification> existingCertifications = certificationService.findByName(newCertification.getName());
		for (Certification existingCertification : existingCertifications) {
			if (authority.getName().equals(existingCertification.getCertificationAuthority().getName())) {
				newCertification = existingCertification;
				break;
			}
		}
		certificationService.create(newCertification);
		return new ResponseEntity<>(ApiResponse.<Certification>builder().data(newCertification).build(),
									HttpStatus.CREATED);
	}
}
