import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cristhianjbd on 26/10/17.
 */
public class Reader {

    public List<List<String>> leerTabla() {

        BufferedReader input = null;
        List<List<String>> tabla = new ArrayList<List<String>>();
        try {
            input = new BufferedReader(new FileReader("src/main/array.txt"));
            String line = null;
            while ((line = input.readLine()) != null) {
                String[] data = line.split(" ");
                tabla.add(Arrays.asList(data));

            }

            Integer i = 0;
            for (List<String> row : tabla) {
                if (i == 0) {
                    for (String s : row) {
                        System.out.print(String.format("%1$-15s", s));
                    }
                    String formato = "%1$-" + row.size() * 15 + "s";
                    System.out.println();
                    System.out.println(String.format(formato, "").replace(" ", "-"));
                } else {
                    for (String s : row) {
                        System.out.print(String.format("%1$-15s", s));
                    }
                    System.out.println();
                }
                i++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return tabla;
    }
}
