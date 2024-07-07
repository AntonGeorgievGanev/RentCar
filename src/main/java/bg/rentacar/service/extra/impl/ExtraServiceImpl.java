package bg.rentacar.service.extra.impl;

import bg.rentacar.model.dto.AllExtrasDTO;
import bg.rentacar.model.dto.ExtraDTO;
import bg.rentacar.model.entity.Extra;
import bg.rentacar.repository.ExtraRepository;
import bg.rentacar.service.extra.ExtraService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtraServiceImpl implements ExtraService {

    private final ExtraRepository extraRepository;

    private final ModelMapper mapper;

    public ExtraServiceImpl(ExtraRepository extraRepository, ModelMapper mapper) {
        this.extraRepository = extraRepository;
        this.mapper = mapper;
    }

    @Override
    public void addExtra(ExtraDTO addExtraDTO) {
        Extra extra = mapper.map(addExtraDTO, Extra.class);
        extraRepository.save(extra);
    }

    @Override
    public AllExtrasDTO getAllExtras() {
        List<Extra> allExtrasFromDb = extraRepository.findAll();
        List<ExtraDTO> extrasDTO = allExtrasFromDb.stream().map(e -> mapper.map(e, ExtraDTO.class)).toList();
        return new AllExtrasDTO(extrasDTO);
    }
}
