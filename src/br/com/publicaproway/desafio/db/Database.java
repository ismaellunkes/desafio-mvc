package br.com.publicaproway.desafio.db;

import java.util.ArrayList;
import java.util.List;

import br.com.publicaproway.desafio.models.Game;

public class Database {

	/**
	 * Memory Database
	 */
	List<Game> games = new ArrayList<>();
	

	public Database() {
		
	}
	
	/**
	 * @param game
	 * @return database message
	 */
	public String save(Game game){
		games.add(game);
		return "Salvo com sucesso";
	}
	
	/**
	 * 
	 * @return List of Game
	 */
	public List<Game> findAll(){
		return games;
	}
	
	/**
	 * 
	 * @return new ID from the list size in the database in memory
	 */
	public Integer getNewId() {
		return games.size()+1;
	}

}
