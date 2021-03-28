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
			System.out.println("Soldado " + getId() + " avançando !");
        }

		// Se não conseguimos nos mover para a direção atual, quer dizer
		// que chegamos no final do mapa ou existe algo bloqueando nosso
		// caminho.
		if(!podeMoverPara(getDirecao())) {
			// Como não conseguimos nos mover, vamos escolher uma direção
			// nova.
			setDirecao(geraDirecaoAleatoria());
			System.out.println("Soldado " + getId() + " Algo bloqueando alterando caminho.");
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
		System.out.println("Agente " + getId() + " Recebendo Energia");
        // Se a energia dele estivar bem alta ele divide
        if(podeDividir() && getEnergia() > 900) {
			divide();
			System.out.println("Agente " + getId() + " Enegia cheia se dividindo.");
		}

	}
	
	public void tomouDano(int energiaRestanteInimigo) {
		// Invocado quando o agente está na mesma posição que um agente inimigo
		// e eles estão batalhando (ambos tomam dano).
		if (getEnergia() > energiaRestanteInimigo) {
			para();
			System.out.println("Agente " + getId() + " tomando dano.");
		} else {
			setDirecao(geraDirecaoAleatoria());
			System.out.println("Agente " + getId() + " fugindo da luta.");
		}
	}
	
	public void ganhouCombate() {
		// Invocado se estamos batalhando e nosso inimigo morreu.
		System.out.println("Combate ganho !");
	}
	
	public void recebeuMensagem(String msg) {
		// Invocado sempre que um agente aliado próximo envia uma mensagem.

		String[] Coordenadas = msg.split(",");
		int meuX = Integer.parseInt(Coordenadas[0]);
		int meuY = Integer.parseInt(Coordenadas[1]);
		System.out.println("Energia proxima enviando mensagem !");
		
		MovePara(meuX, meuY);
	}

	public void MovePara(int x, int y) {
		// Move para ele recebe como parametro os numeros da coordenada de um agente que
		// esta recebendo energia;
		// o que eu quero fazer é que meus agentes se movam para esse lugar no mapa.
		if (getX() > x) {
			System.out.println("Me movendo para captar energia!");
			setDirecao(ESQUERDA);
		} else if (getX() < x) {
			System.out.println("Me movendo para captar energia!");
			setDirecao(DIREITA);
		} else if (getY() > y) {
			System.out.println("Me movendo para captar energia!");
			setDirecao(CIMA);
		} else if (getY() < y) {
			System.out.println("Me movendo para captar energia!");
			setDirecao(BAIXO);
		}
	}
	
	public String getEquipe() {
		return "MushroomHunters";
	}
}
