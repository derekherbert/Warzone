package warzone.service;

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
			System.out.println(l_sb.toString());
		}
	}
}
