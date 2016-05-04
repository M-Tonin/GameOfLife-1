package br.unb.cic.lp.gol.GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import br.unb.cic.lp.gol.GameController;
import br.unb.cic.lp.gol.GameEngine;

public class GUI {
	private JFrame janela;
	private JPanel tabuleiro;
	private JPanel painelDeBotoes;
	private GameController controller;
	private CellButton celulas[][];
	
	public GUI(GameController controller){
		this.controller = controller;
		preparaTela();
		preparaTabuleiro();
		preparaOptionButtonsPanel();
		preparaBotoes();
	}
	
	private void preparaTela(){
		janela = new JFrame("Jogo da Vida");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setLayout(new BorderLayout());
	}
	
	public void mostraTela() {
	    janela.pack();
	    janela.setSize(540, 540);
	    janela.setResizable(false);
	    janela.setLocationRelativeTo(null);
	    janela.setVisible(true);
	  }
	
	public void refresh(){
		GameEngine Engine = controller.getEngine();
		for (int i = 0; i < controller.getEngine().getHeight(); i++) {
			for (int j = 0; j < controller.getEngine().getWidth(); j++) {
				if(Engine.isCellAlive(i, j)){
					celulas[i][j].Create();
				}else{
					celulas[i][j].Kill();
				}
			}
		}
		janela.setVisible(true);
	}
	
	private void preparaOptionButtonsPanel() {
	  painelDeBotoes = new JPanel();
	  janela.add(painelDeBotoes, BorderLayout.SOUTH);
	}
	
	private void optionButton(String nome, final String valor) {
	  JButton botao = new JButton(nome);
	  botao.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	janela.dispose();
	    	controller.getBoard().processChoice(valor);
	    }
	  });
	  painelDeBotoes.add(botao);
	}
	
	private void preparaBotoes(){
		optionButton("Next generation","2");
		optionButton("Conway","3");
		optionButton("High Life","4");
		optionButton("Halt","5");
		optionButton("Automatic Generations","6");
	}
	
	private void preparaTabuleiro(){
		tabuleiro = new JPanel();
		
		int Height = controller.getEngine().getHeight();
		int Width = controller.getEngine().getWidth();
		
		tabuleiro.setLayout(new GridLayout(Height,Width));
		celulas = new CellButton[Height][Width];
		
		for(int i=0;i<Height;i++){
			for(int j=0; j<Width; j++){
				celulas[i][j]=new CellButton(i,j,controller.getEngine());
				tabuleiro.add(celulas[i][j]);
			}
		}
		janela.add(tabuleiro, BorderLayout.CENTER);
	}
	
}
