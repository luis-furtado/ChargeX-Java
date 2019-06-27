package chargex;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JOptionPane;

public final class Principal extends javax.swing.JFrame {

    Lucro paginaLucro;
    Frota alterarFrota;
    NovaEntrega entrega;
    Historico historico;
           
    private double lucro;
    private int quantidadeMotos;
    private int quantidadeCarros;
    private int quantidadeVans;
    private int quantidadeCarretas;
    
    public int getQuantidadeMotos() {
        return this.quantidadeMotos;
    }
    private void setQuantidadeMotos(int quantidadeMotos) {
        this.quantidadeMotos = quantidadeMotos;
    }
 
    public Principal() {
        //ler lucro armazenado na ultima execução do programa
        lerLucroAnterior();
        initComponents();
        criarClasses();
        aplicarEstilizacao();
        setSize(900, 470); 
        setResizable(false);
        this.setLocationRelativeTo(null);
        tratarDadosNoFechamento();
    }
    
    public void criarClasses() {
        paginaLucro = new Lucro(lucro);
        alterarFrota = new Frota();
        entrega = new NovaEntrega();
        historico = new Historico();
    }

    public void lerLucroAnterior() {
        Path arquivo = Paths.get("lucro.txt");
        try {
            byte[] texto = Files.readAllBytes(arquivo);
            String leitura = new String(texto);
            this.lucro = Double.parseDouble(leitura);
            System.out.println(lucro);
            System.out.println("Lucro armazenado com sucesso!");
        }
        catch(Exception erro) {
            System.out.println("Arquivo não encontrado!");
        }
    }
    
    public void aplicarEstilizacao() {
        getContentPane().setBackground(new Color(69,77,93));
        btnNovaEntrega.setSize(64,64);
        btnHistorico.setSize(64,64);
        btnAlterarFrota.setSize(64,64);
        btnAlterarMargemLucro.setSize(64,64);
        btnNovaEntrega.setToolTipText("Nova Viagem");
        btnHistorico.setToolTipText("Histórico de Viagens");
        btnAlterarFrota.setToolTipText("Alterar Frota");
        btnAlterarMargemLucro.setToolTipText("Alterar Margem de Lucro");
    }
    
