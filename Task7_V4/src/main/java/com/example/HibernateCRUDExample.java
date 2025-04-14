package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateCRUDExample {

    public static void main(String[] args) {
        // Налаштовуємо фабрику сесій, використовуючи файл hibernate.cfg.xml
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        // Додаємо всі анотовані класи (не обов’язково, якщо вони вказані в hibernate.cfg.xml)
        configuration.addAnnotatedClass(Hobbyist.class);
        configuration.addAnnotatedClass(Hobby.class);
        configuration.addAnnotatedClass(Profile.class);
        configuration.addAnnotatedClass(Club.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // -------------------
        // CREATE
        // -------------------
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        // Створюємо Profile (OneToOne)
        Profile profile = new Profile("I love sports and music", "http://myprofile.com");

        // Створюємо Hobbyist і встановлюємо Profile
        Hobbyist hobbyist = new Hobbyist("John Doe");
        hobbyist.setProfile(profile);

        // OneToMany: створюємо кілька хобі
        Hobby hobby1 = new Hobby("Football", hobbyist);
        Hobby hobby2 = new Hobby("Guitar", hobbyist);
        hobbyist.getHobbies().add(hobby1);
        hobbyist.getHobbies().add(hobby2);

        // ManyToMany: створюємо кілька клубів
        Club club1 = new Club("Sports Club");
        Club club2 = new Club("Music Club");

        // Додаємо клуби до Hobbyist
        hobbyist.getClubs().add(club1);
        hobbyist.getClubs().add(club2);
        // Для двосторонньої консистентності додаємо Hobbyist до клубів
        club1.getHobbyists().add(hobbyist);
        club2.getHobbyists().add(hobbyist);

        // Зберігаємо Hobbyist (cascade дозволить зберегти і пов'язані сутності)
        session.save(hobbyist);
        tx.commit();
        session.close();
        System.out.println("Створено запис: " + hobbyist);

        // -------------------
        // READ
        // -------------------
        session = sessionFactory.openSession();
        Hobbyist retrieved = session.get(Hobbyist.class, hobbyist.getId());
        System.out.println("Отриманий запис: " + retrieved);
        session.close();

        // -------------------
        // UPDATE
        // -------------------
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        retrieved.setName("John Updated");
        retrieved.getProfile().setBio("Updated bio info");
        session.update(retrieved);
        tx.commit();
        session.close();
        System.out.println("Оновлено запис: " + retrieved);

        // -------------------
        // DELETE
        // -------------------
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.delete(retrieved);
        tx.commit();
        session.close();
        System.out.println("Видалено запис з id: " + hobbyist.getId());

        sessionFactory.close();
    }
}
