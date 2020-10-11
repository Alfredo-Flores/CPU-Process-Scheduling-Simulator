/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.scheduling.simulator.fcfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import process.scheduling.simulator.ProcessObj;

/**
 *
 * @author Alfredo
 */
public class FirstComeFirstServe {
    int tiempo = 0;

    Queue<ProcessObj> cola = new LinkedList<>();
    List<ProcessObj> lista = new ArrayList<>();
    List<ProcessObj> listacompletados = new ArrayList<>();

    public List<ProcessObj> allocateResources( List<ProcessObj> l) throws InterruptedException {
        System.out.println("EMPEZANDO PRIMERO EN LLEGAR PRIMERO EN SER SERVIDO (FCFS)--->");
        lista = l ;

        while (lista.size()>0 || cola.size()>0) {

            agregarACola();

            while (cola.size()==0) {
                System.out.println("------Esperando Procesos--------");
                TimeUnit.SECONDS.sleep(1);
                tiempo++;
                agregarACola();
            }

            ProcessObj ProcesoCorriendo = cola.remove();
            System.out.println("Nombre del Proceso: - " + ProcesoCorriendo.getNombre() + " con ID = " + ProcesoCorriendo.getId() + " esta corriendo");

            for (int j = 0; j < ProcesoCorriendo.getTiempoejecucion(); j++) {
                System.out.println("------Corriendo a 1 segundo--------");
                TimeUnit.SECONDS.sleep(1);
                tiempo++;
                agregarACola();
            }

            ProcesoCorriendo.setInstantefin(tiempo);
            ProcesoCorriendo.setTiemposervicio(tiempo - ProcesoCorriendo.getInstantellegada());
            ProcesoCorriendo.setTiempoespera(ProcesoCorriendo.getTiemposervicio() - ProcesoCorriendo.getTiempoejecucion());
            System.out.println(" TIEMPO DE COMPLETADO = "+ProcesoCorriendo.getInstantefin());
            System.out.println(" TIEMPO DE SERVICIO = "+ProcesoCorriendo.getTiemposervicio());
            System.out.println(" TIEMPO DE ESPERA = "+ProcesoCorriendo.getTiempoespera());
            
            this.listacompletados.add(ProcesoCorriendo);
            
        }

        return listacompletados;
     }
    
    public void agregarACola(){

        if(!lista.isEmpty()){
            for (int i = 0; i < lista.size(); i++) {

            ProcessObj p = lista.get(i);
            if (tiempo >= p.getInstantellegada()) {
                cola.add(p);
                lista.remove(i);
            }

            }
        }

    }
}
