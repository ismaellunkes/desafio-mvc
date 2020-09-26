package br.com.publicaproway.desafio.views;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.publicaproway.desafio.controllers.GamesController;
import br.com.publicaproway.desafio.dto.GameDTO;

public class GameView {

	Scanner tec = new Scanner(System.in);
	private GameDTO gameDTO;
	private GamesController gamesController = new GamesController();
	private String key="";
	private List<String> TestData = new ArrayList<String>();	
	
	public GameView() {
		
		System.out.println(">>>>> CHOOSE AN OPTION: \n (A)UTOMATIC Test  \n (C)ONSOLE Test \n (G)RAFIC Test");
		System.out.println("");	
		key = tec.next(); //"A";
		
		switch (key) {
		case "A":			
			automaticTest();			
			break;
			
		case "C":			
			consoleTest();
			break;
		
		case "G":	
			swingTest();
			break;
			
		default:			
			System.out.println("EXIT PROGRAM");
			break;
			
		}							
	}
	
	
	
	private void consoleTest() {

		System.out.println("***** ------ CADASTRO DE GAMES ------ *****");
		System.out.println("");						
		
		String opt="";
		
		while (!opt.equals("X")) {
			System.out.println("INSIRA A PONTUAÇÃO DA RODADA: ");
			System.out.println("");			
			gameDTO = new GameDTO();
			gameDTO.setPoints(tec.nextInt());
			gamesController.addGame(gameDTO);
			System.out.println("C para novo cadastro | R para exibir relatório  |  X para SAIR");
			opt = tec.next().toUpperCase();			
			
			if (opt.equals("R")) {
				consoleCreateRel();
			}		
		}									
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
		
	
		consoleCreateRel();
		
	}
	
	
	
	private void swingTest() {
			
											
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
		
	private void consoleCreateRel() {
			
			List<String> tableHead = new ArrayList<String>();
			tableHead.add("GAME");
			tableHead.add("POINTS");
			tableHead.add("MAX_POINT_SEASON");
			tableHead.add("MIN_POINT_SEASON");
			tableHead.add("IS_MAX_RECORD_BREAK");
			tableHead.add("IS_MIN_RECORD_BREAK");
			int collumnSpace = 5, col, pos;			
			
			for (int i = 0; i < tableHead.size(); i++) {
				
				System.out.print(tableHead.get(i));			
				
				for (int j = 0; j < collumnSpace; j++) {
					System.out.print(" ");
				}					
			}		
			
			List<GameDTO> gameDTOs = gamesController.findAll();
			for (GameDTO gameDTO : gameDTOs) {
				
				col=0;
				pos=0;
				
				System.out.println("");
						
				System.out.print(gameDTO.getGame());				
				
				
				col++;
				pos = ((tableHead.get(col-1).length()+collumnSpace+(tableHead.get(col).length()/2))-gameDTO.getGame().length())-1;					
				for (int i = 0; i < pos; i++) {
					System.out.print(" ");
				}			
				System.out.print(gameDTO.getPoints());
				
				
				col++;
				pos = ((tableHead.get(col-1).length()+collumnSpace+(tableHead.get(col).length()/2))-gameDTO.getPoints().toString().length())-1;						
				for (int i = 0; i < pos; i++) {
					System.out.print(" ");
				}			
				System.out.print(gameDTO.getMaxPointSeason());
															
				col++;															
				pos = (collumnSpace+(tableHead.get(col).length())-gameDTO.getMaxPointSeason().toString().length())-1;
				for (int i = 0; i <  pos; i++) {
					System.out.print(" ");
				}										
				System.out.print(gameDTO.getMinPointSeason()==1000?"- ":gameDTO.getMinPointSeason().toString());
					
				col++;
				pos = (collumnSpace+(tableHead.get(col).length())-gameDTO.getMinPointSeason().toString().length())-3;												
				for (int i = 0; i < pos; i++) {
					System.out.print(" ");
				}		
				System.out.print(gameDTO.isMaxBreakPointSeason()==true?"YES":"NO ");
				
				col++;
				pos = collumnSpace+(tableHead.get(col).length())-3;									
				for (int i = 0; i < pos; i++) {
					System.out.print(" ");
				}			
				System.out.print(gameDTO.isMinBreakPointSeason()==true?"YES":"NO ");																
			}
					
			System.out.println(" "  );
			System.out.println(" "  );
			System.out.println("O recorde máximo foi quebrado por "+gamesController.countMaxBreakPointSeason()+" vez(es)"  );
			System.out.println(" "  );
			System.out.println("O recorde minimo foi quebrado por "+gamesController.countMinBreakPointSeason()+" vez(es)"  );
		}
		
}
