package StateMachine;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Each {@link Command} and {@link State} has a Call Object, which is called on entry for {@link State}s and on process for {@link Command}s, it is
 * able to inform an Object about the changes within the {@link StateMachine}
 * @author Sebastian van Wickern
 *
 */
public interface Call extends Remote{
	void call() throws RemoteException;
}
