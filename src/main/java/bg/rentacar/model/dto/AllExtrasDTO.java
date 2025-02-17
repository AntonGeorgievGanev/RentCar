package bg.rentacar.model.dto;

import java.util.List;

public class AllExtrasDTO {
    private List<ExtraDTO> allExtrasDTO;

    public AllExtrasDTO() {
    }

    public AllExtrasDTO(List<ExtraDTO> allExtrasDTO) {
        this.allExtrasDTO = allExtrasDTO;
    }

    public List<ExtraDTO> getAllExtrasDTO() {
        return allExtrasDTO;
    }

    public void setAllExtrasDTO(List<ExtraDTO> allExtrasDTO) {
        this.allExtrasDTO = allExtrasDTO;
    }
}
