package com.josutpuk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoDAO {

    public void insertarProducto(Producto producto) throws SQLException {

        String sql = "INSERT INTO productos (nombre, precio, stock) VALUES (?, ?, ?)";

        try (Connection conexion = ConexionDB.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, producto.getNombre());
            ps.setBigDecimal(2, producto.getPrecio());
            ps.setInt(3, producto.getStock());

            ps.executeUpdate();

        }

    }

    public Producto consultarProducto(int idProducto) throws SQLException {

        String sql = "SELECT * FROM productos WHERE id_producto = ?";

        try (Connection conexion = ConexionDB.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idProducto);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Producto producto = new Producto();
                    producto.setIdProducto(rs.getInt("id_producto"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setPrecio(rs.getBigDecimal("precio"));
                    producto.setStock(rs.getInt("stock"));
                    return producto;
                }
            }

        }

        return null;
    }

    public void actualizarStock(int idProducto, int nuevoStock) throws SQLException {

        String sql = "UPDATE productos SET stock = ? WHERE id_producto = ?";

        try (Connection conexion = ConexionDB.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, nuevoStock);
            ps.setInt(2, idProducto);

            int filas = ps.executeUpdate();

            if (filas == 0) {
                throw new SQLException("El producto no existe");
            }

        }



    }
}
