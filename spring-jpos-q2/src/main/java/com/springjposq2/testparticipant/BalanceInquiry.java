package com.springjposq2.testparticipant;

import java.io.Serializable;

import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOSource;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;
import org.jpos.util.Logger;
import org.jpos.util.SimpleLogListener;

import com.springjposq2.testenum.TestEnum;

public class BalanceInquiry implements TransactionParticipant{
	public void abort(long id, Serializable context) { 
        System.out.println("ParticipantTest Process aborted"); 
	} 

	public void commit(long id, Serializable context) { 
		Context ctx=(Context)context; 
		ISOMsg msg=(ISOMsg)ctx.get(TestEnum.request); 
		ISOSource source=(ISOSource)ctx.get(TestEnum.isosource); 
    	try { 
    		source.send(msg); 
    	} catch(Exception e) { 
    		e.printStackTrace(); 
    	} 
    System.out.println("Committing the participantTest process"); 
	} 

	public int prepare(long id, Serializable context) { 
		Context ctx=(Context)context; 
		ISOMsg msg=(ISOMsg)ctx.get(TestEnum.request); 
		Logger logger=new Logger(); 
		logger.addListener (new SimpleLogListener (System.out)); 
		ISOSource source=(ISOSource)ctx.get(TestEnum.isosource); 
		try { 
			msg.setResponseMTI();
			msg.set(39,"02"); 
			source.send(msg); 
		} catch(Exception e) { 
			e.printStackTrace(); 
		} 
		return PREPARED; 
	} 


}
