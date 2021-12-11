package dao;

import entity.Song;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HSFUtil;

import java.util.List;

public class SongDAO {
    public boolean insertSong(Song song) {
        Transaction transaction = null;
        try (Session session = HSFUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(song);
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

    public Song selectSong(int id) {
        Transaction transaction = null;
        Song song = null;
        try (Session session = HSFUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            song = session.get(Song.class, id);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return song;
    }

    public List<Song> selectSongs() {
        Transaction transaction = null;
        List<Song> songs = null;
        try (Session session = HSFUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            songs = session.createQuery("from Song", Song.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return songs;
    }

    public List<Song> selectSongsByName(String name) {
        Transaction transaction = null;
        List<Song> songs = null;
        try (Session session = HSFUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            name = name + "%";

            System.out.println(name);
            songs = session
                    .createQuery("from Song a where upper(trim(a.nameSong)) like upper(trim(:name))", Song.class)
                    .setParameter("name", name)
                    .list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return songs;
    }

    public boolean deleteSong(int id) {
        Transaction transaction = null;
        try (Session session = HSFUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Song song = session.get(Song.class, id);
            if (song != null) {
                session.delete(song);
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

    public boolean updateSong(Song song) {
        Transaction transaction = null;
        try (Session session = HSFUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(song);
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

