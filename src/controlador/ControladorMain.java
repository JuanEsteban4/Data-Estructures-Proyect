package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JPanel;
import vista.InicioVista;
import vista.Vista;

public class ControladorMain implements ActionListener{
    Vista vista;
    ControladorRecordatorios controlRecordatorios;
    ControladorAsignaturas controlAsignaturas;
    
    public ControladorMain(){
        this.vista = new Vista();
        this.controlRecordatorios = new ControladorRecordatorios(this);
        this.controlAsignaturas = new ControladorAsignaturas(this);
        this.vista.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                save();
            }
        });
    }
    
    public void init(){
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
        this.setMain(new InicioVista());
        this.vista.recordatorios.addActionListener(this);
        this.vista.asignaturas.addActionListener(this);
    }
    
    public void setMain(JPanel vista){
        this.vista.main.removeAll();
        this.vista.main.add(vista);
        this.vista.main.revalidate();
        this.vista.main.repaint();
        
    }
    
    //Metodos de los controladores para guardar
    private void save(){
        this.controlRecordatorios.save();
        this.controlAsignaturas.save();
    }
    
    private void clearAdd(){
        controlRecordatorios.cerrarAddVista();
        controlAsignaturas.cerrarVistaAdd();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.recordatorios){
            clearAdd();
            controlRecordatorios.init();
        }
        if(e.getSource() == vista.asignaturas){
            clearAdd();
            controlAsignaturas.init();
        }
        
    }

}
