package com.josutpuk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PedidoDAO {

    public void insertarPedido(Pedido pedido) throws SQLException {

        String sqlPedido = "INSERT INTO pedidos (fecha_pedido, cantidad, id_producto) VALUES (?, ?, ?)";
        String sqlStock = "UPDATE productos SET stock = stock - ? WHERE id_producto = ? AND stock >= ?";

        try (Connection conexion = ConexionDB.getConexion()) {
            conexion.setAutoCommit(false);

            try {

                if (pedido.getCantidad() <= 0) {
                    throw new SQLException("La cantidad debe ser mayor que cero");
                }

                try (PreparedStatement psPedido = conexion.prepareStatement(sqlPedido);
                     PreparedStatement psStock = conexion.prepareStatement(sqlStock)) {
                    psStock.setInt(1, pedido.getCantidad());
                    psStock.setInt(2, pedido.getIdProducto());
                    psStock.setInt(3, pedido.getCantidad());
                    int filas = psStock.executeUpdate();

                    if (filas == 0) {
                        throw new SQLException("El producto no existe o no hay stock suficiente");
                    }

                    psPedido.setDate(1, java.sql.Date.valueOf(pedido.getFechaPedido()));
                    psPedido.setInt(2, pedido.getCantidad());
                    psPedido.setInt(3, pedido.getIdProducto());

                    psPedido.executeUpdate();
                    conexion.commit();
                }

            } catch (SQLException e) {
                conexion.rollback();
                throw e;
            }
        }

    }

    public void listarPedidos() throws SQLException {

        String sql = """
        SELECT p.id_pedido, p.fecha_pedido, p.cantidad,
               pr.nombre
        FROM pedidos p
        JOIN productos pr ON p.id_producto = pr.id_producto
        ORDER BY p.id_pedido
        """;

        try (Connection conexion = ConexionDB.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println("Pedido: " + rs.getInt("id_pedido"));
                System.out.println("Fecha: " + rs.getDate("fecha_pedido"));
                System.out.println("Producto: " + rs.getString("nombre"));
                System.out.println("Cantidad: " + rs.getInt("cantidad"));
                System.out.println("------------------------");
            }

        }

    }

}



