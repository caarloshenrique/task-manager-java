package br.com.gerenciadordetarefas.dao;

import br.com.gerenciadordetarefas.dao.exceptions.NonexistentEntityException;
import br.com.gerenciadordetarefas.modelo.Tarefa;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class TarefaDAO implements Serializable {

    public TarefaDAO() {
        this.emf = Persistence.createEntityManagerFactory("TarefasPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tarefa tarefa) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tarefa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tarefa tarefa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tarefa = em.merge(tarefa);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tarefa.getId();
                if (findTarefa(id) == null) {
                    throw new NonexistentEntityException("The tarefa with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tarefa tarefa;
            try {
                tarefa = em.getReference(Tarefa.class, id);
                tarefa.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tarefa with id " + id + " no longer exists.", enfe);
            }
            em.remove(tarefa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tarefa> findTarefaEntities() {
        return findTarefaEntities(true, -1, -1);
    }

    public List<Tarefa> findTarefaEntities(int maxResults, int firstResult) {
        return findTarefaEntities(false, maxResults, firstResult);
    }

    private List<Tarefa> findTarefaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tarefa.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Tarefa findTarefa(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tarefa.class, id);
        } finally {
            em.close();
        }
    }

    public int getTarefaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tarefa> rt = cq.from(Tarefa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
     public List<Tarefa> buscarTarefasFinalizadas() {
         EntityManager em = null;
         List<Tarefa> tarefas = null;
         try {
             em = getEntityManager();
             em.getTransaction().begin();
             Tarefa tarefa;
             tarefas = em.createNamedQuery("Tarefa.findByFinalizado")
                     .getResultList();
         } finally {
            em.close();
         }
         return tarefas;
     }
     
     public List<Tarefa> buscarTarefasPorPalavra(String palavra) {
         EntityManager em = null;
         List<Tarefa> tarefas = null;
         try {
             em = getEntityManager();
             em.getTransaction().begin();
             Tarefa tarefa;
             tarefas = em.createNamedQuery("Tarefa.findByPalavra")
                     .setParameter("palavra", "%" + palavra + "%")
                     .getResultList();
         } finally {
            em.close();
         }
         return tarefas;
     }
     
     public List<Tarefa> buscarTarefasPorOrdemDecrescente() {
         EntityManager em = null;
         List<Tarefa> tarefas = null;
         try {
             em = getEntityManager();
             em.getTransaction().begin();
             Tarefa tarefa;
             tarefas = em.createNamedQuery("Tarefa.findAllDesc")
                     .getResultList();
         } finally {
            em.close();
         }
         return tarefas;
     }
    
}
