package br.com.publicaproway.desafio.controllers;

import java.util.List;
import br.com.publicaproway.desafio.dto.GameDTO;
import br.com.publicaproway.desafio.services.GameServices;

public class GamesController {
	
	GameServices gameServices;
	
	public GamesController() {
		gameServices = new GameServices();
	}
	
	/**
	 * @param gameDTO
	 * @return database message 
	 */
	public String addGame(GameDTO gameDTO) {
		return gameServices.addGame(gameDTO);				
	}
	
	/**
	 * 
	 * @return List of GameDTO
	 */
	public List<GameDTO> findAll() {
		return gameServices.findAll();				
	}
	
	/**
	 * 
	 * @return boolean countMaxBreakPointSeason
	 */
	public Integer countMaxBreakPointSeason() {
		return gameServices.countMaxBreakPointSeason();				
	}
	
	/**
	 * 
	 * @return Integer countMinBreakPointSeason
	 */
	public Integer countMinBreakPointSeason() {
		return gameServices.countMinBreakPointSeason();				
	}
		
}
