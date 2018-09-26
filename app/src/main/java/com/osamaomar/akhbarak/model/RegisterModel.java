package com.osamaomar.akhbarak.model;

public class RegisterModel {

    /**
     * code : 200
     * msg : You are already registered
     * UserId : 53
     * Name : yjhggghhj
     * FullName : ghgh
     * Phone : ghkh
     * Email : ghkhj
     * Address : ghkgh
     * Token : gkghkghk
     * TimeZone : null
     * LocationId : null
     * LocationName :
     * CityId : 1
     * CityName :
     * PhotoUrl : null
     */


    private int code;
    private String msg;
    private int UserId;
    private String Name;
    private String FullName;
    private String Phone;
    private String Email;
    private String Address;
    private String Token;
    private Object TimeZone;
    private Object LocationId;
    private String LocationName;
    private String CityId;
    private String CityName;
    private Object PhotoUrl;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    public Object getTimeZone() {
        return TimeZone;
    }

    public void setTimeZone(Object TimeZone) {
        this.TimeZone = TimeZone;
    }

    public Object getLocationId() {
        return LocationId;
    }

    public void setLocationId(Object LocationId) {
        this.LocationId = LocationId;
    }

    public String getLocationName() {
        return LocationName;
    }

    public void setLocationName(String LocationName) {
        this.LocationName = LocationName;
    }

    public String getCityId() {
        return CityId;
    }

    public void setCityId(String CityId) {
        this.CityId = CityId;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String CityName) {
        this.CityName = CityName;
    }

    public Object getPhotoUrl() {
        return PhotoUrl;
    }

    public void setPhotoUrl(Object PhotoUrl) {
        this.PhotoUrl = PhotoUrl;
    }
}
