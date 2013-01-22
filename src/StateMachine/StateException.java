package StateMachine;
/**
 * StateException is raised by {@link StateMachine} whenever there is no edge {@link #c} from {@link #oldState}
 * @author Sebastian van Wickern
 *
 */
public class StateException extends Exception {
	private static final long serialVersionUID = 7657981460391491572L;

	private State oldState;
	private Command c;
	public StateException(State oldState, Command c) {
		super();
		this.oldState = oldState;
		this.c = c;
	}
	@Override
	public
	String toString() {
		return "State change not possible: " + oldState + " with " + c;
	}
}
