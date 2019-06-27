package chargex;

import java.awt.Color;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class VeiculosAndamento extends javax.swing.JFrame {
    
    private int quantidadeMotosViagem;
    private int quantidadeCarrosViagem;
    private int quantidadeVansViagem;
    private int quantidadeCarretasViagem;

    public VeiculosAndamento() {
        initComponents();
        this.setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(73,78,93));
    }
    
    public void apresentarVeiculos() {
        lblMoto.setText(String.valueOf(quantidadeMotosViagem));
        lblCarro.setText(String.valueOf(quantidadeCarrosViagem));
        lblVan.setText(String.valueOf(quantidadeVansViagem));
        lblCarreta.setText(String.valueOf(quantidadeCarretasViagem));
    }    
    
    public void apresentarVeiculosEmViagem() {
        Path arquivo = Paths.get("viagem-andamento.txt");
        try {
            byte[] dadosBytes = Files.readAllBytes(arquivo);
            String dados = new String(dadosBytes);
            String[] arquivoSeparado = dados.split("#");
            System.out.println(Arrays.toString(arquivoSeparado));
            this.quantidadeMotosViagem = Integer.parseInt(arquivoSeparado[0]);
            this.quantidadeCarrosViagem = Integer.parseInt(arquivoSeparado[1]);
            this.quantidadeVansViagem = Integer.parseInt(arquivoSeparado[2]);
            this.quantidadeCarretasViagem = Integer.parseInt(arquivoSeparado[3]);
            apresentarVeiculos();
        }
        catch(Exception erro) {
            System.out.println("Deu ruim!");
        }
    }    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblMoto = new javax.swing.JLabel();
        lblCarro = new javax.swing.JLabel();
        lblVan = new javax.swing.JLabel();
        lblCarreta = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Quantidade de Veiculos com viagens em andamento:");

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chargex/moto.png"))); // NOI18N
        jLabel2.setText("    Motos:");

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chargex/carro.png"))); // NOI18N
        jLabel3.setText("   Carros:");

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chargex/van.png"))); // NOI18N
        jLabel4.setText("      Vans:");

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chargex/caminhao.png"))); // NOI18N
        jLabel5.setText(" Carretas:");

        lblMoto.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        lblMoto.setForeground(new java.awt.Color(255, 255, 255));
        lblMoto.setText("0");

        lblCarro.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        lblCarro.setForeground(new java.awt.Color(255, 255, 255));
        lblCarro.setText("0");

        lblVan.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        lblVan.setForeground(new java.awt.Color(255, 255, 255));
        lblVan.setText("0");

        lblCarreta.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        lblCarreta.setForeground(new java.awt.Color(255, 255, 255));
        lblCarreta.setText("0");

        jButton1.setText("Voltar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addContainerGap(40, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblMoto)
                            .addComponent(lblCarro)
                            .addComponent(lblVan)
                            .addComponent(lblCarreta)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblMoto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblCarro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblVan))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(lblCarreta))
                        .addGap(0, 31, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(VeiculosAndamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VeiculosAndamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VeiculosAndamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VeiculosAndamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VeiculosAndamento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblCarreta;
    private javax.swing.JLabel lblCarro;
    private javax.swing.JLabel lblMoto;
    private javax.swing.JLabel lblVan;
    // End of variables declaration//GEN-END:variables
}
