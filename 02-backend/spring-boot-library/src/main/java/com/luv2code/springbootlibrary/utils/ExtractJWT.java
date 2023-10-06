package com.luv2code.springbootlibrary.utils;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExtractJWT 
{
	private static final Logger logger = LoggerFactory.getLogger(ExtractJWT .class);

	public static String payloadJWTExtraction(String token, String extraction) 
	{
		token.replace("Bearer ", "");
		
		String[] chunks = token.split("\\.");
		Base64.Decoder decoder = Base64.getUrlDecoder();
		
		String payload = new String(decoder.decode(chunks[1]));
		
		String[] entries = payload.split(",");
		//logger.info("entries: {}",entries);
		Map<String, String> map = new HashMap<String, String>();
		
		for(String entry : entries) 
		{
			String[] keyValue = entry.split(":");
			
			//logger.info("KeyValue: {}",keyValue);
			
			if(keyValue[0].equals(extraction)) 
			{
			
				int remove = 1;
				if(keyValue[1].endsWith("}")) 
				{
					remove = 2;
				}
				keyValue[1] = keyValue[1].substring(0,keyValue[1].length() - remove);
				//logger.info("KeyValue: {}",keyValue[1]);
				keyValue[1] = keyValue[1].substring(1);
				//logger.info("KeyValue: {}",keyValue[1]);
				map.put(keyValue[0], keyValue[1]);
				//logger.info("KeyValue: {}",keyValue[0]);
			}
		
		}
		if(map.containsKey(extraction)) 
		{
			return map.get(extraction);
		}
	
		return null;
	
	}
}
