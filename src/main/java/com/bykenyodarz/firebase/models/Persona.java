package com.bykenyodarz.firebase.models;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Persona {

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

    private String telefono;

    private String direccion;

}
