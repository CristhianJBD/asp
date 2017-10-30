import java.util.List;
import java.util.Scanner;

/**
 * Created by cristhianjbd on 28/10/17.
 */
public class Main {

    public static void main(String[] args)
    {
        String cadenaEntrada = null;
        Scanner sc = new Scanner(System.in);
    /*    System.out.println("Ingrese la cadena de entrada:");
          cadenaEntrada= sc.nextLine();
*/
        Reader r = new Reader();
        List<List<String>> tabla = r.leerTabla();

        ASP asp = new ASP();

      //  cadenaEntrada="i+i*i";
          cadenaEntrada="n*+i*n";

        asp.algoritmo(cadenaEntrada, tabla);
    }

}
