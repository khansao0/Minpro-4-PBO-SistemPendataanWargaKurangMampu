/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pendataanwarga.model;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pendataanwarga.config.HibernateUtil;

/**
 *
 * @author qonitah
 */
public class WargaDAO {

    public boolean tambahWarga(Warga warga) {
        Transaction tr = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tr = session.beginTransaction();
            session.persist(warga);
            tr.commit();
            return true;
        } catch (Exception e) {
            if (tr != null) tr.rollback();
            System.out.println("Gagal simpan data ke DB: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Warga> getAllWarga() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Warga> list = session.createQuery("FROM Warga", Warga.class).list();
            return new ArrayList<>(list);
        } catch (Exception e) {
            System.out.println("Gagal load data dari DB: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean updateWarga(String nik, String nama, String alamat, int tanggungan, double pendapatan, String statusRumah) {
        Transaction tr = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tr = session.beginTransaction();
            Warga target = session.get(Warga.class, nik);
            if (target != null) {
                target.setNama(nama);
                target.setAlamat(alamat);
                target.setJumlahTanggungan(tanggungan);
                if (target.getKriteria() != null) {
                    target.getKriteria().setPendapatanBulanan(pendapatan);
                    target.getKriteria().setStatusRumah(statusRumah);
                }
                session.merge(target);
                tr.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (tr != null) tr.rollback();
            System.out.println("Gagal update data di DB: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteWarga(String nik) {
        Transaction tr = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tr = session.beginTransaction();
            Warga target = session.get(Warga.class, nik);
            if (target != null) {
                session.remove(target);
                tr.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (tr != null) tr.rollback();
            System.out.println("Gagal hapus data di DB: " + e.getMessage());
            return false;
        }
    }
}