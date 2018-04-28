package edu.sergradcapstone.groupseven.brewday.repositories;


import edu.sergradcapstone.groupseven.brewday.model.Ingredient;
import edu.sergradcapstone.groupseven.brewday.model.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IngredientRepository extends CrudRepository<Ingredient, Long>{
    List<Ingredient> findByUsername(String userName);
}
