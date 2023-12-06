package com.example.wpa_alpha.DataAccessObjects;

import com.example.wpa_alpha.PersistenceModels.Helyszin;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;

public class HelyszinDAO {

    /**
     * Visszaadja id alapján a megfelelő megyét. Ha nincs olyan Helyszin akkor null értéket ad vissza.
     * @param id A kért Helyszin id-je
     * @return Helyszin object
     */
    public static Helyszin getHelyszinById(int id){
        DatabaseConn conn = new DatabaseConn(new Helyszin());
        return conn.getSession().get(Helyszin.class,id);
    }

    /**
     * Visszaadja az összes megyét.
     * @return ArrayList<Helyszin>
     */
    public static ArrayList<Helyszin> getAllHelyszin(){
        DatabaseConn conn = new DatabaseConn(new Helyszin());
        CriteriaBuilder criteriaBuilder = conn.getSession().getCriteriaBuilder();
        CriteriaQuery<Helyszin> criteriaQuery = criteriaBuilder.createQuery(Helyszin.class);
        criteriaQuery.from(Helyszin.class);
        ArrayList<Helyszin> result = (ArrayList<Helyszin>) conn.getSession().createQuery(criteriaQuery).getResultList();
        return result;
    }

    /**
     * Átadott megyét hozzáadja az adatbázishoz.
     * @param item
     */
    public static void addHelyszin(Helyszin item){
        DatabaseConn conn = new DatabaseConn(new Helyszin());
        Transaction transaction = conn.getSession().beginTransaction();
        conn.getSession().save(item);
        transaction.commit();
    }

    /**
     * Átadott megyét felülírja/szerkeszti.
     * @param item
     */
    public static void updateHelyszin(Helyszin item){
        DatabaseConn conn = new DatabaseConn(new Helyszin());
        Transaction transaction = conn.getSession().beginTransaction();
        conn.getSession().update(item);
        transaction.commit();
    }

    /**
     * Átadott megyét törli.
     * @param item
     */
    public static void deleteHelyszin(Helyszin item){
        DatabaseConn conn = new DatabaseConn(new Helyszin());
        Transaction transaction = conn.getSession().beginTransaction();
        conn.getSession().remove(item);
        transaction.commit();
    }

    /**
     * Átadott Helyszin id-je alapján töröl.
     * @param id
     */
    public static void deleteHelyszin(int id){
        Helyszin item = HelyszinDAO.getHelyszinById(id);
        DatabaseConn conn = new DatabaseConn(new Helyszin());
        Transaction transaction = conn.getSession().beginTransaction();
        conn.getSession().remove(item);
        transaction.commit();
    }


}
