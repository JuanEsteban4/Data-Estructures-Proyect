package controlador;

import functions.AsignaturasActivity;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Asignatura;
import vista.horario.HorarioVista;

public class ControladorHorario implements ActionListener {
    private final HorarioVista vista;
    private final ControladorMain main;
    private final AsignaturasActivity asignaturasActivity;

    public ControladorHorario(ControladorMain main, AsignaturasActivity asignaturasActivity) {
        this.main = main;
        this.vista = new HorarioVista(asignaturasActivity);
        this.asignaturasActivity = asignaturasActivity; // Guardar la referencia
        initComboBox(); // Inicializar el JComboBox
    }

    public void init() {
        main.setMain(vista);
    }

    private void initComboBox() {
        // Aquí se inicializa el JComboBox con las asignaturas actuales
        actualizarComboBox();
    }

    // Método para actualizar el JComboBox
    private void actualizarComboBox() {
        // Limpia el JComboBox
        vista.jListAsignaturas.removeAllItems();
        // Agrega las asignaturas del listado
        for (Asignatura asignatura : asignaturasActivity.getListado()) {
            vista.jListAsignaturas.addItem(asignatura.getNombre()); // Ajusta según el método que uses para obtener el nombre
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("ACCION DETECTADA EN LA VISTA DE HORARIO");
    }

    // Método para agregar una nueva asignatura
    public void agregarAsignatura(Asignatura asignatura) {
        asignaturasActivity.agregarAsignatura(asignatura);
        actualizarComboBox(); // Actualizar el JComboBox después de agregar
    }

    // Método para eliminar una asignatura
    public void eliminarAsignatura(String codigo) {
        asignaturasActivity.eliminarAsignatura(codigo);
        actualizarComboBox(); // Actualizar el JComboBox después de eliminar
    }
}
