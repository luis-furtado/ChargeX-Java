package chargex;

import java.awt.Color;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JOptionPane;

public class Frota extends javax.swing.JFrame {
    
    InserirVeiculo insereVeiculo;

    public Frota() {
        initComponents();
        this.veiculoAdicionado = "Nenhum";
        this.setLocationRelativeTo(null);  
        getContentPane().setBackground(new Color(73,78,93));
    }
    private int quantidade;
    private int veiculosDisponiveis;
    private double lucro;
    private String atualFrota;
    private int quantidadeMotos;
    private int quantidadeCarros;
    private int quantidadeVans;
    private int quantidadeCarretas;
    private String veiculoAdicionado;
    
    public int getQuantidadeMotos() {
        return this.quantidadeMotos;
    }
    public void setQuantidadeMotos(int quantidadeMotos) {
        this.quantidadeMotos = quantidadeMotos;
    }
    public int getQuantidadeCarros() {
        return this.quantidadeCarros;
    }
    public void setQuantidadeCarros(int quantidadeCarros) {
        this.quantidadeCarros = quantidadeCarros;
    }
    public int getQuantidadeVans() {
        return this.quantidadeVans;
    }
    public void setQuantidadeVans(int quantidadeVans) {
        this.quantidadeVans = quantidadeVans;
    }
    public int getQuantidadeCarretas() {
        return this.quantidadeCarretas;
    }
    public void setQuantidadeCarretas(int quantidadeCarretas) {
        this.quantidadeCarretas = quantidadeCarretas;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public int getVeiculosDisponiveis() {
        return this.veiculosDisponiveis;
    }
    public void setVeiculosDisponiveis(int veiculosDisponiveis) {
        this.veiculosDisponiveis = veiculosDisponiveis;
    }
    public double getLucro() {
        return this.lucro;
    }
    public void setLucro(double lucro) {
        this.lucro = lucro;
    }
    public String getFrota() {
        return this.atualFrota;
    }
    public void setFrota(String frota) {
        this.atualFrota = frota;
    }
    
    public void receberFrota(String frota) {
        this.atualFrota = frota;
    }
    
    public void separarArquivo() {
        String[] arquivoSeparado = this.atualFrota.split("#");
        this.quantidadeMotos = Integer.parseInt(arquivoSeparado[0]);
        this.quantidadeCarros = Integer.parseInt(arquivoSeparado[1]);
        this.quantidadeVans = Integer.parseInt(arquivoSeparado[2]);
        this.quantidadeCarretas = Integer.parseInt(arquivoSeparado[3]);
    }
    
    public void atualizarVeiculos() {
        lblMoto.setText(String.valueOf(this.quantidadeMotos));
        lblCarro.setText(String.valueOf(this.quantidadeCarros));
        lblVan.setText(String.valueOf(this.quantidadeVans));
        lblCarreta.setText(String.valueOf(this.quantidadeCarretas));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        lblQuantidade = new javax.swing.JLabel();
        lblMotos = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblMoto = new javax.swing.JLabel();
        lblCarro = new javax.swing.JLabel();
        lblVan = new javax.swing.JLabel();
        lblCarreta = new javax.swing.JLabel();
        btnInserir = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton1.setText("Voltar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblQuantidade.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblQuantidade.setForeground(new java.awt.Color(255, 255, 255));
        lblQuantidade.setText("Quantidade de Veiculos:");

        lblMotos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chargex/moto.png"))); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chargex/carro.png"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chargex/van.png"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chargex/caminhao.png"))); // NOI18N

        lblMoto.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblMoto.setForeground(new java.awt.Color(255, 255, 255));
        lblMoto.setText("0");

        lblCarro.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblCarro.setForeground(new java.awt.Color(255, 255, 255));
        lblCarro.setText("0");

        lblVan.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblVan.setForeground(new java.awt.Color(255, 255, 255));
        lblVan.setText("0");

        lblCarreta.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblCarreta.setForeground(new java.awt.Color(255, 255, 255));
        lblCarreta.setText("0");

