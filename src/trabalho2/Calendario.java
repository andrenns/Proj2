/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package trabalho2;
import java.util.ArrayList;

public class Calendario {

    private int dia;
    private int mes;
    private int ndias;
    private int dias;
    private boolean pode;
    private Meses jan = Meses.Janeiro;
    private Meses fev = Meses.Fevereiro;
    private  Meses mar = Meses.Março;
    private  Meses abr = Meses.Abril;
    private  Meses mai = Meses.Maio;
    private  Meses jun = Meses.Junho;
    private  Meses jul = Meses.Julho;
    private Meses ago = Meses.Agosto;
    private Meses set = Meses.Setembro;
    private  Meses out = Meses.Outubro;
    private  Meses nov = Meses.Novembro;
    private  Meses dez = Meses.Dezembro;
    private  DiaSemana dom = DiaSemana.Domingo;
    private  DiaSemana seg = DiaSemana.Segunda;
    private  DiaSemana ter = DiaSemana.Terça;
    private  DiaSemana qua = DiaSemana.Quarta;
    private  DiaSemana qui = DiaSemana.Quinta;
    private  DiaSemana sex = DiaSemana.Sexta;
    private  DiaSemana sab = DiaSemana.Sabado;
    private int ds; // nº dia da semana


    //Construtor
    public Calendario() {
        dia = 1;
        ds = 1;
        mes = 1;
        ndias = 0;
        dias = 1;
    }


    //getter and setters
    public int getDia() {
        return dia;
    }

    public boolean getPode() {
        return pode;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }


    // Avança um dia no calendario
    public void avançaCalendario() {
        for (Meses i : Meses.values()) //Percorre a lista dos valores de meses
        {
            if (i.ordinal() == mes - 1)
                if (dia == i.getDias()) {
                    mes += 1; //Se o dia chegar ao ultimo dia do mes, incrementa o mes e poe o dia a 0
                    dia = 0;
                }
        }
        dia += 1;

        if (ds <= 6) //Se ainda nao tiver chegado ao fim da semana, incrementa o dia de semana
        {
            ds++;
        }
        if (ds > 6) //caso contrario volta a 0
        {
            ds = 0;
        }
    }


    @Override
    public String toString() {
        String info;
        info = "Calendario: ";

        for (DiaSemana d : DiaSemana.values()) {
            if (d.ordinal() == ds) {
                info += d + ", "; //Mostra o dia atual
            }
        }
        info += +dia + " de ";

        for (Meses i : Meses.values())
            if (i.ordinal() == mes - 1)
                info += i; //Mostra o mês atual
        info += " de 2018";
        return info;
    }


    public String calculaDiaSemana(int dia, int mes) {
        int aux;
        aux = 0;
        String info = " ";
        ndias = 0;
        dias=1;
        for (Meses i : Meses.values()) {
            if (i.ordinal() < mes - 1) {
                ndias += i.getDias(); //Calcula o numero de dias nos meses ate o mes introduzido
            }
        }

        ndias = ndias + dia;  // ndias = numero de dias ate a data introduzida

        while (aux < ndias - 1) //Calcula o dia da semana (seg, ter, qua, ...)
        {
            aux++;
            if (dias <= 6) {
                dias++;
            }

            if (dias > 6) {
                dias = 0;
            }
        }

        for (DiaSemana d : DiaSemana.values()) {
            if (d.ordinal() == dias) {
                info += d; //info = dia da semana
            }
        }
        return info;
    }

    //funçao que verifica se ja passaram 7 dias desde a consulta de diagnostico
    public boolean verSeteDias(Calendario c, int dia, int mes)
    {
        int aux = 0;
      pode = false;

        aux = c.getDia() + 7;
        for (Meses i : Meses.values()) {
            if(mes == i.ordinal()+ 1) {
                if (aux > i.getDias()) {
                    aux = aux - i.getDias();
                    if (dia < aux && mes == c.getMes()) {
                        pode = false;
                    }
                    if (dia > aux || mes > c.getMes()) {
                        pode = true;
                    }
                }
                else {
                    if (dia > aux || mes > c.getMes()) {
                        pode = true;
                    } else
                        pode = false;
                }

            }
        }
        return pode;
    }
}