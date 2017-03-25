package phase2;

public class Tuple {
	
	private String first;
	private int second; 
	private int third;
	
	public Tuple(){
		first = "";
		second = 0;
		third = 0;
	}
	
	public Tuple(String first, int second, int third)
	{
		this.first = first;
		this.second = second;
		this.third = third;
	}
	
	public void setFirst(String first){
		this.first = first;
	}
	
	public void setSecond(int second){
		this.second = second; 
	}
	
	public void setThird(int third){
		this.third = third;
	}
	
	public String first(){
		return first;
	}
	
	public int second(){
		return second;
	}
	
	public int third(){
		return third;
	}
}
