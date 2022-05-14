package com.ibm.academia.apirest.ApiSpring.repositories;

import com.ibm.academia.apirest.ApiSpring.entities.Persona;
import com.ibm.academia.apirest.ApiSpring.enums.TipoEmpleado;

public interface EmpleadoRepository extends PersonaRepository
{
    Iterable<Persona> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado);
}
