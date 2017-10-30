import java.util.*;


/**
 * Created by cristhianjbd on 28/10/17.
 */
public class ASP {

    private Integer observaciones = 0;
    private List<String> terminales;
    private String entrada;

    public void algoritmo(String input,  List<List<String>> tabla){

        this.entrada = input;
        this.terminales = tabla.get(0);

        List<String> output = new ArrayList<String>();
        Map<String, String> treeMap = new TreeMap<String, String>();
        Stack<String> pila = new Stack<String>();
        pila.push("$");
        input = input + "$";

        //Se guarda en un hash los valores de la tabla, donde la clave seria un " noTerminal' -> 'terminal'
        for(int i=0 ; i < tabla.size()-1 ; i++) {
            for (int j = 0 ; j < tabla.get(0).size()-1 ; j++) {
                treeMap.put(tabla.get(i+1).get(0).toString()+ "->" + tabla.get(0).get(j+1).toString(), tabla.get(i+1).get(j+1).toString());
                // System.out.println("Clave: " + tabla.get(i+1).get(0).toString()+ "->" + tabla.get(0).get(j+1).toString() + "   Valor: " +  tabla.get(i+1).get(j+1).toString());
            }
            // System.out.println();
        }

        pila.push(tabla.get(1).get(0).toString());   // Se guarda el simbolo inicial de la gramatica

        String X=pila.peek();                           // se guarda en X el tope de la pila
        String a=null, key=null, nextOutput=null;

        while(pila.peek().compareTo("$") !=0){

            a = input.substring(0, 1);  // guarda el primer caracter del input
            key = X + "->" + a;     // Se concatena lo que se quita de la pila con el primer caracater del input, y se guarda como clave

            if(X.compareTo(a)==0) {             // compara si el valor que esta en el tope de la pila es igual al primer caracter del input
                pila.pop();
                input = input.substring(1);      //Se quita el primer caracter del input
                output.add("-");
            }
            else if(isTerminal(X)){
                this.observaciones++;
                input = input.substring(1);
            }
            else if(treeMap.get(key).compareTo("-")==0  || treeMap.get(key).compareTo("x")==0){
                if(treeMap.get(key).compareTo("-")==0){
                    this.observaciones++;
                    input = input.substring(1);
                }else if(treeMap.get(key).compareTo("x")==0){
                    pila.pop();
                    this.observaciones++;
                }
            }
            else if(treeMap.get(key)!= null && treeMap.get(key)!= "-") {

                nextOutput = treeMap.get(key);                  //Se busca en el hash lo que se creo anteriormente como clave
                output.add(nextOutput);                         // Se guarda en la lista de output
                pila.pop();                                     //Se quita un elemento de la pila

                for (int i = nextOutput.length() ; i > nextOutput.lastIndexOf(">")+1; i--) {
                    if(nextOutput.substring(i-1, i).compareTo("Ɛ")!=0
                            && nextOutput.substring(i-1, i).compareTo("x")!=0)              //Si es  Ɛ no mete en la pila
                    pila.push(nextOutput.substring(i-1, i));                                     //meter en la pila los siguientes
                }
            }
            X = pila.peek();  //guardar en X el tope de la pila
        }
        System.out.println();
        System.out.println("Salida: ");
        if(this.observaciones == 0){
            for(String o : output)
                System.out.println(o);
        }else{
            System.out.println("Error sintactico en " + this.entrada );
            System.out.println("Número de errores " +  this.observaciones);
            for(String o : output)
                System.out.println(o);
        }
    }

    private Boolean isTerminal(String str){
        return terminales.contains(str);
    }
}
