package com.ibm.academia.apirest.ApiSpring.repositories;

import com.ibm.academia.apirest.ApiSpring.entities.Carrera;
import com.ibm.academia.apirest.ApiSpring.entities.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
        public interface CarreraRepository extends CrudRepository<Carrera, Integer>
        {

            //@Query("select c from Carrera c where c.cantidadAnios =?1")
                public Iterable <Carrera> findByCantidadAnios(Integer cantidadAnios);

               public Iterable<Carrera> findCarrerasByNombreContains(String nombre);
                public Iterable<Carrera> findCarrerasByNombreContainsIgnoreCase(String nombre);
                public Iterable<Carrera> findCarrerasByCantidadAniosAfter(Integer cantidadAnios);
        }
