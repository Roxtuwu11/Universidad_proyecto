package com.ibm.academia.apirest.ApiSpring.repositories;

import com.ibm.academia.apirest.ApiSpring.entities.Aula;
import com.ibm.academia.apirest.ApiSpring.entities.Pabellon;
import org.springframework.data.repository.CrudRepository;

public interface PabellonRepository extends CrudRepository<Pabellon, Integer>
{
}
