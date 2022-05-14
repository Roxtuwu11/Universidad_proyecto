package com.ibm.academia.apirest.ApiSpring.services;

import com.ibm.academia.apirest.ApiSpring.entities.Alumno;
import com.ibm.academia.apirest.ApiSpring.entities.Carrera;
import com.ibm.academia.apirest.ApiSpring.entities.Persona;
import com.ibm.academia.apirest.ApiSpring.repositories.AlumnoRepository;
import com.ibm.academia.apirest.ApiSpring.repositories.CarreraRepository;
import com.ibm.academia.apirest.ApiSpring.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("AlumnoDAOImpl")
public class AlumnoDAOImpl extends PersonaDAOImpl  implements AlumnoDAO
{
    @Autowired

    public AlumnoDAOImpl(@Qualifier("repositorioAlumnos")PersonaRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Persona> buscarAlumnoPorNombreCarrera(String nombre) {
        return ((AlumnoDAO)repository).buscarAlumnoPorNombreCarrera(nombre);
    }

    @Override
    @Transactional
    public Persona actualizar(Persona alumnoEncontrado, Persona alumno) {
        Persona alumnoActualizado=null;
        alumnoEncontrado.setNombre(alumno.getNombre());
        alumnoEncontrado.setApellido(alumno.getApellido());
        alumnoEncontrado.setDireccion(alumno.getDireccion());
        alumnoActualizado = repository.save(alumnoEncontrado);
        return alumnoActualizado;
    }

    @Override
    @Transactional
    public Persona asociarCarreraAlumno(Persona alumno, Carrera carrera)
    {
        ((Alumno)alumno).setCarrera(carrera);
        return repository.save(alumno);
    }
}
