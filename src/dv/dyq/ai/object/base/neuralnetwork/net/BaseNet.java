package dv.dyq.ai.object.base.neuralnetwork.net;

import java.util.ArrayList;
import java.util.List;

public class BaseNet {
	
	public List<NauralLayer> mNauralLayers = new ArrayList<NauralLayer>() ;
	
	
	public NauralLayer mFirstLayer = null;
	private NauralLayer mPreLayer = null;
	
	
	
	
	
	public void addNextLayer(NauralLayer layer){
		mNauralLayers.add(layer);
		if(mFirstLayer == null){			
			mFirstLayer = layer;
		}
		if(mPreLayer != null){
			mPreLayer.setNextLayer(layer);
			
		}
		mPreLayer = layer;
	}
	
	public void connect(){
		for (NauralLayer layer: mNauralLayers){
			layer.connectPoint();
		}
	}
	public void input(List<Float> inputNum){
		mFirstLayer.setInput(inputNum);
	}
	

}
