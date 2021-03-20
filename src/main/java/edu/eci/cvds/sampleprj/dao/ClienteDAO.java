package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Cliente;

public interface ClienteDAO {
    public Cliente load(long id) throws PersistenceException, edu.eci.cvds.sampleprj.dao.PersistenceException;;
}
