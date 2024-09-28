
package vista.horario;
import functions.AsignaturasActivity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import modelo.Asignatura;



public class HorarioVista extends javax.swing.JPanel{
    AsignaturasActivity AsignaturasActivity = new AsignaturasActivity();


    public HorarioVista(AsignaturasActivity asignaturasActivity) {
        this.AsignaturasActivity = asignaturasActivity;
        initComponents();
        cargarComboBoxProfesores();
        inicializarMapaHorario();

        jListProfesores.addActionListener(e -> {
            String profesorSeleccionado = (String) jListProfesores.getSelectedItem();
            if (profesorSeleccionado != null && !profesorSeleccionado.equals("")) {
                cargarAsignaturasPorProfesor(profesorSeleccionado);
            }
        });
    }

    

    
        private void cargarComboBoxProfesores() {
            limpiarHorario();
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            model.addElement("");

            ArrayList<String> profesores = AsignaturasActivity.obtenerProfesoresDisponibles();

            for (String profesor : profesores) {
                model.addElement(profesor);
            }
            jListProfesores.setModel(model); // Asegúrate de que este JComboBox está inicializado
        }


       // Método para actualizar los JComboBox de profesores y asignaturas
        public void actualizarComboBox() {
            cargarComboBoxProfesores(); // Carga nuevamente los profesores
            // Si ya tienes un profesor seleccionado, puedes cargar sus asignaturas
            String profesorSeleccionado = (String) jListProfesores.getSelectedItem();
            if (profesorSeleccionado != null && !profesorSeleccionado.equals("")) {
                cargarAsignaturasPorProfesor(profesorSeleccionado);
            }
        }

       
       
       

// Método para cargar las asignaturas por el profesor seleccionado
private void cargarAsignaturasPorProfesor(String nombreProfesor) {
    // Obtener la lista de asignaturas para el profesor seleccionado
    ArrayList<Asignatura> asignaturas = AsignaturasActivity.obtenerAsignaturasPorProfesor(nombreProfesor);
    
    // Crear un modelo para el JComboBox de asignaturas
    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
    
    // Agregar cada nombre de asignatura al modelo
    for (Asignatura asignatura : asignaturas) {
        model.addElement(asignatura.getNombre()); // Agregamos el nombre de la asignatura
    }
    
    // Asignar el modelo al JComboBox de asignaturas
    jListAsignaturas.setModel(model); // Asegúrate de que este JComboBox está inicializado
    
    jListAsignaturas.addActionListener(e -> {
    String asignaturaSeleccionada = (String) jListAsignaturas.getSelectedItem();
    if (asignaturaSeleccionada != null) {
        // Limpiar el horario antes de actualizarlo
        limpiarHorario();
        
        // Buscar la asignatura seleccionada en el listado
        Asignatura asignatura = obtenerAsignaturaPorNombre(asignaturaSeleccionada);
        if (asignatura != null) {
            // Desplegar el horario de la asignatura
            desplegar(asignatura.getDia(), asignatura.getHoraInicio());
        }
    }
});
}

    // Método para obtener una asignatura por su nombre
    private Asignatura obtenerAsignaturaPorNombre(String nombreAsignatura) {
        for (Asignatura asignatura : AsignaturasActivity.getListado()) {
            if (asignatura.getNombre().equals(nombreAsignatura)) {
                return asignatura;
            }
        }
        return null;
    }

       

   
    private HashMap<String, HashMap<String, JTextArea>> mapaHorario;

