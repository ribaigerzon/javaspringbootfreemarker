package com.ribai.PuntoDeVenta.logic;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString @EqualsAndHashCode
@Entity
@Table(name="ordenesVenta")
public class OrdenVenta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name="id_orden")
    private Integer id_orden;
    @Column(name="fechaVenta")
    private Date fechaVenta;
    @Column(name="importe")
    private Double importe;

    @ManyToOne
    @JoinColumn(name="id_usuario", nullable=false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name="id_producto", nullable=false)
    private Producto producto;
}
