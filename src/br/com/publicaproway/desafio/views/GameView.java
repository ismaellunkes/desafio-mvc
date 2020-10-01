package br.com.publicaproway.desafio.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import br.com.publicaproway.desafio.controllers.GamesController;
import br.com.publicaproway.desafio.dto.GameDTO;
import br.com.publicaproway.desafio.services.ReportServices;

public class GameView {

	Scanner tec = new Scanner(System.in);
	private GameDTO gameDTO;
	private GamesController gamesController = new GamesController();
	private String key="";	
	private boolean modeSearch=false;
	
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
		
		List<String> TestData = new ArrayList<String>();
		
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
		frame.setSize(600,600);		
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("My list games and records break");		
		
		JPanel jPanelContainer = new JPanel();
		jPanelContainer.setSize(frame.getWidth(), frame.getHeight());
		jPanelContainer.setLayout(new GridLayout(2, 1));
		
		JPanel jPanelTable = new JPanel();
		jPanelTable.setPreferredSize(new Dimension(frame.getWidth(), 200));
		jPanelTable.setLayout(new BorderLayout());
									
		JPanel jPanelControles = new JPanel();
		jPanelTable.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()/4));
		jPanelControles.setBackground(new Color(245, 245, 245));
		jPanelControles.setLayout(null);
		
		jPanelContainer.add(jPanelTable);
		jPanelContainer.add(jPanelControles);

		JLabel jLabelGame = new JLabel("JOGO:");		
		jLabelGame.setBounds(new Rectangle(150, 10, 60, 80));
		jLabelGame.setHorizontalAlignment(SwingConstants.CENTER);
		
		JTextField jTextFieldGame = new JTextField();
		jTextFieldGame.setBounds(jLabelGame.getX(), jLabelGame.getY()+50, 60, 60);
		jTextFieldGame.setText(Integer.toString(gamesController.findAll().size()+1));
		jTextFieldGame.setEditable(false);
		jTextFieldGame.setFont(new Font("Arial", Font.BOLD, 25));		
		
		JLabel jLabelPoints = new JLabel("PONTOS:");		
		jLabelPoints.setBounds(new Rectangle(jLabelGame.getX()+200, jLabelGame.getY(), 60, 80));
		jLabelPoints.setHorizontalAlignment(SwingConstants.CENTER);
				
		JTextField jTextFieldPoints = new JTextField();
		jTextFieldPoints.setBounds(jLabelPoints.getX(), jLabelPoints.getY()+50, 60, 60);
		jTextFieldPoints.setFont(new Font("Arial", Font.BOLD, 25));
					
		String[] colunas = {"GAME", "POINTS", "MAX_POINT_SEASON", "MIN_POINT_SEASON", "IS_MAX_RECORD_BREAK", "IS_MIN_RECORD_BREAK"};
		String[][] dados = {{"", "", "", "", "", ""}};
				
		DefaultTableModel defaultTableModel = new DefaultTableModel(dados, colunas);
		JTable tabela = new JTable();					
		tabela.setBackground(Color.WHITE);
		tabela.setFillsViewportHeight(true);
		JScrollPane barraRolagem = new JScrollPane(tabela);	
		jPanelTable.add(barraRolagem);
		
	    JLabel jLabelMaxRecBreak = new JLabel();
	    JLabel jLabelMinRecBreak = new JLabel();
	    
	    jLabelMaxRecBreak.setBounds(jTextFieldGame.getX(), jTextFieldGame.getY()+60, 300, 60);
	    jLabelMinRecBreak.setBounds(jLabelMaxRecBreak.getX(), jLabelMaxRecBreak.getY()+20, 300, 60);
	    jLabelMaxRecBreak.setVisible(false);
	    jLabelMinRecBreak.setVisible(false);
	    
	    
	    JButton buttonAdd = new JButton("SAVE");		
		buttonAdd.setBounds(jTextFieldGame.getX()+(jTextFieldPoints.getX()-jTextFieldGame.getX())/2, jLabelMinRecBreak.getY()+60, 100, 40);
		buttonAdd.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (modeSearch) {
					
					String game = "JOGO "+jTextFieldGame.getText().trim();
					boolean searchOk=false;
										
					for (int i = 0; i < tabela.getRowCount(); i++) {
					
						if (tabela.getValueAt(i, 0).equals(game)) {
						
							tabela.setRowSelectionInterval(i, i);
							searchOk=true;
							break;
								
							}				
						
					}
					
					if (!searchOk) {
						tabela.clearSelection();
						JOptionPane.showMessageDialog(null, "Game não encontrado.", "Database message", JOptionPane.INFORMATION_MESSAGE);
					}
															
				}else {
					try {																
						gameDTO = new GameDTO();
						gameDTO.setPoints(Integer.parseInt(jTextFieldPoints.getText()));
						String saveOk = gamesController.addGame(gameDTO);										
						JOptionPane.showMessageDialog(null, saveOk, "Database message", JOptionPane.INFORMATION_MESSAGE);
	
						tabela.setModel(defaultTableModel);;
						defaultTableModel.setNumRows(0);
	
						for (GameDTO gameDTO2 : gamesController.findAll()) {
	
							defaultTableModel.addRow(new Object[] {
									gameDTO2.getGame().toString(),
									gameDTO2.getPoints().toString(),
									gameDTO2.getMaxPointSeason().toString(),
									gameDTO2.getMinPointSeason().toString(),
									gameDTO2.isMaxBreakPointSeason()==true?"YES":"NO",
											gameDTO2.isMinBreakPointSeason()==true?"YES":"NO"
							});
	
						}
	
						jTextFieldGame.setText(Integer.toString(gamesController.findAll().size()+1));
						jLabelMaxRecBreak.setVisible(true);
						jLabelMinRecBreak.setVisible(true);
						jLabelMaxRecBreak.setText("O recorde máximo foi quebrado por "+gamesController.countMaxBreakPointSeason()+" vez(es)");
						jLabelMinRecBreak.setText("O recorde mínimo foi quebrado por "+gamesController.countMinBreakPointSeason()+" vez(es)");
						jTextFieldPoints.setText("");
	
					}catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "Este valor deve ser numérico!", "Swing Message", JOptionPane.ERROR_MESSAGE);					
					}catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Erro: "+e2, "Swing Message", JOptionPane.ERROR_MESSAGE);
					}	
				}
			}
		});
		
		
		
		JCheckBox jChbxSearchMode = new  JCheckBox("Search Mode.");
		jChbxSearchMode.setBounds(buttonAdd.getX()+100, buttonAdd.getY(), 120, 15);
		jChbxSearchMode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jChbxSearchMode.isSelected()){
					buttonAdd.setText("SEARCH");
					jTextFieldPoints.setVisible(false);
					jLabelPoints.setVisible(false);
					jTextFieldGame.setEditable(true);
					jLabelGame.setLocation(jLabelGame.getX()+120, jLabelGame.getY());
					jTextFieldGame.setLocation(jTextFieldGame.getX()+120, jTextFieldGame.getY());
					modeSearch=true;
					
				}else {
					buttonAdd.setText("SAVE");
					jTextFieldPoints.setVisible(true);
					jLabelPoints.setVisible(true);
					jTextFieldGame.setEditable(false);
					jLabelGame.setLocation(jLabelGame.getX()-120, jLabelGame.getY());
					jTextFieldGame.setLocation(jTextFieldGame.getX()-120, jTextFieldGame.getY());
					modeSearch=false;
				}				
			}
		});
		
		 JButton buttonPrintReport = new JButton("Print");		
		 buttonPrintReport.setBounds(tabela.getX(), tabela.getY(), 65, 15);
		 buttonPrintReport.addActionListener( new ActionListener() {
			 
				@Override
				public void actionPerformed(ActionEvent e) {
					
					try {
						
						ReportServices reportServices = new ReportServices();
						reportServices.reportCreateHTML();
						reportServices.addHeader("Relatório de JOGOS");
						reportServices.addTitle("Relatório de JOGOS");
						reportServices.addTable((DefaultTableModel)tabela.getModel());
						reportServices.addFooter();
						reportServices.openReport();
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
					
				}
				
			});
				
		jPanelControles.add(jLabelGame);
		jPanelControles.add(jTextFieldGame);
		jPanelControles.add(jLabelPoints);
		jPanelControles.add(jTextFieldPoints);
		jPanelControles.add(jLabelMaxRecBreak);
		jPanelControles.add(jLabelMinRecBreak);
		jPanelControles.add(buttonAdd);				
		jPanelControles.add(jChbxSearchMode);
		jPanelControles.add(buttonPrintReport);
		frame.add(jPanelContainer);
		frame.setVisible(true);
	}
	/**
	 * Cria um relatório para ser usado via console	
	 */
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
