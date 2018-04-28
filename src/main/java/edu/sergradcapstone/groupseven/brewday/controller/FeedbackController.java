package edu.sergradcapstone.groupseven.brewday.controller;

import edu.sergradcapstone.groupseven.brewday.model.Feedback;
import edu.sergradcapstone.groupseven.brewday.model.Ingredient;
import edu.sergradcapstone.groupseven.brewday.model.Recipe;
import edu.sergradcapstone.groupseven.brewday.repositories.FeedbackRepository;
import edu.sergradcapstone.groupseven.brewday.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;


    @RestController
    @RequestMapping("/feedback")
    public class FeedbackController {

        @Autowired
        FeedbackRepository feedbackRepository;

        @RequestMapping(value="/newfeedback",method= RequestMethod.POST)
        public ResponseEntity save(HttpSession httpSession, @RequestParam("name") String name, @RequestParam("email") String email,
                                   @RequestParam("phone") String phone,@RequestParam("message") String message) throws MessagingException {

            System.out.println("inside post method of feedback");

            Feedback feedback;

            feedback = new Feedback(name,email,phone,message);

            Feedback insertedFeedback = feedbackRepository.save(feedback);

            System.out.println("post method of feedback processed");

            /*JavaMailSender sender = null;

            //MimeMessage messageinfo = sender.createMimeMessage();
            //MimeMessageHelper helper = new MimeMessageHelper(messageinfo);

            helper.setTo("brewday7@gmail.com");

            helper.setText("Feedback");
            helper.setSubject("Feedback from "+ "name");*/

            //sender.send(messageinfo);

            return ResponseEntity.ok(201);
            //return new ResponseEntity<>(insertedRecipe, HttpStatus.CREATED);
        }


        }
