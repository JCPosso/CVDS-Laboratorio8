package edu.eci.cvds.sampleprj.dao;
import java.util.Date;
import java.util.List;

import edu.eci.cvds.samples.entities.Item;

public interface ItemDAO {

   public void save(Item it) throws PersistenceException, edu.eci.cvds.sampleprj.dao.PersistenceException;
   public Item load(int id) throws PersistenceException, edu.eci.cvds.sampleprj.dao.PersistenceException;
   public List<Item> itemsDisponibles() throws PersistenceException;
   public long consultarMultaAlquiler(int iditem,Date fechaDevolucion) throws PersistenceException;

}