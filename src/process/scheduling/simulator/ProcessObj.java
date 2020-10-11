/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package process.scheduling.simulator;

/**
 *
 * @author Mahesh
 */
public class ProcessObj {
    private String nombre;
    private int id;
    private int instantellegada;
    private int tiempoejecucion;
    private int instantefin;
    private int tiemposervicio;
    private int tiempoespera;

    /**
     * @param nombre
     * @param id
     * @param instantellegada
     * @param tiempoejecucion
     */
    public ProcessObj(String nombre, int id, int instantellegada, int tiempoejecucion){
        this.id = id;
        this.nombre = nombre;
        this.instantellegada = instantellegada;
        this.tiempoejecucion = tiempoejecucion;
    }

    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the name to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the arrivalTime
     */
    public int getInstantellegada() {
        return instantellegada;
    }

    /**
     * @param instantellegada the arrivalTime to set
     */
    public void setInstantellegada(int instantellegada) {
        this.instantellegada = instantellegada;
    }

    /**
     * @return the brustTime
     */
    public int getTiempoejecucion() {
        return tiempoejecucion;
    }

    /**
     * @param tiempoejecucion the brustTime to set
     */
    public void setTiempoejecucion(int tiempoejecucion) {
        this.tiempoejecucion = tiempoejecucion;
    }

    /**
     * @return the completeTime
     */
    public int getInstantefin() {
        return instantefin;
    }

    /**
     * @param instantefin the completeTime to set
     */
    public void setInstantefin(int instantefin) {
        this.instantefin = instantefin;
    }

    /**
     * @return the turnaroundTime
     */
    public int getTiemposervicio() {
        return tiemposervicio;
    }

    /**
     * @param tiemposervicio the turnaroundTime to set
     */
    public void setTiemposervicio(int tiemposervicio) {
        this.tiemposervicio = tiemposervicio;
    }

    /**
     * @return the waitingTime
     */
    public int getTiempoespera() {
        return tiempoespera;
    }

    /**
     * @param tiempoespera the waitingTime to set
     */
    public void setTiempoespera(int tiempoespera) {
        this.tiempoespera = tiempoespera;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
    
    
}
