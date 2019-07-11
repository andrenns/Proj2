/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho2;

public enum TipoConsultas {//enumeraçao
    
    Diagonostico(30), Exame(90), DescontoSD(0.4),DescontoSE(0.2), DescontoId(0.1), DescontoR(0.05);
    //preço das consultas sem seguro

    
    private double preco;//preço chave para as consultas
    
    private TipoConsultas(double preco)//construtor private pois é uma enumeraçao
    {
        this.preco = preco;
    }
    
    public void setPreco(double preco)
    {
        this.preco= preco;
    }
    
    public double getPreco()
    {
        return preco;
    }
}
