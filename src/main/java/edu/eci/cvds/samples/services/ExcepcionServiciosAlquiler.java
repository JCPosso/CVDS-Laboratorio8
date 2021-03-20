package edu.eci.cvds.samples.services;

import edu.eci.cvds.sampleprj.dao.PersistenceException;

public class ExcepcionServiciosAlquiler extends Exception{
    
    public ExcepcionServiciosAlquiler(String msg, PersistenceException ex){
        super(msg);
    }

    public ExcepcionServiciosAlquiler(String msg) {
        super(msg);
    }
    
}
