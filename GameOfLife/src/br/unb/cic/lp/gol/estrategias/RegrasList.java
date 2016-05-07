package br.unb.cic.lp.gol.estrategias;

import java.util.List;

import br.unb.cic.lp.gol.EstrategiaDeDerivacao;

public class RegrasList {
    private List<EstrategiaDeDerivacao> regras;
    
    public RegrasList(){ }
    
    public List<EstrategiaDeDerivacao> getRegras(){
        return this.regras;
    }

    public void setRegras(List<EstrategiaDeDerivacao> regras){
        this.regras = regras;
    }
}