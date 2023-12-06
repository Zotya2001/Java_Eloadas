package com.example.wpa_alpha.DataAccessObjects;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

class DatabaseConn<T> {
    private Configuration configuration;
    private StandardServiceRegistryBuilder builder;
    private SessionFactory factory;
    private Session session;


    public DatabaseConn(T item) {
        this.configuration = new Configuration().configure();
        this.configuration.addAnnotatedClass(item.getClass());
        this.builder = new StandardServiceRegistryBuilder().applySettings(this.configuration.getProperties());
        this.factory = this.configuration.buildSessionFactory(this.builder.build());

        this.session = this.factory.openSession();
    }

    public Session getSession() {
        return session;
    }
}