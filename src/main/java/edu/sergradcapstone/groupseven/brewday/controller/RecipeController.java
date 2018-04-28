package edu.sergradcapstone.groupseven.brewday.controller;


import edu.sergradcapstone.groupseven.brewday.model.Recipe;
import edu.sergradcapstone.groupseven.brewday.model.ResourceNotFoundException;
import edu.sergradcapstone.groupseven.brewday.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    @Autowired
    RecipeRepository recipeRepository;

    @RequestMapping(value="/newrecipe",method=RequestMethod.POST)
    public ResponseEntity save(HttpSession httpSession,@RequestParam("recipename") String recipename, @RequestParam("ibu") Double ibu, @RequestParam("abv") Double abv, @RequestParam("batchSize") Double batchSize, @RequestParam("Description") String description,
                                       @RequestParam("boilTime") Time boilTime,
                               @RequestParam("brewType") String brewType,
                               @RequestParam("optradio") String optradio){
        System.out.println("before getting username");
        String username = (String) httpSession.getAttribute("username");
        System.out.println("inside post method of recipe");
        System.out.println("This recipe is "+optradio);
        if(username == null){
            return ResponseEntity.status(401).body("Operation Unauthorized. Please Log In");
        }
        Recipe recipe;
        if(optradio.contentEquals("PUBLIC")) {
            recipe = new Recipe(recipename,batchSize, abv, description, ibu, boilTime, brewType, username,"PUBLIC");
        }else{
            recipe = new Recipe(recipename,batchSize, abv, description, ibu, boilTime, brewType, username,"PRIVATE");
        }
        Recipe insertedRecipe = recipeRepository.save(recipe);

        System.out.println("post method of recipe processed");

        return ResponseEntity.ok(201);
        //return new ResponseEntity<>(insertedRecipe, HttpStatus.CREATED);
    }

    // To Get All the recipes with the username same as the one that is logged in
    @GetMapping
    public List<Recipe> get(HttpSession httpSession){
        List<Recipe> recipes = new ArrayList<>();
        String username = (String) httpSession.getAttribute("username");

        //recipeRepository.findByAcess("PUBLIC").iterator().forEachRemaining(x -> recipes.add(x));

        if(username != null){
            recipeRepository.findByEmail(username).iterator().forEachRemaining(x -> recipes.add(x));
        }
        System.out.println(recipes);
        return recipes;
    }

    @GetMapping("/{recipeId}")
    public ResponseEntity findRecipeById(HttpSession httpSession, @PathVariable Long recipeId){

        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", recipeId));
        if(recipe.getEmail().equals("PUBLIC")){
            return ResponseEntity.ok(recipe);
        }
        String username = (String) httpSession.getAttribute("username");
        if(username == null || !username.contentEquals(recipe.getEmail())){
            return ResponseEntity.status(401).body("Operation Unauthorized");
        }
        return ResponseEntity.ok(recipe);
    }

    @GetMapping("/public")
    public List<Recipe> getPublicRecipes(HttpSession httpSession){
        List<Recipe> recipes = new ArrayList<>();
        //String username = (String) httpSession.getAttribute("username");

        recipeRepository.findByAccess("PUBLIC").iterator().forEachRemaining(x -> recipes.add(x));

        System.out.println(recipes);
        return recipes;
    }

    //@PutMapping
    @RequestMapping(value="/editrecipe",method=RequestMethod.PUT)
    //public ResponseEntity<Recipe> save(@Valid @RequestBody Recipe recipe){
        public ResponseEntity editRecipe (HttpSession httpSession,@RequestParam("recipename") String recipename,
                                                  @RequestParam("ibu") Double ibu, @RequestParam("abv") Double abv,
                                                  @RequestParam("batchSize") Double batchSize,
                                                  @RequestParam("Description") String description,
                                                    @RequestParam("boilTime") Time boilTime,
                                                    @RequestParam("brewType") String brewType,
                                                @RequestParam("optradio") String optradio,@RequestParam("id") Long id){

        System.out.println("Inside update method of recipe");
        String username = (String) httpSession.getAttribute("username");
        System.out.println("inside post method of recipe");
        System.out.println("This recipe is "+optradio);

        /*if(username == null){
            return (ResponseEntity<Recipe>) ResponseEntity.status(401);
        }*/
        //Recipe recipe;
        /*if(optradio.contentEquals("PUBLIC")) {
            recipe = new Recipe(recipename,batchSize, abv, description, ibu, boilTime, brewType, username,"PUBLIC");
        }else{
            recipe = new Recipe(recipename,batchSize, abv, description, ibu, boilTime, brewType, username,"PRIVATE");
        }
        Recipe insertedRecipe = recipeRepository.save(recipe); */

        //System.out.println("put method of recipe processed");

        //Recipe recipe;
        //Optional<Recipe> recipe = recipeRepository.findById(id);


        //return ResponseEntity.ok(201);
        Recipe targetRecipe = recipeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", id));
        //Optional<Recipe> targetRecipe = recipeRepository.findById(id);
        targetRecipe.setABV(abv);
        targetRecipe.setBatchSize(batchSize);
        targetRecipe.setAccess(optradio);
        targetRecipe.setBoilTime(boilTime);
        targetRecipe.setBrewType(brewType);
        targetRecipe.setRecipename(recipename);
        targetRecipe.setIBU(ibu);
        targetRecipe.setInstructions(description);

        Recipe insertedRecipe = recipeRepository.save(targetRecipe);

       /* targetRecipe.setABV(abv);
        targetRecipe.setBatchSize(batchSize);
        targetRecipe.setAccess(recipe.getAccess());
        targetRecipe.setBoilTime(recipe.getBoilTime());
        targetRecipe.setBrewType(recipe.getBrewType());
        targetRecipe.setRecipename(recipe.getRecipename());
        targetRecipe.setIBU(recipe.getIBU());
        targetRecipe.setInstructions(recipe.getInstructions());

        Recipe insertedRecipe = recipeRepository.save(recipe);*/
        //return new ResponseEntity<>(insertedRecipe, HttpStatus.CREATED);
        //return new ResponseEntity<Recipe>(targetRecipe);
        return ResponseEntity.ok("success");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteRecipe(HttpSession httpSession, @PathVariable("id") String iD) {
            String[] tokens = iD.split(Pattern.quote("="));
            Long id = Long.parseLong(tokens[1]);
            System.out.println("inside delete method");

            System.out.println("id is "+ id);
        Optional<Recipe> recipe = recipeRepository.findById(id);
        String username = (String) httpSession.getAttribute("username");

        recipeRepository.deleteById(id);
        //recipeRepository.delete(recipe.get());
        return ResponseEntity.ok("success");

        /*if (!recipe.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            if(username == null || !username.contentEquals(recipe.get().getEmail())){
                return ResponseEntity.status(401).body("Operation Unauthorized");
            }
            recipeRepository.deleteById(id);
            //recipeRepository.delete(recipe.get());
            return ResponseEntity.ok("success");
        }*/
    }

}
