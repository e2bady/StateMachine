package test;


import java.util.HashMap;
import java.util.Map;

import StateMachine.Call;
import StateMachine.Command;
import StateMachine.State;
import StateMachine.StateException;
import StateMachine.StateMachine;
/**
 * This test defines a <a href="http://en.wikipedia.org/wiki/Deterministic_finite_automaton">DFA</a>. 
 * M = (Q,E,d,q0,F)<br>
 * Q = {notready,ready,waiting,called,Exception}<br>
 * E = {Add,Peek,Call,isEmpty,isNOTempty}<br>
 * q0 = notready<br>
 * F = {notready}<br>
 * d is defined as follows<br>
 * <table border="1">
 * <tr><td></td><td>Add</td><td>Peek</td><td>Call</td><td>isEmpty</td><td>isNOTempty</td></tr>
 * <tr><td>notready</td><td>ready</td><td>Exception</td><td>Exception</td><td>Exception</td><td>Exception</td></tr>
 * <tr><td>ready</td><td>ready</td><td>waiting</td><td>called</td><td>Exception</td><td>Exception</td></tr>
 * <tr><td>waiting</td><td>waiting</td><td>Exception</td><td>called</td><td>Exception</td><td>Exception</td></tr>
 * <tr><td>called</td><td>Exception</td><td>Exception</td><td>Exception</td><td>ready</td><td>notready</td></tr>
 * </table>
 * @author Sebastian van Wickern
 *
 */
public class threadableMultiQueueStateMachine extends StateMachine {
	private Command Add = new Command("Add");
	private Command Peek = new Command("Peek");
	private Command Call = new Command("Call");
	private Command Isempty = new Command("Isempty");
	private Command IsNOTempty = new Command("IsNOTempty");
	
	private State notready = new State (0, "notready");
	private State ready = new State (1, "ready");
	private State waiting = new State (2, "waiting");
	private State called = new State (3,"called");
	
	public threadableMultiQueueStateMachine(Call onReady) {
		super();
		
		Map<Command, State> notreadyneighbors = new HashMap<Command,State>();
		notreadyneighbors.put(Add, ready);
		notready.setNeigbors(notreadyneighbors);
		
		Map<Command, State> readyneighbors = new HashMap<Command,State>();
		readyneighbors.put(Add, ready);
		readyneighbors.put(Peek, waiting);
		readyneighbors.put(Call, called);
		ready.setNeigbors(readyneighbors);
		
		ready.addCallonEnter(onReady);
		
		Map<Command, State> waitingneighbors = new HashMap<Command,State>();
		waitingneighbors.put(Add, waiting);
		waitingneighbors.put(Call, called);
		waiting.setNeigbors(waitingneighbors);
		
		Map<Command, State> calledneighbors = new HashMap<Command,State>();
		calledneighbors.put(IsNOTempty, ready);
		calledneighbors.put(Isempty, notready);
		called.setNeigbors(calledneighbors);
		
		super.setInitialState(notready);
	}
	
	public void Add() throws StateException {
		super.execute(Add);
	}
	public void Peek() throws StateException {
		super.execute(Peek);
	}
	public void Call() throws StateException {
		super.execute(Call);
	}
	public void IsNOTempty() throws StateException {
		super.execute(IsNOTempty);
	}
	public void Isempty() throws StateException {
		super.execute(Isempty);
	}
	public boolean isReady() {
		return super.getState().equals(ready);
	}
}
