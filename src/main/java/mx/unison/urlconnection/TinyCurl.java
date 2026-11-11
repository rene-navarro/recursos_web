package mx.unison.urlconnection;

import java.io.*;
import java.net.*;
import java.util.logging.Logger;


// https://identidadbuho.unison.mx/wp-content/uploads/2019/08/BOX5.jpg
public class TinyCurl {

    public static final String TAG = TinyCurl.class.getSimpleName();
    public static final Logger LOG = Logger.getLogger(TAG);

    /**
     * Implementación de un cliente HTTP muy simple, que descarga un recurso web y lo guarda en un archivo local.
     * @param args
     */
    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Argumentos incorrectos");
            System.err.println("Uso: java TinyCurl <URL>");
            System.exit(1);
        }

        URL url = null;
        try {
            // verificar que la URL es válida
            URI uri = new URI(args[0]);
            url = uri.toURL();
        } catch (URISyntaxException | MalformedURLException e) {
            LOG.severe(e.getMessage());
            System.exit(2);
        }

        // Abrir una conexión con el recurso web
        URLConnection clientConnection = null;
        String tipoContenido = null;

        try {
            // aquí se realiza la conexión con el recurso web
            clientConnection = url.openConnection();

            // obtener el tipo de contenido del recurso web, como referencia consutar:
            // https://developer.mozilla.org/en-US/docs/Web/HTTP/Guides/MIME_types
            tipoContenido = clientConnection.getContentType();
        } catch (IOException e) {
            LOG.severe(e.getMessage());
            System.exit(3);
        }

        if(tipoContenido == null) {
            LOG.severe("No se pudo determinar el tipo de contenido del recurso web");
            System.exit(4);
        }

        if( tipoContenido.equals("application/json")  ) {
            descargaTexto(clientConnection);
        } else if( tipoContenido.equals("application/xml")  ) {
            descargaTexto(clientConnection);
        } else if ( tipoContenido.startsWith("text") ) {
            descargaTexto(clientConnection);
        } else if ( tipoContenido.startsWith("image") ) {
            descargaBytes(clientConnection);
        } else {
            descargaBytes(clientConnection);
        }

    }

    private static void descargaBytes(URLConnection connection) {
        URL u = connection.getURL();

        String path = u.getPath();

        String fileName = "output.bin";

        if( path != null ) {
            // Extraer el nombre del archivo del path de la URL
            fileName = path.substring(path.lastIndexOf('/') + 1);
            if( fileName == null  || fileName.equals("") ) {
                fileName = "output.bin";
            }
        }

        BufferedOutputStream localFile = null;

        try {
            // Crear un flujo de salida para escribir los bytes descargados en un archivo local
            localFile = new BufferedOutputStream(new FileOutputStream(fileName));
        } catch (FileNotFoundException ex) {
            LOG.severe(ex.getMessage());
        }

        BufferedInputStream fromServer = null;

        try {
            // Crear un flujo de entrada para leer los bytes del recurso web
            fromServer = new BufferedInputStream( connection.getInputStream() );
        } catch (IOException ex) {
            LOG.severe(ex.getMessage());
        }

        // Crear un buffer para leer los bytes en bloques
        byte[] buffer = new byte[20];
        int bytesRead;

        try {
            while (( bytesRead = fromServer.read(buffer, 0, buffer.length)) != -1 ) {
                localFile.write(buffer,0, bytesRead);
                localFile.flush();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fromServer.close();
            localFile.close();
        } catch (IOException ex) {
            LOG.severe(ex.getMessage());
        }
    }

    private static void descargaTexto(URLConnection connection) {
        URL u = connection.getURL();

        String path = u.getPath();

        String fileName = "output.txt";

        if( path != null ) {
            fileName = path.substring(path.lastIndexOf('/') + 1);

            if( fileName == null  || fileName.equals("") ) {
                fileName = "output.txt";
            }
        }

        PrintWriter localFile = null;

        try {
            localFile = new PrintWriter(fileName);
        } catch (FileNotFoundException e) {
           LOG.severe(e.getMessage());
            System.exit(2);
        }

        BufferedReader fromServer = null;
        try {
            fromServer = new BufferedReader(
                    new InputStreamReader( connection.getInputStream() ));
        } catch (IOException ex) {
            LOG.severe(ex.getMessage());
            System.exit(2);
        }

        String inputLine = null;

        try {
            while ((inputLine = fromServer.readLine()) != null) {
                System.out.println(inputLine);
                localFile.println(inputLine);
            }
        } catch (IOException ex) {
            LOG.severe(ex.getMessage());
        }
        try {
            fromServer.close();
            localFile.close();
        } catch (IOException ex) {
            LOG.severe(ex.getMessage());
        }

    }
}