    public void inicializarMapaHorario() {
        mapaHorario = new HashMap<>();

        // Inicializar los mapeos de días y horas
        HashMap<String, JTextArea> lunes = new HashMap<>();
        lunes.put("7", jTextAreaL7);
        lunes.put("9", jTextAreaL9);
        lunes.put("11", jTextAreaL12);
        lunes.put("14", jTextAreaL14);
        lunes.put("16", jTextAreaL15);
        lunes.put("18", jTextAreaL17);

        HashMap<String, JTextArea> martes = new HashMap<>();
        martes.put("7", jTextAreaK7);
        martes.put("9", jTextAreaK9);
        martes.put("11", jTextAreaK12);
        martes.put("14", jTextAreaK14);
        martes.put("16", jTextAreaK15);
        martes.put("18", jTextAreaK17);
        
        HashMap<String, JTextArea> miercoles = new HashMap<>();
        miercoles.put("7", jTextAreaM7);
        miercoles.put("9", jTextAreaM9);
        miercoles.put("11", jTextAreaM12);
        miercoles.put("14", jTextAreaM14);
        miercoles.put("16", jTextAreaM15);
        miercoles.put("18", jTextAreaM17);

        HashMap<String, JTextArea> jueves = new HashMap<>();
        jueves.put("7", jTextAreaJ7);
        jueves.put("9", jTextAreaJ9);
        jueves.put("11", jTextAreaJ12);
        jueves.put("14", jTextAreaJ14);
        jueves.put("16", jTextAreaJ15);
        jueves.put("18", jTextAreaJ17);

        HashMap<String, JTextArea> viernes = new HashMap<>();
        viernes.put("7", jTextAreaV7);
        viernes.put("9", jTextAreaV9);
        viernes.put("11", jTextAreaV12);
        viernes.put("14", jTextAreaV14);
        viernes.put("16", jTextAreaV15);
        viernes.put("18", jTextAreaV17);

        // Asignar los días a su respectivo horario
        mapaHorario.put("Lunes", lunes);
        mapaHorario.put("Martes", martes);
        mapaHorario.put("Miercoles", miercoles);
        mapaHorario.put("Jueves", jueves);
        mapaHorario.put("Viernes", viernes);
    }
    
    
    
