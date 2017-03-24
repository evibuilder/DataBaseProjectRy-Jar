package phase2;

public class Tuple {
	
	private int first;
	private int second; 
	private int third;
	
	public Tuple(){
		first = 0;
		second = 0;
		third = 0;
	}
	
	public Tuple(int first, int second, int third)
	{
		this.first = first;
		this.second = second;
		this.third = third;
	}
	
	public void setFirst(int first){
		this.first = first;
	}
	
	public void setSecond(int second){
		this.second = second; 
	}
	
	public void setThird(int third){
		this.third = third;
	}
	
	public int first(){
		return first;
	}
	
	public int second(){
		return second;
	}
	
	public int third(){
		return third;
	}
}
