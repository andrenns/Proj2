package trabalho2;


import java.util.*;

public class Menu {


    //reconhecer o teclado
    private Scanner scan = new Scanner(System.in);

    //criar instancias dos arrays list
    private Calendario calendario = new Calendario();
    private Utente u = new Utente();
    private Medico m = new Medico();
    private Consultas con = new Consultas();
    private Diagnostico d = new Diagnostico();

    private int pW;

    private boolean aux;


    public Menu() {

            
        pW = 55555; //Palavra-passe para opçoes administrativas
        //le os utentes,medicos e consultas dos ficheiros
        u.leFicheiros(u);
        m.leFicheiros(m);
       
        con.leFicheiros(con, m , calendario);

    }
//password de acesso ao menu acoes administrativas
    public void setpW(int pW) {
    try
    {
        this.pW = pW;
    }
          catch(InputMismatchException e)
          {
                System.out.println("Erro insira um numero!!");
          }
    }

    public int getpW() {

        return pW;

    }

    //menu que aparece quando nos enganmos na introducao de um valor
    private void subMenuCancelar()
    {
        

        boolean sair = false;
        boolean ok = false;
        int opcoes = 0;

        
        do {
            System.out.println("1- Cancelar" + "\n" + "2- Tentar Novamente" +
                    "\n" );
            while(ok == false){
            try{
           
            ok = true;
            opcoes = scan.nextInt();
            }
            catch(InputMismatchException e)
            {
                System.out.println("opção invalida!!!");
            }
            }
            ok = false;
            switch (opcoes) {

                case 1:
                    try{
                    menuPrincipal();
                    }
                    catch(InputMismatchException e)
                    {
                        System.out.println("insira um numero!!! ");
                    }
                    break;

                case 2:
                    aux= false;
                    
                    sair=true;
            }

        }
        while(!sair);
    }

