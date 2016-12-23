package app.clirnet.com.clirnetapp.models;

/**
 * Created by ${Ashish} on 8/6/2016.
 */
//model class
public class RegistrationModel {


    private String action;
    private String flag;
    private String phadded_on;
    private String id;
    private String pat_id;
    private String firstName;
    private String actualFollowupDate;
    private String added_Time;

    private String lastName;
    private String middleName;
    private String dob;
    private String mobileNumber;
    private String gender;
    private String age;
    private String language;
    private String photo;
    private String followUpDate;
    private String followUpdays;
    private String followUpWeek;
    private String followUpMonth;
    private String patient_createdDate;
    private String pres_img;
    private String clinicalNotes;
    private String ailments;
    private String key_visit_id;
    private String added_by;
    private String added_on;
    private String modified_by;


    private String modified_on;
    private String is_disabled;
    private String disabled_by;
    private String disabled_on;
    private String is_deleted;

    private String deleted_by;
    private String deleted_on;

    public String getDrugs() {
        return drugs;
    }

    public String getTests() {
        return tests;
    }

    public String getDignosis() {
        return dignosis;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public String getSugar() {
        return sugar;
    }

    public String getTemprature() {
        return temprature;
    }

    public String getMmhg() {
        return lowBp;
    }
    public String getlowBp() {
        return lowBp;
    }

    public String getBp() {
        return bp;
    }

    public String getPulse() {
        return pulse;
    }

    public String getWeight() {
        return weight;
    }

    public String getState() {
        return state;
    }

    public String getCityortown() {
        return cityortown;
    }

    public String getAddress() {
        return address;
    }

    public String getPatient_city_town() {
        return patient_city_town;
    }

    public String getPatient_info_typ() {
        return patient_info_typ;
    }

    public String getPatient_address() {
        return patient_address;
    }

    public String getPin_code() {
        return pin_code;
    }

    public String getDistrict() {
        return district;
    }

    public String getConsent() {
        return consent;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public String getDoc_mem_id() {
        return doc_mem_id;
    }

    private String special_instruction;
    private String pin_code;
    private String district;
    private String consent;
    private String doctor_id;
    private String doc_mem_id;
    private String patient_info_typ;
    private String patient_address;
    private String patient_city_town;

    private String address;
    private String cityortown;
    private String state;
    private String weight;
    private String pulse;
    private String bp;
    private String lowBp;
    private String temprature;
    private String sugar;
    private String symptoms;
    private String dignosis;
    private String tests;
    private String drugs;


    public String getAction() {
        return action;
    }

    public String getActualFollowupDate() {
        return actualFollowupDate;
    }

    public RegistrationModel(String id) {
        this.pat_id = id;
    }

    public RegistrationModel(String id, String visitId) {
        this.pat_id = id;
        this.key_visit_id = visitId;
    }


    public String getPat_id() {
        return pat_id;
    }


    public String getKey_visit_id() {
        return key_visit_id;
    }

    public String getVisit_date() {
        return visit_date;
    }


    private String visit_date;


    public String getAdded_on() {
        return added_on;
    }


    //to get data from db 123

    public RegistrationModel(String id, String first_name, String middle_name, String last_name, String strdate_of_birth, String current_age, String phone_number, String gender, String selectedLanguage, String patientImagePath, String usersellectedDate, String daysSel, String weekSel, String monthSel, String ailmentList, String prescriptionImgPath, String clinical_note, String added_on, String visit_date, String modified_on) {
        this.pat_id = id;
        this.firstName = first_name;
        this.middleName = middle_name;
        this.lastName = last_name;
        this.dob = strdate_of_birth;
        this.gender = gender;

        this.age = current_age;
        this.mobileNumber = phone_number;
        this.language = selectedLanguage;
        this.photo = patientImagePath;
        this.followUpDate = usersellectedDate;

        this.followUpdays = daysSel;
        this.followUpWeek = weekSel;
        this.followUpMonth = monthSel;
        this.ailments = ailmentList;
        this.pres_img = prescriptionImgPath;
        this.clinicalNotes = clinical_note;
        this.added_on = added_on;
        this.visit_date = visit_date;
        this.modified_on = modified_on;


    }


    public String getClinicalNotes() {
        return clinicalNotes;
    }


    public String getAilments() {

        return ailments;
    }

    public void setAilments(String ailments) {
        this.ailments = ailments;
    }


    public String getPres_img() {
        return pres_img;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //This is used to set values to model class
    public RegistrationModel(String first_name, String middle_name, String last_name, String sex, String strdate_of_birth, String current_age, String phone_number) {//, String language, String s, String s1, String s2, String s3, String s4, String ailments, String s5, String s6) {


        this.firstName = first_name;
        this.middleName = middle_name;
        this.lastName = last_name;
        this.dob = strdate_of_birth;
        this.gender = sex;

        this.age = current_age;
        this.mobileNumber = phone_number;
        //  this.patient_createdDate=date_Patientcreated;


    }

    //This constructor for get the result from db
    public RegistrationModel(String id, String first_name, String middle_name, String last_name, String sex, String strdate_of_birth, String current_age, String phone_number) {//, String language, String s, String s1, String s2, String s3, String s4, String ailments, String s5, String s6) {

        this.id = id;
        this.firstName = first_name;
        this.middleName = middle_name;
        this.lastName = last_name;
        this.dob = strdate_of_birth;
        this.gender = sex;
        this.age = current_age;
        this.mobileNumber = phone_number;


    }


    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMiddleName() {
        return middleName;
    }


    public String getMobileNumber() {
        return mobileNumber;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPhoto() {
        return photo;
    }


    public String getFollowUpdays() {
        return followUpdays;
    }


    public String getFollowUpDate() {
        return followUpDate;
    }


    public String getFollowUpWeek() {
        return followUpWeek;
    }


    public String getFollowUpMonth() {
        return followUpMonth;
    }


    public RegistrationModel(String pat_id, String doctor_id, String doc_membership_id, String patient_info_type_form, String pat_first_name,
                             String pat_middle_name, String pat_last_name, String pat_gender, String pat_date_of_birth,
                             String pat_age, String pat_mobile_no, String pat_address, String pat_city_town, String pat_pincode,
                             String pat_district, String pref_lang, String photo_name, String consent, String special_instruction,
                             String added_by, String added_on, String addedTime, String modified_by, String modified_on, String is_disabled,
                             String disabled_by, String disabled_on, String is_deleted, String deleted_by, String deleted_on, String flag) {

        this.pat_id = pat_id;
        this.doctor_id = doctor_id;
        this.doc_mem_id = doc_membership_id;
        this.patient_info_typ = patient_info_type_form;
        this.firstName = pat_first_name;
        this.middleName = pat_middle_name;
        this.lastName = pat_last_name;
        this.gender = pat_gender;
        this.dob = pat_date_of_birth;
        this.age = pat_age;
        this.mobileNumber = pat_mobile_no;
        this.patient_address = pat_address;
        this.patient_city_town = pat_city_town;
        this.pin_code = pat_pincode;
        this.district = pat_district;
        this.language = pref_lang;
        this.photo = photo_name;
        this.consent = consent;
        this.special_instruction = special_instruction;
        this.added_by = added_by;
        this.added_on = added_on;
        this.added_Time = addedTime;
        this.modified_by = modified_by;
        this.modified_on = modified_on;
        this.is_disabled = is_disabled;
        this.disabled_by = disabled_by;
        this.disabled_on = disabled_on;
        this.is_deleted = is_deleted;
        this.deleted_by = deleted_by;
        this.deleted_on = deleted_on;
        this.flag = deleted_on;

    }

    public RegistrationModel(String pat_id, String ailment, String visit_date, String fod, String clinicalNotes, String actualFod) {
        this.pat_id = pat_id;
        this.ailments = ailment;
        this.visit_date = visit_date;

        this.clinicalNotes = clinicalNotes;
        this.followUpDate = fod;
        this.actualFollowupDate = actualFod;
    }

    public RegistrationModel(String id, String first_name, String middle_name, String last_name, String strdate_of_birth, String current_age, String phone_number, String gender, String selectedLanguage, String patientImagePath, String follow_up_date, String daysSel, String weekSel, String monthSel, String ailmentList, String prescriptionImgPath, String clinical_note, String added_on, String visit_date, String modified_on, String key_visit_id, String actFolDate) {

        this.pat_id = id;
        this.firstName = first_name;
        this.middleName = middle_name;
        this.lastName = last_name;
        this.dob = strdate_of_birth;
        this.gender = gender;

        this.age = current_age;
        this.mobileNumber = phone_number;
        this.language = selectedLanguage;
        this.photo = patientImagePath;
        this.followUpDate = follow_up_date;

        this.followUpdays = daysSel;
        this.followUpWeek = weekSel;
        this.followUpMonth = monthSel;
        this.ailments = ailmentList;
        this.pres_img = prescriptionImgPath;
        this.clinicalNotes = clinical_note;
        this.added_on = added_on;
        this.visit_date = visit_date;
        this.modified_on = modified_on;
        this.key_visit_id = key_visit_id;
        this.actualFollowupDate = actFolDate;
    }

    public RegistrationModel(String id, String first_name, String middle_name, String last_name, String strdate_of_birth, String gender, String current_age, String phone_number, String selectedLanguage, String patientImagePath, String usersellectedDate, String daysSel, String monthSel, String weekSel, String ailmentList, String prescriptionImgPath, String clinical_note, String added_on, String modified_on, String actfod, String action) {
        this.pat_id = id;
        this.firstName = first_name;
        this.middleName = middle_name;
        this.lastName = last_name;
        this.dob = strdate_of_birth;
        this.gender = gender;

        this.age = current_age;
        this.mobileNumber = phone_number;
        this.language = selectedLanguage;
        this.photo = patientImagePath;
        this.followUpDate = usersellectedDate;

        this.followUpdays = daysSel;
        this.followUpWeek = weekSel;
        this.followUpMonth = monthSel;
        this.ailments = ailmentList;
        this.pres_img = prescriptionImgPath;
        this.clinicalNotes = clinical_note;
        this.added_on = added_on;
        this.visit_date = visit_date;
        this.modified_on = modified_on;
        this.actualFollowupDate = actfod;
        this.action = action;

    }

    public RegistrationModel(String id, String first_name, String middle_name, String last_name, String strdate_of_birth, String current_age, String phone_number, String gender, String selectedLanguage, String patientImagePath, String follow_up_date, String daysSel, String weekSel, String monthSel, String ailmentList, String prescriptionImgPath, String clinical_note, String added_on, String visit_date, String modified_on, String key_visit_id, String actFolDate,
                             String address, String city, String district, String pin, String state, String weight, String pulse, String bp, String mmhg, String temprature,
                             String sugar, String symptoms, String dignosis, String tests, String drugs) {
        this.pat_id = id;
        this.firstName = first_name;
        this.middleName = middle_name;
        this.lastName = last_name;
        this.dob = strdate_of_birth;
        this.gender = gender;

        this.age = current_age;
        this.mobileNumber = phone_number;
        this.language = selectedLanguage;
        this.photo = patientImagePath;
        this.followUpDate = follow_up_date;

        this.followUpdays = daysSel;
        this.followUpWeek = weekSel;
        this.followUpMonth = monthSel;
        this.ailments = ailmentList;
        this.pres_img = prescriptionImgPath;
        this.clinicalNotes = clinical_note;
        this.added_on = added_on;
        this.visit_date = visit_date;
        this.modified_on = modified_on;
        this.key_visit_id = key_visit_id;
        this.actualFollowupDate = actFolDate;

        this.address = address;
        this.cityortown = city;
        this.district = district;
        this.pin_code = pin;
        this.state = state;

        this.weight=weight;
        this.pulse = pulse;
        this.bp = bp;
        this.lowBp = mmhg;
        this.temprature = temprature;
        this.sugar = sugar;
        this.symptoms = symptoms;
        this.dignosis = dignosis;
        this.tests = tests;
        this.drugs = drugs;


    }

    public RegistrationModel(String id, String first_name, String middle_name, String last_name, String strdate_of_birth, String current_age, String phone_number, String gender, String selectedLanguage, String patientImagePath, String follow_up_date, String daysSel, String weekSel, String monthSel, String ailmentList, String prescriptionImgPath, String clinical_note, String added_on, String visit_date, String modified_on, String key_visit_id, String actFolDate,
                             String address, String city, String district, String pin, String state) {
        this.pat_id = id;
        this.firstName = first_name;
        this.middleName = middle_name;
        this.lastName = last_name;
        this.dob = strdate_of_birth;
        this.gender = gender;

        this.age = current_age;
        this.mobileNumber = phone_number;
        this.language = selectedLanguage;
        this.photo = patientImagePath;
        this.followUpDate = follow_up_date;

        this.followUpdays = daysSel;
        this.followUpWeek = weekSel;
        this.followUpMonth = monthSel;
        this.ailments = ailmentList;
        this.pres_img = prescriptionImgPath;
        this.clinicalNotes = clinical_note;
        this.added_on = added_on;
        this.visit_date = visit_date;
        this.modified_on = modified_on;
        this.key_visit_id = key_visit_id;
        this.actualFollowupDate = actFolDate;
        this.address = address;
        this.cityortown = city;
        this.district = district;
        this.pin_code = pin;
        this.state = state;

    }
}
