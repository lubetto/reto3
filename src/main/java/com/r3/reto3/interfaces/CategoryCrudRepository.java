package com.r3.reto3.interfaces;

import com.r3.reto3.modelos.Category;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author LUIS GERMAN ORTEGA M.
 */
public interface CategoryCrudRepository extends CrudRepository<Category, Integer> {
}
