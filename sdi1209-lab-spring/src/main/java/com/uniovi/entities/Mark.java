/**
 * 
 */
package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Pablo Rodríguez Valdés
 *
 *         SDI - 2019
 */

@Entity
public class Mark {

	@Id
	@GeneratedValue
	private Long id;
	private String description;
	private Double score;
	private Boolean resend = false;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	/**
	 * Constructor por defecto
	 */
	public Mark() {
	}

	/**
	 * Constructor con parámetros
	 * 
	 * @param id          identificador de la nota
	 * @param description descripcion de la nota
	 * @param score       nota nuumérica
	 */
	public Mark(Long id, String description, Double score) {
		super();
		this.id = id;
		this.description = description;
		this.score = score;
	}

	public Mark(String description, Double score, User user) {
		super();
		this.description = description;
		this.score = score;
		this.user = user;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the score
	 */
	public Double getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(Double score) {
		this.score = score;
	}

	/**
	 * @return the User
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param resend the User to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the resend
	 */
	public Boolean getResend() {
		return resend;
	}

	/**
	 * @param resend the resend to set
	 */
	public void setResend(Boolean resend) {
		this.resend = resend;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Mark [id=" + id + ", description=" + description + ", score=" + score + "]";
	}

}
