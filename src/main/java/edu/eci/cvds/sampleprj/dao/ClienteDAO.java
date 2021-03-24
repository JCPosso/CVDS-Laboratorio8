package edu.eci.cvds.sampleprj.dao;

import java.util.List;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;

public interface ClienteDAO {
    public Cliente load(long id) throws PersistenceException, edu.eci.cvds.sampleprj.dao.PersistenceException;
    public List<Cliente> loadClientes() throws PersistenceException;
    public List<ItemRentado> consultarItems(long id) throws PersistenceException;
}
