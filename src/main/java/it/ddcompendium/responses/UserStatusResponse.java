package it.ddcompendium.responses;

import it.ddcompendium.repository.entities.User;

public class UserStatusResponse {
	private User user;
	private Status status;

	public UserStatusResponse() {
	}

	public UserStatusResponse(User user, Status status) {
		super();
		this.user = user;
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
