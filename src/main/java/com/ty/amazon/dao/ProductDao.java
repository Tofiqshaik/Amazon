package com.ty.amazon.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ty.amazon.dto.Product;

public class ProductDao {
	public EntityManager getEM() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Vaibhav");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager;
	}

	public void saveProd(Product p) {
		EntityTransaction entityTransaction = getEM().getTransaction();
		entityTransaction.begin();
		getEM().persist(p);
		entityTransaction.commit();
	}

	public void removeProd(int id) {
		Product p = getEM().find(Product.class, id);
		EntityTransaction entityTransaction = getEM().getTransaction();
		entityTransaction.begin();
		getEM().remove(p);
		entityTransaction.commit();
	}

	public void getProduct(int id) {
		Product p = getEM().find(Product.class, id);
		if (p != null) {
			System.out.println("Product id is   :" + p.getId());
			System.out.println("Product name is :" + p.getProduct_name());
			System.out.println("Product Brand is:" + p.getBrand());
			System.out.println("Product Cost is :" + p.getProduct_Cost());
		} else {
			System.out.println("no product found");
		}
	}

	public void getAllProducts() {
		Query q = getEM().createQuery("Select p from Product p", Product.class);
		List<Product> l = q.getResultList();
		for (Product p : l) {
			System.out.println("====================================");
			System.out.println("Product Id is: " + p.getId());
			System.out.println("Product Name : " + p.getProduct_name());
			System.out.println("Product Brand:" + p.getBrand());
		}
	}

}
