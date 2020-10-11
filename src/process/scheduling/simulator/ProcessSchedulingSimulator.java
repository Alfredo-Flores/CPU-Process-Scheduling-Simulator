package process.scheduling.simulator;

import process.scheduling.simulator.view.Starter;

public class ProcessSchedulingSimulator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Starter().setVisible(true);
            }
        });
    }
    
}
