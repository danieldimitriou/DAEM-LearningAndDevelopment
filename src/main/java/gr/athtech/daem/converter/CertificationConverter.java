package gr.athtech.daem.converter;

import gr.athtech.daem.domain.Certification;
import gr.athtech.daem.dto.CertificationDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class CertificationConverter {

	private final ModelMapper modelMapper;

	public Certification convertToEntity(CertificationDTO certificationDTO) {
		return modelMapper.map(certificationDTO, Certification.class);
	}

	public List<Certification> convertToEntity(List<CertificationDTO> certificationDTOList) {
		return certificationDTOList.stream().map(this::convertToEntity).toList();
	}

	public CertificationDTO convertToDTO(Certification certification) {
		return modelMapper.map(certification, CertificationDTO.class);
	}

	public List<CertificationDTO> convertToDTO(List<Certification> certifications) {
		return certifications.stream().map(this::convertToDTO).toList();
	}

}
