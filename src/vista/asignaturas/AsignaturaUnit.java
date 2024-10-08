package vista.asignaturas;

import modelo.Asignatura;

public class AsignaturaUnit extends javax.swing.JPanel {
    
    public Asignatura info;
    public final int WIDTH = 900;
    public final int HEIGHT = 180;
    
    public AsignaturaUnit(Asignatura record) {
        this.info = record;
        initComponents();
        updateInfo();
    }
    
    public void updateInfo(){
        nombre.setText(info.getCodigo()+ " - " +info.getNombre());
        creditos.setText("Creditos: "+ String.valueOf(info.getCreditos())+ "  " + "Docente: " + info.getProfesor());
        fecha.setText(info.getHoraInicio() + "   Edificio "+info.getEdificio());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nombre = new javax.swing.JLabel();
        creditos = new javax.swing.JLabel();
        fecha = new javax.swing.JLabel();
        addNoteButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        completeButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        nombre.setBackground(new java.awt.Color(0, 0, 0));
        nombre.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        nombre.setForeground(new java.awt.Color(0, 0, 0));
        nombre.setText("Ti");

        creditos.setBackground(new java.awt.Color(0, 0, 0));
        creditos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        creditos.setForeground(new java.awt.Color(0, 0, 0));
        creditos.setText("Ti");

        fecha.setForeground(new java.awt.Color(51, 51, 51));
        fecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fecha.png"))); // NOI18N

        addNoteButton.setBackground(new java.awt.Color(51, 153, 255));
        addNoteButton.setForeground(new java.awt.Color(0, 0, 0));
        addNoteButton.setText("Calificaciones");

        deleteButton.setBackground(new java.awt.Color(255, 153, 153));
        deleteButton.setForeground(new java.awt.Color(0, 0, 0));
        deleteButton.setText("Eliminar");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        completeButton.setBackground(new java.awt.Color(153, 255, 153));
        completeButton.setForeground(new java.awt.Color(0, 0, 0));
        completeButton.setText("Completado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(addNoteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(creditos, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(completeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(693, 693, 693)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nombre)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(addNoteButton)))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(creditos)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(completeButton)))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fecha)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(deleteButton)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton addNoteButton;
    public javax.swing.JButton completeButton;
    private javax.swing.JLabel creditos;
    public javax.swing.JButton deleteButton;
    private javax.swing.JLabel fecha;
    private javax.swing.JLabel nombre;
    // End of variables declaration//GEN-END:variables
}
