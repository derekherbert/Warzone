package warzone.adapter;

import java.io.IOException;

import warzone.model.GameContext;
import warzone.service.ConquestMapReader;
import warzone.service.StartupService;

public class MapReaderAdapter extends StartupService{
	/**
	 * default serial UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * conquest map reader
	 */
	ConquestMapReader d_conquestMapReader;

	/**
	 * the constructor of the class
	 * @param p_gameContext
	 */
	public MapReaderAdapter(GameContext p_gameContext, ConquestMapReader p_conquestMapReader) {
		super(p_gameContext);
		this.d_conquestMapReader = p_conquestMapReader;
	}

	public boolean saveMap(String p_fileName) throws IOException {
		return d_conquestMapReader.saveConquestMap(p_fileName);
	}

	public boolean loadMap(String p_fileName) {
		return d_conquestMapReader.loadConquestMap(p_fileName);
	}
}
