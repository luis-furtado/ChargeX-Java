package chargex;

public class Veiculo {
    boolean disponibilidade;
    String combustivel;
    int rendimento;
    double cargaMaxima;
    int velocidadeMedia;
    double reducaoRendimentoPorPeso;
    double tempoEntrega;
    
    public void Veiculo() {
        disponibilidade = false;
    }
    
    public boolean getDisponibilidade() {
        return this.disponibilidade;
    }
    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
    public String getCombustivel() {
        return this.combustivel;
    }
    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }
    public int getRendimento() {
        return this.rendimento;
    }
    public void setRendimento(int rendimento) {
        this.rendimento = rendimento;
    }
    public double getCargaMaxima() {
        return this.cargaMaxima;
    }
    public void setCargaMaxima(double cargaMaxima) {
        this.cargaMaxima = cargaMaxima;
    }
    public int getVelocidadeMedia() {
        return this.velocidadeMedia;
    }
    public void setVelocidadeMedia(int velocidadeMedia) {
        this.velocidadeMedia = velocidadeMedia;
    } 
    public double getReducaoRendimentoPorPeso() {
        return this.reducaoRendimentoPorPeso;
    }
    public void setReducaoRendimentoPorPeso(double reducaoRendimentoPorPeso) {
        this.reducaoRendimentoPorPeso = reducaoRendimentoPorPeso;
    }
    public double getTempoEntrega() {
        return this.tempoEntrega;
    }
    public void setTempoEntrega(double veiculoTempoEntrega) {
        this.tempoEntrega = veiculoTempoEntrega*60;
    }
    
    
    public void calculaMelhorCombustivel(double peso, double distancia) {
        //Neste caso, o retorno e trivial, pois so dispomos de um tipo de combustivel
        this.combustivel = "diesel";
    }
}
