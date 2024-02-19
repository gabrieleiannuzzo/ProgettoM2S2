package it.epicode.w6d5.service;

import it.epicode.w6d5.DTO.AssignDispositivoDTO;
import it.epicode.w6d5.DTO.DispositivoDTO;
import it.epicode.w6d5.exception.BadRequestException;
import it.epicode.w6d5.exception.NotFoundException;
import it.epicode.w6d5.exception.WrongMatchException;
import it.epicode.w6d5.model.Dipendente;
import it.epicode.w6d5.model.Dispositivo;
import it.epicode.w6d5.model.Stato;
import it.epicode.w6d5.repository.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DispositivoService {
    @Autowired
    private DispositivoRepository dispositivoRepository;

    @Autowired
    private DipendenteService dipendenteService;

    public Page<Dispositivo> getAll(Pageable pageable){
        return dispositivoRepository.findAll(pageable);
    }

    public Dispositivo getById(int id){
        return dispositivoRepository.findById(id).orElseThrow(() -> new NotFoundException("Dispositivo con id = " + id + " non trovato"));
    }

    public Dispositivo save(DispositivoDTO dispositivoDTO){
        checkCombinations(dispositivoDTO);
        Dipendente dipendente = dispositivoDTO.getIdDipendente() == null ? null : dipendenteService.getById(dispositivoDTO.getIdDipendente());
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setStato(dispositivoDTO.getStato());
        dispositivo.setTipologia(dispositivoDTO.getTipologia());
        dispositivo.setDipendente(dipendente);
        return dispositivoRepository.save(dispositivo);
    }

    public Dispositivo update(int id, DispositivoDTO dispositivoDTO){
        checkCombinations(dispositivoDTO);
        Dipendente dipendente = dispositivoDTO.getIdDipendente() == null ? null : dipendenteService.getById(dispositivoDTO.getIdDipendente());
        Dispositivo dispositivo = getById(id);
        dispositivo.setStato(dispositivoDTO.getStato());
        dispositivo.setTipologia(dispositivoDTO.getTipologia());
        dispositivo.setDipendente(dipendente);
        return dispositivoRepository.save(dispositivo);
    }

    public Dispositivo assignToDipendente(int idDispositivo, AssignDispositivoDTO assignDispositivoDTO){
        Dispositivo dispositivo = getById(idDispositivo);
        if (dispositivo.getDipendente().getId() == assignDispositivoDTO.getIdDipendente()) throw new BadRequestException("Non puoi assegnare un dispositivo allo stesso dipendente a cui è già assegnato");
        Dipendente dipendente = dipendenteService.getById(assignDispositivoDTO.getIdDipendente());
        dispositivo.setStato(Stato.ASSEGNATO);
        dispositivo.setDipendente(dipendente);
        return dispositivoRepository.save(dispositivo);
    }

    public void delete(int id){
        Dispositivo dispositivo = getById(id);
        dispositivoRepository.delete(dispositivo);
    }

    private void checkCombinations(DispositivoDTO dispositivoDTO){
        if (dispositivoDTO.getStato() == Stato.ASSEGNATO && dispositivoDTO.getIdDipendente() == null) throw new WrongMatchException("Il dispositivo non può essere assegnato e non avere un dipendente associato allo stesso tempo");
        if (dispositivoDTO.getStato() != Stato.ASSEGNATO && dispositivoDTO.getIdDipendente() != null) throw new WrongMatchException("Il dispositivo non può essere " + dispositivoDTO.getStato().name().replace("_", " ").toLowerCase() + " ed avere un dipendente assegnato allo stesso tempo");
    }
}
