package warzone.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import warzone.model.GameContext;
import warzone.model.LogEntryBuffer;
import warzone.model.Observable;
import warzone.model.Observer;

public class LogService implements Observer{
	/**
	 * This methods will print log. The format will be [ time, phase, order, result ]
	 */
	@Override
	public void update(Observable p_observable) {
		// TODO Auto-generated method stub
		StringBuilder l_sb = new StringBuilder();
		l_sb.append("[ ");
		l_sb.append(((LogEntryBuffer) p_observable).getResult());
		l_sb.append(" ] ");
		l_sb.append(((LogEntryBuffer) p_observable).getTime());
		l_sb.append(", phase: ");
		l_sb.append(((LogEntryBuffer) p_observable).getPhase());
		l_sb.append(", order: ");
		l_sb.append(((LogEntryBuffer) p_observable).getOrder());
		l_sb.append(", message: ");
		l_sb.append(((LogEntryBuffer) p_observable).getMessage());
		System.out.println(l_sb.toString());
		
		if (GameContext.getGameContext().getIsLog()) {
			write2LogFile(l_sb.toString());
		}
	}
	
	private void write2LogFile(String p_content) {
		FileWriter l_fw = null;
		try {
			File directory = new File("./");
			File l_dir = new File(GameContext.getGameContext().getLogfolder());
			if (!l_dir.isDirectory()) {
				l_dir.mkdir();
			}
			File l_f = new File(l_dir, "log");
			if (!l_f.isFile()) {
				l_f.createNewFile();
			}
	        l_fw = new FileWriter(l_f, true);
		} catch (IOException e) {
	        e.printStackTrace();
		}
		PrintWriter l_pw = new PrintWriter(l_fw);
		l_pw.println(p_content);
		l_pw.flush();
		try {
		        l_fw.flush();
		        l_pw.close();
		        l_fw.close();
		} catch (IOException e) {
		        e.printStackTrace();
		}
	}
}
