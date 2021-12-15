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
		// on regarde si l'agent existe;
        if (position >= this.pListBomberman.size() || this.pListBomberman.get(position) == null) return false;
        // on regarde si l'agent est vivant
        else if (this.pListBomberman.get(position).getLiving()) return true;
        else {
        	// si l'agent n'est pas vivant on regarde l'élement suivant
        	while(position < this.pListBomberman.size()) {
        		position = position + 1;
        		if(position >= this.pListBomberman.size() || this.pListBomberman.get(position) == null) return false;
        		else if(this.pListBomberman.get(position).getLiving()) return true; 
        	}
        }
		return false;
	}

	@Override
	public Object next() {
		Agent agent = this.pListBomberman.get(position);
        position = position + 1;
		return agent;
	}

}
