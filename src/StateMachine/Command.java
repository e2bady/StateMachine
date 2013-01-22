package StateMachine;

import java.rmi.RemoteException;

/**
 * A {@link Command} is used to define the edges between the {@link State}s. It holds a {@link Call}-Object {@link #CallOnProcess} that is called by {@link #call()}.
 * @author XTFIWS
 *
 */
public class Command {
	String name;
	Call CallOnProcess = null;
	
	/**
	 * @param name The name of the command.
	 * @param c The {@link Call} it should execute.
	 */
	public Command(String name, Call c) {
		super();
		this.name = name;
		this.CallOnProcess = c;
	}
	/**
	 * @param name The name of the command.
	 */
	public Command(String name) {
		super();
		this.name = name;
	}
	/**
	 * Adds or changes the referenced object which is called by {@link #call()}
	 * @param c
	 */
	public void addCallOnProcess(Call c) {
		this.CallOnProcess = c;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	/**
	 * Two Calls are equal if their names ({@link #name}) are equal.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Command other = (Command) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	/**
	 * If not null {@link #CallOnProcess} is called.
	 */
	public void call() {
		if(this.CallOnProcess != null)
			try {
				this.CallOnProcess.call();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	@Override
	public String toString()
	{
		return this.name;
	}
}
