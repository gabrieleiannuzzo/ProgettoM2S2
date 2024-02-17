package it.epicode.w6d5.controller;

import com.cloudinary.Cloudinary;
import it.epicode.w6d5.DTO.DipendenteDTO;
import it.epicode.w6d5.exception.BadRequestException;
import it.epicode.w6d5.exception.ErrorResponse;
import it.epicode.w6d5.model.CustomResponse;
import it.epicode.w6d5.service.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Collectors;

@RestController
public class DipendenteController {
    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private Cloudinary cloudinary;

    @GetMapping("/dipendenti")
    public CustomResponse getAll(Pageable pageable){
        return new CustomResponse(HttpStatus.OK.toString(), dipendenteService.getAll(pageable));
    }

    @GetMapping("/dipendenti/{id}")
    public CustomResponse getById(@PathVariable int id){
        return new CustomResponse(HttpStatus.OK.toString(), dipendenteService.getById(id));
    }

    @PostMapping("/dipendenti")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse save(@RequestBody @Validated DipendenteDTO dipendenteDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new BadRequestException(ErrorResponse.handleValidationMessages(bindingResult));
        return new CustomResponse(HttpStatus.CREATED.toString(), dipendenteService.save(dipendenteDTO));
    }

    @PutMapping("/dipendenti/{id}")
    public CustomResponse update(@PathVariable int id, @RequestBody @Validated DipendenteDTO dipendenteDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new BadRequestException(ErrorResponse.handleValidationMessages(bindingResult));
        return new CustomResponse(HttpStatus.OK.toString(), dipendenteService.update(id, dipendenteDTO));
    }

    @DeleteMapping("/dipendenti/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CustomResponse delete(@PathVariable int id){
        dipendenteService.delete(id);
        return new CustomResponse(HttpStatus.NO_CONTENT.toString(), null);
    }

    @PatchMapping("/dipendenti/{id}/upload")
    public CustomResponse uploadFotoProfilo(@PathVariable int id, @RequestParam("upload") MultipartFile file) throws IOException {
        return new CustomResponse(HttpStatus.OK.toString(), dipendenteService.updateUrlFotoProfilo(id, (String)cloudinary.uploader().upload(file.getBytes(), new HashMap()).get("url")));
    }
}
