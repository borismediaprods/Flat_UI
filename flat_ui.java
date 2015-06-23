import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
 
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class Main {
 
	public static void main(String[] args) {
		Dimension dim = new Dimension(337, 350);
		JFrame frame = new JFrame("Flat UI Colour Picker!");
		frame.setSize(dim);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new ColorFrame(dim.width, dim.height));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
}
 
class ColorFrame extends JPanel{
 
	private static final long serialVersionUID = 1L;
 
	int w, h, blockWidth, blockHeight, padding = 5;
 
	String col[] = new String[] { "1ABC9C", "16A085", "2ECC71", "27AE60", "3498DB", "2980B9", "9B59B6", "8E44AD",
			"34495E", "2C3E50", "F1C40F", "F39C12", "E67E22", "D35400", "E74C3C", "C0392B", "ECF0F1", "BDC3C7",
			"95A5A6", "7F8C8D" };
	
	public int pos[][] = new int[20][4];
	public boolean hovered = false;
	public Clipboard clipboard;
	public StringSelection selection;
	
	public Font arialFont = new Font("Arial", Font.PLAIN, 20);
	public Font arialFont2 = new Font("Arial", Font.PLAIN, 9);
 
 
	public ColorFrame(int width, int height) {
		this.w = width;
		this.h = height;
		this.blockWidth = 60;
		this.blockHeight = blockWidth;
 
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				checkForHover(e.getPoint());
			}
		});
	}
 
	void checkForHover(Point event) {
		int x = event.x;
		int y = event.y;
		
		for (int i = 0; i < 20; i++) {
			if (x >= pos[i][0] && x <= pos[i][2] && y >= pos[i][1] && y <= pos[i][3]) {
				selection = new StringSelection("#" + col[i]);
			    clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			    clipboard.setContents(selection, selection);
				break;
			}
		}
	}
 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font("Arial", 20, 10));
 
		g.setColor(new Color(11, 11, 11));
		g.fillRect(0, 0, w, h);
 
		g.setColor(new Color(33, 33, 33));
		g.fillRect(0, 0, w, 40);
 
		int x = padding;
		int y = -10;
 
		for (int i = 0; i < 20; i++) {
			g.setColor(new Color(Integer.valueOf(col[i], 16)));
 
			if (i % 5 == 0) {
				y += 65;
				x = padding;
			}
 
			g.fillRect(x, y, blockWidth, blockHeight);
 
			pos[i][0] = x;
			pos[i][1] = y;
			pos[i][2] = blockWidth + x;
			pos[i][3] = blockHeight + y;
 
			if (i != 16)
				g.setColor(Color.WHITE);
			else
				g.setColor(Color.BLACK);
			
			
			x += blockWidth + padding;
		}
		
		g.setColor(new Color(90, 90, 90));
		g.setFont(arialFont);
		g.drawString("Press, to Copy!", getWidth() / 2 - 3 * 22, 20);
		g.setColor(new Color(90, 90, 90));
		g.setFont(arialFont2);
		g.drawString("© Boris Skurikhin", getWidth() / 2 - 1 * 35, 321);
		
		System.out.println("PAINT");
	}
}