package it.epicode.w6d5.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DipendenteDTO {
    @NotNull(message = "Username obbligatorio")
    @NotEmpty(message = "Lo username non può essere vuoto")
    private String username;
    @Email(message = "Formato email non valido")
    private String email;
    @NotNull(message = "Nome obbligatorio")
    @NotEmpty(message = "Il nome non può essere vuoto")
    private String nome;
    @NotNull(message = "Cognome obbligatorio")
    @NotEmpty(message = "Il cognome non può essere vuoto")
    private String cognome;
}
