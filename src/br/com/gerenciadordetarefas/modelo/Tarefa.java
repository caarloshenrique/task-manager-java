package br.com.gerenciadordetarefas.modelo;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
    @NamedQuery(name = "Tarefa.findByFinalizado", query = "SELECT t FROM Tarefa t WHERE t.finalizado = true AND t.usuario = :usuario"),
    @NamedQuery(name = "Tarefa.findByPalavra", query = "SELECT t FROM Tarefa t WHERE t.descricao LIKE :palavra AND t.usuario = :usuario"),
    @NamedQuery(name = "Tarefa.findAllDesc", query = "SELECT t FROM Tarefa t WHERE t.dataFinalizacao IS NOT NULL AND t.usuario = :usuario ORDER BY t.id DESC"),
    @NamedQuery(name = "Tarefa.findByUsuario", query = "SELECT t FROM Tarefa t WHERE t.usuario = :usuario"),
    @NamedQuery(name = "Tarefa.findByTarefaIdAndUsuario", query = "SELECT t FROM Tarefa t WHERE t.id = :tarefaId AND t.usuario = :usuario")
})
@SuppressWarnings("JPQLValidation")
public class Tarefa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String descricao;
    
    @Temporal(TemporalType.DATE)
    private Calendar dataFinalizacao;
    
    private boolean finalizado;
    
    @ManyToOne
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Calendar getDataFinalizacao() {
        return dataFinalizacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setDataFinalizacao(Calendar dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    

    @Override
    public String toString() {
        return  "id: " + id + ", descricao: " + descricao + ", dataFinalizacao: " + dataFinalizacao + ", finalizado: " + finalizado + "usuario: " + usuario.getNome();
    }
}
