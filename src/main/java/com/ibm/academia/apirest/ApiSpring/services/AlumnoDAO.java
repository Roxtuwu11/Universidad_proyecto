package com.ibm.academia.apirest.ApiSpring.services;

import com.ibm.academia.apirest.ApiSpring.entities.Carrera;
import com.ibm.academia.apirest.ApiSpring.entities.Persona;

import java.util.Optional;

public interface AlumnoDAO extends PersonaDAO
{

    public Iterable <Persona> buscarAlumnoPorNombreCarrera(String nombre);
    public Persona asociarCarreraAlumno(Persona alumno, Carrera carrera);
    public Persona actualizar(Persona alumnoEncontrado, Persona alumno);

}
