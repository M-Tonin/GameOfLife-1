package br.unb.cic.lp.gol.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import br.unb.cic.lp.gol.GameEngine;

public class CellButton extends JButton implements ActionListener {
	
	ImageIcon alive,dead,dead2;
	private boolean isAlive;
	
	public int iPos;
	public int jPos;
	
	private GameEngine engine;

	public CellButton(int i, int j,GameEngine engine){
		addActionListener(this);
		alive = new ImageIcon(this.getClass().getResource("image/Alive.png"));
		this.iPos=i;
		this.jPos=j;
		this.engine = engine;
		dead = new ImageIcon(this.getClass().getResource("image/Dead.png"));
		//dead2 = new ImageIcon(this.getClass().getResource("image/dead2.png"));
	}
	
	public void Kill(){
		isAlive = false;
		setIcon(dead);
	}
	
	public void Create(){
		isAlive = true;
		setIcon(alive);
	}
	
	public void actionPerformed (ActionEvent e){
		engine.makeCellAlive(iPos, jPos);
		isAlive = !isAlive;
		if(isAlive){
			setIcon(alive);
		}else{
			setIcon(dead);
		}
	}
}
