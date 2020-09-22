package br.com.publicaproway.desafio.models;

public class Game {

	private Integer id;
	private String game;
	private Integer points;
	private Integer maxPointSeason;
	private Integer minPointSeason;
	private boolean maxBreakPointSeason;
	private boolean minBreakPointSeason;
	

	public Game() {	}
	
	public Game(Integer id, String game, Integer points, Integer maxPointSeason, Integer minPointSeason,
			boolean maxBreakPointSeason, boolean minBreakPointSeason) {
		super();
		this.id = id;
		this.game = game;
		this.points = points;
		this.maxPointSeason = maxPointSeason;
		this.minPointSeason = minPointSeason;
		this.maxBreakPointSeason = maxBreakPointSeason;
		this.minBreakPointSeason = minBreakPointSeason;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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

	/**
	 * @return the maxPointSeason
	 */
	public Integer getMaxPointSeason() {
		return maxPointSeason;
	}

	/**
	 * @param maxPointSeason the maxPointSeason to set
	 */
	public void setMaxPointSeason(Integer maxPointSeason) {
		this.maxPointSeason = maxPointSeason;
	}

	/**
	 * @return the minPointSeason
	 */
	public Integer getMinPointSeason() {
		return minPointSeason;
	}

	/**
	 * @param minPointSeason the minPointSeason to set
	 */
	public void setMinPointSeason(Integer minPointSeason) {
		this.minPointSeason = minPointSeason;
	}

	/**
	 * @return the maxBreakPointSeason
	 */
	public boolean isMaxBreakPointSeason() {
		return maxBreakPointSeason;
	}

	/**
	 * @param maxBreakPointSeason the maxBreakPointSeason to set
	 */
	public void setMaxBreakPointSeason(boolean maxBreakPointSeason) {
		this.maxBreakPointSeason = maxBreakPointSeason;
	}

	/**
	 * @return the minBreakPointSeason
	 */
	public boolean isMinBreakPointSeason() {
		return minBreakPointSeason;
	}

	/**
	 * @param minBreakPointSeason the minBreakPointSeason to set
	 */
	public void setMinBreakPointSeason(boolean minBreakPointSeason) {
		this.minBreakPointSeason = minBreakPointSeason;
	}

	
}
