package edu.sergradcapstone.groupseven.brewday.controller;


import edu.sergradcapstone.groupseven.brewday.model.Ingredient;
import edu.sergradcapstone.groupseven.brewday.model.Recipe;
import edu.sergradcapstone.groupseven.brewday.model.ResourceNotFoundException;
import edu.sergradcapstone.groupseven.brewday.model.User;
import edu.sergradcapstone.groupseven.brewday.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")

public class LoginController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/login")
    public ResponseEntity processLogin(HttpSession httpSession, @RequestParam("username") String username, @RequestParam("password") String password) {

        System.out.println("entering login controller");
        User user = userRepository.findOneByEmail(username);
        if (user == null) {
            System.out.println("checking if the database has this user to authenticate");

            throw new ResourceNotFoundException("Username", "id", username);
        }
        if (user.getPassword().contentEquals(password)) {
            System.out.println("password from form is "+password);
            System.out.println("password from db is "+user.getPassword());


            System.out.println("checking if the database has the same password as entered during login");

            httpSession.setAttribute("username", username);
            return ResponseEntity.ok("success");
        }
        return ResponseEntity.status(401).build();
    }

    //TO GET USER DATA
    @GetMapping("/{userId}")
    public ResponseEntity findByEmail(HttpSession httpSession, @PathVariable Long userId){
        String username = (String) httpSession.getAttribute("username");

        if(username == null ){
            return ResponseEntity.status(401).body("Operation Unauthorized");
        }
        User user = userRepository.findOneByEmail(username);

        return ResponseEntity.ok(user);
    }


    @RequestMapping(value="/logout",method = RequestMethod.POST)
    public ResponseEntity processLogout(HttpSession httpSession){
        System.out.println("inside logout controller");
        httpSession.invalidate();

        return ResponseEntity.ok("success");
    }


    @PostMapping
    @RequestMapping(value="/signup" ,method= RequestMethod.POST)
    public ResponseEntity processSignUp(HttpSession httpSession, @PathParam("firstname") String firstname,
                                        @PathParam("lastname") String lastname, @PathParam("password") String password,
                                        @PathParam("email") String email,@PathParam("gender") String gender,
                                        @PathParam("addr") String address,@PathParam("city") String city,
                                        @PathParam("state") String state,@PathParam("zip") String zip
                                        )
    {
        System.out.println("inside signup");
        User user = new User();

        if(userRepository.findOneByEmail(email) != null){
            //return ResponseEntity.status(400).body("Username already exists");
            return ResponseEntity.ok("unsuccess");
        }

        String fullAddress = address + new StringBuilder().append(",").append(city).append(",").append(state).append("\n").append(zip).toString();
        String fullAddress1 = address + "," + city + ","+ state + ","+ zip ;

        System.out.println(firstname);
        System.out.println(lastname);
        System.out.println(email);
        System.out.println(password);
        System.out.println(fullAddress1);
        System.out.println(gender);
        //System.out.println(phoneNumber);

        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setPassword(password);
        user.setAddress(fullAddress1);
        user.setGender(gender);
        //user.setPhoneNumber("480-4060-623");

        userRepository.save(user);
        //return ResponseEntity.ok(201);
        return ResponseEntity.ok("success");
    }


    // put mapping
    @RequestMapping(value="/update" ,method= RequestMethod.PUT)
    public ResponseEntity processUpdate(HttpSession httpSession, @PathParam("firstname") String firstname,
                                        @PathParam("lastname") String lastname, @PathParam("password") String password,
                                        @PathParam("email") String email,@PathParam("gender") String gender,
                                        @PathParam("addr") String address,@PathParam("city") String city,
                                        @PathParam("state") String state,@PathParam("zip") String zip,
                                        @PathParam("id") Long id
    )
    {
        User targetUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        //Optional<Recipe> targetRecipe = recipeRepository.findById(id);

        System.out.println("Address before " + address);
        //String fullAddress = address + new StringBuilder().append(",").append(city).append(",").append(state).append("\n").append(zip).toString();
        String fullAddress1 = address + "," + city + ","+ state + ","+ zip ;

        System.out.println(firstname);
        System.out.println(lastname);
        System.out.println(email);
        System.out.println(password);
        System.out.println(fullAddress1);
        System.out.println(gender);

        targetUser.setFirstname(firstname);
        targetUser.setLastname(lastname);
        targetUser.setGender(gender);
        targetUser.setPassword(password);
        targetUser.setEmail(email);
        targetUser.setAddress(fullAddress1);


        User insertedUser = userRepository.save(targetUser);
        return ResponseEntity.ok("Success");


        //User user = new User();

        //User user1 = userRepository.findOneByEmail(email);
       /*if(userRepository.findOneByEmail(email) != null){
            return ResponseEntity.status(400).body("Username already exists");
        }*/

        /* String fullAddress = address + new StringBuilder().append(",").append(city).append(",").append(state).append("\n").append(zip).toString();
        String fullAddress1 = address + "," + city + ","+ state + ","+ zip ;

        System.out.println(firstname);
        System.out.println(lastname);
        System.out.println(email);
        System.out.println(password);
        System.out.println(fullAddress1);
        System.out.println(gender);
        //System.out.println(phoneNumber);

        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setPassword(password);
        user.setAddress(fullAddress1);
        user.setGender(gender);
        //user.setPhoneNumber("480-4060-623");

        userRepository.save(user);
        //return ResponseEntity.ok(201);
        return ResponseEntity.ok("Updated"); */

    }
}
