package br.com.publicaproway.desafio.services;

import java.util.ArrayList;
import java.util.List;

import br.com.publicaproway.desafio.db.Database;
import br.com.publicaproway.desafio.dto.GameDTO;
import br.com.publicaproway.desafio.models.Game;
/**
 * 
 * @author ismael
 *
 * Contains business logic
 * 
 */
public class GameServices {
	
	Game game;
	Database db = new Database();
	boolean minBreakPointSeason, maxBreakPointSeason;

	public GameServices() {}

	public String addGame(GameDTO gameDTO) {
		
		this.game = new Game();
		this.game.setId(getNewId());
		this.game.setGame(getGameName());
		this.game.setPoints(gameDTO.getPoints());		
		this.game.setMaxPointSeason(maxPointSeason(gameDTO.getPoints()));
		this.game.setMaxBreakPointSeason(maxBreakPointSeason);
		this.game.setMinPointSeason(minPointSeason(gameDTO.getPoints()));
		this.game.setMinBreakPointSeason(minBreakPointSeason);
		
		return db.save(game);				
	}
	
	/**
	 * Retorna uma lista com todos os games no banco de dados de memória, carregando uma lista de GameDtos
	 * @return gamesDtos
	 */
	public List<GameDTO> findAll() {
		
		List<Game> games = db.findAll();
		List<GameDTO> gamesDtos = new ArrayList<GameDTO>();
				
		for (Game game : games) {
			GameDTO gameDTO = new GameDTO();
			gameDTO.setGame(game.getGame());
			gameDTO.setPoints(game.getPoints());
			gameDTO.setMaxBreakPointSeason(game.isMaxBreakPointSeason());
			gameDTO.setMaxPointSeason(game.getMaxPointSeason());
			gameDTO.setMinBreakPointSeason(game.isMinBreakPointSeason());
			gameDTO.setMinPointSeason(game.getMinPointSeason());
			gamesDtos.add(gameDTO);
		}		
		return gamesDtos;	
	}
	/**
	 * Quantidade de vezes que teve quebra de recorde máximo.
	 * @return count com quantidade de vezes que foi true para isMaxBreakPointSeason()
	 */
	public Integer countMaxBreakPointSeason() {
		List<Game> games = db.findAll();
		Integer count = 0;
						
		for (Game game : games) {			
			
			if (game.isMaxBreakPointSeason()) {
				count++;
			}			
		}					
		
		return count;
	}
	/**
	 * Quantidade de vezes que teve quebra de recorde mínimo.
	 * @return count com quantidade de vezes que foi true para isMinBreakPointSeason()
	 */
	public Integer countMinBreakPointSeason() {
		List<Game> games = db.findAll();
		Integer count = 0;
		
		for (Game game : games) {			
			
			if (game.isMinBreakPointSeason()) {
				count++;
			}			
		}					
		
		return count;
	}
	
	/**
	 * 
	 * @param points recebe os pontos do jogo que está sendo cadastrado
	 * @return maxPoint atualiazado, em comparação com a pontuação do ultimo jogo
	 */
	private Integer maxPointSeason (Integer points) {		
		List<Game> games = db.findAll();
		Integer maxPoint = points;
				
		for (Game game : games) {			
			maxPoint = maxPoint < game.getPoints()?game.getPoints(): maxPoint;
			maxBreakPointSeason = points < maxPoint?false:true;
		}
		
		return maxPoint;
	}
	
	/**
	 * 
	 * @param points recebe os pontos do jogo que está sendo cadastrado
	 * @return minPoint atualiazado, em comparação com a pontuação do ultimo jogo
	 */
	private Integer minPointSeason (Integer points) {		
		List<Game> games = db.findAll();
		Integer minPoint = points;
						
		for (Game game : games) {			
			minPoint = minPoint > game.getPoints()?game.getPoints() : minPoint;
			minBreakPointSeason = points > minPoint?false:true;
		}			
		
		return minPoint;
	}
	/**
	 * 	
	 * @return getNewId() com o valor no novo ID baseado na quantidade de games cadastrados no banco de dados
	 */
	private Integer getNewId() {
		return db.getNewId();
	}
	
	/**
	 * 
	 * @return new name from the list size in the database in memory
	 */
	private String getGameName() {
		List<Game> games = db.findAll();
		Integer gameNumber = games.size() + 1;
		return "JOGO "+ gameNumber;
	}
}
