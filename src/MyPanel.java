import java.awt.CardLayout;

import javax.swing.JPanel;


public interface MyPanel {
	public void SetCards(CardLayout cards);
	public CardLayout GetCards();
	public void SetConteiner(JPanel conyriner0);
}
