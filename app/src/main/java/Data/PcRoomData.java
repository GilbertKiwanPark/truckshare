package Data;

import java.util.ArrayList;

/**
 * Created by gilbert-mac on 2017. 9. 25..
 */

public class PcRoomData {
    String key;
    String name;
    String gu;
    String dong;
    String address;
    String change_address;
    String latitude;
    String longitude;
    String tel_number;
    String favor_game;
    boolean bool_favor_game;
    String cpu;
    String gpu;
    String ram;
    boolean bool_ssd;
    String paid_game_pay;
    ArrayList<String> paid_game_list;
    String monitor;
    String keyboard;
    String mouse;
    boolean bool_headphone;
    boolean bool_wordprocess_ms;
    boolean bool_wordprocess_hansoft;
    String phone_charge;
    String printer;
    boolean bool_scanner;
    boolean bool_parking;
    boolean bool_tv;
    ArrayList<String> foodname_list;
    ArrayList<String> foodimgurl_list;
    String speeker;
    ArrayList<String> photo_list;
    ArrayList<String> pay_policy;
    boolean bool_card_pay;
    String seats_number;
    boolean bool_couple_seat;
    boolean bool_wifi;
    String membership_pay;
    boolean bool_discount;
    ArrayList<String> discount_list;
    boolean bool_in_toilet;
    boolean bool_smoke_room;
    String facilities;
    String reservation_count;

