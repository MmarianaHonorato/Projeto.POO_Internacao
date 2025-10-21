package view;


import control.Control;

// Importa o Model para entender os dados que o Controller devolve
import model.Paciente;
import model.Medico;
import model.Leito;
import model.Internacao;

//  Importa as ferramentas de UI e de formatação
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Main {

    // Ferramentas da View
    private static Scanner scanner = new Scanner(System.in);
    private static SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
    
    
    // A View (atendente) precisa de uma referência ao Controller (gerente)
    // para poder enviar e pedir dados.
    private static Control controlador = new Control();

    
    // Método principal: Inicia o programa
    public static void main(String[] args) {
        
        int opcao = -1; 
        
        // Loop principal do menu
        while (opcao != 0) {
            exibirMenuPrincipal();
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, digite um número.");
                opcao = -1; // Reseta a opção para não dar loop infinito
                continue; // Volta ao início do 'while'
            }

            // A View decide qual sub-menu mostrar
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
        
        scanner.close(); // Fecha o scanner ao sair
    }

    //  MENUS DA VIEW 

    private static void exibirMenuPrincipal() {
        System.out.println("\n--- Sistema de Internação Hospitalar (MVC) ---");
        System.out.println("1. Gerenciar Pacientes");
        System.out.println("2. Gerenciar Médicos");
        System.out.println("3. Gerenciar Leitos");
        System.out.println("4. Gerenciar Internações");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    // Pacientes 
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
    
    private static void cadastrarPaciente() {
        System.out.println("\n--- Cadastro de Paciente ---");
        try {
            //  View coleta os dados do usuário
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

            //  View envia os dados para o CONTROLLER
            boolean sucesso = controlador.cadastrarPaciente(nome, cpf, email, dataNasc, endereco, historico);

            //  View informa o resultado ao usuário
            if (sucesso) {
                System.out.println(">>> Paciente cadastrado com sucesso! <<<");
            } else {
                System.out.println(">>> Erro ao cadastrar paciente. <<<");
            }
            
        } catch (ParseException e) {
            System.out.println("Erro no formato da data! Use dd/MM/yyyy. Cadastro cancelado.");
        }
    }

    private static void listarPacientes() {
        System.out.println("\n--- Lista de Pacientes ---");
        
        // View PEDE a lista ao CONTROLLER
        ArrayList<Paciente> pacientes = controlador.listarPacientes();
        
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
            return; 
        }
        
        //  View MOSTRA a lista para o usuário
        for (int i = 0; i < pacientes.size(); i++) {
            Paciente p = pacientes.get(i);
            System.out.println(i + ": " + p.toString()); 
        }
    }

    // Médicos 
    private static void menuGerenciarMedicos() {
        System.out.println("\n--- Médicos ---");
        System.out.println("1. Cadastrar Novo Médico");
        System.out.println("2. Listar Médicos");
        System.out.print("Opção: ");
        
        String opcao = scanner.nextLine();
        
        if (opcao.equals("1")) {
            // Lógica para coletar dados do médico e chamar controlador.cadastrarMedico(...)
            System.out.println("Funcionalidade de cadastrar médico a ser implementada.");
        } else if (opcao.equals("2")) {
            listarMedicos();
        }
    }

    private static void listarMedicos() {
        System.out.println("\n--- Lista de Médicos ---");
        ArrayList<Medico> medicos = controlador.listarMedicos(); // 1. Pede
        if (medicos.isEmpty()) {
            System.out.println("Nenhum médico cadastrado.");
            return;
        }
        for (int i = 0; i < medicos.size(); i++) { // 2. Mostra
            System.out.println(i + ": " + medicos.get(i).toString());
        }
    }
    
    // Leitos 
    private static void menuGerenciarLeitos() {
        System.out.println("\n--- Leitos ---");
        System.out.println("1. Cadastrar Novo Leito");
        System.out.println("2. Listar Todos os Leitos");
        System.out.print("Opção: ");
        
        String opcao = scanner.nextLine();
        
        if (opcao.equals("1")) {
            // Lógica para coletar dados e chamar controlador.cadastrarLeito(...)
             System.out.println("Funcionalidade de cadastrar leito a ser implementada.");
        } else if (opcao.equals("2")) {
            listarLeitos();
        }
    }
    
    private static void listarLeitos() {
        System.out.println("\n--- Lista de Todos os Leitos ---");
        ArrayList<Leito> leitos = controlador.listarLeitos(); // 1. Pede
        if (leitos.isEmpty()) {
            System.out.println("Nenhum leito cadastrado.");
            return;
        }
        for (Leito l : leitos) { // 2. Mostra
            System.out.println(l.toString());
        }
    }
    
    //  Internações 
    
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
        }
    }
    
    private static void registrarInternacao() {
        System.out.println("\n--- Registrar Nova Internação ---");
        
        // --- ETAPA 1: Escolher Paciente ---
        System.out.println("Selecione o paciente:");
        ArrayList<Paciente> pacientes = controlador.listarPacientes();
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado. Cancele e cadastre um.");
            return;
        }
        for (int i = 0; i < pacientes.size(); i++) {
            System.out.println(i + ": " + pacientes.get(i).getNome());
        }
        System.out.print("Digite o ÍNDICE do paciente: ");
        int idxPaciente = Integer.parseInt(scanner.nextLine());

        //  Escolher Médico
        System.out.println("\nSelecione o médico:");
        ArrayList<Medico> medicos = controlador.listarMedicos();
        if (medicos.isEmpty()) {
            System.out.println("Nenhum médico cadastrado. Cancele e cadastre um.");
             return;
        }
        for (int i = 0; i < medicos.size(); i++) {
            System.out.println(i + ": " + medicos.get(i).getNome());
        }
        System.out.print("Digite o ÍNDICE do médico: ");
        int idxMedico = Integer.parseInt(scanner.nextLine());
        
        //  Escolher Leito 
        System.out.println("\nSelecione um leito LIVRE:");
        // A View NÃO sabe como filtrar, ela pede ao Controller
        ArrayList<Leito> leitosLivres = controlador.listarLeitosLivres(); 
        
        if (leitosLivres.isEmpty()) {
            System.out.println(">>> ERRO: Nenhum leito livre disponível! <<<");
            return;
        }
        for (int i = 0; i < leitosLivres.size(); i++) {
            System.out.println(i + ": " + leitosLivres.get(i).toString());
        }
        System.out.print("Digite o ÍNDICE do leito livre: ");
        int idxLeitoLivre = Integer.parseInt(scanner.nextLine());

        // Coletar dados restantes ---
        try {
            System.out.print("Data de Entrada (dd/MM/yyyy): ");
            Date dataEntrada = formatadorData.parse(scanner.nextLine());
            System.out.print("Diagnóstico: ");
            String diagnostico = scanner.nextLine();
            
            //  View envia tudo para o Controller ---
            boolean sucesso = controlador.registrarInternacao(idxPaciente, idxMedico, idxLeitoLivre, dataEntrada, diagnostico);
            
            if(sucesso) {
                 System.out.println("\n>>> Internação registrada com SUCESSO! <<<");
            } else {
                System.out.println("\n>>> Erro ao registrar internação (Ex: índice inválido). <<<");
            }

        } catch (ParseException e) {
            System.out.println("Erro no formato da data! Internação cancelada.");
        } catch (NumberFormatException e) {
            System.out.println("Erro: Índice deve ser um número. Internação cancelada.");
        }
    }
    
    private static void listarInternacoes() {
        System.out.println("\n--- Histórico de Internações ---");
        
        ArrayList<Internacao> internacoes = controlador.listarInternacoes();
        
        if (internacoes.isEmpty()) {
            System.out.println("Nenhuma internação registrada.");
            return;
        }
        
        for (Internacao i : internacoes) {
            System.out.println("---------------------------------");
            System.out.println("Paciente: " + i.getPaciente().getNome());
            System.out.println("Médico: " + i.getMedico().getNome());
            System.out.println("Leito: " + i.getLeito().getNumero());
            System.out.println("Data Entrada: " + formatadorData.format(i.getDataEntrada()));
            System.out.println("Diagnóstico: " + i.getDiagnostico());
            
            if (i.getDataAlta() != null) {
                System.out.println("Data Alta: " + formatadorData.format(i.getDataAlta()));
            } else {
                System.out.println("Status: Paciente Internado");
            }
        }
    }
}