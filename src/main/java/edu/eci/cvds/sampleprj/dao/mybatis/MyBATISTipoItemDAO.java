package edu.eci.cvds.sampleprj.dao.mybatis;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.TipoItemDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.TipoItemMapper;
import edu.eci.cvds.samples.entities.TipoItem;

public class MyBATISTipoItemDAO implements TipoItemDAO{
    
    @Inject
    private TipoItemMapper tipoItemMapper;


    @Override
    public TipoItem consultarTipoItem(int id) throws PersistenceException{
        TipoItem tipoItem = null;
        try {
            tipoItem = tipoItemMapper.consultarTipoItem(id);
        } catch (Exception e) {
            throw new PersistenceException("Error consultar tipo item o item no existe", e);
        }

        return tipoItem;
    }
   
    @Override
    public List<TipoItem> consultarTiposItems() throws PersistenceException{
        List<TipoItem> tipoItems = new ArrayList<TipoItem>();
        try {
            tipoItems = tipoItemMapper.consultarTipoItems();
        } catch (Exception e) {
            throw new PersistenceException("Error al consultar los tipos de items", e);
        }

        return tipoItems;
    }
    
}
