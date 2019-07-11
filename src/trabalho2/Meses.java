package trabalho2;

public enum Meses {//enumeraçao
    Janeiro(31) ,Fevereiro(28) ,Março(31) ,Abril(30) ,Maio(31) ,Junho(30) ,Julho(31) ,Agosto(31) ,Setembro(30) ,Outubro(31) ,
    Novembro(30) ,Dezembro(31);

    private int dias;

    
    private Meses(int dias)//construtor
    {
        this.dias=dias;
    }
    
//Getters e Setters
    public int getDias()
    {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

}
