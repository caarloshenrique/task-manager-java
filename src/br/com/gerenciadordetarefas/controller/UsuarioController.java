package br.com.gerenciadordetarefas.controller;

import br.com.gerenciadordetarefas.dao.UsuarioDAO;
import br.com.gerenciadordetarefas.modelo.Usuario;

public class UsuarioController {
    private Usuario usuario;
    private final UsuarioDAO dao;
    
    public UsuarioController() {
        dao = new UsuarioDAO();
        novoUsuario();
    }
    
     public Usuario getUsuario() {
        return usuario;
    }
     
    public void autenticarUsuario(String email, String senha) {
       this.usuario = dao.buscarUsuarioPorEmailESenha(email, senha);
        System.out.println("Usuário: " + this.usuario);
    }
    
    public void salvarUsuario() {
        dao.create(usuario);   
    }
    
    public void novoUsuario() {
        usuario = new Usuario();
    }
}
