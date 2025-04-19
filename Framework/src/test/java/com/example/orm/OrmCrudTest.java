package com.example.orm;

import com.example.orm.entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static org.testng.Assert.assertTrue;

@SuppressWarnings("deprecation")
public class OrmCrudTest {

    @Test(groups = "orm")
    public void testCreateAndRead() {
        Session session = DatabaseUtil.getSession();
        Transaction tx = session.beginTransaction();

        session.createQuery("delete from Issue").executeUpdate();
        session.createQuery("delete from Project").executeUpdate();
        session.createQuery("delete from UserProfile").executeUpdate();
        session.createQuery("delete from User").executeUpdate();

        User user = new User();
        user.username = "romad";

        UserProfile profile = new UserProfile();
        profile.bio = "QA Automation";
        profile.user = user;
        user.profile = profile;

        Project project1 = new Project();
        project1.name = "Framework";

        Issue issue = new Issue();
        issue.title = "Add database integration";
        issue.project = project1;
        project1.issues.add(issue);

        user.projects.add(project1);
        project1.users.add(user);

        session.persist(user);
        session.persist(project1);

        tx.commit();
        session.close();

        Session session2 = DatabaseUtil.getSession();
        List<User> users = session2.createQuery("from User", User.class).list();
        assertTrue(users.size() > 0);
        session2.close();
    }

    @Test(dependsOnMethods = "testCreateAndRead")
    public void testUpdate() {
        Session session = DatabaseUtil.getSession();
        Transaction tx = session.beginTransaction();

        List<User> users = session.createQuery("from User where username = 'romad'", User.class).list();
        assertTrue(users.size() > 0);
        User user = users.get(0);

        user.username = "updatedRomad";
        session.merge(user);
        tx.commit();
        session.close();

        Session session2 = DatabaseUtil.getSession();
        List<User> updatedUsers = session2.createQuery("from User where username = 'updatedRomad'", User.class).list();
        assertTrue(updatedUsers.size() > 0);
        session2.close();
    }

    @Test(dependsOnMethods = "testUpdate")
    public void testDelete() {
        Session session = DatabaseUtil.getSession();
        Transaction tx = session.beginTransaction();

        Optional<User> userOpt = session.createQuery("from User where username = 'updatedRomad'", User.class)
                                        .uniqueResultOptional();

        userOpt.ifPresent(session::remove);

        tx.commit();
        session.close();

        Session session2 = DatabaseUtil.getSession();
        List<User> users = session2.createQuery("from User where username = 'updatedRomad'", User.class).list();
        assertTrue(users.isEmpty());
        session2.close();
    }
}