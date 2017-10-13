package cz.fi.muni.pa165.tasks;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.validation.ConstraintViolationException;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cz.fi.muni.pa165.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.entity.Category;
import cz.fi.muni.pa165.entity.Product;


@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class Task02 extends AbstractTestNGSpringContextTests {

	@PersistenceUnit
	private EntityManagerFactory emf;

	private Category electro, kitchen;
	private Product flashlight, kitchenRobot, plate;

	@BeforeClass
	public void createObjects(){
		EntityManager em = emf.createEntityManager();

		Category electro = new Category();
		electro.setName("Electro");

		Category kitchen = new Category();
		kitchen.setName("Kitchen");

		Product flashlight = new Product();
		flashlight.setName("Flashlight");

		Product kitchenRobot = new Product();
		kitchenRobot.setName("Kitchen robot");

		Product plate = new Product();
		plate.setName("Plate");

		electro.addProduct(flashlight);
		kitchen.addProduct(kitchenRobot);
		kitchen.addProduct(plate);

		flashlight.addCategory(electro);
		kitchenRobot.addCategory(kitchen);
		plate.addCategory(kitchen);

		em.getTransaction().begin();
		em.persist(electro);
		em.persist(kitchen);
		em.persist(flashlight);
		em.persist(kitchenRobot);
		em.persist(plate);
		em.getTransaction().commit();
		em.close();

		this.electro = electro;
		this.flashlight = flashlight;
		this.kitchen = kitchen;
		this.kitchenRobot = kitchenRobot;
		this.plate = plate;
	}



	private void assertContainsCategoryWithName(Set<Category> categories,
												String expectedCategoryName) {
		for(Category cat: categories){
			if (cat.getName().equals(expectedCategoryName))
				return;
		}

		Assert.fail("Couldn't find category "+ expectedCategoryName+ " in collection "+categories);
	}
	private void assertContainsProductWithName(Set<Product> products,
											   String expectedProductName) {

		for(Product prod: products){
			if (prod.getName().equals(expectedProductName))
				return;
		}

		Assert.fail("Couldn't find product "+ expectedProductName+ " in collection "+products);
	}


}
