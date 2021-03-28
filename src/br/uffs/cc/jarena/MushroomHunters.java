package br.uffs.cc.jarena;

public class MushroomHunters extends Agente
{
    public int quantidadeEnergia;
	public int aux;
	public int aux2;
	public int quantidadeIds;

	public MushroomHunters(Integer x, Integer y, Integer energia) {
		super(x, y, energia);
		setDirecao(geraDirecaoAleatoria());
        this.quantidadeEnergia = getEnergia();
		this.aux = 0;
		this.aux2 = 0;
		this.quantidadeIds = 0;
	}
	
	public void pensa() {
		// Faz todos os Agentes andarem pra baixo
		if (aux == 0) {
			setDirecao(BAIXO);
		}

		// Espalha os Agentes na direção direita
		if (aux % 5 == 0) {
			if (aux2 == getId() && quantidadeIds < 15) {
				setDirecao(DIREITA);
				this.quantidadeIds++;
			}
			aux2++;
		}

        // Agente volta a mover-se após distanciar-se de um cogumelo
		if (isParado() && getEnergia() < this.quantidadeEnergia) {
            setDirecao(geraDirecaoAleatoria());
			System.out.println("Agente " + getId() + " avançando !");
        }

		// Se não conseguimos nos mover para a direção atual, quer dizer
		// que chegamos no final do mapa ou existe algo bloqueando nosso
		// caminho.
		if(!podeMoverPara(getDirecao())) {
			setDirecao(geraDirecaoAleatoria());
			System.out.println("Agente " + getId() + " Algo bloqueando alterando caminho.");
		}
		aux++;
	}
	
	// Invocado sempre que o agente recebe energia.
	public void recebeuEnergia() {
		// Envia a posição para aliados próximos
		enviaMensagem(String.valueOf(getX()) + "," + String.valueOf(getY()));
        this.quantidadeEnergia = getEnergia();
        // O agente para, obtendo mais energia do cogumelo
        para();
		System.out.println("Agente " + getId() + " Recebendo Energia");
        // Se a energia dele estivar bem alta ele divide
        if(podeDividir() && getEnergia() > 900) {
			divide();
			System.out.println("Agente " + getId() + " Enegia cheia se dividindo.");
		}


	}
	// Invocado quando o agente está na mesma posição que um agente inimigo e eles estão batalhando (ambos tomam dano).
	public void tomouDano(int energiaRestanteInimigo) {
		if (getEnergia() > energiaRestanteInimigo) {
			para();
			System.out.println("Agente " + getId() + " tomando dano.");
		} else {
			setDirecao(geraDirecaoAleatoria());
			System.out.println("Agente " + getId() + " fugindo da luta.");
		}
	}
	
	// Invocado se estamos batalhando e nosso inimigo morreu.
	public void ganhouCombate() {
		System.out.println("GG Easy. Combate ganho!");
	}
	
	// Invocado sempre que um agente aliado próximo envia uma mensagem.
	public void recebeuMensagem(String msg) {

		String[] localizacao = msg.split(",");
		int x = Integer.parseInt(localizacao[0]);
		int y = Integer.parseInt(localizacao[1]);
		System.out.println("Agente " + getId() + " recebeu mensagem.");
		
		if (x > getX()){
			setDirecao(DIREITA);
		} else if (x < getX()) {
			setDirecao(ESQUERDA);
		} else if (y > getY()){
			setDirecao(BAIXO);
		} else if (y < getY()) {
			setDirecao(CIMA);
		}
	}
	
	public String getEquipe() {
		return "MushroomHunters";
	}
}
