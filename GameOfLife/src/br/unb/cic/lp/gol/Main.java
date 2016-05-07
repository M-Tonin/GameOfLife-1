package br.unb.cic.lp.gol;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

import br.unb.cic.lp.gol.GUI.GUI;

import br.unb.cic.lp.gol.estrategias.Conway;

/**
 * Classe que define o metodo principal do programa; ou 
 * seja, o metodo que vai ser executado pela JVM para inicializar 
 * o programa. 
 * 
 * @author rbonifacio
 */
public class Main {

	public static void main(String args[]) {
		
		GameController controller = new GameController();
		
		Statistics statistics = new Statistics();
		BeanFactory factory = new XmlBeanFactory(new FileSystemResource("spring.xml"));
		EstrategiaDeDerivacao Conway=(EstrategiaDeDerivacao)factory.getBean("Conway");

		GameEngine engine = new GameEngine(10, 10, statistics);	
		
		//nessa implementacao, a estrategia do Conway eh 
		//configurada como a estrategia inicial. 
		engine.setEstrategia(Conway);
		
		GameView board = new GameView(controller, engine);
		
		controller.setBoard(board);
		controller.setEngine(engine);
		controller.setStatistics(statistics);
		
		GUI Interface = new GUI(controller);
		controller.setGui(Interface);
		
		controller.start();
	}
}
