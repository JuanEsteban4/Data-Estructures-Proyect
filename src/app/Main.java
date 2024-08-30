package app;
import controlador.ControladorMain;

public class Main {
    
    public static void main(String[] args){
        new ControladorMain().init();
    }
    
    /*
    public void app(){
        int opc;
        do{
            menu();
            opc = leer.nextInt();
            switch(opc){
                case 1 -> recordatorios();
                case 2 -> asignaturas();
                    
            }
        }while(opc!=9);
    }
    
    public void recordatorios(){
        leer = new Scanner(System.in);
        int opc;
        do{
            menuR();
            opc = leer.nextInt();
            switch(opc){
                case 1 -> addR();
                case 2 -> viewR();
                case 3 -> checkR();
                    
            }
        }while(opc!=4);
        
    }
    
    public void addR(){
        leer = new Scanner(System.in);
        String titulo,desc,fecha;
        System.out.print("Ingrese el titulo: ");
        titulo = leer.nextLine();
        System.out.print("Ingrese la descripcion: ");
        desc = leer.nextLine();
        System.out.print("Ingrese la fecha (aaaa/mm/dd): ");
        fecha = leer.nextLine();
        
        //RA.addRecordatorio(new Recordatorio(titulo,desc,fecha));
    }
    
    public void viewR(){
        RA.printRecordatorios();
    }
    
    public void checkR(){
        int cod;
        System.out.println("Ingreso el indice del recordatorio");
        viewR();
        leer = new Scanner(System.in);
        cod = leer.nextInt();
               
        RA.checkRecordatorio(RA.getListado().get(cod));
    }
    
    public void asignaturas(){
        leer = new Scanner(System.in);
        int opc;
        do{
            menuA();
            opc = leer.nextInt();
            switch(opc){
                case 1 -> addA();
                case 2 -> viewA();
                case 3 -> findA();    
                case 4 -> deleteA();
            }
        }while(opc!=5);
        
    }
    
    public void addA(){
        leer = new Scanner(System.in);
        String nombre,cod,profesor;
        int creditos;
        System.out.print("Ingrese el nombre de la asignatura: ");
        nombre = leer.nextLine();
        System.out.print("Ingrese el codigo de la asignatura: ");
        cod = leer.nextLine();
        System.out.print("Ingrese el nombre del profesor: ");
        profesor = leer.nextLine();
        System.out.print("Ingrese el numero de creditos: ");
        creditos = leer.nextInt();
        
        AA.agregarAsignatura(new Asignatura(nombre,cod,profesor,creditos));
    }
    
    public void viewA(){
        AA.imprimirAsignaturas();
    }
    
    public void findA(){
        leer = new Scanner(System.in);
        String cod;
        System.out.println("Ingrese el codigo de la asignatura");
        cod = leer.nextLine();
        Asignatura buscada = AA.buscarAsignaturaPorCodigo(cod);
        if(buscada !=null){
            System.out.println(buscada);
        }else{
            System.out.println("Asignatura no encontrada");
        }
    }
    
    public void deleteA(){
        leer = new Scanner(System.in);
        String cod;
        System.out.println("Ingrese el codigo de la asignatura a ELIMINAR");
        cod = leer.nextLine();
        boolean si = AA.eliminarAsignatura(cod);
        if(si){
            System.out.println("Asignatura eliminada");
        }else{
            System.out.println("Asingtura no resgistrada");
        }
    }
    
    public static void menu(){
        System.out.println("""
                           
                           Menu
                           (1) Rercordatorios
                           (2) Asignaturas
                           (9) Salir
                           """);
    }
    
    public static void menuR(){
        System.out.println("""
                           
                           Recordatorios
                           (1) Agregar recordatorio
                           (2) Ver recordatorios
                           (3) Marcar recordatorio
                           (4) volver                        
                           """);
    }
    
    public static void menuA(){
        System.out.println("""
                           
                           Asignaturas
                           (1) Agregar asignatura
                           (2) Ver Asignaturas
                           (3) Buscar asignatura
                           (4) Eliminar asignatura
                           (5) volver                        
                           """);
    }
    
    */
    
}