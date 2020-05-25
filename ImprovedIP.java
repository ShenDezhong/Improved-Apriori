
import java.util.*;
import java.io.*;


public class ImprovedIP {

	public static void main(String[] args) {
		
		File newfile = new File("apriori_data.csv");
		ArrayList<ArrayList<String>> dataarray = new ArrayList<ArrayList<String>>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(newfile));
			br.readLine();//read the first line
			
			String line=null;
			while((line=br.readLine())!=null) {
				String[] item=line.split(",");
				ArrayList<String> linedata = new ArrayList<String>();
				for(int i=0;i<item.length;i++) {
					linedata.add(item[i]);
				}
				dataarray.add(linedata);
			}
			br.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		//ask the user to enter the min_sup and the min_confidence
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to this application.");
		System.out.println("Please enter the Min-Support and the Min-Confidence.");
		System.out.print("Support: ");
		double s = in.nextDouble();
		in.nextLine();
		System.out.print("Confidence: ");
		double c = in.nextDouble();
		in.nextLine();
		
		int totalnum = dataarray.size();//the total number of the data
		//record all the items and the counts of them
		
		ArrayList<String> key = new ArrayList<String>();
		ArrayList<ArrayList<Integer>> num = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<dataarray.size();i++) {
			for(int j=0;j<dataarray.get(i).size();j++) {
				if(key.contains(dataarray.get(i).get(j))) {
					int index = key.indexOf(dataarray.get(i).get(j));
					num.get(index).add(i);
				}
				else {
					key.add(dataarray.get(i).get(j));
					ArrayList<Integer> linenum = new ArrayList<Integer>();
					linenum.add(i);
					num.add(linenum);
				}
			}
		}
		
		//set an arraylist to hold all the information about count which will be print out
		ArrayList<String> out1 = new ArrayList<String>();
		
		//Do the first check. Check if the counts of 1-item itemset are larger than the min_support.
		//Store the itemset which the count is larger than the min_support. Use for next check.
		//Set 2 arraylist to store them
		ArrayList<ArrayList<String>> a1 = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<Integer>> b1 = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<num.size();i++) {
			if((double)num.get(i).size()/totalnum>=s) {
				ArrayList<String> k = new ArrayList<String>();
				k.add(key.get(i));
				a1.add(k);
				b1.add(num.get(i));
				//String line = key.get(i)+" = "+(double)num.get(i).size()/totalnum;
				//out1.add(line);
			}
		}
		String outofsup1 = "";
		for(int i=0;i<a1.size();i++) {
			for(int j=0;j<a1.get(i).size();j++) {
				outofsup1 = outofsup1+a1.get(i).get(j)+" ";
			}
			outofsup1 = outofsup1+"= "+(double)b1.get(i).size()/totalnum+"\n";
		}
		out1.add(outofsup1);
		
		//get 2-item itemsets and check if their counts are larger than the min_support
		ArrayList<ArrayList<String>> a2 = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<Integer>> b2 = new ArrayList<ArrayList<Integer>>();
		getitemset item2 = new getitemset(2,a1,b1,s,totalnum);
		item2.find();
		a2 = item2.getset();
		b2 = item2.getnum();
		String outofsup2 = item2.toString();
		out1.add(outofsup2);
		
		//get 3-item itemsets
		ArrayList<ArrayList<String>> a3 = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<Integer>> b3 = new ArrayList<ArrayList<Integer>>();
		getitemset item3 = new getitemset(3,a2,b2,s,totalnum);
		item3.find();
		a3 = item3.getset();
		b3 = item3.getnum();
		String outofsup3 = item3.toString();
		out1.add(outofsup3);
		
		//get 4-item itemsets
		ArrayList<ArrayList<String>> a4 = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<Integer>> b4 = new ArrayList<ArrayList<Integer>>();
		getitemset item4 = new getitemset(4,a3,b3,s,totalnum);
		item4.find();
		a4 = item4.getset();
		b4 = item4.getnum();
		String outofsup4 = item4.toString();
		out1.add(outofsup4);
		
		//get 5-item itemsets
		ArrayList<ArrayList<String>> a5 = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<Integer>> b5 = new ArrayList<ArrayList<Integer>>();
		getitemset item5 = new getitemset(5,a4,b4,s,totalnum);
		item5.find();
		a5 = item5.getset();
		b5 = item5.getnum();
		String outofsup5 = item5.toString();
		out1.add(outofsup5);
		
		//get 6-item itemsets
		ArrayList<ArrayList<String>> a6 = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<Integer>> b6 = new ArrayList<ArrayList<Integer>>();
		getitemset item6 = new getitemset(6,a5,b5,s,totalnum);
		item6.find();
		a6 = item6.getset();
		b6 = item6.getnum();
		String outofsup6 = item6.toString();
		out1.add(outofsup6);
		
		//get 7-item itemsets
		ArrayList<ArrayList<String>> a7 = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<Integer>> b7 = new ArrayList<ArrayList<Integer>>();
		getitemset item7 = new getitemset(7,a6,b6,s,totalnum);
		item7.find();
		a7 = item7.getset();
		b7 = item7.getnum();
		String outofsup7 = item7.toString();
		out1.add(outofsup7);
		
		//set string to store all the outputs for rules
		String out2 = "";
		ArrayList<ArrayList<ArrayList<String>>> A = new ArrayList<ArrayList<ArrayList<String>>>();
		ArrayList<ArrayList<ArrayList<Integer>>> B = new ArrayList<ArrayList<ArrayList<Integer>>>();
		A.add(a1);
		A.add(a2);
		A.add(a3);
		A.add(a4);
		A.add(a5);
		A.add(a6);
		A.add(a7);
		B.add(b1);
		B.add(b2);
		B.add(b3);
		B.add(b4);
		B.add(b5);
		B.add(b6);
		B.add(b7);
		out2 = getrules(A,B,c);
		
		
		//set a string to be the output
		String out = "";
		out = out+"\nMin-support: "+s+"\n"+"Min-confidence: "+c+"\n\n";
		for(int i=0;i<out1.size();i++) {
			out = out+out1.get(i);
		}
		out=out+"\n\n"+"Rules:\n\n"+out2;
		
		//print out the result
		//put the result into a file
		try {
			File fileout = new File("Result.txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileout,true));
			bw.write(out);
			bw.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		in.close();
	}
	
	//set a method to get all available rules and their confidences
	public static String getrules(ArrayList<ArrayList<ArrayList<String>>> A, ArrayList<ArrayList<ArrayList<Integer>>> B, double c) {
		String re = "";
		for(int i=1;i<A.size();i++) {
			ArrayList<ArrayList<String>> arr1 = new ArrayList<ArrayList<String>>();
			arr1 = A.get(i);
			for(int j=0;j<arr1.size();j++) {
				ArrayList<String> a = arr1.get(j);
				for(int m=0;m<i;m++) {
					ArrayList<ArrayList<String>> ch1 = A.get(m);
					for(int n=0;n<ch1.size();n++) {
						if(a.containsAll(ch1.get(n))) {
							double con = (double)B.get(i).get(j).size()/B.get(m).get(n).size();
							if(con>=c) {
								ArrayList<String> backup = new ArrayList<String>();
								for(int x=0;x<a.size();x++) {
									backup.add(a.get(x));
								}
								backup.removeAll(ch1.get(n));
								for(int y=0;y<ch1.get(n).size();y++) {
									re = re+ch1.get(n).get(y)+" ";
								}
								re = re+"=> ";
								for(int z=0;z<backup.size();z++) {
									re = re+backup.get(z)+" ";
								}
								re = re+"\nConfidence: "+con+"\n";
							}
						}
					}
				}
			}
		}
		return re;
	}
	
}
