package com.ibm.academia.apirest.ApiSpring.services;


import com.ibm.academia.apirest.ApiSpring.entities.Carrera;
import com.ibm.academia.apirest.ApiSpring.repositories.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Service
public class CarreraDAOImpl extends GenericoDAOImpl <Carrera, CarreraRepository> implements CarreraDAO  {

    @Autowired
    public CarreraDAOImpl(CarreraRepository repository) {
        super(repository);
    }

    @Override
    @Transactional (readOnly = true)
    public Iterable<Carrera> findCarreraByNombreContains(String nombre) {
        return repository.findCarrerasByNombreContains(nombre);
    }

    @Override
    public Iterable<Carrera> findCarreraByNombreContainsIgnoreCase(String nombre) {
        return findCarreraByNombreContainsIgnoreCase(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Carrera> findCarreraByCantidadAniosAfter(Integer cantidadAnios) {
        return findCarreraByCantidadAniosAfter(cantidadAnios);
    }

    @Override
    @Transactional
    public Carrera actualizar(Carrera carreraEncontrada, Carrera carrera) {
        Carrera careraActualizada=null;
        carreraEncontrada.setCantidadAnios(carrera.getCantidadAnios());
        carreraEncontrada.setCantidadMaterias(carrera.getCantidadMaterias());
        careraActualizada = repository.save(carreraEncontrada);
        return careraActualizada;
    }
}

