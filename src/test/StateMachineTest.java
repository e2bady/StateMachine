package test;
import StateMachine.Call;
import StateMachine.State;
import StateMachine.StateException;

/**
 * Tests {@link threadableMultiQueueStateMachine}.
 * @author Sebastian van Wickern
 *
 */

public class StateMachineTest {
	public static void main(String[] args) {
		State notready = new State (0, "notready");
		State ready = new State (1, "ready");
		State waiting = new State (2, "waiting");
		State called = new State (3,"called");
		threadableMultiQueueStateMachine test = new threadableMultiQueueStateMachine(new Call() {
			@Override
			public void call() {
				System.err.println("Queue ready.");
			} });
		
		System.out.println(test);
		
		assert test.isReady() == false;
		assert test.getState().equals(notready);
		
		
		try {
			test.Peek();
		} catch (StateException e) {
			System.out.println(e.toString());
		}
		try {
			test.Call();
		} catch (StateException e) {
			System.out.println(e.toString());
		}
		try {
			test.Isempty();
		} catch (StateException e) {
			System.out.println(e.toString());
		}
		try {
			test.IsNOTempty();
		} catch (StateException e) {
			System.out.println(e.toString());
		}
		try {
			test.Add();
		} catch (StateException e) {
			System.out.println(e.toString());
		}
		
		System.out.println(test);
		
		assert test.getState().equals(ready);
		
		try {
			test.Isempty();
		} catch (StateException e) {
			System.out.println(e.toString());
		}
		try {
			test.IsNOTempty();
		} catch (StateException e) {
			System.out.println(e.toString());
		}
		
		System.out.println(test);
		
		assert test.getState().equals(ready);
		
		try {
			test.Add();
		} catch (StateException e) {
			System.out.println(e.toString());
		}
		
		System.out.println(test);
		
		assert test.getState().equals(ready);
		
		try {
			test.Peek();
		} catch (StateException e) {
			System.out.println(e.toString());
		}
		
		System.out.println(test);
		
		assert test.getState().equals(waiting);
		
		try {
			test.Peek();
		} catch (StateException e) {
			System.out.println(e.toString());
		}
		try {
			test.Isempty();
		} catch (StateException e) {
			System.out.println(e.toString());
		}
		try {
			test.IsNOTempty();
		} catch (StateException e) {
			System.out.println(e.toString());
		}
		
		System.out.println(test);
		
		assert test.getState().equals(waiting);
		
		try {
			test.Add();
		} catch (StateException e) {
			System.out.println(e.toString());
		}
		
		System.out.println(test);
		
		assert test.getState().equals(waiting);
		
		try {
			test.Call();
		} catch (StateException e) {
			System.out.println(e.toString());
		}
		
		System.out.println(test);
		
		assert test.getState().equals(called);
		
		try {
			test.Call();
		} catch (StateException e) {
			System.out.println(e.toString());
		}
		
		System.out.println(test);
		
		assert test.getState().equals(called);
		
		try {
			test.Add();
		} catch (StateException e) {
			System.out.println(e.toString());
		}
		try {
			test.Peek();
		} catch (StateException e) {
			System.out.println(e.toString());
		}
		try {
			test.Call();
		} catch (StateException e) {
			System.out.println(e.toString());
		}
		
		System.out.println(test);
		
		assert test.getState().equals(called);
		
		try {
			test.IsNOTempty();
		} catch (StateException e) {
			System.out.println(e.toString());
		}
		
		System.out.println(test);
		
		assert test.getState().equals(ready);
		
		try {
			test.Call();
		} catch (StateException e) {
			System.out.println(e.toString());
		}
		
		System.out.println(test);
		
		assert test.getState().equals(called);
		
		try {
			test.Isempty();
		} catch (StateException e) {
			System.out.println(e.toString());
		}
		
		System.out.println(test);
		
		assert test.getState().equals(notready);
		
		
		
	}

}