    //Metodo para pintar de color en el horario con respecto a la hora de inicio y fin de asignatura
    private void desplegar(String dia, String horaIni) {
    Color colorAsignatura = new Color(57, 147, 206); 
    limpiarHorario();
    // Pintar hora de inicio para el día actual
    if (mapaHorario.containsKey(dia)) {
        HashMap<String, JTextArea> horarioDia = mapaHorario.get(dia);
        // Dividir la cadena de horas 
        String[] horasPartes = horaIni.split("-");
        String horaInicio = horasPartes[0].replace("AM", "").replace("PM", "").trim(); // Remover AM/PM y espacios
        // Pintar hora de inicio
        if (horarioDia.containsKey(horaInicio)) {
            horarioDia.get(horaInicio).setBackground(colorAsignatura);
        }
        


        // Determinar el día correspondiente
        String diaCorrespondiente;
        switch (dia) {
            case "Lunes": // Lunes
                diaCorrespondiente = "Miercoles"; 
                break;
            case "Martes": // Martes
                diaCorrespondiente = "Jueves"; // Jueves
                break;
            case "Miercoles": // Miércoles
                diaCorrespondiente = "Viernes"; // Viernes
                break;
            case "Jueves": // Jueves
                diaCorrespondiente = "Martes"; // Lunes
                break;
            case "Viernes": // Viernes
                diaCorrespondiente = "Miercoles"; // Martes
                break;
            default:
                System.out.println("Día no válido: " + dia);
                return; // Salir si el día no es válido
        }

        // Pintar hora de inicio y fin para el día correspondiente
        if (mapaHorario.containsKey(diaCorrespondiente)) {
            HashMap<String, JTextArea> horarioDiaCorrespondiente = mapaHorario.get(diaCorrespondiente);
            
            // Pintar hora de inicio en el día correspondiente
            if (horarioDiaCorrespondiente.containsKey(horaInicio)) {
                horarioDiaCorrespondiente.get(horaInicio).setBackground(colorAsignatura);
            }
        }
    } else {
        System.out.println("Día no válido: " + dia);
    }
}


 


 
    
    
    private void limpiarHorario() {
        jTextAreaL7.setBackground(Color.WHITE); jTextAreaL12.setBackground(Color.WHITE);
        jTextAreaL9.setBackground(Color.WHITE); jTextAreaL14.setBackground(Color.WHITE);
        jTextAreaL9.setBackground(Color.WHITE); jTextAreaL14.setBackground(Color.WHITE);
        jTextAreaL15.setBackground(Color.WHITE); jTextAreaL17.setBackground(Color.WHITE);
        jTextAreaK7.setBackground(Color.WHITE); jTextAreaK12.setBackground(Color.WHITE);
        jTextAreaK9.setBackground(Color.WHITE); jTextAreaK14.setBackground(Color.WHITE);
        jTextAreaK15.setBackground(Color.WHITE); jTextAreaK17.setBackground(Color.WHITE);
        jTextAreaM7.setBackground(Color.WHITE); jTextAreaM12.setBackground(Color.WHITE);
        jTextAreaM9.setBackground(Color.WHITE); jTextAreaM14.setBackground(Color.WHITE);
        jTextAreaM15.setBackground(Color.WHITE); jTextAreaM17.setBackground(Color.WHITE);
        jTextAreaJ7.setBackground(Color.WHITE); jTextAreaJ12.setBackground(Color.WHITE);
        jTextAreaJ9.setBackground(Color.WHITE); jTextAreaJ14.setBackground(Color.WHITE);
        jTextAreaJ15.setBackground(Color.WHITE); jTextAreaJ17.setBackground(Color.WHITE); 
        jTextAreaV7.setBackground(Color.WHITE); jTextAreaV12.setBackground(Color.WHITE);
        jTextAreaV9.setBackground(Color.WHITE); jTextAreaV14.setBackground(Color.WHITE);
        jTextAreaV15.setBackground(Color.WHITE); jTextAreaV17.setBackground(Color.WHITE);
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane57 = new javax.swing.JScrollPane();
        jTextAreaV16 = new javax.swing.JTextArea();
        jScrollPane58 = new javax.swing.JScrollPane();
        jTextAreaM16 = new javax.swing.JTextArea();
        jScrollPane59 = new javax.swing.JScrollPane();
        jTextAreaK16 = new javax.swing.JTextArea();
        jScrollPane60 = new javax.swing.JScrollPane();
        jTextAreaL16 = new javax.swing.JTextArea();
        jScrollPane61 = new javax.swing.JScrollPane();
        jTextAreaJ16 = new javax.swing.JTextArea();
        jScrollPane30 = new javax.swing.JScrollPane();
        jTextArea7 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane24 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane26 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jScrollPane27 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane28 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        jScrollPane32 = new javax.swing.JScrollPane();
        jTextAreaV7 = new javax.swing.JTextArea();
        jScrollPane33 = new javax.swing.JScrollPane();
        jTextAreaM7 = new javax.swing.JTextArea();
        jScrollPane40 = new javax.swing.JScrollPane();
        jTextAreaK7 = new javax.swing.JTextArea();
        jScrollPane41 = new javax.swing.JScrollPane();
        jTextAreaL7 = new javax.swing.JTextArea();
        jScrollPane42 = new javax.swing.JScrollPane();
        jTextAreaJ7 = new javax.swing.JTextArea();
        jScrollPane34 = new javax.swing.JScrollPane();
        jTextAreaV9 = new javax.swing.JTextArea();
        jScrollPane35 = new javax.swing.JScrollPane();
        jTextAreaM9 = new javax.swing.JTextArea();
        jScrollPane43 = new javax.swing.JScrollPane();
        jTextAreaK9 = new javax.swing.JTextArea();
        jScrollPane44 = new javax.swing.JScrollPane();
        jTextAreaL9 = new javax.swing.JTextArea();
        jScrollPane45 = new javax.swing.JScrollPane();
        jTextAreaJ9 = new javax.swing.JTextArea();
        jScrollPane36 = new javax.swing.JScrollPane();
        jTextAreaV12 = new javax.swing.JTextArea();
        jScrollPane37 = new javax.swing.JScrollPane();
        jTextAreaM12 = new javax.swing.JTextArea();
        jScrollPane46 = new javax.swing.JScrollPane();
        jTextAreaK12 = new javax.swing.JTextArea();
        jScrollPane47 = new javax.swing.JScrollPane();
        jTextAreaL12 = new javax.swing.JTextArea();
        jScrollPane48 = new javax.swing.JScrollPane();
        jTextAreaJ12 = new javax.swing.JTextArea();
        jScrollPane38 = new javax.swing.JScrollPane();
        jTextAreaV14 = new javax.swing.JTextArea();
        jScrollPane39 = new javax.swing.JScrollPane();
        jTextAreaM14 = new javax.swing.JTextArea();
        jScrollPane49 = new javax.swing.JScrollPane();
        jTextAreaK14 = new javax.swing.JTextArea();
        jScrollPane50 = new javax.swing.JScrollPane();
        jTextAreaL14 = new javax.swing.JTextArea();
        jScrollPane51 = new javax.swing.JScrollPane();
        jTextAreaJ14 = new javax.swing.JTextArea();
        jListProfesores = new javax.swing.JComboBox<>();
        jListAsignaturas = new javax.swing.JComboBox<>();
        jScrollPane52 = new javax.swing.JScrollPane();
        jTextAreaV15 = new javax.swing.JTextArea();
        jScrollPane53 = new javax.swing.JScrollPane();
        jTextAreaM15 = new javax.swing.JTextArea();
        jScrollPane54 = new javax.swing.JScrollPane();
        jTextAreaK15 = new javax.swing.JTextArea();
        jScrollPane55 = new javax.swing.JScrollPane();
        jTextAreaL15 = new javax.swing.JTextArea();
        jScrollPane56 = new javax.swing.JScrollPane();
        jTextAreaJ15 = new javax.swing.JTextArea();
        jScrollPane29 = new javax.swing.JScrollPane();
        jTextArea6 = new javax.swing.JTextArea();
        jScrollPane62 = new javax.swing.JScrollPane();
        jTextAreaV17 = new javax.swing.JTextArea();
        jScrollPane63 = new javax.swing.JScrollPane();
        jTextAreaM17 = new javax.swing.JTextArea();
        jScrollPane64 = new javax.swing.JScrollPane();
        jTextAreaK17 = new javax.swing.JTextArea();
        jScrollPane65 = new javax.swing.JScrollPane();
        jTextAreaL17 = new javax.swing.JTextArea();
        jScrollPane66 = new javax.swing.JScrollPane();
        jTextAreaJ17 = new javax.swing.JTextArea();
        jScrollPane31 = new javax.swing.JScrollPane();
        jTextArea8 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        jTextAreaV16.setColumns(1);
        jTextAreaV16.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaV16.setRows(2);
        jTextAreaV16.setTabSize(2);
        jTextAreaV16.setBorder(null);
        jTextAreaV16.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane57.setViewportView(jTextAreaV16);

        jTextAreaM16.setColumns(1);
        jTextAreaM16.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaM16.setRows(2);
        jTextAreaM16.setTabSize(2);
        jTextAreaM16.setBorder(null);
        jTextAreaM16.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane58.setViewportView(jTextAreaM16);

        jTextAreaK16.setColumns(1);
        jTextAreaK16.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaK16.setRows(2);
        jTextAreaK16.setTabSize(2);
        jTextAreaK16.setBorder(null);
        jTextAreaK16.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane59.setViewportView(jTextAreaK16);

        jTextAreaL16.setColumns(1);
        jTextAreaL16.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaL16.setRows(2);
        jTextAreaL16.setTabSize(2);
        jTextAreaL16.setBorder(null);
        jTextAreaL16.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane60.setViewportView(jTextAreaL16);

        jTextAreaJ16.setColumns(1);
        jTextAreaJ16.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaJ16.setRows(2);
        jTextAreaJ16.setTabSize(2);
        jTextAreaJ16.setBorder(null);
        jTextAreaJ16.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane61.setViewportView(jTextAreaJ16);

        jTextArea7.setEditable(false);
        jTextArea7.setColumns(1);
        jTextArea7.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextArea7.setRows(2);
        jTextArea7.setTabSize(1);
        jTextArea7.setText("\t\n   14:00\n\t16:00");
        jTextArea7.setBorder(null);
        jTextArea7.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextArea7.setEnabled(false);
        jTextArea7.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane30.setViewportView(jTextArea7);

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Horario de Profesores");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel2.setText("    Hora    |             Lunes           |          Martes            |        Miércoles           |         Jueves             |            Viernes");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(1);
        jTextArea1.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(0, 0, 0));
        jTextArea1.setRows(1);
        jTextArea1.setTabSize(1);
        jTextArea1.setText("\t\n   09:00\n\t11:00");
        jTextArea1.setBorder(null);
        jTextArea1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextArea1.setEnabled(false);
        jTextArea1.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane24.setViewportView(jTextArea1);

