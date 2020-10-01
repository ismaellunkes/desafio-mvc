package br.com.publicaproway.desafio.services;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class ReportServices {
	
	File file;
	File directory;
	
	
	
	public void reportCreateHTML() throws IOException {
		
		directory = new File("relatorios");
		
		if (!directory.exists()) {
			directory.mkdir();	
		}
				
		file = new File(directory, "report_"+Instant.now().toString().replace(":", "_").replace(".", "_")+".html");
								
	}

	public void addHeader(String title) throws IOException {
		
		FileWriter fw = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("<html>");
		bw.write("<title>"+title+"</title>");				
		bw.write("<body>");
        bw.newLine();
        bw.close();
        fw.close();
        
	}
	
	public void addTitle(String title) throws IOException {
		
		FileWriter fw = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("<center>");
		bw.write("<h2>"+title+"</h2>");				
		bw.write("</center>");
        bw.newLine();
        bw.close();
        fw.close();
        
	}

	
	public void addTable(DefaultTableModel  defaultTableModel) throws IOException {
		
		FileWriter fw = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("<center>");
		bw.write("<table border=\"1\">");
		for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
			bw.write("<tr>");			
			for (int j = 0; j < defaultTableModel.getColumnCount(); j++) {
				bw.write("<td>");
				bw.write(defaultTableModel.getValueAt(i, j).toString());
				bw.write("</td>");				
			}
			bw.write("</tr>");
		}		
		bw.write("</table>");
		bw.write("</center>");
        bw.newLine();
        
        bw.close();
        fw.close();
                
	}
	
	public void addItemList(List<String> items) throws IOException {
		
		FileWriter fw = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(fw);
		
		for (String item : items) {
			bw.write("<li>"+item+"</li>");
		}
		
        bw.newLine();
        bw.close();
        fw.close();
        
	}
	
	public void addFooter() throws IOException {
		
		FileWriter fw = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write("<center><h5> Relatório de jogos - "+Instant.now()+"</h5></center>");				
		bw.write("</body>");
		bw.write("</html>");
        bw.newLine();
        bw.close();
        fw.close();
        
	}

	public void openReport() throws IOException {
		
		Desktop.getDesktop().open(file);
		
	}
	
}
