package mx.unison.toc;


import javax.swing.text.html.HTMLEditorKit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Logger;

public class TOCGenerator {
    public static final String TAG = TOCGenerator.class.getSimpleName();

    public static final Logger LOG = Logger.getLogger(TAG);
     // https://docs.oracle.com/javase/8/docs/api/java/net/URL.html
    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Argumentos incorrectos");
            System.exit(1);
        }

        URL recursoWeb = null;
        try {
            URI uri = new URI(args[0]);
            recursoWeb = uri.toURL();
        } catch (URISyntaxException | MalformedURLException e) {
            LOG.severe(e.getMessage());
            System.exit(2);
        }

        // Crear un flujo para leer datos del URL
        BufferedReader htmlReader = null;
        try {
            htmlReader = new BufferedReader(
                    new InputStreamReader( recursoWeb.openStream() ));
        } catch (IOException ex) {
            LOG.severe(ex.getMessage());
            System.exit(2);
        }

        HTMLParser htmlParser  = new HTMLParser();
        HTMLEditorKit.Parser procesador = htmlParser.getParser();

        ManejadorEtiquetas manejador = new ManejadorEtiquetas();

        try {
            // procesador.parse( fileReader, new ManejadorEtiquetas(), true);
            procesador.parse( htmlReader, manejador, true);
        } catch (IOException e) {
            LOG.severe("No se puede leer documento HTML");
            System.exit(2);
        }
    }
}
