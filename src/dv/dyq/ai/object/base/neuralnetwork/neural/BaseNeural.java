package dv.dyq.ai.object.base.neuralnetwork.neural;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseNeural {
	
	private List<InputNeural> inputPoint = new ArrayList<InputNeural>();
	private List<BaseNeural> outputPoint = new ArrayList<BaseNeural>();
	
	private float thesHold=0;
	private float input = 0;
	private float output = 0;
	private int poitState = 0;
	private float grad = 0;
	
	//public
	public void addInput(BaseNeural point,float weight){
		inputPoint.add(new InputNeural(point,weight));
	}
	
	public void addInput(BaseNeural point){
		inputPoint.add(new InputNeural(point,1));
	}
	
	public void addOutput(BaseNeural point){
		outputPoint.add(point);
	}
	
	public void calBack(){//返传计算，得知输入后，反向递归获得结果
		return ;
	}
	
	public float calFore(){//得知输入后，逐层推进
		float total = input;
		for(InputNeural item : inputPoint){
			total += item.mWeight*item.mPoint.getOutput();
		}
		output = selfFunc(total);
//		System.out.println("this net get res : "+output );
		return output;
	}
	
	public void changeNeural(float errGrad, float studyRate){
		grad = errGrad;
		thesHold = thesHold - grad*studyRate;
		if(inputPoint != null && inputPoint.size() != 0){			
			for(InputNeural item :inputPoint){
				item.changeWeight(grad,studyRate);
			}
		}
	}
	
	public void changeNeural(int index,float studyRate){
		if(outputPoint != null){
			float nextTotalGrad = 0;
			for(BaseNeural item : outputPoint){
				nextTotalGrad += item.getGradWithWeight(index);
			}
			grad = output*(1-output)* nextTotalGrad;
			changeNeural(grad, studyRate);
		}
	}
	
	public float getGradWithWeight(int index){
		return grad*inputPoint.get(index).mWeight;//输入是上一层的第几个
	}
	//private
	protected abstract float selfFunc(float in);
	//get set
	public float getOutput() {
		return output;
	}

	public void setOutput(float output) {
		this.output = output;
	}

	public int getPoitState() {
		return poitState;
	}

	public void setPoitState(int poitState) {
		this.poitState = poitState;
	}
	
	

	public float getThesHold() {
		return thesHold;
	}

	public void setThesHold(float thesHold) {
		this.thesHold = thesHold;
	}

	public float getInput() {
		return input;
	}

	public void setInput(float input) {
		this.input = input;
	}

	public float getGrad() {
		return grad;
	}

	public void setGrad(float grad) {
		this.grad = grad;
	}

	public List<InputNeural> getInputPoint() {
		return inputPoint;
	}

	public void setInputPoint(List<InputNeural> inputPoint) {
		this.inputPoint = inputPoint;
	}

	public List<BaseNeural> getOutputPoint() {
		return outputPoint;
	}

	public void setOutputPoint(List<BaseNeural> outputPoint) {
		this.outputPoint = outputPoint;
	}

	//inner class
	class InputNeural{
		public BaseNeural mPoint;
		public float mWeight;
		public InputNeural(BaseNeural point,float weight){
			mPoint = point;
			mWeight = weight;
		}
		public void changeWeight(float errGrad, float studyRate) {
			if(mPoint != null){
				mWeight = mWeight + errGrad*studyRate*mPoint.getOutput();
			}
		}
	}

}
