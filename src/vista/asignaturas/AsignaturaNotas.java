/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.asignaturas;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import modelo.Asignatura;
import modelo.Nota;

public class AsignaturaNotas extends javax.swing.JFrame {

    public AsignaturaNotas() {
        initComponents();
    }
    
    public void init(Asignatura s){
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.titulo.setText(s.getCodigo()+" - "+s.getNombre());
        DefaultTableModel tabla = (DefaultTableModel) this.listaNotas.getModel();
        
        if(!s.getNotas().isEmpty()){
            for(Nota x: s.getNotas()){
                tabla.addRow(new Object[]{String.valueOf(x.getNota()),String.valueOf(x.getPorcentaje()),x.getInfo()});
            }
        }
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                actualizarNotas(tabla,s);
            }
        });

    }
    
    private void actualizarNotas(DefaultTableModel tabla,Asignatura s){
        s.setNotas(new ArrayList<Nota>());
        for(int i = 0; i<tabla.getRowCount(); i++){
            if("".equals(String.valueOf(tabla.getValueAt(i, 0)))){
                continue;
            }
            s.getNotas().add(new Nota(Float.parseFloat((String)tabla.getValueAt(i, 0)),
                            Float.parseFloat("".equals((String)tabla.getValueAt(i, 1)) ? "0":(String)tabla.getValueAt(i, 1)),
                            (String)tabla.getValueAt(i, 2)
            ));
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaNotas = new javax.swing.JTable();
        borrar = new javax.swing.JButton();
        agregar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        titulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        titulo.setForeground(new java.awt.Color(0, 0, 0));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("Titulo");

        listaNotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Nota", "Porcentaje", "Descripción"
            }
        ));
        jScrollPane1.setViewportView(listaNotas);
        // Configurar el editor para la columna de números (Nota y Porcentaje)
        TableColumn numberColumn = listaNotas.getColumnModel().getColumn(0);
        numberColumn.setCellEditor(new NumericEditor());

        numberColumn = listaNotas.getColumnModel().getColumn(1);
        numberColumn.setCellEditor(new NumericEditor());

        // Configurar el editor para la columna de texto (Descripción)
        TableColumn textColumn = listaNotas.getColumnModel().getColumn(2);
        textColumn.setCellEditor(new DefaultCellEditor(new JTextField()));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Aplicar el renderizador a todas las columnas
        for (int i = 0; i < listaNotas.getColumnCount(); i++) {
            TableColumn column = listaNotas.getColumnModel().getColumn(i);
            column.setCellRenderer(centerRenderer);
        }

        borrar.setText("Borrar");
        borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarActionPerformed(evt);
            }
        });

        agregar.setText("Agregar");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(agregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(borrar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                    .addComponent(titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(titulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(borrar)
                    .addComponent(agregar))
                .addGap(14, 14, 14))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        DefaultTableModel tabla = (DefaultTableModel) this.listaNotas.getModel();
        tabla.addRow(new Object[]{"","",""});
    }//GEN-LAST:event_agregarActionPerformed

    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed
        DefaultTableModel tabla = (DefaultTableModel) this.listaNotas.getModel();
        int row = this.listaNotas.getSelectedRow();
        if(row == -1){
            return;
        }
        tabla.removeRow(row);
    }//GEN-LAST:event_borrarActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton agregar;
    public javax.swing.JButton borrar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable listaNotas;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables

}
class NumericEditor extends DefaultCellEditor {
        private final JTextField textField;

        public NumericEditor() {
            super(new JTextField());
            textField = (JTextField) getComponent();
            ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DecimalDocumentFilter());
        }

        @Override
        public Object getCellEditorValue() {
            return textField.getText();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            textField.setText(value != null ? value.toString() : "");
            return textField;
        }
 }
class DecimalDocumentFilter extends DocumentFilter {
    private static final String DECIMAL_PATTERN = "\\d*\\.?\\d*"; // Permite dígitos con un solo punto decimal

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (isValidInput(fb.getDocument(), offset, string)) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (isValidInput(fb.getDocument(), offset, text)) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length);
    }

    private boolean isValidInput(Document doc, int offset, String input) {
        try {
            String currentText = doc.getText(0, doc.getLength());
            String newText = new StringBuilder(currentText).insert(offset, input).toString();
            return newText.matches(DECIMAL_PATTERN);
        } catch (BadLocationException e) {
            return false;
        }
    }
}

