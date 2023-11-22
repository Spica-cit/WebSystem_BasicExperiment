package db;

public class Pokemon {
	
	private int id;
	private String name;
	private int attack;
	private int defense;
	private int stamina;
	private int cp;
	private String type;

	public Pokemon(int id, String name, int attack, int defense, int stamina, int cp) {
		this.id = id;
		this.name = name;
		this.attack = attack;
		this.defense = defense;
		this.stamina = stamina;
		this.cp = cp;
	}
	
	public Pokemon(int id, String name, int attack, int defense, int stamina, int cp, String type) {
		this.id = id;
		this.name = name;
		this.attack = attack;
		this.defense = defense;
		this.stamina = stamina;
		this.cp = cp;
		this.type = type;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAttack() {
		return attack;
	}
	
	public int getDefense() {
		return defense;
	}
	
	public int getStamina() {
		return stamina;
	}
	
	public int getCp() {
		return cp;
	}
	
	public String getType() {
		return type;
	}
}