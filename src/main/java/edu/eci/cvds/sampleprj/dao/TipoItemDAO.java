package edu.eci.cvds.sampleprj.dao;

import java.util.List;

import edu.eci.cvds.samples.entities.TipoItem;

public interface TipoItemDAO {
   public List<TipoItem> consultarTiposItems() throws PersistenceException;
   public TipoItem consultarTipoItem(int id) throws PersistenceException;
}
