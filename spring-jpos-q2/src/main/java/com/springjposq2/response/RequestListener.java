package com.springjposq2.response;

//import org.jpos.core.Configurable;
//import org.jpos.core.Configuration;
//import org.jpos.core.ConfigurationException;
//import org.jpos.iso.ISOMsg;
//import org.jpos.iso.ISORequestListener;
//import org.jpos.iso.ISOSource;
//import org.jpos.space.Space;
//import org.jpos.space.SpaceFactory;
//import org.jpos.transaction.Context;
//
//import com.springjposq2.testenum.TestEnum;
//
//public class IsoResponse implements ISORequestListener, Configurable{
//	private String queueName;
//	//private  Configuration cfig ;
//	protected Space<String, Context> sp;
//	public static final String REQUEST = "REQUEST";
//	public static final String ISOSOURCE = "ISOSOURCE";
//	
//	public IsoResponse() {
//		super();
//	// TODO Auto-generated constructor stub
//	}
//	@Override
//	public void setConfiguration(Configuration cfg)throws ConfigurationException {
//		queueName = cfg.get("queue");
//		System.out.println("------------------"+queueName);
//		sp = SpaceFactory.getSpace (cfg.get ("space"));
//		System.out.println("space--------------"+sp.toString());
//		//this.cfig=cfg;
//	}
//	public boolean process (ISOSource source, ISOMsg m) {
//		//m.dump(System.out, " ");
//		System.out.println("Processed method--------");
//		Context ctx = new Context ();
//		System.out.println("keeping the message in request");
//		ctx.put (TestEnum.request, m);
//		System.out.println("keeping the source in the ISOSource");
//		ctx.put (TestEnum.isosource, source);
//		System.out.println("adding the context to space");
//		sp.out (queueName, ctx);
//	return true;
//	}
//	@Override
//	public boolean process (ISOSource source, ISOMsg m) {
//		queueName = cfig.get("queue");
//		System.out.println("------------------"+queueName);
//		sp = SpaceFactory.getSpace (cfig.get ("space"));
//		System.out.println("space--------------"+sp.toString());
//		m.dump(System.out, " ");
//		Context ctx = new Context ();
//		
//		ctx.put (TestEnum.request, m);
//		ctx.put(TestEnum.isosource, source);
//		
//		
//		try {
//			m.setResponseMTI();
//			Random random = new Random (System.currentTimeMillis());
//			m.set (37, Integer.toString(Math.abs(random.nextInt()) % 1000000));
//			m.set (38, Integer.toString(Math.abs(random.nextInt()) % 1000000));
//			if ("000000009999".equals (m.getString (4)))
//			m.set (39, "01");
//			else
//			m.set (39, "00");
//			source.send(m);
//		}catch (ISOException | IOException e) {
//			e.printStackTrace();
//		}
//		return false;
//	}		

//}
import org.jpos.core.Configurable;
import org.jpos.core.Configuration;
import org.jpos.core.ConfigurationException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOSource;
import org.jpos.space.Space;
import org.jpos.space.SpaceFactory;
import org.jpos.transaction.Context;


import com.springjposq2.testenum.TestEnum;


public class RequestListener implements ISORequestListener ,Configurable{

	private String queueName;
	protected Space<String, Context> sp;
	public static final String REQUEST = "REQUEST";
	public static final String ISOSOURCE = "ISOSOURCE";
	
	public RequestListener() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public void setConfiguration(Configuration cfg)throws ConfigurationException {
		queueName = cfg.get("queue");
		System.out.println("------------------"+queueName);
		sp = SpaceFactory.getSpace (cfg.get ("space"));
		System.out.println("space--------------"+sp.toString());
	}
	public boolean process (ISOSource source, ISOMsg m) {
		System.out.println("Processed method--------");
		Context ctx = new Context ();
		System.out.println("keeping the message in request");
		ctx.put (TestEnum.request, m);
		System.out.println("keeping the source in the ISOSource");
		ctx.put (TestEnum.isosource, source);
		System.out.println("adding the context to space");
		sp.out (queueName, ctx);
		return true;
	}
}