package bg.rentacar.service.extra.impl;

import bg.rentacar.model.dto.AddExtraDTO;
import bg.rentacar.model.entity.Extra;
import bg.rentacar.repository.ExtraRepository;
import bg.rentacar.service.extra.ExtraService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ExtraServiceImpl implements ExtraService {

    private final ExtraRepository extraRepository;

    private final ModelMapper mapper;

    public ExtraServiceImpl(ExtraRepository extraRepository, ModelMapper mapper) {
        this.extraRepository = extraRepository;
        this.mapper = mapper;
    }

    @Override
    public void addExtra(AddExtraDTO addExtraDTO) {
        Extra extra = mapper.map(addExtraDTO, Extra.class);
        extraRepository.save(extra);
    }
}
