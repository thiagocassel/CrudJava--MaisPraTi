package com.company;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Pessoas {
    protected String nome;
    protected String telefone;
    protected LocalDate dataNasc;
    protected LocalDateTime dataCadastro;
    protected LocalDateTime dataAlteracao;
    private ArrayList<Pessoas> pessoas = new ArrayList<>();
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter formato2 = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");

    public Pessoas(String nome, String telefone, LocalDate dataNasc) {
        this.nome = nome;
        this.telefone = telefone;
        this.dataNasc = dataNasc;
        this.dataCadastro = LocalDateTime.now();
        this.dataAlteracao = this.dataCadastro;
    }

    public Pessoas() {
    }

    public void setNome() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome");
        this.nome = scanner.nextLine();
    }

    public void setTelefone() {
        Scanner scanner = new Scanner(System.in);
        boolean telefoneValido = false;
        String telefone = "";
        while (!telefoneValido) {
            System.out.println("Digite o telefone com 8 ou 9 dígitos");
            telefone = scanner.nextLine();
            if ((telefone.length() == 8 || telefone.length() == 9) && (telefone.matches("[0-9]*"))) {
                telefoneValido = true;
            } else {
                System.out.println("Telefone deve possuir 8 ou 9 caracteres numéricos");
            }
        }
        this.telefone = telefone;
    }

    public void setDataNasc() {
        Scanner scanner = new Scanner(System.in);
        boolean dataValida = false;
        while (!dataValida) {
            System.out.println("Digite a data de nascimento no formato dd/mm/aaaa");
            this.dataNasc = LocalDate.parse(scanner.nextLine(), formato);
            if (this.dataNasc.isBefore(LocalDate.parse("01/01/1900", formato)) || this.dataNasc.isAfter(LocalDate.now())) {
                System.out.println("Data de nascimento deve ser entre 01/01/1900 e hoje");
            } else {
                dataValida = true;
            }
        }
    }


    public void cadastrar() {
        boolean confirmar = false;
        String op;

        while (!confirmar) {
            Scanner scanner = new Scanner(System.in);
            setNome();
            setTelefone();
            setDataNasc();
            System.out.println("Nome: " + this.nome + "\nTelefone: " + this.telefone + "\nData de nascimento: " + this.dataNasc.format(formato));
            System.out.println("Confirma a inclusão dos dados acima?");
            System.out.println("Pressione 's' ou ENTER para confirmar, 'c' para cancelar ou qualquer outra tecla para corrigir os dados");
            op = scanner.nextLine();
            if (op.equals("s") || op.equals("")) {
                confirmar = true;
                pessoas.add(new Pessoas(this.nome,this.telefone, this.dataNasc));
                System.out.println("Pessoa " + this.nome + " cadastrada com sucesso");
            } else if (op.equals("c")) {
                System.out.println("Operação cancelada!\n");
                break;
            }
        }
    }

    public void exibir() {
        System.out.println("----------------");
        System.out.println("Pessoas");
        System.out.println("----------------");
        if (pessoas.size() == 0) {
            System.out.println("Não ha pessoas cadastradas!\n");
        } else {
            for (Pessoas p : pessoas) {
                System.out.println("Número da pessoa: " + pessoas.indexOf(p));
                System.out.println(p.toString());
                System.out.println("----------------");
            }
        }
    }

    public void editar() {
        int escolheNumero = 0;
        String op;
        boolean confirmar = false;
        Scanner scanner = new Scanner(System.in);

        if (pessoas.size() == 0) {
            System.out.println("Não há dados para editar\n");
        } else {
            while (!confirmar) {
                boolean numeroExiste = false;
                while (!numeroExiste) {
                    exibir();
                    System.out.println("Selecione o número da pessoa cujos dados deseja editar");
                    escolheNumero = Integer.parseInt(scanner.nextLine());
                    if (escolheNumero < pessoas.size() && escolheNumero >= 0) {
                        numeroExiste = true;
                    } else {
                        System.out.println("Escolha um número existente");
                    }
                }

                setNome();
                setTelefone();
                setDataNasc();

                System.out.println("Nome: " + this.nome + "\nTelefone: " + this.telefone + "\nData de nascimento: " + this.dataNasc.format(formato));
                System.out.println("Confirma os dados acima?");
                System.out.println("Pressione 's' ou ENTER para confirmar, 'c' para cancelar ou qualquer outra tecla para corrigir os dados");
                op = scanner.nextLine();
                if (op.equals("s") || op.equals("")) {
                    pessoas.get(escolheNumero).nome = this.nome;
                    pessoas.get(escolheNumero).telefone = this.telefone;
                    pessoas.get(escolheNumero).dataNasc = this.dataNasc;
                    pessoas.get(escolheNumero).dataAlteracao = LocalDateTime.now();
                    System.out.println("Dados de " + pessoas.get(escolheNumero).nome + " atualizados com sucesso!");
                    confirmar = true;
                } else if (op.equals("c")) {
                    System.out.println("Operação cancelada!\n");
                    break;
                }
            }
        }
    }

    public void excluir() {
        int escolheNumero = 0;
        boolean confirma = false;
        String nome;
        String op;

        Scanner scanner = new Scanner(System.in);

        if (pessoas.size() == 0) {
            System.out.println("Não há dados para excluir\n");
        } else {
            while (!confirma) {
                boolean numeroExiste = false;
                while (!numeroExiste) {
                    exibir();
                    System.out.println("Selecione o número da pessoa cujos dados deseja excluir");
                    escolheNumero = Integer.parseInt(scanner.nextLine());
                    if (escolheNumero < pessoas.size() && escolheNumero >=0) {
                        numeroExiste = true;
                    } else {
                        System.out.println("Escolha um número existente");
                    }
                }
                System.out.println(pessoas.get(escolheNumero).toString());
                System.out.println("Excluir os dados acima?");
                System.out.println("Pressione 's' ou ENTER para confirmar, 'c' para cancelar ou qualquer outra tecla para selecionar outro registro");
                op = scanner.nextLine();
                if (op.equals("s") || op.equals("")) {
                    nome = pessoas.get(escolheNumero).nome;
                    pessoas.remove(escolheNumero);
                    System.out.println("Dados de " + nome + " excluídos com sucesso ");
                    confirma = true;
                } else if (op.equals("c")) {
                    System.out.println("Operação cancelada!\n");
                    break;
                }
            }
        }
    }

    public String toString() {
        return "Nome: " + this.nome + "\nTelefone: " + this.telefone + "\nData de nascimento: " + this.dataNasc.format(formato) + "\nData de Cadastro: " +
                this.dataCadastro.format(formato2) + "\nData da Última Alteração: " + this.dataAlteracao.format(formato2);
    }
}