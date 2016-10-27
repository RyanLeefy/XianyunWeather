package com.example.ryanlee.rainbowweather.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ryanlee on 2016/8/7 0007.
 */
public class HeWeatherDataService30 {


    @SerializedName("basic")
    @Expose
    private Basic basic;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("aqi")
    @Expose
    private Aqi aqi;
    @SerializedName("now")
    @Expose
    private Now now;
    @SerializedName("daily_forecast")
    @Expose
    private List<DailyForecast> dailyForecast = new ArrayList<DailyForecast>();
    @SerializedName("hourly_forecast")
    @Expose
    private List<HourlyForecast> hourlyForecast = new ArrayList<HourlyForecast>();
    @SerializedName("suggestion")
    @Expose
    private Suggestion suggestion;

    /**
     *
     * @return
     * The basic
     */
    public Basic getBasic() {
        return basic;
    }

    /**
     *
     * @param basic
     * The basic
     */
    public void setBasic(Basic basic) {
        this.basic = basic;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The aqi
     */
    public Aqi getAqi() {
        return aqi;
    }

    /**
     *
     * @param aqi
     * The aqi
     */
    public void setAqi(Aqi aqi) {
        this.aqi = aqi;
    }

    /**
     *
     * @return
     * The now
     */
    public Now getNow() {
        return now;
    }

    /**
     *
     * @param now
     * The now
     */
    public void setNow(Now now) {
        this.now = now;
    }

    /**
     *
     * @return
     * The dailyForecast
     */
    public List<DailyForecast> getDailyForecast() {
        return dailyForecast;
    }

    /**
     *
     * @param dailyForecast
     * The daily_forecast
     */
    public void setDailyForecast(List<DailyForecast> dailyForecast) {
        this.dailyForecast = dailyForecast;
    }

    /**
     *
     * @return
     * The hourlyForecast
     */
    public List<HourlyForecast> getHourlyForecast() {
        return hourlyForecast;
    }

    /**
     *
     * @param hourlyForecast
     * The hourly_forecast
     */
    public void setHourlyForecast(List<HourlyForecast> hourlyForecast) {
        this.hourlyForecast = hourlyForecast;
    }

    /**
     *
     * @return
     * The suggestion
     */
    public Suggestion getSuggestion() {
        return suggestion;
    }

    /**
     *
     * @param suggestion
     * The suggestion
     */
    public void setSuggestion(Suggestion suggestion) {
        this.suggestion = suggestion;
    }






    public class Aqi {
        @SerializedName("city")
        @Expose
        private City city;

        public City getCity() {
            return city;
        }

        public void setCity(City city) {
            this.city = city;
        }
    }

    public class Basic {
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("cnty")
        @Expose
        private String cnty;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("lat")
        @Expose
        private String lat;
        @SerializedName("lon")
        @Expose
        private String lon;
        @SerializedName("update")
        @Expose
        private Update update;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCnty() {
            return cnty;
        }

        public void setCnty(String cnty) {
            this.cnty = cnty;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public Update getUpdate() {
            return update;
        }

        public void setUpdate(Update update) {
            this.update = update;
        }
    }

    public class Astro {
        @SerializedName("sr")
        @Expose
        private String sr;
        @SerializedName("ss")
        @Expose
        private String ss;

        public String getSr() {
            return sr;
        }

        public void setSr(String sr) {
            this.sr = sr;
        }

        public String getSs() {
            return ss;
        }

        public void setSs(String ss) {
            this.ss = ss;
        }
    }

    public class City {

        @SerializedName("aqi")
        @Expose
        private String aqi;
        @SerializedName("co")
        @Expose
        private String co;
        @SerializedName("no2")
        @Expose
        private String no2;
        @SerializedName("o3")
        @Expose
        private String o3;
        @SerializedName("pm10")
        @Expose
        private String pm10;
        @SerializedName("pm25")
        @Expose
        private String pm25;
        @SerializedName("qlty")
        @Expose
        private String qlty;
        @SerializedName("so2")
        @Expose
        private String so2;

        public String getAqi() {
            return aqi;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public String getCo() {
            return co;
        }

        public void setCo(String co) {
            this.co = co;
        }

        public String getNo2() {
            return no2;
        }

        public void setNo2(String no2) {
            this.no2 = no2;
        }

        public String getO3() {
            return o3;
        }

        public void setO3(String o3) {
            this.o3 = o3;
        }

        public String getPm10() {
            return pm10;
        }

        public void setPm10(String pm10) {
            this.pm10 = pm10;
        }

        public String getPm25() {
            return pm25;
        }

        public void setPm25(String pm25) {
            this.pm25 = pm25;
        }

        public String getQlty() {
            return qlty;
        }

        public void setQlty(String qlty) {
            this.qlty = qlty;
        }

        public String getSo2() {
            return so2;
        }

        public void setSo2(String so2) {
            this.so2 = so2;
        }

    }

    public class Comf {

        @SerializedName("brf")
        @Expose
        private String brf;
        @SerializedName("txt")
        @Expose
        private String txt;

        /**
         *
         * @return
         * The brf
         */
        public String getBrf() {
            return brf;
        }

        /**
         *
         * @param brf
         * The brf
         */
        public void setBrf(String brf) {
            this.brf = brf;
        }

        /**
         *
         * @return
         * The txt
         */
        public String getTxt() {
            return txt;
        }

        /**
         *
         * @param txt
         * The txt
         */
        public void setTxt(String txt) {
            this.txt = txt;
        }

    }

    public class Cond {

        @SerializedName("code")
        @Expose
        private String code;
        @SerializedName("txt")
        @Expose
        private String txt;

        /**
         *
         * @return
         * The code
         */
        public String getCode() {
            return code;
        }

        /**
         *
         * @param code
         * The code
         */
        public void setCode(String code) {
            this.code = code;
        }

        /**
         *
         * @return
         * The txt
         */
        public String getTxt() {
            return txt;
        }

        /**
         *
         * @param txt
         * The txt
         */
        public void setTxt(String txt) {
            this.txt = txt;
        }

    }

    public class Cond_ {

        @SerializedName("code_d")
        @Expose
        private String codeD;
        @SerializedName("code_n")
        @Expose
        private String codeN;
        @SerializedName("txt_d")
        @Expose
        private String txtD;
        @SerializedName("txt_n")
        @Expose
        private String txtN;

        /**
         *
         * @return
         * The codeD
         */
        public String getCodeD() {
            return codeD;
        }

        /**
         *
         * @param codeD
         * The code_d
         */
        public void setCodeD(String codeD) {
            this.codeD = codeD;
        }

        /**
         *
         * @return
         * The codeN
         */
        public String getCodeN() {
            return codeN;
        }

        /**
         *
         * @param codeN
         * The code_n
         */
        public void setCodeN(String codeN) {
            this.codeN = codeN;
        }

        /**
         *
         * @return
         * The txtD
         */
        public String getTxtD() {
            return txtD;
        }

        /**
         *
         * @param txtD
         * The txt_d
         */
        public void setTxtD(String txtD) {
            this.txtD = txtD;
        }

        /**
         *
         * @return
         * The txtN
         */
        public String getTxtN() {
            return txtN;
        }

        /**
         *
         * @param txtN
         * The txt_n
         */
        public void setTxtN(String txtN) {
            this.txtN = txtN;
        }

    }

    public class Cw {

        @SerializedName("brf")
        @Expose
        private String brf;
        @SerializedName("txt")
        @Expose
        private String txt;

        /**
         *
         * @return
         * The brf
         */
        public String getBrf() {
            return brf;
        }

        /**
         *
         * @param brf
         * The brf
         */
        public void setBrf(String brf) {
            this.brf = brf;
        }

        /**
         *
         * @return
         * The txt
         */
        public String getTxt() {
            return txt;
        }

        /**
         *
         * @param txt
         * The txt
         */
        public void setTxt(String txt) {
            this.txt = txt;
        }

    }

    public class DailyForecast {

        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("astro")
        @Expose
        private Astro astro;
        @SerializedName("cond")
        @Expose
        private Cond_ cond;
        @SerializedName("hum")
        @Expose
        private String hum;
        @SerializedName("pcpn")
        @Expose
        private String pcpn;
        @SerializedName("pop")
        @Expose
        private String pop;
        @SerializedName("pres")
        @Expose
        private String pres;
        @SerializedName("tmp")
        @Expose
        private Tmp tmp;
        @SerializedName("vis")
        @Expose
        private String vis;
        @SerializedName("wind")
        @Expose
        private Wind_ wind;

        /**
         *
         * @return
         * The date
         */
        public String getDate() {
            return date;
        }

        /**
         *
         * @param date
         * The date
         */
        public void setDate(String date) {
            this.date = date;
        }

        /**
         *
         * @return
         * The astro
         */
        public Astro getAstro() {
            return astro;
        }

        /**
         *
         * @param astro
         * The astro
         */
        public void setAstro(Astro astro) {
            this.astro = astro;
        }

        /**
         *
         * @return
         * The cond
         */
        public Cond_ getCond() {
            return cond;
        }

        /**
         *
         * @param cond
         * The cond
         */
        public void setCond(Cond_ cond) {
            this.cond = cond;
        }

        /**
         *
         * @return
         * The hum
         */
        public String getHum() {
            return hum;
        }

        /**
         *
         * @param hum
         * The hum
         */
        public void setHum(String hum) {
            this.hum = hum;
        }

        /**
         *
         * @return
         * The pcpn
         */
        public String getPcpn() {
            return pcpn;
        }

        /**
         *
         * @param pcpn
         * The pcpn
         */
        public void setPcpn(String pcpn) {
            this.pcpn = pcpn;
        }

        /**
         *
         * @return
         * The pop
         */
        public String getPop() {
            return pop;
        }

        /**
         *
         * @param pop
         * The pop
         */
        public void setPop(String pop) {
            this.pop = pop;
        }

        /**
         *
         * @return
         * The pres
         */
        public String getPres() {
            return pres;
        }

        /**
         *
         * @param pres
         * The pres
         */
        public void setPres(String pres) {
            this.pres = pres;
        }

        /**
         *
         * @return
         * The tmp
         */
        public Tmp getTmp() {
            return tmp;
        }

        /**
         *
         * @param tmp
         * The tmp
         */
        public void setTmp(Tmp tmp) {
            this.tmp = tmp;
        }

        /**
         *
         * @return
         * The vis
         */
        public String getVis() {
            return vis;
        }

        /**
         *
         * @param vis
         * The vis
         */
        public void setVis(String vis) {
            this.vis = vis;
        }

        /**
         *
         * @return
         * The wind
         */
        public Wind_ getWind() {
            return wind;
        }

        /**
         *
         * @param wind
         * The wind
         */
        public void setWind(Wind_ wind) {
            this.wind = wind;
        }

    }

    public class Drsg {

        @SerializedName("brf")
        @Expose
        private String brf;
        @SerializedName("txt")
        @Expose
        private String txt;

        /**
         *
         * @return
         * The brf
         */
        public String getBrf() {
            return brf;
        }

        /**
         *
         * @param brf
         * The brf
         */
        public void setBrf(String brf) {
            this.brf = brf;
        }

        /**
         *
         * @return
         * The txt
         */
        public String getTxt() {
            return txt;
        }

        /**
         *
         * @param txt
         * The txt
         */
        public void setTxt(String txt) {
            this.txt = txt;
        }

    }

    public class Flu {

        @SerializedName("brf")
        @Expose
        private String brf;
        @SerializedName("txt")
        @Expose
        private String txt;

        /**
         *
         * @return
         * The brf
         */
        public String getBrf() {
            return brf;
        }

        /**
         *
         * @param brf
         * The brf
         */
        public void setBrf(String brf) {
            this.brf = brf;
        }

        /**
         *
         * @return
         * The txt
         */
        public String getTxt() {
            return txt;
        }

        /**
         *
         * @param txt
         * The txt
         */
        public void setTxt(String txt) {
            this.txt = txt;
        }

    }

    public class HourlyForecast {

        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("hum")
        @Expose
        private String hum;
        @SerializedName("pop")
        @Expose
        private String pop;
        @SerializedName("pres")
        @Expose
        private String pres;
        @SerializedName("tmp")
        @Expose
        private String tmp;
        @SerializedName("wind")
        @Expose
        private Wind__ wind;

        /**
         *
         * @return
         * The date
         */
        public String getDate() {
            return date;
        }

        /**
         *
         * @param date
         * The date
         */
        public void setDate(String date) {
            this.date = date;
        }

        /**
         *
         * @return
         * The hum
         */
        public String getHum() {
            return hum;
        }

        /**
         *
         * @param hum
         * The hum
         */
        public void setHum(String hum) {
            this.hum = hum;
        }

        /**
         *
         * @return
         * The pop
         */
        public String getPop() {
            return pop;
        }

        /**
         *
         * @param pop
         * The pop
         */
        public void setPop(String pop) {
            this.pop = pop;
        }

        /**
         *
         * @return
         * The pres
         */
        public String getPres() {
            return pres;
        }

        /**
         *
         * @param pres
         * The pres
         */
        public void setPres(String pres) {
            this.pres = pres;
        }

        /**
         *
         * @return
         * The tmp
         */
        public String getTmp() {
            return tmp;
        }

        /**
         *
         * @param tmp
         * The tmp
         */
        public void setTmp(String tmp) {
            this.tmp = tmp;
        }

        /**
         *
         * @return
         * The wind
         */
        public Wind__ getWind() {
            return wind;
        }

        /**
         *
         * @param wind
         * The wind
         */
        public void setWind(Wind__ wind) {
            this.wind = wind;
        }

    }

    public class Now {

        @SerializedName("cond")
        @Expose
        private Cond cond;
        @SerializedName("fl")
        @Expose
        private String fl;
        @SerializedName("hum")
        @Expose
        private String hum;
        @SerializedName("pcpn")
        @Expose
        private String pcpn;
        @SerializedName("pres")
        @Expose
        private String pres;
        @SerializedName("tmp")
        @Expose
        private String tmp;
        @SerializedName("vis")
        @Expose
        private String vis;
        @SerializedName("wind")
        @Expose
        private Wind wind;

        /**
         *
         * @return
         * The cond
         */
        public Cond getCond() {
            return cond;
        }

        /**
         *
         * @param cond
         * The cond
         */
        public void setCond(Cond cond) {
            this.cond = cond;
        }

        /**
         *
         * @return
         * The fl
         */
        public String getFl() {
            return fl;
        }

        /**
         *
         * @param fl
         * The fl
         */
        public void setFl(String fl) {
            this.fl = fl;
        }

        /**
         *
         * @return
         * The hum
         */
        public String getHum() {
            return hum;
        }

        /**
         *
         * @param hum
         * The hum
         */
        public void setHum(String hum) {
            this.hum = hum;
        }

        /**
         *
         * @return
         * The pcpn
         */
        public String getPcpn() {
            return pcpn;
        }

        /**
         *
         * @param pcpn
         * The pcpn
         */
        public void setPcpn(String pcpn) {
            this.pcpn = pcpn;
        }

        /**
         *
         * @return
         * The pres
         */
        public String getPres() {
            return pres;
        }

        /**
         *
         * @param pres
         * The pres
         */
        public void setPres(String pres) {
            this.pres = pres;
        }

        /**
         *
         * @return
         * The tmp
         */
        public String getTmp() {
            return tmp;
        }

        /**
         *
         * @param tmp
         * The tmp
         */
        public void setTmp(String tmp) {
            this.tmp = tmp;
        }

        /**
         *
         * @return
         * The vis
         */
        public String getVis() {
            return vis;
        }

        /**
         *
         * @param vis
         * The vis
         */
        public void setVis(String vis) {
            this.vis = vis;
        }

        /**
         *
         * @return
         * The wind
         */
        public Wind getWind() {
            return wind;
        }

        /**
         *
         * @param wind
         * The wind
         */
        public void setWind(Wind wind) {
            this.wind = wind;
        }

    }

    public class Sport {

        @SerializedName("brf")
        @Expose
        private String brf;
        @SerializedName("txt")
        @Expose
        private String txt;

        /**
         *
         * @return
         * The brf
         */
        public String getBrf() {
            return brf;
        }

        /**
         *
         * @param brf
         * The brf
         */
        public void setBrf(String brf) {
            this.brf = brf;
        }

        /**
         *
         * @return
         * The txt
         */
        public String getTxt() {
            return txt;
        }

        /**
         *
         * @param txt
         * The txt
         */
        public void setTxt(String txt) {
            this.txt = txt;
        }

    }

    public class Suggestion {

        @SerializedName("comf")
        @Expose
        private Comf comf;
        @SerializedName("cw")
        @Expose
        private Cw cw;
        @SerializedName("drsg")
        @Expose
        private Drsg drsg;
        @SerializedName("flu")
        @Expose
        private Flu flu;
        @SerializedName("sport")
        @Expose
        private Sport sport;
        @SerializedName("trav")
        @Expose
        private Trav trav;
        @SerializedName("uv")
        @Expose
        private Uv uv;

        /**
         *
         * @return
         * The comf
         */
        public Comf getComf() {
            return comf;
        }

        /**
         *
         * @param comf
         * The comf
         */
        public void setComf(Comf comf) {
            this.comf = comf;
        }

        /**
         *
         * @return
         * The cw
         */
        public Cw getCw() {
            return cw;
        }

        /**
         *
         * @param cw
         * The cw
         */
        public void setCw(Cw cw) {
            this.cw = cw;
        }

        /**
         *
         * @return
         * The drsg
         */
        public Drsg getDrsg() {
            return drsg;
        }

        /**
         *
         * @param drsg
         * The drsg
         */
        public void setDrsg(Drsg drsg) {
            this.drsg = drsg;
        }

        /**
         *
         * @return
         * The flu
         */
        public Flu getFlu() {
            return flu;
        }

        /**
         *
         * @param flu
         * The flu
         */
        public void setFlu(Flu flu) {
            this.flu = flu;
        }

        /**
         *
         * @return
         * The sport
         */
        public Sport getSport() {
            return sport;
        }

        /**
         *
         * @param sport
         * The sport
         */
        public void setSport(Sport sport) {
            this.sport = sport;
        }

        /**
         *
         * @return
         * The trav
         */
        public Trav getTrav() {
            return trav;
        }

        /**
         *
         * @param trav
         * The trav
         */
        public void setTrav(Trav trav) {
            this.trav = trav;
        }

        /**
         *
         * @return
         * The uv
         */
        public Uv getUv() {
            return uv;
        }

        /**
         *
         * @param uv
         * The uv
         */
        public void setUv(Uv uv) {
            this.uv = uv;
        }

    }

    public class Tmp {

        @SerializedName("max")
        @Expose
        private String max;
        @SerializedName("min")
        @Expose
        private String min;

        /**
         *
         * @return
         * The max
         */
        public String getMax() {
            return max;
        }

        /**
         *
         * @param max
         * The max
         */
        public void setMax(String max) {
            this.max = max;
        }

        /**
         *
         * @return
         * The min
         */
        public String getMin() {
            return min;
        }

        /**
         *
         * @param min
         * The min
         */
        public void setMin(String min) {
            this.min = min;
        }

    }

    public class Trav {

        @SerializedName("brf")
        @Expose
        private String brf;
        @SerializedName("txt")
        @Expose
        private String txt;

        /**
         *
         * @return
         * The brf
         */
        public String getBrf() {
            return brf;
        }

        /**
         *
         * @param brf
         * The brf
         */
        public void setBrf(String brf) {
            this.brf = brf;
        }

        /**
         *
         * @return
         * The txt
         */
        public String getTxt() {
            return txt;
        }

        /**
         *
         * @param txt
         * The txt
         */
        public void setTxt(String txt) {
            this.txt = txt;
        }

    }

    public class Update {

        @SerializedName("loc")
        @Expose
        private String loc;
        @SerializedName("utc")
        @Expose
        private String utc;

        /**
         *
         * @return
         * The loc
         */
        public String getLoc() {
            return loc;
        }

        /**
         *
         * @param loc
         * The loc
         */
        public void setLoc(String loc) {
            this.loc = loc;
        }

        /**
         *
         * @return
         * The utc
         */
        public String getUtc() {
            return utc;
        }

        /**
         *
         * @param utc
         * The utc
         */
        public void setUtc(String utc) {
            this.utc = utc;
        }

    }

    public class Uv {

        @SerializedName("brf")
        @Expose
        private String brf;
        @SerializedName("txt")
        @Expose
        private String txt;

        /**
         *
         * @return
         * The brf
         */
        public String getBrf() {
            return brf;
        }

        /**
         *
         * @param brf
         * The brf
         */
        public void setBrf(String brf) {
            this.brf = brf;
        }

        /**
         *
         * @return
         * The txt
         */
        public String getTxt() {
            return txt;
        }

        /**
         *
         * @param txt
         * The txt
         */
        public void setTxt(String txt) {
            this.txt = txt;
        }

    }



    public class Wind {

        @SerializedName("deg")
        @Expose
        private String deg;
        @SerializedName("dir")
        @Expose
        private String dir;
        @SerializedName("sc")
        @Expose
        private String sc;
        @SerializedName("spd")
        @Expose
        private String spd;

        /**
         *
         * @return
         * The deg
         */
        public String getDeg() {
            return deg;
        }

        /**
         *
         * @param deg
         * The deg
         */
        public void setDeg(String deg) {
            this.deg = deg;
        }

        /**
         *
         * @return
         * The dir
         */
        public String getDir() {
            return dir;
        }

        /**
         *
         * @param dir
         * The dir
         */
        public void setDir(String dir) {
            this.dir = dir;
        }

        /**
         *
         * @return
         * The sc
         */
        public String getSc() {
            return sc;
        }

        /**
         *
         * @param sc
         * The sc
         */
        public void setSc(String sc) {
            this.sc = sc;
        }

        /**
         *
         * @return
         * The spd
         */
        public String getSpd() {
            return spd;
        }

        /**
         *
         * @param spd
         * The spd
         */
        public void setSpd(String spd) {
            this.spd = spd;
        }

    }

    public class Wind_ {

        @SerializedName("deg")
        @Expose
        private String deg;
        @SerializedName("dir")
        @Expose
        private String dir;
        @SerializedName("sc")
        @Expose
        private String sc;
        @SerializedName("spd")
        @Expose
        private String spd;

        /**
         *
         * @return
         * The deg
         */
        public String getDeg() {
            return deg;
        }

        /**
         *
         * @param deg
         * The deg
         */
        public void setDeg(String deg) {
            this.deg = deg;
        }

        /**
         *
         * @return
         * The dir
         */
        public String getDir() {
            return dir;
        }

        /**
         *
         * @param dir
         * The dir
         */
        public void setDir(String dir) {
            this.dir = dir;
        }

        /**
         *
         * @return
         * The sc
         */
        public String getSc() {
            return sc;
        }

        /**
         *
         * @param sc
         * The sc
         */
        public void setSc(String sc) {
            this.sc = sc;
        }

        /**
         *
         * @return
         * The spd
         */
        public String getSpd() {
            return spd;
        }

        /**
         *
         * @param spd
         * The spd
         */
        public void setSpd(String spd) {
            this.spd = spd;
        }

    }

    public class Wind__ {

        @SerializedName("deg")
        @Expose
        private String deg;
        @SerializedName("dir")
        @Expose
        private String dir;
        @SerializedName("sc")
        @Expose
        private String sc;
        @SerializedName("spd")
        @Expose
        private String spd;

        /**
         *
         * @return
         * The deg
         */
        public String getDeg() {
            return deg;
        }

        /**
         *
         * @param deg
         * The deg
         */
        public void setDeg(String deg) {
            this.deg = deg;
        }

        /**
         *
         * @return
         * The dir
         */
        public String getDir() {
            return dir;
        }

        /**
         *
         * @param dir
         * The dir
         */
        public void setDir(String dir) {
            this.dir = dir;
        }

        /**
         *
         * @return
         * The sc
         */
        public String getSc() {
            return sc;
        }

        /**
         *
         * @param sc
         * The sc
         */
        public void setSc(String sc) {
            this.sc = sc;
        }

        /**
         *
         * @return
         * The spd
         */
        public String getSpd() {
            return spd;
        }

        /**
         *
         * @param spd
         * The spd
         */
        public void setSpd(String spd) {
            this.spd = spd;
        }

    }



}
