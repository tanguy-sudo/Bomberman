package Models;

import java.util.ArrayList;

import Models.agent.Agent;

public class AgentIterator implements MyIterator{
	private ArrayList<Agent> pListBomberman;
	private int position;
	
	public AgentIterator(ArrayList<Agent> listBomberman) {
		this.pListBomberman = listBomberman;
		this.position = 0;
	}

	@Override
	public boolean hasNext() {
        if (position >= this.pListBomberman.size() || this.pListBomberman.get(position) == null || this.pListBomberman.get(position).getLiving() == false) return false;
        else return true;
	}

	@Override
	public Object next() {
		Agent agent = this.pListBomberman.get(position);
        position = position + 1;
		return agent;
	}

}
