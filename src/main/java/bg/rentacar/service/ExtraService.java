package bg.rentacar.service;


import bg.rentacar.model.dto.AllExtrasDTO;
import bg.rentacar.model.dto.ExtraDTO;

public interface ExtraService {

    void addExtra(ExtraDTO addExtraDTO);

    AllExtrasDTO getAllExtras();
}