        jTextArea3.setEditable(false);
        jTextArea3.setColumns(1);
        jTextArea3.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextArea3.setRows(2);
        jTextArea3.setTabSize(1);
        jTextArea3.setText("\t\n   11:00\n\t13:00");
        jTextArea3.setBorder(null);
        jTextArea3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextArea3.setEnabled(false);
        jTextArea3.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane26.setViewportView(jTextArea3);

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(1);
        jTextArea2.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextArea2.setForeground(new java.awt.Color(0, 0, 0));
        jTextArea2.setRows(1);
        jTextArea2.setTabSize(1);
        jTextArea2.setText("\t\n   07:00\n\t09:00");
        jTextArea2.setBorder(null);
        jTextArea2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextArea2.setEnabled(false);
        jTextArea2.setMinimumSize(new java.awt.Dimension(80, 80));
        jTextArea2.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        jTextArea2.setSelectionColor(new java.awt.Color(0, 0, 0));
        jScrollPane27.setViewportView(jTextArea2);

        jTextArea5.setEditable(false);
        jTextArea5.setColumns(1);
        jTextArea5.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextArea5.setRows(2);
        jTextArea5.setTabSize(1);
        jTextArea5.setText("\t\n   14:00\n\t16:00");
        jTextArea5.setBorder(null);
        jTextArea5.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextArea5.setEnabled(false);
        jTextArea5.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane28.setViewportView(jTextArea5);

