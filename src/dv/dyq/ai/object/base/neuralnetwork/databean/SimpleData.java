package dv.dyq.ai.object.base.neuralnetwork.databean;

import java.util.ArrayList;
import java.util.List;

public class SimpleData {
	
	List<Float> input;;

	List<Float> output;
	
	public SimpleData(List<Float> x,List<Float> y){
		input = x;
		output = y;
	}
	
	public static class Builder{
		List<Float> input = new ArrayList<Float>();

		List<Float> output = new ArrayList<Float>();
		public Builder(){
			
		}
		
		public Builder input(float x){
			input.add(x);
			return this;
		}
		public Builder output(float y){
			output.add(y);
			return this;
		}
		public SimpleData build(){
			return new SimpleData(this);
		}
	}
	private SimpleData(Builder b){
		input = b.input;
		output = b.output;
	}
	public List<Float> getInput() {
		return input;
	}
	public void setInput(List<Float> input) {
		this.input = input;
	}
	public List<Float> getOutput() {
		return output;
	}
	public void setOutput(List<Float> output) {
		this.output = output;
	}
	
	
}
