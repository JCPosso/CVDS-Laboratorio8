package edu.eci.cvds.sampleprj.dao.mybatis.mappers;


import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.TipoItem;

/**
 *
 * @author 2106913
 */
public interface ItemMapper {
    
    public void insertarItem(@Param("item") Item it);
    
    public List<Item> consultarItems();  
    public List<Item> consultarDisponibles();         
    public List<TipoItem> consultarTipoItems();

    public Item consultarItem(@Param("id") int id);
    public long consultarMultaAlquiler(@Param("iditem") long id, @Param("fechaDevolucion") Date fecha);
    public long consultarCostoAlquiler(@Param("iditem") long id, @Param("numdias") int numdias);
        
}
