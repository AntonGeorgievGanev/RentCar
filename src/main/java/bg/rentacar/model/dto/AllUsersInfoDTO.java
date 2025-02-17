package bg.rentacar.model.dto;

import java.util.List;

public class AllUsersInfoDTO {
    private List<UserInfoDTO> usersInfoDTO;

    public List<UserInfoDTO> getUsersInfoDTO() {
        return usersInfoDTO;
    }

    public AllUsersInfoDTO() {
    }

    public AllUsersInfoDTO(List<UserInfoDTO> usersInfoDTO) {
        this.usersInfoDTO = usersInfoDTO;
    }

    public void setUsersInfoDTO(List<UserInfoDTO> usersInfoDTO) {
        this.usersInfoDTO = usersInfoDTO;
    }
}
