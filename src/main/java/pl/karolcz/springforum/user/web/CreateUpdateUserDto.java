package pl.karolcz.springforum.user.web;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
class CreateUpdateUserDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
