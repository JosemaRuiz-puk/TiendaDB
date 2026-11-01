package com.josutpuk;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

//        try (Connection conexion = ConexionDB.getConexion()) {
//            System.out.println("Conexión establecida correctamente");
//        } catch (SQLException e) {
//            System.out.println("Error de conexión: " + e.getMessage());
//        }
//        Producto producto = new Producto();
//        producto.setNombre("Agua");
//        producto.setPrecio(new BigDecimal("0.60"));
//        producto.setStock(50);
//
//        ProductoDAO dao = new ProductoDAO();
//
//        try {
//            dao.insertarProducto(producto);
//            System.out.println("Producto insertado correctamente");
//        } catch (SQLException e) {
//            System.out.println("Error al insertar producto: " + e.getMessage());
//        }

//        ProductoDAO dao = new ProductoDAO();

//        try {
//            Producto encontrado = dao.consultarProducto(5);
//
//            if (encontrado != null) {
//                System.out.println("Producto: " + encontrado.getNombre());
//                System.out.println("Precio: " + encontrado.getPrecio());
//                System.out.println("Stock: " + encontrado.getStock());
//            } else {
//                System.out.println("Producto no encontrado");
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Error al consultar: " + e.getMessage());
//        }

        ProductoDAO dao = new ProductoDAO();

        try {
            dao.actualizarStock(1, 35);
            System.out.println("Stock actualizado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al actualizar stock: " + e.getMessage());
        }
    }
    }