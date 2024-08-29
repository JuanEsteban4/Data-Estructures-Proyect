
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.asignaturas.AsignaturaVista;

public class ControladorAsignaturas implements ActionListener{
    //Para dibujar recordatorios en JPanel
    public static int puntero = 0;
    private AsignaturaVista vista;
    private ControladorMain main;
    
    public ControladorAsignaturas(ControladorMain main){
        this.main = main;
        this.vista = new AsignaturaVista();
    }
    
    public void init(){
        this.main.setMain(vista);
        this.vista.add.addActionListener(this);
    }    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Agregar asign");
    }    
}
