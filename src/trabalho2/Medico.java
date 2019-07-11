/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho2;

import java.util.ArrayList;
import java.io.*;                               //bibliotecas
import java.util.*;

import java.lang.Integer;
import java.util.TreeMap;
import java.util.TreeSet;

public class Medico {
    
    private double avaliacao;
    private int numero;                         //encapsulamento
    private String especialidade;
    private ArrayList<Medico> medico;
    private boolean aux;
    
    
    //Construtor
     public Medico() 
     {
        numero = 0;
        especialidade = " ";
        medico = new ArrayList<Medico>();
        avaliacao = 0;
    }
     
     
     //overloading do construtor
    public Medico(int numero, String especialidade)
    {
        this.numero = numero;
        this.especialidade = especialidade;
        avaliacao = 0;
        medico = new ArrayList<Medico>();
    }   

    //realiza a media entre as avaliaçoes, com os KYES do TreeMap
    public double mediaAvaliacao(Medico m, double avaliacao1, Utente u)
    {
        double a =0;
        for(Medico i : u.getListaAvaliacao().values()) //percorre o TreeMap (lista de avaliacoes)
        {           
            if(i == m)  //verifica se o medico da lista é igual ao medico introduzido 
            {
                a = avaliacao = (m.getAvaliacao() + avaliacao1)/2;   // caso seja igual a avaliaçao será igual a media feita com a
            }                                                         // avaliaçao (key do treeMap)
        }
        return a;
    }
    
    //Getters e Setters
    
    public void setAvaliacao(double avaliacao)
    {
        this.avaliacao = avaliacao;
    }
        
    public double getAvaliacao()
    {
        return avaliacao;
    } 
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade)
    {
        this.especialidade = especialidade;
    }
    
    public ArrayList<Medico> getMedico() {
        return medico;
    }

    public void setMedico(ArrayList<Medico>medico) {
        this.medico = medico;
    }

       public boolean getAux()
   {

       return aux;

   }
    
       
     public String getEspecialidade(int nmedico)
    {
        String info="";
        for(Medico i: medico)
        {

            if(nmedico == i.getNumero())
            {
                info += i.especialidade;
            }

        }
       return info;
    }
    //------------------------------------------------------------------------
//verificaçao do numero do medico na lista 
     public void verificaMedico(int numMedico)
   {
       aux=false;
       for(Medico i : medico) // percorre a lista dos medicos
       {
           if(i.getNumero() == numMedico) //se o numero do medico da lista for igual ao numero do medico atual 
           aux = true;                     // o aux será igual a true
       }

   }

        public boolean equals (Object Obj) //overriding do metodo equals
    {  
       if(this==Obj)
           return true;
       if(Obj == null)
           return false;
       if(this.getClass()!= Obj.getClass())
           return false;
       Medico m = (Medico)Obj;
      return numero == m.getNumero(); // verificaçao se o numero de medico é igual a um dado objeto
    }


    //toString 
    public String toString()
    {
        String info;
        info = "numero: " + numero + "\n";
        info +="Especialidade do Medico: " + especialidade + "\n";
        return info;

    }

    //adiciona o medico a lista de medicos
    public void addMedico(Medico medicos) 
    {

        medico.add(medicos); 

    }
    
    //mostra a lista de medicos do centro medico
    public String listaMedicos()
    {

        String info;
        info = "Lista Medicos: \n";
        for(Medico i : medico)
            info += i + "\n";
        return info;

    }

    
    //leitura de ficheiros
    public void leFicheiros(Medico m)
    {
        
        try{
        //criar e abrir a stream 
        FileReader inStream = new FileReader("Medico.txt");
        BufferedReader br = new BufferedReader(inStream);
        
        //ciclo de leitura do ficheiro
        String linha = br.readLine();//le uma linha 
        
          while(linha != null) // enquanto o ficheiro nao tiver uma linha vazia, irá introduzir nos parametros as linhas desejadas
          {
                numero = Integer.parseInt(linha);
            
                linha = br.readLine();
            
                especialidade = linha;
                
                Medico m1 = new Medico(numero, especialidade);
                
                m.addMedico(m1);

                linha = br.readLine();
                linha = br.readLine();
          }
        br.close(); 
        }
        catch(IOException e) // exceçao dos erros que podera dar em relaçao a leitura de dados (IOException)
        {
            System.out.println("Erro devido a inexistência do ficheiro, ou devido a um erro do IOException ");
        }
    }

    //escreve no bloco de  notas os medicos na lista de medicos
    public void escreveFicheiro(Medico m)
    {

        try {
            //stream de escrita
            FileWriter outStream = new FileWriter("Medico.txt");
            BufferedWriter bW = new BufferedWriter(outStream);
            PrintWriter out = new PrintWriter(bW);

            //ciclo escrita
            for(Medico i : m.getMedico())
            {
                out.println(i.numero);
                out.println(i.especialidade);
                out.println();
            }

            out.close();


        } 
        catch (IOException e) 
        {
            System.out.println("Erro ao escrever ficheiro");
        }
    }

}
