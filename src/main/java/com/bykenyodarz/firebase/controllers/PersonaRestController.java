package com.bykenyodarz.firebase.controllers;

import com.bykenyodarz.firebase.models.Persona;
import com.bykenyodarz.firebase.models.dto.PersonaDTO;
import com.bykenyodarz.firebase.services.apis.PersonaServiceAPI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/persona/api/")
public class PersonaRestController {

    private final PersonaServiceAPI serviceAPI;

    public PersonaRestController(PersonaServiceAPI serviceAPI) {
        this.serviceAPI = serviceAPI;
    }

    // Validador de campos
    public ResponseEntity<?> validar(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> errores.put(err.getField(), " El campo " + err.getField() + " " + err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errores);
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getAll() throws Exception {
        return ResponseEntity.ok().body(serviceAPI.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOne(@PathVariable String id) throws Exception {
        PersonaDTO personaDTO = serviceAPI.get(id);

        return personaDTO != null ? ResponseEntity.ok().body(personaDTO)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("no se encontró esta persona");

    }

    @PostMapping("save/{id}")
    public ResponseEntity<?> save(@RequestBody @Valid Persona persona, BindingResult result, @PathVariable String id) throws Exception {
        if (id == null || id.isEmpty() || id.equals("null")) {
            id = serviceAPI.save(persona);
        }
        id = serviceAPI.save(persona, id);
        return ResponseEntity.ok().body(id);
    }

}
