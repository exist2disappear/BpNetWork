package dv.dyq.ai.object.base.example;

import java.util.ArrayList;
import java.util.List;

import dv.dyq.ai.object.base.neuralnetwork.NetWorkManager;
import dv.dyq.ai.object.base.neuralnetwork.databean.SimpleData;
import dv.dyq.ai.object.base.neuralnetwork.net.BaseNet;
import dv.dyq.ai.object.base.neuralnetwork.neural.BaseNeural;
import dv.dyq.ai.object.base.neuralnetwork.neural.SigmoidNeural;
import dv.dyq.ai.object.base.neuralnetwork.train.BPTrainning;

public class ExclusiveOrExample {
	static List<SimpleData> simple;
	static BPTrainning testTrain;

	public static void main(String[] args) {
		System.out.println("Hello ,I'm dyq's AI Object");
		initDate();
		initNet();
		testTrain.startTrain();
//		List<Float> input = Arrays.asList(-2f,-5f);
//		bp.input(input);
//		bp.mFirstLayer.docal();

	}
	
	private static void initDate() {
		simple = new ArrayList<SimpleData>();
		simple.add(new SimpleData.Builder().input(0).input(0).output(1).build());
		simple.add(new SimpleData.Builder().input(1).input(0).output(0).build());
		simple.add(new SimpleData.Builder().input(0).input(1).output(0).build());
		simple.add(new SimpleData.Builder().input(1).input(1).output(1).build());
	}
	
	private static void initNet() {
		NetWorkManager nm = new NetWorkManager();
		BaseNet bp = nm.getBPNet(2, 3, 1);
		testTrain = new BPTrainning.Builder().net(bp).simple(simple).studyRate(0.3f).build();
	}

	private static void single(){
//		BaseNeural firstPoit = new SigNeural();
		BaseNeural firstPoit = new SigmoidNeural();
		firstPoit.setThesHold(3.14f);
		float x = -20f;
		firstPoit.setInput(x);
		float y = firstPoit.calFore();
		System.out.println("input :"+x+"; output :"+y);
	}

}
