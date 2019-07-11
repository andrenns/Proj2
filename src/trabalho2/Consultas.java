/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho2;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;
import java.lang.Math;
import java.util.Objects;
import java.util.Random;

public class Consultas {

    private Meses jan = Meses.Janeiro;
    private Meses fev = Meses.Fevereiro;
    private Meses mar = Meses.Março;
    private Meses abr = Meses.Abril;
    private Meses mai = Meses.Maio;
    private Meses jun = Meses.Junho;
    private Meses jul = Meses.Julho;                    //inicializaçao dos meses 
    private Meses ago = Meses.Agosto;
    private Meses set = Meses.Setembro;
    private Meses out = Meses.Outubro;
    private Meses nov = Meses.Novembro;
    private Meses dez = Meses.Dezembro;
    private int dia;
    private int mes;
    private int medico;
    private int nUtente;
    private int limitaConsulta;
    private int limitaConsulta1;
    private int semana;                             
    private String especialidade;
    private String diaSemana;
    private String tipoConsulta;
    private boolean tipoC;
    private boolean cE;

    private ArrayList<Consultas> consulta;// lista de consultas
    private boolean seguro;
    private  Boolean temCon;

   
    
    
    public Boolean getTemCon() {
        return temCon;
    }

    public void setTemCon(Boolean temCon) {
        this.temCon = temCon;
    }

    //Construtor
    public Consultas() {
        dia = 0;
        mes = 0;
        limitaConsulta= 0 ;
        limitaConsulta1 = 0;
        medico=0;
       especialidade = "";
        tipoConsulta="";
        nUtente=0;
        consulta = new ArrayList<Consultas>();
        semana = 0;
        tipoC=false;
        diaSemana="";
        seguro = false;
        cE=false;

    }
    
    public Consultas(int nUtente, int dia, int mes, int medico, String especialidade, String tipoConsulta, int semana, String diaSemana, boolean seguro, boolean tipoC) {
        this.nUtente = nUtente;
        this.dia = dia;
        this.mes = mes;
        this.tipoConsulta=tipoConsulta;
        this.medico=medico;
        this.especialidade=especialidade;
        this.semana=semana;
        this.diaSemana=diaSemana;
        this.seguro=seguro;
        this.tipoC=tipoC;
        cE =false;
        consulta = new ArrayList<Consultas>();

    }
    
    
    //getter setters
    public boolean getcE() {
        return cE;
    }

    public void setcE(boolean cE) {
        this.cE = cE;
    }

    public ArrayList<Consultas> getConsulta() {
        return consulta;
    }

    public boolean getTipoC() {
        return tipoC;
    }

    public void setTipoC(boolean tipoC) {
        this.tipoC = tipoC;
    }

    public void setConsulta(ArrayList<Consultas> consulta) {
        this.consulta = consulta;
    }
    public void removeC(Consultas c)
    {
        consulta.remove(c);
    }

    public int getDia() { return dia; }

    public int getUtente()
    {return nUtente;}

    public int getMedico() {
        return medico;
    }

    public void setMedico(int medico) {
        this.medico = medico;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public void setDia(int dia) { this.dia = dia; }

    public int getMes() { return mes;}

    public void setMes(int mes) { this.mes = mes; }

    public int getSemana() {
        return semana;
    }

    public void setSemana(int semana) {
        this.semana = semana;
    }
    

    public void setLimitaConsulta(int limitaConsulta)
    {
        this.limitaConsulta = limitaConsulta;
    }

    public int getlimitaConsulta()
    {
        return limitaConsulta;
    }

    public void setLimitaConsulta1(int limitaConsulta1)
    {
        this.limitaConsulta1 = limitaConsulta1;
    }

    public int getlimitaConsulta1()
    {
        return limitaConsulta1;
    }
    
    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }
    
    //adiciona na listas as consultas
    public void addConsultas(Consultas consultas)
    {

        consulta.add(consultas);

    }
    
    //getter setters
    public boolean getSeguro() {
        return seguro;
    }

    public void setSeguro(boolean seguro) {
        this.seguro = seguro;
    }
    
    
    //toString
    public String toString() {

       String info=  "Médico nº " + medico  + ": " + "\n" + "Especialidade: "+ especialidade +"\n" + "\n" +
                "Utente nº " + nUtente +
                " tem consulta" + diaSemana + ", dia " + dia +
                " de " ;
        for(Meses i: Meses.values())//percorre a enumeraçao dos meses 
            if(i.ordinal() == mes - 1) 
                info += i;
        info += " na semana " + semana +
                ". " + "\n" +
                "Consulta de " + tipoConsulta + "\n";


        if(seguro = true)
        {
            info+="Utente contem seguro." + "\n";
        }

       return info;
    }

