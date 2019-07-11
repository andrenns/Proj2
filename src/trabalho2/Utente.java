/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho2;
import java.util.Objects;
import java.util.Random;
import java.util.ArrayList;
import java.io.*;                                                   // bibliotecas
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

public class Utente {

    private boolean aux;
    private String nome;
    private int idade;
    private int numUtente;                               //encapsulamento
    private double divida;
    private boolean seguro;
    private boolean recomendou;
    private ArrayList<Utente> utentes;
    private String str = "merda";
    
    
     private TreeMap<Double, Medico> listaAvaliacao; // lista para guardar os medicos com as avaliaçoes
    
    //Construtores
public Utente()
{
    divida = 0.0;
    nome= "";
    numUtente=0;
    utentes = new ArrayList<Utente>();
    listaAvaliacao = new TreeMap<>();
    
}


    public Utente(String nome, int idade, double divida, boolean seguro, boolean recomendou)
    {

        this.nome = nome;
        this.idade = idade;
        this.divida = divida;
        RandomNumUtente();
        this.seguro=seguro;
        this.recomendou=recomendou;
        utentes = new ArrayList<Utente>();
        listaAvaliacao = new TreeMap<>();
    }
    
        public Utente(String nome, int idade, int numUtente, double divida, boolean seguro, boolean recomendou)
    {

        this.nome = nome;
        this.numUtente = numUtente;
        this.idade = idade;
        this.divida = divida;
       // RandomNumUtente();
        this.seguro=seguro;
        this.recomendou=recomendou;
        utentes = new ArrayList<Utente>();
        listaAvaliacao = new TreeMap<>();
    }
        
         // metodo que insere o novo medico na lista de avaliacoes
    public void insereMedico(Medico m, double avaliacao, Utente u)
    {
        Medico ms = new Medico();
         double a = 0;
         double ax = 0;
                if(listaAvaliacao.containsValue(m) == true)//se existir um medico na lista
                 {
                    for(Medico i : listaAvaliacao.values()) //percorre a lista de avaliaçoes (TreeMap)
                     {
                        ms=i;
                     }
                         if(ms == m) //se o medico for igual ao medico inserido:
                         {
                             if(ms.getNumero() == m.getNumero()) //se o numero de medico for igual ao medico 
                             {
                                 a = ms.getAvaliacao();
                             }
                             ax = m.mediaAvaliacao(m, avaliacao, u); // vai ao medico e realiza a funçao nos medicos
                              listaAvaliacao.put(ax,m);    //insere o medico com a key(media)
                              listaAvaliacao.remove(a); // remove o medico com o mesmo key
                         }
                }
            else
            {
                m.setAvaliacao(avaliacao); //caso o medico nao for igual ira inserir lo na lista
                listaAvaliacao.put((m.getAvaliacao()), m);
                
            }
    }
 
    
    //metodo que verifica se existe um medico no TreeMap
    
    public boolean existeMedico(double a)
    {
        return listaAvaliacao.keySet().contains(a);
    }
    
    
    //remove o medico da lista 
    
    public void removeMedico(double a)
    {
        listaAvaliacao.remove(a);
    }
    
    
    //Getters e setters

    public TreeMap<Double, Medico> getListaAvaliacao()
    {
        return listaAvaliacao;
    }
    
    public void imprimeMedicoAvali()
    {
        System.out.println(listaAvaliacao);
    }
    
    //set do TreeMap
    public TreeSet<Double> Avalicao()
    {
        TreeSet<Double> aval = new TreeSet<Double>();
        
        for(Medico m : listaAvaliacao.values())
        {
            aval.add(m.getAvaliacao());
        }
        return aval;
    }    
    
    /////////////////////////////////  
                
    //Getters e Setters
    
    public double getDivida() {
        return divida;
    }

