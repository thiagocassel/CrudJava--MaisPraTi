package com.company;

import java.time.format.DateTimeParseException;
import java.lang.NumberFormatException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        boolean continuar = true;
        int opcao;

        Pessoas p = new Pessoas();
        Pessoas a = new Alunos();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo!");

        while (continuar) {
            System.out.println("Digite a opção desejada");
            System.out.println("1 - Cadastrar pessoa");
            System.out.println("2 - Cadastrar aluno");
            System.out.println("3 - Exibir pessoas e alunos cadastrados");
            System.out.println("4 - Editar dados de pessoa");
            System.out.println("5 - Editar dados de aluno");
            System.out.println("6 - Excluir pessoa");
            System.out.println("7 - Excluir aluno");
            System.out.println("8 - Sair");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                if (opcao == 1) {
                    p.cadastrar();
                } else if (opcao == 2) {
                    a.cadastrar();
                } else if (opcao == 3) {
                    p.exibir();
                    a.exibir();
                } else if (opcao == 4) {
                    p.editar();
                } else if (opcao == 5) {
                    a.editar();
                } else if (opcao == 6) {
                    p.excluir();
                } else if (opcao == 7) {
                    a.excluir();
                } else if (opcao == 8) {
                    System.out.println("FIM!");
                    continuar = false;
                } else {
                    System.out.println("Digite uma opção válida\n");
                }
            } catch (DateTimeParseException e1) {
                System.out.println("Erro! Operação cancelada");
                System.out.println("Informe a data no formato dd/mm/aaaa\n");
            } catch (NumberFormatException e2) {
                System.out.println("Erro! Operação cancelada");
                System.out.println("Formato de entrada inválido\n");
            } catch (Exception e3) {
                System.out.println("Erro! Operação cancelada");
                System.out.println("Escolha uma opção válida\n");
            }
        }
    }
}