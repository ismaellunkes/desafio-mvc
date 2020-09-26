package br.com.publicaproway.desafio.controllers;

import java.util.List;
import br.com.publicaproway.desafio.dto.GameDTO;
import br.com.publicaproway.desafio.services.GameServices;

public class GamesController {
	
	GameServices gameServices;
	
	public GamesController() {
		gameServices = new GameServices();
	}
	
	public String addGame(GameDTO gameDTO) {
		return gameServices.addGame(gameDTO);				
	}
	
	public List<GameDTO> findAll() {
		return gameServices.findAll();				
	}
	
	public Integer countMaxBreakPointSeason() {
		return gameServices.countMaxBreakPointSeason();				
	}
	
	public Integer countMinBreakPointSeason() {
		return gameServices.countMinBreakPointSeason();				
	}
		
}