    public PcRoomData(){}
    public PcRoomData(String key, String name, String gu, String dong, String address, String change_address, String latitude, String longitude, String tel_number, String favor_game, boolean bool_favor_game, String cpu, String gpu, String ram, boolean bool_ssd, String paid_game_pay, ArrayList<String> paid_game_list, String monitor, String keyboard, String mouse, boolean bool_headphone, boolean bool_wordprocess_ms, boolean bool_wordprocess_hansoft, String phone_charge, String printer, boolean bool_scanner, boolean bool_parking, boolean bool_tv, ArrayList<String> foodname_list, ArrayList<String> foodimgurl_list, String speeker, ArrayList<String> photo_list, ArrayList<String> pay_policy, boolean bool_card_pay, String seats_number, boolean bool_couple_seat, boolean bool_wifi, String membership_pay, boolean bool_discount, ArrayList<String> discount_list, boolean bool_in_toilet, boolean bool_smoke_room, String facilities, String reservation_count) {
        this.key = key;
        this.name = name;
        this.gu = gu;
        this.dong = dong;
        this.address = address;
        this.change_address = change_address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.tel_number = tel_number;
        this.favor_game = favor_game;
        this.bool_favor_game = bool_favor_game;
        this.cpu = cpu;
        this.gpu = gpu;
        this.ram = ram;
        this.bool_ssd = bool_ssd;
        this.paid_game_pay = paid_game_pay;
        this.paid_game_list = paid_game_list;
        this.monitor = monitor;
        this.keyboard = keyboard;
        this.mouse = mouse;
        this.bool_headphone = bool_headphone;
        this.bool_wordprocess_ms = bool_wordprocess_ms;
        this.bool_wordprocess_hansoft = bool_wordprocess_hansoft;
        this.phone_charge = phone_charge;
        this.printer = printer;
        this.bool_scanner = bool_scanner;
        this.bool_parking = bool_parking;
        this.bool_tv = bool_tv;
        this.foodname_list = foodname_list;
        this.foodimgurl_list = foodimgurl_list;
        this.speeker = speeker;
        this.photo_list = photo_list;
        this.pay_policy = pay_policy;
        this.bool_card_pay = bool_card_pay;
        this.seats_number = seats_number;
        this.bool_couple_seat = bool_couple_seat;
        this.bool_wifi = bool_wifi;
        this.membership_pay = membership_pay;
        this.bool_discount = bool_discount;
        this.discount_list = discount_list;
        this.bool_in_toilet = bool_in_toilet;
        this.bool_smoke_room = bool_smoke_room;
        this.facilities = facilities;
        this.reservation_count = reservation_count;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGu() {
        return gu;
    }

    public void setGu(String gu) {
        this.gu = gu;
    }

    public String getDong() {
        return dong;
    }

    public void setDong(String dong) {
        this.dong = dong;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getChange_address() {
        return change_address;
    }

    public void setChange_address(String change_address) {
        this.change_address = change_address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTel_number() {
        return tel_number;
    }

    public void setTel_number(String tel_number) {
        this.tel_number = tel_number;
    }

    public String getFavor_game() {
        return favor_game;
    }

    public void setFavor_game(String favor_game) {
        this.favor_game = favor_game;
    }

    public boolean isBool_favor_game() {
        return bool_favor_game;
    }

    public void setBool_favor_game(boolean bool_favor_game) {
        this.bool_favor_game = bool_favor_game;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public boolean isBool_ssd() {
        return bool_ssd;
    }

    public void setBool_ssd(boolean bool_ssd) {
        this.bool_ssd = bool_ssd;
    }

    public String getPaid_game_pay() {
        return paid_game_pay;
    }

    public void setPaid_game_pay(String paid_game_pay) {
        this.paid_game_pay = paid_game_pay;
    }

    public ArrayList<String> getPaid_game_list() {
        return paid_game_list;
    }

    public void setPaid_game_list(ArrayList<String> paid_game_list) {
        this.paid_game_list = paid_game_list;
    }

    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }

    public String getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(String keyboard) {
        this.keyboard = keyboard;
    }

    public String getMouse() {
        return mouse;
    }

    public void setMouse(String mouse) {
        this.mouse = mouse;
    }

    public boolean isBool_headphone() {
        return bool_headphone;
    }

    public void setBool_headphone(boolean bool_headphone) {
        this.bool_headphone = bool_headphone;
    }

    public boolean isBool_wordprocess_ms() {
        return bool_wordprocess_ms;
    }

    public void setBool_wordprocess_ms(boolean bool_wordprocess_ms) {
        this.bool_wordprocess_ms = bool_wordprocess_ms;
    }

    public boolean isBool_wordprocess_hansoft() {
        return bool_wordprocess_hansoft;
    }

    public void setBool_wordprocess_hansoft(boolean bool_wordprocess_hansoft) {
        this.bool_wordprocess_hansoft = bool_wordprocess_hansoft;
    }

    public String getPhone_charge() {
        return phone_charge;
    }

    public void setPhone_charge(String phone_charge) {
        this.phone_charge = phone_charge;
    }

    public String getPrinter() {
        return printer;
    }

    public void setPrinter(String printer) {
        this.printer = printer;
    }

    public boolean isBool_scanner() {
        return bool_scanner;
    }

    public void setBool_scanner(boolean bool_scanner) {
        this.bool_scanner = bool_scanner;
    }

    public boolean isBool_parking() {
        return bool_parking;
    }

    public void setBool_parking(boolean bool_parking) {
        this.bool_parking = bool_parking;
    }

    public boolean isBool_tv() {
        return bool_tv;
    }

    public void setBool_tv(boolean bool_tv) {
        this.bool_tv = bool_tv;
    }

    public ArrayList<String> getFoodname_list() {
        return foodname_list;
    }

    public void setFoodname_list(ArrayList<String> foodname_list) {
        this.foodname_list = foodname_list;
    }

    public ArrayList<String> getFoodimgurl_list() {
        return foodimgurl_list;
    }

    public void setFoodimgurl_list(ArrayList<String> foodimgurl_list) {
        this.foodimgurl_list = foodimgurl_list;
    }

    public String getSpeeker() {
        return speeker;
    }

    public void setSpeeker(String speeker) {
        this.speeker = speeker;
    }

    public ArrayList<String> getPhoto_list() {
        return photo_list;
    }

    public void setPhoto_list(ArrayList<String> photo_list) {
        this.photo_list = photo_list;
    }

    public ArrayList<String> getPay_policy() {
        return pay_policy;
    }

    public void setPay_policy(ArrayList<String> pay_policy) {
        this.pay_policy = pay_policy;
    }

    public boolean isBool_card_pay() {
        return bool_card_pay;
    }

    public void setBool_card_pay(boolean bool_card_pay) {
        this.bool_card_pay = bool_card_pay;
    }

    public String getSeats_number() {
        return seats_number;
    }

    public void setSeats_number(String seats_number) {
        this.seats_number = seats_number;
    }

    public boolean isBool_couple_seat() {
        return bool_couple_seat;
    }

    public void setBool_couple_seat(boolean bool_couple_seat) {
        this.bool_couple_seat = bool_couple_seat;
    }

    public boolean isBool_wifi() {
        return bool_wifi;
    }

    public void setBool_wifi(boolean bool_wifi) {
        this.bool_wifi = bool_wifi;
    }

    public String getMembership_pay() {
        return membership_pay;
    }

    public void setMembership_pay(String membership_pay) {
        this.membership_pay = membership_pay;
    }

    public boolean isBool_discount() {
        return bool_discount;
    }

    public void setBool_discount(boolean bool_discount) {
        this.bool_discount = bool_discount;
    }

    public ArrayList<String> getDiscount_list() {
        return discount_list;
    }

    public void setDiscount_list(ArrayList<String> discount_list) {
        this.discount_list = discount_list;
    }

    public boolean isBool_in_toilet() {
        return bool_in_toilet;
    }

    public void setBool_in_toilet(boolean bool_in_toilet) {
        this.bool_in_toilet = bool_in_toilet;
    }

    public boolean isBool_smoke_room() {
        return bool_smoke_room;
    }

    public void setBool_smoke_room(boolean bool_smoke_room) {
        this.bool_smoke_room = bool_smoke_room;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public String getReservation_count() {
        return reservation_count;
    }

    public void setReservation_count(String reservation_count) {
        this.reservation_count = reservation_count;
    }
}
