package edu.eci.cvds.sampleprj.dao.mybatis;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;

public class MyBATISClienteDAO implements ClienteDAO{

    @Inject
    private ClienteMapper clienteMapper;

    
    @Override
    public Cliente load(long id) throws PersistenceException {
        Cliente cliente = null;
        try{
           cliente =  clienteMapper.consultarClienteBydoc(id);
        }catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException(" Error cliente no existe",e);
        }
        return cliente;
    }

    @Override
    public List<Cliente> loadClientes() throws PersistenceException {
        List<Cliente> clientes = new ArrayList<Cliente>();
        try {
            clientes = clienteMapper.consultarClientes();            
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Ocurrio un error leyendo los clientes", e);
        }
        return clientes;
    }

    @Override
    public List<ItemRentado> consultarItems(long id) throws PersistenceException {
        List<ItemRentado> itemRentados = new ArrayList<ItemRentado>();
        try {
            itemRentados = clienteMapper.consultarItems(id);
        } catch (Exception e) {
            throw new PersistenceException("Ocurrio un error consultando items", e);
        }

        return itemRentados;
    }

    @Override
    public void registrarAlquilerCliente(Date date, long docu, Item item, int numdias) throws PersistenceException {
        Date fechaFinal = sumarDiasAFecha(date, numdias);
        try {
            clienteMapper.registrarAlquilerCliente(date, docu, item, numdias);
        } catch (Exception e) {
            throw new PersistenceException("No se pudo registrar el cliente", e);
        }
        
    }

    @Override
    public void registrarCliente(Cliente c) throws PersistenceException {
        try {
            clienteMapper.registrarCliente(c);
        } catch (Exception e) {
            throw new PersistenceException("No se pudo registrar el cliente", e);
        }
        
    }

    //Auxiliar
    private Date sumarDiasAFecha(Date fecha, int dias){
        if (dias==0) return fecha;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); 
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return calendar.getTime(); 
    }

    @Override
    public void vetarCliente(long docu, boolean estado) throws PersistenceException {
        try {
            clienteMapper.vetarCliente(docu, estado);
        } catch (Exception e) {
            throw new PersistenceException("No se pudo vetar al cliente", e);
        }
    }
}

