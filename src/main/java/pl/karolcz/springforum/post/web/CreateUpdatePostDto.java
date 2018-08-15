package pl.karolcz.springforum.post.web;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class CreateUpdatePostDto {

    @NotBlank
    private String content;
}
