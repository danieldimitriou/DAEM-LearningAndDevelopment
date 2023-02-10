package gr.athtech.daem.converter;

import gr.athtech.daem.domain.Position;
import gr.athtech.daem.dto.PositionDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PositionConverter {

	private final ModelMapper modelMapper;

	public Position convertToEntity(PositionDTO positionDTO) {
		return modelMapper.map(positionDTO, Position.class);
	}
}
