package br.com.gerenciadordetarefas.controller;

import br.com.gerenciadordetarefas.dao.TarefaDAO;
import br.com.gerenciadordetarefas.dao.exceptions.NonexistentEntityException;
import br.com.gerenciadordetarefas.modelo.Tarefa;
import java.util.ArrayList;
import java.util.List;

public class TarefaController {
    private Tarefa tarefa;
    private final TarefaDAO dao;
    private List<Tarefa> tarefas;
    
    public TarefaController() {
        dao = new TarefaDAO();
        tarefas = new ArrayList<>();
        novaTarefa();
    }
    
    public Tarefa getTarefa() {
        return tarefa;
    }
    
    public void salvarTarefa() {
        dao.create(tarefa);
        novaTarefa();
    }
    
    public void listarTodasTarefas() {
        tarefas = dao.findTarefaEntities();
    }
    
    public void novaTarefa() {
        tarefa = new Tarefa();
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }
    
    public Tarefa buscarTarefaPorId(Long id) {
        this.tarefa = dao.findTarefa(id);
        return tarefa;
    }

    public void removerTarefa() throws NonexistentEntityException {
        dao.destroy(tarefa.getId());
    }
    
     public void atualizarTarefa() throws NonexistentEntityException, Exception {
        dao.edit(tarefa);
    }
     
    public void buscarTarefasFinalizadas() {
        tarefas = dao.buscarTarefasFinalizadas();
    }
    
     public void buscarTarefasPorPalavra(String palavra) {
        tarefas = dao.buscarTarefasPorPalavra(palavra);
    }
    
    public void buscarTarefasPorOrdemDecrescente() {
        tarefas = dao.buscarTarefasPorOrdemDecrescente();
    }
}
