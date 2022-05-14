package com.ibm.academia.apirest.ApiSpring.services;

import com.ibm.academia.apirest.ApiSpring.entities.Carrera;
import com.ibm.academia.apirest.ApiSpring.entities.Persona;

import java.util.Optional;

public interface PersonaDAO extends GenericoDAO  <Persona>
{
    public Optional <Persona> buscarPorNombreYApellido(String nombre, String apellido);
    public Optional <Persona> buscarPorDni(String dni);
    public Iterable <Persona> buscarPersonaPorApellido(String apellido);

    //Persona actualizarAlumno(Persona persona, Persona alumno);
}