        btnInserir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chargex/adicionar.png"))); // NOI18N
        btnInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInserirActionPerformed(evt);
            }
        });

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chargex/remover.png"))); // NOI18N
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        btnAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chargex/atualizar.png"))); // NOI18N
        btnAtualizar.setText("Atualizar");
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblCarro, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblMotos)
                                .addGap(72, 72, 72)
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(61, 61, 61)
                        .addComponent(jLabel2)
                        .addGap(54, 54, 54)
                        .addComponent(jLabel3)
                        .addGap(109, 109, 109))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblMoto, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(399, 399, 399))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAtualizar)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnInserir)
                        .addGap(83, 83, 83)
                        .addComponent(btnRemover))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblVan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblQuantidade))
                        .addGap(58, 58, 58)
                        .addComponent(lblCarreta, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAtualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnInserir)
                    .addComponent(btnRemover))
                .addGap(42, 42, 42)
                .addComponent(lblQuantidade)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2)
                        .addComponent(lblMotos)))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCarreta)
                    .addComponent(lblVan)
                    .addComponent(lblCarro)
                    .addComponent(lblMoto))
                .addGap(33, 33, 33)
                .addComponent(jButton1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Guardar Frota atualizada no arquivo TXT
        String dados = String.valueOf(this.quantidadeMotos)+"#"+String.valueOf(this.quantidadeCarros)+"#"+String.valueOf(this.quantidadeVans)+"#"+String.valueOf(this.quantidadeCarretas);
        byte[] dadosByte = dados.getBytes();
        Path arquivo = Paths.get("frota.txt");
        try {
            Files.write(arquivo, dadosByte);
        }
        catch(Exception erro) {
            System.out.println("Arquivo não encontrado!");
        }
        
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    
    private void btnInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInserirActionPerformed
        if(insereVeiculo==null) {
            insereVeiculo = new InserirVeiculo();
            insereVeiculo.preencherBarras();
            insereVeiculo.setVisible(true);
        }
        else {
            insereVeiculo.setVisible(true);
        }
        System.out.println(insereVeiculo.getItemEscolhido());
        
        
    }//GEN-LAST:event_btnInserirActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        if(insereVeiculo==null) {
            
        }
        else {
             if(insereVeiculo.getItemEscolhido()=="Adicionar Moto") {
                 this.quantidadeMotos++;
             }
             else if(insereVeiculo.getItemEscolhido()=="Adicionar Carro") {
                 this.quantidadeCarros++;
             }
             else if(insereVeiculo.getItemEscolhido()=="Adicionar Van") {
                 this.quantidadeVans++;
             }
             else if(insereVeiculo.getItemEscolhido()=="Adicionar Carreta") {
                 this.quantidadeCarretas++;
             }
             else if(insereVeiculo.getItemEscolhido()=="Remover Moto") {
                 if(this.quantidadeMotos==0) {
                     JOptionPane.showMessageDialog(null, "Você não pode remover moto, pois não há motos para remoção");
                 }
                 else {
                     this.quantidadeMotos--;
                 }
             }
             else if(insereVeiculo.getItemEscolhido()=="Remover Carro") {
                 if(this.quantidadeCarros==0) {
                     JOptionPane.showMessageDialog(null, "Você não pode remover carro, pois não há carros para remoção");
                 }
                 else {
                     this.quantidadeCarros--;
                 }
             }
             else if(insereVeiculo.getItemEscolhido()=="Remover Van") {
                 if(this.quantidadeVans==0) {
                     JOptionPane.showMessageDialog(null, "Você não pode remover vans, pois não há vans para remoção");
                 }
                 else {
                     this.quantidadeVans--;
                 }
             }
             else if(insereVeiculo.getItemEscolhido()=="Remover Carreta") {
                 if(this.quantidadeCarretas==0) {
                     JOptionPane.showMessageDialog(null, "Você não pode remover Carretas, pois não há carretas para remoção");
                 }
                 else {
                     this.quantidadeCarretas--;
                 }
             }
             insereVeiculo.setItemEscolhido("nenhum");
        }
        atualizarVeiculos();
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        if(insereVeiculo==null) {
            insereVeiculo = new InserirVeiculo();
            insereVeiculo.preencherBarras2();
            insereVeiculo.setVisible(true);
        }
        else {
            insereVeiculo.setVisible(true);
           
        }
        
        atualizarVeiculos();
        
        
    }//GEN-LAST:event_btnRemoverActionPerformed

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
            java.util.logging.Logger.getLogger(Frota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frota().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnInserir;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblCarreta;
    private javax.swing.JLabel lblCarro;
    private javax.swing.JLabel lblMoto;
    private javax.swing.JLabel lblMotos;
    private javax.swing.JLabel lblQuantidade;
    private javax.swing.JLabel lblVan;
    // End of variables declaration//GEN-END:variables
}
