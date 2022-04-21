package com.fpt.main.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.fpt.main.entity.Customer;
import com.fpt.main.entity.DishCategories;
import com.fpt.main.entity.Dishes;
import com.fpt.main.entity.Offer;
import com.fpt.main.entity.Order;
import com.fpt.main.entity.OrderItem;
import com.fpt.main.entity.Restaurant;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer{
	
	private EntityManager entityManager;
	
	@Value("${allowed.origins}")
	private String[] theAllowedOrigins;
	
	@Autowired
	public MyDataRestConfig(EntityManager _entityManager) {
		entityManager = _entityManager;
	}

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		
		HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PATCH};
	
		disableHttpMethods(Dishes.class, config, theUnsupportedActions);
		disableHttpMethods(DishCategories.class, config, theUnsupportedActions);

		disableHttpMethods(Restaurant.class, config, theUnsupportedActions);
		
		disableHttpMethods(Order.class, config, theUnsupportedActions);
		disableHttpMethods(OrderItem.class, config, theUnsupportedActions);
		
		disableHttpMethods(Customer.class, config, theUnsupportedActions);
		disableHttpMethods(Offer.class, config, theUnsupportedActions);
		
		// call an internal helper method
		exposeId(config);
		
		
		//configure cors mapping
		cors.addMapping(config.getBasePath() + "/**").allowedOrigins(theAllowedOrigins);
	}
	
    private void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }

	private void exposeId(RepositoryRestConfiguration config) {		
		// expose entity ids	
		// get a list of all entity classes from the entity manager
		Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();		
		
		// create an array of the entities type
		List<Class> entityClasses = new ArrayList<>();
		
		//get the entity types for the entities 
		for (EntityType tempEntityType : entities) {
			entityClasses.add(tempEntityType.getJavaType());
		}	
		// expose the entity ids for the array of entity/domain types
		Class[] domainTypes = entityClasses.toArray(new Class[entityClasses.size()]);
		config.exposeIdsFor(domainTypes);
		
	}
}
