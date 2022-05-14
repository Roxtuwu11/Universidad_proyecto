package com.ibm.academia.apirest.ApiSpring.entities;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable
public class Direccion implements Serializable {


    private String calle;
    private String numero;
    private String codigoPostal;
    private String departamento;
    private String localidad;
    private String piso;



}
