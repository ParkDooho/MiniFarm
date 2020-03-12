package shinhan.ac.kr.minifarm_android.Z_Data;

public class SensorDTO {
    private String userId;
    private String sensorName;
    private float sensorTem;
    private float sensorHum;
    private int sensorIllu;
    private float sensorPH;
    private int sensorFD;

    public SensorDTO() {
        super();
        // TODO Auto-generated constructor stub
    }



    public SensorDTO(String userId, String sensorName, float sensorTem, float sensorHum, int sensorIllu, float sensorPH,
                     int sensorFD) {
        super();
        this.userId = userId;
        this.sensorName = sensorName;
        this.sensorTem = sensorTem;
        this.sensorHum = sensorHum;
        this.sensorIllu = sensorIllu;
        this.sensorPH = sensorPH;
        this.sensorFD = sensorFD;
    }



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public float getSensorTem() {
        return sensorTem;
    }

    public void setSensorTem(float sensorTem) {
        this.sensorTem = sensorTem;
    }

    public float getSensorHum() {
        return sensorHum;
    }

    public void setSensorHum(float sensorHum) {
        this.sensorHum = sensorHum;
    }

    public int getSensorIllu() {
        return sensorIllu;
    }

    public void setSensorIllu(int sensorIllu) {
        this.sensorIllu = sensorIllu;
    }

    public float getSensorPH() {
        return sensorPH;
    }

    public void setSensorPH(float sensorPH) {
        this.sensorPH = sensorPH;
    }

    public int getSensorFD() {
        return sensorFD;
    }

    public void setSensorFD(int sensorFD) {
        this.sensorFD = sensorFD;
    }

    @Override
    public String toString() {
        return "SensorDTO [userId=" + userId + ", sensorName=" + sensorName + ", sensorTem=" + sensorTem
                + ", sensorHum=" + sensorHum + ", sensorIllu=" + sensorIllu + ", sensorPH=" + sensorPH + ", sensorFD="
                + sensorFD + "]";
    }



}
