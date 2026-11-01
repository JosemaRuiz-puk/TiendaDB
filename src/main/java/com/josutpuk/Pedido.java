package com.josutpuk;

import lombok.Data;

import java.time.LocalDate;

@Data

public class Pedido {
    private int idPedido;
    private LocalDate fechaPedido;
    private int cantidad;
    private int idProducto;
}
