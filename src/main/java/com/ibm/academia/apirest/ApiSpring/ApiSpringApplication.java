package com.ibm.academia.apirest.ApiSpring;

import com.ibm.academia.apirest.ApiSpring.entities.Alumno;
import com.ibm.academia.apirest.ApiSpring.entities.Direccion;
import com.ibm.academia.apirest.ApiSpring.entities.Persona;
import com.ibm.academia.apirest.ApiSpring.services.AlumnoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ApiSpringApplication {
	@Autowired
	private AlumnoDAO alumnoDAO;
	public static void main(String[] args) {
		SpringApplication.run(ApiSpringApplication.class, args);
	}


}
