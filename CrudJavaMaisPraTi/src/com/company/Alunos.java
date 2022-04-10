package com.company;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Alunos extends Pessoas{
    protected double nota;
    private ArrayList<Alunos> alunos = new ArrayList<>();

    public Alunos(String nome, String telefone, LocalDate dataNasc, double nota) {
        super(nome, telefone, dataNasc);
        this.nota = nota;
    }

    public Alunos() {
    }

    public void setNota() {
        Scanner scanner = new Scanner(System.in);
        boolean notaValida = false;
        double nota = 0;
        while (!notaValida) {
            System.out.println("Digite a nota final");
            nota = Double.parseDouble(scanner.nextLine());
            if ((nota >= 0) && (nota <= 10)) {
                notaValida = true;
            } else {
                System.out.println("Nota deve ser valor entre 0 e 10");
            }
        }
        this.nota = nota;
    }

    public void cadastrar() {
        boolean confirmar = false;
        String op;
        while (!confirmar) {
            Scanner scanner = new Scanner(System.in);
            setNome();
            setTelefone();
            setDataNasc();
            setNota();

            System.out.println("Nome: " + this.nome + "\nTelefone: " + this.telefone + "\nData de nascimento: " + this.dataNasc.format(formato) + "\nNota: " +                     this.nota);
            System.out.println("Confirma a inclusão dos dados acima?");
            System.out.println("Pressione 's' ou ENTER para confirmar, 'c' para cancelar ou qualquer outra tecla para corrigir os dados");
            op = scanner.nextLine();
            if (op.equals("s") || op.equals("")) {
                confirmar = true;
                alunos.add(new Alunos(this.nome, this.telefone, this.dataNasc, this.nota));
                System.out.println("Aluno " + this.nome + " cadastrada com sucesso");
            } else if (op.equals("c")) {
                System.out.println("Operação cancelada!\n");
                break;
            }
        }
    }

    public void exibir() {
        System.out.println("----------------");
        System.out.println("Alunos");
        System.out.println("----------------");
        if (alunos.size() == 0) {
            System.out.println("Não há alunos cadastrados\n");
        } else {
            for (Alunos a : alunos) {
                System.out.println("Número do aluno: " + alunos.indexOf(a));
                System.out.println(a.toString());
                System.out.println("----------------");
            }
        }
    }

    public void editar() {
        int escolheNumero = 0;
        boolean confirmar = false;
        String op;
        Scanner scanner = new Scanner(System.in);

        if (alunos.size() == 0) {
            System.out.println("Não há dados para editar\n");
        } else {
            while (!confirmar) {
                boolean numeroExiste = false;
                while (!numeroExiste) {
                    exibir();
                    System.out.println("Selecione o número da pessoa cujos dados deseja editar");
                    escolheNumero = Integer.parseInt(scanner.nextLine());
                    if (escolheNumero < alunos.size() && escolheNumero >= 0) {
                        numeroExiste = true;
                    } else {
                        System.out.println("Escolha um número existente");
                    }
                }
                setNome();
                setTelefone();
                setDataNasc();
                setNota();

                System.out.println("Nome: " + this.nome + "\nTelefone: " + this.telefone + "\nData de nascimento: " + this.dataNasc.format(formato) + "\nNota final: " + this.nota);
                System.out.println("Confirma os dados acima?");
                System.out.println("Pressione 's' ou ENTER para confirmar, 'c' para cancelar ou qualquer outra tecla para corrigir os dados");
                op = scanner.nextLine();
                if (op.equals("s") || op.equals("")) {
                    alunos.get(escolheNumero).nome = this.nome;
                    alunos.get(escolheNumero).telefone = this.telefone;
                    alunos.get(escolheNumero).dataNasc = this.dataNasc;
                    alunos.get(escolheNumero).nota = this.nota;
                    alunos.get(escolheNumero).dataAlteracao = LocalDateTime.now();
                    System.out.println("Dados de " + alunos.get(escolheNumero).nome + " atualizados com sucesso!");
                    confirmar = true;
                } else if (op.equals("c")) {
                    System.out.println("Operação cancelada!\n");
                    break;
                }
            }
        }
    }

    public void excluir() {
        int escolheNumero = 0 ;
        boolean confirma = false;
        String nome;
        String op;

        Scanner scanner = new Scanner(System.in);
        if (alunos.size() == 0) {
            System.out.println("Não há dados para excluir\n");
        } else {
            while (!confirma) {
                boolean numeroExiste = false;
                while (!numeroExiste) {
                    exibir();
                    System.out.println("Selecione o número da pessoa cujos dados deseja excluir");
                    escolheNumero = Integer.parseInt(scanner.nextLine());
                    if (escolheNumero < alunos.size() && escolheNumero >=0) {
                        numeroExiste = true;
                    } else {
                        System.out.println("Escolha um número existente");
                    }
                }
                System.out.println(alunos.get(escolheNumero).toString());
                System.out.println("Excluir os dados acima?");
                System.out.println("\"Pressione 's' ou ENTER para confirmar, 'c' para cancelar ou qualquer outra tecla para selecionar outro registro");
                op = scanner.nextLine();
                if (op.equals("s") || op.equals("")) {
                    nome = alunos.get(escolheNumero).nome;
                    alunos.remove(escolheNumero);
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
        return "Nome: " + this.nome + "\nTelefone: " + this.telefone + "\nData de nascimento: " + this.dataNasc.format(formato) + "\nNota: " +
                this.nota + "\nData de Cadastro: " + this.dataCadastro.format(formato2) + "\nData da Última alteração: " + this.dataAlteracao.format(formato2);
    }
}