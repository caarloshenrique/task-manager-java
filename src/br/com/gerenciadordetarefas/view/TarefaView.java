package br.com.gerenciadordetarefas.view;

import br.com.gerenciadordetarefas.controller.TarefaController;
import br.com.gerenciadordetarefas.controller.UsuarioController;
import br.com.gerenciadordetarefas.dao.exceptions.NonexistentEntityException;
import br.com.gerenciadordetarefas.modelo.Tarefa;
import br.com.gerenciadordetarefas.modelo.Usuario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class TarefaView {
    private TarefaController tarefaController;
    private UsuarioController usuarioController;
    private Usuario usuario;
    
    public TarefaView(Usuario usuario) throws NonexistentEntityException, Exception {
        tarefaController = new TarefaController();
        this.usuario = usuario;
    }
    
    public void menu() throws ParseException, NonexistentEntityException, Exception {
        int op;
        Scanner sc = new Scanner(System.in);
        do {            
            System.out.println("------Gerenciador de Tarefas------");
            System.out.println("1 - Cadastrar Tarefas");
            System.out.println("2 - Listar Tarefas");
            System.out.println("3 - Remover Tarefa por ID");
            System.out.println("4 - Atualizar Tarefa por ID");
            System.out.println("5 - Buscar tarefas finalizadas");
            System.out.println("6 - Buscar tarefas por palavra");
            System.out.println("7 - Buscar tarefas em ordem decrescente");
            System.out.println("0 - Sair");
            op = sc.nextInt();
            switch(op) {
                case 1:
                    cadastrarTarefa();
                    break;
                case 2:
                    listarTarefas();
                    break;
                case 3:
                    removerPorId();
                    break; 
                case 4:
                    atualizarPorId();
                    break; 
                case 5:
                    buscarTarefasFinalizadas();
                    break; 
                case 6:
                     buscarTarefasPorPalavra();
                     break;
                case 7:
                    buscarTarefasPorOrdemDecrescente();
                    break;
                case 0:
                    System.exit(0);
                    break;    
                default:
                    System.out.println("Opção inválida!");
            }
        } while (op != 0);
        
    }
    
    public void cadastrarTarefa() throws ParseException {
         Scanner sc = new Scanner(System.in);
         System.out.println("Entre com a descrição da tarefa:");
         tarefaController.getTarefa().setDescricao(sc.nextLine());
         System.out.println("A tarefa está finalizada? (sim|não)");
         boolean resposta = sc.next().equals("sim");
         tarefaController.getTarefa().setFinalizado(resposta);
         if(resposta) {
             SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
             System.out.println("Entre com a data de finalização da tarefa:");
             Date data = sdf.parse(sc.next());
             Calendar calendario = Calendar.getInstance();
             calendario.setTime(data);
             tarefaController.getTarefa().setDataFinalizacao(calendario);
         } else {
             tarefaController.getTarefa().setDataFinalizacao(null);
         }
         tarefaController.getTarefa().setUsuario(usuario);
         tarefaController.salvarTarefa();
    }
    
    public void listarTarefas() {
        tarefaController.listarTodasTarefas();
        for (Tarefa tarefa : tarefaController.getTarefas()) {
            System.out.println(tarefa);
        }
    }

    public void removerPorId() throws NonexistentEntityException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Entre com o ID da tarefa a ser removida:");
        long id = sc.nextLong();
        if(tarefaController.buscarTarefaPorId(id) != null) {
            sc.nextLine();
            System.out.println("Deseja remover a tarefa: " + tarefaController.getTarefa());
            String resp = sc.nextLine();
            if(resp.equals("sim")) {
                tarefaController.removerTarefa();
                System.out.println("Tarefa removida!");
            } else {
                System.out.println("Tarefa não removida!");
            }
        } else {
            System.out.println("A tarefa não existe!");
        }
    }

    public void atualizarPorId() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Entre com o ID da tarefa a ser atualizada:");
        long id = sc.nextLong();
        if(tarefaController.buscarTarefaPorId(id) != null) {
            sc.nextLine();
            System.out.println("Descrição: " + tarefaController.getTarefa().getDescricao());
            System.out.println("Finalizado?: " + tarefaController.getTarefa().isFinalizado());
            System.out.println("Data finalização: " + tarefaController.getTarefa().getDataFinalizacao());
            System.out.println("----------------------");
            System.out.print("Descrição: ");
            tarefaController.getTarefa().setDescricao(sc.nextLine());
            System.out.print("A tarefa está finalizada? (sim|não)");
            boolean resposta = sc.next().equals("sim");
            tarefaController.getTarefa().setFinalizado(resposta);
            if(resposta) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
                System.out.print("Data de finalização da tarefa:");
                Date data = sdf.parse(sc.next());
                Calendar calendario = Calendar.getInstance();
                calendario.setTime(data);
                tarefaController.getTarefa().setDataFinalizacao(calendario);
            } else {
                tarefaController.getTarefa().setDataFinalizacao(null);
            }
            tarefaController.atualizarTarefa();
            System.out.println("Tarefa atualizada com sucesso!");
        } else {
            System.out.println("A tarefa não existe!");
        }
    }
    
    public void buscarTarefasFinalizadas() {
        tarefaController.buscarTarefasFinalizadas();
        for (Tarefa tarefa : tarefaController.getTarefas()) {
            System.out.println(tarefa);
        }
    }
    
    public void buscarTarefasPorPalavra() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Entre com a palavra para buscar as tarefas:");
        String palavra = sc.next();
        tarefaController.buscarTarefasPorPalavra(palavra);
        for (Tarefa tarefa : tarefaController.getTarefas()) {
            System.out.println(tarefa);
        }
    }
    
    public void buscarTarefasPorOrdemDecrescente() {
        tarefaController.buscarTarefasPorOrdemDecrescente();
        for (Tarefa tarefa : tarefaController.getTarefas()) {
            System.out.println(tarefa);
        }
    }
}
