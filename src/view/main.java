package view;

import model.Paciente;
import model.Medico;
import model.Leito;
import model.Internacao;


import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException; 


public class Main {

    // --- "Banco de Dados" em memória ---
    // Usamos 'static' para que fiquem acessíveis em toda a classe
    private static ArrayList<Paciente> listaPacientes = new ArrayList<>();
    private static ArrayList<Medico> listaMedicos = new ArrayList<>();
    private static ArrayList<Leito> listaLeitos = new ArrayList<>();
    private static ArrayList<Internacao> listaInternacoes = new ArrayList<>();

    // Ferramentas para ler do console
    private static Scanner scanner = new Scanner(System.in);
    private static SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");

    // Método principal que inicia o programa
    public static void main(String[] args) {
        
       
        criarDadosDeTeste();

        int opcao = -1; 


        while (opcao != 0) {
            exibirMenuPrincipal();
            
            try {
                opcao = Integer.parseInt(scanner.nextLine()); 
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, digite um número válido.");
                opcao = -1; 
                continue; 
            }

            switch (opcao) {
                case 1:
                    menuGerenciarPacientes();
                    break;
                case 2:
                    menuGerenciarMedicos();
                    break;
                case 3:
                    menuGerenciarLeitos();
                    break;
                case 4:
                    menuGerenciarInternacoes();
                    break;
                case 0:
                    System.out.println("Obrigado por usar o sistema. Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
        
        scanner.close(); 
    }

        // OS MENUS
    private static void exibirMenuPrincipal() {
        System.out.println("\n--- Sistema de Internação Hospitalar ---");
        System.out.println("1. Gerenciar Pacientes");
        System.out.println("2. Gerenciar Médicos");
        System.out.println("3. Gerenciar Leitos");
        System.out.println("4. Gerenciar Internações");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }
        // Cadastrar Paciente
    private static void menuGerenciarPacientes() {
        System.out.println("\n--- Pacientes ---");
        System.out.println("1. Cadastrar Novo Paciente");
        System.out.println("2. Listar Pacientes");
        System.out.print("Opção: ");
        
        String opcao = scanner.nextLine();
        
        if (opcao.equals("1")) {
            cadastrarPaciente();
        } else if (opcao.equals("2")) {
            listarPacientes();
        } else {
            System.out.println("Opção inválida.");
        }
    }
        // Registrar Internação
    private static void menuGerenciarInternacoes() {
        System.out.println("\n--- Internações ---");
        System.out.println("1. Registrar Nova Internação");
        System.out.println("2. Listar Internações");
        System.out.print("Opção: ");
        
        String opcao = scanner.nextLine();
        
        if (opcao.equals("1")) {
            registrarInternacao();
        } else if (opcao.equals("2")) {
            listarInternacoes();
        } else {
            System.out.println("Opção inválida.");
        }
    }
    
    private static void menuGerenciarMedicos() {
        System.out.println("\n--- Médicos ---");
        System.out.println("1. Cadastrar Novo Médico");
        System.out.println("2. Listar Médicos");
        System.out.print("Opção: ");
        
        String opcao = scanner.nextLine();
        
        if (opcao.equals("1")) {
            cadastrarMedico();
        } else if (opcao.equals("2")) {
            listarMedicos();
        } else {
            System.out.println("Opção inválida.");
        }
    }
    
    private static void menuGerenciarLeitos() {
         System.out.println("\n--- Leitos ---");
        System.out.println("1. Cadastrar Novo Leito");
        System.out.println("2. Listar Leitos");
        System.out.print("Opção: ");
        
        String opcao = scanner.nextLine();
        
        if (opcao.equals("1")) {
            cadastrarLeito();
        } else if (opcao.equals("2")) {
            listarLeitos();
        } else {
            System.out.println("Opção inválida.");
        }
    }


    private static void cadastrarPaciente() {
        System.out.println("\n--- Cadastro de Paciente ---");
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Endereço: ");
            String endereco = scanner.nextLine();
            System.out.print("Data Nasc. (dd/MM/yyyy): ");
            Date dataNasc = formatadorData.parse(scanner.nextLine());
            System.out.print("Histórico Clínico: ");
            String historico = scanner.nextLine();

            // Cria o novo objeto Paciente
            Paciente novoPaciente = new Paciente(nome, cpf, email, dataNasc, endereco, historico);
            
            // Adiciona na lista 
            listaPacientes.add(novoPaciente);
            
            System.out.println("Paciente cadastrado com sucesso!");
            
        } catch (ParseException e) {
            System.out.println("Erro no formato da data! Use dd/MM/yyyy. Cadastro cancelado.");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar paciente: " + e.getMessage());
        }
    }

    private static void listarPacientes() {
        System.out.println("\n--- Lista de Pacientes ---");
        if (listaPacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
            return; // Sai do método
        }
        
        // percorre a lista de pacientes
        for (int i = 0; i < listaPacientes.size(); i++) {
            Paciente p = listaPacientes.get(i);
            System.out.println(i + ": " + p.toString()); 
        }
    }

    private static void cadastrarMedico() {
        System.out.println("\n--- Cadastro de Médico ---");
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Endereço: ");
            String endereco = scanner.nextLine();
            System.out.print("Data Nasc. (dd/MM/yyyy): ");
            Date dataNasc = formatadorData.parse(scanner.nextLine());
            System.out.print("CRM: ");
            String crm = scanner.nextLine();
            System.out.print("Especialidade: ");
            String especialidade = scanner.nextLine();

            Medico novoMedico = new Medico(nome, cpf, email, dataNasc, endereco, crm, especialidade);
            listaMedicos.add(novoMedico);
            
            System.out.println("Médico cadastrado com sucesso!");
            
        } catch (ParseException e) {
            System.out.println("Erro no formato da data! Use dd/MM/yyyy. Cadastro cancelado.");
        }
    }
    
    private static void listarMedicos() {
        System.out.println("\n--- Lista de Médicos ---");
        if (listaMedicos.isEmpty()) {
            System.out.println("Nenhum médico cadastrado.");
            return;
        }
        for (int i = 0; i < listaMedicos.size(); i++) {
            System.out.println(i + ": " + listaMedicos.get(i).toString());
        }
    }

    private static void cadastrarLeito() {
        System.out.println("\n--- Cadastro de Leito ---");
        try {
            System.out.print("Número do Leito: ");
            int numero = Integer.parseInt(scanner.nextLine());
            System.out.print("Tipo (ex: UTI, Quarto): ");
            String tipo = scanner.nextLine();
            
            Leito novoLeito = new Leito(numero, tipo);
            listaLeitos.add(novoLeito);
            
            System.out.println("Leito cadastrado com sucesso!");

        } catch (NumberFormatException e) {
            System.out.println("Erro: O número do leito deve ser um número.");
        }
    }

    private static void listarLeitos() {
        System.out.println("\n--- Lista de Leitos ---");
        if (listaLeitos.isEmpty()) {
            System.out.println("Nenhum leito cadastrado.");
            return;
        }
        for (Leito l : listaLeitos) {
            System.out.println(l.toString()); 
        }
    }
    
    private static void registrarInternacao() {
        System.out.println("\n--- Registrar Nova Internação ---");
        
        // Escolher Paciente
        System.out.println("Selecione o paciente:");
        listarPacientes();
        if (listaPacientes.isEmpty()) return; // Não continua se não há pacientes
        System.out.print("Digite o índice (número) do paciente: ");
        int idxPaciente = Integer.parseInt(scanner.nextLine());
        Paciente paciente = listaPacientes.get(idxPaciente);

        // Escolher Médico
        System.out.println("\nSelecione o médico:");
        listarMedicos();
        if (listaMedicos.isEmpty()) return;
        System.out.print("Digite o índice (número) do médico: ");
        int idxMedico = Integer.parseInt(scanner.nextLine());
        Medico medico = listaMedicos.get(idxMedico);
        
        // Escolher Leito
        System.out.println("\nSelecione um leito LIVRE:");
        ArrayList<Leito> leitosLivres = new ArrayList<>();
        for(Leito l : listaLeitos) {
            if (!l.isOcupado()) { // Verifica a pré-condição do paciente e da internação
                leitosLivres.add(l);
                System.out.println(leitosLivres.size() - 1 + ": " + l.toString());
            }
        }
        
        if (leitosLivres.isEmpty()) {
            System.out.println("ERRO: Nenhum leito livre disponível! Não é possível internar.");
            return;
        }
        
        System.out.print("Digite o índice (número) do leito: ");
        int idxLeito = Integer.parseInt(scanner.nextLine());
        Leito leito = leitosLivres.get(idxLeito);

        // Pega dados restantes
        try {
            System.out.print("Data de Entrada (dd/MM/yyyy): ");
            Date dataEntrada = formatadorData.parse(scanner.nextLine());
            System.out.print("Diagnóstico: ");
            String diagnostico = scanner.nextLine();
            
            // Cria a internação
            Internacao novaInternacao = new Internacao(paciente, medico, leito, dataEntrada, diagnostico);
            listaInternacoes.add(novaInternacao);
            
            System.out.println("\nInternação registrada com SUCESSO!");
            System.out.println("Paciente: " + paciente.getNome());
            System.out.println("Leito: " + leito.getNumero() + " (Agora está OCUPADO)");

        } catch (ParseException e) {
            System.out.println("Erro no formato da data! Internação cancelada.");
        }
    }
    
    private static void listarInternacoes() {
        System.out.println("\n--- Histórico de Internações ---");
        if (listaInternacoes.isEmpty()) {
            System.out.println("Nenhuma internação registrada.");
            return;
        }
        
        for (Internacao i : listaInternacoes) {
            System.out.println("---------------------------------");
            System.out.println("Paciente: " + i.getPaciente().getNome());
            System.out.println("Médico: " + i.getMedico().getNome());
            System.out.println("Leito: " + i.getLeito().getNumero());
            System.out.println("Data Entrada: " + formatadorData.format(i.getDataEntrada()));
            System.out.println("Diagnóstico: " + i.getDiagnostico());
            
            // Verifica se já teve alta
            if (i.getDataAlta() != null) {
                System.out.println("Data Alta: " + formatadorData.format(i.getDataAlta()));
            } else {
                System.out.println("Status: Paciente Internado");
            }
            System.out.println("---------------------------------");
        }
    }

    // Método para popular o sistema com dados falsos para teste
    private static void criarDadosDeTeste() {
        try {
           
            Medico m1 = new Medico("Dr. House", "111", "house@email.com", formatadorData.parse("15/05/1959"), "Rua A", "CRM123", "Diagnóstico");
            Medico m2 = new Medico("Dra. Grey", "222", "grey@email.com", formatadorData.parse("22/11/1979"), "Rua B", "CRM456", "Cirurgia");
            listaMedicos.add(m1);
            listaMedicos.add(m2);

          
            Paciente p1 = new Paciente("João Silva", "333", "joao@email.com", formatadorData.parse("10/01/1980"), "Rua C", "Asma");
            Paciente p2 = new Paciente("Maria Souza", "444", "maria@email.com", formatadorData.parse("05/06/1990"), "Rua D", "Diabetes");
            listaPacientes.add(p1);
            listaPacientes.add(p2);
            
        
            Leito l1 = new Leito(101, "UTI");
            Leito l2 = new Leito(102, "Quarto");
            Leito l3 = new Leito(103, "Quarto");
            listaLeitos.add(l1);
            listaLeitos.add(l2);
            listaLeitos.add(l3);

        } catch (ParseException e) {
            System.out.println("Erro ao criar dados de teste.");
        }
    }
}