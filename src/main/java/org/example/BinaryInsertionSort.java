package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BinaryInsertionSort {

    // Função que implementa o Binary Insertion Sort
    public static void binaryInsertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int chave = array[i];
            int pos = Math.abs(buscaBinaria(array, chave, 0, i - 1) + 1);

            // Move todos os elementos maiores que a chave uma posição à frente
            for (int j = i - 1; j >= pos; j--) {
                array[j + 1] = array[j];
            }

            // Insere a chave na posição encontrada
            array[pos] = chave;
        }
    }

    // Função de busca binária modificada
    private static int buscaBinaria(int[] array, int chave, int inicio, int fim) {
        if (fim <= inicio) {
            return (chave > array[inicio]) ? inicio + 1 : inicio;
        }

        int meio = (inicio + fim) / 2;

        if (chave == array[meio]) {
            return meio + 1;
        }

        if (chave > array[meio]) {
            return buscaBinaria(array, chave, meio + 1, fim);
        }

        return buscaBinaria(array, chave, inicio, meio - 1);
    }

    public static void main(String[] args) {
        String[] arquivos = {"1000_numbers.txt", "5000_numbers.txt", "10000_numbers.txt"};

        for (String nomeArquivo : arquivos) {
            try {
                int[] vetorDesordenado = lerNumerosDoArquivo(nomeArquivo);

                long inicio = System.nanoTime(); // Medindo o tempo em nanosegundos
                binaryInsertionSort(vetorDesordenado);
                long fim = System.nanoTime(); // Tempo final

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
