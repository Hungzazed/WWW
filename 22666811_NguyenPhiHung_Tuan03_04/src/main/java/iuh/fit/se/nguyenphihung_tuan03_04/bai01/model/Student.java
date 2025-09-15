package iuh.fit.se.nguyenphihung_tuan03_04.bai01.model;

import java.util.List;

public class Student {
    private String firstName;
    private String lastName;
    private String dob;
    private String email;
    private String mobile;
    private String gender;
    private String address;
    private String city;
    private String pinCode;
    private String state;
    private String country;
    private List<String> hobbies;
    private String boardX;
    private String percentageX;
    private String yearX;

    private String boardXII;
    private String percentageXII;
    private String yearXII;

    // Khóa học đăng ký
    private String course;

    // Constructors
    public Student() {}

    public Student(String firstName, String lastName, String dob, String email, String mobile,
                   String gender, String address, String city, String pinCode, String state, String country,
                   List<String> hobbies, String boardX, String percentageX, String yearX,
                   String boardXII, String percentageXII, String yearXII, String course) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.pinCode = pinCode;
        this.state = state;
        this.country = country;
        this.hobbies = hobbies;
        this.boardX = boardX;
        this.percentageX = percentageX;
        this.yearX = yearX;
        this.boardXII = boardXII;
        this.percentageXII = percentageXII;
        this.yearXII = yearXII;
        this.course = course;
    }

    // Getters and Setters
    // (Có thể sinh tự động bằng IDE hoặc viết tay như mẫu dưới)

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // (Tương tự cho các thuộc tính còn lại)

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public String getBoardX() {
        return boardX;
    }

    public void setBoardX(String boardX) {
        this.boardX = boardX;
    }

    public String getPercentageX() {
        return percentageX;
    }

    public void setPercentageX(String percentageX) {
        this.percentageX = percentageX;
    }

    public String getYearX() {
        return yearX;
    }

    public void setYearX(String yearX) {
        this.yearX = yearX;
    }

    public String getBoardXII() {
        return boardXII;
    }

    public void setBoardXII(String boardXII) {
        this.boardXII = boardXII;
    }

    public String getPercentageXII() {
        return percentageXII;
    }

    public void setPercentageXII(String percentageXII) {
        this.percentageXII = percentageXII;
    }

    public String getYearXII() {
        return yearXII;
    }

    public void setYearXII(String yearXII) {
        this.yearXII = yearXII;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
