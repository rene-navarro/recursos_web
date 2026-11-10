package mx.unison.urlconnection;

import java.io.*;
import java.net.*;
import java.util.logging.Logger;


// https://identidadbuho.unison.mx/wp-content/uploads/2019/08/BOX5.jpg
public class Curl {

    public static final String TAG = Curl.class.getSimpleName();

    public static final Logger LOG = Logger.getLogger(TAG);

    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Argumentos incorrectos");
            System.exit(1);
        }

        URL url = null;
        try {
            URI uri = new URI(args[0]);
            url = uri.toURL();
        } catch (URISyntaxException | MalformedURLException e) {
            LOG.severe(e.getMessage());
            System.exit(2);
        }

        URLConnection connection = null;
        String tipoContenido = null;
        try {
            connection = url.openConnection();
            tipoContenido = connection.getContentType();
        } catch (IOException e) {
            LOG.severe(e.getMessage());
            System.exit(3);
        }

        if (tipoContenido.startsWith("text")) {
            descargaTexto(connection);
        } else {
            descargaBytes(connection);
        }

    }

    private static void descargaBytes(URLConnection connection) {
        URL u = connection.getURL();

        String path = u.getPath();

        String fileName = "output.bin";

        if( path != null ) {
            fileName = path.substring(path.lastIndexOf('/') + 1);
        }

        BufferedOutputStream localFile = null;

        try {
            localFile = new BufferedOutputStream(new FileOutputStream(fileName));
        } catch (FileNotFoundException ex) {
            LOG.severe(ex.getMessage());
        }

        BufferedInputStream fromServer=null;

        try {
            fromServer = new BufferedInputStream( connection.getInputStream() );
        } catch (IOException ex) {
            LOG.severe(ex.getMessage());
        }
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
        }

        PrintWriter localFile = null;

        try {
            localFile = new PrintWriter(fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        BufferedReader fromServer = null;
        try {
            fromServer = new BufferedReader(
                    new InputStreamReader( connection.getInputStream() ));
        } catch (IOException ex) {
            LOG.severe(ex.getMessage());
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
