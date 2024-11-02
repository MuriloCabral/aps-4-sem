package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SelectionSort {

    // Função que implementa o Selection Sort
    public static void selectionSort(int[] array) {
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            // Encontra o menor elemento do array não ordenado
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            // Troca o menor elemento encontrado com o primeiro elemento não ordenado
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
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

    public static void imprimirVetor(int[] vetor) {
        for (int num : vetor) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void escreverEmArquivo(int[] vetor, String nomeArquivo) {
        try {
            File arquivo = new File("selection_ordenado_" + nomeArquivo);
            FileWriter escritor = new FileWriter(arquivo);
            BufferedWriter buffer = new BufferedWriter(escritor);

            for (int num : vetor) {
                buffer.write(num + "\n");
            }

            buffer.close();
            escritor.close();
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String[] arquivos = {"1000_numbers.txt", "5000_numbers.txt", "10000_numbers.txt"};

        for (String nomeArquivo : arquivos) {
            try {
                int[] vetorDesordenado = lerNumerosDoArquivo(nomeArquivo);

                long inicio = System.nanoTime(); // Medindo o tempo em nanosegundos
                selectionSort(vetorDesordenado);
                long fim = System.nanoTime(); // Tempo final

                long tempoGasto = fim - inicio;

                System.out.println("Arquivo: " + nomeArquivo);
                System.out.println("Tempo gasto para ordenar: " + tempoGasto + " nanosegundos");
                imprimirVetor(vetorDesordenado);
                escreverEmArquivo(vetorDesordenado, nomeArquivo);
                System.out.println("===========================================");

            } catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado: " + e.getMessage());
            }
        }
    }
}
