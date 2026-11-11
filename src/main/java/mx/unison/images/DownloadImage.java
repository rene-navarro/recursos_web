package mx.unison.images;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URI;
import java.util.logging.Logger;

public class DownloadImage {

    public static final String TAG = DownloadImage.class.getSimpleName();

    public static final Logger LOG = Logger.getLogger(TAG);

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Argumentos incorrectos");
            System.exit(1);
        }

        String urlString = args[0];

        if (!validateURL(urlString)) {
            System.err.println("URL no valida");
            System.exit(2);
        }


        ImageIcon icon = loadIconFromUrl(urlString);

        if (icon != null) {
            // Mostrar la imagen en un JFrame
            JFrame frame = new JFrame("Imagen descargada");
            JLabel label = new JLabel(icon);
            frame.getContentPane().add(label);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setResizable(false);
        } else {
            System.err.println("No se pudo cargar la imagen desde la URL proporcionada.");
            System.exit(3);
        }
    }

    public static boolean validateURL(String urlString) {
        boolean valid = false;
        try {
            URL url = URI.create(urlString).toURL();
            valid = true;
        } catch (MalformedURLException e) {
            LOG.severe( e.getMessage() );
        }
        return valid;
    }

    public static ImageIcon loadIconFromUrl(String urlString) {
        ImageIcon image = null;
        try {
            URL url = URI.create(urlString).toURL();
            BufferedImage imageBytes = ImageIO.read(url);

            if ( imageBytes != null ) {
                image =  new ImageIcon(imageBytes);
            } else {
                LOG.severe( "Error en formato de la imagen o respuesta vacia"  );
            }
        } catch (Exception e) {
            LOG.severe( "Error al cargar la imagen: " + e.getMessage() );
        }
        return image;
    }
}
