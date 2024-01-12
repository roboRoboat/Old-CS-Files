package a9;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;


/**
 * This defines a program to visually explore the kNN classifier.
 * @author dejohnso
 *
 */
public class ClassifierGUI extends JPanel implements MouseListener {
	
	private KNNClassifier classifier; // The classifier code - no GUI
	private SampleDisplayPanel display; // The view of the samples and the k nearest
	private JScrollPane faceDisplay; // The images that are the samples
	private JPanel facesPanel; // The panel that is scrolled
	private JPanel buttonPanel; // A place to hold the buttons
	private JTextArea result; // text output from the classifier
	
	public ClassifierGUI() {
		super();
		// Create a vertical layout
		LayoutManager layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);
		// Create a classifier with a starting k of 5 neighbors
		classifier = new KNNClassifier(5);

		// The top of the program shows the samples in the sample space
		display = new SampleDisplayPanel(this);
		display.setPreferredSize(new Dimension(500,500));
		this.add(display);
		
		// Show all the images
		facesPanel = new JPanel();
  		faceDisplay = new JScrollPane(facesPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
  		faceDisplay.setPreferredSize(new Dimension(1500, 175));
		this.add(faceDisplay);

		// Output classifier results here
		result = new JTextArea("");
		result.setPreferredSize(new Dimension(500, 40));
		this.add(result);
		
		// Add buttons to control the program
		JPanel buttonPanel = addButtons();
		this.add(buttonPanel);

		readFolder();
	}
	
	
	/**
	 * For every Face stored in the classifier, make an image panel to display the Face. 
	 */
	private void addFacePanels() {
		facesPanel.removeAll();
  		for (Face face : classifier.getSamples()) {
  			FacePanel facePanel = new FacePanel(face, 100, 140);
  			facePanel.addMouseListener(this); // These panels are clickable to set the unknown sample.
  			facesPanel.add(facePanel);
  		}
		facesPanel.revalidate();
	}
	
	/**
	 * A helper method to generate all the buttons and their actions.
	 * Use anonymous Listener classes to handle clicks.
	 * @return
	 */
	private JPanel addButtons() {
		buttonPanel = new JPanel();
  		buttonPanel.setPreferredSize(new Dimension(500, 50));

  		JButton classifyButton = new JButton("Classify");
  		classifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Face unknown = classifier.getUnknown();
				if (unknown != null) {
					String classification = classifier.classifyUnknownSample(classifier.getUnknown());
					result.setText("  Unknown: " + unknown.getClassification() + "\n  Classified as: " + classification);
					repaint();					
				}
			}
  		});
  		buttonPanel.add(classifyButton);

  		JButton quitButton = new JButton("Quit");
  		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
  		});
  		buttonPanel.add(quitButton);

  		return buttonPanel;
	}
	
	
	/**
	 * Use a JFileChooser to select the directory where the images are.
	 */
	private void readFolder() {
		classifier.clearSamples();
		ArrayList<Face> faces = Face.readSamples(new File("src/a9/faces"));
        for (Face f : faces)
        		classifier.addSample(f);
        // Take the samples and turn them into image panels.
        addFacePanels();
		repaint();
	}

	
	public KNNClassifier getClassifier() {
		return classifier;
	}
		
	private static final long serialVersionUID = 1L;

	/**
	 * Run the GUI program.
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("kNN Explorer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(500,800));
		ClassifierGUI gui = new ClassifierGUI();
		frame.setContentPane(gui);
		frame.pack();
		frame.setVisible(true);
	}

	/* 
	 * Only listens for clicks on the Face images. Sets the unknown and highlights the image.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		for (Component c : facesPanel.getComponents()) {
			((JPanel)c).setBorder(null);
		}
		FacePanel clicked = (FacePanel)e.getSource();
		clicked.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		classifier.setUnknown(clicked.getFace());
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
