package StateMachine;

/**
 * Implements a <a href="http://en.wikipedia.org/wiki/Deterministic_finite_automaton">DFA</a>, where the edges are {@link Command}s and the vertices are {@link State}s.
 * Each State and Command can be equipped with a call, that is triggered when the state is entered or the command is executed. Edges that lead to an Error-State, can be left out,
 * to raise a {@link StateException}.
 * 
 * 
 * @author Sebastian van Wickern
 * 
 *
 */

public abstract class StateMachine {
	private State state;
	
	/**
	 * 
	 * @param initial State to start with.
	 */
	public StateMachine(State initial) {
		this.setInitialState(initial);
	}
	public StateMachine() {}
	/**
	 * This method sets the state to initial, but it does this only once, to insure that the StateMachine cannot be reset, or changed within runtime (which would potentially lead to an undefined state).
	 * @param initial State to start with. 
	 */
	synchronized final public void setInitialState(State initial) {
		if(this.state == null) {
			this.state = initial;
		}
	}
	/**
	 * Executes a {@link Command} c on the DFA, meaning that it tries to go from {@link #state} with c to another {@link State}, if that is not possible (because {@link #state} has no edge for the {@link Command} c) it throws a {@link StateException}.
	 * @param c the {@link Command} to execute on the DFA.
	 * @throws StateException if there is no edge c from {@link #state}.
	 */
	synchronized public void execute(Command c) throws StateException {
		State dest = this.state.execute(c);
		if(dest != null) {
			c.call(); // Call CommandInProgress.
			this.state.callOnExit(); //Call OnStateExit
			this.state = dest;
			this.state.callOnEnter(); // Call OnStateEnter
		}
		else
			throw new StateException(this.state, c);
	}
	/**
	 * returns a copy of the state the DFA is at right now.
	 * @return a copy of the state the DFA is at right now.
	 */
	public State getState() {
		return this.state.clone();
	}

	@Override
	public String toString() {
		return "State: " + state;
	}
}
