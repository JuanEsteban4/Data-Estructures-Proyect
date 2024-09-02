
package controlador;

import functions.AsignaturasActivity;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Asignatura;
import vista.asignaturas.*;


public class ControladorAsignaturas implements ActionListener{
    //Para dibujar JPanel
    public static int puntero = 0;
    private final AsignaturaVista vista;
    private final ControladorMain main;
    private final AgregarAsignatura add;
    private final AsignaturasActivity activity;
    private final AsignaturaNotas notas;
    
    public ControladorAsignaturas(ControladorMain main){
        this.main = main;
        this.activity = new AsignaturasActivity();
        this.vista = new AsignaturaVista();
        this.add = new AgregarAsignatura();
        this.notas = new AsignaturaNotas();
        this.vista.buscar.setEnabled(false);
        this.vista.restablecer.setVisible(false);
    }
    
    public void init(){
        this.main.setMain(vista);
        
        this.vista.add.addActionListener(this);
        this.add.agregar.addActionListener(this);
        this.vista.buscar.addActionListener(this);
        this.vista.restablecer.addActionListener(this);
        this.vista.orden.addActionListener(this);
        this.vista.edificio.addActionListener(this);
                
        this.actualizarVistaListado();
    }
    
    public void save(){
        this.activity.saveAsignaturas();
    }
    
    public void cerrarVistaAdd(){
        this.add.dispose();
        this.notas.dispose();
    }
    
    public void limpiarVistaAdd(){
        this.add.nombre.setText("");
        this.add.codigo.setText("");
        this.add.profesor.setText("");
        this.add.creditos.setText("");
        this.add.edificio.setText("");
        
        this.add.nombreWarn.setVisible(false);
        this.add.codigoWarn.setVisible(false);
        this.add.profesorWarn.setVisible(false);
        this.add.creditosWarn.setVisible(false);
        this.add.edificioWarn.setVisible(false);
    }
    
    public void repaint(){
        this.vista.revalidate();
        this.vista.repaint();
    }
    
    public void agregarAsignatura(Asignatura s){
        activity.agregarAsignatura(s);
        dibujarAsignatura(s);
    }
    
    private void actualizarVistaListado(){
        this.vista.asignaturasLista.removeAll();
        puntero = 0;
        if( activity.getListado()== null) {
            repaint();
            return;
        }
        for(Asignatura r: activity.getListado()){
            dibujarAsignatura(r);
        }
        repaint();
    }
    
    private void actualizarVistaListado(Asignatura[] s){
        this.vista.asignaturasLista.removeAll();
        puntero = 0;
        if(s == null) {
            repaint();
            return;
        }
        for(Asignatura r: s){
            dibujarAsignatura(r);
        }
        repaint();
    }
    
    private void actualizarVistaListado(ArrayList<Asignatura> s){
        this.vista.asignaturasLista.removeAll();
        puntero = 0;
        if(s == null) {
            repaint();
            return;
        }
        for(Asignatura r: s){
            dibujarAsignatura(r);
        }
        repaint();
    }
    
    private boolean verifyInput(){
        
        boolean result = true;
        
        if(this.add.nombre.getText().isEmpty()){
            this.add.nombreWarn.setVisible(true);
            result = false;
        }
        if(this.add.codigo.getText().isEmpty()){
            this.add.codigoWarn.setVisible(true);
            result = false;
        }
        if(this.add.profesor.getText().isEmpty()){
            this.add.profesorWarn.setVisible(true);
            result = false;
        }
        if(this.add.creditos.getText().isEmpty()){
            this.add.creditosWarn.setVisible(true);
            result = false;
        }
        if(this.add.edificio.getText().isEmpty() || IsNumeric(this.add.edificio.getText().charAt(0))){
            this.add.edificioWarn.setVisible(true);
            result = false;
        }
        try{
            Integer.valueOf(this.add.creditos.getText());
        }catch(NumberFormatException e){
            this.add.creditosWarn.setVisible(true);
            result = false;
        }
        
        try{
            Integer.valueOf(this.add.codigo.getText());
        }catch(NumberFormatException e){
            this.add.codigoWarn.setVisible(true);
        }
        return result;
    }
    
