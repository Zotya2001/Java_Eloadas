package com.example.wpa_alpha.DataAccessObjects;

import com.example.wpa_alpha.PersistenceModels.Megye;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class MegyeDAO {

    /**
     * Visszaadja id alapján a megfelelő megyét. Ha nincs olyan megye akkor null értéket ad vissza.
     * @param id A kért megye id-je
     * @return Megye object
     */
    public static Megye getMegyeById(int id){
        DatabaseConn conn = new DatabaseConn(new Megye());
        return conn.getSession().get(Megye.class,id);
    }

    /**
     * Visszaadja az összes megyét.
     * @return ArrayList<Megye>
     */
    public static ArrayList<Megye> getAllMegye(){
        DatabaseConn conn = new DatabaseConn(new Megye());
        CriteriaBuilder criteriaBuilder = conn.getSession().getCriteriaBuilder();
        CriteriaQuery<Megye> criteriaQuery = criteriaBuilder.createQuery(Megye.class);
        criteriaQuery.from(Megye.class);
        ArrayList<Megye> result = (ArrayList<Megye>) conn.getSession().createQuery(criteriaQuery).getResultList();
        return result;
    }

    /**
     * Átadott megyét hozzáadja az adatbázishoz.
     * @param item
     */
    public static void addMegye(Megye item){
        DatabaseConn conn = new DatabaseConn(new Megye());
        Transaction transaction = conn.getSession().beginTransaction();
        conn.getSession().save(item);
        transaction.commit();
    }

    /**
     * Átadott megyét felülírja/szerkeszti.
     * @param item
     */
    public static void updateMegye(Megye item){
        DatabaseConn conn = new DatabaseConn(new Megye());
        Transaction transaction = conn.getSession().beginTransaction();
        conn.getSession().update(item);
        transaction.commit();
    }

    /**
     * Átadott megyét törli.
     * @param item
     */
    public static void deleteMegye(Megye item){
        DatabaseConn conn = new DatabaseConn(new Megye());
        Transaction transaction = conn.getSession().beginTransaction();
        conn.getSession().remove(item);
        transaction.commit();
    }

    /**
     * Átadott megye id-je alapján töröl.
     * @param id
     */
    public static void deleteMegye(int id){
        Megye item = MegyeDAO.getMegyeById(id);
        DatabaseConn conn = new DatabaseConn(new Megye());
        Transaction transaction = conn.getSession().beginTransaction();
        conn.getSession().remove(item);
        transaction.commit();
    }


}
