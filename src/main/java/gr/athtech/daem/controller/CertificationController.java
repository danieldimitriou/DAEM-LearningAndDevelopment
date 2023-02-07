package gr.athtech.daem.controller;

import gr.athtech.daem.domain.Certification;
import gr.athtech.daem.service.BaseService;
import gr.athtech.daem.service.CertificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("certifications")
@CrossOrigin
public class CertificationController {

	private final CertificationService certificationService;

	protected BaseService<Certification> getBaseService() {
		return certificationService;
	}
}
