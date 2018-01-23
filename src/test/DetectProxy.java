package test;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;

public class DetectProxy {
	
	public DetectProxy() {
		super();
		System.setProperty("java.net.useSystemProxies","true");
        List l = null;
		try {
			l = ProxySelector.getDefault().select(
			            new URI("http://www.yahoo.com/"));
	       for (Iterator iter = l.iterator(); iter.hasNext(); ) {
	             
	            Proxy proxy = (Proxy) iter.next();
	            proxyType = proxy.type().toString();
	             
	            InetSocketAddress addr = (InetSocketAddress)
	                proxy.address();
	             
	            if(addr == null) {
	            	hasProxy= false;
	            } else {
	            	hasProxy= true;
	                port= addr.getPort();
	                hostProxy= addr.getHostName();
	            }
	        }
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private int port;
	private String hostProxy;
	private boolean hasProxy;
	private String proxyType;
	
	
	public static void main(String[] args) throws URISyntaxException {

		System.setProperty("java.net.useSystemProxies","true");
        List l = ProxySelector.getDefault().select(
                    new URI("http://www.yahoo.com/"));
         
        for (Iterator iter = l.iterator(); iter.hasNext(); ) {
             
            Proxy proxy = (Proxy) iter.next();
             
            System.out.println("proxy hostname : " + proxy.type());
             
            InetSocketAddress addr = (InetSocketAddress)
                proxy.address();
             
            if(addr == null) {
                 
                System.out.println("No Proxy");
                 
            } else {
                 
                System.out.println("proxy hostname : " + 
                        addr.getHostName());
                 
                System.out.println("proxy port : " + 
                        addr.getPort());
                 
            }
        }
	}

	public int getPort() {
		return port;
	}

	public String getHostProxy() {
		return hostProxy;
	}

	public boolean isHasProxy() {
		return hasProxy;
	}

	public String getProxyType() {
		return proxyType;
	}

}
