package warzone.adapter;

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
}
