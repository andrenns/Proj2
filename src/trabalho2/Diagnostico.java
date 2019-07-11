/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho2;
//import java.util.ArrayList;
import java.util.Random;


public class Diagnostico extends Consultas{
   
     private TipoConsultas diagnostico = TipoConsultas.Diagonostico;
    private TipoConsultas exames = TipoConsultas.Exame;
    private TipoConsultas descSD = TipoConsultas.DescontoSD;
    private TipoConsultas descSE = TipoConsultas.DescontoSE;
    private TipoConsultas descID = TipoConsultas.DescontoId;
    private TipoConsultas descR = TipoConsultas.DescontoR;

      
      private String diagn;
      private boolean exame;//random
    private double divida;
      
    
    //construtores
    public Diagnostico()
    {
        super();
        diagn = "diagnostico";
        exameRan();
        divida = 0.0;
    }


    
    //getter e setters
    public double getDivida() {
        return divida;
    }

    public void setDivida(double divida) {
        this.divida = divida;
    }

    //adquire aleatoriamente os exames (true ou false)
    public boolean exameRan()
    {
        Random rand = new Random();
        int cons;
        cons = rand.nextInt(2);
        if(cons == 1)
        {
           exame = true;
        }
        else exame = false;
        
        return exame;
    }

    //  retorna true se tem consulta no dia atual
    public boolean temCon(Calendario cal,Consultas c)
    {
        boolean tC=false;
        if (cal.getDia() == c.getDia() && cal.getMes() == c.getMes())
        {
            tC=true;
        }
        return tC;
    }

    //executa a consulta de diagnostico, e ve os descontos 
  public boolean executaConsultaD(Utente u, Consultas c)
  {
        boolean exam;
         divida = 0.0;
        boolean consultaExam=false;

            for(Utente i : u.getUtentes())
            {
                if(i.getNumUtente() == c.getUtente())
                    u = i;
            }

            if (u.getSeguro() && u.getIdade() < 65) {
                divida += diagnostico.getPreco() * descSD.getPreco();
            }
            else {
                divida += diagnostico.getPreco();
            }

            if (u.getIdade() > 65) {
                divida = diagnostico.getPreco() * descID.getPreco();
            }

            if (u.getRecomendou()) {
                divida += diagnostico.getPreco() * descR.getPreco();
            }


            exam = exameRan();
              if(exam)
                 consultaExam=true;
              else
                  consultaExam=false;
              
      return consultaExam;

  }

  //imprime o recibo, ou seja, imprime o pagamento e o utente que tem que pagar
    public String imprimeRecibo(Utente u, int nUtente, int tC) {

        String info="";
        for(Utente i : u.getUtentes()) {
            if(i.getNumUtente()== nUtente)
            {
                info = "------------------------ \n" + "Recibo \n" + "Centro Médico \n";
                info += "NºUtente " + i.getNumUtente() + "\n" + "Total: " + i.getDivida();
                info += "\nObrigado! \n" + "Volte Sempre! \n" + "------------------------ \n";
            }
        }
        return info;
    }

    //pagamento das consultas de diagnostico
    public void pagamentoD(Utente u, int nUtente) {

        for (Utente i : u.getUtentes())
        {
            if (i.getNumUtente() ==nUtente)
            {
                i.setDivida(0);
            }
        }

    }

    //executa as consultas de resultados, e ve os descontos
    public boolean executaConsultaR( Utente u, Consultas i)
    {

       for(Utente ut: u.getUtentes())
       {
           if (ut.getNumUtente() == i.getUtente())
               divida = ut.getDivida();
             u=ut;
       }
        boolean consultaExecutada;


        if (u.getSeguro() && u.getIdade() < 65) {
            divida += exames.getPreco() * descSE.getPreco();
            consultaExecutada = true;
        }
        else {
            divida += exames.getPreco();
            consultaExecutada = true;
        }

        if (u.getIdade() > 65) {
            divida = exames.getPreco() * descID.getPreco();
            consultaExecutada = true;
        }

        if (u.getRecomendou()) {
            divida += exames.getPreco() * descR.getPreco();
            consultaExecutada = true;
        }


        return consultaExecutada;

    }
}
