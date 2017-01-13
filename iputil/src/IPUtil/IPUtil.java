package IPUtil;

import java.util.Scanner;

public class IPUtil {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String str = "";
		while((str = scan.next()) != null)
		{
			String[] substr = str.split("/");
			String ip = substr[0];
			int netmask = Integer.parseInt(substr[1]);
			System.out.println(getEndIP(ip,netmask).getStartIP()
				+"~"+getEndIP(ip,netmask).getEndIP());
		}
	}

		
	/**
     * ������ʼIP��ַ���������������ֹIP
     */
    public static Nets getEndIP(String StartIP,int netmask)
    {
        return getEndIP(StartIP,getMask(netmask));
    }
    
    /**
     * ������ʼIP��ַ���������������ֹIP
     */
    public static Nets getEndIP(String StartIP,String netmask)
    {
        Nets nets = new Nets();
        String[] start=Negation(StartIP,netmask).split("\\.");
        nets.setStartIP(start[0]+"."+start[1]+"."+start[2]+"."+(Integer.valueOf(start[3])+1));
        nets.setEndIP(TaskOR(Negation(StartIP,netmask),netmask));
        nets.setNetMask(netmask);
        return nets;
    }
    /**
     * ��������λ��������
     */
    public static String getMask(int masks)
    {
        if(masks == 1)
            return "128.0.0.0";
        else if(masks == 2)
            return "192.0.0.0";
        else if(masks == 3)
            return "224.0.0.0";
        else if(masks == 4)
            return "240.0.0.0";
        else if(masks == 5)
            return "248.0.0.0";
        else if(masks == 6) 
            return "252.0.0.0";
        else if(masks == 7)
            return "254.0.0.0";
        else if(masks == 8)
            return "255.0.0.0";
        else if(masks ==9)
            return "255.128.0.0";
        else if(masks == 10)
            return "255.192.0.0";
        else if(masks == 11)
            return "255.224.0.0";
        else if(masks == 12)
            return "255.240.0.0";
        else if(masks == 13)
            return "255.248.0.0";
        else if(masks == 14)
            return "255.252.0.0";
        else if(masks == 15)
            return "255.254.0.0";
        else if(masks == 16)
            return "255.255.0.0";
        else if(masks == 17)
            return "255.255.128.0";
        else if(masks == 18)
            return "255.255.192.0";
        else if(masks == 19)
            return "255.255.224.0";
        else if(masks == 20)
            return "255.255.240.0";
        else if(masks == 21)
            return "255.255.248.0";
        else if(masks == 22)
            return "255.255.252.0";
        else if(masks == 23)
            return "255.255.254.0";
        else if(masks == 24)
            return "255.255.255.0";
        else if(masks == 25)
            return "255.255.255.128";
        else if(masks == 26)
            return "255.255.255.192";
        else if(masks == 27)
            return "255.255.255.224";
        else if(masks == 28)
            return "255.255.255.240";
        else if(masks == 29)
            return "255.255.255.248";
        else if(masks == 30)
            return "255.255.255.252";
        else if(masks == 31)
            return "255.255.255.254";
        else if(masks == 32)
            return "255.255.255.255";
        return "";
    }
    /**
     * temp1����temp2ȡ��
     */
    private static String Negation(String StartIP,String netmask)
    {
        String[] temp1=StartIP.trim().split("\\.");
        String[] temp2=netmask.trim().split("\\.");
        int[] rets=new int[4];
        for (int i =0;i<4;i++) {
            rets[i]=Integer.parseInt(temp1[i])&Integer.parseInt(temp2[i]);
            //System.out.println(rets[i]);
        }
        return rets[0]+"."+rets[1]+"."+rets[2]+"."+rets[3];
    }
    /**
     * temp1����temp2ȡ��
     */
    private static String TaskOR(String StartIP,String netmask)
    {
        String[] temp1=StartIP.trim().split("\\.");
        String[] temp2=netmask.trim().split("\\.");
        int[] rets=new int[4];
        for (int i =0;i<4;i++) {
            rets[i]=255-(Integer.parseInt(temp1[i])^Integer.parseInt(temp2[i]));
            //System.out.println(rets[i]);
        }
        return rets[0]+"."+rets[1]+"."+rets[2]+"."+(rets[3]-1);
    }
    /**
     * ����������С
     */
    public static int getPoolMax(int netmask)
    {
        if(netmask<=0||netmask>=32)
        {
            return 0;
        }
        int bits=32-netmask;
        return (int) Math.pow(2,bits) -2;
    }
    /**
     * ת��Ϊ����λ��
     */
    public static int getNetMask(String netmarks)
    {
        StringBuffer sbf;
        String str;
        int inetmask=0,count=0;
        String[] ipList=netmarks.split("\\.");
        for(int n=0;n<ipList.length;n++)
        {
            sbf = toBin(Integer.parseInt(ipList[n]));
            str=sbf.reverse().toString();
            count=0;
            for(int i=0;i<str.length();i++){
                i=str.indexOf('1',i);             
                if(i==-1){break;}
                count++;
            }
            inetmask+=count;
        }
        return inetmask;
    }
    private static StringBuffer toBin(int x)
    {
        StringBuffer result=new StringBuffer();
        result.append(x%2);
        x/=2;
        while(x>0){
            result.append(x%2);
            x/=2;
        }
        return result;
    }
}