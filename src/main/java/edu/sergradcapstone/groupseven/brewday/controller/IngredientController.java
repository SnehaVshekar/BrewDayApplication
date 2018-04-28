package edu.sergradcapstone.groupseven.brewday.controller;

import edu.sergradcapstone.groupseven.brewday.model.Ingredient;
import edu.sergradcapstone.groupseven.brewday.model.Recipe;
import edu.sergradcapstone.groupseven.brewday.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@RestController
    @RequestMapping("/ingredient")
    public class IngredientController {
        @Autowired
        IngredientRepository ingredientRepository;

        @RequestMapping(value="/newingredient",method= RequestMethod.POST)
        public ResponseEntity save(HttpSession httpSession, @RequestParam("type") String type, @RequestParam("quantity") Double quantity,
                                    @RequestParam("name") String name){
            System.out.println("before getting username");
            String username = (String) httpSession.getAttribute("username");
            System.out.println("inside post method of ingredient");
            if(username == null){
                return ResponseEntity.status(401).body("Operation Unauthorized. Please Log In");
            }
            Ingredient ingredient;

                ingredient = new Ingredient(type,quantity,name,username);

            Ingredient insertedIngredient = ingredientRepository.save(ingredient);

            System.out.println("post method of Ingredient processed");

            return ResponseEntity.ok("success");
            //return new ResponseEntity<>(insertedRecipe, HttpStatus.CREATED);
        }

        // to fetch ingredients from database
    @GetMapping
    public List<Ingredient> get(HttpSession httpSession){
        List<Ingredient> ingredient = new ArrayList<Ingredient>();
        String username = (String) httpSession.getAttribute("username");

        if(username != null){
            System.out.println("hello");

            ingredientRepository.findByUsername(username).iterator().forEachRemaining(x -> ingredient.add(x));
        }
        System.out.println(ingredient);
        return ingredient;
    }

}
