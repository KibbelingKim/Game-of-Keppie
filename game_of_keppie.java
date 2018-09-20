/**
 * @author roelantvanderhilst@gmail.com
 */

import java.util.*;

public class GoK {
	
	// Grid Dimensions x-axis size x, y-axis size y, Delay Between Generations in ms z, Number of Generations n
	public static int x = 32;
	public static int y = 14;
	public static int z = 175;
	public static int n = 200;
	
	public static char liveCel = ' ';
	public static char deadCel = '█';
	public static int z2 = 2000;
	public static char g[][] = new char[y][x];
	public static char g2[][] = new char[y][x];
	public static int genNo = 0;
	public static String xBorder = "";
	
	// Main Method
	public static void main(String[] args) {
		
		xBorder = new String(new char[x]).replace("\0", "═");
		
		makeGenesis();
		gtog2();
		delay2();
		print();
		appRules();
		g2tog();
		delay2();
		print();
		
		for(int i=0;i<n;i++) {
			genNo++;
			appRules();
			g2tog();
			delay();
			print();
		}
	}
	
	// Print Boardstate Method
	public static void print() {
		
		String s = "";
		
		System.out.println("\r\n\r\n\r\n          Generation: " + genNo);
		System.out.println("      ╔" + xBorder + "╗");
		for(int i=0;i<y;i++) {
			s = "      ║";
			for(int ii=0;ii<x;ii++) {
				s += g[i][ii];
			}
			System.out.println(s + "║");
		}
		System.out.println("      ╚" + xBorder + "╝\r\n");
	}
	
	// Transfer Main Boardstate g to Temporary Boardstate g2 Method
	public static void gtog2() {
		for(int i=0;i<y;i++) {
			for(int ii=0;ii<x;ii++) {
				g2[i][ii] = g[i][ii];
			}
		}
	}
	
	// Transfer Updated Boardstate g2 to Main Boardstate g Method
	public static void g2tog() {
		for(int i=0;i<y;i++) {
			for(int ii=0;ii<x;ii++) {
				g[i][ii] = g2[i][ii];
			}
		}
	}
	
	// Count Live Neighbours Method
	public static int cln(int yy, int xx) {
		
		int ln = 0;
		
		if(yy != 0 && xx != 0 && g[yy-1][xx-1] == liveCel) {
			ln++;
		}
		if(yy != 0 && g[yy-1][xx] == liveCel) {
			ln++;
		}
		if(yy != 0 && xx != x-1 && g[yy-1][xx+1] == liveCel) {
			ln++;
		}
		if(xx != 0 && g[yy][xx-1] == liveCel) {
			ln++;
		}
		if(xx != x-1 && g[yy][xx+1] == liveCel) {
			ln++;
		}
		if(yy != y-1 && xx != 0 && g[yy+1][xx-1] == liveCel) {
			ln++;
		}
		if(yy != y-1 && g[yy+1][xx] == liveCel) {
			ln++;
		}
		if(yy != y-1 && xx != x-1 && g[yy+1][xx+1] == liveCel) {
			ln++;
		}
		return ln;
	}
	
	// Apply Rules to Create Updated Boardstate g2 Method
	public static void appRules() {
		for(int i=0;i<y;i++) {
			for(int ii=0;ii<x;ii++) {
				if(g[i][ii] == liveCel && (cln(i, ii) < 2 || cln(i, ii) > 3)) {
					g2[i][ii] = deadCel;
				}
				else if(g[i][ii] == deadCel && cln(i, ii) == 3) {
					g2[i][ii] = liveCel;
				}
			}
		}
	}
	
	// Short Delay Method
	public static void delay() {
		try {
			Thread.sleep(z);
		}
		catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
	
	// Long Delay Method
	public static void delay2() {
		try {
			Thread.sleep(z2);
		}
		catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
	
	// Make Genesis Boardstate Method
	public static void makeGenesis() {
		for(int i=0;i<y;i++) {
			for(int ii=0;ii<x;ii++) {
				
				Random rb = new Random();
				
				if(rb.nextBoolean()) {
					g[i][ii] = deadCel;
				}
				else {
					g[i][ii] = liveCel;
				}
			}
		}
	}
}