    //mostra a lista de consultas do centro medico
        public String listaConsulta()
    {

        String info;
        info = "Lista consultas: \n";
        
        for(Consultas i : consulta)
            info += i + "\n";
        
        return info;

    }
     
        //limatador dos medicos
        public void limConMedico(int dia, int mes, int nMedico)
        {
            limitaConsulta=0; // contador
            for(Consultas i :consulta)
            {
                if(i.dia == dia && i.mes == mes)
                {
                    if(i.medico == nMedico)
                    {
                        limitaConsulta++;//incrementa o contador sempre que o dia e o mes sejam iguais ao mes e dia da consulta
                    }
                }
            }

        }
        
        //limitador de consultas para os utentes
      public void limConUtente(int dia, int mes, int nUtente, double semana)
        { 
            for (Consultas i: consulta){
                
                if (i.getSemana() == semana)
                    if(i.nUtente == nUtente){
                        limitaConsulta1++;//se o numero de utente e a semana da consulta for igual entao incrementa o contador(limitaConsulta1)
                    }
            }

        }
      


//metodo que calcula a semana do mes em que se ta
   public int calculaSemana(int dia, int mes)//
   {
        semana=0;
        int aux;
     for(Meses i: Meses.values())//percorre a lista dos meses
     {
         
       if(i.ordinal() < mes-1) //verifica se o numero do mes (ex. abril(31)) for inferior ao mes introduzido 
         {
             semana += i.getDias();
         }
     }
       aux = (semana + dia + 1 )%7;
       semana =(semana + dia + 1 )/7;
     if( aux !=0)
         semana += 1 ;

     return semana ;
   }

//metodo equals overriding
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consultas consultas = (Consultas) o;
        return getDia() == consultas.getDia() &&
                getMes() == consultas.getMes() &&
                medico == consultas.medico &&
                nUtente == consultas.nUtente;
    }

    //toString que da a informaçao que a consulta tem que ser realizada
    public String diaConsulta(Calendario c)
    {
            temCon=false;
        String info = " ";
        for(Consultas i : consulta)
        {
            if(c.getDia() == i.dia && c.getMes() == i.mes)
            {
                info += "Consulta a realizar, com Medico: " + i.medico + ", e Utente : " + i.nUtente + "\n";
                temCon=true;
            }
        }
        
        return info;
    }

//le o bloco de notas 
    public void leFicheiros(Consultas c, Medico m, Calendario cal)
    {
        try{
            //criar e abrir a stream
            FileReader inStream = new FileReader("Consultas.txt");
            BufferedReader br = new BufferedReader(inStream);

            //ciclo de leitura do ficheiro
            String linha = br.readLine();//le uma linha

            while(linha != null)
            {


                nUtente = Integer.parseInt(linha);

                linha = br.readLine();

                dia = Integer.parseInt(linha);

                linha = br.readLine();

                mes = Integer.parseInt(linha);

                linha = br.readLine();

                medico = Integer.parseInt(linha);


                for(Medico i : m.getMedico())
                        if(i.getNumero()==medico)
                especialidade = i.getEspecialidade();

                linha = br.readLine();

                semana = calculaSemana(dia,mes);

                diaSemana = cal.calculaDiaSemana(dia, mes);

                seguro = Boolean.parseBoolean(linha);

                linha = br.readLine();

                tipoC = Boolean.parseBoolean(linha);


                if(!tipoC)
                tipoConsulta = "Diagnostico";
                else
                    tipoConsulta = "Resultados";

                Consultas c1 = new Consultas(nUtente, dia ,mes, medico , especialidade, tipoConsulta, semana, diaSemana, seguro, tipoC);

                c.addConsultas(c1);
                linha = br.readLine();
                linha = br.readLine();
            }
            br.close();
        }
        catch(IOException e)
        {
            System.out.println("Erro devido a inexistência do ficheiro, ou devido a um erro do IOException ");
        }
    }

    
  //escreve no bloco de notas os elemetos da lista de consultas
    public void escreveFicheiro(Consultas c)
    {

        try {
            //stream de escrita
            FileWriter outStream = new FileWriter("Consultas.txt");
            BufferedWriter bW = new BufferedWriter(outStream);
            PrintWriter out = new PrintWriter(bW);

            //ciclo escrita
            for(Consultas i : c.getConsulta())
            {
                out.println(i.nUtente);
                out.println(i.dia);
                out.println(i.mes);
                out.println(i.medico);
                out.println(i.seguro);
                out.println(i.tipoC);
                out.println();
            }

            out.close();


        } catch (IOException e) {
            System.out.println("Erro ao escrever ficheiro");
        }

    }

}
