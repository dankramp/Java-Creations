import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;

public class Pic2Text_GUI extends JFrame implements ActionListener{
	
	private BufferedImage image;
	private JTextArea fileName;
	private JTextArea text;
	private JComboBox pictureRes;
	
	public Pic2Text_GUI() {
		super("Pic2Text GUI");
		setSize(600,800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		initialize();
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		new Pic2Text_GUI().setVisible(true);
	}
	
	private void initialize() {
		JPanel controls = new JPanel();
		JButton open = new JButton("Open File");
		fileName = new JTextArea("No file selected.   ");
		String[] res = { "1", "2", "3", "4", "5" };
		pictureRes = new JComboBox(res);
		pictureRes.setSelectedIndex(2);
		JButton genText = new JButton("Convert");
		text = new JTextArea();
		JScrollPane scroll = new JScrollPane(text);
		Font font = new Font("Consolas", Font.PLAIN, 3);
		text.setFont(font);
		JTextArea resolution = new JTextArea("Resolution:");
		resolution.setEditable(false);
		resolution.setOpaque(false);
		
		fileName.setEditable(false);
		open.addActionListener(this);
		genText.addActionListener(this);
		text.setEditable(false);
		
		controls.add(fileName);
		controls.add(open);
		controls.add(resolution);
		controls.add(pictureRes);
		controls.add(genText);
		
		add(controls, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("Open File")) {
			open();
		} else if (arg0.getActionCommand().equals("Convert")) {
			convert();
		}
		
	}

	private void open() {
		// File chooser - file extension types accepted below
		JFileChooser chooser = new JFileChooser("./src/");
		chooser.setFileFilter(new FileFilter() {
			public String getDescription() {
				return "Image Files (*.jpg, *.jpeg, *.png)";
			}
			public boolean accept(File f) {
				if (f.isDirectory()) {
					return true;
				} else {
					String filename = f.getName().toLowerCase();
					return filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith(".png");
				}
			}
		});
		
		int returned = chooser.showOpenDialog(this);
		
		if (returned == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			try {
				image = ImageIO.read(file);
				fileName.setText(file.getName());
			} catch (IOException e) {
				e.printStackTrace();
				image = null;
				JOptionPane.showInternalMessageDialog(null, "File not valid.", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void convert() {
		if (!fileName.getText().equals("No file selected.   ")) {
			text.setText("");
			int height = image.getHeight();
			int width = image.getWidth();
			final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
			int rgb[][] = new int[height][width];
	        final boolean hasAlphaChannel = image.getAlphaRaster() != null;
			
		    final int pixelLength = (hasAlphaChannel)? 4: 3;
	        for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
	            int argb = -16777216; // 255 alpha
	            argb += ((int) pixels[pixel] & 0xff); // blue
	            argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
	            argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
	            Color c = new Color(argb);
	            int val = (c.getRed()+c.getBlue()+c.getGreen())/3;
	            rgb[row][col] = val;
	            col++;
	            if (col == width) {
	            	col = 0;
	            	row++;
	            }
	        }
	        int res = pictureRes.getSelectedIndex() + 1;
	        for (int r = 0; r<height; r+=res) {
	        	for (int c = 0; c<width; c+=res) {
	        		int val = 0;
	        		for (int i = 0; i<res; i++) {
	        			for (int a = 0; a<res; a++) {
	        				if (r+i<rgb.length && c+a<rgb[0].length) val += rgb[r+i][c+a];
	        			}
	        		}
	        		val/=(res*res);
	                if (val < 20) text.append("&&");
	       			else if (val < 35) text.append("BB");
	       			else if (val < 65) text.append("00");
	       			else if (val < 100) text.append("ll");
	       			else if (val < 160) text.append(";;");
	       			else if (val < 200) text.append("..");
	       			else text.append("  ");
	        	}
	        	text.append("\n");
	        }
		} else {
			int response = JOptionPane.showConfirmDialog(null, "No file selected. Please choose a file and try again.", "Error", JOptionPane.OK_CANCEL_OPTION);
			if (response == JOptionPane.OK_OPTION) open();
		}
	}
}
