package com.ciclo3.Ciclo3.sevicios;

import com.ciclo3.Ciclo3.modelos.Empleado;
import com.ciclo3.Ciclo3.modelos.MovimientoDinero;
import com.ciclo3.Ciclo3.repositorio.MovimientoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovimientosServicios {
    @Autowired
    MovimientoRepositorio movimientosRepositorio;

    public List<MovimientoDinero> getAllMovimientos(){ //Metodo que me muestra todos los movimientos sin ningn filtro
        List<MovimientoDinero> movimientosList = new ArrayList<>();
        movimientosRepositorio.findAll().forEach(movimiento -> movimientosList.add(movimiento));  //Recorremos el iterable que regresa el metodo findAll del Jpa y lo guardamos en la lista creada
        return movimientosList;
    }

    public MovimientoDinero getMovimientoById(Integer id){ //Ver movimientos por id
        return movimientosRepositorio.findById(id).get();
    }

    public boolean saveOrUpdateMovimiento(MovimientoDinero movimientoDinero) {
        MovimientoDinero mov = movimientosRepositorio.save(movimientoDinero);
        if (movimientosRepositorio.findById(mov.getId()) != null) {
            return true;
        }
        return false;
    }

    public boolean deleteMovimiento(Integer id){ //Eliminar movimiento por id
        movimientosRepositorio.deleteById(id); //Eliminar usando el metodo que nos ofrece el repositorio
        if(this.movimientosRepositorio.findById(id).isPresent()){ //Si al buscar el movimiento lo encontramos, no se eliminĂ³ (false)
            return false;
        }
        return true; //Si al buscar el movimiento no lo encontramos, si lo eliminĂ² (true)
    }

    public ArrayList<MovimientoDinero> obtenerPorEmpleado(Integer id) { //Obterner teniendo en cuenta el id del empleado
        return movimientosRepositorio.findByEmpleado(id);
    }

    //Obtener movimientos teniendo en cuenta el id de la empresa a la que pertencen los empleados que la registraron
    public ArrayList<MovimientoDinero> obtenerPorEmpresa(Integer id) {
        return movimientosRepositorio.findByEmpresa(id);
    }

    // Obtener suma de todos los movimientos
    public long obtenerSumaMonto(){
        return movimientosRepositorio.sumaMonto();
    }

    // Obtener suma de todos los movimientos por empleado
    public long montoPorEmpleado(Integer id){
        return movimientosRepositorio.sumaMontoPorEmpleado(id);
    }

    // Obtener suma de todos los movimientos por empresa
    public long montoPorEmpresa(Integer id){
        return movimientosRepositorio.sumaMontoPorEmpresa(id);
    }

    //Metodo que nos permite tener el id si tenemos su correo
    public Integer idPorCorreo(String correo){
        return movimientosRepositorio.idPorCorreo(correo);

    }
}