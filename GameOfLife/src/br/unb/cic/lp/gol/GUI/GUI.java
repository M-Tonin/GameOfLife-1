package br.unb.cic.lp.gol.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import br.unb.cic.lp.gol.GameController;

public class GUI {
	private JFrame janela;
	private JPanel painelPrincipal;
	private GameController controller;
	
	public GUI(GameController controller){
		this.controller = controller;
		preparaTela();
		preparaPainelPrincipal();
		preparaBotoes();
	}
	
	private void preparaTela(){
		janela = new JFrame("Escolha uma Opção");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void mostraTela() {
	    janela.pack();
	    janela.setSize(320, 230);
	    janela.setLocationRelativeTo(null);
	    janela.setVisible(true);
	  }
	
	private void preparaPainelPrincipal() {
	  painelPrincipal = new JPanel();
	  janela.add(painelPrincipal);
	}
	
	private void preparaBotao(String nome, final String valor) {
	  JButton botao = new JButton(nome);
	  botao.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	janela.dispose();
	    	controller.getBoard().processChoice(valor);
	    }
	  });
	  painelPrincipal.add(botao);
	}
	
	private void preparaBotoes(){
		//TODO Botão Make a cell alive deve chamar outra tela, passando as coordenadas X e Y, que atualmente são pedidas no console
		preparaBotao("Make a cell alive","1");
		preparaBotao("Next generation","2");
		preparaBotao("Conway","3");
		preparaBotao("High Life","4");
		preparaBotao("Halt","5");
		preparaBotao("Automatic Generations","6");
	}
	
}
