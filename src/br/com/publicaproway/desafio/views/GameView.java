package br.com.publicaproway.desafio.views;

import java.awt.Button;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.publicaproway.desafio.controllers.GamesController;
import br.com.publicaproway.desafio.dto.GameDTO;

public class GameView {

	Scanner tec = new Scanner(System.in);
	private GameDTO gameDTO;
	private GamesController gamesController = new GamesController();
	private String exit="", key="";
	private List<String> TestData = new ArrayList<String>();	
	
	public GameView() {
		
		System.out.println("***** TEST: AUTOMATIC(A)  OR MANUALLY (M)? *****");
		System.out.println("");	
		key = "A"; //tec.next();
		
		switch (key) {
		case "A":
			
			automaticTest();			
			break;
			
		case "M":
			
			manuallyTest();
			break;
			
		default:
			
			System.out.println("EXIT PROGRAM");
			break;
		}							
	}
	
	private void manuallyTest() {
		
		System.out.println("***** ------ GAMES ------ *****");
		System.out.println("");						
		
		while (!exit.equals("X")) {
			System.out.println("INSIRA A PONTUAÇÃO DA RODADA: ");
			System.out.println("");			
			gameDTO = new GameDTO();
			gameDTO.setPoints(tec.nextInt());
			gamesController.addGame(gameDTO);
			System.out.println("X para SAIR");
			exit = tec.next().toUpperCase();			
		}		
		
		System.out.println("-------------- JOGOS CADASTRADOS --------");
		System.out.println("");
		System.out.println(" GAME     PONTOS     MAX_POINT_SEASON     MIX_POINT_SEASON     IS_MAX_RECORD_BREAK     IS_MIN_RECORD_BREAK");
		List<GameDTO> gameDTOs = gamesController.findAll();
		for (GameDTO gameDTO : gameDTOs) {
			
			System.out.println("");
			System.out.println(gameDTO.getGame()+"    "+ gameDTO.getPoints()+"          "+gameDTO.getMaxPointSeason()+"                     "+
					(gameDTO.getMinPointSeason()==1000?" - ":gameDTO.getMinPointSeason().toString())+"                   "+(gameDTO.isMaxBreakPointSeason()==true?"YES":"NO")+"                 "
					+(gameDTO.isMinBreakPointSeason()==true?"YES":"NO"));
			
		}	
		
		System.out.println(" "  );
		System.out.println(" "  );
		System.out.println("O recorde máximo foi quebrado por "+gamesController.countMaxBreakPointSeason()+" vez(es)"  );
		System.out.println(" "  );
		System.out.println("O recorde minimo foi quebrado por "+gamesController.countMinBreakPointSeason()+" vez(es)"  );
		
		
	}
	
	private void automaticTest() {		
		
		TestData.add("12");		
		TestData.add("25");		
		TestData.add("86");		
		TestData.add("1");
		TestData.add("24");
		TestData.add("15");
		TestData.add("32");
		TestData.add("17");
		TestData.add("43");		
		
		for (int i = 0; i < TestData.size(); i++) {
			gameDTO = new GameDTO();
			gameDTO.setPoints(Integer.parseInt(TestData.get(i)));
			gamesController.addGame(gameDTO);		
		}
		
		System.out.println("-------------- JOGOS CADASTRADOS --------");
		System.out.println("");
		System.out.println(" GAME     PONTOS     MAX_POINT_SEASON     MIX_POINT_SEASON     IS_MAX_RECORD_BREAK     IS_MIN_RECORD_BREAK");
		List<GameDTO> gameDTOs = gamesController.findAll();
		for (GameDTO gameDTO : gameDTOs) {
			
			System.out.println("");
			System.out.println(gameDTO.getGame()+"    "+ gameDTO.getPoints()+"          "+gameDTO.getMaxPointSeason()+"                     "+
					(gameDTO.getMinPointSeason()==1000?" - ":gameDTO.getMinPointSeason().toString())+"                   "+(gameDTO.isMaxBreakPointSeason()==true?"YES":"NO")+"                 "
					+(gameDTO.isMinBreakPointSeason()==true?"YES":"NO"));
			
		}	
		
		System.out.println(" "  );
		System.out.println(" "  );
		System.out.println("O recorde máximo foi quebrado por "+gamesController.countMaxBreakPointSeason()+" vez(es)"  );
		System.out.println(" "  );
		System.out.println("O recorde minimo foi quebrado por "+gamesController.countMinBreakPointSeason()+" vez(es)"  );
		
			
	}
		
}
