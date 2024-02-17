package it.epicode.w6d5.controller;

import it.epicode.w6d5.DTO.DispositivoDTO;
import it.epicode.w6d5.exception.BadRequestException;
import it.epicode.w6d5.exception.ErrorResponse;
import it.epicode.w6d5.model.CustomResponse;
import it.epicode.w6d5.service.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class DispositivoController {
    @Autowired
    private DispositivoService dispositivoService;

    @GetMapping("/dispositivi")
    public CustomResponse getAll(Pageable pageable){
        return new CustomResponse(HttpStatus.OK.toString(), dispositivoService.getAll(pageable));
    }

    @GetMapping("/dispositivi/{id}")
    public CustomResponse getById(@PathVariable int id){
        return new CustomResponse(HttpStatus.OK.toString(), dispositivoService.getById(id));
    }

    @PostMapping("/dispositivi")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse save(@RequestBody @Validated DispositivoDTO dispositivoDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new BadRequestException(ErrorResponse.handleValidationMessages(bindingResult));
        return new CustomResponse(HttpStatus.CREATED.toString(), dispositivoService.save(dispositivoDTO));
    }

    @PutMapping("/dispositivi/{id}")
    public CustomResponse update(@PathVariable int id, @RequestBody @Validated DispositivoDTO dispositivoDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new BadRequestException(ErrorResponse.handleValidationMessages(bindingResult));
        return new CustomResponse(HttpStatus.OK.toString(), dispositivoService.update(id, dispositivoDTO));
    }

    @DeleteMapping("/dispositivi/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CustomResponse delete(@PathVariable int id){
        dispositivoService.delete(id);
        return new CustomResponse(HttpStatus.NO_CONTENT.toString(), null);
    }
}
