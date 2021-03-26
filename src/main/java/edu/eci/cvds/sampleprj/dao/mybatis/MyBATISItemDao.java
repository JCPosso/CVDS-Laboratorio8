package edu.eci.cvds.sampleprj.dao.mybatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.cvds.samples.entities.TipoItem;
import java.sql.SQLException;

public class MyBATISItemDAO implements ItemDAO {
    @Inject
    private ItemMapper itemMapper;

    @Override
    public void save(Item it) throws PersistenceException {
        try {
            itemMapper.insertarItem(it);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al registrar el item " + it.toString(), e);
        }

    }

    @Override
    public Item load(int id) throws PersistenceException {
        try {
            return itemMapper.consultarItem(id);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar el item " + id, e);
        }

    }

    @Override
    public List<Item> itemsDisponibles() throws PersistenceException {
        List<Item> itemRentados = new ArrayList<Item>();
        try {
            itemRentados = itemMapper.consultarDisponibles();
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Ocurrio un error consultando items", e);
        }
        return itemRentados;
    }

    @Override
    public long consultarMultaAlquiler(int iditem,Date fechaDevolucion) throws PersistenceException{
        long multa=0;
        Date today = new Date();
        int todayDay = today.getDay();
        int diasRetraso = todayDay - fechaDevolucion.getDay();
        try {
            multa = itemMapper.consultarMultaAlquiler(iditem,fechaDevolucion, diasRetraso);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar el item ", e);
        }
        return multa;
    }

    @Override
    public long consultarCostoAlquiler(int iditem, int numdias) throws PersistenceException {
        long costo = -1;
        try {
            costo = itemMapper.consultarCostoAlquiler(iditem, numdias);
        } catch (Exception e) {
            throw new PersistenceException("No se pudo consultar el costo del alquiler", e);
        }
        return costo;
    }

    @Override
    public void actualizarTarifaItem(int id, long tarifa) throws PersistenceException {
        try {
            itemMapper.actualizarTarifaItem(id,tarifa);
        } catch (Exception e) {
            throw new PersistenceException("No se pudo actualizar la tarifa del item", e);
        }
    }

    @Override
    public int valorMultaRetrasoxDia(int id) throws PersistenceException {
        int valor = -1;
        try {
            valor = itemMapper.valorMultaRetrasoxDia(id);
        } catch (Exception e) {
            throw new PersistenceException("No se pudo obtener el valor de la multa", e);
        }
        return valor;
    }
}