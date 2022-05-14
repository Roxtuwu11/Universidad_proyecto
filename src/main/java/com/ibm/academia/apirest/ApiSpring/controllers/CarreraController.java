package com.ibm.academia.apirest.ApiSpring.controllers;

import com.ibm.academia.apirest.ApiSpring.entities.Carrera;
import com.ibm.academia.apirest.ApiSpring.exceptions.BadRequestException;
import com.ibm.academia.apirest.ApiSpring.exceptions.NotFoundException;
import com.ibm.academia.apirest.ApiSpring.services.CarreraDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.Collator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/carrera")
public class CarreraController
{
    @Autowired
    private CarreraDAO carreraDao;

    @GetMapping("/lista/carreras")
    public List<Carrera> buscarTodas()
    {
        List<Carrera> carreras = (List<Carrera>)carreraDao.buscarTodos();
        if(carreras.isEmpty())
            throw new BadRequestException("No existen carreras");
        return carreras;
    }
    @GetMapping("/id/{carreraId}")
    public Carrera buscarCarreraPorId(@PathVariable(value = "carreraId")  Integer carreraId)
    {
       Optional<Carrera> oCarrera = carreraDao.buscarPorId(carreraId);
       if(!oCarrera.isPresent())
           throw new BadRequestException(String.format("La carrera con ID: %d no existe", carreraId));

        return oCarrera.get();
    }
    @PostMapping
    public ResponseEntity<?> guardarCarrera(@Valid @RequestBody Carrera carrera, BindingResult result)
        {
            Map<String, Object> validaciones = new HashMap<String, Object>();
            if (result.hasErrors())
            {
                List <String> listaErrores =result.getFieldErrors()
                        .stream()
                        .map(errores -> "campo:" + errores.getField() + errores.getDefaultMessage())
                        .collect(Collectors.toList());
                validaciones.put("Lista errores", listaErrores);
                return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
            }
            Carrera carreraGuardada = carreraDao.guardar(carrera);
            return new ResponseEntity<Carrera>(carreraGuardada, HttpStatus.CREATED );
        }

    /**
     * EndPoint para actualizar un objeto de tipo carrera
     * @param carreraId
     * @param carrera
     * @return ub objeto de tipo cvarrera con la informacion actualizada
     * @NotFoundException en cado de que falle
     * auto XRMG 11/05/2022
     */
    @PostMapping("/upd/carreraId/{carreraId}")
    public ResponseEntity<?> actualizarCarrera(@PathVariable Integer carreraId, @RequestBody Carrera carrera)
    {
        Optional<Carrera> oCarrera = carreraDao.buscarPorId(carreraId);
        if (!oCarrera.isPresent())

            throw new NotFoundException(String.format("La carrera con ID: %d no existe", carreraId));

        Carrera carreraActualizada = carreraDao.actualizar(oCarrera.get(), carrera);
        return new ResponseEntity<Carrera>(carreraActualizada, HttpStatus.OK );
    }
    @PostMapping("/carreraId/{carreraId}")
    public ResponseEntity<?> eliminarCarrera(@PathVariable Integer carreraId)
    {
        Map<String, Object> respuesta = new HashMap<String, Object>();
        Optional<Carrera> carrera = carreraDao.buscarPorId(carreraId);
        if (!carrera.isPresent())

            throw new NotFoundException(String.format("La carrera con ID: %d no existe", carreraId));

        carreraDao.eliminarPorId(carreraId);
        respuesta.put("OK", "Carrera ID:" + carreraId + "eliminada exitosamente");
        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
    }
}
