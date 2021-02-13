package warzone.view;

import warzone.model.Continent;
import warzone.model.Country;
import warzone.model.GameContext;
import warzone.model.Render;

import java.util.Map;

/*
 * for specific ui, should create dedicated view class.
 * */
public class GenericView {
	
	public static void println(String p_text) {
		System.out.println(p_text);
	}
	
	public static void println(Render p_content) {
		p_content.render();
	}


	/**
	 * generate the table of continent information
	 * continentId and continentName
	 *
	 * @param p_continents map of continent
	 */
	public static void printContinentTable(Map<Integer, Continent> p_continents) {

		GenericView.println("*****************************");
		GenericView.println("**    Continent   Table    **");
		GenericView.println("*****************************");

		if( p_continents.isEmpty())
			GenericView.println("No continent exist.");

		StringBuilder l_outStr = new StringBuilder();

		l_outStr.append(String.format("%-15s", "ContinentId"));
		l_outStr.append("ContinentsName\n");

		for (Continent _continent : p_continents.values()) {
			l_outStr.append(String.format("%-15s", _continent.getContinentID()));
			l_outStr.append(_continent.getContinentName()).append("\n");
		}

		GenericView.println(l_outStr.toString());
	}

	/**
	 * generate the table of country information
	 * continentId, countryId, countryName, neighbours
	 *
	 * @param p_countries map of country
	 */
	public static void printCountryTable(Map<Integer, Country> p_countries) {

		GenericView.println("*******************************************************");
		GenericView.println("**                 Country     Table                 **");
		GenericView.println("*******************************************************");

		if(p_countries.isEmpty())
			GenericView.println("No country exist.");

		StringBuilder l_outStr = new StringBuilder();
		l_outStr.append(String.format("%-15s", "ContinentId"));
		l_outStr.append(String.format("%-15s", "CountryId"));
		l_outStr.append(String.format("%-20s","CountryName"));
		l_outStr.append("NeighbourCountries\n");

		for( Country _country : p_countries.values()) {

			Map<Integer, Country> _mapNeighbor = _country.getNeighbors();

			StringBuilder _neighbors = new StringBuilder();
			for (Country _nCountry : _mapNeighbor.values())
				_neighbors.append(_nCountry.getCountryID()).append(" ");

			l_outStr.append(String.format("%-15s", _country.getContinent().getContinentID()));
			l_outStr.append(String.format("%-15s", _country.getCountryID()));
			l_outStr.append(_country.getCountryName());
			l_outStr.append(_neighbors.toString()).append("\n");

			GenericView.println(l_outStr.toString());
		}
	}
}
