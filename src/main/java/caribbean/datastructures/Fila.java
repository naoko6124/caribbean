/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caribbean.datastructures;

/**
 *
 * @author bruno
 */
public class Fila {
    int tamanho;
	int inicio;
	int fim;
	int total;
	Object vetor[];
	public Fila(int tam){
		inicio = 0;
		fim = 0;
		total = 0;
		tamanho = tam;
		vetor = new Object[tam];
	}
	public boolean vazia() {
		if (total == 0)
			return true;
		else
			return false;
	}
	public boolean cheia() {
	if (total >= tamanho)
		return true;
	else
		return false;
	}
	public void enfileirar(Object elem) {
		if (!cheia())
		{ 
			vetor[fim] = elem;
			fim++;
			total++;
			if (fim >= tamanho)
				fim = 0;
		}
		else
			System.out.println("Fila Cheia");
		} 
	public Object desenfileirar(){
		Object elem ;
		if (vazia() == false)
		{
			elem = vetor[inicio];
			inicio++;
			if (inicio >= tamanho)
				inicio = 0;
			total --;
		}else
			elem = "Fila vazia"; 
		return elem; 
	}
	public void exibeFila() {
		for (int i = 0; i < total; i++)
			System.out.println("posicao " + i + " valor " + vetor[i]);
	}
}
