package com.ibm.academia.apirest.ApiSpring.repositories;

import com.ibm.academia.apirest.ApiSpring.entities.Aula;
import com.ibm.academia.apirest.ApiSpring.entities.Carrera;
import org.springframework.data.repository.CrudRepository;

public interface AulaRepository extends CrudRepository<Aula, Integer>
{
}
