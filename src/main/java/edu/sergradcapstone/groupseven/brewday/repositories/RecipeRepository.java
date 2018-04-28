package edu.sergradcapstone.groupseven.brewday.repositories;

import edu.sergradcapstone.groupseven.brewday.model.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends CrudRepository<Recipe, Long>{
    List<Recipe> findByBrewType(String brewType);
    List<Recipe> findByEmail(String userName);
    List<Recipe> findByAccess(String access);
    //Recipe findById(Long Id);
    Optional<Recipe> findById(Long ID);

}
