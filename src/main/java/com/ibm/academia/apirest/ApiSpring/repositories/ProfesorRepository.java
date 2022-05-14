package com.ibm.academia.apirest.ApiSpring.repositories;

import com.ibm.academia.apirest.ApiSpring.entities.Persona;
import org.springframework.data.jpa.repository.Query;

public interface ProfesorRepository extends PersonaRepository
{
    @Query("select p from Profesor p JOIN p.carreras c where c.nombre= ?1")
    Iterable<Persona> findProfesoresByCarrera(String carrera);

}
