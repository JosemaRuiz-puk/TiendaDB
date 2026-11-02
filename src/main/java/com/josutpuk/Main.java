
package com.josutpuk;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ProductoDAO productoDAO = new ProductoDAO();
        PedidoDAO pedidoDAO = new PedidoDAO();

        int opcion = -1;

        do {
            System.out.println("\nElige una opción");
            System.out.println("--------------------------------");
            System.out.println("1. Insertar producto");
            System.out.println("2. Buscar producto por ID");
            System.out.println("3. Actualizar stock de producto");
            System.out.println("4. Insertar pedido");
            System.out.println("5. Listar pedidos");
            System.out.println("0. Salir del programa");

            try {
                opcion = sc.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Introduce un número entre 0 y 5");
                sc.nextLine();
                opcion = -1;
                continue;
            }

            try {
                switch (opcion) {

                    case 1:
                        System.out.println("Has elegido insertar producto");
                        System.out.println("Introduce el nombre del producto:");
                        sc.nextLine();
                        String nombre = sc.nextLine();

                        if (nombre.isBlank()) {
                            System.out.println("El nombre no puede estar vacío");
                            break;
                        }

                        System.out.println("Introduce el precio:");
                        BigDecimal precio = sc.nextBigDecimal();

                        if (precio.signum() < 0) {
                            System.out.println("El precio no puede ser negativo");
                            break;
                        }

                        System.out.println("Introduce el stock:");
                        int stock = sc.nextInt();

                        if (stock < 0) {
                            System.out.println("El stock no puede ser negativo");
                            break;
                        }

                        Producto producto = new Producto();
                        producto.setNombre(nombre);
                        producto.setPrecio(precio);
                        producto.setStock(stock);

                        try {
                            productoDAO.insertarProducto(producto);
                            System.out.println("Producto insertado correctamente");
                        } catch (SQLException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 2:
                        System.out.println("Has elegido buscar producto por ID");
                        System.out.println("Introduce el ID del producto:");
                        int idProducto = sc.nextInt();

                        try {
                            Producto encontrado = productoDAO.consultarProducto(idProducto);

                            if (encontrado != null) {
                                System.out.println("Producto: " + encontrado.getNombre());
                                System.out.println("Precio: " + encontrado.getPrecio());
                                System.out.println("Stock: " + encontrado.getStock());
                            } else {
                                System.out.println("Producto no encontrado");
                            }

                        } catch (SQLException e) {
                            System.out.println("Error al consultar: " + e.getMessage());
                        }
                        break;

                    case 3:
                        System.out.println("Has elegido cambiar el stock de un producto");
                        System.out.println("Introduce el ID del producto:");
                        int idStock = sc.nextInt();

                        System.out.println("Introduce el nuevo stock:");
                        int nuevoStock = sc.nextInt();

                        if (nuevoStock < 0) {
                            System.out.println("El stock no puede ser negativo");
                            break;
                        }

                        try {
                            productoDAO.actualizarStock(idStock, nuevoStock);
                            System.out.println("Stock actualizado correctamente");
                        } catch (SQLException e) {
                            System.out.println("Error al actualizar stock: " + e.getMessage());
                        }
                        break;

                    case 4:
                        System.out.println("Has elegido insertar un pedido");
                        System.out.println("Introduce el ID del producto:");
                        int idPedidoProducto = sc.nextInt();

                        System.out.println("Introduce la cantidad:");
                        int cantidad = sc.nextInt();

                        if (cantidad <= 0) {
                            System.out.println("La cantidad debe ser mayor que cero");
                            break;
                        }

                        Pedido pedido = new Pedido();
                        pedido.setFechaPedido(LocalDate.now());
                        pedido.setIdProducto(idPedidoProducto);
                        pedido.setCantidad(cantidad);

                        try {
                            pedidoDAO.insertarPedido(pedido);
                            System.out.println("Pedido registrado correctamente");
                        } catch (SQLException e) {
                            System.out.println("Error al registrar pedido: " + e.getMessage());
                        }
                        break;

                    case 5:
                        System.out.println("Has elegido listar todos los pedidos");

                        try {
                            pedidoDAO.listarPedidos();
                        } catch (SQLException e) {
                            System.out.println("Error al listar pedidos: " + e.getMessage());
                        }
                        break;

                    case 0:
                        System.out.println("Saliendo del programa...");
                        break;

                    default:
                        System.out.println("Elige un número entre 0 y 5");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: introduce un valor numérico válido");
                sc.nextLine();
            }

        } while (opcion != 0);

        sc.close();
    }
}
