package org.whut.monitor.business.dataTest.misc;

import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.logger.PlatformLogger;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-12-12
 * Time: 上午11:02
 * To change this template use File | Settings | File Templates.
 */
public class SimulatorThread implements Runnable{
    private static final PlatformLogger logger = PlatformLogger.getLogger(SimulatorThread.class);

    private static SimulatorThread singleton;

    private String host;
    private int port;
    private Socket client;
    private Writer writer;

    private boolean flag=false;
    private long interval = 5000;

    public static SimulatorThread getSingleton(){
        if(singleton==null){
            start();
        }
        return singleton;
    }

    /**
     * Runnable 接口方法
     * 由Service启动
     */
    @Override
    public void run() {
        ini();
        while (true){
            if (flag) break;
            doTask();
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }
    }

    /**
     * 启动监听
     */
    public static synchronized void start(){
        if (singleton==null){
            singleton = new SimulatorThread();
            new Thread(singleton).start();
            logger.info("simulator start successfully!");
        }else {
            logger.info("simulator has been started!");
        }
    }

    /**
     * 停止线程
     */
    public static synchronized void stop(){
        if(singleton!=null){
            singleton.flag = true;
            try {
                singleton.writer.close();
                singleton.client.close();
            }
            catch (Exception e){
               logger.error(e.getMessage());
            }finally {
                singleton=null;
                logger.info("simulator stop successfully!");
            }
        }else{
            logger.info("simulator has been stopped!");
        }
    }

    public static void setInterval(int interval){
         if (singleton!=null){
             singleton.interval = interval;
         }else{
             logger.info("simulator not started!");
         }
    }

    /**
     * 初始化
     */
    private void ini(){
        this.host = FundamentalConfigProvider.get("realtimemonitor.simulator.host");
        this.port = Integer.parseInt(FundamentalConfigProvider.get("realtimemonitor.simulator.port").trim());
        try{
            client = new Socket(host, port);
            writer = new OutputStreamWriter(client.getOutputStream());
        }
        catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    /**
     * 发送数据
     */
    private void doTask(){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long random[] = new long[24];
            for(int x=0;x<24;x++){
                random[x] = Math.round(Math.random()*600);
            }
            String prefix="310000000001";
            Date now = new Date();
            String json = "{sensors:["+"{sensorNum:'"+prefix+"0000',dataType:'Route',time:'"+format.format(now)+"',data:["+random[0]+"]},"
                    +"{sensorNum:'"+prefix+"0001',dataType:'Route',time:'"+format.format(now)+"',data:["+random[1]+"]},"
                    +"{sensorNum:'"+prefix+"0002',dataType:'Route',time:'"+format.format(now)+"',data:["+random[2]+"]},"
                    +"{sensorNum:'"+prefix+"0003',dataType:'Route',time:'"+format.format(now)+"',data:["+random[3]+"]},"
                    +"{sensorNum:'"+prefix+"0004',dataType:'Route',time:'"+format.format(now)+"',data:["+random[4]+"]},"
                    +"{sensorNum:'"+prefix+"0005',dataType:'Route',time:'"+format.format(now)+"',data:["+random[5]+"]},"
                    +"{sensorNum:'"+prefix+"0006',dataType:'Route',time:'"+format.format(now)+"',data:["+random[6]+"]},"
                    +"{sensorNum:'"+prefix+"0007',dataType:'Route',time:'"+format.format(now)+"',data:["+random[7]+"]},"
                    +"{sensorNum:'"+prefix+"0008',dataType:'Route',time:'"+format.format(now)+"',data:["+random[8]+"]},"
                    +"{sensorNum:'"+prefix+"0009',dataType:'Route',time:'"+format.format(now)+"',data:["+random[9]+"]},"
                    +"{sensorNum:'"+prefix+"0010',dataType:'Route',time:'"+format.format(now)+"',data:["+random[10]+"]},"
                    +"{sensorNum:'"+prefix+"0011',dataType:'Route',time:'"+format.format(now)+"',data:["+random[11]+"]},"
                    +"{sensorNum:'"+prefix+"0012',dataType:'Route',time:'"+format.format(now)+"',data:["+random[12]+"]},"
                    +"{sensorNum:'"+prefix+"0013',dataType:'Route',time:'"+format.format(now)+"',data:["+random[13]+"]},"
                    +"{sensorNum:'"+prefix+"0014',dataType:'Route',time:'"+format.format(now)+"',data:["+random[14]+"]},"
                    +"{sensorNum:'"+prefix+"0015',dataType:'Route',time:'"+format.format(now)+"',data:["+random[15]+"]},"
                    +"{sensorNum:'"+prefix+"0016',dataType:'Route',time:'"+format.format(now)+"',data:["+random[16]+"]},"
                    +"{sensorNum:'"+prefix+"0017',dataType:'Route',time:'"+format.format(now)+"',data:["+random[17]+"]},"
                    +"{sensorNum:'"+prefix+"0018',dataType:'Route',time:'"+format.format(now)+"',data:["+random[18]+"]},"
                    +"{sensorNum:'"+prefix+"0019',dataType:'Route',time:'"+format.format(now)+"',data:["+random[19]+"]},"
                    +"{sensorNum:'"+prefix+"0020',dataType:'Route',time:'"+format.format(now)+"',data:["+random[20]+"]},"
                    +"{sensorNum:'"+prefix+"0021',dataType:'Route',time:'"+format.format(now)+"',data:["+random[21]+"]},"
                    +"{sensorNum:'"+prefix+"0022',dataType:'Route',time:'"+format.format(now)+"',data:["+random[22]+"]},"
                    +"{sensorNum:'"+prefix+"0023',dataType:'Route',time:'"+format.format(now)+"',data:["+random[23]+"]}]}";
            writer.write(json);
            writer.flush();//写完后要记得flush
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

}
