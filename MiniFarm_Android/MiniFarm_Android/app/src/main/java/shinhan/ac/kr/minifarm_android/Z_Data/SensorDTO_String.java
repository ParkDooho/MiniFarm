package shinhan.ac.kr.minifarm_android.Z_Data;

public class SensorDTO_String {
    private String sensorTem;
    private String sensorHum;
    private String sensorIllu;
    private String sensorPH;
    private String sensorFD;

    public SensorDTO_String() {
        super();
        // TODO Auto-generated constructor stub
    }


    public SensorDTO_String(String sensorTem, String sensorHum, String sensorIllu, String sensorPH, String sensorFD) {
        super();
        this.sensorTem = sensorTem;
        this.sensorHum = sensorHum;
        this.sensorIllu = sensorIllu;
        this.sensorPH = sensorPH;
        this.sensorFD = sensorFD;
    }

    public String getSensorTem() {
        return sensorTem;
    }

    public void setSensorTem(String sensorTem) {
        this.sensorTem = sensorTem;
    }

    public String getSensorHum() {
        return sensorHum;
    }

    public void setSensorHum(String sensorHum) {
        this.sensorHum = sensorHum;
    }

    public String getSensorIllu() {
        return sensorIllu;
    }

    public void setSensorIllu(String sensorIllu) {
        this.sensorIllu = sensorIllu;
    }

    public String getSensorPH() {
        return sensorPH;
    }

    public void setSensorPH(String sensorPH) {
        this.sensorPH = sensorPH;
    }

    public String getSensorFD() {
        return sensorFD;
    }

    public void setSensorFD(String sensorFD) {
        this.sensorFD = sensorFD;
    }



    @Override
    public String toString() {
        return "SensorDTO [sensorTem=" + sensorTem + ", sensorHum=" + sensorHum + ", sensorIllu=" + sensorIllu + ", sensorPH=" + sensorPH + ", sensorFD="
                + sensorFD + "]";
    }











}
