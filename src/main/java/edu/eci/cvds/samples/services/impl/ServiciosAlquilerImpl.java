package edu.eci.cvds.samples.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.TipoItemDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBATISClienteDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class ServiciosAlquilerImpl implements ServiciosAlquiler {

   @Inject
   private ItemDAO itemDAO;

   @Inject
   private ClienteDAO ClienteDAO;

   @Inject
   private TipoItemDAO tipoItemDAO;

   @Override
   public int valorMultaRetrasoxDia(int itemId) {
       int valor = -1;
      try {
          valor = itemDAO.valorMultaRetrasoxDia(itemId);
      } catch (Exception e) {
          e.printStackTrace();
      }
      return valor;
   }

   @Override
   public Cliente consultarCliente(long docu) throws ExcepcionServiciosAlquiler {
       Cliente cliente = null;
       try {
           cliente = ClienteDAO.load(docu);
       } catch (Exception e) {
           throw new ExcepcionServiciosAlquiler("Ha ocudrrido un error buscando al cliente");
       }
       
       return cliente;
   }

   @Override
   public List<ItemRentado> consultarItemsCliente(long idcliente) throws ExcepcionServiciosAlquiler {
       List<ItemRentado> items = new ArrayList<ItemRentado>();
       try {
           items = ClienteDAO.consultarItems(idcliente);
           if(items.size() <=0)
               System.out.println("El Cliente no tiene items rentados");

       } catch (Exception e) {
           e.printStackTrace();
       }

       return items;
   }

   @Override
   public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler {
       List<Cliente> clientes = new ArrayList<Cliente>();
       try {
           clientes = ClienteDAO.loadClientes();
       } catch (Exception e) {
           e.printStackTrace();
       }
       return clientes;
   }

   @Override
   public Item consultarItem(int id) throws ExcepcionServiciosAlquiler {
       try {
           return itemDAO.load(id);
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el item "+id, ex);
       }
   }

   @Override
   public List<Item> consultarItemsDisponibles() throws ExcepcionServiciosAlquiler {
        List<Item> disponibles = new ArrayList<Item>();
        try{
            disponibles= itemDAO.itemsDisponibles();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return disponibles;
   }

   @Override
   public long consultarMultaAlquiler(int iditem, Date fechaDevolucion) throws ExcepcionServiciosAlquiler {
       long multa = -1;
       try {
           multa = itemDAO.consultarMultaAlquiler(iditem,fechaDevolucion);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return multa;
   }

   @Override
   public TipoItem consultarTipoItem(int id) throws ExcepcionServiciosAlquiler {
       TipoItem tipoItem = null;
       try {
           tipoItem = tipoItemDAO.consultarTipoItem(id);
       } catch (Exception e) {
           e.printStackTrace();
       }

       return tipoItem;
   }

   @Override
   public List<TipoItem> consultarTiposItem() throws ExcepcionServiciosAlquiler {
       List<TipoItem> tipoItems = new ArrayList<TipoItem>();
       try {
           tipoItems = tipoItemDAO.consultarTiposItems();
           if(tipoItems.size() <=0)
               System.out.println("La lista esta vacia");
           
       } catch (Exception e) {
           e.printStackTrace();
       }

       return tipoItems;
   }

   @Override
   public void registrarAlquilerCliente(Date date, long docu, Item item, int numdias) throws ExcepcionServiciosAlquiler {
       try {
           ClienteDAO.registrarAlquilerCliente(date, docu, item, numdias);
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

   @Override
   public void registrarCliente(Cliente c) throws ExcepcionServiciosAlquiler {
       try {
           ClienteDAO.registrarCliente(c);
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

   @Override
   public long consultarCostoAlquiler(int iditem, int numdias) throws ExcepcionServiciosAlquiler {
       long costo = -1;
       try {
           costo = itemDAO.consultarCostoAlquiler(iditem, numdias);
       } catch (Exception e) {
           e.printStackTrace();
       }

       return costo;
   }

   @Override
   public void actualizarTarifaItem(int id, long tarifa) throws ExcepcionServiciosAlquiler {
       try {
           itemDAO.actualizarTarifaItem(id, tarifa);
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

   @Override
   public void registrarItem(Item i) throws ExcepcionServiciosAlquiler {
       try {
           itemDAO.save(i);
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

   @Override
   public void vetarCliente(long docu, boolean estado) throws ExcepcionServiciosAlquiler {
       try {
           ClienteDAO.vetarCliente(docu, estado);
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
}