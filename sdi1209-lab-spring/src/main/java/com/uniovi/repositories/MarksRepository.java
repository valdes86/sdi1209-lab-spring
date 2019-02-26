/**
 * 
 */
package com.uniovi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Mark;

/**
 * @author Pablo Rodríguez Valdés
 *
 *  SDI - 2019
 */
public interface MarksRepository extends CrudRepository<Mark, Long> {

}
