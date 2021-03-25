package edu.eci.cvds.sampleprj.dao.mybatis.mappers;


import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Item;

/**
 *
 * @author 2106913
 */
public interface ItemMapper {
    
    
    public List<Item> consultarItems();  
    public List<Item> consultarDisponibles();         
    public Item consultarItem(@Param("id") int id);
    public void insertarItem(@Param("item") Item it);
    public long consultarMultaAlquiler(@Param("iditem") long id, @Param("fechaDevolucion") Date fecha);

        
}
