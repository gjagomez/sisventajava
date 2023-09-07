/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sisventa;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javaswingdev.menu.EventMenuSelected;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import logicanegocio.Productos;
import logicanegocio.prodInterfaz;
import sisventa.frmUsuarios;

public class frmPrincipal extends javax.swing.JFrame {

    private JTable jTableProductos;

    public frmPrincipal() {
        initComponents();
        this.txtCodBarra.requestFocus();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        menu1.addEvent(new EventMenuSelected() {
            @Override
            public void menuSelected(int index, int indexSubMenu) {
                if (index == 0 && indexSubMenu == 0) {

                    showForm(index, indexSubMenu);
                } else {
                    showForm(index, indexSubMenu);
                }
            }
        });
        menu1.setSelectedIndex(0, 0);

        // Agrega un KeyListener al campo de texto
        txtCodBarra.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // 
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Cuando se libera una tecla (por ejemplo, cuando se ingresa un código de barras),
                // se ejecutará este método.
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // El lector de códigos de barras a menudo envía un "ENTER" después de leer el código.
                    // Puedes agregar código aquí para manejar el código de barras leído.
                    String codigoLeido = txtCodBarra.getText();
                    // Hacer algo con el código de barras, por ejemplo, agregarlo a un grid
                    agregarCodigoAlGrid(codigoLeido);
                    // También puedes borrar el campo de texto si lo deseas
                    txtCodBarra.setText("");
                }
            }
        });
    }

    // Método para agregar el código de barras al grid (debes implementarlo según tu lógica)
    private void agregarCodigoAlGrid(String codigo) {

        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        boolean productoExistente = false;

        for (int i = 0; i < model.getRowCount(); i++) {
            String codbarraTabla = model.getValueAt(i, 0).toString();
            if (codbarraTabla.equals(codigo)) {
                // El producto ya existe en la tabla, actualiza la cantidad
                int cantidadActual = Integer.parseInt(model.getValueAt(i, 3).toString());
                double precio = Double.parseDouble(model.getValueAt(i, 2).toString()); // Suponemos que el precio está en la columna 2
                double nuevoTotal = (cantidadActual + 1) * precio;
                model.setValueAt(cantidadActual + 1, i, 3); // Supongamos que la cantidad está en la columna 3

                model.setValueAt(nuevoTotal, i, 4); // Actualiza el total (suponemos que está en la columna 4)
                productoExistente = true;
                break;
            }
        }

        if (!productoExistente) {
            // Conecta a la base de datos y obtén los productos correspondientes al código de barras
            Productos productos = new Productos(); // Supongamos que tienes una instancia de la clase Productos
            List<prodInterfaz> productosEncontrados = productos.cargarProductos();

            // Busca el producto correspondiente al código de barras
            prodInterfaz productoEncontrado = null;
            for (prodInterfaz producto : productosEncontrados) {
                if (producto.getCodBarra().equals(codigo)) {
                    productoEncontrado = producto;
                    break;
                }
            }

            // Si se encuentra el producto, agrégalo al grid
            if (productoEncontrado != null) {
                model.addRow(new Object[]{
                    productoEncontrado.getCodBarra(),
                    productoEncontrado.getProducto(),
                    productoEncontrado.getPrecio(),
                    1,
                    productoEncontrado.getPrecio()
                });
            
           
            } else {
                JOptionPane.showMessageDialog(this, "El producto con el código de barras " + codigo + " no existe en la base de datos.");
            }

           

        }
        actualizarTotalGeneral();
    }
    
    private void actualizarTotalGeneral() {
    DefaultTableModel model = (DefaultTableModel) table1.getModel();
    double totalGeneral = 0.0;

    for (int i = 0; i < model.getRowCount(); i++) {
        double totalProducto = Double.parseDouble(model.getValueAt(i, 4).toString()); // Suponemos que los totales están en la columna 4
        totalGeneral += totalProducto;
    }

    // Actualiza el valor en el JTextField 'txtTotalGeneral'
    this.txtGranTotal.setText(String.valueOf(totalGeneral));
}

    public void showForm(int Component, int com) {

        int opcion = Component * 10 + com;
        System.out.println(opcion);
        switch (opcion) {
            case 21:
                frmProductos frmProd = new frmProductos();
                frmProd.setVisible(true);
                break;
            case 71:
                frmListUsuarios frmList = new frmListUsuarios();
                frmList.setVisible(true);
                break;
            case 72:
                frmUsuarios frmUs = new frmUsuarios();
                frmUs.setVisible(true);
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu1 = new javaswingdev.menu.Menu();
        jPanel1 = new javax.swing.JPanel();
        card2 = new javaswingdev.card.Card();
        imageAvatar1 = new javaswingdev.avatar.ImageAvatar();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javaswingdev.swing.table.Table();
        txtCodBarra = new textfield2.TextField();
        button1 = new Button.Button();
        txtGranTotal = new textfield2.TextField();
        jLabel1 = new javax.swing.JLabel();
        button2 = new Button.Button();
        button3 = new Button.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        card2.setColor1(new java.awt.Color(51, 102, 255));
        card2.setColor2(new java.awt.Color(0, 102, 255));
        card2.setIcon(javaswingdev.GoogleMaterialDesignIcon.AV_TIMER);

        imageAvatar1.setBorderSize(2);
        imageAvatar1.setBorderSpace(2);
        imageAvatar1.setGradientColor1(new java.awt.Color(0, 102, 204));
        imageAvatar1.setGradientColor2(new java.awt.Color(51, 102, 255));
        imageAvatar1.setImage(new javax.swing.ImageIcon(getClass().getResource("/img/jav.jpeg"))); // NOI18N

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION", "PRECIO", "CANTIDAD", "TOTAL"
            }
        ));
        jScrollPane1.setViewportView(table1);

        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/barcode.png"))); // NOI18N
        button1.setText("Codigo Barra");

        txtGranTotal.setText("0.00");
        txtGranTotal.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel1.setText("TOTAL GENERAL");

        button2.setBackground(new java.awt.Color(255, 153, 153));
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar64.png"))); // NOI18N
        button2.setText("CANCELAR FACTURA");
        button2.setMaximumSize(new java.awt.Dimension(183, 89));
        button2.setMinimumSize(new java.awt.Dimension(183, 89));

        button3.setBackground(new java.awt.Color(0, 102, 204));
        button3.setForeground(new java.awt.Color(255, 255, 255));
        button3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/money-check (1).png"))); // NOI18N
        button3.setText("COBRAR [ F-2 ]");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(255, 255, 255)
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 337, Short.MAX_VALUE)
                .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGranTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodBarra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(903, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodBarra, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGranTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(598, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Button.Button button1;
    private Button.Button button2;
    private Button.Button button3;
    private javaswingdev.card.Card card2;
    private javaswingdev.avatar.ImageAvatar imageAvatar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javaswingdev.menu.Menu menu1;
    private javaswingdev.swing.table.Table table1;
    private textfield2.TextField txtCodBarra;
    private textfield2.TextField txtGranTotal;
    // End of variables declaration//GEN-END:variables
}
