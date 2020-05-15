package br.com.gerenciadordetarefas.view;

import br.com.gerenciadordetarefas.controller.UsuarioController;
import br.com.gerenciadordetarefas.dao.exceptions.NonexistentEntityException;
import br.com.gerenciadordetarefas.modelo.Usuario;
import java.util.Scanner;

public class UsuarioView {

    private UsuarioController usuarioController;
    private TarefaView tarefaView;

    public UsuarioView() {
        usuarioController = new UsuarioController();
    }

    public void autenticar() throws NonexistentEntityException, Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("------Autenticar------");
        System.out.println("Entre com o email do usuário:");
        String email = sc.nextLine();
        System.out.println("Entre com a senha do usuário:");
        String senha = sc.nextLine();
        try {
            usuarioController.autenticarUsuario(email, senha);
            Usuario usuario = this.usuarioController.getUsuario();
            TarefaView tarefaView = new TarefaView(usuario);
            tarefaView.menu();
        } finally {
            System.out.println("Usuário ou senha não conferem!");
        }
    }

    public void cadastrarUsuario() {
        System.out.println("------Cadastro de Usuário------");
        Scanner sc = new Scanner(System.in);
        System.out.println("Entre com o nome de usuário:");
        usuarioController.getUsuario().setNome(sc.nextLine());
        System.out.println("Entre com o email do usuário:");
        usuarioController.getUsuario().setEmail(sc.nextLine());
        System.out.println("Entre com a senha do usuário:");
        usuarioController.getUsuario().setSenha(sc.nextLine());
        usuarioController.salvarUsuario();
    }
}
