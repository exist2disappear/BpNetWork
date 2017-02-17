package dv.dyq.ai.object.base.neuralnetwork;

import dv.dyq.ai.object.base.neuralnetwork.net.BaseNet;
import dv.dyq.ai.object.base.neuralnetwork.net.NauralLayer;
import dv.dyq.ai.object.base.neuralnetwork.neural.LinearNeural;
import dv.dyq.ai.object.base.neuralnetwork.neural.SigNeural;
import dv.dyq.ai.object.base.neuralnetwork.neural.SigmoidNeural;

public class NetWorkManager {
	
	public BaseNet getBPNet(int inLayer,int hideLayer,int outLayer){
		BaseNet bp = new BaseNet();
		NauralLayer in = new  NauralLayer();
		for(int i = 0;i<inLayer;i++){			
			in.add(new LinearNeural());
		}
		
		NauralLayer hide = new  NauralLayer();
		for(int i = 0;i<hideLayer;i++){			
			hide.add(new SigmoidNeural());
		}
		NauralLayer out = new  NauralLayer();
		for(int i = 0;i<outLayer;i++){			
			out.add(new SigmoidNeural());
		}
		bp.addNextLayer(in);
		bp.addNextLayer(hide);
		bp.addNextLayer(out);
		bp.connect();
		return bp;
	}

}
