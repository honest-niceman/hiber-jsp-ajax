package dao;

import entity.Artist;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HSFUtil;

import java.util.List;

public class ArtistDAO {
    public boolean insertArtist(Artist artist) {
        Transaction transaction = null;
        try (Session session = HSFUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(artist);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Artist selectArtist(int id) {
        Transaction transaction = null;
        Artist artist = null;
        try (Session session = HSFUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            artist = session.get(Artist.class, id);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return artist;
    }

    public List<Artist> selectArtists() {
        Transaction transaction = null;
        List<Artist> artists = null;
        try (Session session = HSFUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            artists = session.createQuery("from Artist", Artist.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return artists;
    }

    public List<Artist> selectArtistsByName(String name) {
        Transaction transaction = null;
        List<Artist> artists = null;
        try (Session session = HSFUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            name = name + "%";

            System.out.println(name);
            artists = session
                    .createQuery("from Artist a where upper(trim(a.nameArtist)) like upper(trim(:name))", Artist.class)
                    .setParameter("name", name)
                    .list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return artists;
    }

    public boolean deleteArtist(int id) {
        Transaction transaction = null;
        try (Session session = HSFUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Artist artist = session.get(Artist.class, id);
            if (artist != null) {
                session.delete(artist);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateArtist(Artist artist) {
        Transaction transaction = null;
        try (Session session = HSFUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(artist);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
