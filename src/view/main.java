package view;

// Importa tudo que vai ser usado: o controller, as classes do model, o scanner, etc.
import control.ControlController;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import model.*;

public class Main {

    // Método principal, onde o programa começa
    public static void main(String[] args) throws ParseException {
        // 'sc' é pra ler o que o usuário digita no console
        Scanner sc = new Scanner(System.in);
        // 'ctrl' é o objeto que vai fazer a ponte com a lógica (cadastrar, buscar, etc)
        ControlController ctrl = new ControlController();
        int opcaoPrincipal;

        // Loop principal do menu, pra ficar rodando até o usuário digitar 0
        do {
            System.out.println("\n========= MENU PRINCIPAL =========");
            System.out.println("1 - Gerenciar Internação");
            System.out.println("2 - Gerenciar Profissionais");
            System.out.println("3 - Prontuário Eletrônico");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcaoPrincipal = Integer.parseInt(sc.nextLine()); // Lê a opção do usuário

            // 'switch' pra decidir o que fazer com a opção escolhida
            switch (opcaoPrincipal) {
                case 1 -> menuInternacao(sc, ctrl); // Chama o método do menu de internação
                case 2 -> menuProfissionais(sc, ctrl); // Chama o método do menu de profissionais
                case 3 -> menuProntuario(sc, ctrl); // Chama o método do prontuário
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcaoPrincipal != 0); // Repete enquanto a opção não for 0

        sc.close(); // Fecha o 'sc' pra não dar problema de recurso aberto
    }

    
    // Um método separado só pro menu de Internação (Paciente, Exame, Leito)
    private static void menuInternacao(Scanner sc, ControlController ctrl) throws ParseException {
        int opcao;
        do {
            System.out.println("\n--- GERENCIAR INTERNAÇÃO ---");
            System.out.println("1 - Paciente");
            System.out.println("2 - Exame");
            System.out.println("3 - Leito");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(sc.nextLine());

            // Switch pra chamar os sub-menus
            switch (opcao) {
                case 1 -> menuPaciente(sc, ctrl);
                case 2 -> menuExame(sc, ctrl);
                case 3 -> menuLeito(sc, ctrl);
                case 0 -> {} // Se for 0, não faz nada e o loop 'do-while' termina
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    
    // Método só para as coisas do Paciente (CRUD - Cadastrar, Buscar, Atualizar, Excluir)
    private static void menuPaciente(Scanner sc, ControlController ctrl) throws ParseException {
        int opcao;
        do {
            System.out.println("\n--- GERENCIAR PACIENTE ---");
            System.out.println("1 - Buscar paciente por nome");
            System.out.println("2 - Cadastrar novo paciente");
            System.out.println("3 - Atualizar paciente");
            System.out.println("4 - Excluir paciente");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> { // Buscar
                    System.out.print("Digite o nome do paciente: ");
                    String nome = sc.nextLine();
                    // Pede o nome e usa o controller pra buscar na lista
                    List<Paciente> lista = ctrl.buscarPacientePorNome(nome);
                    if (lista == null || lista.isEmpty()) {
                        System.out.println("Nenhum paciente encontrado.");
                    } else {
                        // Mostra os pacientes que achou
                        System.out.println("\n--- RESULTADOS ---");
                        for (Paciente p : lista) {
                            System.out.println("Nome: " + p.getNome());
                            System.out.println("CPF: " + p.getCpf());
                            System.out.println("Email: " + p.getEmail());
                            System.out.println("Histórico: " + p.getHistoricoClinico());
                            System.out.println("-------------------------");
                        }
                    }
                }
                case 2 -> { // Cadastrar
                    // Pede todos os dados do paciente novo
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Data de nascimento (dd/MM/yyyy): ");
                    String dataStr = sc.nextLine();
                    // Pega a data que o usuário digitou (string) e transforma em data (Date)
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    Date dataNascimento = formato.parse(dataStr);

                    System.out.println("Informe os dados do endereço:");
                    System.out.print("CEP: ");
                    String cep = sc.nextLine();
                    System.out.print("Estado: ");
                    String estado = sc.nextLine();
                    System.out.print("Cidade: ");
                    String cidade = sc.nextLine();
                    System.out.print("Número: ");
                    String numero = sc.nextLine();
                    System.out.print("Complemento: ");
                    String complemento = sc.nextLine();
                    // Junta os dados do endereço em um objeto 'Endereco'
                    Endereco endereco = new Endereco(cep, estado, cidade, numero, complemento);

                    System.out.print("Perfil: ");
                    String perfil = sc.nextLine();
                    System.out.print("Senha: ");
                    String senha = sc.nextLine();
                    System.out.print("Histórico Clínico: ");
                    String historicoClinico = sc.nextLine();

                    // Cria o objeto Paciente com tudo e manda o controller salvar
                    Paciente paciente = new Paciente(cpf, nome, email, dataNascimento, endereco, perfil, senha, historicoClinico);
                    ctrl.cadastrarPaciente(paciente);
                }
                case 3 -> { // Atualizar
                    System.out.print("Digite o CPF do paciente: ");
                    String cpf = sc.nextLine();
                    // Pede o CPF pra achar o paciente que vai ser mudado
                    Paciente p = (Paciente) ctrl.buscarPessoaPorCpf(cpf);
                    if (p == null) {
                        System.out.println("Paciente não encontrado!");
                    } else {
                        // Pede os novos dados e atualiza lá no controller
                        System.out.print("Novo nome: ");
                        p.setNome(sc.nextLine());
                        System.out.print("Novo email: ");
                        p.setEmail(sc.nextLine());
                        System.out.print("Novo histórico clínico: ");
                        p.setHistoricoClinico(sc.nextLine());
                        ctrl.atualizarPaciente(p);
                    }
                }
                case 4 -> { // Excluir
                    System.out.print("Digite o CPF do paciente: ");
                    String cpf = sc.nextLine();
                    // Pede o CPF e manda o controller apagar
                    ctrl.excluirPaciente(cpf);
                }
                case 0 -> {}
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }


    // Outro método, agora só pro menu de Exames
    private static void menuExame(Scanner sc, ControlController ctrl) throws ParseException {
        int opcao;
        do {
            System.out.println("\n--- GERENCIAR EXAMES ---");
            System.out.println("1. Cadastrar exame");
            System.out.println("2. Atualizar resultado de exame");
            System.out.println("3. Listar exames");
            System.out.println("4. Excluir exame");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> { // Cadastrar Exame
                    Exame exame = new Exame();
                    System.out.print("Nome do exame: ");
                    exame.setNome(sc.nextLine());
                    System.out.print("Descrição do exame: ");
                    exame.setDescricao(sc.nextLine());

                    System.out.print("CPF do paciente: ");
                    String cpfPaciente = sc.nextLine();
                    // Busca o paciente pelo CPF pra poder ligar o exame a ele
                    Paciente paciente = (Paciente) ctrl.buscarPessoaPorCpf(cpfPaciente);
                    if (paciente == null) {
                        System.out.println("Paciente não encontrado!");
                        break; // Para a execução do 'case' se não achar
                    }
                    exame.setPaciente(paciente);

                    System.out.print("CPF do médico: ");
                    String cpfMedico = sc.nextLine();
                    // Busca o médico pra ligar ao exame
                    Medico medico = (Medico) ctrl.buscarPessoaPorCpf(cpfMedico);
                    if (medico == null) {
                        System.out.println("Médico não encontrado!");
                        break;
                    }
                    exame.setMedico(medico);

                    System.out.print("ID da internação: ");
                    int idInternacao = Integer.parseInt(sc.nextLine());
                    // Busca a internação pra ligar ao exame
                    Internacao internacao = ctrl.buscarInternacaoPorId(idInternacao);
                    if (internacao == null) {
                        System.out.println("Internação não encontrada!");
                        break;
                    }
                    exame.setInternacao(internacao);

                    // Manda o controller salvar o exame
                    ctrl.cadastrarExame(exame);
                }
                case 2 -> { // Atualizar Resultado
                    System.out.print("ID do exame: ");
                    int idExame = Integer.parseInt(sc.nextLine());
                    System.out.print("Data do resultado (dd/MM/yyyy): ");
                    String dataStr = sc.nextLine();
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    java.util.Date data = formato.parse(dataStr);
                    // Manda o controller atualizar só o resultado
                    ctrl.atualizarResultadoExame(idExame, data);
                }
                case 3 -> ctrl.listarExames(); // Chama o controller pra listar todos
                case 4 -> { // Excluir
                    System.out.print("ID do exame: ");
                    int idExame = Integer.parseInt(sc.nextLine());
                    ctrl.excluirExame(idExame);
                }
                case 0 -> {}
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    
    // Método para gerenciar os Leitos
    private static void menuLeito(Scanner sc, ControlController ctrl) {
        int opcao;
        do {
            System.out.println("\n--- GERENCIAR LEITOS ---");
            System.out.println("1. Cadastrar leito");
            System.out.println("2. Atualizar leito");
            System.out.println("3. Listar leitos disponíveis");
            System.out.println("4. Excluir leito");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> { // Cadastrar
                    Leito leito = new Leito();
                    System.out.print("Número do leito: ");
                    leito.setNumero(Integer.parseInt(sc.nextLine()));
                    System.out.print("Tipo do leito: ");
                    leito.setTipo(sc.nextLine());
                    ctrl.cadastrarLeito(leito);
                }
                case 2 -> { // Atualizar
                    System.out.print("ID do leito: ");
                    int idLeito = Integer.parseInt(sc.nextLine());
                    // Busca o leito antes de atualizar
                    Leito leito = ctrl.buscarLeitoPorId(idLeito);
                    if (leito == null) {
                        System.out.println("Leito não encontrado.");
                        break;
                    }
                    // Pede os novos dados
                    System.out.print("Novo número do leito: ");
                    leito.setNumero(Integer.parseInt(sc.nextLine()));
                    System.out.print("Novo tipo do leito: ");
                    leito.setTipo(sc.nextLine());
                    System.out.print("Novo status (Disponível/Ocupado): ");
                    leito.setStatus(sc.nextLine());
                    ctrl.atualizarLeito(leito);
                }
                case 3 -> ctrl.listarLeitosDisponiveis(); // Lista só os disponíveis
                case 4 -> { // Excluir
                    System.out.print("ID do leito: ");
                    int idLeito = Integer.parseInt(sc.nextLine());
                    ctrl.excluirLeito(idLeito);
                }
                case 0 -> {}
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    
    // Menu que leva pra outro menu (Medico ou Enfermeiro)
    private static void menuProfissionais(Scanner sc, ControlController ctrl) throws ParseException {
        int opcao;
        do {
            System.out.println("\n--- GERENCIAR PROFISSIONAIS ---");
            System.out.println("1 - Médico");
            System.out.println("2 - Enfermeiro");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> menuMedico(sc, ctrl);
                case 2 -> menuEnfermeiro(sc, ctrl);
                case 0 -> {}
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    // Método só pra cadastrar Médico
    private static void menuMedico(Scanner sc, ControlController ctrl) throws ParseException {
        System.out.println("\n--- CADASTRAR MÉDICO ---");
        // Pede todos os dados (igual no cadastro de paciente)
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Data de nascimento (dd/MM/yyyy): ");
        String dataStr = sc.nextLine();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date dataNascimento = formato.parse(dataStr);

        System.out.println("Informe os dados do endereço:");
        System.out.print("CEP: "); String cep = sc.nextLine();
        System.out.print("Estado: "); String estado = sc.nextLine();
        System.out.print("Cidade: "); String cidade = sc.nextLine();
        System.out.print("Número: "); String numero = sc.nextLine();
        System.out.print("Complemento: "); String complemento = sc.nextLine();
        Endereco endereco = new Endereco(cep, estado, cidade, numero, complemento);

        System.out.print("Perfil: "); String perfil = sc.nextLine();
        System.out.print("Senha: "); String senha = sc.nextLine();
        // Dados específicos do Médico
        System.out.print("CRM: "); String crm = sc.nextLine();
        System.out.print("Especialidade: "); String especialidade = sc.nextLine();

        // Cria o objeto Medico e manda salvar
        Medico medico = new Medico(cpf, nome, email, dataNascimento, endereco, perfil, senha, crm, especialidade);
        ctrl.cadastrarMedico(medico);
    }

    // Método só pra cadastrar Enfermeiro
    private static void menuEnfermeiro(Scanner sc, ControlController ctrl) throws ParseException {
        System.out.println("\n--- CADASTRAR ENFERMEIRO ---");
        System.out.print("Nome: "); String nome = sc.nextLine();
        System.out.print("CPF: "); String cpf = sc.nextLine();
        System.out.print("Email: "); String email = sc.nextLine();
        System.out.print("Data de nascimento (dd/MM/yyyy): "); String dataStr = sc.nextLine();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date dataNascimento = formato.parse(dataStr);

        System.out.println("Informe os dados do endereço:");
        System.out.print("CEP: "); String cep = sc.nextLine();
        System.out.print("Estado: "); String estado = sc.nextLine();
        System.out.print("Cidade: "); String cidade = sc.nextLine();
        System.out.print("Número: "); String numero = sc.nextLine();
        System.out.print("Complemento: "); String complemento = sc.nextLine();
        Endereco endereco = new Endereco(cep, estado, cidade, numero, complemento);

        System.out.print("Perfil: "); String perfil = sc.nextLine();
        System.out.print("Senha: "); String senha = sc.nextLine();
        // Dado específico do Enfermeiro
        System.out.print("COREN: "); String coren = sc.nextLine();

        // Cria o objeto Enfermeiro e manda salvar
        Enfermeiro enfermeiro = new Enfermeiro(cpf, nome, email, dataNascimento, endereco, perfil, senha, coren);
        ctrl.cadastrarEnfermeiro(enfermeiro);
    }

    
    // Menu final, pra consultar o prontuário
    private static void menuProntuario(Scanner sc, ControlController ctrl) {
        System.out.println("\n--- PRONTUÁRIO ELETRÔNICO ---");
        System.out.print("Digite o CPF do paciente: ");
        String cpf = sc.nextLine();

        // Verifica se o paciente existe antes de tentar mostrar o prontuário
        Paciente p = (Paciente) ctrl.buscarPessoaPorCpf(cpf);
        if (p != null) {
            // Pede o CPF e chama a função de exibir
            ctrl.exibirProntuario(cpf);
        } else {
            System.out.println("Paciente não encontrado!");
        }
    }
}