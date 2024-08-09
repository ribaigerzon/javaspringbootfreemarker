package com.ribai.PuntoDeVenta.logic;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode
@Entity
@Table(name="usuarios")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name="id_usuario")
    private Integer id_usuario;
    @Column(name="nombre")
    private String nombre;
    @Column(name="contrasenia")
    private String contrasenia;
    @Column(name="rol")
    private String rol;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<OrdenVenta> ordenesVenta;
}
