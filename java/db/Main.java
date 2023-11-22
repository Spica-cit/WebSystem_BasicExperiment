package db;

import java.util.List;

public class Main {
	
	void allPokemon() {
		PokemonDAO pokemonDAO = new PokemonDAO();
		List<Pokemon> list = pokemonDAO.findAll();
		for(Pokemon p: list) {
			int id = p.getId();
			String name = p.getName();
			int attack = p.getAttack();
			int defense = p.getDefense();
			int stamina = p.getStamina();
			int cp = p.getCp();
			System.out.printf("%3d %-10s %3d %3d %3d %4d\n",id,name,attack,defense,stamina,cp);
		}
	}
	
	void pikachu() {
		PokemonDAO pokemonDAO = new PokemonDAO();
		List<Pokemon> list = pokemonDAO.pikachu();
		for(Pokemon p: list) {
			int attack = p.getAttack();
			int defense = p.getDefense();
			int stamina = p.getStamina();
			System.out.printf("%3d %3d %3d\n",attack,defense,stamina);
		}
	}
	
	void threeLetters() {
		PokemonDAO pokemonDAO = new PokemonDAO();
		List<Pokemon> list = pokemonDAO.threeLetters();
		for(Pokemon p: list) {
			String name = p.getName();
			System.out.printf("%-10s\n",name);
		}
	}
	
	void max() {
		PokemonDAO pokemonDAO = new PokemonDAO();
		List<Pokemon> list = pokemonDAO.max();
		for(Pokemon p: list) {
			int attack = p.getAttack();
			int defense = p.getDefense();
			int stamina = p.getStamina();
			System.out.printf("%3d %3d %3d\n",attack,defense,stamina);
		}
	}
	
	void denki() {
		PokemonDAO pokemonDAO = new PokemonDAO();
		List<Pokemon> list = pokemonDAO.denki();
		for(Pokemon p: list) {
			int id = p.getId();
			String name = p.getName();
			int attack = p.getAttack();
			int defense = p.getDefense();
			int stamina = p.getStamina();
			int cp = p.getCp();
			String type = p.getType();
			System.out.printf("%3d %-10s %3d %3d %3d %4d %-10s\n",id,name,attack,defense,stamina,cp,type);
		}
	}
	
	public static void main(String args[]) {
		Main main = new Main();
		main.allPokemon();
		main.pikachu();
		main.threeLetters();
		main.max();
		main.denki();
	}

}
