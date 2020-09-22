package br.com.publicaproway.desafio.dto;

public class GameDTO {
		
	private String game;
	private Integer points;

	public GameDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public GameDTO(String game, Integer points) {
		super();
		this.game = game;
		this.points = points;
	}

	/**
	 * @return the game
	 */
	public String getGame() {
		return game;
	}

	/**
	 * @param game the game to set
	 */
	public void setGame(String game) {
		this.game = game;
	}

	/**
	 * @return the points
	 */
	public Integer getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(Integer points) {
		this.points = points;
	}

	

}
