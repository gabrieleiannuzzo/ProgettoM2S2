package it.epicode.w6d5.service;

import it.epicode.w6d5.DTO.DipendenteDTO;
import it.epicode.w6d5.exception.NotFoundException;
import it.epicode.w6d5.model.Dipendente;
import it.epicode.w6d5.repository.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DipendenteService {
    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    public Page<Dipendente> getAll(Pageable pageable){
        return dipendenteRepository.findAll(pageable);
    }

    public Dipendente getById(int id){
        return dipendenteRepository.findById(id).orElseThrow(() -> new NotFoundException("Dipendente con id = " + id + " non trovato"));
    }

    public Dipendente save(DipendenteDTO dipendenteDTO){
        Dipendente dipendente = new Dipendente();
        dipendente.setNome(dipendenteDTO.getNome());
        dipendente.setCognome(dipendenteDTO.getCognome());
        dipendente.setUsername(dipendenteDTO.getUsername());
        dipendente.setEmail(dipendenteDTO.getEmail());
        sendEmail(dipendenteDTO.getEmail());
        return dipendenteRepository.save(dipendente);
    }

    public Dipendente update(int id, DipendenteDTO dipendenteDTO){
        Dipendente dipendente = getById(id);
        dipendente.setNome(dipendenteDTO.getNome());
        dipendente.setCognome(dipendenteDTO.getCognome());
        dipendente.setUsername(dipendenteDTO.getUsername());
        dipendente.setEmail(dipendenteDTO.getEmail());
        return dipendenteRepository.save(dipendente);
    }

    public void delete(int id){
        Dipendente dipendente = getById(id);
        dipendenteRepository.delete(dipendente);
    }

    public Dipendente updateUrlFotoProfilo(int id, String url){
        Dipendente dipendente = getById(id);
        dipendente.setUrlFotoProfilo(url);
        return dipendenteRepository.save(dipendente);
    }

    public void sendEmail(String email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Registrazione nuovo dipendente");
        message.setText("Registrazione del nuovo dipendente effettuata con successo");
        javaMailSender.send(message);
    }
}
