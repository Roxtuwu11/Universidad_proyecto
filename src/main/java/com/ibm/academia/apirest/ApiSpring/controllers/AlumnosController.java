package com.ibm.academia.apirest.ApiSpring.controllers;

import com.ibm.academia.apirest.ApiSpring.entities.Carrera;
import com.ibm.academia.apirest.ApiSpring.entities.Persona;
import com.ibm.academia.apirest.ApiSpring.exceptions.BadRequestException;
import com.ibm.academia.apirest.ApiSpring.exceptions.NotFoundException;
import com.ibm.academia.apirest.ApiSpring.services.AlumnoDAO;
import com.ibm.academia.apirest.ApiSpring.services.CarreraDAO;
import com.ibm.academia.apirest.ApiSpring.services.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alumno")
public class AlumnosController
{
    @Autowired
    @Qualifier("AlumnoDAOImpl")
    private PersonaDAO alumnoDao;

    @Autowired
    private CarreraDAO carreraDao;

    /**
     * EndPoint que crea a alumno
     * @param alumno
     * @return un objeto persona al coodigo httpstatus
     * @autor XRMG
     */
    @PostMapping
    public ResponseEntity<?> crearAlumno(@RequestBody Persona alumno)
    {
        Persona alumnoGuardado= alumnoDao.guardar(alumno);
        return new ResponseEntity<Persona>(alumnoGuardado, HttpStatus.CREATED);
    }

    /**
     * Endpoint para listar alumnos
     * @return lista de alumnos
     * @Autor XRMG 11/05/2022
     */
    @GetMapping("alumnos/lista")
    public ResponseEntity<?> ObtenerTodos()
    {
        List<Persona> alumnos = (List<Persona>)alumnoDao.buscarTodos();
        if(alumnos.isEmpty())
            throw new BadRequestException("No existen alumnos");
        return new ResponseEntity<List<Persona>>(alumnos, HttpStatus.OK);
    }

    @GetMapping("/alumnoId/{alumnoId}")
    public ResponseEntity <?> buscarAlumnoPorId(@PathVariable(value = "alumnoId")  Integer alumnoId)
    {
        Optional<Persona> oAlumno = alumnoDao.buscarPorId(alumnoId);
        if(!oAlumno.isPresent())
            throw new NotFoundException(String.format("El alumno con el  ID: %d no existe", alumnoId));

        return new ResponseEntity<Persona>(oAlumno.get(),HttpStatus.OK);
    }


    @PostMapping("/upd/alumnoId/{alumnoId}")
    public ResponseEntity<?> actualizarAlumno(@PathVariable Integer alumnoId, @RequestBody Persona alumno)
    {
        Optional<Persona> oAlumno = alumnoDao.buscarPorId(alumnoId);
        if (!oAlumno.isPresent())
            throw new NotFoundException(String.format("El alumno con ID: %d no existe", alumnoId));

        Persona alumnoActualizado = ((AlumnoDAO)alumnoDao).actualizar(oAlumno.get(), alumno);
        return new ResponseEntity<Persona>(alumnoActualizado, HttpStatus.OK );
    }

    @PostMapping("/alumnoId/{alumnoId}/carrera/{carreraId}")
    public ResponseEntity<?> actualizarAlumno(@PathVariable Integer carreraId, @PathVariable Integer alumnoId)
    {
        Optional<Persona> oAlumno = alumnoDao.buscarPorId(alumnoId);
        if (!oAlumno.isPresent())
            throw new NotFoundException(String.format("El alumno con ID: %d no existe", alumnoId));

        Optional<Carrera> oCarrera = carreraDao.buscarPorId(carreraId);
        if (!oCarrera.isPresent())
            throw new NotFoundException(String.format("La carrera con ID: %d no existe", carreraId));

        Persona alumno =((AlumnoDAO)alumnoDao).asociarCarreraAlumno(oAlumno.get(), oCarrera.get());
        return new ResponseEntity<Persona>(alumno, HttpStatus.OK );
    }

}