        jTextAreaV7.setColumns(1);
        jTextAreaV7.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaV7.setRows(2);
        jTextAreaV7.setTabSize(2);
        jTextAreaV7.setBorder(null);
        jTextAreaV7.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane32.setViewportView(jTextAreaV7);

        jTextAreaM7.setColumns(1);
        jTextAreaM7.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaM7.setRows(2);
        jTextAreaM7.setTabSize(2);
        jTextAreaM7.setBorder(null);
        jTextAreaM7.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane33.setViewportView(jTextAreaM7);

        jTextAreaK7.setColumns(1);
        jTextAreaK7.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaK7.setRows(2);
        jTextAreaK7.setTabSize(2);
        jTextAreaK7.setBorder(null);
        jTextAreaK7.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane40.setViewportView(jTextAreaK7);

        jTextAreaL7.setColumns(1);
        jTextAreaL7.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaL7.setRows(2);
        jTextAreaL7.setTabSize(2);
        jTextAreaL7.setBorder(null);
        jTextAreaL7.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane41.setViewportView(jTextAreaL7);

        jTextAreaJ7.setColumns(1);
        jTextAreaJ7.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaJ7.setRows(2);
        jTextAreaJ7.setTabSize(2);
        jTextAreaJ7.setBorder(null);
        jTextAreaJ7.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane42.setViewportView(jTextAreaJ7);

        jTextAreaV9.setColumns(1);
        jTextAreaV9.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaV9.setRows(2);
        jTextAreaV9.setTabSize(2);
        jTextAreaV9.setBorder(null);
        jTextAreaV9.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane34.setViewportView(jTextAreaV9);

        jTextAreaM9.setColumns(1);
        jTextAreaM9.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaM9.setRows(2);
        jTextAreaM9.setTabSize(2);
        jTextAreaM9.setBorder(null);
        jTextAreaM9.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane35.setViewportView(jTextAreaM9);

        jTextAreaK9.setColumns(1);
        jTextAreaK9.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaK9.setRows(2);
        jTextAreaK9.setTabSize(2);
        jTextAreaK9.setBorder(null);
        jTextAreaK9.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane43.setViewportView(jTextAreaK9);

        jTextAreaL9.setColumns(1);
        jTextAreaL9.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaL9.setRows(2);
        jTextAreaL9.setTabSize(2);
        jTextAreaL9.setBorder(null);
        jTextAreaL9.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane44.setViewportView(jTextAreaL9);

        jTextAreaJ9.setColumns(1);
        jTextAreaJ9.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaJ9.setRows(2);
        jTextAreaJ9.setTabSize(2);
        jTextAreaJ9.setBorder(null);
        jTextAreaJ9.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane45.setViewportView(jTextAreaJ9);

