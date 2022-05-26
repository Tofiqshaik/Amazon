package com.ty.amazon.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ty.amazon.dto.User;

public class UserDao {
	public EntityManager getEM() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Vaibhav");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager;
	}

	public void saveUser(User u) {
		EntityTransaction entityTransaction = getEM().getTransaction();
		entityTransaction.begin();
		getEM().persist(u);
		entityTransaction.commit();
	}

	public void UpdateUser(User u) {
		EntityTransaction entityTransaction = getEM().getTransaction();
		entityTransaction.begin();
		getEM().merge(u);
		entityTransaction.commit();
	}

	public void removeUser(int id) {
		EntityManager eManager = getEM();
		EntityTransaction entityTransaction = eManager.getTransaction();
		User u = eManager.find(User.class, id);
		if (u != null) {
			entityTransaction.begin();
			eManager.remove(u);
			System.out.println("User removed");
			entityTransaction.commit();
		} else {
			System.out.println("User not exist for give id");
		}
	}

	public void getUser(int id) {
		EntityManager eManager = getEM();
		User u = eManager.find(User.class, id);
		if (u != null) {
			System.out.println("Name of User     :" + u.getName());
			System.out.println("Email of User    :" + u.getEmail());
			System.out.println("Phone No of User :" + u.getNo());
			System.out.println("Age of User      :" + u.getAge());
		} else {
			System.out.println("User is not Present");
		}
	}
	public void getAllUsers() {
		Query query=getEM().createQuery("Select u from User u ",User.class);
		List<User> l = query.getResultList();
		System.out.println("============================================================");
		for (User u : l) {
			System.out.println("Id is    : " + u.getId());
			System.out.println("Name of User is :" + u.getName());
			System.out.println("=======================================================");
		}
	}
}
