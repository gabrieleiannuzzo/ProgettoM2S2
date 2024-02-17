package it.epicode.w6d5.DTO;

import it.epicode.w6d5.model.Stato;
import it.epicode.w6d5.model.Tipologia;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DispositivoDTO {
    private Tipologia tipologia;
    private Stato stato;
    // Non inserisco il not null perchè se un dispositivo è disponibile allora non deve essere assegnato a nessun dipendente
    // questo controllo è solo una questione di ordine e chiarezza nel programma, perchè ovviamente sarebbe comunque gestito da una NotFoundException
    @Min(value = 1, message = "L'id dipendente minimo accettato è 1")
    private Integer idDipendente;
}
