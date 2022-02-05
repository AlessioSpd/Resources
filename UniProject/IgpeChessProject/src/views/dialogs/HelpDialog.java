package views.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import config.Settings;

public class HelpDialog extends JDialog{

	private final Font font = new Font("Arial", Font.PLAIN, Settings.screenHeight/35);
	
	private int width;
	private int height;
	private JPanel packer;
	private JLabel label;
	private JScrollPane scroller;
	
	private final String rules = "<html>Regole scacchi: come si muovono i pezzi sulla scacchiera "
			+ "<br><br>Pedone:<br>la prima mossa gli consente di muoversi di una o due caselle in avanti a patto che la casella finale e la casella"
			+ "<br>saltata siano libere. Se spostandosi il pedone finisce accanto al pedone avversario allora quest' ultimo può effettuare la presa"
			+ "<br>en passant (o presa al varco).  Nelle mosse successive il pedone può avanzare di una sola casa. A differenza degli altri pezzi non"
			+ "<br>può mai muoversi all'indietro e cattura gli avversari spostandosi in senso diagonale e sempre in avanti. Un pedone viene promosso e"
			+ "<br>sostituito con un pezzo dello stesso colore, scelto dal giocatore, quando raggiunge l'ottava traversa."
			+ "<br><br>Cavallo:<br>pu' spostarsi e catturare sia su caselle nere che su caselle bianche e i suoi movimenti formano idealmente una 'L'."
			+ "<br>L'unico dei sedici pezzi a cui è permesso 'saltare' sia gli alleati che gli avversari e pur trovandosi dietro i pedoni può essere"
			+ "<br>mosso senza spostarli<br><br>Alfiere:<br>può muoversi diagonalmente in base al numero delle caselle libere disponibili e la sua"
			+ "<br>casa d'arrivo non può essere occupata da un pezzo amico.<br><br>Torre:<br>la torre può muoversi sia in senso verticale che orizzontale"
			+ "<br>in base alle case libere disponibili davanti e di fianco. Se non ostacolata da uno qualunque dei pezzi, a prescindere dalla sua"
			+ "<br>posizione, la torre controlla sempre 14 case. Il protagonista, insieme al re, di una mossa particolare conosciuta con il nome di"
			+ "<br>arrocco, l'unica che permette di spostare contemporaneamente due pezzi e che consente al re di muoversi di due caselle."
			+ "<br><br>Regina:<br>o donna, è il pezzo più forte e conosciuto in passato con il nome di \"generale\", \"stratega\" o \"visir\"."
			+ "<br>Può spostarsi verticalmente, orizzontalmente o diagonalmente in base alle caselle libere disponibili e a ogni mossa può anche"
			+ "<br>scegliere se muoversi come un alfiere o una torre. A differenza della torre non può effettuare la mossa dell'arrocco."
			+ "<br><br>Re:<br>tra i sedici pezzi è il più importante e può muoversi in qualsiasi direzione ma una casa alla volta e a patto che la"
			+ "<br>casella di destinazione non sia minacciata da un pezzo avversario. Al re è permesso catturare pezzi avversari muovendosi sulla casa"
			+ "<br>occupata da questi ultimi e insieme alla torre può eseguire la mossa dell'arrocco. Quando il re è minacciato si trova sotto scacco."
			+ "<br>Lo scacco matto (o più semplicemente, matto) è invece una situazione che si verifica quando non è più possibile per il giocatore"
			+ "<br>difendere il re con altri pezzi o spostarlo in una casella libera che lo salverebbe dall'avversario. Il giocatore che utilizza questa "
			+ "<br>mossa chiude la partita e si aggiudica la vittoria.<html>";
	
	public HelpDialog() {
		super();
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setTitle("Help page");
		this.setModal(true);
		this.width = Settings.screenWidth/3-80;
		this.height = width - 150;
		this.setPreferredSize(new Dimension(width,height));
		initText();
	}
	
	private void initText() {
		label = new JLabel(rules);
		scroller = new JScrollPane(label, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		this.add(scroller);
	}
}

