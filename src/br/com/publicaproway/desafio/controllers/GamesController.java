package br.com.publicaproway.desafio.controllers;

import java.util.ArrayList;
import java.util.List;

import br.com.publicaproway.desafio.db.Database;
import br.com.publicaproway.desafio.dto.GameDTO;
import br.com.publicaproway.desafio.models.Game;
import br.com.publicaproway.desafio.services.GameServices;

public class GamesController {
	
	GameServices gameServices;
	
	public GamesController() {
		gameServices = new GameServices();
	}
	
	public void addGame(GameDTO gameDTO) {
		gameServices.addGame(gameDTO);				
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
