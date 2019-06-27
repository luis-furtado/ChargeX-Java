package chargex;

import java.awt.Color;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;

public class Historico extends javax.swing.JFrame {
    
    VeiculosAndamento veiculosAndamento;
    
    private String historico;
    Color vermelhoEscuro = new Color(237,237,237);
    
    public Historico() {
        initComponents();
        lerArquivo();
        criarClasses();
        alterarTexto();
        this.setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(73,78,93));
    }
    
    public void criarClasses() {
        veiculosAndamento = new VeiculosAndamento();
    }
    
    public void lerArquivo() {
        Path arquivo = Paths.get("historico.txt");
            try {
                byte[] historicoBytes = Files.readAllBytes(arquivo);
                historico = new String(historicoBytes);
            }
            catch(Exception erro) {
                System.out.println("Arquivo não encontrado!");
            }
    }
    
    public void alterarTexto() {
       txtHistorico.setEditable(false);
       txtHistorico.setText(historico);
    }
    
    public void atualizarLucroTotal() {
        DecimalFormat fmt = new DecimalFormat("0.00");
        Path arquivo = Paths.get("lucro-total.txt");
                    try {
                        byte[] lucroBytes = Files.readAllBytes(arquivo);
                        String lucroTotal = new String(lucroBytes);
                        double lucroTotalDouble = Double.parseDouble(lucroTotal);
                        lblLucroTotal.setText("R$ "+fmt.format(lucroTotalDouble));
                    }
                    catch(Exception erro) {
                        System.out.println("Deu ruim!");
                    }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtHistorico = new java.awt.TextArea();
        btnVoltar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblLucroTotal = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnZeraHistorico = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtHistorico.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Histórico de viagens ");

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Lucro total da Empresa:");

        lblLucroTotal.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblLucroTotal.setForeground(new java.awt.Color(255, 255, 255));
        lblLucroTotal.setText("0.0");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chargex/historico.png"))); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chargex/cifrao.png"))); // NOI18N

        jButton1.setText("Viagens em Andamento");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Zerar Lucro");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnZeraHistorico.setText("Zerar Histórico");
        btnZeraHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZeraHistoricoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel5)
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addGap(35, 35, 35)
                .addComponent(lblLucroTotal)
                .addGap(85, 85, 85)
                .addComponent(jButton2)
                .addGap(104, 104, 104)
                .addComponent(btnZeraHistorico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 199, Short.MAX_VALUE)
                .addComponent(btnVoltar))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHistorico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(21, 21, 21))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(txtHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnVoltar)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLucroTotal)
                            .addComponent(jButton2)
                            .addComponent(btnZeraHistorico)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        veiculosAndamento.setVisible(true);
        veiculosAndamento.apresentarVeiculosEmViagem();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Path arquivo = Paths.get("lucro-total.txt");
        try {
            String valor = "0.00";
            byte[] valorBytes = valor.getBytes();
            Files.write(arquivo,valorBytes);
            lblLucroTotal.setText("R$"+valor);
        }
        catch(Exception erro) {
            System.out.println("Deu ruim!");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnZeraHistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZeraHistoricoActionPerformed
        Path arquivo = Paths.get("historico.txt");
        try {
            String novoHistorico = "Veiculo\t\t\tTempo\t\t\t\tCusto\t\t\tData Inicio\t\t\t\tData Entrega\n\n";
            byte[] historicoBytes = novoHistorico.getBytes();
            Files.write(arquivo,historicoBytes);
            txtHistorico.setText(novoHistorico);
        }
        catch(Exception erro) {
            System.out.println("Deu ruim!");
        }
    }//GEN-LAST:event_btnZeraHistoricoActionPerformed

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
            java.util.logging.Logger.getLogger(Historico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Historico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Historico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Historico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Historico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVoltar;
    private javax.swing.JButton btnZeraHistorico;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblLucroTotal;
    private java.awt.TextArea txtHistorico;
    // End of variables declaration//GEN-END:variables
}
