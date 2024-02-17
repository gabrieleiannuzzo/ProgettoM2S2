package it.epicode.w6d5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "dipendenti")
public class Dipendente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenza_dipendenti")
    @SequenceGenerator(name = "sequenza_dipendenti", initialValue = 1, allocationSize = 1)
    private int id;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String urlFotoProfilo;
    @JsonIgnore
    @OneToMany(mappedBy = "dipendente")
    private List<Dispositivo> dispositivi;
}
