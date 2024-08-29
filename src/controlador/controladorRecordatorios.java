package controlador;

import functions.RecordatoriosActivity;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Recordatorio;
import vista.recordatorio.AgregarRecordatorio;
import vista.recordatorio.RecordatorioUnit;
import vista.recordatorio.RecordatoriosVista;

public class ControladorRecordatorios implements ActionListener{
    
    //Para dibujar recordatorios en JPanel
    private static int puntero = 0;
    private RecordatoriosVista vista;
    private AgregarRecordatorio addVista;
    private ControladorMain main;
    
    RecordatoriosActivity activity;

    public ControladorRecordatorios(ControladorMain main){
        this.vista = new RecordatoriosVista();
        this.addVista = new AgregarRecordatorio();
        this.activity = new RecordatoriosActivity();
        this.main = main;
    }
    
    public void init(){
        this.main.setMain(vista);
        this.vista.add.addActionListener(this);
        this.addVista.agregar.addActionListener(this);
        //this.actualizarVistaListado(); //Si hay recordatorios guardados
    }
    
    public boolean verifyInput(){
        if(this.addVista.titulo.getText().isEmpty()){
            this.addVista.tituloLabel.setVisible(true);
            return false;
        }
        return true;                                  
    }
    
    public void agregarRecordatorio(Recordatorio nw){
        //Agregamos al listado, limpiamos los inputs y cerramos ventana de agregar
        activity.addRecordatorio(nw);
        limpiarVistaAdd();
        this.addVista.dispose();
        dibujarRecordatorio(nw);
    }
    
    public void dibujarRecordatorio(Recordatorio nw){
        //Dibujamos recordatorio y agregamo listeners para sus botones
        RecordatorioUnit recordDraw = new RecordatorioUnit(nw);
        
        recordDraw.checkButton.addActionListener((ActionEvent e) -> {
            activity.checkRecordatorio(recordDraw.info);
            recordDraw.updateInfo();
        });
        
        recordDraw.deleteButton.addActionListener((ActionEvent e) -> {
            int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar " + nw.getTitulo()+"?", "Eliminar recordatorio " + nw.getTitulo(), JOptionPane.YES_NO_OPTION);
        
            if (opcion == JOptionPane.YES_OPTION) {
                activity.deleteRecordatorio(recordDraw.info);
                actualizarVistaListado();
                puntero--;
            } 
        });
        
        //Establecemos coords para dibujar, agregamos al panel y repintamos
        recordDraw.setBounds(0, puntero*recordDraw.HEIGHT, recordDraw.WIDTH, recordDraw.HEIGHT);
        this.vista.recordatoriosLista.setPreferredSize(new Dimension(0,puntero*recordDraw.HEIGHT+recordDraw.HEIGHT));
        this.vista.recordatoriosLista.add(recordDraw);
        repaint();
        puntero++;
    }
    
    public void repaint(){
        this.vista.revalidate();
        this.vista.repaint();
    }
    
    public void save(){
        activity.saveRecordatorios();
    }
    
    public void limpiarVistaAdd(){
        this.addVista.titulo.setText("");
        this.addVista.descripcion.setText("");
        this.addVista.fecha.setDate(null);
    }
    
    //Si hay un cambio en la lista de recordatorios
    private void actualizarVistaListado(){
        this.vista.recordatoriosLista.removeAll();
        puntero = 0;
        for(Recordatorio r: activity.getListado()){
            dibujarRecordatorio(r);
        }
        repaint();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.vista.add){
            this.addVista.setLocationRelativeTo(null);
            this.addVista.setVisible(true);
        }
        if(e.getSource() == this.addVista.agregar){
            if(!verifyInput()) return;
            agregarRecordatorio(new Recordatorio(this.addVista.titulo.getText(),
                                                 this.addVista.descripcion.getText(),
                                                 this.addVista.fecha.getDate()));
        }
    }
}
