package br.unb.cic.lp.gol;

import java.util.Scanner;
import javax.swing.*;

import br.unb.cic.lp.gol.estrategias.Conway;
import br.unb.cic.lp.gol.estrategias.HighLife;

/**
 * Atua como um componente de apresentacao (view), exibindo o estado atual do
 * game com uma implementacao baseada em caracteres ASCII.
 * 
 * @author rbonifacio
 */
public class GameView {
	private static final String LINE = "+-----+";
	private static final String DEAD_CELL = "|     |";
	private static final String ALIVE_CELL = "|  o  |";
	
	private static final int INVALID_OPTION = 0;
	private static final int MAKE_CELL_ALIVE = 1;
	private static final int NEXT_GENERATION = 2;
	private static final int CONWAY = 3;
	private static final int HIGH_LIFE = 4; 
	private static final int HALT = 5; 
	private static final int AUTO_GENERATION = 6;

	private GameEngine engine;
	private GameController controller;

	/**
	 * Construtor da classe GameBoard
	 */
	public GameView(GameController controller, GameEngine engine) {
		this.controller = controller;
		this.engine = engine;
	}

	/**
	 * Atualiza o componente view (representado pela classe GameBoard),
	 * possivelmente como uma resposta a uma atualizacao do jogo.
	 */
	public void update() {
		controller.getGui().refresh();
	}
	
	public void updateInfinity(){
		int i=0;
		//evitar stack OverFlow
		do {
			controller.getGui().refresh();
			nextGeneration(false);
		} while (i<3);
	}

	public void processChoice(String choice) {
		System.out.println("\n \n");
		int option;
		do {
			option = parseOption(choice);
		}while(option == 0);
		
		switch(option) {
			case MAKE_CELL_ALIVE : makeCellAlive(); break;
			case NEXT_GENERATION : nextGeneration(false); break;
			case CONWAY : engine.setEstrategia(new Conway()); update(); break;
			case HIGH_LIFE : engine.setEstrategia(new HighLife()); update();break;
			case HALT : halt();break;
			case AUTO_GENERATION : nextGeneration(true);
		}
	}
	
	private void makeCellAlive() {
		int i, j = 0;
		Scanner s = new Scanner(System.in);
		
		do {
			System.out.print("\n Inform the row number (0 - " + engine.getHeight() + "): " );
			
			i = s.nextInt();
			
			System.out.print("\n Inform the column number (0 - " + engine.getWidth() + "): " );
			
			j = s.nextInt();
		}while(!validPosition(i,j));
		
		controller.makeCellAlive(i, j);
	}
	
	private void nextGeneration(boolean infinity) {
		controller.nextGeneration(infinity);
	}
	
	private void halt() {
		controller.halt();
	}
	
	private boolean validPosition(int i, int j) {
		System.out.println(i);
		System.out.println(j);
		return i >= 0 && i < engine.getHeight() && j >= 0 && j < engine.getWidth();
	}

	private int parseOption(String option) {
		if(option.equals("1")) {
			return MAKE_CELL_ALIVE;
		}
		else if (option.equals("2")) {
			return NEXT_GENERATION;
		}
		else if (option.equals("3")) {
			return CONWAY;
		}
		else if (option.equals("4")) {
			return HIGH_LIFE;
		}
		else if(option.equals("5")) {
			return HALT;
		}
		else if(option.equals("6")){
			return AUTO_GENERATION;
		}
		else return INVALID_OPTION;
	}

	/* Imprime uma linha usada como separador das linhas do tabuleiro */
	private void printLine() {
		for (int j = 0; j < engine.getWidth(); j++) {
			System.out.print(LINE);
		}
		System.out.print("\n");
	}

	/*
	 * Imprime os identificadores das colunas na primeira linha do tabuleiro
	 */
	private void printFirstRow() {
		System.out.println("\n \n");
		for (int j = 0; j < engine.getWidth(); j++) {
			System.out.print("   " + j + "   ");
		}
		System.out.print("\n");
	}
}
