package it.epicode.w6d5.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "dispositivi")
public class Dispositivo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenza_dispositivi")
    @SequenceGenerator(name = "sequenza_dispositivi", initialValue = 1, allocationSize = 1)
    private int id;
    @Enumerated(EnumType.STRING)
    private Tipologia tipologia;
    @Enumerated(EnumType.STRING)
    private Stato stato;
    @ManyToOne
    @JoinColumn(name = "id_dipendente")
    private Dipendente dipendente;
}
