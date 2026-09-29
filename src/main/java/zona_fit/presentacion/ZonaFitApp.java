package zona_fit.presentacion;

import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;

import java.util.Scanner;

public class ZonaFitApp {

    public static void main(String[] args){
        zonaFitApp();
    }

    private static void zonaFitApp(){
        var salir = false;
        Scanner s = new Scanner(System.in);
        // Creamos un objeto de la clase clienteDao
        IClienteDAO clienteDao = new ClienteDAO();
        while(!salir){
            try{
                var opcion = mostrarMenu(s);
                salir = ejecutarOpciones(s, opcion, clienteDao);
            }catch (Exception e){
                System.out.println("Error al ejecutar opciones" + e.getMessage());
            }
            System.out.println();
        }
    }

    private static int mostrarMenu(Scanner s){
        System.out.print("""
                *** Zona Fit (GYM)
                1. Listar Clientes
                2. Buscar Cliente
                3. Agregar Cliente
                4. Modificar Cliente
                5. Eliminar Cliente
                6. Salir
                Elija una opcion:\s """);
        return Integer.parseInt(s.nextLine());
    }

    private static boolean ejecutarOpciones(Scanner s, int opcion, IClienteDAO clienteDAO){

        var salir = false;
        switch (opcion){
            case 1 -> { // 1. Listar clientes
                System.out.println("--- Listado de Clientes ---");
                var clientes = clienteDAO.listarClientes();
                clientes.forEach(System.out::println);
            }
            case 2 -> {
                System.out.println(" Intorduce el id de cliente a buscar: ");
                var idCliente = Integer.parseInt(s.nextLine());
                var cliente = new Cliente(idCliente);
                var encontrado = clienteDAO.buscarClientePorId(cliente);
                if(encontrado){
                    System.out.println(" Cliente encontrado: " + cliente);
                }
                else {
                    System.out.println(" Cliente NO encontrado: " + cliente);
                }
            }
            case 3 -> {
                System.out.println("--- Agregar Cliente ---");
                System.out.print(" Nombre: ");
                String nombre = s.nextLine();
                System.out.print(" Apellido: ");
                String apellido = s.nextLine();
                System.out.print(" Membresia: ");
                var membresia = Integer.parseInt(s.nextLine());
                var cliente = new Cliente(nombre, apellido, membresia);
                var agregado = clienteDAO.agregarCliente(cliente);
                if(agregado){
                    System.out.println(" Cliente agregado: " + cliente);
                }
                else {
                    System.out.println(" Cliente NO agregado: " + cliente);
                }
            }
            case 4 -> {
                System.out.println("--- Modificar Cliente ---");
                System.out.print("Id Cliente: ");
                var idCliente = Integer.parseInt(s.nextLine());
                System.out.print("Nombre: ");
                var nombre = s.nextLine();
                System.out.print("Apellido: ");
                var apellido = s.nextLine();
                System.out.print("Membresia: ");
                var membresia = Integer.parseInt(s.nextLine());
                // Creamos el objeto a modificar
                var cliente = new Cliente(idCliente, nombre, apellido, membresia);
                var modificado = clienteDAO.modificarCliente(cliente);
                if(modificado){
                    System.out.println(" Cliente modificado: " + cliente);
                }
                else {
                    System.out.println(" Cliente NO modificado: " + cliente);
                }
            }
            case 5 -> {
                System.out.println("--- Eliminar Cliente ---");
                System.out.print("Id Cliente: ");
                var idCliente = Integer.parseInt(s.nextLine());
                var cliente = new Cliente(idCliente);
                var eliminado = clienteDAO.eliminarCliente(cliente);
                if (eliminado) {
                    System.out.println(" Cliente Eliminado: " + cliente);
                }
                else {
                    System.out.println(" Cliente NO eliminado: " + cliente);
                }
            }
            case 6 -> {
                System.out.println(" --- Hasta Pronto --- ");
                salir = true;
            }
            default -> System.out.println(" Opcion no reconocida: " + opcion);
        }
        return salir;
    }
}
