import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Apriori2<I> {

	private List<Set<I>> transactions; //All transactions
	//think about this one..
	private Map<List<Set<I>>, Integer> frequentSets; //frequent sets and sup-count
	private Map<Integer, List<Set<I>>> kLevelSets; //frequent sets on lvl k (key)
	private List<Set<I>> candidates; //cands on current lvl
	private List<Set<I>> currentFreqSets;
	private int supportCount; //the support count to be reached
	
	public Apriori2(){
		//create the transaction list
	}
	
	private Map<Integer, List<Set<I>>> apriori(){
		//extract lvl1 cands
		//lvl1 since we look at each item in each trans
		for(Set<I> trans : transactions){
			for(I item : trans){
				Set<I> newSet = new HashSet<I>();
				newSet.add(item);
				//if item is not already a candidate, add it
				if(!candidates.contains(newSet)){
					candidates.add(newSet);
				}
			}
		}
		
		//filter lvl1 cands for frequent sets
		extractFrequentSets();
		
		return null;
	}
	
	private void extractFrequentSets() {
		for(Set<I> set : candidates){
			int count = count(set);
			if(count >= supportCount){
				currentFreqSets.add(set);
			}
		}
	}

	
	private int count(Set<I> set) {
		return 0;
	}

	private List<Set<I>> candidateGen(int k){
		return null;
	}
}
