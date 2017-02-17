package dv.dyq.ai.object.base.neuralnetwork.neural;

public class SigNeural extends BaseNeural {
	
	protected float selfFunc(float in){
		float out = 0;
		if(in>getThesHold()){
			out = 1f;
		}
		return out;
	}

}
