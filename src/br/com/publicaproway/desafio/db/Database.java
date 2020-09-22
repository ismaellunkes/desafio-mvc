package br.com.publicaproway.desafio.db;

import java.util.ArrayList;
import java.util.List;

import br.com.publicaproway.desafio.models.Game;

public class Database {

	List<Game> games = new ArrayList<>();
	
	
	public Database() {
		
	}
	
	public void save(Game game){
		games.add(game);
	}
	
	public List<Game> findAll(){
		return games;
	}
	
	public Integer getNewId() {
		return games.size()+1;
	}

}
