package br.com.publicaproway.desafio.views;

import java.awt.Button;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.sun.jdi.IntegerValue;

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
	
	private void consoleCreate() {
		
		List<String> tableHead = new ArrayList<String>();
		tableHead.add("GAME");
		tableHead.add("POINTS");
		tableHead.add("MAX_POINT_SEASON");
		tableHead.add("MIX_POINT_SEASON");
		tableHead.add("IS_MAX_RECORD_BREAK");
		tableHead.add("IS_MIN_RECORD_BREAK");
		
		for (int i = 0; i < tableHead.size(); i++) {
			
			System.out.print(tableHead.get(i));			
			
			for (int j = 0; j < 5; j++) {
				System.out.print(" ");
			}					
		}		
		
		List<GameDTO> gameDTOs = gamesController.findAll();
		for (GameDTO gameDTO : gameDTOs) {
			
			System.out.println("");
			
			int margemLeft00 = tableHead.get(0).length()+5;	
			System.out.print(gameDTO.getGame());
			for (int i = 0; i < margemLeft00; i++) {
				System.out.print(" ");
			}
			
			int margemLeft01 = tableHead.get(0).length()+5;			
			System.out.print(gameDTO.getPoints());
			for (int i = 0; i < margemLeft01; i++) {
				System.out.print(" ");
			}
			
			int margemLeft02 = tableHead.get(0).length()+5+tableHead.get(1).length()+5;	
			System.out.print(gameDTO.getMaxPointSeason());					
			for (int i = 0; i < margemLeft02; i++) {
				System.out.print(" ");
			}
			
			System.out.print(gameDTO.getMinPointSeason()==1000?" - ":gameDTO.getMinPointSeason().toString());
			for (int i = 0; i < 22; i++) {
				System.out.print(" ");
			}
			
			System.out.print(gameDTO.isMaxBreakPointSeason()==true?"YES":"NO");
			for (int i = 0; i < 22; i++) {
				System.out.print(" ");
			}								
			
			System.out.print(gameDTO.isMinBreakPointSeason()==true?"YES":"NO");
			
			
			
			
			/*System.out.println(gameDTO.getGame()+"    "+ gameDTO.getPoints()+"          "+gameDTO.getMaxPointSeason()+"                     "+
					(gameDTO.getMinPointSeason()==1000?" - ":gameDTO.getMinPointSeason().toString())+"                   "+(gameDTO.isMaxBreakPointSeason()==true?"YES":"NO")+"                 "
					+(gameDTO.isMinBreakPointSeason()==true?"YES":"NO"));*/
			
		}
				
		System.out.println(" "  );
		System.out.println(" "  );
		System.out.println("O recorde máximo foi quebrado por "+gamesController.countMaxBreakPointSeason()+" vez(es)"  );
		System.out.println(" "  );
		System.out.println("O recorde minimo foi quebrado por "+gamesController.countMinBreakPointSeason()+" vez(es)"  );
	}
	
	private void manuallyTest() {
		
		consoleCreate();
		
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
		TestData.add("23");		
		TestData.add("86");		
		TestData.add("1");
		TestData.add("24");
		TestData.add("15");
		TestData.add("7");
		TestData.add("17");
		TestData.add("95");		
		
		for (int i = 0; i < TestData.size(); i++) {
			gameDTO = new GameDTO();
			gameDTO.setPoints(Integer.parseInt(TestData.get(i)));
			gamesController.addGame(gameDTO);		
		}
		
	consoleCreate();
		
		/*System.out.println("          -------------- JOGOS CADASTRADOS --------      ");
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
		System.out.println("O recorde minimo foi quebrado por "+gamesController.countMinBreakPointSeason()+" vez(es)"  );*/
		
		openFrame();	
	}
	
	
	
		private void openFrame() {
			
											
		JFrame frame = new JFrame();		
		frame.setSize(600, 400);		
		frame.setLocationRelativeTo(null);
		//frame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);	
		
		//Point point = new Point();
		//point.x = frame.getX()/4;
		//point.y = frame.getY()/4;
		
		JPanel jPanel = new JPanel();
		jPanel.setSize(100, 50);
				
		JLabel jLabelGame = new JLabel("JOGO:");		
		jLabelGame.setBounds(new Rectangle(150, 30, 60, 80));
		jLabelGame.setHorizontalAlignment(SwingConstants.CENTER);
		
		JTextField jTextFieldGame = new JTextField();
		jTextFieldGame.setBounds(jLabelGame.getX(), jLabelGame.getY()+50, 60, 60);
		//jTextFieldPoints.setSize(300,50);
		
		JLabel jLabelPoints = new JLabel("PONTOS:");		
		jLabelPoints.setBounds(new Rectangle(jLabelGame.getX()+200, jLabelGame.getY(), 60, 80));
		jLabelPoints.setHorizontalAlignment(SwingConstants.CENTER);
				
		JTextField jTextFieldPoints = new JTextField();
		jTextFieldPoints.setBounds(jLabelPoints.getX(), jLabelPoints.getY()+50, 60, 60);

		//TableModel tableModel;
		//tableModel.setValueAt("Teste", 1, 1);
		
		//TableColumnModel columnModel;
		//columnModel.addColumn("");
		
		//TableColumn column;
		//column.set
		
		//JTable jTable = new JTable();
		//jTable.setColumnModel(tableModel);
		//jPanel.add(jTable);
		 
		frame.add(jLabelGame);
		frame.add(jTextFieldGame);
		frame.add(jLabelPoints);
		frame.add(jTextFieldPoints);
		frame.add(jPanel);	
		frame.setVisible(true);
		
	}
		
}
