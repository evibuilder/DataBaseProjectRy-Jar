package phase2;

public class Pair {
	
	private int first;
	private int second; 
	
	public Pair(){
		first = 0;
		second = 0;
	}
	
	public Pair(int first, int second)
	{
		this.first = first;
		this.second = second;
	}
	
	public void setFirst(int first){
		this.first = first;
	}
	
	public void setSecond(int second){
		this.second = second; 
	}
	
	
	public int first(){
		return first;
	}
	
	public int second(){
		return second;
	}
	
}
