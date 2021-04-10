package warzone.adapter;

import java.io.IOException;

import warzone.model.GameContext;
import warzone.service.ConquestMapWriter;
import warzone.service.MapService;

public class MapWriterAdapter extends MapService{
	/**
	 * conquest map writer
	 */
	ConquestMapWriter d_conquestMapWriter;

	/**
	 * the constructor of the class
	 * @param p_gameContext
	 */
	public MapWriterAdapter(GameContext p_gameContext, ConquestMapWriter p_conquestMapWriter) {
		super(p_gameContext);
		this.d_conquestMapWriter = p_conquestMapWriter;
	}
	
	public boolean saveMap(String p_fileName) throws IOException {
		return d_conquestMapWriter.saveConquestMap(p_fileName);
	}
}
