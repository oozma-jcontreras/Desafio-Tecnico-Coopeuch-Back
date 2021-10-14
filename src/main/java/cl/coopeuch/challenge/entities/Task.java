package cl.coopeuch.challenge.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "TAREAS")
@Getter
@Setter
@ToString
public class Task {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID_TAREA")
    private int id;
    @Column(name = "DESCRIPCION", length = 250)
    @NotBlank(message = "Debe indicar la descripcion")
    private String description;
    @Column(name = "FECHA_CREACION")
    @NotNull(message = "Debe indicar la fecha de creacion")
    private LocalDateTime creationDate;
    @Column(name = "ES_VIGENTE", columnDefinition = "BIT")
    @NotNull(message = "Debe indicar si es o no vigente")
    private boolean active;
}