  //sub menu mostrado se nesse dia tiver consultas a executar     
    private void subMenuConsulta()
    {
         boolean sair = false;
         boolean ok1 = false;
        int opcoes=0;
        
        do {
            while(ok1 == false){
            try{
            System.out.println("\n1- Executar Consultas do dia \n" + "0- Voltar ao menu anterior \n");
            System.out.println("Insira Opçao: ");
            ok1 = true;
            opcoes = scan.nextInt();
            }
            catch(InputMismatchException e)
            {
                System.out.println("opção invalida!!!");
            }
            }
            ok1 = false;
            switch (opcoes) {
                case 1:
                    boolean cEx = true;
                    int dia;
                    int mes;
                    boolean tC=true;
                    boolean tipoC = false;
                    int semana = 0;
                    String dS;
                    Consultas auxC = con;
                    double avaliacao;

        while(tC) {
                    tC=false;
                    for (Consultas c : con.getConsulta())
                    {

                        if(c.getDia() == calendario.getDia() && c.getMes()==calendario.getMes())
                        {

                                tC=true;
                                tipoC = c.getTipoC();
                             
                                auxC=c;
                        }

                    }
                    
                    if(tC && !tipoC) {

                        cEx = d.executaConsultaD(u, con);
                        for(Utente i : u.getUtentes())
                        {
                            if(auxC.getUtente() == i.getNumUtente())
                            {
                                i.setDivida(d.getDivida());
                            }
                        }
                     
                        if(!cEx && !tipoC)
                        {
                            System.out.println("Utente apenas realizou consulta de diagnostico \n");
                            System.out.println(d.imprimeRecibo(u,auxC.getUtente(), 1));                                                   
                            System.out.println("\n Executar Pagamento \n" + "1 - Ok");
                            int ok=scan.nextInt();
                            aux=false;
                            while(!aux)
                            {
                                if (ok == 1) {
                                    d.pagamentoD(u, auxC.getUtente());
                                    aux = true;
                                } else {
                                    System.out.println("Insisra Opcão Válida!");

                                }
                            }
                            con.removeC(auxC);
                            
                            System.out.println("Avaliacao do Medico: ");
                            avaliacao = scan.nextDouble();      
 
                                 for(Medico md : m.getMedico())
                                 {
                                     if(auxC.getMedico() == md.getNumero())
                                     {
                      
                                        u.insereMedico(md, avaliacao, u);

                                     }
                                 }
                            
                        }
                        if (cEx && !tipoC) {

                            System.out.println("Utente nº" + auxC.getUtente() + " necessita fazer exames \n" +
                                    "Escolha uma data para a marcação da consulta de resultados!!"
                                    + "\n" + "Nota: Apenas poderá ser marcada " +
                                    "após 7 dias \n");
                            aux = false;
                            while (!aux) {

                                System.out.println("Insira dia: ");
                                dia = scan.nextInt();
                                System.out.println("Insira mês: ");
                                mes = scan.nextInt();
                                boolean teste = calendario.verSeteDias(calendario, dia, mes);

                                semana = con.calculaSemana(dia, mes);
                                dS = calendario.calculaDiaSemana(dia, mes);


                                if (!teste) {
                                    System.out.println("Apenas poderá ser marcada após 7 dias!! \n" + "Tente outra data!");
                                    aux = false;
                                } else {

                                    Consultas con2 = new Consultas(auxC.getUtente(), dia, mes, auxC.getMedico(),
                                            auxC.getEspecialidade(), "Resultados", semana, dS, auxC.getSeguro(), true);
                                    con.addConsultas(con2);
                                    con.removeC(auxC);
                                    aux = true;

                                }
                            }
                        }

                    }

            if(tipoC && tC)
            {
                System.out.println("Utente realizou consulta de diagnostico, exame e consulta de resultados! \n");
                d.executaConsultaR(u, con);
                 for(Utente i : u.getUtentes())
                        {
                            if(auxC.getUtente() == i.getNumUtente())
                            {
                                i.setDivida(d.getDivida());
                            }
                        }
                System.out.println(d.imprimeRecibo(u,auxC.getUtente(), 1));
                System.out.println("\n Executar Pagamento \n" + "1 - Ok");
                int ok=scan.nextInt();

                aux=false;
                while(!aux)
                {
                    if (ok == 1) {
                        d.pagamentoD(u, auxC.getUtente());
                        con.removeC(auxC);
                        aux = true;
                    } else {
                        System.out.println("Insisra Opcão Válida!");
                    }
                }

                 System.out.println("Avaliacao do Medico: ");
                 avaliacao = scan.nextDouble();    

                                 for(Medico md : m.getMedico())
                                 {
                                     if(auxC.getMedico()==md.getNumero())
                                     {
                                        u.insereMedico(md, avaliacao, u);

                                     }
                                 }

            }

                }

                   menuPrincipal();

                    break;
                case 0:
                    menuPrincipal();
                    sair = true;
                    break;

            }
            }
        while(!sair);
            }
    
