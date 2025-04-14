package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.Set;

public class HibernateCRUDExample {

    public static void main(String[] args) {
        // Налаштовуємо фабрику сесій Hibernate, використовуючи конфігураційний файл hibernate.cfg.xml
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // --- CREATE ---
        // Створення запису в таблиці hobbyist із пов’язаними hobby
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Hobbyist hobbyist = new Hobbyist("Alice");

        // Створюємо декілька хобі для hobbyist
        Hobby hobby1 = new Hobby("Reading", hobbyist);
        Hobby hobby2 = new Hobby("Hiking", hobbyist);

        // Додаємо хобі до набору
        Set<Hobby> hobbies = new HashSet<>();
        hobbies.add(hobby1);
        hobbies.add(hobby2);
        hobbyist.setHobbies(hobbies);

        // Зберігаємо об’єкт (завдяки cascade збережуться також і Hobby)
        session.save(hobbyist);

        tx.commit();
        session.close();
        System.out.println("Створено запис: " + hobbyist);

        // --- READ ---
        session = sessionFactory.openSession();
        Hobbyist retrieved = session.get(Hobbyist.class, hobbyist.getId());
        System.out.println("Отриманий запис: " + retrieved);
        session.close();

        // --- UPDATE ---
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        retrieved.setName("Alice Updated");
        session.update(retrieved);
        tx.commit();
        session.close();
        System.out.println("Оновлено запис: " + retrieved);

        // --- DELETE ---
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.delete(retrieved);
        tx.commit();
        session.close();
        System.out.println("Видалено запис з id: " + hobbyist.getId());

        // --- Генерація кількох записів ---
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        for (int i = 1; i <= 5; i++) {
            Hobbyist h = new Hobbyist("Hobbyist " + i);
            Hobby h1 = new Hobby("Hobby A", h);
            Hobby h2 = new Hobby("Hobby B", h);
            Set<Hobby> hobbySet = new HashSet<>();
            hobbySet.add(h1);
            hobbySet.add(h2);
            h.setHobbies(hobbySet);
            session.save(h);
        }
        tx.commit();
        session.close();
        System.out.println("Згенеровано кілька записів у таблицях.");

        sessionFactory.close();
    }
}
