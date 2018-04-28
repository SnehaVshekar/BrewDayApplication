package edu.sergradcapstone.groupseven.brewday.model;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;



    @Entity
    @Table(name="Feedback")
    public class Feedback {

        protected Feedback(){
        }

        public Feedback(String name, String email, String phone,String message) {
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.message = message;
        }
        //foreign key
        //user id

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;

        @Temporal(TIMESTAMP)
        @Column(name="created_date", nullable = false)
        private Date createdDate;

        @Column(name="name")
        private String name;

        @Column(name="email")
        private String email;

        @Column(name="phone")
        private String phone;

        @Column(name="message")
        private String message;


        @PrePersist
        protected void onCreate() {
            createdDate = new Date();
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public Date getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(Date createdDate) {
            this.createdDate = createdDate;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
