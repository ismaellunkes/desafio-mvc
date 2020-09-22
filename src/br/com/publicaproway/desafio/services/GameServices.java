package br.com.publicaproway.desafio.services;

import java.util.ArrayList;
import java.util.List;

import br.com.publicaproway.desafio.db.Database;
import br.com.publicaproway.desafio.dto.GameDTO;
import br.com.publicaproway.desafio.models.Game;

public class GameServices {
	
	Game game;
	Database db = new Database();
	boolean minBreakPointSeason, maxBreakPointSeason;

	public GameServices() {}

	public void addGame(GameDTO gameDTO) {
		
		this.game = new Game();
		this.game.setId(getNewId());
		this.game.setGame(getGameName());
		this.game.setPoints(gameDTO.getPoints());
		this.game.setMaxPointSeason(maxPointSeason());
		this.game.setMaxBreakPointSeason(maxBreakPointSeason);
		this.game.setMinPointSeason(minPointSeason());
		this.game.setMinBreakPointSeason(minBreakPointSeason);
		
		db.save(game);				
	}
	
	public List<GameDTO> findAll() {
		
		List<Game> games = db.findAll();
		List<GameDTO> gamesDtos = new ArrayList<GameDTO>();
				
		for (Game game : games) {
			GameDTO gameDTO = new GameDTO();
			gameDTO.setGame(game.getGame());
			gamesDtos.add(gameDTO);
		}		
		return gamesDtos;	
	}
	
	private Integer maxPointSeason () {		
		List<Game> games = db.findAll();
		Integer maxPoint = 0;
				
		for (Game game : games) {
			maxPoint = maxPoint > game.getPoints()?game.getPoints(): maxPoint;
			maxBreakPointSeason = maxPoint > game.getPoints()?false: true; 
		}
		
		return maxPoint;
	}
	
	private Integer minPointSeason () {		
		List<Game> games = db.findAll();
		Integer minPoint = 1000;
						
		for (Game game : games) {
			minPoint = minPoint < game.getPoints()?game.getPoints() : minPoint;
			minBreakPointSeason = minPoint < game.getPoints()?false: true;
		}			
		
		return minPoint;
	}
		
	private Integer getNewId() {
		return db.getNewId();
	}
	
	private String getGameName() {
		List<Game> games = db.findAll();
		return "JOGO "+games.size()+1;
	}
}
