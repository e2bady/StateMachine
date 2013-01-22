package StateMachine;

import java.rmi.RemoteException;
import java.util.Map;

/**
 * A state consists of a status, a message, a callable {@link Call}-Object which is called on entry and a Map mapping other States to {@link Command}s. 
 * @author XTFIWS
 *
 */
public class State implements Comparable<State>, Cloneable{ 
	private int status;
	private String Msg;
	private Map<Command,State> neighbors;
	private Call callOnEnter;
	private Call callOnExit;
	
	/**
	 * Status and Message must be a primary key. The status is just a way of being able to have the same message, but define two State's as different.
	 * @param Status
	 * @param Msg
	 */
	public State(int Status, String Msg) {
		this.status = Status;
		this.Msg = Msg;
	}
	/**
	 * c is called on entry.
	 * @see #State(int, String)
	 */
	public State(int Status, String Msg, Call c) {
		this.status = Status;
		this.Msg = Msg;
		this.callOnEnter = c;
	}
	/**
	 * Adds/Replaces the {@link Call}-Object, which is called on Entry.
	 * @param callOnEnter
	 */
	public void addCallonEnter(Call callOnEnter) {
		this.callOnEnter = callOnEnter;
	}
	/**
	 * If not null, {@link #callOnEnter} is called.
	 */
	public void callOnEnter() {
		if(callOnEnter != null)
			try {
				this.callOnEnter.call();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	/**
	 * Adds/Replaces the {@link Call}-Object, which is called on Entry.
	 * @param callOnEnter
	 */
	public void addCallonExit(Call callOnEnter) {
		this.callOnExit = callOnEnter;
	}
	/**
	 * If not null, {@link #callOnEnter} is called.
	 */
	public void callOnExit() {
		if(callOnExit != null)
			try {
				this.callOnExit.call();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	/**
	 * Sets the neighboring States.
	 * @param neighbors a Map mapping {@link Command}s to {@link State}s
	 */
	public void setNeigbors(Map<Command,State> neighbors) {
		this.neighbors = neighbors;
	}
	/**
	 * @return the status of the State.
	 */
	int getStatus() {return this.status;}
	/**
	 * @return {@link #Msg}, as given by {@link #State(int, String)} or {@link #State(int, String, Call)}.
	 */
	public String toString() {
		return this.Msg;
	}
	
	/**
	 * Checks if the {@link Command} c is a key in {@link #neighbors}, if it is, the State it refers to is returned.
	 * @param c a {@link Command} defining an edge.
	 * @return the State refered by {@link Command} c
	 */
	State execute(Command c) {
		if (neighbors.containsKey(c)) {
			return neighbors.get(c);
		}
		return null;
	}

	@Override
	public int compareTo(State arg0) {
		return this.getStatus() - arg0.getStatus();
	}
	@Override
	public State clone() {
		return new State(this.status,this.Msg);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Msg == null) ? 0 : Msg.hashCode());
		result = prime * result + status;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (Msg == null) {
			if (other.Msg != null)
				return false;
		} else if (!Msg.equals(other.Msg))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	
}
