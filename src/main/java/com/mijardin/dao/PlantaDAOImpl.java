package com.mijardin.dao;

import com.mijardin.entities.Planta;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class PlantaDAOImpl implements PlantaDAO {
    private final SessionFactory sessionFactory;

    public PlantaDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Planta save(Planta planta) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(planta);
            transaction.commit();
            return planta;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    @Override
    public Planta findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Planta.class, id);
        }
    }

    @Override
    public List<Planta> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Planta", Planta.class).list();
        }
    }

    @Override
    public void update(Planta planta) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(planta);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    @Override
    public void delete(Planta planta) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(planta);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }
}