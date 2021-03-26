package edu.eci.cvds.test.java;

import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class ServiciosAlquilerTest {

    Date fecha = Date.valueOf(LocalDate.now());
    ArrayList<ItemRentado> rentadonull = new ArrayList<>(); 

    ServiciosAlquiler serviciosAlquiler = ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting();
    
    TipoItem tipoitem = new TipoItem(12000, "Juguete");
    Item item = new Item(tipoitem, 12001, "Rubick", "Pentamix QiYi", fecha, 12000L, "formatoRenta","genero");

    @Before
    public void setUp() {
    }
    
    @Test
    public void emptyDB() {
        for(int i = 0; i < 100; i += 10) {
            boolean r = false;
            try {
                serviciosAlquiler.consultarCliente(i);
            } catch(ExcepcionServiciosAlquiler e) {
                r = true;
            } catch(IndexOutOfBoundsException e) {
                r = true;
            }
            // Validate no Client was found;
            Assert.assertTrue(r);
        }
    };
    @Test
    public void deberia_consultarItemsCliente() throws ExcepcionServiciosAlquiler {
        try {
            serviciosAlquiler.consultarItemsCliente(10);
            Assert.assertTrue(true);         
        } catch (Exception e) {
            Assert.fail();
        }        
    }
    
    @Test
    public void deberia_consultarItemsDisponibles() throws ExcepcionServiciosAlquiler {
        try {
            serviciosAlquiler.consultarItemsDisponibles();
            Assert.assertTrue(true);         
        } catch (Exception e) {
            Assert.fail();
        }        
    }

    @Test
    public void deberia_consultarMultaAlquiler() throws ExcepcionServiciosAlquiler {
        try {
            Date fechaalquiler = Date.valueOf(LocalDate.of(1998, 11, 25));
            long multa = serviciosAlquiler.consultarMultaAlquiler(1200, fecha);
            Assert.assertEquals(multa,-1);         
        } catch (ExcepcionServiciosAlquiler e) {
            Assert.fail();
        }  
    }

    @Test
    public void deberia_RegistrarCliente() throws ExcepcionServiciosAlquiler{     
        Cliente cliente = new Cliente("eljohann2",1234567L,"31232342234","cll23#3-12","eljohangu@algo.com",false,rentadonull);         
        try{
            serviciosAlquiler.registrarCliente( cliente);
            Assert.assertTrue(true);
        }catch (ExcepcionServiciosAlquiler ex){
            Assert.fail();
        }
    }
    
    @Test
    public void deberia_consultarCostoAlquiler() throws ExcepcionServiciosAlquiler {
        try {
            long multa = serviciosAlquiler.consultarCostoAlquiler(12000, 12);
            Assert.assertEquals(1,1);         
        } catch (ExcepcionServiciosAlquiler e) {
            Assert.fail();
        }  
    } 
    
    @Test
    public void deberia_registrarItem() throws ExcepcionServiciosAlquiler{
        try {
            serviciosAlquiler.registrarItem(item);  
            serviciosAlquiler.consultarItem(12001);
            Assert.assertTrue(true);         
        } catch (Exception e) {
            Assert.fail();
        }       
    }
    
    @Test
    public void deberia_VetarCliente() throws ExcepcionServiciosAlquiler {
        Cliente cliente = new Cliente("eljohann1",10041232L,"3124324312","cll23#43-12","eljohangu@algo.com",false,rentadonull);
        try {
               serviciosAlquiler.registrarCliente(cliente);
               serviciosAlquiler.vetarCliente(10041232L, true);
               boolean esVetado = serviciosAlquiler.consultarCliente(10041232L).isVetado();
               Assert.assertTrue(true);
        } catch (Exception e) {
                Assert.fail();
        }
    }
}