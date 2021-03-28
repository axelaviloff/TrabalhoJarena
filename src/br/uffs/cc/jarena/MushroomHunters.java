/**
 * Um exemplo de agente que anda aleatoriamente na arena. Esse agente pode ser usado como base
 * para a criação de um agente mais esperto. Para mais informações sobre métodos que podem
 * ser utilizados, veja a classe Agente.java.
 * 
 * Fernando Bevilacqua <fernando.bevilacqua@uffs.edu.br>
 */

package br.uffs.cc.jarena;

public class MushroomHunters extends Agente
{
    public int qtdEnergia;
	public MushroomHunters(Integer x, Integer y, Integer energia) {
		super(x, y, energia);
		setDirecao(geraDirecaoAleatoria());
        this.qtdEnergia = getEnergia();
	}
	
	public void pensa() {
        // Quando o cogumelo sair de perto do Agente ele irá voltar a se mover
		if (isParado() && getEnergia() < this.qtdEnergia) {
            setDirecao(geraDirecaoAleatoria());
        }

		// Se não conseguimos nos mover para a direção atual, quer dizer
		// que chegamos no final do mapa ou existe algo bloqueando nosso
		// caminho.
		if(!podeMoverPara(getDirecao())) {
			// Como não conseguimos nos mover, vamos escolher uma direção
			// nova.
			setDirecao(geraDirecaoAleatoria());
		}
		
		// Se o agente conseguie se dividir (tem energia) e se o total de energia
		// do agente é maior que 400, nos dividimos. O agente filho terá a metade
		// da nossa energia atual.
	}
	
	public void recebeuEnergia() {
		//Invocado sempre que o agente recebe energia.
        this.qtdEnergia = getEnergia();
        // O agente para, obtendo mais energia do cogumelo
        para();
        // Se a energia dele estivar bem alta ele divide
        if(podeDividir() && getEnergia() >= 900) {
			divide();
		}

	}
	
	public void tomouDano(int energiaRestanteInimigo) {
		// Invocado quando o agente está na mesma posição que um agente inimigo
		// e eles estão batalhando (ambos tomam dano).
		if (getEnergia() > energiaRestanteInimigo) {
			para();
		} else {
			setDirecao(geraDirecaoAleatoria());
		}
	}
	
	public void ganhouCombate() {
		// Invocado se estamos batalhando e nosso inimigo morreu.
	}
	
	public void recebeuMensagem(String msg) {
		// Invocado sempre que um agente aliado próximo envia uma mensagem.
	}
	
	public String getEquipe() {
		return "MushroomHunters";
	}
}
