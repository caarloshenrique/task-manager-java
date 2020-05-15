package br.com.gerenciadordetarefas.executavel;

import br.com.gerenciadordetarefas.dao.exceptions.NonexistentEntityException;
import br.com.gerenciadordetarefas.view.MenuView;
import java.sql.SQLException;

public class Executavel {
    public static void main(String[] args) throws SQLException, NonexistentEntityException, Exception {
        MenuView mv = new MenuView();
    }
}
