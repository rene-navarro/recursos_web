package mx.unison.toc;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import java.util.logging.Logger;

public class ManejadorEtiquetas extends HTMLEditorKit.ParserCallback {

    public static final String TAG = ManejadorEtiquetas.class.getSimpleName();
    public static final Logger LOG = Logger.getLogger(TAG);


    HTML.Tag lastTag;

    public ManejadorEtiquetas() {
        super();
    }

    @Override
    public void handleStartTag(HTML.Tag tag, MutableAttributeSet attributeSet, int pos) {
        // Solo interesa el contenido de las etiquetas H1, H2, H3, H4 y TITLE
        if (tag == HTML.Tag.H1) {
            lastTag = tag;
        }
        if (tag == HTML.Tag.H2) {
            lastTag = tag;
        }
        if (tag == HTML.Tag.H3) {
            lastTag = tag;
        }
        if (tag == HTML.Tag.H4) {
            lastTag = tag;
        }

        if (tag == HTML.Tag.TITLE) {
            lastTag = tag;
        }
    }

    @Override
    public void handleText(char[] data, int pos) {
        String contenidoEtiqueta = new String(data);

        if (lastTag == HTML.Tag.TITLE) {
            contenidoEtiqueta = contenidoEtiqueta.trim().toUpperCase();
            System.out.println(contenidoEtiqueta);
        }
        if (lastTag == HTML.Tag.H1) {
            System.out.println(contenidoEtiqueta);
        }
        if (lastTag == HTML.Tag.H2) {
            System.out.printf("\t%s\n", contenidoEtiqueta);
        }
        if (lastTag == HTML.Tag.H3) {
            System.out.printf("\t\t%s\n", contenidoEtiqueta);
        }
        if (lastTag == HTML.Tag.H4) {
            System.out.printf("\t\t\t%s\n", contenidoEtiqueta);
        }

        // Resetear la última etiqueta procesada
        lastTag = null;

    }
}
