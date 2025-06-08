package com.mijardin.entities;

import jakarta.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "ubicaciones")
public class Ubicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Lob
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoUbicacion tipo;

    @Column(name = "luz")
    private Integer luz;

    @OneToMany(mappedBy = "ubicacion")
    private Set<Planta> plantas = new LinkedHashSet<>();

    public enum TipoUbicacion {
        interior,
        exterior;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoUbicacion getTipo() {
        return tipo;
    }

    public void setTipo(TipoUbicacion tipo) {
        this.tipo = tipo;
    }

    public Integer getLuz() {
        return luz;
    }

    public void setLuz(Integer luz) {
        this.luz = luz;
    }

    public Set<Planta> getPlantas() {
        return plantas;
    }

    public void setPlantas(Set<Planta> plantas) {
        this.plantas = plantas;
    }
}