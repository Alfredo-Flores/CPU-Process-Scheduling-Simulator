/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.scheduling.simulator.roundRobin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import process.scheduling.simulator.ProcessObj;

public class RoundRobin {

    int tiempo = 0;
    int tiempoCuantico;
    List<ProcessObj> q = new ArrayList<>();
    List<ProcessObj> cloneq = new ArrayList<>();
    List<ProcessObj> lista = new ArrayList<>();
    List<ProcessObj> completado = new ArrayList<>();

    public List<ProcessObj> allocateResources(List<ProcessObj> l, List<ProcessObj> c, int tiempoQ) throws InterruptedException {
        System.out.println("Comenzó el algoritmo round robin--->");
        lista.addAll(l);
        completado.addAll(c);
        tiempoCuantico=tiempoQ;
        while (lista.size() > 0 || q.size() > 0) {
            AgregarAlaLista();
            while (q.isEmpty()) {
                System.out.println("------Esperando a recibir un proceso---------");
                TimeUnit.SECONDS.sleep(1);
                tiempo++;
                AgregarAlaLista();
            }
            SeleccionaElMejor();
        }
        for (int j = 0; j < completado.size(); j++) {
            ProcessObj p = completado.get(j);
            p.setTiempoejecucion(l.get(j).getTiempoejecucion());
            p.setTiemposervicio(p.getInstantefin()- p.getInstantellegada());
            p.setTiempoespera(p.getTiemposervicio()- p.getTiempoejecucion());
            completado.set(j, p);
            }
        
        for (int i = 0; i < completado.size(); i++) {
            System.out.println("  Hora de llegada del proceso = "+completado.get(i).getInstantellegada());
            System.out.println(" Tiempo completado del proceso = "+completado.get(i).getInstantefin());
            System.out.println(" Tiempo de respuesta del proceso = "+completado.get(i).getTiemposervicio());
            System.out.println(" Tiempo de ráfaga del proceso = "+completado.get(i).getTiempoejecucion());
            System.out.println(" Tiempo de espera del proceso = "+completado.get(i).getTiempoespera());
            System.out.println("---------------------------------------------------------");
        }
        return completado;
    }

    public void AgregarAlaLista() {
        if (!lista.isEmpty()) {
            for (int i = 0; i < lista.size(); i++) {
                ProcessObj p = lista.get(i);
                if (tiempo >= p.getInstantellegada()) {
                    q.add(p);
                    lista.remove(i);
                }
            }
        }
    }

    private void SeleccionaElMejor() throws InterruptedException {
        List<Integer> bTiempolista = new ArrayList<>();
        if (!q.isEmpty()) {
            for (int i = 0; i < q.size(); i++) {
                bTiempolista.add(q.get(i).getTiempoejecucion());
            }
            Collections.sort(bTiempolista);

            for (int i = 0; i < q.size(); i++) {
                if (bTiempolista.get(0) == q.get(i).getTiempoejecucion()) {
                    if (bTiempolista.get(0) > tiempoCuantico) {
                        ProcessObj p = q.get(i);
                        ProcessObj pSiguiente = p;
                        pSiguiente.setTiempoejecucion(p.getTiempoejecucion() - tiempoCuantico);
                        q.set(i, pSiguiente);
                        Correr(p,tiempoCuantico);

                    } else if (bTiempolista.get(0) == tiempoCuantico) {
                        for (int j = 0; j < completado.size(); j++) {
                            if (q.get(i).getId() == completado.get(j).getId()) {
                                ProcessObj p = completado.get(j);
                                p.setInstantefin(tiempo + tiempoCuantico);
                                completado.set(j, p);
                            }
                        }
                        Correr(q.remove(i),tiempoCuantico);

                    } else if (bTiempolista.get(0) < tiempoCuantico) {
                        for (int j = 0; j < completado.size(); j++) {
                            if (q.get(i).getId() == completado.get(j).getId()) {
                                ProcessObj p = completado.get(j);
                                p.setInstantefin(tiempo + bTiempolista.get(0));
                                completado.set(j, p);
                            }
                        }
                        Correr(q.remove(i),bTiempolista.get(0));
                    }
                }
            }
        }
    }

    private void Correr(ProcessObj ProcesoCorriendo, Integer t) throws InterruptedException {
         System.out.println("Proceso con el Nombre:  - " +  ProcesoCorriendo.getNombre() + " E Id = " + ProcesoCorriendo.getId() + " esta corriendo");
        for (int j = 0; j < t; j++) {
                    System.out.println("------Corriendo una segunda--------");
                    TimeUnit.SECONDS.sleep(1);
                    tiempo++;
                    AgregarAlaLista();
                }
    }

}
