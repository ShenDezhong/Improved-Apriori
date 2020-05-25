
//This class is used for getting itemsets and their counts

import java.util.*;

public class getitemset {
	int numofd;
	ArrayList<ArrayList<String>> prev;
	ArrayList<ArrayList<String>> newone =new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<Integer>> prevnum;
	ArrayList<ArrayList<Integer>> newnum = new ArrayList<ArrayList<Integer>>();
	double s;
	int total;
	
	//constructor
	public getitemset(int numofd, ArrayList<ArrayList<String>> prev, ArrayList<ArrayList<Integer>> prevnum, double s,int total) {
		this.numofd = numofd;
		this.prev = prev;
		this.prevnum = prevnum;
		this.s = s;
		this.total = total;
	}
	
	//find method. This method finds the new itemset and the count
	public void find() {
		if(numofd==2) {
			for(int i=0;i<prev.size();i++) {
				for(int j=i+1;j<prev.size();j++) {
					ArrayList<String> a = new ArrayList<String>();//arraylist a which holds the new items combo
					ArrayList<Integer> b = new ArrayList<Integer>();//arraylist b which holds the record
					
					a.add(prev.get(i).get(0));
					a.add(prev.get(j).get(0));

					for(int x=0;x<prevnum.get(i).size();x++) {
						int checknum = prevnum.get(i).get(x);
						if(prevnum.get(j).contains(checknum)) {
							b.add(checknum);
						}
					}
					
					if((double)b.size()/total>=s) {
						if(a!=null) newone.add(a);
						if(b!=null) newnum.add(b);
						
					}
				}
			}
		}
		else if(numofd>2) {
			for(int i=0;i<prev.size();i++) {
				for(int j=i+1;j<prev.size();j++) {
					ArrayList<String> a = new ArrayList<String>();//arraylist a which holds the new items combo
					ArrayList<Integer> b = new ArrayList<Integer>();//arraylist b which holds the record
					
					ArrayList<String> p1 = new ArrayList<String>();
					p1 = prev.get(i);
					ArrayList<String> p2 = new ArrayList<String>();
					p2 = prev.get(j);
					int check = 0;
					String diff = "";
					for(int x = 0;x<p1.size();x++) {
						if(p2.contains(p1.get(x))==false) {
							diff = p1.get(x);
							check = check+1;
						}
					}
					if(check==1) {
						a.add(diff);
						for(int y=0;y<p2.size();y++) {
							a.add(p2.get(y));
						}
						//check if the newone arraylist already has the new itemset
						boolean ch1=false;
						for(int m=0;m<newone.size();m++) {
							if(newone.get(m).containsAll(a)) {
								ch1=true;
								break;
							}
						}
						if(ch1==false) {
							for(int z=0;z<prevnum.get(i).size();z++) {
								int checknum = prevnum.get(i).get(z);
								if(prevnum.get(j).contains(checknum)) {
									b.add(checknum);
								}
							}
							if((double)b.size()/total>=s) {
								newone.add(a);
								newnum.add(b); 
							}
						}
					}
					
				}
			}
		}
		else {}
	}
	//getset method. This method returns the new itemset
	public ArrayList<ArrayList<String>> getset(){
		return newone;
	}
	//getnum method. This method returns the new count for the new itemset
	public ArrayList<ArrayList<Integer>> getnum(){
		return newnum;
	}
	
	//store all the results into a string
	public String toString() {
		String re = "";
		for(int i=0;i<newone.size();i++) {
			for(int j=0;j<newone.get(i).size();j++) {
				re = re+newone.get(i).get(j)+" ";
			}
			re = re+"= "+(double)newnum.get(i).size()/total+"\n";
		}
		return re;
	}

}
