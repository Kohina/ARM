import java.util.ArrayList;

public class Apriori {

	private ArrayList<ArrayList<Integer>> trans = new ArrayList<>();
	private int minsup = 3;
	ArrayList<ArrayList<Integer>> cands = new ArrayList<>();
	
	public Apriori(){
		ArrayList<Integer> trans1 = new ArrayList<>();
		ArrayList<Integer> trans2 = new ArrayList<>();
		ArrayList<Integer> trans3 = new ArrayList<>();
		ArrayList<Integer> trans4 = new ArrayList<>();
		ArrayList<Integer> trans5 = new ArrayList<>();
		ArrayList<Integer> trans6 = new ArrayList<>();
		
		//A=1, B=2, C=3, D=4, E=5, F=6, G=7
		trans1.add(1);
		trans1.add(3);
		trans1.add(4);
		trans1.add(6);
		trans1.add(7);
		
		trans2.add(1);
		trans2.add(2);
		trans2.add(3);
		trans2.add(4);
		trans2.add(6);
		
		trans3.add(3);
		trans3.add(4);
		trans3.add(5);
		
		trans4.add(1);
		trans4.add(4);
		trans4.add(6);
		
		trans5.add(1);
		trans5.add(3);
		trans5.add(4);
		trans5.add(5);
		trans5.add(6);
		
		trans6.add(2);
		trans6.add(3);
		trans6.add(4);
		trans6.add(5);
		trans6.add(6);
		trans6.add(7);
		
		trans.add(trans1);
		trans.add(trans2);
		trans.add(trans3);
		trans.add(trans4);
		trans.add(trans5);
		trans.add(trans6);
		ArrayList<ArrayList<Integer>> result = apriori(trans);
		
		for(ArrayList<Integer> i : result){
			System.out.println(i);
		}
	}
	
	public ArrayList<ArrayList<Integer>> apriori(ArrayList<ArrayList<Integer>> trans){
		ArrayList<Integer> c1 = unique1items(trans); //the first pass over T
		System.out.println(c1);
		ArrayList<ArrayList<Integer>> filtered = new ArrayList<>();
		
		
		//clean C1 based on minsup
		for(Integer i : c1){
			ArrayList<Integer> itemList = new ArrayList<Integer>();
			itemList.add(i);
			
			if(count(itemList, trans) >= minsup){
				filtered.add(itemList);
			}
		}
		
		System.out.println("Result after 1 iter");
		for(ArrayList<Integer> r : filtered){
			System.out.println(r);
		}
		
		//subsequent passes over T
		boolean cont = true;
		
		while(cont){
			cont = false;
			candidateGen(filtered);
			
			for(ArrayList<Integer> cand : cands){
				if(count(cand, trans) >= minsup){
					filtered.add(cand);
					cont = true;
				}
			}
			System.out.println("Result after n iter");
			for(ArrayList<Integer> r : filtered){
				System.out.println(r);
			}
		}
		return filtered;
	}
	
	//TODO: Join step is incorrect, fix
	private void candidateGen(ArrayList<ArrayList<Integer>> filtered) {
		cands.clear(); //remove candidates from step k-1
		boolean add = true;
		
		for(int k=0; k < filtered.size()-1; k++){
			ArrayList<Integer> trans1 = filtered.get(k);
			ArrayList<Integer> trans2 = filtered.get(k+1);
			
			System.out.println("trans1: " + trans1);
			System.out.println("trans2: " + trans2);
			
			//differ only on last elem? then join
			if(trans1.subList(0, trans1.size()-1) == trans2.subList(0, trans2.size()-1)){
				trans1.add(trans2.get(trans2.size()));
				
				//check that all subsets are in filtered
				for(int j=0; j<=trans1.size(); j++){
					ArrayList<Integer> checkList = trans1;
					checkList.remove(j);
					
					if(!filtered.contains(checkList)){
						add = false;
						break;
					}
				}
				if(add){
					cands.add(trans1);
				}
			}
		}
	}

	private int count(ArrayList<Integer> i, ArrayList<ArrayList<Integer>> t) {
		int count = 0;
		boolean contains;
		
		for(ArrayList<Integer> x : t){
			contains = true;
			for(Integer y : i){
				if(!x.contains(y)){
					contains = false;
				}
			}
			if(contains){
				count++;
			}
		}
		return count;
	}

	//TODO: should this be treated as a special case?
	private ArrayList<Integer> unique1items(ArrayList<ArrayList<Integer>> trans) {
		ArrayList<Integer> newList = new ArrayList<Integer>();
		
		for(ArrayList<Integer> t : trans){
			for(Integer item : t){
				if(!newList.contains(item)){
					newList.add(item);
				}
			}
		}
		return newList;
	}

	public static void main(String[] args){
		new Apriori();
	}
}
