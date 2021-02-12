package warzone.service;

import warzone.model.Continent;
import warzone.model.Country;
import warzone.model.GameContext;

import java.util.Map;


public class MapService {

	public boolean validateMap() {
		return true;
	}

	/**
	 * generate the table of continent information
	 * @param p_gameContext game context
	 * @return continentId and continentName
	 */
	public static String displayContinentTable(GameContext p_gameContext) {

		Map<Integer, Continent> l_mapContinent = p_gameContext.getContinents();

		if( l_mapContinent.isEmpty())
			return "No continent exist.";

		String l_returnTable = "";
		l_returnTable += String.format("%-15s", "ContinentId") + "ContinentsName\n";

		for (Continent _continent : l_mapContinent.values()) {
			l_returnTable += String.format("%-15s", _continent.getContinentID()) + _continent.getContinentName() + "\n";
		}

		return l_returnTable;
	}

	/**
	 * generate the table of country information
	 * @param p_gameContext game context
	 * @return continentId, countryId, countryName, neighbours
	 */
	public static String displayCountryTable(GameContext p_gameContext) {

			Map<Integer, Continent> l_mapContinent = p_gameContext.getContinents();

			String l_returnTable = "";
			boolean l_hasTable = false;

			l_returnTable += String.format("%-15s", "ContinentId")
					+ String.format("%-15s", "CountryId")
					+ "CountryName\n";

			for (Continent _continent : l_mapContinent.values()) {

				Map<Integer, Country> _mapCountry = _continent.getCountries();
				for (Country _country : _mapCountry.values()) {

					l_hasTable = true;
					l_returnTable += String.format("%-15s", _continent.getContinentID())
							+ String.format("%-15s", _country.getCountryID())
							+ _country.getCountryName() + "\n";
				}
			}

		if(l_hasTable)
			return l_returnTable;
		else
			return "No country exist.";
	}

	/**
	 * generate the table of country neighbour
	 * @param p_gameContext game context
	 * @return countryId, neighbours
	 */
	public static String displayCountryNeighbourTable(GameContext p_gameContext) {

		Map<Integer, Continent> l_mapContinent = p_gameContext.getContinents();

		String l_returnTable = "";
		l_returnTable += String.format("%-15s", "CountryId") + "NeighbourCountries\n";
		for (Continent _continent : l_mapContinent.values()) {

			Map<Integer, Country> _mapCountry = _continent.getCountries();
			for (Country _country : _mapCountry.values()) {

				Map<Integer, Country> _mapNeighbor = _country.getNeighbors();
				String _neighbors = "";
				for (Country _nCountry : _mapNeighbor.values())
					_neighbors += _nCountry.getCountryID() + " ";

				l_returnTable += String.format("%-15s", _country.getCountryID()) + _neighbors + "\n";
			}
		}
		return l_returnTable;
	}
}
