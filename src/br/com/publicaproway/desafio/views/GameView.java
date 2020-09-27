package br.com.publicaproway.desafio.views;

import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.TableModel;

import br.com.publicaproway.desafio.controllers.GamesController;
import br.com.publicaproway.desafio.dto.GameDTO;

public class GameView {

	Scanner tec = new Scanner(System.in);
	private GameDTO gameDTO;
	private GamesController gamesController = new GamesController();
	private String key="";
	private Integer trialVersionRec=9;
	private List<String> TestData = new ArrayList<String>();	
	
	/**
	 * Contains the program initialization menu
	 * A - Automatic test, with defined data;
	 * C - Testing with data insertion via console
	 * G - Test with data insertion via the swing library screen
	 * 
	 * Any other key ends this menu
	 */
	public GameView() {
		
		System.out.println(">>>>> CHOOSE AN OPTION: \n (A)UTOMATIC Test  \n (C)ONSOLE Test \n (G)RAFIC Test");
		System.out.println("");	
		key =  tec.next().toUpperCase(); 
		
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
	
	/**
	 * Contains the test via the console
	 * Each iteration asks for a new insertion of points
	 * R - Displays report via registered games console
	 * X- Displays the registered games report and ends the iteration
	 * 
	 *  Any other key starts a new registration and requests a score
	 *
	 */
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
	
	/**
	 * Contains a List of scores for automatic test
	 * 
	 * Displays the registered games report and ends the iteration
	 * 
	 */	
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
	
	/**
	 * Contain a swing test
	 */
	
	private void swingTest() {
														
		JFrame frame = new JFrame();		
		frame.setSize(600, 400);		
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setTitle("My list games and records break");
		//frame.setAlwaysOnTop(true);

		JLabel jLabelGame = new JLabel("JOGO:");		
		jLabelGame.setBounds(new Rectangle(150, 160, 60, 80));
		jLabelGame.setHorizontalAlignment(SwingConstants.CENTER);
		
		JTextField jTextFieldGame = new JTextField();
		jTextFieldGame.setBounds(jLabelGame.getX(), jLabelGame.getY()+50, 60, 60);
		jTextFieldGame.setText(Integer.toString(gamesController.findAll().size()+1));
		jTextFieldGame.setEnabled(false);
		
		JLabel jLabelPoints = new JLabel("PONTOS:");		
		jLabelPoints.setBounds(new Rectangle(jLabelGame.getX()+200, jLabelGame.getY(), 60, 80));
		jLabelPoints.setHorizontalAlignment(SwingConstants.CENTER);
				
		JTextField jTextFieldPoints = new JTextField();
		jTextFieldPoints.setBounds(jLabelPoints.getX(), jLabelPoints.getY()+50, 60, 60);
		
		JPanel jPanel = new JPanel();
					
		String[] colunas = {"GAME", "POINTS", "MAX_POINT_SEASON", "MIN_POINT_SEASON", "IS_MAX_RECORD_BREAK", "IS_MIN_RECORD_BREAK"};
		String[][] dados = {{"", "", "", "", "", ""}, {"", "", "", "", "", ""}, {"", "", "", "", "", ""}, {"", "", "", "", "", ""}, {"", "", "", "", "", ""},
							{"", "", "", "", "", ""}, {"", "", "", "", "", ""}, {"", "", "", "", "", ""}, {"", "", "", "", "", ""}, {"", "", "", "", "", ""}};
		JTable tabela = new JTable(dados, colunas);
		JScrollPane barraRolagem = new JScrollPane(tabela);		
		jPanel.setLayout(new GridLayout(1, 1));
	    barraRolagem = new JScrollPane(tabela);
	    jPanel.add(barraRolagem);
	    tabela.setBounds(jLabelPoints.getX(), jLabelPoints.getY()+50, 60, 60);
	    
	    JButton buttonAdd = new JButton();
		buttonAdd.setText("SAVE");
		buttonAdd.setBounds(jTextFieldGame.getX()+(jTextFieldPoints.getX()-jTextFieldGame.getX())/2, jTextFieldGame.getY()+100, 65, 40);
		buttonAdd.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(gamesController.findAll().size()>trialVersionRec)) {				
					try {						
						
						gameDTO = new GameDTO();
						gameDTO.setPoints(Integer.parseInt(jTextFieldPoints.getText()));
						String saveOk = gamesController.addGame(gameDTO);										
						JOptionPane.showMessageDialog(null, saveOk, "Database message", JOptionPane.INFORMATION_MESSAGE);
						tabela.setModel(reBuildingTableModel(gamesController.findAll(), tabela.getModel()));						
						jTextFieldGame.setText(Integer.toString(gamesController.findAll().size()+1));
						jTextFieldPoints.setText("");
						
						}catch (NumberFormatException e1) {
							JOptionPane.showMessageDialog(null, "Este valor deve ser numérico!", "Swing Message", JOptionPane.ERROR_MESSAGE);					
						}catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "Erro: "+e2, "Swing Message", JOptionPane.ERROR_MESSAGE);
						}
				}else {
					JOptionPane.showMessageDialog(null, "Records number exceeded your trial license", "Swing message", JOptionPane.INFORMATION_MESSAGE);
				}								
			}			
		});
		
		frame.add(jLabelGame);
		frame.add(jTextFieldGame);
		frame.add(jLabelPoints);
		frame.add(jTextFieldPoints);
		frame.add(buttonAdd);
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
	
	private TableModel reBuildingTableModel(List<GameDTO> gameDTOs, TableModel tableModel) {
		
		int l=0;		
		
		for (GameDTO gameDTO : gameDTOs) {			
			tableModel.setValueAt(gameDTO.getGame(), l, 0);			
			tableModel.setValueAt(gameDTO.getPoints().toString(), l, 1);
			tableModel.setValueAt(gameDTO.getMaxPointSeason().toString(), l, 2);
			tableModel.setValueAt(gameDTO.getMinPointSeason().toString(), l, 3);
			tableModel.setValueAt(gameDTO.isMaxBreakPointSeason()?"YES":"NO", l, 4);
			tableModel.setValueAt(gameDTO.isMinBreakPointSeason()?"YES":"NO", l, 5);	
			l++;
		}				
		return tableModel;		
	}	
		
}
