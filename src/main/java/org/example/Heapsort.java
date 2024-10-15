package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Heapsort {
    private static int tam;
    private static int[] vetor;

    public static void heapsort(int[] array) {
        vetor = array;
        tam = vetor.length - 1;

        constroiHeap();

        for (int i = tam; i > 0; i--) {
            troca(0, tam);
            tam -= 1;
            maxHeapifica(0);
        }
    }

    private static void constroiHeap() {
        int meio = (int) (tam / 2);

        for (int i = meio - 1; i >= 0; i--) {
            maxHeapifica(i);
        }
    }

    private static void troca(int i, int j) {
        int aux;
        aux = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = aux;
    }

    private static void maxHeapifica(int pai) {
        int maior = pai,
                esquerda = 2 * pai + 1,
                direita = 2 * pai + 2;

        if (esquerda <= tam && vetor[esquerda] > vetor[maior]) {
            maior = esquerda;
        }

        if (direita <= tam && vetor[direita] > vetor[maior]) {
            maior = direita;
        }

        if (maior != pai) {
            troca(pai, maior);
            maxHeapifica(maior);
        }
    }

    public static void main(String[] args) {
        String[] arquivos = {"1000_numbers.txt", "5000_numbers.txt", "10000_numbers.txt"};

        for (String nomeArquivo : arquivos) {
            try {
                int[] vetorDesordenado = lerNumerosDoArquivo(nomeArquivo);

                long inicio = System.nanoTime(); // Medindo em nanosegundos
                heapsort(vetorDesordenado);
                long fim = System.nanoTime(); // Medindo em nanosegundos

                long tempoGasto = fim - inicio;

                System.out.println("Arquivo: " + nomeArquivo);
                System.out.println("Tempo gasto para ordenar: " + tempoGasto + " nanosegundos");
                System.out.println("===========================================");

            } catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado: " + e.getMessage());
            }
        }
    }

    private static int[] lerNumerosDoArquivo(String nomeArquivo) throws FileNotFoundException {
        File arquivo = new File(nomeArquivo);
        Scanner scanner = new Scanner(arquivo);
        ArrayList<Integer> listaNumeros = new ArrayList<>();

        while (scanner.hasNextInt()) {
            listaNumeros.add(scanner.nextInt());
        }

        scanner.close();

        int[] vetor = new int[listaNumeros.size()];
        for (int i = 0; i < listaNumeros.size(); i++) {
            vetor[i] = listaNumeros.get(i);
        }

        return vetor;
    }
}
