package chargex;

public class Carro extends Veiculo{
    String combustivel2;
    int rendimento2;
    double reducaoRendimentoPorPeso2;
    double consumo;
    double combustivelUtilizado = 0;
    double rendimentoReduzidoAlcool;
    double rendimentoReduzidoGasolina;
    
    public Carro() {
        this.disponibilidade = false;
        this.combustivel = "gasolina";
        this.combustivel2 = "alcool";
        this.rendimento = 14;
        this.rendimento2 = 12;
        this.cargaMaxima = 360;
        this.velocidadeMedia = 100;
        this.reducaoRendimentoPorPeso = 0.025;
        this.reducaoRendimentoPorPeso2 = 0.0231;
        this.consumo = 0;
        this.combustivelUtilizado = 0;
        
    }
    
    public double getCombustivelUtilizado() {
        return this.combustivelUtilizado;
    }
    public void setCombustivelUtilizado(double combustivelUtilizado) {
        this.combustivelUtilizado = combustivelUtilizado;
    }
    public double getConsumo() {
        return this.consumo;
    }
    public void setConsumo(double combustivelUtilizado) {
        this.consumo = combustivelUtilizado;
    }
    public String getCombustivel2() {
        return this.combustivel2;
    }
    public void setCombustivel2(String combustivel2) {
        this.combustivel2 = combustivel2;
    }
    public int getRendimento2() {
        return this.rendimento2;
    }
    public void setRendimento2(int rendimento2) {
        this.rendimento2 = rendimento2;
    }
    public double getReducaoRendimentoPorPeso2() {
        return this.reducaoRendimentoPorPeso2;
    }
    public void setReducaoRendimentoPorPeso2(double reducaoRendimentoPorPeso) {
        this.reducaoRendimentoPorPeso2 = reducaoRendimentoPorPeso;
    }
    public void calculaRendimentoAlcool(double peso) {
        rendimentoReduzidoAlcool = this.rendimento2 - peso*this.reducaoRendimentoPorPeso2;                    
    }
    public void calculaRendimentoGasolina(double peso) {
        rendimentoReduzidoGasolina = this.rendimento - peso*this.reducaoRendimentoPorPeso;
    }
    @Override
    public void calculaMelhorCombustivel(double peso, double distancia) {
        //calculando a quantidade de litros de cada rendimento
        //System.out.println(rendimentoReduzidoGasolina);
        //System.out.println(rendimentoReduzidoAlcool);
        double litrosGasolina = distancia/rendimentoReduzidoGasolina;
        double litrosAlcool = distancia/rendimentoReduzidoAlcool;
        //tomada de decisao para qual combustivel escolher
        //System.out.println("O valor a ser gasto de gasolina e: "+litrosGasolina*4.449);
        //System.out.println("O valor a ser gasto de alcool e: "+litrosAlcool*3.499);
        if(litrosGasolina*4.449>litrosAlcool*3.499) {
            this.consumo = rendimentoReduzidoAlcool;
            this.combustivelUtilizado = 3.499;
        }
        else {
            this.consumo = rendimentoReduzidoGasolina;
            this.combustivelUtilizado = 4.449;
        }
    }
    
}