   //submenu que faz a criacao de utentes e medicos 
    private void subMenu1 () 
    {
         boolean sair = false;
         boolean ok = false;
        int opcoes=0;

        do {
            System.out.println("1- Criar Novo Utente" + "\n" + "2- Criar Novo Médico" +
                    "\n" + "3- Lista de Utentes" + "\n" + "4- Lista de Médicos" + "\n" +
                    "5-Alterar palavra-passe" + "\n" + "6- lista de Medicos ordenados por avaliaçao" + "\n"  + "\n" +"0- Voltar ao menu anterior" + "\n");
            
            while(ok == false){
            try{
            System.out.println("Insira Opçao: ");
            opcoes = scan.nextInt();
            ok = true;
            }
            
              catch(InputMismatchException e)
            {
                System.out.println("opção invalida!!!");
                scan.nextLine();
            }
        }
            ok = false;
            
            switch (opcoes) {

                case 1:
                    String nome;
                    int nUtente;
                    int idade;
                    int aSeguro;
                    double divida = 0.0;
                    boolean seguro = false;
                    boolean recomendou = false;
                    int aRecomendou;
                    aux = true;

                    System.out.println("Insira nome de utente: ");
                    nome = scan.next();
                    System.out.println("Insira idade de utente: ");
                    idade = scan.nextInt();
                   while(aux) {
                       System.out.println("O utente possui seguro de saúde? \n" + "1- Sim  " + "2-Não \n");
                       aSeguro = scan.nextInt();
                       if (aSeguro == 1)
                       { seguro = true;
                       aux=false;}
                       if (aSeguro == 2)
                       {seguro = false;
                       aux=false;}
                       if (aSeguro != 1 && aSeguro != 2)
                       {
                           System.out.println("Opção Invalida!");
                            aux=true;
                       }

                   }



                        System.out.println("Foi recomendado por algum utente?\n" + "1- Sim  " + "2-Não");
                        aRecomendou = scan.nextInt();

                    aux=true;
                    while(aux) {
                        if (aRecomendou == 1)
                        {
                            System.out.println("Insira o numero de utente que fez a recomendação");
                            nUtente = scan.nextInt();

                            u.verificaUtente(nUtente);
                            aux=u.getAux();

                            if(aux)
                                {
                                    for(Utente u : u.getUtentes())
                                    if(u.getNumUtente() == nUtente)
                                        u.setRecomendou(true);
                                    aux = false;
                                }
                                else
                                {
                                    System.out.println("User inexistente! \n");
                                    System.out.println("Deseja tentar novamente?\n" + "1- Sim  " + "2-Não ");
                                    int tent = scan.nextInt();

                                    if (tent == 1)
                                    {
                                        aux = true;
                                    }
                                    if(tent == 2)
                                    {
                                        aux = false;
                                    }
                                    if (tent != 1 && tent != 2)
                                    {
                                        System.out.println("Opção Invalida!");
                                        aux = true;
                                    }

                                }
                            }

                        if (aRecomendou == 2) {
                            aux = false;
                        }
                        if (aRecomendou != 1 && aRecomendou != 2) {
                            System.out.println("Opção Invalida!");
                            aux = true;
                        }
                    }


                   aux = true;
                    while (aux){     //ASSIM NUNCA DA NUMEROS REPETIDOS
                        Utente u2 = new Utente(nome, idade, divida, seguro, recomendou);

                        u.verificaUtente(u2.getNumUtente());
                        aux = u.getAux();
                        if(!aux){
                            u.addUtente(u2);
                        }
                    }
                    break;

                case 2:
                    String especificacao;
                    int numero;
                    aux =true;
                    Scanner scan2 = new Scanner (System.in);
                    System.out.println("Insira numero do medico: ");
                    numero = scan2.nextInt();
                    System.out.println("Insira especialidade do medico: ");
                    especificacao = scan2.next();

                    while (aux){
                        Medico m2 = new Medico(numero, especificacao);

                        m.verificaMedico(m2.getNumero());
                        aux = m.getAux();
                        if(!aux){
                            m.addMedico(m2);
                        }
                    }
                    break;

                case 3:
                   System.out.println(u.listaUtentes());

                    break;

                case 4:
                    //mostra a lista dos medicos
                    System.out.println(m.listaMedicos());

                    break;
                case 5:
                    int pW;
                    System.out.println("Insira a password nova: ");
                    pW=scan.nextInt();
                    setpW(pW);
                    break;
                case 6:
                    
                    u.imprimeMedicoAvali();
                    
                    break;
                case 0:
                    menuPrincipal();
                    sair = true;
                    break;


            }

        }
        while(!sair);
    }

// menu apresentado quando é iniciado o programa
    public void menuPrincipal(){
        int pass;
        boolean sair = false;
        boolean ok = false;
        int opcoes=0;

        do {
            System.out.println(calendario + "\n");
            System.out.println("1- Ações administrativas" + "\n" + "2- Dia seguinte" + "\n" +
                    "3- Marcar Consulta" + "\n" + "4- Ver Consultas" + "\n" + "\n" + "0- Sair do Programa" + "\n");
            while(ok == false){
            try{
            System.out.println("Insira Opçao: ");
            opcoes = scan.nextInt();
            ok = true;
            }
            
              catch(InputMismatchException e)
            {
                System.out.println("opção invalida!!!");
                scan.nextLine();
            }
        }
            ok = false;
            switch (opcoes) {

                case 1:
                    aux = false;
                    
                    pass = 0;
                    
                    while (!aux) {
                        
                       while(ok == false){
                    
                        try{
                        System.out.println("Insira palavra passe: ");
                        pass = scan.nextInt();
                        ok = true;
                        }
                          catch(InputMismatchException e)
                        {
                            System.out.println("opção nao valida!!! insira uma opção valida ");
                            scan.nextLine();
                        }
                        }
                    
                        if (pass == pW) {
                            subMenu1();
                            aux = true;
                        } else {
                            System.out.println("Password incorreta!!");
                            subMenuCancelar();
                        }
                     }     
       
                    break;
                    
                case 2:
                    System.out.println("Dia Seguinte! \n");
                    calendario.avançaCalendario();
                    System.out.println(con.diaConsulta(calendario));
                    if(con.getTemCon())
                    {
                        subMenuConsulta();
                    }
                    break;

                case 3:
                    System.out.println("Consulta: ");
                    int dia = 0;
                    int mes = 0;
                    int nmedico = 0;
                    int nutente = 0;
                    int semana = 0;
                    boolean seguro=false;
                    String diaSemana="";
                    String esp="";
                    String tipo_consulta;
                    aux = false;
           
                    while (!aux) {
                        System.out.println("Insira o numero de utente: ");
                        nutente = scan.nextInt();
                        u.verificaUtente(nutente);
                        aux = u.getAux();
                        if (!aux) {

                            System.out.println("Utente inixistente!!");
                            subMenuCancelar();
                        }
                    }
                    aux = false;

                    while (con.getlimitaConsulta() < 5) {

                        while (!aux) {
                            System.out.println("Insira o numero de medico que deseja fazer a consulta: ");
                            nmedico = scan.nextInt();
                            esp = m.getEspecialidade(nmedico);
                            m.verificaMedico(nmedico);
                            aux = m.getAux();

                            if (!aux) {

                                System.out.println("Médico inixistente!!");
                                 subMenuCancelar();
                            }

                        }
                        aux = false;
                        
                            while (con.getlimitaConsulta1() < 4) {
                        System.out.println("Insira dia: ");
                        dia = scan.nextInt();
                        System.out.println("Insira mes: ");
                        mes = scan.nextInt();
                        semana = con.calculaSemana( dia, mes);
                       
                        diaSemana= calendario.calculaDiaSemana(dia,mes);
                        con.limConMedico(dia, mes, nmedico);                       
                          con.limConUtente(dia, mes, nutente, semana);
                      if(con.getlimitaConsulta1() < 4)
                      {
                       con.setLimitaConsulta1(5);
                      }
                      else 
                     {
                       con.setLimitaConsulta1(0);
                       System.out.println("Utente não pode marcar mais consultas neste dia" + "\n" + "Selecione outro dia");
                       aux = false;
                        subMenuCancelar();
                        }

                        if (con.getlimitaConsulta() < 5) {
                            con.setLimitaConsulta(6);

                        } else {
                            con.setLimitaConsulta(0);
                            System.out.println("Médico com dia preenchido" + "\n" + "Selecione outro dia");
                             subMenuCancelar();
                            aux = false;
                        }
                    }
                     }
                     con.setLimitaConsulta1(0);
                     con.setLimitaConsulta(0);

                    tipo_consulta = "diagnóstico";

                    seguro = u.getSeguro(nutente);

                    Consultas con2 = new Consultas(nutente, dia, mes, nmedico, esp , tipo_consulta,  semana  ,diaSemana, seguro, false);

                    con.addConsultas(con2);
                    con.setSemana(0);
                     
                    break;

                case 4:
                    
                        System.out.println(con.listaConsulta());

                    break;

                case 0:
                    con.escreveFicheiro(con);
                    m.escreveFicheiro(m);
                    u.escreveFicheiro(u);
                    System.out.println("  OBRIGADO!");
                    System.exit(0);
                    sair = true;
                    break;

            }

        }
        while (!sair);
    
    }
}