        jTextAreaV12.setColumns(1);
        jTextAreaV12.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaV12.setRows(2);
        jTextAreaV12.setTabSize(2);
        jTextAreaV12.setBorder(null);
        jTextAreaV12.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane36.setViewportView(jTextAreaV12);

        jTextAreaM12.setColumns(1);
        jTextAreaM12.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaM12.setRows(2);
        jTextAreaM12.setTabSize(2);
        jTextAreaM12.setBorder(null);
        jTextAreaM12.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane37.setViewportView(jTextAreaM12);

        jTextAreaK12.setColumns(1);
        jTextAreaK12.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaK12.setRows(2);
        jTextAreaK12.setTabSize(2);
        jTextAreaK12.setBorder(null);
        jTextAreaK12.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane46.setViewportView(jTextAreaK12);

        jTextAreaL12.setColumns(1);
        jTextAreaL12.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaL12.setRows(2);
        jTextAreaL12.setTabSize(2);
        jTextAreaL12.setBorder(null);
        jTextAreaL12.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane47.setViewportView(jTextAreaL12);

        jTextAreaJ12.setColumns(1);
        jTextAreaJ12.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaJ12.setRows(2);
        jTextAreaJ12.setTabSize(2);
        jTextAreaJ12.setBorder(null);
        jTextAreaJ12.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane48.setViewportView(jTextAreaJ12);

        jTextAreaV14.setColumns(1);
        jTextAreaV14.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaV14.setRows(2);
        jTextAreaV14.setTabSize(2);
        jTextAreaV14.setBorder(null);
        jTextAreaV14.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane38.setViewportView(jTextAreaV14);

        jTextAreaM14.setColumns(1);
        jTextAreaM14.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaM14.setRows(2);
        jTextAreaM14.setTabSize(2);
        jTextAreaM14.setBorder(null);
        jTextAreaM14.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane39.setViewportView(jTextAreaM14);

        jTextAreaK14.setColumns(1);
        jTextAreaK14.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaK14.setRows(2);
        jTextAreaK14.setTabSize(2);
        jTextAreaK14.setBorder(null);
        jTextAreaK14.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane49.setViewportView(jTextAreaK14);

        jTextAreaL14.setColumns(1);
        jTextAreaL14.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaL14.setRows(2);
        jTextAreaL14.setTabSize(2);
        jTextAreaL14.setBorder(null);
        jTextAreaL14.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane50.setViewportView(jTextAreaL14);

        jTextAreaJ14.setColumns(1);
        jTextAreaJ14.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaJ14.setRows(2);
        jTextAreaJ14.setTabSize(2);
        jTextAreaJ14.setBorder(null);
        jTextAreaJ14.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane51.setViewportView(jTextAreaJ14);

