package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import controller.LoadPageCtrl;

public class LoadPage extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JButton back, delete;
	private BufferedImage img = null;
	private LinkedList<JLabel> labels;
	private JPanel innerPanel;

	public LoadPage(JFrame frame) {

		this.frame = frame;
		innerPanel = new JPanel();
		labels = new LinkedList<JLabel>();
		requestFocusInWindow();
		setFocusable(true);
		this.frame.setFocusable(true);
		frame.requestFocusInWindow();
		this.frame.requestFocus();
		initView();

	}

	private void initView() {

		ImageIcon icon = new ImageIcon(getClass().getResource("/supportMedia/back.png"));

		back = new JButton(icon);
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		back.setBorder(BorderFactory.createEmptyBorder());
		back.setContentAreaFilled(false);

		icon = new ImageIcon(getClass().getResource("/supportMedia/delete.png"));

		delete = new JButton(icon);
		delete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		delete.setBorder(BorderFactory.createEmptyBorder());
		delete.setContentAreaFilled(false);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		innerPanel.setOpaque(false);
		innerPanel.setBorder(BorderFactory.createEmptyBorder(300, 2, labels.size() * 20 + 300, 2));
		add(innerPanel);

		JPanel innerPanel2 = new JPanel();

		innerPanel2.setOpaque(false);
		innerPanel2.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 2));
		innerPanel2.add(back);
		innerPanel2.add(delete);
		add(innerPanel2);

		JLabel contentPane = new JLabel();
		contentPane.setLayout(new BorderLayout());
		frame.setContentPane(contentPane);
		frame.getContentPane().add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// frame.setUndecorated(true);
		frame.setVisible(true);
		LoadPageCtrl loadPageCtrl = new LoadPageCtrl(this);
		addActListner(loadPageCtrl);
		frame.addKeyListener(loadPageCtrl);

		File file = new File(getClass().getResource("/supportMedia/loadpage.jpg").getFile());
		String path = file.getAbsolutePath().replaceAll("%20", " ");
		try {
			img = ImageIO.read((new File(path)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addActListner(ActionListener listner) {

		back.addActionListener(listner);
		delete.addActionListener(listner);
	}

	@Override
	public void paintComponent(Graphics g) {
		// the paint method
		super.paintComponent(g);
		Graphics2D gc = (Graphics2D) g;
		gc.drawImage(img, -50, 0, null);
	}

	public JButton getBack() {
		return back;
	}

	public JButton getDelete() {
		return delete;
	}

	public JFrame getFrame() {
		return frame;
	}

	public JPanel getInnerPanel() {
		return innerPanel;
	}

	public void highLightChoice(LinkedList<JLabel> labels, int pointer) {

		this.labels = labels;
		innerPanel.removeAll();
		for (int i = 0; i < labels.size(); i++) {

			labels.get(i).setFont(new Font("Consolas", Font.BOLD, 30));
			labels.get(i).setForeground(Color.white);
			labels.get(i).setOpaque(false);
			if (i == pointer) {
				labels.get(i).setOpaque(true);
				labels.get(i).setForeground(Color.red);
				labels.get(i).setBackground(Color.lightGray);
			}
			innerPanel.add(labels.get(i));
			setVisible(true);
			frame.setVisible(true);
			repaint();
		}
	}

}
