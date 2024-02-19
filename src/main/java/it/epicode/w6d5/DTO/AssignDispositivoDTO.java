package it.epicode.w6d5.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AssignDispositivoDTO {
    @NotNull(message = "Id dipendente obbligatorio")
    @Min(value = 1, message = "L'id dipendente minimo accettato è 1")
    private Integer idDipendente;
}
