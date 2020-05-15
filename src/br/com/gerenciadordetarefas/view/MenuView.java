package br.com.gerenciadordetarefas.view;

import br.com.gerenciadordetarefas.dao.exceptions.NonexistentEntityException;
import java.text.ParseException;
import java.util.Scanner;

public class MenuView {

    public MenuView() throws ParseException, NonexistentEntityException, Exception {
        int op;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("------Cadastro de Usuário------");
            System.out.println("1 - Autenticar");
            System.out.println("2 - Cadastrar Usuário");
            System.out.println("0 - Sair");
            op = sc.nextInt();
            switch (op) {
                case 1:
                    autenticarUsuario();
                    break;
                case 2:
                    cadastrarUsuario();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (op != 0);

    }

    public void autenticarUsuario() throws NonexistentEntityException, Exception {
        UsuarioView usuarioView = new UsuarioView();
        usuarioView.autenticar();
    }

    public void cadastrarUsuario() throws NonexistentEntityException, Exception {
        UsuarioView usuarioView = new UsuarioView();
        usuarioView.cadastrarUsuario();
    }

}