    public void tratarDadosNoFechamento() {
        addWindowListener(
            new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent we) {
                //quando o programa for fechado, sobrescrever o arquivo de lucro com a porcentagem de lucro atual do programa
                lucro = paginaLucro.getLucro();
                String dados = String.valueOf(lucro);
                byte[] dadosByte = dados.getBytes();
                Path arquivo = Paths.get("lucro.txt");
                    try {
                        Files.write(arquivo, dadosByte);
                        System.out.println("Escrevi lucro antes de fechar o programa");
                        
                        quantidadeMotos = 0;
                        quantidadeCarros = 0;
                        quantidadeVans = 0;
                        quantidadeCarretas = 0;
                        
                        //Tratando agora os veiculos que estao em viagem (devem ser retornados a frota)
                        arquivo = Paths.get("viagem-andamento.txt");
                        byte[] dadosBytes = Files.readAllBytes(arquivo);
                        dados = new String(dadosBytes);
                        String[] arquivoSeparado = dados.split("#");
                        quantidadeMotos = Integer.parseInt(arquivoSeparado[0]);
                        quantidadeCarros = Integer.parseInt(arquivoSeparado[1]);
                        quantidadeVans = Integer.parseInt(arquivoSeparado[2]);
                        quantidadeCarretas = Integer.parseInt(arquivoSeparado[3]);
                        
                        //Se houver viagens sendo executadas, exibiremos o botao de alerta para o usuario escolher se realmente vai fechar o programa
                        if(quantidadeMotos>0||quantidadeCarros>0||quantidadeVans>0||quantidadeCarretas>0) {
                            Alerta alerta = new Alerta();
                            
                            //1 thread para apresentar a nova janela e continuar rodando o programa e a outra para pausar o sistema enquanto a escolha acontece
                            new Thread() {
                                @Override
                                public void run() {
                                    alerta.setVisible(true);
                                }
                            }.start();
                            //2 thread decidir se o programa ira fechar ou nao e fazer os respectivos tratamentos
                            new Thread() {
                                @Override
                                public void run() {
                                    while(alerta.getEscolha()==-1) {
                                        try {
                                            Thread.sleep(10);
                                        } catch (InterruptedException ex) {
                                            System.out.println("Deu erro ao pausar o programa");
                                        }
                                    }
                                    //Se escolhido 0, o programa fecha e nao precisa alterar nada
                                    if(alerta.getEscolha()==0) {
                                        alerta.dispose();
                                        alerta.setEscolha(-1);
                                        System.out.println("O programa nao ira fazer nada");
                                        }
                                    //Depois de escolhido, o programa deve devolver os veiculos a frota e depois deve ser finalizado
                                    if(alerta.getEscolha()==1){
                                        System.out.println("Entrou aquii!");
                                        Path arquivo = Paths.get("frota.txt");
                                        try {
                                            byte[] dadosBytes = Files.readAllBytes(arquivo);
                                        String dados = new String(dadosBytes);
                                        String[] arquivoSeparado = dados.split("#");
                                        quantidadeMotos += Integer.parseInt(arquivoSeparado[0]);
                                        quantidadeCarros += Integer.parseInt(arquivoSeparado[1]);
                                        quantidadeVans += Integer.parseInt(arquivoSeparado[2]);
                                        quantidadeCarretas += Integer.parseInt(arquivoSeparado[3]);
                                        String frotaAtualizada = quantidadeMotos+"#"+quantidadeCarros+"#"+quantidadeVans+"#"+quantidadeCarretas;
                                        //Reescrevendo arquivo frota devolvendo veiculos
                                        dadosBytes = frotaAtualizada.getBytes();
                                        Files.write(arquivo, dadosBytes);
                                        //Reescrevendo arquivo viagem-andamento zerando veiculos que estao em viagem
                                        arquivo = Paths.get("viagem-andamento.txt");
                                        String zerandoVeiculos = 0+"#"+0+"#"+0+"#"+0;
                                        dadosBytes = zerandoVeiculos.getBytes();
                                        Files.write(arquivo, dadosBytes);
                                        System.out.println("Veiculos que estavam em viagem sao devolvidos a frota!");
                                        //Fechando programa
                                        System.exit(1);
                                        }
                                        catch(Exception erro) {
                                            System.out.println("Deu ruim!");
                                        }
                                    }
                                }
                            }.start();     
                        }
                        else {
                            //Fechando programa caso nao tenha nenhuma viagem em andamento
                            System.exit(1);
                        }
                    }
                    catch(Exception erro) {
                        System.out.println("Arquivo não encontrado!");
                    }
            }
	});
    }
     
    public double getLucro() {
        return lucro;
    }
    
    public void setLucro(double lucro) {
        this.lucro = lucro;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnNovaEntrega = new javax.swing.JButton();
        btnHistorico = new javax.swing.JButton();
        btnAlterarFrota = new javax.swing.JButton();
        btnAlterarMargemLucro = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(null);

        btnNovaEntrega.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chargex/adicionar.png"))); // NOI18N
        btnNovaEntrega.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/chargex/adicionar.png"))); // NOI18N
        btnNovaEntrega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovaEntregaActionPerformed(evt);
            }
        });
        getContentPane().add(btnNovaEntrega);
        btnNovaEntrega.setBounds(30, 40, 90, 40);

        btnHistorico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chargex/historico.png"))); // NOI18N
        btnHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoricoActionPerformed(evt);
            }
        });
        getContentPane().add(btnHistorico);
        btnHistorico.setBounds(30, 130, 90, 40);

        btnAlterarFrota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chargex/atualizar.png"))); // NOI18N
        btnAlterarFrota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarFrotaActionPerformed(evt);
            }
        });
        getContentPane().add(btnAlterarFrota);
        btnAlterarFrota.setBounds(30, 230, 90, 40);

        btnAlterarMargemLucro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chargex/editar.png"))); // NOI18N
        btnAlterarMargemLucro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarMargemLucroActionPerformed(evt);
            }
        });
        getContentPane().add(btnAlterarMargemLucro);
        btnAlterarMargemLucro.setBounds(30, 330, 90, 40);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chargex/transportadora.png"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(-30, -3, 980, 470);

        jButton1.setText("jButton1");
        getContentPane().add(jButton1);
        jButton1.setBounds(270, 120, 97, 29);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoricoActionPerformed
        historico.lerArquivo();
        historico.alterarTexto();
        historico.atualizarLucroTotal();
        historico.setVisible(true);
        JOptionPane.showMessageDialog(null, "\nA viagem só é armazenada no histórico após acabar o tempo da viagem.\n\nPara visualizar os veículos que estão em viagem nesse momento, clique no botão Viagens em andamento\n");
    }//GEN-LAST:event_btnHistoricoActionPerformed

    private void btnAlterarMargemLucroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarMargemLucroActionPerformed
        paginaLucro.setVisible(true);
        this.lucro = paginaLucro.getLucro();
        System.out.println(this.lucro);
    }//GEN-LAST:event_btnAlterarMargemLucroActionPerformed

    private void btnAlterarFrotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarFrotaActionPerformed
        alterarFrota.setVisible(true);
        JOptionPane.showMessageDialog(null, "Clique no botão de atualizar após modificar sua frota para visualizar sua frota Atualizada!");
        Path caminho = Paths.get("frota.txt");
        try {
            byte[] texto = Files.readAllBytes(caminho);
            String leitura = new String(texto);
            alterarFrota.receberFrota(leitura);
            alterarFrota.separarArquivo();
            alterarFrota.atualizarVeiculos();
        }
        catch(Exception erro) {
            System.out.println("Arquivo não encontrado!");
        }
    }//GEN-LAST:event_btnAlterarFrotaActionPerformed

    private void btnNovaEntregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovaEntregaActionPerformed
        entrega.setVisible(true);
        //Atualizando quantidade de veiculos antes de receber dados Nova Entrega
        Path caminho = Paths.get("frota.txt");
        try {
            byte[] texto = Files.readAllBytes(caminho);
            String leitura = new String(texto);
            alterarFrota.receberFrota(leitura);
            alterarFrota.separarArquivo();
            alterarFrota.atualizarVeiculos();
        }
        catch(Exception erro) {
            System.out.println("Arquivo não encontrado!");
        }
        entrega.MandarFrota(alterarFrota);
        this.lucro = paginaLucro.getLucro();
        entrega.receberLucro(lucro);
            
   
    }//GEN-LAST:event_btnNovaEntregaActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterarFrota;
    private javax.swing.JButton btnAlterarMargemLucro;
    private javax.swing.JButton btnHistorico;
    private javax.swing.JButton btnNovaEntrega;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables

    void setIconeImage(String iconepng) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
