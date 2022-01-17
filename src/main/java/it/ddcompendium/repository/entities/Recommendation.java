package it.ddcompendium.repository.entities;

public class Recommendation {
	private Integer id;
	private User recommendedBy;
	private User recommendedTo;
	private Spell recommendation;

	public User getRecommendedTo() {
		return recommendedTo;
	}

	public void setRecommendedTo(User recommendedTo) {
		this.recommendedTo = recommendedTo;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getRecommendedBy() {
		return recommendedBy;
	}

	public void setRecommendedBy(User recommendedBy) {
		this.recommendedBy = recommendedBy;
	}

	public Spell getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(Spell recommendation) {
		this.recommendation = recommendation;
	}

}
