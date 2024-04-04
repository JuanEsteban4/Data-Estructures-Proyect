package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JPanel;
import vista.inicioVista;
import vista.vista;

public class controladorMain implements ActionListener{
    vista vista;
    controladorRecordatorios controlRecordatorios;
    
    public controladorMain(){
        this.vista = new vista();
        this.controlRecordatorios = new controladorRecordatorios(this);
        
        this.vista.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                save();
            }
        });
    }
    
    public void init(){
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        this.setMain(new inicioVista());
        vista.recordatorios.addActionListener(this);
    }
    
    public void setMain(JPanel vista){
        this.vista.main.removeAll();
        this.vista.main.add(vista);
        this.vista.main.revalidate();
        this.vista.main.repaint();
        
    }
    
    //Metodos de los controladore para guardar
    private void save(){
        this.controlRecordatorios.save();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.recordatorios){
            controlRecordatorios.init();
        }
    }


    
}