    public void dibujarAsignatura(Asignatura s){
        //Dibujamos recordatorio y agregamo listeners para sus botones
        AsignaturaUnit dibujo = new AsignaturaUnit(s);
        
        dibujo.addNoteButton.addActionListener((ActionEvent e) -> {
            new AsignaturaNotas().init(s);
        });
        
        dibujo.deleteButton.addActionListener((ActionEvent e) -> {
            int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar " + s.getNombre()+"?", "Eliminar asignatura " + s.getNombre(), JOptionPane.YES_NO_OPTION);
        
            if (opcion == JOptionPane.YES_OPTION) {
                activity.eliminarAsignatura(dibujo.info.getCodigo());
                actualizarVistaListado();
                puntero--;
            } 
        });
        
        //Establecemos coords para dibujar, agregamos al panel y repintamos
        dibujo.setBounds(0, puntero*dibujo.HEIGHT, dibujo.WIDTH, dibujo.HEIGHT);
        this.vista.asignaturasLista.setPreferredSize(new Dimension(0,puntero*dibujo.HEIGHT+dibujo.HEIGHT));
        this.vista.asignaturasLista.add(dibujo);
        repaint();
        puntero++;
    }
    
    public void buscarAsignatura(){
        System.out.println("Hala");
        Asignatura s = activity.buscarAsignaturaPorCodigo(this.vista.codigo.getText());
        if(s == null){
            JOptionPane.showMessageDialog(null, "No se ha encontrado asignatura", "No resultados", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        this.vista.restablecer.setVisible(true);
        this.vista.asignaturasLista.removeAll();
        puntero = 0;
        dibujarAsignatura(s);
        repaint();
        System.out.println("Hala");
    }
    
    private void cambiarOrden(){
        if(this.vista.orden.getSelectedIndex() == 0){
            this.actualizarVistaListado();
            return;
        }
        if(this.vista.orden.getSelectedIndex() == 1){
            this.activity.ordenarAsignaturaByCodigo();
        }
        if(this.vista.orden.getSelectedIndex() == 2){
            this.activity.ordenarAsignaturaByNombre();
        }
        this.actualizarVistaListado(this.activity.orden);
    }
    
    private void cambiarOrden(int index){
        switch(index){
            case 0 -> actualizarVistaListado();
            case 1 -> actualizarVistaListado(activity.set.mostrarEdificiosConAsignaturas("A"));
            case 2 -> actualizarVistaListado(activity.set.mostrarEdificiosConAsignaturas("B"));
            case 3 -> actualizarVistaListado(activity.set.mostrarEdificiosConAsignaturas("C"));
            case 4 -> actualizarVistaListado(activity.set.mostrarEdificiosConAsignaturas("D"));
            case 5 -> actualizarVistaListado(activity.set.mostrarEdificiosConAsignaturas("E"));
            case 6 -> actualizarVistaListado(activity.set.mostrarEdificiosConAsignaturas("F"));
            case 7 -> actualizarVistaListado(activity.set.mostrarEdificiosConAsignaturas("G"));
            case 8 -> actualizarVistaListado(activity.set.mostrarEdificiosConAsignaturas("H"));
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.vista.add){
            this.add.setLocationRelativeTo(null);
            this.add.setVisible(true);
        }
        if(e.getSource() == this.add.agregar){
            if(!verifyInput()) return;
            agregarAsignatura(new Asignatura(this.add.nombre.getText(),
                                             this.add.codigo.getText(),
                                             this.add.profesor.getText(),
                                             Integer.parseInt(this.add.creditos.getText()),
                                             this.add.edificio.getText())
            );
            
            limpiarVistaAdd();
            cerrarVistaAdd();
        }
        if(e.getSource() == this.vista.buscar){
            buscarAsignatura();
        }
        if(e.getSource() == this.vista.restablecer){
            this.vista.codigo.setText("");
            this.actualizarVistaListado();
            this.vista.buscar.setEnabled(false);
            this.vista.restablecer.setVisible(false);
        }
        if(e.getSource() == this.vista.orden){
            cambiarOrden();
        }
        if(e.getSource() == this.vista.edificio){
            cambiarOrden(this.vista.edificio.getSelectedIndex());
        }
    }    

    private boolean IsNumeric(char s) {
        try{
            Integer.valueOf(String.valueOf(s));
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
}
