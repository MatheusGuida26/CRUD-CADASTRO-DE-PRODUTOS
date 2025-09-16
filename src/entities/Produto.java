package entities;

import java.math.BigDecimal;

public class Produto {
	private int id;
    private String nome;
    private BigDecimal preco;

    public Produto(int id, String nome, BigDecimal preco) {
        this.id = id;
        this.nome = nome;
        if (preco.compareTo(BigDecimal.ZERO) > 0) {
            this.preco = preco;
        } else {
            throw new IllegalArgumentException("Preço deve ser maior que 0");
        }
    }

    public int getId() { 
    	return id; 
    	}
    public String getNome() { 
    	return nome; 
    	}
    public void setNome(String nome) { 
    	this.nome = nome; 
    	}
    public BigDecimal getPreco() { 
    	return preco; 
    	}
    public void setPreco(BigDecimal preco) {
        if (preco.compareTo(BigDecimal.ZERO) > 0) {
            this.preco = preco;
        } else {
            throw new IllegalArgumentException("Preço deve ser maior que 0");
        }
    }
}