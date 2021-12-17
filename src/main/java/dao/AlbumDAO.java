package dao;

import entity.Album;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HSFUtil;

import java.util.List;

public class AlbumDAO {
    public boolean insertAlbum(Album album) {
        Transaction transaction = null;
        try (Session session = HSFUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(album);
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

    public Album selectAlbum(int id) {
        Transaction transaction = null;
        Album album = null;
        try (Session session = HSFUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            album = session.get(Album.class, id);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return album;
    }

    public List<Album> selectAlbums() {
        Transaction transaction = null;
        List<Album> albums = null;
        try (Session session = HSFUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            albums = session.createQuery("from Album", Album.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return albums;
    }

    public List<Album> selectAlbumsByName(String name) {
        Transaction transaction = null;
        List<Album> albums = null;
        try (Session session = HSFUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            name = name + "%";

            System.out.println(name);
            albums = session
                    .createQuery("from Album a where upper(trim(a.nameAlbum)) like upper(trim(:name))", Album.class)
                    .setParameter("name", name)
                    .list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return albums;
    }

    public boolean deleteAlbum(int id) {
        Transaction transaction = null;
        try (Session session = HSFUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Album album = session.get(Album.class, id);
            if (album != null) {
                session.delete(album);
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

    public boolean updateAlbum(Album album) {
        Transaction transaction = null;
        try (Session session = HSFUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(album);
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

    public List<Album> selectAlbumsByArtistName(String name) {
        Transaction transaction = null;
        List<Album> albums = null;
        try (Session session = HSFUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            name = name + "%";

            System.out.println(name);
            albums = session
                    .createQuery("select a from Album a join a.idArtist where upper(trim(a.idArtist.nameArtist)) like upper(trim(:name))", Album.class)
                    .setParameter("name", name)
                    .list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return albums;
    }
}
