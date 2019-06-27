package chargex;

public class Van extends Veiculo{
    
    private double rendimentoReduzidoDiesel;
    
    public Van() { 
        this.disponibilidade = false;
        this.combustivel = "diesel";
        this.rendimento = 10;
        this.cargaMaxima = 3500;
        this.velocidadeMedia = 80;
        this.reducaoRendimentoPorPeso = 0.0001;
    }
    
    public double getRendimentoReduzidoDiesel() {
        return this.rendimentoReduzidoDiesel;
    }
    public void setRendimentoReduzidoDiesel(double rendimentoReduzidoDiesel) {
        this.rendimentoReduzidoDiesel = rendimentoReduzidoDiesel;
    }
    
    public void calculaRendimentoDiesel(double peso) {
        //calculando o rendimento real da gasolina e do alcool
        rendimentoReduzidoDiesel = this.rendimento-peso*this.reducaoRendimentoPorPeso;                            
    }
}
