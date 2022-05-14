package com.ibm.academia.apirest.ApiSpring.services;

import com.ibm.academia.apirest.ApiSpring.entities.Alumno;
import com.ibm.academia.apirest.ApiSpring.entities.Carrera;

import java.util.Optional;

public interface CarreraDAO extends GenericoDAO <Carrera> {

    public Iterable<Carrera> findCarreraByNombreContains (String nombre);
    public Iterable<Carrera> findCarreraByNombreContainsIgnoreCase (String nombre);
    public Iterable<Carrera> findCarreraByCantidadAniosAfter (Integer cantidadAnios);
    public Carrera actualizar(Carrera  carreraEncontrada, Carrera carrera);



}
