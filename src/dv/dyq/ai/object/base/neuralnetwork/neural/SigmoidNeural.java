package dv.dyq.ai.object.base.neuralnetwork.neural;

public class SigmoidNeural extends BaseNeural {
	
	@Override
	protected float selfFunc(float in) {
		double tmp1 =  Math.exp(in-getThesHold());
		float out = (float)(1f/(1+1/tmp1));
		return out;
	}
	
	

}
