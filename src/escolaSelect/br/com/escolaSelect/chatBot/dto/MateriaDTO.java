package escolaSelect.br.com.escolaSelect.chatBot.dto;

public class MateriaDTO {

	private String nome;
	private double notaProva;
	private double notaTrabalho;
	private double notaPonto;
	private int faltas;

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getNotaProva() {
		return notaProva;
	}
	public void setNotaProva(double notaProva) {
		this.notaProva = notaProva;
	}
	public double getNotaTrabalho() {
		return notaTrabalho;
	}
	public void setNotaTrabalho(double notaTrabalho) {
		this.notaTrabalho = notaTrabalho;
	}
	public double getNotaPonto() {
		return notaPonto;
	}
	public void setNotaPonto(double notaPonto) {
		this.notaPonto = notaPonto;
	}
	public int getFaltas() {
		return faltas;
	}
	public void setFaltas(int faltas) {
		this.faltas = faltas;
	}
}
