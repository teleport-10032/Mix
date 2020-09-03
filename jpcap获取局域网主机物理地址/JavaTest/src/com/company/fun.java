package com.company;

import jpcap.JpcapCaptor;
import jpcap.JpcapSender;
import jpcap.NetworkInterface;
import jpcap.packet.*;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class fun
{

    //当前默认打开的网卡
    public static NetworkInterface devices = JpcapCaptor.getDeviceList()[0];
    //本机的IP
    public static String local_IP = "";
    public static String ByteToString(byte[] mac)
    {
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<mac.length; i++){
            if (i != 0){
                sb.append("-");
            }
            String s = Integer.toHexString(mac[i] & 0xFF);
            sb.append(s.length()==1?0+s:s);
        }
        return sb.toString().toUpperCase();
    }

    public static NetworkInterface getDevices()
    {
        NetworkInterface[] devices = JpcapCaptor.getDeviceList();
        for (NetworkInterface n : devices){
            System.out.println("网卡名称" + n.name + "     |     " + "描述："  + n.description);
            System.out.println("MAC地址：" + ByteToString(n.mac_address));
        }
        return devices[0];
    }
    public static byte[] getMACByIp(String ip) throws IOException
    {
        JpcapCaptor jc = JpcapCaptor.openDevice(devices, 2000, false, 3000);
        JpcapSender sender = jc.getJpcapSenderInstance();
        InetAddress source_IP = InetAddress.getByName(local_IP);
        InetAddress dst_IP = InetAddress.getByName(ip);

        //构建arp包
        ARPPacket arp = new ARPPacket();
        arp.hardtype = ARPPacket.HARDTYPE_ETHER;
        arp.prototype = ARPPacket.PROTOTYPE_IP;
        arp.operation = ARPPacket.ARP_REQUEST;
        arp.hlen = 6;
        arp.plen = 4;
        //指定发送端mac地址和ip地址
        arp.sender_hardaddr = devices.mac_address;
        arp.sender_protoaddr = source_IP.getAddress();


        byte[] broadcast = new byte[]{(byte)255,(byte)255,(byte)255,(byte)255,(byte)255,(byte)255};
        //指定接收端mac地址(此时不知道，所以采用广播)和ip地址
        arp.target_hardaddr = broadcast;
        arp.target_protoaddr = dst_IP.getAddress();

        //构建以太网帧
        jpcap.packet.EthernetPacket ether = new jpcap.packet.EthernetPacket();
        ether.frametype = jpcap.packet.EthernetPacket.ETHERTYPE_ARP;
        ether.src_mac = devices.mac_address;
        ether.dst_mac = broadcast;
        arp.datalink = ether;

        //发送报文
        try
        {
            sender.sendPacket(arp);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        while(true)
        {

            Packet packet = jc.getPacket();
            if(packet instanceof ARPPacket)
            {
                ARPPacket p=(ARPPacket)packet;
                if(Arrays.equals(p.target_protoaddr,source_IP.getAddress()))
                {
                    return p.sender_hardaddr;
                }
            }
        }

    }


    public static void getLocalInfo() throws Exception
    {
        InetAddress inetAddress = InetAddress.getLocalHost();

        //获取IP
        String localName = inetAddress.getHostName();
        String local_IP = inetAddress.getHostAddress();


        //获取MAC
        byte[] mac = devices.mac_address;

        local_IP = local_IP;
        System.out.println("本机IP：" + local_IP);
        System.out.println("本机MAC：" + ByteToString(mac));
    }

    public static String getLocalIP() throws Exception
    {
        InetAddress inetAddress = InetAddress.getLocalHost();

        //获取IP
        String local_IP = inetAddress.getHostAddress();
        local_IP = local_IP;
        return local_IP.toString();
    }
    public static boolean judge(String text)
    {
        if (text != null && !text.isEmpty())
        {
            String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."+
                    "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."+
                    "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."+
                    "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
            if (text.matches(regex))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }
    public static boolean judge_mask(String text)
    {
        try
        {
            int mask = Integer.parseInt(text);
            if(mask >= 1 && mask <= 31)
                return true;
            else
                return false;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public static boolean judge_net(String ip1,String ip2,String mask)
    {
        //是否在同一子网
        String ip1_arr[] = ip1.split("\\.");
        String ip2_arr[] = ip2.split("\\.");

        String ip1_str = "";
        String ip2_str = "";

        for(int i = 0 ; i < 4 ; i++)
        {
//            ip1_str += ip1_arr[i];
//            ip2_str += ip2_arr[i];
            int len1 = Integer.toBinaryString(Integer.parseInt(ip1_arr[i])).length();
            int len2 = Integer.toBinaryString(Integer.parseInt(ip2_arr[i])).length();
            ip1_str += Integer.toBinaryString(Integer.parseInt(ip1_arr[i]));
            ip2_str += Integer.toBinaryString(Integer.parseInt(ip2_arr[i]));
            if(len1 < 8)
            {
                for(int  j= 0 ; j < 8-len1 ; j ++)
                    ip1_str += "0";
            }
            if(len2 < 8)
            {
                for(int  j= 0 ; j < 8-len2 ; j ++)
                    ip2_str += "0";
            }
        }
        //网络位相同则是一个子网
        int mask_int = Integer.parseInt(mask);
        int flag = 0;
        for(int i = 0 ; i < mask_int ; i ++)
            if(ip1_str.charAt(i) != ip2_str.charAt(i))
                flag = 1;
        if(flag == 1)
            return false;
        else
            return true;

    }

    public static void main(String[] args) throws Exception
    {

//        getLocalInfo();
//        getDevices();
//        System.out.println("输入目标IP:");
//        Scanner scanner = new Scanner(System.in);
//        String WangGuanIP = scanner.nextLine().toString();
//
//        byte[] wangGuanMAC = getMACByIp(WangGuanIP);
//        String str = ByteToString(wangGuanMAC);
//        System.out.println(str);
    }
}
/*
10.37.129.2
 */