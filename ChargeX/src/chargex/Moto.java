package chargex;

public class Moto extends Carro{
    
    public Moto() {
        this.disponibilidade = false;
        this.combustivel = "gasolina";
        this.combustivel2 = "alcool";
        this.rendimento = 50;
        this.rendimento2 = 43;
        this.cargaMaxima = 50;
        this.velocidadeMedia = 110;
        this.reducaoRendimentoPorPeso = 0.3;
        this.reducaoRendimentoPorPeso2 = 0.4;
    }
    @Override
    public void calculaMelhorCombustivel(double peso, double distancia) {
        
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
