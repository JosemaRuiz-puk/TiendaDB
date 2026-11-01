package com.josutpuk;

import lombok.Data;

import java.math.BigDecimal;

@Data

public class Producto {
    private int idProducto;
    private String nombre;
    private BigDecimal precio;
    private int stock;

}
