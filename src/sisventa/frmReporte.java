package sisventa;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.jdi.connect.spi.Connection;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import logicanegocio.Usuarios;
import logicanegocio.usdb;

public class frmReporte extends javax.swing.JFrame {

    private final String url = "jdbc:mysql://localhost:3306/VENTAS";
    private final String usuarioDB = "root";
    private final String contraseñaDB = "";

    public frmReporte() {

        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtTotal = new javax.swing.JSpinner();
        btnReporte = new Button.Button();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtTotal.setValue(100);

        btnReporte.setText("Generar reporte");
        btnReporte.setShadowColor(new java.awt.Color(0, 102, 204));
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });

        jLabel1.setText("Ingrese un valor que necesita ver");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtTotal)
                        .addComponent(btnReporte, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)))
                .addContainerGap(104, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
        // TODO add your handling code here:
        Usuarios usuarios = new Usuarios();

        generarReportePDF(usuarios.obtenerUsuarios(), "reporteusuarios.pdf");
    }//GEN-LAST:event_btnReporteActionPerformed

    public void generarReportePDF(List<usdb> usuarios, String rutaArchivo) {

        try {
            java.sql.Connection con = DriverManager.getConnection(url, usuarioDB, contraseñaDB);

            String sql = "SELECT ID, CODBARRA, PRODUCTO, PRECIO, EXISTENCIA, LINEA, FECHA FROM PRODUCTOS ";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            // Nombre del archivo PDF de salida
            String archivoSalida = "reporte_productos.pdf";

            Document document = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(document, new FileOutputStream(archivoSalida));
            document.open();

            // Agrega el título
            document.add(new Paragraph("Reporte de Productos"));
           

            // Agrega espacio en blanco
            document.add(new Paragraph(" ")); // Un párrafo en blanco como espacio

            // Crea una tabla para mostrar los datos
            PdfPTable table = new PdfPTable(7); // 7 columnas para los campos seleccionados
            // Agrega encabezados de tabla
            table.addCell("ID");
            table.addCell("Código de Barras");
            table.addCell("Producto");
            table.addCell("Precio");
            table.addCell("Existencia");
            table.addCell("Línea");
            table.addCell("Fecha");

            // Agrega datos de la consulta a la tabla
            while (resultSet.next()) {
                table.addCell(resultSet.getString("ID"));
                table.addCell(resultSet.getString("CODBARRA"));
                table.addCell(resultSet.getString("PRODUCTO"));
                table.addCell(resultSet.getString("PRECIO"));
                table.addCell(resultSet.getString("EXISTENCIA"));
                table.addCell(resultSet.getString("LINEA"));
                table.addCell(resultSet.getString("FECHA"));
            }

            document.add(table);
            document.close();
            System.out.println("El informe se ha generado correctamente en " + archivoSalida);

            // Cierra la conexión a la base de datos
            resultSet.close();
            statement.close();
            abrirPDFEnPantalla("reporte_productos.pdf");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void abrirPDFEnPantalla(String rutaArchivoPDF) {
        try {
            File archivoPDF = new File(rutaArchivoPDF);

            if (archivoPDF.exists() && Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(archivoPDF);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmReporte().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Button.Button btnReporte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner txtTotal;
    // End of variables declaration//GEN-END:variables
}
