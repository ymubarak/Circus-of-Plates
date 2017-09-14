package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.ThemeMenuCtrl;

@SuppressWarnings("serial")
public class ThemeMenu extends JPanel {

	private JButton theme1, theme2, theme3;
	private JFrame frame;
	private JPanel innerPane;
	private BufferedImage img;

	public ThemeMenu(JFrame frame) {

		this.frame = frame;
		initView();
	}

	private void initView() {

		ImageIcon icon = new ImageIcon(getClass().getResource("/supportMedia/thumbnail1.png"));
		theme1 = new JButton(icon);
		theme1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		theme1.setBorder(BorderFactory.createEmptyBorder());
		theme1.setContentAreaFilled(false);
		theme1.setText("Theme 1");
		theme1.setFont(new Font("Arial", Font.PLAIN, 40));
		theme1.setForeground(Color.black);
		theme1.setVerticalTextPosition(AbstractButton.BOTTOM);
		theme1.setHorizontalTextPosition(AbstractButton.CENTER);

		icon = new ImageIcon(getClass().getResource("/supportMedia/thumbnail2.png"));
		theme2 = new JButton(icon);
		theme2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		theme2.setBorder(BorderFactory.createEmptyBorder());
		theme2.setContentAreaFilled(false);
		theme2.setText("Theme 2");
		theme2.setFont(new Font("Arial", Font.PLAIN, 40));
		theme2.setForeground(Color.black);
		theme2.setVerticalTextPosition(AbstractButton.BOTTOM);
		theme2.setHorizontalTextPosition(AbstractButton.CENTER);

		icon = new ImageIcon(getClass().getResource("/supportMedia/thumbnail3.png"));
		theme3 = new JButton(icon);
		theme3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		theme3.setBorder(BorderFactory.createEmptyBorder());
		theme3.setContentAreaFilled(false);
		theme3.setText("Theme 3");
		theme3.setFont(new Font("Arial", Font.PLAIN, 40));
		theme3.setForeground(Color.black);
		theme3.setVerticalTextPosition(AbstractButton.BOTTOM);
		theme3.setHorizontalTextPosition(AbstractButton.CENTER);
		addListner(new ThemeMenuCtrl(this));
		display();

	}

	private void display() {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel labelPane = new JPanel();
		JLabel label = new JLabel();
		label.setText("Chosse Theme ");
		label.setFont(new Font("Arial", Font.BOLD, 72));
		label.setForeground(Color.black);
		labelPane.add(label, BorderLayout.CENTER);
		labelPane.setOpaque(false);
		labelPane.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 2));
		add(labelPane, BorderLayout.CENTER);

		innerPane = new JPanel(new GridLayout(1, 3, 0, 40));
		innerPane.add(theme1);
		innerPane.add(theme2);
		innerPane.add(theme3);
		innerPane.setOpaque(false);
		innerPane.setBorder(BorderFactory.createEmptyBorder(0, 2, 230, 2));

		add(innerPane);
		JLabel contentPane = new JLabel();
		contentPane.setLayout(new BorderLayout());
		frame.setContentPane(contentPane);
		frame.getContentPane().add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}

	@Override
	public void paintComponent(Graphics g) {
		// the paint method
		super.paintComponent(g);
		Graphics2D gc = (Graphics2D) g;
		File file = new File(getClass().getResource("/supportMedia/themes.jpg").getFile());
		String path = file.getAbsolutePath().replaceAll("%20", " ");
		try {
			img = ImageIO.read((new File(path)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gc.drawImage(img, 0, 0, null);

	}

	private void addListner(ActionListener listener) {
		theme1.addActionListener(listener);
		theme2.addActionListener(listener);
		theme3.addActionListener(listener);
	}

	public JButton getTheme1() {

		return theme1;
	}

	public JButton getTheme2() {

		return theme2;
	}

	public JButton getTheme3() {

		return theme3;
	}

}
