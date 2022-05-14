package com.ibm.academia.apirest.ApiSpring.entities;

import com.ibm.academia.apirest.ApiSpring.enums.Pizarron;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.boot.archive.scan.spi.PackageInfoArchiveEntryHandler;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
//@Table(name ="aulas", schema = "universidad")
@Table(name ="aulas")
public class Aula implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column (name ="numero_aula", nullable = false)
    private Integer numeroAula;
    @Column(name = "medidas")
    private String medidas;
    @Column(name="cantidad_pupitres")
    private Integer cantidadPupitres;
    @Column(name = "tipo_pizarron")
    @Enumerated(EnumType.STRING)
    private Pizarron pizarron;
    @Column(name = "fecha_alta")
    private Date fechaAlta;
    @Column (name = "fecha_modificacion")
    private Date fechaModificacion;


    @ManyToOne(optional = true, cascade ={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "pabellon_id",foreignKey = @ForeignKey(name = "FK_PABELLON_ID"))
    private Pabellon pabellon;

    public Aula(Integer id, Integer numeroAula, String medidas, Integer cantidadPupitres, Pizarron pizarron) {

        this.id = id;
        this.numeroAula = numeroAula;
        this.medidas = medidas;
        this.cantidadPupitres = cantidadPupitres;
        this.pizarron = pizarron;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aula aula = (Aula) o;
        return id.equals(aula.id) && Objects.equals(numeroAula, aula.numeroAula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numeroAula);
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
