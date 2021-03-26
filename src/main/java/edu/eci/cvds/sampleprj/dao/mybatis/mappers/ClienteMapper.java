package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;

/**
 *
 * @author Juan Posso && Johann Cepeda
 */
public interface ClienteMapper {
    
    public Cliente consultarClienteById(@Param("idcli") int id); 
    
    public Cliente consultarClienteBydoc(@Param("doc") long documento);
    
    /**
     * Registrar un nuevo item rentado asociado al cliente identificado
     * con 'idc' y relacionado con el item identificado con 'idi'
     * @param id
     * @param idit
     * @param fechainicio
     * @param fechafin 
     */
    public void agregarItemRentadoACliente(@Param("id") int id, 
            @Param("idit") int idit, 
            @Param("fechaInicio") Date fechainicio,
            @Param("fechaFin") Date fechafin);

    /**
     * Consultar todos los clientes
     * @return 
     */
    public List<Cliente> consultarClientes();

    public List<ItemRentado> consultarItems(@Param("id") long id);

    public void registrarAlquilerCliente(
        @Param("date")Date date, 
        @Param("docu")long docu, 
        @Param("item")Item item, 
        @Param("numdias")int numdias
    );    

    public void registrarCliente(@Param("cliente") Cliente c);

    public void vetarCliente(@Param("docu")long docu, @Param("estado")boolean estado);
}
