package InxhinieriSofti.projekt.DataObjects;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserDTO {

    private String idDTO;
    private String firstNameDTO;
    private String lastNameDTO;
    private String usernameDTO;
    private String passwordDTO;
    private String emailDTO;



    public UserDTO(){
    }


    public UserDTO(String firstNameDTO,
                   String lastNameDTO,
                   String usernameDTO,
                   String passwordDTO,
                   String emailDTO){
        this.firstNameDTO=firstNameDTO;
        this.lastNameDTO= lastNameDTO;
        this.usernameDTO=usernameDTO;
        this.passwordDTO=passwordDTO;
        this.emailDTO=emailDTO;
    }

    public String getIdDTO() {
        return this.idDTO;
    }

    public void setIdDTO(final String idDTO) {
        this.idDTO = idDTO;
    }

    public String getFirstNameDTO() {
        return this.firstNameDTO;
    }

    public void setFirstNameDTO(final String firstNameDTO) {
        this.firstNameDTO = firstNameDTO;
    }

    public String getLastNameDTO() {
        return this.lastNameDTO;
    }

    public void setLastNameDTO(final String lastNameDTO) {
        this.lastNameDTO = lastNameDTO;
    }

    public String getUsernameDTO() {
        return this.usernameDTO;
    }

    public void setUsernameDTO(final String usernameDTO) {
        this.usernameDTO = usernameDTO;
    }

    public String getPasswordDTO() {
        return this.passwordDTO;
    }

    public void setPasswordDTO(final String passwordDTO) {
        this.passwordDTO = passwordDTO;
    }

    public String getEmailDTO() {
        return this.emailDTO;
    }

    public void setEmailDTO(final String emailDTO) {
        this.emailDTO = emailDTO;
    }
}
