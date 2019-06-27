package chargex;

public class Carreta extends Veiculo {
    
    private double rendimentoReduzidoDiesel;
    
    public Carreta(){
        this.disponibilidade = false;
        this.combustivel = "diesel";
        this.rendimento = 8;
        this.cargaMaxima = 30000;
        this.velocidadeMedia = 60;
        this.reducaoRendimentoPorPeso = 0.0002;
    }
    
    public double getRendimentoReduzidoDiesel() {
        return this.rendimentoReduzidoDiesel;
    }
    public void setRendimentoReduzidoDiesel(double rendimentoReduzidoDiesel) {
        this.rendimentoReduzidoDiesel = rendimentoReduzidoDiesel;
    }
    
    public void calculaRendimentoDiesel(double peso) {
        //calculando rendimento real a partir do peso
        rendimentoReduzidoDiesel = this.rendimento-peso*this.reducaoRendimentoPorPeso;
    }
        
}