        jListProfesores.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jListProfesoresItemStateChanged(evt);
            }
        });
        jListProfesores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jListProfesoresActionPerformed(evt);
            }
        });

        jListAsignaturas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jListAsignaturasItemStateChanged(evt);
            }
        });
        jListAsignaturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jListAsignaturasActionPerformed(evt);
            }
        });

        jTextAreaV15.setColumns(1);
        jTextAreaV15.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaV15.setRows(2);
        jTextAreaV15.setTabSize(2);
        jTextAreaV15.setBorder(null);
        jTextAreaV15.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane52.setViewportView(jTextAreaV15);

        jTextAreaM15.setColumns(1);
        jTextAreaM15.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaM15.setRows(2);
        jTextAreaM15.setTabSize(2);
        jTextAreaM15.setBorder(null);
        jTextAreaM15.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane53.setViewportView(jTextAreaM15);

        jTextAreaK15.setColumns(1);
        jTextAreaK15.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaK15.setRows(2);
        jTextAreaK15.setTabSize(2);
        jTextAreaK15.setBorder(null);
        jTextAreaK15.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane54.setViewportView(jTextAreaK15);

        jTextAreaL15.setColumns(1);
        jTextAreaL15.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaL15.setRows(2);
        jTextAreaL15.setTabSize(2);
        jTextAreaL15.setBorder(null);
        jTextAreaL15.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane55.setViewportView(jTextAreaL15);

        jTextAreaJ15.setColumns(1);
        jTextAreaJ15.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaJ15.setRows(2);
        jTextAreaJ15.setTabSize(2);
        jTextAreaJ15.setBorder(null);
        jTextAreaJ15.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane56.setViewportView(jTextAreaJ15);

        jTextArea6.setEditable(false);
        jTextArea6.setColumns(1);
        jTextArea6.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextArea6.setRows(2);
        jTextArea6.setTabSize(1);
        jTextArea6.setText("\t\n   16:00\n\t18:00");
        jTextArea6.setBorder(null);
        jTextArea6.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextArea6.setEnabled(false);
        jTextArea6.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane29.setViewportView(jTextArea6);

        jTextAreaV17.setColumns(1);
        jTextAreaV17.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaV17.setRows(2);
        jTextAreaV17.setTabSize(2);
        jTextAreaV17.setBorder(null);
        jTextAreaV17.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane62.setViewportView(jTextAreaV17);

        jTextAreaM17.setColumns(1);
        jTextAreaM17.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaM17.setRows(2);
        jTextAreaM17.setTabSize(2);
        jTextAreaM17.setBorder(null);
        jTextAreaM17.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane63.setViewportView(jTextAreaM17);

        jTextAreaK17.setColumns(1);
        jTextAreaK17.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaK17.setRows(2);
        jTextAreaK17.setTabSize(2);
        jTextAreaK17.setBorder(null);
        jTextAreaK17.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane64.setViewportView(jTextAreaK17);

        jTextAreaL17.setColumns(1);
        jTextAreaL17.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaL17.setRows(2);
        jTextAreaL17.setTabSize(2);
        jTextAreaL17.setBorder(null);
        jTextAreaL17.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane65.setViewportView(jTextAreaL17);

        jTextAreaJ17.setColumns(1);
        jTextAreaJ17.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextAreaJ17.setRows(2);
        jTextAreaJ17.setTabSize(2);
        jTextAreaJ17.setBorder(null);
        jTextAreaJ17.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane66.setViewportView(jTextAreaJ17);

        jTextArea8.setEditable(false);
        jTextArea8.setColumns(1);
        jTextArea8.setFont(new java.awt.Font("Meiryo", 0, 10)); // NOI18N
        jTextArea8.setRows(2);
        jTextArea8.setTabSize(1);
        jTextArea8.setText("\t\n   18:00\n\t20:00");
        jTextArea8.setBorder(null);
        jTextArea8.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextArea8.setEnabled(false);
        jTextArea8.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane31.setViewportView(jTextArea8);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Seleccione un profesor");

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Seleccione una Asignatura");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane44, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane43, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane35, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane45, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane34, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane47, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane46, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane37, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane48, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane36, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane28, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane50, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane49, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane39, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane51, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane38, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane29, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane55, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane54, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane53, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane56, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane52, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane31, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane65, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane64, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane63, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane66, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane62, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jScrollPane41, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jScrollPane40, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jScrollPane33, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jScrollPane42, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jScrollPane32, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jListProfesores, 0, 180, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jListAsignaturas, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(196, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane41, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane40, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane33, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane42, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane32, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jListAsignaturas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jListProfesores, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane44, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane43, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane35, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane45, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane34, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane47, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane46, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane37, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane48, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane36, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane28, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane50, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane49, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane39, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane51, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane38, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane29, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane55, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane54, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane53, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane56, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane52, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane31, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane65, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane64, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane63, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane66, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane62, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(191, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jListProfesoresItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jListProfesoresItemStateChanged
        //
    }//GEN-LAST:event_jListProfesoresItemStateChanged

    private void jListProfesoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jListProfesoresActionPerformed
        
    }//GEN-LAST:event_jListProfesoresActionPerformed

    private void jListAsignaturasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jListAsignaturasItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jListAsignaturasItemStateChanged

    private void jListAsignaturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jListAsignaturasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jListAsignaturasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JComboBox<String> jListAsignaturas;
    public javax.swing.JComboBox<String> jListProfesores;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane28;
    private javax.swing.JScrollPane jScrollPane29;
    private javax.swing.JScrollPane jScrollPane30;
    private javax.swing.JScrollPane jScrollPane31;
    private javax.swing.JScrollPane jScrollPane32;
    private javax.swing.JScrollPane jScrollPane33;
    private javax.swing.JScrollPane jScrollPane34;
    private javax.swing.JScrollPane jScrollPane35;
    private javax.swing.JScrollPane jScrollPane36;
    private javax.swing.JScrollPane jScrollPane37;
    private javax.swing.JScrollPane jScrollPane38;
    private javax.swing.JScrollPane jScrollPane39;
    private javax.swing.JScrollPane jScrollPane40;
    private javax.swing.JScrollPane jScrollPane41;
    private javax.swing.JScrollPane jScrollPane42;
    private javax.swing.JScrollPane jScrollPane43;
    private javax.swing.JScrollPane jScrollPane44;
    private javax.swing.JScrollPane jScrollPane45;
    private javax.swing.JScrollPane jScrollPane46;
    private javax.swing.JScrollPane jScrollPane47;
    private javax.swing.JScrollPane jScrollPane48;
    private javax.swing.JScrollPane jScrollPane49;
    private javax.swing.JScrollPane jScrollPane50;
    private javax.swing.JScrollPane jScrollPane51;
    private javax.swing.JScrollPane jScrollPane52;
    private javax.swing.JScrollPane jScrollPane53;
    private javax.swing.JScrollPane jScrollPane54;
    private javax.swing.JScrollPane jScrollPane55;
    private javax.swing.JScrollPane jScrollPane56;
    private javax.swing.JScrollPane jScrollPane57;
    private javax.swing.JScrollPane jScrollPane58;
    private javax.swing.JScrollPane jScrollPane59;
    private javax.swing.JScrollPane jScrollPane60;
    private javax.swing.JScrollPane jScrollPane61;
    private javax.swing.JScrollPane jScrollPane62;
    private javax.swing.JScrollPane jScrollPane63;
    private javax.swing.JScrollPane jScrollPane64;
    private javax.swing.JScrollPane jScrollPane65;
    private javax.swing.JScrollPane jScrollPane66;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextArea jTextArea6;
    private javax.swing.JTextArea jTextArea7;
    private javax.swing.JTextArea jTextArea8;
    private javax.swing.JTextArea jTextAreaJ12;
    private javax.swing.JTextArea jTextAreaJ14;
    private javax.swing.JTextArea jTextAreaJ15;
    private javax.swing.JTextArea jTextAreaJ16;
    private javax.swing.JTextArea jTextAreaJ17;
    private javax.swing.JTextArea jTextAreaJ7;
    private javax.swing.JTextArea jTextAreaJ9;
    private javax.swing.JTextArea jTextAreaK12;
    private javax.swing.JTextArea jTextAreaK14;
    private javax.swing.JTextArea jTextAreaK15;
    private javax.swing.JTextArea jTextAreaK16;
    private javax.swing.JTextArea jTextAreaK17;
    private javax.swing.JTextArea jTextAreaK7;
    private javax.swing.JTextArea jTextAreaK9;
    private javax.swing.JTextArea jTextAreaL12;
    private javax.swing.JTextArea jTextAreaL14;
    private javax.swing.JTextArea jTextAreaL15;
    private javax.swing.JTextArea jTextAreaL16;
    private javax.swing.JTextArea jTextAreaL17;
    private javax.swing.JTextArea jTextAreaL7;
    private javax.swing.JTextArea jTextAreaL9;
    private javax.swing.JTextArea jTextAreaM12;
    private javax.swing.JTextArea jTextAreaM14;
    private javax.swing.JTextArea jTextAreaM15;
    private javax.swing.JTextArea jTextAreaM16;
    private javax.swing.JTextArea jTextAreaM17;
    private javax.swing.JTextArea jTextAreaM7;
    private javax.swing.JTextArea jTextAreaM9;
    private javax.swing.JTextArea jTextAreaV12;
    private javax.swing.JTextArea jTextAreaV14;
    private javax.swing.JTextArea jTextAreaV15;
    private javax.swing.JTextArea jTextAreaV16;
    private javax.swing.JTextArea jTextAreaV17;
    private javax.swing.JTextArea jTextAreaV7;
    private javax.swing.JTextArea jTextAreaV9;
    // End of variables declaration//GEN-END:variables

    
}
