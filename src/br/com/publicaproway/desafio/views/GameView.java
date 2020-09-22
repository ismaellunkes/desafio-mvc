package br.com.publicaproway.desafio.views;

import java.util.List;
import java.util.Scanner;

import br.com.publicaproway.desafio.controllers.GamesController;
import br.com.publicaproway.desafio.dto.GameDTO;

public class GameView {

	Scanner tec = new Scanner(System.in);
	private GameDTO gameDTO;
	private GamesController gamesController = new GamesController();
	private String exit="";
	
	public GameView() {
		
		System.out.println("***** ------ GAMES ------ *****");
		
		while (!exit.equals("X")) {
			System.out.println("INSIRA A PONTUAÇÃO DA RODADA: ");
			gameDTO = new GameDTO();
			gameDTO.setPoints(tec.nextInt());
			gamesController.addGame(gameDTO);
			System.out.println("X para SAIR");
			exit = tec.next().toUpperCase();			
		}		
		
		System.out.println("-------------- JOGOS CADASTRADOS --------");
		System.out.println("");
		System.out.println(" GAME     PONTOS  ");
		List<GameDTO> gameDTOs = gamesController.findAll();
		for (GameDTO gameDTO : gameDTOs) {
			
			System.out.println("");
			System.out.println(gameDTO.getGame()+"    "+ gameDTO.getPoints());
			
		}
		
		
	}

}
