/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caribbean.datastructures;

import javax.swing.JOptionPane;

/**
 *
 * @author bruno
 */
public class ListaEncadeada {
    ListaNo primeiro, ultimo;
    int numero_nos=0;
    
    public ListaEncadeada(){
        primeiro = ultimo = null;   
    }
    public void insereNo_fim (ListaNo novoNo){
	    novoNo.prox = null;
        if (primeiro == null)
            primeiro = novoNo;
        if (ultimo != null)
                ultimo.prox = novoNo;
            ultimo = novoNo;
        numero_nos++;
    }
    public void insereNo_inicio (ListaNo novoNo){
        novoNo.prox = primeiro;
        if(primeiro == null && ultimo==null) //Só tem um elemento na lista
            {
                ultimo = novoNo;
        }
        primeiro = novoNo;
            numero_nos++;
        }
        int ContarNos(){
        int tamanho = 0;
            ListaNo temp_no = primeiro;
            while (temp_no != null)
            {   tamanho++;
                temp_no = temp_no.prox;
        }
            return tamanho;
        }
        void insereNo_posicao(ListaNo novoNo, int posicao){
            ListaNo temp_no = primeiro;
        int numero_nos = ContarNos();
        int pos_aux;
        if(posicao == 0)
        {
                novoNo.prox = primeiro;
                if(primeiro == ultimo)
                {
                    ultimo = novoNo;
                }
                primeiro = novoNo;
            }
            else
	    {
            if (posicao <= numero_nos)
            {   
		pos_aux = 1;
		while(temp_no != null && posicao > pos_aux)
		{
                    temp_no = temp_no.prox;
                    pos_aux ++;
                }
                novoNo.prox = temp_no.prox;
                temp_no.prox = novoNo;
            }
            else
            {
                if(posicao > numero_nos)
		{
                    ultimo.prox = novoNo;
                    ultimo = novoNo;
		}
            }	 
        }
    }


    public ListaNo buscaNo (Object buscaValor){
        int i = 0;
        ListaNo temp_no = primeiro;
        while (temp_no != null)
        {
            if (temp_no.valor == buscaValor)
            {
                JOptionPane.showMessageDialog(null, "No " + temp_no.valor + " posição " + i);
                return temp_no;	
            }
            i++;
            temp_no = temp_no.prox;
        }
        return null;
    }
    public void excluiNo (Object valor){
        ListaNo temp_no = primeiro;
        ListaNo anterior_no=null;
        while (temp_no != null && temp_no.valor != valor){
            anterior_no = temp_no;
            temp_no = temp_no.prox;
        }
        if (temp_no == primeiro){
            if (temp_no.prox !=null)
                primeiro = temp_no.prox;
            else
                primeiro = null;
        }
        else{
            anterior_no.prox =temp_no.prox;
        }
        
        if (ultimo == temp_no)
            ultimo = anterior_no;
    }
    public String exibeLista(){
        String texto = "";
        ListaNo temp_no = primeiro;
        while (temp_no != null)
        {
            texto += temp_no.valor.toString() + "\\\\ ";
            temp_no = temp_no.prox;
        }
        return texto;
    }
    public Object[] toArray(){
        Object dados[] = new Object[ContarNos()];
        ListaNo temp_no = primeiro;
        int i = 0;
        while (temp_no != null)
        {
            dados[i] = temp_no.valor;
            temp_no = temp_no.prox;
            i++;
        }
        return dados;
    }
}
