package edu.eci.cvds.samples.services.client;

import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;

public class ServiceExample {
    public static void main(String args[]){
        try {
            System.out.println(ServiciosAlquilerFactory.getInstance().getServiciosAlquiler().consultarCliente(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
