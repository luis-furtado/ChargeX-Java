package chargex;

import java.awt.Color;
import java.awt.Font;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class CalculoEntrega extends javax.swing.JFrame {
    
    Frota frota;
    Moto moto;
    Carro carro;
    Van van;
    Carreta carreta;
    
    DecimalFormat fmt = new DecimalFormat("0.00");
    
    private double litrosMoto;
    private double litrosCarro;
    private double litrosVan;
    private double litrosCarreta;
    private double valorMoto;
    private double valorCarro;
    private double valorVan;
    private double valorCarreta;
    private double valorMenor;
    private double tempoMenor;
    private double tempoCustoBeneficio;
    private double tempoMenorCusto;
    private double custoBeneficio;
    private double consumo;
    private double valorCombustivel;
    private double valorFinal;
    private double distancia;
    private double peso;
    private double tempo;
    private double lucro;
    private double tempoParado;
    private int quantidadeMotosViagem;
    private int quantidadeCarrosViagem;
    private int quantidadeVansViagem;
    private int quantidadeCarretasViagem;
    private String veiculoMenorCusto;
    private String veiculoRapidaEntrega;
    private String veiculoCustoBeneficio;
    private String veiculoRemovido;
    private String armazenaHistorico;
    
    SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy  hh:mm");

    public CalculoEntrega() {
        initComponents();
        criarClasses();
        this.setLocationRelativeTo(null);
        valorMenor = 100000000;
        tempoMenor = 100000000;
        custoBeneficio = 0;
        veiculoMenorCusto="nenhum";
        veiculoRapidaEntrega="nenhum";
        veiculoCustoBeneficio="nenhum";
        veiculoRemovido="nenhum";
        preencherBarras();
        getContentPane().setBackground(new Color(73,78,93));
        aplicarEstilizacao();
    }
    
    public void criarClasses() {
        frota = new Frota();
        moto = new Moto();
        carro = new Carro();
        van = new Van();
        carreta = new Carreta();
    }
    
    public void aplicarEstilizacao() {
        lblRapidaEntrega.setFont(new Font("Arial", Font.BOLD, 14));
        lblMenorCusto.setFont(new Font("Arial", Font.BOLD, 14));
        lblCustoBeneficio.setFont(new Font("Arial", Font.BOLD, 14));
        lblRapidaEntrega.setForeground(new Color(62,156,0));
        lblMenorCusto.setForeground(new Color(62,156,0));
        lblCustoBeneficio.setForeground(new Color(62,156,0));
        lblCombustivelRapidaEntrega.setFont(new Font("Arial", Font.BOLD, 14));
        lblCombustivelMenorCusto.setFont(new Font("Arial", Font.BOLD, 14));
        lblCombustivelCustoBeneficio.setFont(new Font("Arial", Font.BOLD, 14));
        lblCombustivelRapidaEntrega.setForeground(new Color(62,156,0));
        lblCombustivelMenorCusto.setForeground(new Color(62,156,0));
        lblCombustivelCustoBeneficio.setForeground(new Color(62,156,0));
        lblValorCombustivel1.setFont(new Font("Arial", Font.BOLD, 14));
        lblValorCombustivel2.setFont(new Font("Arial", Font.BOLD, 14));
        lblValorCombustivel3.setFont(new Font("Arial", Font.BOLD, 14));
        lblValorCombustivel1.setForeground(new Color(62,156,0));
        lblValorCombustivel2.setForeground(new Color(62,156,0));
        lblValorCombustivel3.setForeground(new Color(62,156,0));
    }
    
    public String getArmazenaHistorico() {
        return this.armazenaHistorico;
    }
    
    public void preencherBarras() {
        boxOpcaoViagem.removeAllItems();
        boxOpcaoViagem.addItem("Selecione uma opção de viagem");
        boxOpcaoViagem.addItem("Menor Custo");
        boxOpcaoViagem.addItem("Entrega rápida");
        boxOpcaoViagem.addItem("Custo/Benefício");
    }
    
    public void resetarEscolhas() {
        veiculoMenorCusto = "nenhum";
        veiculoRapidaEntrega = "nenhum";
        veiculoCustoBeneficio = "nenhum";
        valorMenor = 10000000;
        tempoMenor = 10000000;
        custoBeneficio = 0;
        moto.setDisponibilidade(false);
        carro.setDisponibilidade(false);
        van.setDisponibilidade(false);
        carreta.setDisponibilidade(false);
    }
    
    public void disponibilidadeEntrega() {
        moto.setTempoEntrega(distancia/moto.getVelocidadeMedia());
        carro.setTempoEntrega(distancia/carro.getVelocidadeMedia());
        van.setTempoEntrega(distancia/van.getVelocidadeMedia());
        carreta.setTempoEntrega(distancia/carreta.getVelocidadeMedia());
        if(moto.getTempoEntrega()<=tempo*60&&peso<=moto.getCargaMaxima()&&frota.getQuantidadeMotos()>0) {
            moto.setDisponibilidade(true);
        } 
        if(carro.getTempoEntrega()<=tempo*60&&peso<=carro.getCargaMaxima()&&frota.getQuantidadeCarros()>0) {
            carro.setDisponibilidade(true);
        }
        if(van.getTempoEntrega()<=tempo*60&&peso<=van.getCargaMaxima()&&frota.getQuantidadeVans()>0) {
            van.setDisponibilidade(true);
        }
        if(carreta.getTempoEntrega()<=tempo*60&&peso<=carreta.getCargaMaxima()&&frota.getQuantidadeCarretas()>0) {
            carreta.setDisponibilidade(true);
        }
    }
    public void receberLucro(double lucro) {
        this.lucro = lucro;
        System.out.println(lucro);
    }
    public void mandarFrota(Frota frot) {
        frota = frot;
    }
    
    public void preencherViagemAndamento() {
            //Reescrever arquivo viagens-andamento
            Path arquivo = Paths.get("viagem-andamento.txt");
                    try {
                        byte[] dadosBytes = Files.readAllBytes(arquivo);
                        String dados = new String(dadosBytes);
                        String[] arquivoSeparado = dados.split("#");
                        this.quantidadeMotosViagem = Integer.parseInt(arquivoSeparado[0]);
                        this.quantidadeCarrosViagem = Integer.parseInt(arquivoSeparado[1]);
                        this.quantidadeVansViagem = Integer.parseInt(arquivoSeparado[2]);
                        this.quantidadeCarretasViagem = Integer.parseInt(arquivoSeparado[3]);
                        //Escolhendo qual veiculo sera adicionado a viagem em andamento
                        if(veiculoRemovido=="moto") {
                            quantidadeMotosViagem++;
                        }
                        if(veiculoRemovido=="carro") {
                            quantidadeCarrosViagem++;
                        }
                        if(veiculoRemovido=="van") {
                            quantidadeVansViagem++;
                        }
                        if(veiculoRemovido=="carreta") {
                            quantidadeCarretasViagem++;
                        }
                        String reescrever = quantidadeMotosViagem+"#"+quantidadeCarrosViagem+"#"+quantidadeVansViagem+"#"+quantidadeCarretasViagem;
                        byte[] reescreverBytes = reescrever.getBytes();
                        Files.write(arquivo, reescreverBytes);
                        }
                        catch(Exception erro) {
                            System.out.println("Deu ruim!");
                        }
    }
    
    public void removerViagemAndamento() {
        //Reescrever arquivo viagens-andamento removendo o veiculo que finalizou a viagem
        Path arquivo = Paths.get("viagem-andamento.txt");
        try {
            byte[] dadosByte = Files.readAllBytes(arquivo);
            String dados = new String(dadosByte);
            String[] arquivoSeparado = dados.split("#");
            this.quantidadeMotosViagem = Integer.parseInt(arquivoSeparado[0]);
            this.quantidadeCarrosViagem = Integer.parseInt(arquivoSeparado[1]);
            this.quantidadeVansViagem = Integer.parseInt(arquivoSeparado[2]);
            this.quantidadeCarretasViagem = Integer.parseInt(arquivoSeparado[3]);
            //Escolhendo qual veiculo sera adicionado a viagem em andamento
            if(veiculoRemovido=="moto") {
                quantidadeMotosViagem--;
            }
            if(veiculoRemovido=="carro") {
                quantidadeCarrosViagem--;
            }
            if(veiculoRemovido=="van") {
                quantidadeVansViagem--;
            }
            if(veiculoRemovido=="carreta") {
                quantidadeCarretasViagem--;
            }
            String reescrever = quantidadeMotosViagem+"#"+quantidadeCarrosViagem+"#"+quantidadeVansViagem+"#"+quantidadeCarretasViagem;
            byte[] reescreverBytes = reescrever.getBytes();
            Files.write(arquivo, reescreverBytes);
        }
        catch(Exception erro) {
            System.out.println("Deu ruim!");
        }
    }
    
    public void mandarDadosViagem(double Peso, double Distancia, double Tempo) {
        this.peso = Peso;
        this.distancia = Distancia;
        this.tempo = Tempo;
    }
    
    public void calculaValoresViagem() {
        //calculando valor final para viagem com a moto
        //tenho que arrumar no carro e na moto a reducao do rendimento por peso ;D
        moto.calculaRendimentoAlcool(peso);
        moto.calculaRendimentoGasolina(peso);
        moto.calculaMelhorCombustivel(peso, distancia);
        this.litrosMoto = distancia/moto.getConsumo();
        valorMoto = litrosMoto*moto.getCombustivelUtilizado()+litrosMoto*moto.getCombustivelUtilizado()*lucro;
        //calculando valor final para viagem com o carro
        carro.calculaRendimentoAlcool(peso);
        carro.calculaRendimentoGasolina(peso);
        carro.calculaMelhorCombustivel(peso, distancia);
        this.litrosCarro = distancia/carro.getConsumo();
        valorCarro = litrosCarro*carro.getCombustivelUtilizado()+litrosCarro*carro.getCombustivelUtilizado()*lucro;
        //Calculando valor final para viagem com a van
        van.calculaRendimentoDiesel(peso);
        this.litrosVan = distancia/van.getRendimentoReduzidoDiesel();
        valorVan = litrosVan*3.869+litrosVan*3.869*lucro;
        //Calculando valor final para viagem com a carreta
        carreta.calculaRendimentoDiesel(peso);
        this.litrosCarreta = distancia/carreta.getRendimentoReduzidoDiesel();
        valorCarreta = litrosCarreta*4.869+litrosCarreta*4.869*lucro;
    }
    
    public void calcularMenorCusto() {
        //decidindo qual será o veiculo para fazer a viagem com o menor valor
        if(valorMenor>valorMoto&&moto.getDisponibilidade()==true) {
            veiculoMenorCusto = "moto";
            valorMenor = valorMoto;
        }
        if(valorMenor>valorCarro&&carro.getDisponibilidade()==true) {
            veiculoMenorCusto = "carro";
            valorMenor = valorCarro;
        }
        if(valorMenor>valorVan&&van.getDisponibilidade()==true) {
            veiculoMenorCusto = "van";
            valorMenor = valorVan;
        }
        if(valorMenor>valorCarreta&&carreta.getDisponibilidade()==true) {
            veiculoMenorCusto = "carreta";
            valorMenor = valorCarreta;
        }
        if(veiculoMenorCusto=="moto") {
            lblMenorCusto.setText("Veiculo: Moto\t\t\tTempo: "+fmt.format(moto.getTempoEntrega()/60)+" horas\t\t\tCusto: R$"+fmt.format(valorMoto)+"\t\t\t");
            if(moto.getCombustivelUtilizado()==3.499) {
                lblValorCombustivel1.setText("Álcool");
            }
            else {
                lblValorCombustivel1.setText("Gasolina");
            }
            tempoMenorCusto = moto.getTempoEntrega();
        }
        if(veiculoMenorCusto=="carro") {
            lblMenorCusto.setText("Veiculo: Carro\t\t\tTempo: "+fmt.format(carro.getTempoEntrega()/60)+" horas\t\t\tCusto: R$"+fmt.format(valorCarro)+"\t\t\t");
            if(carro.getCombustivelUtilizado()==3.499) {
                lblValorCombustivel1.setText("Álcool");
            }
            else {
                lblValorCombustivel1.setText("Gasolina");
            }
            tempoMenorCusto = carro.getTempoEntrega();
        }
        if(veiculoMenorCusto=="van") {
            lblMenorCusto.setText("Veiculo: Van\t\t\tTempo: "+fmt.format(van.getTempoEntrega()/60)+" horas\t\t\tCusto: R$"+fmt.format(valorVan)+"\t\t\t");
            lblValorCombustivel1.setText("Diesel");
            tempoMenorCusto = van.getTempoEntrega();
        }
        if(veiculoMenorCusto=="carreta") {
            lblMenorCusto.setText("Veiculo: Carreta\t\tTempo: "+fmt.format(carreta.getTempoEntrega()/60)+" horas\t\t\tCusto: R$"+fmt.format(valorCarreta)+"\t\t\t");
            lblValorCombustivel1.setText("Diesel");
            tempoMenorCusto = carreta.getTempoEntrega();
        }
        if(frota.getQuantidadeMotos()==0&&frota.getQuantidadeCarros()==0&&frota.getQuantidadeVans()==0&&frota.getQuantidadeCarretas()==0) {
            JOptionPane.showMessageDialog(null, "Você não possui veiculos cadastrados para realizar a entrega!");
            dispose();
        }
        if(veiculoMenorCusto=="nenhum") {
            lblMenorCusto.setText("Nenhum veículo pode fazer esse estilo de viagem com os dados fornecidos.");
        }
    }
    
    public void calculaRapidaEntrega() {
        //decidir qual entrega sera a mais rapida
        if(tempoMenor>moto.getTempoEntrega()&&moto.getDisponibilidade()==true) {
            tempoMenor = moto.getTempoEntrega();
            veiculoRapidaEntrega = "moto";
        }
        if(tempoMenor>carro.getTempoEntrega()&&carro.getDisponibilidade()==true) {
            tempoMenor = carro.getTempoEntrega();
            veiculoRapidaEntrega = "carro";
        }
        if(tempoMenor>van.getTempoEntrega()&&van.getDisponibilidade()==true) {
            tempoMenor = van.getTempoEntrega();
            veiculoRapidaEntrega = "van";
        }
        if(tempoMenor>carreta.getTempoEntrega()&&carreta.getDisponibilidade()==true) {
            tempoMenor = carreta.getTempoEntrega();
            veiculoRapidaEntrega = "carreta";
        }
        if(veiculoRapidaEntrega=="moto") {
            lblRapidaEntrega.setText("Veiculo: Moto\t\t\tTempo: "+fmt.format(moto.getTempoEntrega()/60)+" horas\t\t\tCusto: R$"+fmt.format(valorMoto)+"\t\t\t");
            if(moto.getCombustivelUtilizado()==3.499) {
                lblValorCombustivel2.setText("Álcool");
            }
            else {
                lblValorCombustivel2.setText("Gasolina");
            }
        }
        if(veiculoRapidaEntrega=="carro") {
            lblRapidaEntrega.setText("Veiculo: Carro\t\t\tTempo: "+fmt.format(carro.getTempoEntrega()/60)+" horas\t\t\tCusto: R$"+fmt.format(valorCarro)+"\t\t\t");
            if(carro.getCombustivelUtilizado()==3.499) {
                lblValorCombustivel2.setText("Álcool");
            }
            else {
                lblValorCombustivel2.setText("Gasolina");
            }
        }
        if(veiculoRapidaEntrega=="van") {
            lblRapidaEntrega.setText("Veiculo: Van\t\t\tTempo: "+fmt.format(van.getTempoEntrega()/60)+" horas\t\t\tCusto: R$"+fmt.format(valorVan)+"\t\t\t");
            lblValorCombustivel2.setText("Diesel");
        }
        if(veiculoRapidaEntrega=="carreta") {
            lblRapidaEntrega.setText("Veiculo: Carreta\t\tTempo: "+fmt.format(carreta.getTempoEntrega()/60)+" horas\t\t\tCusto: R$"+fmt.format(valorCarreta)+"\t\t\t");
            lblValorCombustivel2.setText("Diesel");
        }
        if(veiculoRapidaEntrega=="nenhum") {
            lblRapidaEntrega.setText("Nenhum veículo pode fazer esse estilo de viagem com os dados fornecidos.");
        }
    }
    
    public void calculaCustoBeneficio() {
        // O custo beneficio sera o meio termo em relacao de custo/tempo
        if(custoBeneficio<valorMoto/moto.getTempoEntrega()&&moto.getDisponibilidade()==true) {
            custoBeneficio = valorMoto/moto.getTempoEntrega();
            veiculoCustoBeneficio = "moto";
        }
        if(custoBeneficio<valorCarro/carro.getTempoEntrega()&&carro.getDisponibilidade()==true) {
            custoBeneficio = valorCarro/carro.getTempoEntrega();
            veiculoCustoBeneficio = "carro";
        }
        if(custoBeneficio<valorVan/van.getTempoEntrega()&&van.getDisponibilidade()==true) {
            custoBeneficio = valorVan/van.getTempoEntrega();
            veiculoCustoBeneficio = "van";
        }
        if(custoBeneficio<valorCarreta/carreta.getTempoEntrega()&&carreta.getDisponibilidade()==true) {
            custoBeneficio = valorCarreta/carreta.getTempoEntrega();
            veiculoCustoBeneficio = "carreta";
        }
        if(veiculoCustoBeneficio=="moto") {
            lblCustoBeneficio.setText("Veiculo: Moto\t\t\tTempo: "+fmt.format(moto.getTempoEntrega()/60)+" horas\t\t\tCusto: R$"+fmt.format(valorMoto)+"\t\t\t");
            if(moto.getCombustivelUtilizado()==3.499) {
                lblValorCombustivel3.setText("Álcool");
            }
            else {
                lblValorCombustivel3.setText("Gasolina");
            }
            tempoCustoBeneficio = moto.getTempoEntrega();
        }
        if(veiculoCustoBeneficio=="carro") {
            lblCustoBeneficio.setText("Veiculo: Carro\t\t\tTempo: "+fmt.format(carro.getTempoEntrega()/60)+" horas\t\t\tCusto: R$"+fmt.format(valorCarro)+"\t\t\t");
            if(carro.getCombustivelUtilizado()==3.499) {
                lblValorCombustivel3.setText("Álcool");
            }
            else {
                lblValorCombustivel3.setText("Gasolina");
            }
            tempoCustoBeneficio = carro.getTempoEntrega();
        }
        if(veiculoCustoBeneficio=="van") {
            lblCustoBeneficio.setText("Veiculo: Van\t\t\tTempo: "+fmt.format(van.getTempoEntrega()/60)+" horas\t\t\tCusto: R$"+fmt.format(valorVan)+"\t\t\t");
            lblValorCombustivel3.setText("Diesel");
            tempoCustoBeneficio = van.getTempoEntrega();
        }
        if(veiculoCustoBeneficio=="carreta") {
            lblCustoBeneficio.setText("Veiculo: Carreta\t\tTempo: "+fmt.format(carreta.getTempoEntrega()/60)+" horas\t\t\tCusto: R$"+fmt.format(valorCarreta)+"\t\t\t");
            lblValorCombustivel3.setText("Diesel");
            tempoCustoBeneficio = carreta.getTempoEntrega();
        }
        if(veiculoCustoBeneficio=="nenhum") {
            lblCustoBeneficio.setText("Nenhum veículo pode fazer esse estilo de viagem com os dados fornecidos.");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        lblMenorCusto = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblRapidaEntrega = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblCustoBeneficio = new javax.swing.JLabel();
        boxOpcaoViagem = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblCombustivelMenorCusto = new javax.swing.JLabel();
        lblCombustivelRapidaEntrega = new javax.swing.JLabel();
        lblCombustivelCustoBeneficio = new javax.swing.JLabel();
        lblValorCombustivel1 = new javax.swing.JLabel();
        lblValorCombustivel2 = new javax.swing.JLabel();
        lblValorCombustivel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Veículo disponível com o menor custo de operação:");

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Veículo disponível que mais rápido faz a entrega:");

        lblRapidaEntrega.setText("-");

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Veículo disponível que possui o maior custo/benefício para a empresa:");

        lblCustoBeneficio.setText("-");

        boxOpcaoViagem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setText("Confirmar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Voltar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chargex/economia.png"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chargex/velocidade.png"))); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chargex/custo-beneficio.png"))); // NOI18N

        lblCombustivelMenorCusto.setText("Combustivel utilizado:");

        lblCombustivelRapidaEntrega.setText("Combustivel utilizado:");

        lblCombustivelCustoBeneficio.setText("Combustivel utilizado:");

        lblValorCombustivel1.setText("0");

        lblValorCombustivel2.setText("0");

        lblValorCombustivel3.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRapidaEntrega, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCustoBeneficio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCombustivelCustoBeneficio)
                                .addGap(18, 18, 18)
                                .addComponent(lblValorCombustivel3))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCombustivelRapidaEntrega)
                                .addGap(18, 18, 18)
                                .addComponent(lblValorCombustivel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel1)))
                            .addComponent(lblMenorCusto)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCombustivelMenorCusto)
                                .addGap(18, 18, 18)
                                .addComponent(lblValorCombustivel1)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(217, 217, 217)
                .addComponent(boxOpcaoViagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(45, 45, 45))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(lblMenorCusto)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCombustivelMenorCusto)
                    .addComponent(lblValorCombustivel1))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(lblRapidaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCombustivelRapidaEntrega)
                    .addComponent(lblValorCombustivel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(lblCustoBeneficio)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCombustivelCustoBeneficio)
                    .addComponent(lblValorCombustivel3))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(boxOpcaoViagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton1)))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(lblMenorCusto.getText()=="Nenhum veículo pode fazer esse estilo de viagem com os dados fornecidos."&&lblRapidaEntrega.getText()=="Nenhum veículo pode fazer esse estilo de viagem com os dados fornecidos."&&lblCustoBeneficio.getText()=="Nenhum veículo pode fazer esse estilo de viagem com os dados fornecidos.") {
            JOptionPane.showMessageDialog(null,"Nenhum veículo será capaz de realizar a entrega com os dados fornecidos!");
            dispose();
        }
        else if(boxOpcaoViagem.getSelectedItem()=="Selecione uma opção de viagem") {
            JOptionPane.showMessageDialog(null, "Você deve escolher alguma opção de viagem");
        }
        else {
            if(boxOpcaoViagem.getSelectedItem()=="Menor Custo") {
                if(veiculoMenorCusto=="moto"&&frota.getQuantidadeMotos()>0) {
                    frota.setQuantidadeMotos(frota.getQuantidadeMotos()-1);
                    veiculoRemovido = "moto";
                }
                if(veiculoMenorCusto=="carro"&&frota.getQuantidadeCarros()>0) {
                    frota.setQuantidadeCarros(frota.getQuantidadeCarros()-1);
                    veiculoRemovido = "carro";
                }
                if(veiculoMenorCusto=="van"&&frota.getQuantidadeVans()>0) {
                    frota.setQuantidadeVans(frota.getQuantidadeVans()-1);
                    veiculoRemovido = "van";
                }
                if(veiculoMenorCusto=="carreta"&&frota.getQuantidadeCarretas()>0) {
                    frota.setQuantidadeCarretas(frota.getQuantidadeCarretas()-1);
                    veiculoRemovido = "carreta";
                }
            }
            if(boxOpcaoViagem.getSelectedItem()=="Entrega rápida") {
                if(veiculoRapidaEntrega=="moto") {
                    frota.setQuantidadeMotos(frota.getQuantidadeMotos()-1);
                    veiculoRemovido = "moto";
                }
                if(veiculoRapidaEntrega=="carro") {
                    frota.setQuantidadeCarros(frota.getQuantidadeCarros()-1);
                    veiculoRemovido = "carro";
                }
                if(veiculoRapidaEntrega=="van") {
                    frota.setQuantidadeVans(frota.getQuantidadeVans()-1);
                    veiculoRemovido = "van";
                }
                if(veiculoRapidaEntrega=="carreta") {
                    frota.setQuantidadeCarretas(frota.getQuantidadeCarretas()-1);
                    veiculoRemovido = "carreta";
                }
            }
            if(boxOpcaoViagem.getSelectedItem()=="Custo/Benefício") {
                if(veiculoCustoBeneficio=="moto"&&frota.getQuantidadeMotos()>0) {
                    frota.setQuantidadeMotos(frota.getQuantidadeMotos()-1);
                    veiculoRemovido = "moto";
                }
                if(veiculoCustoBeneficio=="carro"&&frota.getQuantidadeCarros()>0) {
                    frota.setQuantidadeCarros(frota.getQuantidadeCarros()-1);
                    veiculoRemovido = "carro";
                }
                if(veiculoCustoBeneficio=="van"&&frota.getQuantidadeVans()>0) {
                    frota.setQuantidadeVans(frota.getQuantidadeVans()-1);
                    veiculoRemovido = "van";
                }
                if(veiculoCustoBeneficio=="carreta"&&frota.getQuantidadeCarretas()>0) {
                    frota.setQuantidadeCarretas(frota.getQuantidadeCarretas()-1);
                    veiculoRemovido = "carreta";
                }
            }
            
            //Montando estrutura de String que sera guardada no txt dados
                    if(boxOpcaoViagem.getSelectedItem()=="Menor Custo") {
                       armazenaHistorico = lblMenorCusto.getText();
                    }
                    if(boxOpcaoViagem.getSelectedItem()=="Entrega rápida") {
                       armazenaHistorico = lblRapidaEntrega.getText();
                    }
                    if(boxOpcaoViagem.getSelectedItem()=="Custo/Benefício") {
                       armazenaHistorico = lblCustoBeneficio.getText();
                    }

            preencherViagemAndamento();

            //Reescrever arquivo retirando o Veiculo selecionado temporariamente
            //Guardar Frota atualizada no arquivo TXT
            Date datInicio = new Date(System.currentTimeMillis());
            String dataInicio = formatador.format(datInicio);
            String dados = String.valueOf(frota.getQuantidadeMotos())+"#"+String.valueOf(frota.getQuantidadeCarros())+"#"+String.valueOf(frota.getQuantidadeVans())+"#"+String.valueOf(frota.getQuantidadeCarretas());
            byte[] dadosByte = dados.getBytes();
            Path arquivo = Paths.get("frota.txt");
            try {
                Files.write(arquivo, dadosByte);
            }
            catch(Exception erro) {
                System.out.println("Arquivo não encontrado!");
            }
           
            //Continuacao da execucao do programa com processamento dividido
            new Thread() {
                @Override
                public void run() {
                    dispose();
                }
            }.start();
            
            //Iniciando estado de pausa do sistem enquanto viagem e executada
            new Thread() {
                @Override
                public void run() {
                    JOptionPane.showMessageDialog(null, "Veiculo selecionado com sucesso!\n\nApós a viagem ser finalizada, a viagem será adicionada ao seu Histórico de Viagens.");
                    //esperando o veiculo terminar a viagem para adicionar o veiculo novamente a frota
                    if(boxOpcaoViagem.getSelectedItem()=="Menor Custo") {
                        resetarEscolhas();
                        try { 
                            Thread.sleep ((long)(1000*60*tempoMenorCusto));
                        }
                        catch (InterruptedException ex) {
                            System.out.println("Não deu para pausar o sistema");
                        }
                    }
                    if(boxOpcaoViagem.getSelectedItem()=="Entrega rápida") {
                        try { 
                            Thread.sleep ((long)(1000*60*tempoMenor)); 
                        }
                        catch (InterruptedException ex) {
                            System.out.println("Não deu para pausar o sistema");
                        }
                    }
                    if(boxOpcaoViagem.getSelectedItem()=="Custo/Benefício") {
                        resetarEscolhas();
                        try { 
                            Thread.sleep ((long)(1000*60*tempoCustoBeneficio));
                        }
                        catch (InterruptedException ex) {
                            System.out.println("Não deu para pausar o sistema");
                        }
                    }
                    //Depois de finalizar o tempo da viagem, adicionar novamente ao arquivo o veiculo na frota;
                    if(veiculoRemovido=="moto") {
                        frota.setQuantidadeMotos(frota.getQuantidadeMotos()+1);
                    }
                    if(veiculoRemovido=="carro") {
                        frota.setQuantidadeCarros(frota.getQuantidadeCarros()+1);
                    }
                    if(veiculoRemovido=="van") {
                        frota.setQuantidadeVans(frota.getQuantidadeVans()+1);
                    }
                    if(veiculoRemovido=="carreta") {
                        frota.setQuantidadeCarretas(frota.getQuantidadeCarretas()+1);
                    }
                    //Abrindo arquivo e devolvendo veiculo
                    String dados = String.valueOf(frota.getQuantidadeMotos())+"#"+String.valueOf(frota.getQuantidadeCarros())+"#"+String.valueOf(frota.getQuantidadeVans())+"#"+String.valueOf(frota.getQuantidadeCarretas());
                    byte[] dadosByte = dados.getBytes();
                    Path arquivo = Paths.get("frota.txt");
                    try {
                        Files.write(arquivo, dadosByte);
                        System.out.println("Escrevi depois de terminar a viagem");
                    }
                    catch(Exception erro) {
                        System.out.println("Arquivo não encontrado!");
                    }
                    
                    //Abrindo arquivo historico para leitura e gravação
                    arquivo = Paths.get("historico.txt");
                    try {
                        byte[] historicoBytes = Files.readAllBytes(arquivo);
                        String historico = new String(historicoBytes);
                        Date data = new Date(System.currentTimeMillis());
                        String dataFinal = formatador.format(data);
                        historico = historico.concat("\n\n"+armazenaHistorico+"Data Inicio: "+dataInicio+"\t\t\tData Entrega: "+dataFinal);
                        System.out.println(historico);
                        historicoBytes = historico.getBytes();
                        Files.write(arquivo,historicoBytes);
                    }
                    catch(Exception erro) {
                        System.out.println("Deu ruim!");
                    }
                    
                    //Abrindo arquivo TXT lucro-total para leitura e gravação
                    arquivo = Paths.get("lucro-total.txt");
                    try {
                        //Pegando valor da entrega para adicionar ao valor total do arquivo
                        System.out.println(armazenaHistorico);
                        String[] pegaLucro = armazenaHistorico.split("\\$");
                        System.out.println(pegaLucro[1]);
                        //lendo arquivo
                        byte[] lucroBytes = Files.readAllBytes(arquivo);
                        String lucroTotal = new String(lucroBytes);
                        //lendo armazenamento historico ate $ e quebrando para pegar valor do lucro
                        double lucroTotalDouble = Double.parseDouble(lucroTotal);
                        String auxiliar = pegaLucro[1];
                        auxiliar = auxiliar.replaceAll(",","\\.");
                        lucroTotalDouble+= Double.parseDouble(auxiliar);
                        lucroTotal = String.valueOf(lucroTotalDouble);
                        byte[] lucroTotalBytes = lucroTotal.getBytes();
                        Files.write(arquivo,lucroTotalBytes);
                    }
                    catch(Exception erro) {
                        System.out.println("Arquivo não encontrado!");
                    }
                   
                    removerViagemAndamento();
                   
                }
            }.start();
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(CalculoEntrega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CalculoEntrega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CalculoEntrega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CalculoEntrega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CalculoEntrega().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxOpcaoViagem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblCombustivelCustoBeneficio;
    private javax.swing.JLabel lblCombustivelMenorCusto;
    private javax.swing.JLabel lblCombustivelRapidaEntrega;
    private javax.swing.JLabel lblCustoBeneficio;
    private javax.swing.JLabel lblMenorCusto;
    private javax.swing.JLabel lblRapidaEntrega;
    private javax.swing.JLabel lblValorCombustivel1;
    private javax.swing.JLabel lblValorCombustivel2;
    private javax.swing.JLabel lblValorCombustivel3;
    // End of variables declaration//GEN-END:variables
}
