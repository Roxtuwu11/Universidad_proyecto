package com.ibm.academia.apirest.ApiSpring.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
//@Table(name ="personas", schema = "universidad")
@Table(name ="personas")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(
        use= JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipo"
)
@JsonSubTypes({

        @JsonSubTypes.Type(value = Alumno.class, name = "alumno"),
        @JsonSubTypes.Type(value = Profesor.class, name = "profesor"),
        @JsonSubTypes.Type(value = Empleado.class, name = "empleado")

} )
public abstract class  Persona implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column (name = "nombre", nullable = false, length = 60)
    private String nombre;
    @Column (name = "apellido", nullable = false, length = 60)
    private String apellido;
    @Column (name = "dni", nullable = false, unique = true, length = 10)
    private String dni;
    @Column (name = "fecha_alta")
    private Date fechaAlta;
    @Column (name = "fecha_modificacion", nullable = true)
    private Date fechaModificacion;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "codigoPostal", column = @Column(name = "codigo_postal")),
            @AttributeOverride(name = "departamento", column = @Column(name = "departamento"))
    })
    private Direccion direccion;

    public Persona(Integer id, String nombre, String apellido, String dni, Direccion direccion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.direccion = direccion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return id.equals(persona.id) && dni.equals(persona.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dni);
    }


    @PrePersist
    private void antesPersistir()
    {
        this.fechaAlta=new Date();
    }


    @PreUpdate
    private void antesActualizar()
    {
        this.fechaModificacion= new Date();
    }
}
