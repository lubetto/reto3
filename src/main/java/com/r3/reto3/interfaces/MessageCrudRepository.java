package com.r3.reto3.interfaces;

import com.r3.reto3.modelos.Message;
import org.springframework.data.repository.CrudRepository;
/**
 *
 * @author LUIS GERMAN ORTEGA M.
 */
public interface MessageCrudRepository extends CrudRepository<Message, Integer> {
}
