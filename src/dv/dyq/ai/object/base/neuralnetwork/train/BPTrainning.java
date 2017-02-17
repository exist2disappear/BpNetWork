package dv.dyq.ai.object.base.neuralnetwork.train;

import java.util.List;

import dv.dyq.ai.object.base.neuralnetwork.databean.SimpleData;
import dv.dyq.ai.object.base.neuralnetwork.net.BaseNet;
import dv.dyq.ai.object.base.neuralnetwork.net.NauralLayer;

public class BPTrainning {
	
	private float studyRate;
	private float stopErr;
	
	private BaseNet mBaseNet;
	private List<SimpleData> simpleData;
	
	
	public static class Builder{
		private float studyRate;
		private float stopErr;
		
		private BaseNet mBaseNet;
		private List<SimpleData> simpleData;
		public Builder studyRate(float rate){
			studyRate = rate;
			return this;
		}
		public Builder stopErr(float err){
			stopErr = err;
			return this;
		}
		public Builder net(BaseNet net){
			mBaseNet = net;
			return this;
		}
		public Builder simple(List<SimpleData> simple){
			simpleData = simple;
			return this;
		}
		public BPTrainning build(){
			return new BPTrainning(this);
		}
	}
	private BPTrainning(Builder b){
		studyRate = b.studyRate;
		stopErr = b.stopErr;
		simpleData = b.simpleData;
		mBaseNet = b.mBaseNet;
	}
	
	public void startTrain(){
		float err = 0;
		for(int i =0;i<100;i++){
			err = 0;
			for(SimpleData data : simpleData){			
				err += trainSimple(data);
			}
			System.out.println("********************************************total simple is "+err);
		}
//		List<Float> input = Arrays.asList(-2f,-5f);
//		bp.input(input);
//		bp.mFirstLayer.docal();
	}
	
	private float trainSimple(SimpleData simple){
		System.out.println("====calulate all net=====");
		mBaseNet.input(simple.getInput());
		mBaseNet.mFirstLayer.docal();
		System.out.println("====start net BP study=====");
		List<NauralLayer> layerList = mBaseNet.mNauralLayers;
		NauralLayer layer = layerList.get(layerList.size()-1);
		return layer.errBackout(simple.getOutput(),studyRate);
	}
	
	

}
