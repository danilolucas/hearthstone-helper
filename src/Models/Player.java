package Models;

public class Player {
	private String nome;
	private int mana;
	private int turn;
	private int life;
	private String status;
	
	public Player()
	{
		
	}
	public Player(String nome, int mana, int turn, int life, String status)
	{
		this.mana = mana;
		this.nome = nome;
		this.turn = turn;
		this.life = life;
		this.status = status;
	}
	public int getMana()
	{
		return mana;
	}
	public void setMana(int mana)
	{
		this.mana = mana;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getTurn() {
		return turn;
	}
	public void setTurn(int turn) {
		this.turn = turn;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
