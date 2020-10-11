/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package process.scheduling.simulator.sjf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import process.scheduling.simulator.ProcessObj;

/**
 *
 * @author Alejandro Marquez
 */
public class ShortestJobFirst {
     int tiempo = 0;
        List<ProcessObj> Cola = new ArrayList<>();
        List<ProcessObj> lista = new ArrayList<>();
        List<ProcessObj> completados = new ArrayList<>();
    public List<ProcessObj> allocateResources( List<ProcessObj> l) throws InterruptedException {

        System.out.println("EMPEZANDO SIGUIENTE PROCESO MAS CORTO (SJN) ");
        lista = l;

        while (lista.size()>0 || Cola.size()>0) { 
            AnadirACola();

            while (Cola.isEmpty()) {                
                System.out.println("------Esperando Procesos---------");
                TimeUnit.SECONDS.sleep(1);
                tiempo++;
                AnadirACola();
            }

            ProcessObj ProcesoCorriendo = SeleccionarMejor();
            System.out.println("Nombre del Proceso: - " + ProcesoCorriendo.getNombre() + " con ID = " + ProcesoCorriendo.getId() + " esta corriendo");

            for (int j = 0; j < ProcesoCorriendo.getTiempoejecucion(); j++) {
                System.out.println("------Corriendo a 1 segundo--------");
                TimeUnit.SECONDS.sleep(1);
                tiempo++;
                AnadirACola();
            }

            ProcesoCorriendo.setInstantefin(tiempo);
            ProcesoCorriendo.setTiemposervicio(tiempo-ProcesoCorriendo.getInstantellegada());
            ProcesoCorriendo.setTiempoespera(ProcesoCorriendo.getTiemposervicio()-ProcesoCorriendo.getTiempoejecucion());
            System.out.println(" TIEMPO DE COMPLETADO= "+ProcesoCorriendo.getInstantefin());
            System.out.println(" TIEMPO DE SERVICIO = "+ProcesoCorriendo.getTiemposervicio());
            System.out.println(" TIEMPO DE ESPERA = "+ProcesoCorriendo.getTiempoespera());
            
            this.completados.add(ProcesoCorriendo);
            
        }
        return completados;
     }
    
    public void AnadirACola(){
        if(!lista.isEmpty()){
            for (int i = 0; i < lista.size(); i++) {
            ProcessObj p = lista.get(i);
            if (tiempo >= p.getInstantellegada()) {
                Cola.add(p);
                lista.remove(i);
            }
        }
                                
        }
    }

    private ProcessObj SeleccionarMejor() {
        List<Integer> TiempoenlistaB = new ArrayList<>();
        if(!Cola.isEmpty()){
            for (int i = 0; i < Cola.size(); i++) {
                TiempoenlistaB.add(Cola.get(i).getTiempoejecucion());
            }
            Collections.sort(TiempoenlistaB);
            
            for (int i = 0; i < Cola.size(); i++) {
                if (TiempoenlistaB.get(0)== Cola.get(i).getTiempoejecucion()) {
                    return Cola.remove(i);
                }
            }
        }
        return null;
    }

}
