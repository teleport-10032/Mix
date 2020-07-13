package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import static com.company.fun.judge;
import static com.company.fun.judge_mask;
import static com.company.fun.getLocalIP;
import static com.company.fun.judge_net;
import static com.company.fun.getMACByIp;
import static com.company.fun.ByteToString;

public class display extends JFrame
{
    int size = 5;
    JPanel jp[] = new JPanel[size];


    JLabel label_ip = new JLabel("input IP:");
    JTextField jtf = new JTextField(20);
    JLabel label_mask = new JLabel("input Mask(Net):");
    JTextField jtf2 = new JTextField(20);

    JLabel local_ip = new JLabel("localIP");
    JTextField jtf_localip = new JTextField(20);

    JButton button = new JButton("getMAC");
    JButton exit = new JButton("Exit");

    JLabel label_result = new JLabel("result:");
    JTextField jtf_result = new JTextField(20);

    public display()
    {
        this.setLayout(new GridLayout(4,1));
        for (int i = 0; i < size; i++)
        {
            jp[i] = new JPanel();
            jp[i].setLayout(new FlowLayout());
            this.add(jp[i]);
        }

        jp[0].add(label_ip);
        jp[0].add(jtf);
        jp[1].add(label_mask);
        jp[1].add(jtf2);

        jp[2].add(local_ip);
        jp[2].add(jtf_localip);
        jtf_localip.setEditable(false);
        String localip_text;
        try {
            localip_text = getLocalIP();
            jtf_localip.setText(localip_text);
        }
        catch (Exception ee)
        {
        }

        jp[2].add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String ip = jtf.getText().toString();
                String mask = jtf2.getText().toString();
                boolean r = judge(ip);
                //合法
                if(r)
                {
                    //mask合法
                    if(judge_mask(mask))
                    {

                        try
                        {
                            //同一子网
                            boolean r2 = judge_net(getLocalIP(),ip,mask);
                            if(r2)
                            {
                                if(ip.equals(getLocalIP()))
                                {
                                    jtf_result.setText("不能对localIP操作");
                                }
                                else
                                {
                                    String str = ByteToString(getMACByIp(ip));
                                    jtf_result.setText(str);
                                }
                            }
                            else
                            {
                                jtf_result.setText("非同一子网");
                            }
                        }
                        catch (Exception e2)
                        {
//                            e2.printStackTrace();
                        }
                    }
                    else
                    {
                        jtf_result.setText("mask不合法");
                    }
                }
                //不合法
                else
                {
                    jtf_result.setText("IP地址不合法");
                }

            }
        });

        jp[3].add(label_result);
        jp[3].add(jtf_result);
        jtf_result.setEditable(false);


        jp[4].add(exit);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.setTitle("通过ARP获取MAC地址");
        this.setSize(700, 300);
        this.setLocation(800, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        display d = new display();
    }

}

/*
10.37.129.2
 */