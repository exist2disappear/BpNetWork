package dv.dyq.ai.object.base.neuralnetwork.net;

import java.util.ArrayList;
import java.util.List;

import dv.dyq.ai.object.base.neuralnetwork.neural.BaseNeural;

public class NauralLayer {
	
	private List<BaseNeural> mBaseNeuralList = new ArrayList<BaseNeural>();
	
	private NauralLayer nextLayer =null;
	private NauralLayer preLayer =null;
	
	public void add(BaseNeural point){
		mBaseNeuralList.add(point);
	}
	
	public void setInput(List<Float> inputNum){
		if(inputNum == null){
			System.out.println("inputNum == null");
			return;
		}
		if(inputNum.size() != mBaseNeuralList.size()){
			System.out.println("input signal not match net size, net has "+ mBaseNeuralList.size() +" point, out input "+ inputNum.size());
			return;
		}
		for(int i = 0;i<mBaseNeuralList.size();i++){
			mBaseNeuralList.get(i).setInput(inputNum.get(i));
		}
		
	}
	
	public void docal(){
		for(BaseNeural item : mBaseNeuralList){
			item.calFore();
		}
		if(nextLayer != null){
			nextLayer.docal();
		}
	}
	
	public void setNextLayer(NauralLayer layer){
		nextLayer = layer;
		layer.setPreLayer(this);
		
	}
	
	public void connectPoint(){
		if(nextLayer != null){
			List<BaseNeural> nextLayerList = nextLayer.getmBaseNeuralList();
			for(BaseNeural thisPoint:mBaseNeuralList){
				thisPoint.setOutputPoint(nextLayerList);
			}
		}
		if(preLayer != null){
			List<BaseNeural> preLayerList = preLayer.getmBaseNeuralList();
			for(BaseNeural thisPoint:mBaseNeuralList){
				thisPoint.getInputPoint();
				for(BaseNeural prePoint:preLayerList){
					thisPoint.addInput(prePoint);
				}
				
			}
		}
	}

	public NauralLayer getPreLayer() {
		return preLayer;
	}

	public void setPreLayer(NauralLayer preLayer) {
		this.preLayer = preLayer;
	}

	public NauralLayer getNextLayer() {
		return nextLayer;
	}

	public List<BaseNeural> getmBaseNeuralList() {
		return mBaseNeuralList;
	}

	public void setmBaseNeuralList(List<BaseNeural> mBaseNeuralList) {
		this.mBaseNeuralList = mBaseNeuralList;
	}

	public float errBackout(List<Float> output, float studyRate) {
		float totalerr = 0;
		if(mBaseNeuralList.size() != output.size()){
			System.out.println("err,wrong size of out put");
			return 0;
		}
		for(int i = 0;i < mBaseNeuralList.size();i++){
			float realout = mBaseNeuralList.get(i).getOutput();
			float thinkout = output.get(i);
			totalerr +=(thinkout - realout);
			System.out.println("real value :"+ realout+"; thinkout :"+thinkout+";err is"+(thinkout - realout));
			float grad = realout * (1-realout)*(thinkout-realout);
			mBaseNeuralList.get(i).changeNeural(grad, studyRate);
		}
		
		if(preLayer != null){
			preLayer.errBack(studyRate);
		}
		return totalerr;
	}
	
	public void errBack(float studyRate) {
		
		for(int i = 0;i < mBaseNeuralList.size();i++){
			mBaseNeuralList.get(i).changeNeural(i,studyRate);
		}
		
		if(preLayer != null){
			preLayer.errBack(studyRate);
		}
		
	}
	
	

}
