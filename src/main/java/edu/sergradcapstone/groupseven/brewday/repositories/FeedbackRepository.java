package edu.sergradcapstone.groupseven.brewday.repositories;

import edu.sergradcapstone.groupseven.brewday.model.Feedback;
import org.springframework.data.repository.CrudRepository;

public interface FeedbackRepository extends CrudRepository<Feedback, Long> {
}