    public void setDivida(double divida) {
        this.divida = divida;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getNumUtente() 
    {
        return numUtente;
    }

    public void setNumUtente(int numUtente) 
    {
        this.numUtente = numUtente;
    }
    
     public ArrayList<Utente> getUtentes() 
    {
        return utentes;
    }

    public boolean getSeguro()
    {

        return seguro;

    }

    public boolean getRecomendou() {
        return recomendou;
    }

    public void setRecomendou(boolean recomendou) {
        this.recomendou = recomendou;
    }

    public void setUtentes(ArrayList<Utente> utentes)
    {
        this.utentes = utentes;
    }
    
       public boolean getAux()
   {

       return aux;

   }
       
       
    public boolean getSeguro(int nUtente) //
   {
       boolean aux=false;
       for(Utente u: utentes) //percorre a lista de utente 
       {

           if(u.numUtente==nUtente) // caso o numero de utente for igual ao numero de utente da lista 
           {                        // o aux será igual ao seguto do utente da lista

               aux = u.seguro; 

           }

       }

       return aux;

   }

    //------------------------------------------------------------------------
    
    //random number para o numero unico 
    public void RandomNumUtente()
    {
        Random rand = new Random();
        int nUtente;    
        nUtente = rand.nextInt(10000);//gerar um numero de 0 a 9999 aleatoriamente
        numUtente = nUtente + 1;
    }
    
    
    //add Utente a lista
    public void addUtente(Utente utente) 
    {
        utentes.add(utente);
    }
    
    //toString
    public String toString()
    {
        String info;
        info = "nome: " + nome + "\n";
        info += "idade: " + idade + "\n";
        info += "numero de Utente: " + numUtente+ "\n";
        if(seguro)
        info += "Utente possui seguro! " + "\n";
        if(!seguro)
            info+="Utente não possui seguro! " + "\n";
        if(recomendou)
        {
            info+="Utente com direito a desconto de 5% pois recomendou outros utentes!"+"\n";
        }
        if(divida != 0)
        info+= "O utente tem " + divida + " por pagar!";

        return info;
    }
    
    
    //mostra a lista de utentes do centro medico
    public String listaUtentes()
    {
        String info;
        info = "Lista Utentes: \n";
        for(Utente i : utentes)
            info += i + "\n";
        return info;

    }


   public void verificaUtente(int numUtente) // verifica se o numero de utente é igual
   {

       aux=false;
       for(Utente i : utentes)
       {
           if(i.getNumUtente() == numUtente) // caso seja igual o aux ficara a true
           aux = true;
       }

   }

    //funçao de le o bloco de notas 
public void leFicheiros(Utente u)
    {
        try{
            
        //criar e abrir a stream 
        FileReader inStream = new FileReader("Utente.txt");
        BufferedReader br = new BufferedReader(inStream);
          
        //ciclo de leitura do ficheiro
        String linha = br.readLine();//le uma linha          

        
        while(linha != null) // ciclo enquanto a linha nao for vazia no bloco de notas
        {                    
            nome = linha;
            
            linha = br.readLine();
            
            idade = Integer.parseInt(linha);

            linha = br.readLine();
            
            numUtente = Integer.parseInt(linha);
            
            linha = br.readLine();
            
            divida = Double.parseDouble(linha);
            
            linha = br.readLine();
            
            seguro = Boolean.parseBoolean(linha);

            linha= br.readLine();

            recomendou = Boolean.parseBoolean(linha);
            
            linha = br.readLine();
            linha = br.readLine();
            
            Utente u2 = new Utente(nome, idade, numUtente, divida, seguro , recomendou);
            u.addUtente(u2); // adiciona na lista de utentes os parametros

        }
        
        br.close(); 
        }
        catch(IOException e) // exceçao no caso do ficheiro der erro
        {                                       
            System.out.println("Erro devido a inexistência do ficheiro, ou devido a um erro do IOException ");
        }
    }

//escreve no bloco de notas a lista de utentes
    public void escreveFicheiro(Utente u)
    {

        try {
            //stream de escrita
            FileWriter outStream = new FileWriter("Utente.txt");
            BufferedWriter bW = new BufferedWriter(outStream);
            PrintWriter out = new PrintWriter(bW);

            //ciclo escrita
            for(Utente i : u.getUtentes())
            {
                out.println(i.nome);
                out.println(i.idade);
                out.println(i.numUtente);
                out.println(i.divida);
                out.println(i.seguro);
                out.println(i.recomendou);
                out.println();
            }

            out.close();


        } catch (IOException e) {
            System.out.println("Erro ao escrever ficheiro");
        }

    }

}

