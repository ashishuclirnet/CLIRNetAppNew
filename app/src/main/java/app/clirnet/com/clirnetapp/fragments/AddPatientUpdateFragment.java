package app.clirnet.com.clirnetapp.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.view.ContextThemeWrapper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.tokenautocomplete.TokenCompleteTextView;

import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import app.clirnet.com.clirnetapp.R;
import app.clirnet.com.clirnetapp.activity.NavigationActivity;
import app.clirnet.com.clirnetapp.app.AppController;
import app.clirnet.com.clirnetapp.helper.BannerClass;
import app.clirnet.com.clirnetapp.helper.ClirNetAppException;
import app.clirnet.com.clirnetapp.helper.DatabaseClass;
import app.clirnet.com.clirnetapp.helper.LastnameDatabaseClass;
import app.clirnet.com.clirnetapp.helper.SQLController;
import app.clirnet.com.clirnetapp.helper.SQLiteHandler;
import app.clirnet.com.clirnetapp.utility.ContactsCompletionView;
import app.clirnet.com.clirnetapp.utility.ImageCompression;
import butterknife.ButterKnife;
import butterknife.Bind;

//Our class extending fragment
public class AddPatientUpdateFragment extends Fragment {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1888;
    private static final int DATE_DIALOG_ID1 = 1;
    private static final int DATE_DIALOG_ID2 = 2;
    private Button addUpdate;
    private Button cancel;
    private ContactsCompletionView edtSymptoms;
    private ContactsCompletionView edtDignosis;
    private BootstrapEditText fodtextshow;
    private Button days;
    private Button week;
    private Button month;
    private BootstrapEditText inputnumber;
    private Button btnclear;
    private EditText clinicalNotes;

    private String strPatientId;
    private String fowSel;
    private String daysSel;
    private String userSellectedDate;
    private String monthSel;
    private int maxVisitId;
    private String doctor_membership_number;
    private String docId;
    private SQLController sqlController;
    private SQLiteHandler dbController;
    private BannerClass bannerClass;
    Button addPatientprescriptionBtn;
    private Intent imageIntent;
    private File imagesFolder, imagePathFile;
    private String PrescriptionimageName;
    private AppController appController;
    private Uri uriSavedImage;
    private String prescriptionImgPath;
    private TextView edtprescriptionImgPath;
    private LastnameDatabaseClass lastNamedb;
    private ArrayList<String> mSymptomsList;
    private ArrayList<String> mDiagnosisList;
    private String value;

    private SimpleDateFormat sdf1;
    private EditText visitDate;
    private String addedTime;
    private String addedOnDate;
    private ArrayList<String> specialityArray;
    private HashMap<String, String> NameData;
    private String strReferredByName;
    private String strReferredTo1Name;
    private String strReferredTo2Name;
    private String strReferredTo3Name;
    private String strReferredTo4Name;
    private String strReferredTo5Name;
    private String strReferedTo;
    private String strReferedBy;
    private int addCounter;
    private TextView textRefredByShow;
    private TextView textRefredToShow;

    private EditText edtInput_weight;
    private EditText edtInput_pulse;
    private EditText edtInput_bp;
    private EditText edtLowBp;
    private EditText edtInput_temp;

    private EditText edtInput_bmi;
    private EditText edtInput_height;


    @Bind(R.id.showObservations)
    LinearLayout showObservationsDataLayout;
    @Bind(R.id.showVitals)
    LinearLayout showVitalsDataLayout;
    @Bind(R.id.showInvestigation)
    LinearLayout showshowInvestigationDataLayout;

    private String strWeight;
    private String strPulse;
    private String strBp;
    private String strLowBp;
    private String strTemp;
    private String strHeight;
    private String strBmi;
    private String strSugarFasting;
    private DatabaseClass databaseClass;
    private LinearLayout showReferrals;
    private TextView txtReferredTo, txtReferredBy;

    private ViewPager viewPager;
    private String strEcg;
    private String strPft;
    private String strLipidTC;
    private String strLipidTG;
    private String strLipidLDL;
    private String strLipidVHDL;
    private String strLipidHDL;
    private String strSugar;
    private String strHbA1c;
    private String strSerumUrea;
    private String strAcer;
    private Button buttonInvestigation;
    private String strVisitId;
    private TextView showInvestigationData;
    private TextView showVitalsData;

    private StringBuilder sbVitals = new StringBuilder();
    private StringBuilder sbObservations = new StringBuilder();
    private StringBuilder sbInvestigations = new StringBuilder();
    private String strPatientFollowUpStatus;

    private Button buttonObservations;
    private String strPallorDescription;
    private String strCyanosisDescription;
    private String strTremorsDescription;
    private String strIcterusDescription;
    private String strClubbingDescription;
    private String strOedemaDescription;
    private String strCalfTendernessDescription;
    private String strLymphadenopathyDescription;
    private EditText edtInput_spo2;
    private EditText edtInput_respiration_rate;
    private String strSpo2, strRespirationRate;
    private String strPallore;
    private String strCyanosis, strTremors, strIcterus, strClubbing, strOedema, strCalfTenderness, strLymphadenopathy;
    private TextView showObservationsData;
    private EditText ediInput_obesity;
    private String strObesity;

    private int counttxthemogram = 1;
    private int counttxtLiverFunctionTest = 1;
    private int counttxtLipidProfile = 1;
    private int counttxtDiabeticProfile = 1;
    private int counttxtUrineRoutineExamination = 1;
    private int counttxtRft = 1;
    private int counttxtThyroidProfile = 1;


    private LinearLayout hemogramlayout;
    private LinearLayout liverFunctionlayout;
    private LinearLayout lipidProfilelayout;
    private LinearLayout diabeticlayout;
    private LinearLayout urineRoutineExaminationlayout;
    private LinearLayout rftlayout;
    private LinearLayout thyroidProfilelayout;


    private TextView txthemogram;
    private TextView txtLiverFunctionTest;
    private TextView txtLipidProfile;
    private TextView txtDiabeticProfile;
    private TextView txtUrineRoutineExamination;
    private TextView txtRft;
    private TextView txtThyroidProfile;
    private Button refered;
    private Button buttonVital;
    private ArrayList<String> nameReferralsList;
    private ArrayAdapter<String> referralName;
    private boolean fodChecked;
   /* private String strHb;
    private String strPlateletCount;
    private String strEsr;
    private String strDcl;
    private String strDcn;
    private String strDce;
    private String strDcm;
    private String strDcb;
    private String strTotalBiliubin;
    private String strDirectBilirubin;
    private String strIndirectBilirubin;
    private String strSgpt;
    private String strGgt;
    private String strTotalProtein;
    private String strAlbumin;
    private String strGlobulin;
    private String strAgRatio;
    private String strLdlHdlRatio;
    private String strSugarRbs;
    private String strUrinaryPusCell;
    private String strUrineRbc;
    private String strUrinaryCast;
    private String strUrineProtein;
    private String strUrineCrystal;
    private String strMicroalbuminuria;
    private String strSerumCreatinine;
    private String strTsh;
    private String strT3;
    private String strT4;
    private String strAcr;
    private String strSgot;
    private String strTch;*/


    public AddPatientUpdateFragment() {

    }

    public static AddPatientUpdateFragment newInstance(ViewPager viewPager, String strPatientId) {
        AddPatientUpdateFragment fragment = new AddPatientUpdateFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            strPatientId = getArguments().getString("PATIENTID");
            Log.e("strPatientId",""+strPatientId);
        }
    }

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_patient_update_fragment, container, false);

        ButterKnife.bind(this, view);

        cancel = (Button) view.findViewById(R.id.cancel);
        addUpdate = (Button) view.findViewById(R.id.addUpdate);
        edtSymptoms = (ContactsCompletionView) view.findViewById(R.id.symptoms);
        edtDignosis = (ContactsCompletionView) view.findViewById(R.id.dignosis);
        fodtextshow = (BootstrapEditText) view.findViewById(R.id.fodtextshow);
        inputnumber = (BootstrapEditText) view.findViewById(R.id.inputnumber);
        days = (Button) view.findViewById(R.id.days);
        week = (Button) view.findViewById(R.id.week);
        month = (Button) view.findViewById(R.id.month);
        btnclear = (Button) view.findViewById(R.id.btnclear);
        clinicalNotes = (EditText) view.findViewById(R.id.clinicalNotes);

        addPatientprescriptionBtn = (Button) view.findViewById(R.id.addPatientprescriptionBtn);
        edtprescriptionImgPath = (TextView) view.findViewById(R.id.prescriptionImgPath);
        visitDate = (EditText) view.findViewById(R.id.visitDate);

        textRefredByShow = (TextView) view.findViewById(R.id.txtrefredby);
        textRefredToShow = (TextView) view.findViewById(R.id.txtrefredto);
        refered = (Button) view.findViewById(R.id.buttonReferrals);
        buttonVital = (Button) view.findViewById(R.id.buttonVital);
        Button buttonHistory = (Button) view.findViewById(R.id.buttonHistory);
        buttonHistory.setVisibility(View.GONE);
        showReferrals = (LinearLayout) view.findViewById(R.id.showReferrals);
        buttonInvestigation = (Button) view.findViewById(R.id.buttonInvestigation);
        buttonObservations = (Button) view.findViewById(R.id.buttonObservations);

        txtReferredBy = (TextView) view.findViewById(R.id.txtReferredBy);
        txtReferredTo = (TextView) view.findViewById(R.id.txtReferredTo);


        showInvestigationData = (TextView) view.findViewById(R.id.showInvestigationData);
        showObservationsData = (TextView) view.findViewById(R.id.showObservationsData);
        showVitalsData = (TextView) view.findViewById(R.id.showVitalsData);

        sdf1 = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

        refered.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {

                    showReferedDialogBox();
                    refered.setBackground(getResources().getDrawable(R.drawable.rounded_corner_withbackground));

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    refered.setBackground(getResources().getDrawable(R.drawable.rounded_corner_withbackground_blue));
                }
                return false;
            }

        });
        buttonVital.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {

                    addVitalsDialog();
                    buttonVital.setBackground(getResources().getDrawable(R.drawable.rounded_corner_withbackground));

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    buttonVital.setBackground(getResources().getDrawable(R.drawable.rounded_corner_withbackground_blue));
                }
                return false;
            }

        });


        buttonHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
            }
        });

        try {
            if (sqlController == null) {
                sqlController = new SQLController(getContext());
                sqlController.open();
            }
            appController = new AppController();
            dbController = SQLiteHandler.getInstance(getContext().getApplicationContext());
            //This will get all the visit  history of patient
            doctor_membership_number = sqlController.getDoctorMembershipIdNew();
            docId = sqlController.getDoctorId();
            maxVisitId = sqlController.getPatientVisitIdCount();
            strVisitId = String.valueOf(maxVisitId + 1);

            //investigationDeatilsList = sqlController.getInvestigationDeatils(strVisitId);


            if (bannerClass == null) {
                bannerClass = new BannerClass(getContext().getApplicationContext());
            }
            if (lastNamedb == null) {
                lastNamedb = new LastnameDatabaseClass(getContext().getApplicationContext());
            }
            if (databaseClass == null) {
                databaseClass = new DatabaseClass(getContext().getApplicationContext());
            }

        } catch (ClirNetAppException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (sqlController != null) {
                sqlController.close();
            }
        }

        cancel.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {

                    cancel.setBackground(getResources().getDrawable(R.drawable.rounded_corner_withbackground));
                    goToNavigation();

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    cancel.setBackground(getResources().getDrawable(R.drawable.rounded_corner_withbackground_blue));
                }
                return false;
            }

        });

        CheckBox checkBoxFollowUp = (CheckBox) view.findViewById(R.id.checkBoxFollowUp);

        checkBoxFollowUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chk checked?
                if (((CheckBox) v).isChecked()) {

                    strPatientFollowUpStatus = "FollowUp";
                }
            }
        });

        SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
        Date todayDate3 = new Date();


        //this date is ued to set update records date in patient history table
        addedTime = sdf3.format(todayDate3);

        //This will give date format in 2-9-2016 format
        final Calendar c = Calendar.getInstance();
        int year1 = c.get(Calendar.YEAR);
        int month1 = c.get(Calendar.MONTH);
        int day1 = c.get(Calendar.DAY_OF_MONTH);

        addedOnDate = String.valueOf(new StringBuilder().append(day1).append("-").append(month1 + 1).append("-").append(year1).append(""));
        visitDate.setText(addedOnDate);
        addPrescription();
        addSymptomsAndDisgnosis();
        addFollowupdateButtonListner();

        addUpdate.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {

                    addUpdate.setBackground(getResources().getDrawable(R.drawable.rounded_corner_withbackground));

                    saveDataToDataBase();//saved data int db

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    addUpdate.setBackground(getResources().getDrawable(R.drawable.rounded_corner_withbackground_blue));
                }
                return false;
            }

        });
        buttonInvestigation.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {

                    buttonInvestigation.setBackground(getResources().getDrawable(R.drawable.rounded_corner_withbackground));

                    showInvestigationDialog();//Open Investigation Dialog
                    //setOnClickListner();

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    buttonInvestigation.setBackground(getResources().getDrawable(R.drawable.rounded_corner_withbackground_blue));
                }
                return false;
            }

        });
        buttonObservations.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {

                    buttonObservations.setBackground(getResources().getDrawable(R.drawable.rounded_corner_withbackground));
                    showObservationsDialog();

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    buttonObservations.setBackground(getResources().getDrawable(R.drawable.rounded_corner_withbackground_blue));
                }
                return false;
            }

        });

        return view;
    }


    private void addSymptomsAndDisgnosis() {
        try {
            mSymptomsList = lastNamedb.getSymptoms();
            if (mSymptomsList.size() > 0) {
                ArrayAdapter<String> lastnamespin = new ArrayAdapter<>(getContext(),
                        android.R.layout.simple_dropdown_item_1line, mSymptomsList);

                edtSymptoms.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
                lastnamespin.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                edtSymptoms.setThreshold(1);
                edtSymptoms.setAdapter(lastnamespin);
                edtSymptoms.setTokenClickStyle(TokenCompleteTextView.TokenClickStyle.Delete);
                edtSymptoms.allowDuplicates(false);
            }
        } catch (ClirNetAppException e) {
            e.printStackTrace();
            appController.appendLog(appController.getDateTime() + " " + "/ " + " AddPatientUpdate" + e + " Line Number: " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        try {
            mDiagnosisList = lastNamedb.getDiagnosis();
            if (mDiagnosisList.size() > 0) {
                ArrayAdapter<String> lastnamespin = new ArrayAdapter<>(getContext(),
                        android.R.layout.simple_dropdown_item_1line, mDiagnosisList);

                edtDignosis.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
                lastnamespin.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                edtDignosis.setThreshold(1);
                edtDignosis.setAdapter(lastnamespin);
                edtDignosis.setTokenClickStyle(TokenCompleteTextView.TokenClickStyle.Delete);
                edtDignosis.allowDuplicates(false);
            }
        } catch (ClirNetAppException e) {
            e.printStackTrace();
            appController.appendLog(appController.getDateTime() + " " + "/ " + " AddPatientUpdate" + e + " Line Number: " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void addPrescription() {
        addPatientprescriptionBtn.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    addPatientprescriptionBtn.setBackgroundColor(getResources().getColor(R.color.bg_login));

                    imageIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    imagesFolder = new File(Environment.getExternalStorageDirectory(), "PatientsImages");
                    imagesFolder.mkdirs();

                    PrescriptionimageName = "prescription_" + docId + "_" + appController.getDateTime() + ".jpg";
                    imagePathFile = new File(imagesFolder, PrescriptionimageName);
                    uriSavedImage = Uri.fromFile(imagePathFile);
                    imageIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
                    imageIntent.putExtra("data", uriSavedImage);
                    startActivityForResult(imageIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    addPatientprescriptionBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
                return false;
            }

        });

    }

    //Image capture code
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {

                if (resultCode == Activity.RESULT_OK) {
                    // successfully captured the image
                    // display it in image view
                    new ImageCompression(getContext(), imagePathFile.getPath()).execute(uriSavedImage.getPath().trim());
                    previewCapturedImage();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            appController.appendLog(appController.getDateTime() + " " + "/ " + "Add Patient" + e + " Line Number: " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    //this will show captured image to image view
    private void previewCapturedImage() {
        try {
            prescriptionImgPath = uriSavedImage.getPath();


            edtprescriptionImgPath.setVisibility(View.VISIBLE);
            edtprescriptionImgPath.setText(prescriptionImgPath);
        } catch (NullPointerException e) {
            e.printStackTrace();
            appController.appendLog(appController.getDateTime() + " " + "/ " + "AddPatientUpdate" + e + " Line Number: " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void addFollowupdateButtonListner() {

        days.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    days.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                    week.setTextColor(getResources().getColor(R.color.black));
                    month.setTextColor(getResources().getColor(R.color.black));
                    days.setTextColor(getResources().getColor(R.color.white));
                    btnclear.setTextColor(getResources().getColor(R.color.black));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        week.setBackground(getResources().getDrawable(R.drawable.circle));
                        month.setBackground(getResources().getDrawable(R.drawable.circle));
                        btnclear.setBackground(getResources().getDrawable(R.drawable.circle));
                    }

                    value = inputnumber.getText().toString().trim();

                    if (TextUtils.isEmpty(value)) {
                        inputnumber.setError(getResources().getString(R.string.foderror));
                        return true;
                    }


                    int val = Integer.parseInt(value);

                    int days = Integer.parseInt(value);
                    //
                    if (days > 366) {
                        inputnumber.setError("Enter up to 366 Days");
                        return true;
                    } else {
                        inputnumber.setError(null);
                    }

                    String strVisitDate = visitDate.getText().toString();
                    Date calDate = appController.stringToDate(strVisitDate);
                    String dateis = sdf1.format(AppController.addDay1(calDate, val));
                    fodtextshow.setText(dateis);
                    daysSel = value;
                    fowSel = null;
                    monthSel = null;
                    fodChecked = true;
                    userSellectedDate = dateis;

                } else if (event.getAction() == KeyEvent.ACTION_UP) {
                    days.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        week.setBackground(getResources().getDrawable(R.drawable.circle));
                        month.setBackground(getResources().getDrawable(R.drawable.circle));
                    }
                }
                return false;
            }
        });

        week.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                week.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                week.setTextColor(getResources().getColor(R.color.white));
                month.setTextColor(getResources().getColor(R.color.black));
                days.setTextColor(getResources().getColor(R.color.black));
                btnclear.setTextColor(getResources().getColor(R.color.black));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    days.setBackground(getResources().getDrawable(R.drawable.circle));
                    month.setBackground(getResources().getDrawable(R.drawable.circle));
                    btnclear.setBackground(getResources().getDrawable(R.drawable.circle));
                }

                value = inputnumber.getText().toString().trim();

                if (TextUtils.isEmpty(value)) {
                    inputnumber.setError(getResources().getString(R.string.foderror));
                    return;
                }
                long val = Long.parseLong(value);

                if (value != null) {

                    if (val > 54) {
                        inputnumber.setError("Enter up to 54 Weeks");
                        return;
                    } else {
                        inputnumber.setError(null);
                    }
                }
                int fVal = (int) (val * 7);

                String strVisitDate = visitDate.getText().toString();
                Date calDate = appController.stringToDate(strVisitDate);
                String dateis = sdf1.format(AppController.addDay1(calDate, fVal));
                userSellectedDate = dateis;
                fowSel = value;
                daysSel = null;
                monthSel = null;
                fodChecked = true;
                fodtextshow.setText(dateis);
            }
        });

        month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                month.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                week.setTextColor(getResources().getColor(R.color.black));
                month.setTextColor(getResources().getColor(R.color.white));
                days.setTextColor(getResources().getColor(R.color.black));
                btnclear.setTextColor(getResources().getColor(R.color.black));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    days.setBackground(getResources().getDrawable(R.drawable.circle));
                    week.setBackground(getResources().getDrawable(R.drawable.circle));
                    btnclear.setBackground(getResources().getDrawable(R.drawable.circle));
                }

                value = inputnumber.getText().toString().trim();

                if (TextUtils.isEmpty(value)) {
                    inputnumber.setError(getResources().getString(R.string.foderror));
                    return;
                }
                long val = Long.parseLong(value);
                if (val > 12) {
                    inputnumber.setError("Enter up to 12 Months");
                    return;
                } else {
                    inputnumber.setError(null);
                }

                String strVisitDate = visitDate.getText().toString();
                Date calDate = appController.stringToDate(strVisitDate);
                String dateis = sdf1.format(AppController.addMonth(calDate, Integer.parseInt(value)));
                userSellectedDate = dateis;
                monthSel = value;
                daysSel = null;
                fowSel = null;
                fodChecked = true;
                fodtextshow.setText(dateis);
            }
        });

        btnclear.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {

                    btnclear.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.MULTIPLY);
                    btnclear.setTextColor(getResources().getColor(R.color.colorPrimary));
                    inputnumber.setText("");
                    fodtextshow.setText("");
                    daysSel = null;
                    fowSel = null;
                    monthSel = null;
                    fodChecked = false;
                    CleanFollowup();

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    btnclear.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                    btnclear.setTextColor(getResources().getColor(R.color.white));
                }
                return false;
            }

        });

        visitDate.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                shpwDialog(DATE_DIALOG_ID1);

            }
        });
        fodtextshow.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                shpwDialog(DATE_DIALOG_ID2);

            }
        });
    }

    private void shpwDialog(int id) {

        final Calendar c1 = Calendar.getInstance();
        int mYear1 = c1.get(Calendar.YEAR);
        int mMonth1 = c1.get(Calendar.MONTH);
        int mDay1 = c1.get(Calendar.DAY_OF_MONTH);
        switch (id) {

            case DATE_DIALOG_ID1: //for visit date

                DatePickerDialog dpd2 = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                visitDate.setText(dayOfMonth + "-"
                                        + (monthOfYear + 1) + "-" + year);


                            }
                        }, mYear1, mMonth1, mDay1);
                c1.add(Calendar.DATE, 0);

                Date newDate = c1.getTime();
                dpd2.getDatePicker().setMaxDate(newDate.getTime());

                dpd2.show();

                break;

            case DATE_DIALOG_ID2: //for fod

                DatePickerDialog dpd3 = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {


                                fodtextshow.setText(dayOfMonth + "-"
                                        + (monthOfYear + 1) + "-" + year);
                               /* showfodtext.setText(dayOfMonth + "-"
                                        + (monthOfYear + 1) + "-" + year);*/
                                inputnumber.setText("");
                                CleanFollowup();

                            }
                        }, mYear1, mMonth1, mDay1);
                c1.add(Calendar.DATE, 1);

                Date newDate3 = c1.getTime();
                dpd3.getDatePicker().setMinDate(newDate3.getTime());

                dpd3.show();

                break;
        }
    }

    private void CleanFollowup() {

        month.setTextColor(getResources().getColor(R.color.black));
        days.setTextColor(getResources().getColor(R.color.black));
        week.setTextColor(getResources().getColor(R.color.black));
        inputnumber.setText("");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            days.setBackground(getResources().getDrawable(R.drawable.circle));
            month.setBackground(getResources().getDrawable(R.drawable.circle));
            week.setBackground(getResources().getDrawable(R.drawable.circle));
        }
    }

    private void goToNavigation() {
        Intent i = new Intent(getContext(), NavigationActivity.class);

        i.putExtra("ACTION_PATH", "HomeFragment");
        i.putExtra("HEADER", "goto");//dummy text to activity
        // i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);  // it will directly jump to navigation activity with called fragment

        startActivity(i);
        getActivity().finish();
    }

    private void showReferedDialogBox() {

        final Dialog dialog = new Dialog(getContext());
        LayoutInflater factory = LayoutInflater.from(getContext());

        final View f = factory.inflate(R.layout.refered_by_dialog, null);


        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(f);

        dialog.setTitle("Referrals");
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(f);
        Button dialogButtonCancel = (Button) f.findViewById(R.id.customDialogCancel);
        Button dialogButtonOk = (Button) f.findViewById(R.id.customDialogOk);
        final Button addMore = (Button) f.findViewById(R.id.addMore);

        final Spinner nameRefredTo2Spinner = (Spinner) f.findViewById(R.id.nameRefredTo2Spinner);

        specialityArray = new ArrayList<>();

        try {
            final ArrayList<HashMap<String, String>> list = sqlController.getAllDataAssociateMaster();

            NameData = new HashMap<>();

            for (int im = 0; im < list.size(); im++) {
                String strid = list.get(im).get("ID");
                String strName = list.get(im).get("NAME");
                String str = list.get(im).get("SPECIALITY");
                specialityArray.add(str);
                NameData.put(strName, strid);
            }

            setSpinner(f);//passsing view to method

        } catch (ClirNetAppException e) {
            e.printStackTrace();
            appController.appendLog(appController.getDateTime() + " " + "/ " + "Add Patient Update" + e + " Line Number: " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        // Click cancel to dismiss android custom dialog box
        dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCounter = 0;
                dialog.dismiss();

            }
        });

        dialogButtonOk.setOnClickListener(new View.OnClickListener() {

            String code;


            @Override
            public void onClick(View v) {

                StringBuilder sb = new StringBuilder();
                StringBuilder sbname = new StringBuilder();
                if (addCounter >= 0) {
                    if (strReferredTo1Name != null && !strReferredTo1Name.equals("") && strReferredTo1Name.length() > 0) {
                        code = NameData.get(strReferredTo1Name.trim());

                        if (code != null) {
                            int index = Integer.parseInt(code);
                            sb.append(index);
                            sbname.append(strReferredTo1Name);
                        }
                    }
                }
                if (addCounter >= 1) {

                    if (strReferredTo2Name != null && !strReferredTo2Name.equals("") && strReferredTo2Name.length() > 0) {
                        code = NameData.get(strReferredTo2Name.trim());
                        if (code != null) {

                            int index = Integer.parseInt(code);
                            sb.append(",").append(index);
                            sbname.append(",").append(strReferredTo2Name);
                        }
                    }
                }
                if (addCounter >= 2) {

                    if (strReferredTo3Name != null && !strReferredTo3Name.equals("") && strReferredTo3Name.length() > 0) {
                        code = NameData.get(strReferredTo3Name.trim());
                        if (code != null) {
                            int index = Integer.parseInt(code);
                            sb.append(",").append(index);
                            sbname.append(",").append(strReferredTo3Name);
                        }
                    }
                }
                if (addCounter >= 3) {

                    if (strReferredTo4Name != null && !strReferredTo4Name.equals("") && strReferredTo4Name.length() > 0) {
                        code = NameData.get(strReferredTo4Name.trim());
                        if (code != null) {
                            int index = Integer.parseInt(code);
                            sb.append(",").append(index);
                            sbname.append(",").append(strReferredTo4Name);
                        }
                    }
                }
                if (addCounter >= 4) {

                    if (strReferredTo5Name != null && !strReferredTo5Name.equals("") && strReferredTo5Name.length() > 0) {
                        code = NameData.get(strReferredTo5Name.trim());
                        if (code != null) {
                            int index = Integer.parseInt(code);
                            sb.append(",").append(index);
                            sbname.append(",").append(strReferredTo5Name);
                        }
                    }
                }

                strReferedTo = String.valueOf(sb);

                if (strReferredByName != null && !strReferredByName.equals("") && strReferredByName.length() > 0) {
                    code = NameData.get(strReferredByName.trim());
                    if (code != null) {
                        textRefredByShow.setVisibility(View.VISIBLE);
                        txtReferredBy.setVisibility(View.VISIBLE);
                        int index = Integer.parseInt(code);
                        strReferedBy = String.valueOf(index);
                        textRefredByShow.setText("");
                        textRefredByShow.setText(strReferredByName);
                    } else {
                        textRefredByShow.setVisibility(View.GONE);
                        txtReferredBy.setVisibility(View.GONE);
                    }
                }
                showReferrals.setVisibility(View.VISIBLE);

                strReferedTo = String.valueOf(sb);
                String insertedName = String.valueOf(sbname);
                insertedName = appController.removeCommaOccurance(insertedName);
                if (insertedName != null && !insertedName.equals("") && insertedName.length() > 0) {
                    textRefredToShow.setVisibility(View.VISIBLE);
                    txtReferredTo.setVisibility(View.VISIBLE);
                    textRefredToShow.setText(insertedName + "");
                } else {
                    textRefredToShow.setVisibility(View.INVISIBLE);
                    txtReferredTo.setVisibility(View.INVISIBLE);
                }
                addCounter = 0;
                dialog.dismiss();
            }
        });

        addMore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                addCounter = addCounter + 1;
                if (addCounter == 1) {
                    LinearLayout refred1 = (LinearLayout) dialog.findViewById(R.id.refred1);
                    refred1.setVisibility(View.VISIBLE);
                }
                if (addCounter == 2) {
                    LinearLayout refred2 = (LinearLayout) dialog.findViewById(R.id.refred2);
                    refred2.setVisibility(View.VISIBLE);
                    nameRefredTo2Spinner.setAdapter(referralName);
                }
                if (addCounter == 3) {
                    LinearLayout refred3 = (LinearLayout) dialog.findViewById(R.id.refred3);
                    refred3.setVisibility(View.VISIBLE);
                }
                if (addCounter == 4) {
                    LinearLayout refred4 = (LinearLayout) dialog.findViewById(R.id.refred4);
                    refred4.setVisibility(View.VISIBLE);
                }
                if (addCounter >= 4) {
                    addMore.setVisibility(View.INVISIBLE);
                    Toast.makeText(getContext(), "Sorry you can only add up to 5 records", Toast.LENGTH_LONG).show();
                }
            }
        });


        dialog.show();
    }

    private void setSpinner(View f) {

        final Spinner nameRefredBySpinner = (Spinner) f.findViewById(R.id.nameRefredBySpinner);
        final Spinner nameRefredTo1Spinner = (Spinner) f.findViewById(R.id.nameRefredTo1Spinner);
        final Spinner nameRefredTo2Spinner = (Spinner) f.findViewById(R.id.nameRefredTo2Spinner);
        final Spinner nameRefredTo3Spinner = (Spinner) f.findViewById(R.id.nameRefredTo3Spinner);
        final Spinner nameRefredTo4Spinner = (Spinner) f.findViewById(R.id.nameRefredTo4Spinner);
        final Spinner nameRefredTo5Spinner = (Spinner) f.findViewById(R.id.nameRefredTo5Spinner);


        final TextView referredtoSpeciality1 = (TextView) f.findViewById(R.id.refredtoSpeciality1);
        final TextView referredtoSpeciality2 = (TextView) f.findViewById(R.id.refredtoSpeciality2);
        final TextView referredtoSpeciality3 = (TextView) f.findViewById(R.id.refredtoSpeciality3);
        final TextView referredtoSpeciality4 = (TextView) f.findViewById(R.id.refredtoSpeciality4);
        final TextView referredtoSpeciality5 = (TextView) f.findViewById(R.id.refredtoSpeciality5);
        final TextView referredBySpeciality = (TextView) f.findViewById(R.id.refredBySpeciality);


        try {
            nameReferralsList = dbController.getReferalsnew();

            if (nameReferralsList.size() > 0) {
                referralName = new ArrayAdapter<>(getContext(),
                        R.layout.spinner_item);
                referralName.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                referralName.add("Select Referrals");
                referralName.addAll(nameReferralsList);

                nameRefredBySpinner.setAdapter(referralName);
                nameRefredTo1Spinner.setAdapter(referralName);
                nameRefredTo2Spinner.setAdapter(referralName);
                nameRefredTo3Spinner.setAdapter(referralName);
                nameRefredTo4Spinner.setAdapter(referralName);
                nameRefredTo5Spinner.setAdapter(referralName);

            }
        } catch (ClirNetAppException e) {
            e.printStackTrace();
            appController.appendLog(appController.getDateTime() + " " + "/ " + " AddPatientUpdate" + e + " Line Number: " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        }


        nameRefredTo1Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                strReferredTo1Name = (String) parent.getItemAtPosition(position);


                try {
                    if (nameRefredTo1Spinner.getSelectedItem() != "Select Referrals") {

                        if (appController.contains(strReferredTo1Name, ".")) {
                            String[] parts = strReferredTo1Name.split(". ", 2);
                            // String string1 = parts[0];//namealias
                            strReferredTo1Name = parts[1].trim();//actual name

                        }
                        ArrayList<HashMap<String, String>> list = sqlController.getIdNameDataAssociateMaster(strReferredTo1Name.trim());


                        //String strReferredTo1Id = list.get(0).get("ID");
                        if (list.size() > 0) {
                            String strSpeciality = list.get(0).get("SPECIALITY");
                            referredtoSpeciality1.setText(strSpeciality);
                        }

                       /* if (position > 0) {
                            nameReferralsList.remove(position-1);
                            nameReferralsList.remove(strReferredTo1Name);
                            referralName.notifyDataSetChanged();
                        }*/
                    }
                } catch (ClirNetAppException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        nameRefredTo2Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                strReferredTo2Name = (String) parent.getItemAtPosition(position);
                try {
                    if (nameRefredTo2Spinner.getSelectedItem() != "Select Referrals") {

                        if (appController.contains(strReferredTo2Name, ".")) {
                            String[] parts = strReferredTo2Name.split(". ", 2);
                            //String string1 = parts[0];//namealias
                            strReferredTo2Name = parts[1];//actual name
                        }
                        ArrayList<HashMap<String, String>> list = sqlController.getIdNameDataAssociateMaster(strReferredTo2Name.trim());

                        if (list.size() > 0) {
                            String strSpeclty = list.get(0).get("SPECIALITY");
                            referredtoSpeciality2.setText(strSpeclty);
                        }
                    }
                } catch (ClirNetAppException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        nameRefredTo3Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                strReferredTo3Name = (String) parent.getItemAtPosition(position);
                try {
                    if (nameRefredTo3Spinner.getSelectedItem() != "Select Referrals") {

                        if (appController.contains(strReferredTo3Name, ".")) {
                            String[] parts = strReferredTo3Name.split(". ", 2);
                            //String string1 = parts[0];//namealias
                            strReferredTo3Name = parts[1];//actual name
                        }

                        ArrayList<HashMap<String, String>> list = sqlController.getIdNameDataAssociateMaster(strReferredTo3Name.trim());

                        if (list.size() > 0) {
                            String strSpeclty = list.get(0).get("SPECIALITY");
                            referredtoSpeciality3.setText(strSpeclty);
                        }
                    }
                } catch (ClirNetAppException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        nameRefredTo4Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                strReferredTo4Name = (String) parent.getItemAtPosition(position);
                try {
                    if (nameRefredTo4Spinner.getSelectedItem() != "Select Referrals") {

                        if (appController.contains(strReferredTo4Name, ".")) {
                            String[] parts = strReferredTo4Name.split(". ", 2);
                            //String string1 = parts[0];//namealias
                            strReferredTo4Name = parts[1];//actual name
                        }

                        ArrayList<HashMap<String, String>> list = sqlController.getIdNameDataAssociateMaster(strReferredTo4Name.trim());

                        if (list.size() > 0) {
                            String strSpeclty = list.get(0).get("SPECIALITY");
                            referredtoSpeciality4.setText(strSpeclty);
                        }
                    }
                } catch (ClirNetAppException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        nameRefredTo5Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                strReferredTo5Name = (String) parent.getItemAtPosition(position);
                try {
                    if (nameRefredTo5Spinner.getSelectedItem() != "Select Referrals") {

                        if (appController.contains(strReferredTo5Name, ".")) {
                            String[] parts = strReferredTo5Name.split(". ", 2);
                            // String string1 = parts[0];//namealias
                            strReferredTo5Name = parts[1];//actual name
                        }

                        ArrayList<HashMap<String, String>> list = sqlController.getIdNameDataAssociateMaster(strReferredTo5Name.trim());

                        if (list.size() > 0) {
                            String strSpeclty = list.get(0).get("SPECIALITY");
                            referredtoSpeciality5.setText(strSpeclty);
                        }
                    }
                } catch (ClirNetAppException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        nameRefredBySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                strReferredByName = (String) parent.getItemAtPosition(position);
                try {
                    if (nameRefredBySpinner.getSelectedItem() != "Select Referrals") {

                        if (appController.contains(strReferredByName, ".")) {

                            String[] parts = strReferredByName.split(". ", 2);
                            // String string1 = parts[0];//namealias
                            strReferredByName = parts[1];//actual name
                        }
                        ArrayList<HashMap<String, String>> list = sqlController.getIdNameDataAssociateMaster(strReferredByName.trim());

                        // String strReferredById = list.get(0).get("ID");
                        if (list.size() > 0) {
                            String strSpeclty = list.get(0).get("SPECIALITY");
                            referredBySpeciality.setText(strSpeclty);
                        }
                    }
                } catch (ClirNetAppException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @SuppressLint("RestrictedApi")
    private void addVitalsDialog() {

        final Dialog dialog;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            dialog = new Dialog(new ContextThemeWrapper(getContext(), android.R.style.Theme_DeviceDefault_Light_NoActionBar_Overscan));
            LayoutInflater factory = LayoutInflater.from(getContext());

            final View f = factory.inflate(R.layout.vitals_in_dialog, null);

            Button cancel = (Button) f.findViewById(R.id.customDialogCancel);
            Button save = (Button) f.findViewById(R.id.customDialogOk);
            edtInput_weight = (EditText) f.findViewById(R.id.input_weight);
            edtInput_height = (EditText) f.findViewById(R.id.input_height);
            edtInput_bmi = (EditText) f.findViewById(R.id.input_bmi);
            edtInput_pulse = (EditText) f.findViewById(R.id.input_pulse);
            edtInput_bp = (EditText) f.findViewById(R.id.input_bp);
            edtLowBp = (EditText) f.findViewById(R.id.lowBp);
            edtInput_temp = (EditText) f.findViewById(R.id.input_temp);
            edtInput_spo2 = (EditText) f.findViewById(R.id.input_spo2);
            edtInput_respiration_rate = (EditText) f.findViewById(R.id.input_respiration_rate);
            ediInput_obesity = (EditText) f.findViewById(R.id.input_obesity);

            dialog.setTitle(" Add Vitals ");
            dialog.setCanceledOnTouchOutside(false);
            dialog.setContentView(f);

            if (strWeight != null && !strWeight.equals("") && strWeight.length() > 0) {
                edtInput_weight.setText(strWeight);
            }

            if (strHeight != null && !strHeight.equals("") && strHeight.length() > 0) {

                edtInput_height.setText(strHeight);
            }

            if (strBmi != null && !strBmi.equals("") && strBmi.length() > 0) {

                edtInput_bmi.setText(strBmi);
            }

            if (strPulse != null && !strPulse.equals("") && strPulse.length() > 0) {

                edtInput_pulse.setText(strPulse);
            }
            if (strBp != null && !strBp.equals("") && strBp.length() > 0) {

                edtInput_bp.setText(strBp);
            }
            if (strLowBp != null && !strLowBp.equals("") && strLowBp.length() > 0) {

                edtLowBp.setText(strLowBp);
            }
            if (strTemp != null && !strTemp.equals("") && strTemp.length() > 0) {

                edtInput_temp.setText(strTemp);
            }
            if (strSpo2 != null && !strSpo2.equals("")) {
                edtInput_spo2.setText(strSpo2);
            }
            if (strRespirationRate != null && !strRespirationRate.equals("")) {
                edtInput_respiration_rate.setText(strRespirationRate);
            }
            if (strObesity != null && !strObesity.equals("") && strObesity.length() > 0) {
                ediInput_obesity.setText(strObesity);
            }
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    sbVitals.setLength(0);

                    strWeight = edtInput_weight.getText().toString().trim();
                    strPulse = edtInput_pulse.getText().toString().trim();
                    strBp = edtInput_bp.getText().toString().trim();
                    strLowBp = edtLowBp.getText().toString().trim();
                    strTemp = edtInput_temp.getText().toString().trim();


                    strHeight = edtInput_height.getText().toString().trim();
                    strBmi = edtInput_bmi.getText().toString().trim();
                    strSpo2 = edtInput_spo2.getText().toString();
                    strRespirationRate = edtInput_respiration_rate.getText().toString();
                    strObesity = ediInput_obesity.getText().toString();

                    if (strTemp.length() > 0) {
                        double valueTemp = Double.parseDouble(strTemp);
                        if (valueTemp > 110) {
                            edtInput_temp.setError("Temperature cannot be more than 110");
                            return;
                        } else {
                            edtInput_temp.setError(null);
                        }
                    }

                    if (strWeight.length() > 0) {
                        double valueWeight = Double.parseDouble(strWeight);
                        // int intTemp = Math.round(Integer.parseInt(strTemp));
                        if (valueWeight > 250) {
                            edtInput_weight.setError("The maximum Weight that can be recorded 250 kg");
                            return;
                        } else {
                            edtInput_weight.setError(null);
                        }
                    }
                    if (strHeight.length() > 0) {
                        double valueHeight = Double.parseDouble(strHeight);
                        // int intTemp = Math.round(Integer.parseInt(strTemp));
                        if (valueHeight > 240) {
                            edtInput_height.setError("The maximum height that can be recorded 240 cm");
                            return;
                        } else {
                            edtInput_height.setError(null);
                        }
                    }
                    if (strPulse.length() > 0) {
                        double valuePulse = Double.parseDouble(strPulse);
                        // int intTemp = Math.round(Integer.parseInt(strTemp));
                        if (valuePulse > 150) {
                            edtInput_pulse.setError("The maximum Pulse that can be recorded 150");
                            return;
                        } else {
                            edtInput_pulse.setError(null);
                        }
                    }
                    if (strSpo2.length() > 0) {
                        double valueSpo2 = Double.parseDouble(strSpo2);
                        // int intTemp = Math.round(Integer.parseInt(strTemp));
                        if (valueSpo2 > 100) {
                            edtInput_spo2.setError("The maximum SPO2 that can be recorded 100 mmHg");
                            return;
                        } else {
                            edtInput_spo2.setError(null);
                        }
                    }
                    if (strBp.length() > 0) {
                        double valueBp = Double.parseDouble(strBp);
                        // int intTemp = Math.round(Integer.parseInt(strTemp));
                        if (valueBp > 400) {
                            edtInput_bp.setError("The maximum Systole that can be recorded 400 mmHg");
                            return;
                        } else {
                            edtInput_bp.setError(null);
                        }
                    }
                    if (strLowBp.length() > 0) {
                        double valueDiastole = Double.parseDouble(strLowBp);
                        // int intTemp = Math.round(Integer.parseInt(strTemp));
                        if (valueDiastole > 200) {
                            edtLowBp.setError("The maximum Diastole that can be recorded 200 mmHg");
                            return;
                        } else {
                            edtLowBp.setError(null);
                        }
                    }

                    if (strRespirationRate.length() > 0) {
                        double valueRespRate = Double.parseDouble(strRespirationRate);
                        // int intTemp = Math.round(Integer.parseInt(strTemp));
                        if (valueRespRate > 30) {
                            edtInput_respiration_rate.setError("The maximum Respiration Rate that can be recorded 30");
                            return;
                        } else {
                            edtInput_respiration_rate.setError(null);
                        }
                    }
                    if (strWeight != null && strWeight.length() > 0) {
                        sbVitals.append("Weight - ").append(strWeight).append("  ;  ");
                    }

                    if (strHeight != null && !strHeight.equals("") && strHeight.length() > 0) {

                        sbVitals.append("Height - ").append(strHeight).append("  ;  ");
                    }
                    if (strBmi != null && !strBmi.equals("") && strBmi.length() > 0) {

                        sbVitals.append("BMI - ").append(strBmi).append("  ;  ");
                    }
                    if (strPulse != null && !strPulse.equals("") && strPulse.length() > 0) {

                        sbVitals.append("Pulse - ").append(strPulse).append("  ;  ");
                    }
                    if (strBp != null && !strBp.equals("") && strBp.length() > 0) {

                        sbVitals.append("Systole - ").append(strBp).append("  ;  ");
                    }

                    if (strLowBp != null && !strLowBp.equals("") && strLowBp.length() > 0) {

                        sbVitals.append("Diastole - ").append(strLowBp).append("  ;  ");
                    }
                    if (strTemp != null && !strTemp.equals("") && strTemp.length() > 0) {

                        sbVitals.append("Temp - ").append(strTemp).append("  ;  ");
                    }
                    if (strSpo2 != null && !strSpo2.equals("") && strSpo2.length() > 0) {

                        sbVitals.append("SPO2 - ").append(strSpo2).append("  ;  ");
                    }
                    if (strRespirationRate != null && !strRespirationRate.equals("") && strRespirationRate.length() > 0) {

                        sbVitals.append("Respiration - ").append(strRespirationRate).append("  ;  ");
                    }

                    if (strObesity != null && !strObesity.equals("") && strObesity.length() > 0) {
                        sbVitals.append("Obesity - ").append(strObesity).append("  ;  ");
                    }
                    if (sbVitals != null && sbVitals.length() > 1) {
                        showVitalsDataLayout.setVisibility(View.VISIBLE);
                    }

                    showVitalsData.setText(sbVitals);
                    dialog.dismiss();
                }
            });

            dialog.show();
        }
        edtInput_height.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                edtInput_height.setError(null);
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                String bmi = appController.CalculateBMI(edtInput_weight.getText().toString(), edtInput_height.getText().toString());
                edtInput_bmi.setText(bmi);
                strObesity = appController.getObesity(bmi);
                ediInput_obesity.setText(strObesity);
            }
        });
        edtInput_weight.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                edtInput_weight.setError(null);
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                String bmi = appController.CalculateBMI(edtInput_weight.getText().toString(), edtInput_height.getText().toString());
                edtInput_bmi.setText(bmi);
                strObesity = appController.getObesity(bmi);
                ediInput_obesity.setText(strObesity);
            }
        });
    }

    private void saveDataToDataBase() {


        String clinical_note = clinicalNotes.getText().toString().trim();


        String strSymptoms = edtSymptoms.getText().toString().trim();

        List symptoms = edtSymptoms.getObjects();
        String[] arraySymptoms = (String[]) symptoms.toArray(new String[symptoms.size()]);
        String sympt = Arrays.toString(arraySymptoms);
        strSymptoms = strSymptoms.replace(",,", "");

        String correctSymptoms = sympt.substring(1, sympt.length() - 1);
        strSymptoms = correctSymptoms + strSymptoms;

        String strDignosis = edtDignosis.getText().toString().trim();

        List dignosis = edtDignosis.getObjects();
        String[] diagonosArray = (String[]) dignosis.toArray(new String[dignosis.size()]);
        String diagonos = Arrays.toString(diagonosArray);
        strDignosis = strDignosis.replace(",,", "");

        String correctDignosis = diagonos.substring(1, diagonos.length() - 1);
        strDignosis = correctDignosis + strDignosis;

        userSellectedDate = fodtextshow.getText().toString();

        String enterNumberForFod = inputnumber.getText().toString();

        if (TextUtils.isEmpty(strSymptoms) && TextUtils.isEmpty(strDignosis)) {
            Toast.makeText(getContext(), "Please enter at least 1 symptom or diagnosis", Toast.LENGTH_LONG).show();
            return;
        }
        if (!fodChecked) {
            if (!enterNumberForFod.equals("")) {
                Toast.makeText(getContext(), "Please select measure of time for follow up date", Toast.LENGTH_LONG).show();
                return;
            }
        }

        String visit_id = String.valueOf(maxVisitId + 1);
        String visit_date = addedOnDate;

        //here we need to convert date it to 1-09-2016 date format to get records in sys date filter list
        sdf1 = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

        SimpleDateFormat fromUser = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

        String added_on = visitDate.getText().toString();
        String delimiter = ",";
        String[] diagno = strDignosis.split(delimiter);
             /* print substrings */
        for (String aTemp : diagno) {

            if (!new AppController().isDuplicate(mDiagnosisList, aTemp)) {

                databaseClass.addDiagnosis(aTemp);

            }
        }

        String[] symp = strSymptoms.split(delimiter);

        for (String aTemp : symp) {

            if (!new AppController().isDuplicate(mSymptomsList, aTemp)) {

                databaseClass.addSymptoms(aTemp);

            }
        }

        try {
            //convert visit date from 2016-11-1 to 2016-11-01
            visit_date = myFormat.format(fromUser.parse(added_on));
            added_on = myFormat.format(fromUser.parse(addedOnDate));

            if (userSellectedDate != null && !userSellectedDate.equals("")) {
                userSellectedDate = myFormat.format(fromUser.parse(userSellectedDate));
            }

        } catch (ParseException e) {
            e.printStackTrace();
            appController.appendLog(appController.getDateTime() + " " + "/ " + "Add Patient" + e + " " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        String flag = "0";
        String added_by = docId;
        String patientInfoType = "App";
        String action = "added";
        String record_source = "Add Patient Update";

        dbController.addPatientNextVisitRecord(visit_id, strPatientId, userSellectedDate, daysSel, fowSel, monthSel, clinical_note, prescriptionImgPath, visit_date, docId, doctor_membership_number, added_on, addedTime, flag, added_by, action, patientInfoType,
                strWeight, strPulse, strBp, strLowBp, strTemp, strSymptoms, strDignosis, strHeight, strBmi, strReferedBy, strReferedTo, strPatientFollowUpStatus, record_source, strSpo2, strRespirationRate, strObesity);

        dbController.addInvestigation(strPatientId, visit_id, strSugar, strSugarFasting, strHbA1c, strAcer, strSerumUrea, strLipidHDL, strLipidTC
                , strLipidTG, strLipidLDL, strLipidVHDL, strEcg, strPft, flag);

        try {
            if(prescriptionImgPath!=null)
                dbController.addImages(strPatientId, visit_id, prescriptionImgPath);
        } catch (ClirNetAppException e) {
            e.printStackTrace();
        }

        /*dbController.addInvestigation(strPatientId, visit_id, strSugar, strSugarFasting, strHbA1c, strAcer, strSerumUrea, strLipidHDL, strLipidTC,strTch
                , strLipidTG, strLipidLDL, strLipidVHDL, strEcg, strPft,strHb,strPlateletCount,strEsr,strDcl,strDcn ,strDce,strDcm,strDcb,
                strTotalBiliubin,strDirectBilirubin,strIndirectBilirubin,strSgpt,strSgot,strGgt,strTotalProtein,strAlbumin,strGlobulin,strAgRatio,strLdlHdlRatio,strSugarRbs,strUrinaryPusCell,strUrineRbc,strUrinaryCast,strUrineProtein,strUrineCrystal,strMicroalbuminuria,
                strSerumCreatinine,strAcr,strTsh,strT3,strT4,flag);*/

        Toast.makeText(getContext(), "Patient Record Updated", Toast.LENGTH_LONG).show();
        //Redirect to navigation Activity
        goToNavigation();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (sqlController != null) {
            sqlController = null;
        }
        if (dbController != null) {
            dbController = null;
        }
        if (databaseClass != null) {
            databaseClass = null;
        }
        if (appController != null) {
            appController = null;
        }
        if (bannerClass != null) {
            bannerClass = null;
        }
        edtInput_weight = null;
        edtInput_pulse = null;
        edtInput_bp = null;
        edtLowBp = null;
        edtInput_temp = null;
        edtInput_bmi = null;
        edtInput_height = null;
        txtReferredTo = null;
        txtReferredBy = null;

        visitDate = null;
        edtprescriptionImgPath = null;
        addPatientprescriptionBtn = null;
        inputnumber = null;
        btnclear = null;
        clinicalNotes = null;
        fodtextshow = null;
        days = null;
        week = null;
        month = null;
        addUpdate = null;
        cancel = null;
        edtSymptoms = null;
        edtDignosis = null;

        daysSel = null;
        fowSel = null;
        monthSel = null;
        userSellectedDate = null;
        strWeight = null;
        strPulse = null;
        strBp = null;
        strLowBp = null;
        strTemp = null;
        strSugarFasting = null;
        strHeight = null;
        showReferrals = null;
        textRefredByShow = null;
        textRefredToShow = null;
        strReferredByName = null;
        strReferredTo1Name = null;
        strReferredTo2Name = null;
        strReferredTo3Name = null;
        strReferredTo4Name = null;
        strReferredTo5Name = null;
        strReferedTo = null;
        strReferedBy = null;
        NameData = null;
        mDiagnosisList = null;
        mSymptomsList = null;
        value = null;
        sdf1 = null;
        imageIntent = null;
        uriSavedImage = null;
        imagesFolder = null;
        doctor_membership_number = null;
        docId = null;
        PrescriptionimageName = null;
        prescriptionImgPath = null;
        addedTime = null;
        addedOnDate = null;
        specialityArray = null;
        buttonObservations = null;
        strPatientFollowUpStatus = null;
        strPallorDescription = null;
        strCyanosisDescription = null;
        strTremorsDescription = null;
        strIcterusDescription = null;
        strClubbingDescription = null;
        strOedemaDescription = null;
        strCalfTendernessDescription = null;
        strLymphadenopathyDescription = null;
        edtInput_spo2 = null;
        edtInput_respiration_rate = null;

        strSpo2 = null;
        strRespirationRate = null;
        strPallore = null;
        strCyanosis = null;
        strTremors = null;
        strIcterus = null;
        strClubbing = null;
        strOedema = null;
        strCalfTenderness = null;
        strLymphadenopathy = null;
        showObservationsData = null;
        sbVitals = null;
        sbObservations = null;
        sbInvestigations = null;
        ediInput_obesity = null;
        strObesity = null;
        showInvestigationData = null;
        showVitalsData = null;
        showObservationsData = null;
        strPft = null;
        strLipidTC = null;
        strLipidTG = null;
        strLipidLDL = null;
        strLipidVHDL = null;
        strLipidHDL = null;
        strSugar = null;
        strHbA1c = null;
        strSerumUrea = null;
        strAcer = null;
        buttonInvestigation = null;
        strVisitId = null;

        hemogramlayout = null;
        liverFunctionlayout = null;
        lipidProfilelayout = null;
        diabeticlayout = null;
        urineRoutineExaminationlayout = null;
        rftlayout = null;
        thyroidProfilelayout = null;
        txthemogram = null;
        txtLiverFunctionTest = null;
        txtLipidProfile = null;
        txtDiabeticProfile = null;
        txtUrineRoutineExamination = null;
        txtRft = null;
        txtThyroidProfile = null;
        refered = null;
        refered = null;
    }

    public void getViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    private void showInvestigationDialog() {

        final Dialog dialog;

        dialog = new Dialog(getContext());
        LayoutInflater factory = LayoutInflater.from(getContext());

        final View f = factory.inflate(R.layout.investigation_dialog, null);
        //new_investigation_dialog
        dialog.setTitle("Add Investigation");
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(f);

        Button cancel = (Button) f.findViewById(R.id.customDialogCancel);
        Button ok = (Button) f.findViewById(R.id.customDialogOk);
        final EditText input_hba1c = (EditText) f.findViewById(R.id.input_hba1c);
        final EditText input_acer = (EditText) f.findViewById(R.id.input_acer);
        final EditText input_seremUrea = (EditText) f.findViewById(R.id.input_seremUrea);
        final EditText input_LipidHDL = (EditText) f.findViewById(R.id.input_LipidHDL);
        final EditText input_LipidTC = (EditText) f.findViewById(R.id.input_LipidTC);
        final EditText input_LipidTG = (EditText) f.findViewById(R.id.input_LipidTG);
        final EditText input_LipidLDL = (EditText) f.findViewById(R.id.input_LipidLDL);
        final EditText input_LipidVHDL = (EditText) f.findViewById(R.id.input_LipidVHDL);
        final EditText edtInput_sugar = (EditText) f.findViewById(R.id.input_sugar);
        final EditText edtInput_sugarfasting = (EditText) f.findViewById(R.id.input_sugarfasting);

        /*final EditText input_hb = (EditText) f.findViewById(R.id.input_hb);
        final  EditText input_tch=(EditText)f.findViewById(R.id.input_tch);
        final EditText input_plateletcount = (EditText) f.findViewById(R.id.input_plateletcount);
        final EditText input_esr = (EditText) f.findViewById(R.id.input_esr);

        final EditText input_dcl = (EditText) f.findViewById(R.id.input_dcl);
        final EditText input_dcn = (EditText) f.findViewById(R.id.input_dcn);
        final EditText input_dce = (EditText) f.findViewById(R.id.input_dce);
        final EditText input_dcm = (EditText) f.findViewById(R.id.input_dcm);
        final EditText input_dcb = (EditText) f.findViewById(R.id.input_dcb);
        final EditText input_totalbilirubin = (EditText) f.findViewById(R.id.input_totalbilirubin);
        final EditText input_directbilirubin = (EditText) f.findViewById(R.id.input_directbilirubin);
        final EditText input_indirectbilirubin = (EditText) f.findViewById(R.id.input_indirectbilirubin);
        final EditText input_sgpt = (EditText) f.findViewById(R.id.input_sgpt);
        final EditText input_sgot=(EditText)f.findViewById(R.id.input_sgot);
        final EditText input_ggt = (EditText) f.findViewById(R.id.input_ggt);
        final EditText input_totalprotein = (EditText) f.findViewById(R.id.input_totalprotein);
        final EditText input_albumin = (EditText) f.findViewById(R.id.input_albumin);

        final EditText input_globulin = (EditText) f.findViewById(R.id.input_globulin);
        final EditText input_agratio = (EditText) f.findViewById(R.id.input_agratio);
        final EditText input_Lipidlhdl_ratio = (EditText) f.findViewById(R.id.input_Lipidlhdl_ratio);
        final EditText input_sugarrbs = (EditText) f.findViewById(R.id.input_sugarrbs);
        final EditText input_urinePusCell = (EditText) f.findViewById(R.id.input_urinePusCell);
        final EditText input_urineRbc = (EditText) f.findViewById(R.id.input_urineRbc);
        final EditText input_urinaryCast = (EditText) f.findViewById(R.id.input_urinaryCast);
        final EditText input_urineProtein = (EditText) f.findViewById(R.id.input_urineProtein);
        final EditText input_urineCrystal = (EditText) f.findViewById(R.id.input_urineCrystal);
        final EditText input_microalbuminuria = (EditText) f.findViewById(R.id.input_microalbuminuria);
        final EditText input_serumCreatinine = (EditText) f.findViewById(R.id.input_serumCreatinine);
        final EditText input_tsh = (EditText) f.findViewById(R.id.input_tsh);
        final EditText input_t3 = (EditText) f.findViewById(R.id.input_t3);
        final EditText input_t4 = (EditText) f.findViewById(R.id.input_t4);
        final EditText input_acr = (EditText) f.findViewById(R.id.input_acr);

        hemogramlayout = (LinearLayout) f.findViewById(R.id.hemogramlayout);
        liverFunctionlayout = (LinearLayout) f.findViewById(R.id.liverFunctionlayout);
        lipidProfilelayout = (LinearLayout) f.findViewById(R.id.lipidProfilelayout);
        diabeticlayout = (LinearLayout) f.findViewById(R.id.diabeticlayout);
        urineRoutineExaminationlayout = (LinearLayout) f.findViewById(R.id.urineRoutineExaminationlayout);
        rftlayout = (LinearLayout) f.findViewById(R.id.rftlayout);
        thyroidProfilelayout = (LinearLayout) f.findViewById(R.id.thyroidProfilelayout);

        txthemogram = (TextView) f.findViewById(R.id.txthemogram);
        txtLiverFunctionTest = (TextView) f.findViewById(R.id.txtLiverFunctionTest);
        txtLipidProfile = (TextView) f.findViewById(R.id.txtLipidProfile);
        txtDiabeticProfile = (TextView) f.findViewById(R.id.txtDiabeticProfile);
        txtUrineRoutineExamination = (TextView) f.findViewById(R.id.txtUrineRoutineExamination);
        txtRft = (TextView) f.findViewById(R.id.txtRft);
        txtThyroidProfile = (TextView) f.findViewById(R.id.txtThyroidProfile);*/

        final CheckBox cbPftNormal = (CheckBox) f.findViewById(R.id.cbPftNormal);
        final CheckBox cbPftAbnormal = (CheckBox) f.findViewById(R.id.cbPftAbnormal);

        final CheckBox cbEcgNormal = (CheckBox) f.findViewById(R.id.cbEcgNormal);
        final CheckBox cbEcgAbnormal = (CheckBox) f.findViewById(R.id.cbEcgAbnormal);

       /* RadioGroup radioEcg = (RadioGroup) f.findViewById(R.id.radioEcg);
        RadioGroup radioPft = (RadioGroup) f.findViewById(R.id.radioPft);*/

        sbInvestigations.setLength(0);//clearing string builder from previous data

        if (strLipidTC != null) {
            input_LipidTC.setText(strLipidTC);
        }
        if (strLipidTG != null && !strLipidTG.equals("")) input_LipidTG.setText(strLipidTG);
        if (strLipidLDL != null && !strLipidLDL.equals("")) input_LipidLDL.setText(strLipidLDL);
        if (strLipidVHDL != null && !strLipidVHDL.equals("")) input_LipidVHDL.setText(strLipidVHDL);
        if (strLipidHDL != null && !strLipidHDL.equals("")) input_LipidHDL.setText(strLipidHDL);
        if (strSugar != null && !strSugar.equals("")) edtInput_sugar.setText(strSugar);
        if (strSugarFasting != null && !strSugarFasting.equals(""))
            edtInput_sugarfasting.setText(strSugarFasting);
        if (strHbA1c != null && !strHbA1c.equals("")) input_hba1c.setText(strHbA1c);
        if (strSerumUrea != null && !strSerumUrea.equals("")) input_seremUrea.setText(strSerumUrea);
        if (strAcer != null && !strAcer.equals("")) input_acer.setText(strAcer);
        /*if (strHb != null && !strHb.equals(""))input_hb.setText(strHb);
        if (strPlateletCount != null && !strPlateletCount.equals("")) input_plateletcount.setText(strPlateletCount);
        if (strEsr != null && !strEsr.equals(""))input_esr.setText(strEsr);
        if (strDcl != null && !strDcl.equals(""))input_dcl.setText(strDcl);
        if (strDcn != null && !strDcn.equals("")) input_dcn.setText(strDcn);
        if (strDce != null && !strDce.equals("")) input_dce.setText(strDce);
        if (strDcm != null && !strDcm.equals("")) input_dcm.setText(strDcm);
        if (strDcb != null && !strDcb.equals("")) input_dcb.setText(strDcb);
        if (strTotalBiliubin != null && !strTotalBiliubin.equals("")) input_totalbilirubin.setText(strTotalBiliubin);
        if (strDirectBilirubin != null && !strDirectBilirubin.equals("")) input_directbilirubin.setText(strDirectBilirubin);
        if (strIndirectBilirubin != null && !strIndirectBilirubin.equals("")) input_indirectbilirubin.setText(strIndirectBilirubin);
        if (strSgpt != null && !strSgpt.equals(""))input_sgpt.setText(strSgpt);
        if (strSgot != null && !strSgot.equals(""))input_sgot.setText(strSgot);
        if (strGgt != null && !strGgt.equals(""))input_ggt.setText(strGgt);
        if (strTotalProtein != null && !strTotalProtein.equals(""))input_totalprotein.setText(strTotalProtein);
        if(strAlbumin!= null && !strAlbumin.equals(""))input_albumin.setText(strAlbumin);
        if (strGlobulin != null && !strGlobulin.equals(""))input_globulin.setText(strGlobulin);
        if(strAgRatio!= null && !strAgRatio.equals(""))input_agratio.setText(strAgRatio);
        if( strTch!= null && !strTch.equals(""))input_tch.setText(strTch);
        if(strLdlHdlRatio!= null && !strLdlHdlRatio.equals(""))input_Lipidlhdl_ratio.setText(strLdlHdlRatio);
        if (strSugarRbs!= null && !strSugarRbs.equals(""))input_sugarrbs.setText(strSugarRbs);
        if(strUrinaryPusCell!= null && !strUrinaryPusCell.equals(""))input_urinePusCell.setText(strUrinaryPusCell);
        if(strUrineRbc!= null && !strUrineRbc.equals(""))input_urineRbc.setText(strUrineRbc);
        if(strUrinaryCast!= null && !strUrinaryCast.equals(""))input_urinaryCast.setText(strUrinaryCast);
        if(strUrineProtein!= null && !strUrineProtein.equals(""))input_urineProtein.setText(strUrineProtein);
        if(strUrineCrystal!= null && !strUrineCrystal.equals(""))input_urineCrystal.setText(strUrineCrystal);
        if(strMicroalbuminuria!= null && !strMicroalbuminuria.equals(""))input_microalbuminuria.setText(strMicroalbuminuria);
        if(strSerumCreatinine!= null && !strSerumCreatinine.equals(""))input_serumCreatinine.setText(strSerumCreatinine);
        if(strAcr != null && !strAcr.equals(""))input_acr.setText(strAcr);
        if(strTsh != null && !strTsh.equals(""))input_tsh.setText(strTsh);
        if(strT3!= null && !strT3.equals(""))input_t3.setText(strT3);
        if(strT4!= null && !strT4.equals(""))input_t4.setText(strT4);*/


        if (strEcg != null && !strEcg.equals(""))
            switch (strEcg) {
                case "Normal":

                    cbEcgNormal.setChecked(true);
                    break;
                case "Abnormal":

                    cbEcgAbnormal.setChecked(true);
                    break;
            }
        if (strPft != null && !strPft.equals(""))
            switch (strPft) {
                case "Normal":

                    cbPftNormal.setChecked(true);
                    break;
                case "Abnormal":

                    cbPftAbnormal.setChecked(true);
                    break;
            }
        cbEcgNormal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (cbEcgNormal.isChecked()) {
                    strEcg = "Normal";
                    cbEcgAbnormal.setChecked(false);
                } else {
                    cbEcgNormal.setChecked(false);
                    strEcg = "";
                }
            }
        });
        cbEcgAbnormal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (cbEcgAbnormal.isChecked()) {
                    strEcg = "Abnormal";
                    cbEcgNormal.setChecked(false);
                } else {
                    cbEcgAbnormal.setChecked(false);
                    strEcg = "";

                }
            }
        });


        cbPftNormal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (cbPftNormal.isChecked()) {
                    strPft = "Normal";
                    cbPftAbnormal.setChecked(false);
                } else {
                    cbPftNormal.setChecked(false);
                    strPft = "";
                }
            }
        });
        cbPftAbnormal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (cbPftAbnormal.isChecked()) {
                    strPft = "Abnormal";
                    cbPftNormal.setChecked(false);
                } else {
                    cbPftAbnormal.setChecked(false);
                    strPft = "";

                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sbInvestigations.setLength(0);

                strLipidTC = input_LipidTC.getText().toString();
                strLipidTG = input_LipidTG.getText().toString();
                strLipidLDL = input_LipidLDL.getText().toString();
                strLipidVHDL = input_LipidVHDL.getText().toString();
                strLipidHDL = input_LipidHDL.getText().toString();
                strSugarFasting = edtInput_sugarfasting.getText().toString();
                strSugar = edtInput_sugar.getText().toString();
                strHbA1c = input_hba1c.getText().toString();
                strSerumUrea = input_seremUrea.getText().toString();
                strAcer = input_acer.getText().toString();

               /* strHb = input_hb.getText().toString();
                strTch=input_tch.getText().toString();
                strPlateletCount = input_plateletcount.getText().toString();
                strEsr = input_esr.getText().toString();
                strDcl = input_dcl.getText().toString();
                strDcn = input_dcn.getText().toString();
                strDce = input_dce.getText().toString();
                strDcm = input_dcm.getText().toString();
                strDcb = input_dcb.getText().toString();
                strTotalBiliubin = input_totalbilirubin.getText().toString();
                strDirectBilirubin = input_directbilirubin.getText().toString();
                strIndirectBilirubin = input_indirectbilirubin.getText().toString();
                strSgpt = input_sgpt.getText().toString();
                strSgot=input_sgot.getText().toString();
                strGgt = input_ggt.getText().toString();
                strTotalProtein = input_totalprotein.getText().toString();
                strAlbumin = input_albumin.getText().toString();
                strGlobulin = input_globulin.getText().toString();
                strAgRatio = input_agratio.getText().toString();
                strLdlHdlRatio = input_Lipidlhdl_ratio.getText().toString();
                strSugarRbs = input_sugarrbs.getText().toString();
                strUrinaryPusCell = input_urinePusCell.getText().toString();
                strUrineRbc = input_urineRbc.getText().toString();
                strUrinaryCast = input_urinaryCast.getText().toString();
                strUrineProtein = input_urineProtein.getText().toString();
                strUrineCrystal = input_urineCrystal.getText().toString();
                strMicroalbuminuria = input_microalbuminuria.getText().toString();
                strSerumCreatinine = input_serumCreatinine.getText().toString();
                strTsh = input_tsh.getText().toString();
                strT3 = input_t3.getText().toString();
                strT4 = input_t4.getText().toString();
                strAcr=input_acr.getText().toString();
*/

                /*if (strObesity != null && !strObesity.equals("") && strObesity.length() > 0) {
                    //sbVitals.append("  ");
                    sbVitals.append("Obesity - ").append(strObesity).append("  ;  ");
                }*/

                if (strSugar != null && strSugar.length() > 0) {
                    sbInvestigations.append("Sugar(PPG) - ").append(strSugar).append("  ;  ");
                }
                if (strSugarFasting != null && strSugarFasting.length() > 0) {
                    sbInvestigations.append("Sugar(FPG) - ").append(strSugarFasting).append("  ;  ");
                }
                if (strEcg != null && strEcg.length() > 0) {
                    sbInvestigations.append("ECG  - ").append(strEcg).append("  ;  ");
                }
                if (strPft != null && strPft.length() > 0) {
                    sbInvestigations.append("PFT - ").append(strPft).append("  ;  ");
                }
                if (strHbA1c != null && strHbA1c.length() > 0) {
                    sbInvestigations.append("HbA1c - ").append(strHbA1c).append("  ;  ");
                }
                if (strAcer != null && strAcer.length() > 0) {
                    sbInvestigations.append("ACR - ").append(strAcer).append("  ;  ");
                }
                if (strSerumUrea != null && strSerumUrea.length() > 0) {
                    sbInvestigations.append("SerumUrea - ").append(strSerumUrea).append("  ;  ");
                }
                if (strLipidHDL != null && strLipidHDL.length() > 0) {
                    sbInvestigations.append("HDL - ").append(strLipidHDL).append("  ;  ");
                }
                if (strLipidTC != null && strLipidTC.length() > 0) {
                    sbInvestigations.append("TC - ").append(strLipidTC).append("  ;  ");
                }
                if (strLipidTG != null && strLipidTG.length() > 0) {
                    sbInvestigations.append("TG - ").append(strLipidTG).append("  ;  ");
                }
                if (strLipidLDL != null && strLipidLDL.length() > 0) {
                    sbInvestigations.append("LDL - ").append(strLipidLDL).append("  ;  ");
                }
                if (strLipidVHDL != null && strLipidVHDL.length() > 0) {
                    sbInvestigations.append("VLDL - ").append(strLipidVHDL).append("  ;  ");
                }
                if (sbInvestigations != null && sbInvestigations.length() > 1) {
                    showshowInvestigationDataLayout.setVisibility(View.VISIBLE);
                    showInvestigationData.setText(sbInvestigations);
                }

                /*dbController.addInvestigation(strPatientId, strVisitId, strSugar, strSugarFasting, strHbA1c, strAcer, strSerumUrea, strLipidHDL, strLipidTC
                        , strLipidTG, strLipidLDL, strLipidVHDL, strEcg, strPft, flag);*/


                dialog.dismiss();
            }
        });
        dialog.show();

    }

    private void showObservationsDialog() {

        final Dialog dialog;

        dialog = new Dialog(getContext());
        LayoutInflater factory = LayoutInflater.from(getContext());

        final View f = factory.inflate(R.layout.observation_dialog_new, null);
        dialog.setTitle("Add Observation");
        dialog.setCanceledOnTouchOutside(false);

        dialog.setContentView(f);


        Button cancel = (Button) f.findViewById(R.id.customDialogCancel);
        Button ok = (Button) f.findViewById(R.id.customDialogOk);

        final CheckBox cbPalloreYes = (CheckBox) f.findViewById(R.id.cbPalloreYes);
        final CheckBox cbPalloreNo = (CheckBox) f.findViewById(R.id.cbPalloreNo);

        final CheckBox cbCyanosiYes = (CheckBox) f.findViewById(R.id.cbCyanosiYes);
        final CheckBox cbCyanosisNo = (CheckBox) f.findViewById(R.id.cbCyanosiNo);
        final CheckBox cbTremorsYes = (CheckBox) f.findViewById(R.id.cbTremorsYes);
        final CheckBox cbTremorsNo = (CheckBox) f.findViewById(R.id.cbTremorsNo);

        final CheckBox cbIcterusYes = (CheckBox) f.findViewById(R.id.cbIcterusYes);
        final CheckBox cbIcterusNo = (CheckBox) f.findViewById(R.id.cbIcterusNo);
        final CheckBox cbClubbingYes = (CheckBox) f.findViewById(R.id.cbClubbingYes);
        final CheckBox cbClubbingNo = (CheckBox) f.findViewById(R.id.cbClubbingNo);

        final CheckBox cbOedemaYes = (CheckBox) f.findViewById(R.id.cbOedemaYes);
        final CheckBox cbOedemaNo = (CheckBox) f.findViewById(R.id.cbOedemaNo);
        final CheckBox cbCalfTendernessYes = (CheckBox) f.findViewById(R.id.cbCalfTendernessYes);
        final CheckBox cbCalfTendernessNo = (CheckBox) f.findViewById(R.id.cbCalfTendernessNo);

        final CheckBox cbLymphadenopathyYes = (CheckBox) f.findViewById(R.id.cbLymphadenopathyYes);
        final CheckBox cbLymphadenopathyNo = (CheckBox) f.findViewById(R.id.cbLymphadenopathyNo);

        final EditText pallorDescription = (EditText) f.findViewById(R.id.pallorDescription);
        final EditText cyanosisDescription = (EditText) f.findViewById(R.id.cyanosisDescription);
        final EditText tremorsDescription = (EditText) f.findViewById(R.id.tremorsDescription);
        final EditText icterusDescription = (EditText) f.findViewById(R.id.icterusDescription);
        final EditText clubbingDescription = (EditText) f.findViewById(R.id.clubbingDescription);
        final EditText oedemaDescription = (EditText) f.findViewById(R.id.oedemaDescription);
        final EditText calfTendernessDescription = (EditText) f.findViewById(R.id.calfTendernessDescription);
        final EditText lymphadenopathyDescription = (EditText) f.findViewById(R.id.lymphadenopathyDescription);

        if (strPallorDescription != null) pallorDescription.setText(strPallorDescription);
        if (strCyanosisDescription != null) cyanosisDescription.setText(strCyanosisDescription);
        if (strTremorsDescription != null) tremorsDescription.setText(strTremorsDescription);
        if (strIcterusDescription != null) icterusDescription.setText(strIcterusDescription);
        if (strClubbingDescription != null) clubbingDescription.setText(strClubbingDescription);
        if (strOedemaDescription != null) oedemaDescription.setText(strOedemaDescription);
        if (strCalfTendernessDescription != null)
            calfTendernessDescription.setText(strCalfTendernessDescription);
        if (strLymphadenopathyDescription != null)
            lymphadenopathyDescription.setText(strLymphadenopathyDescription);

        if (strPallore != null && !strPallore.equals(""))
            switch (strPallore) {
                case "Yes":
                    cbPalloreYes.setChecked(true);

                    break;
                case "No":
                    cbPalloreNo.setChecked(true);
                    break;

            }
        if (strCyanosis != null && !strCyanosis.equals(""))
            switch (strCyanosis) {
                case "Yes":
                    cbCyanosiYes.setChecked(true);
                    break;
                case "No":
                    cbCyanosisNo.setChecked(true);
                    break;
            }
        if (strTremors != null && !strTremors.equals(""))
            switch (strTremors) {
                case "Yes":
                    cbTremorsYes.setChecked(true);
                    break;
                case "No":
                    cbTremorsNo.setChecked(true);
                    break;
            }
        if (strIcterus != null && !strIcterus.equals(""))
            switch (strIcterus) {
                case "Yes":
                    cbIcterusYes.setChecked(true);

                    break;
                case "No":
                    cbIcterusNo.setChecked(true);

                    break;
            }

        if (strClubbing != null && !strClubbing.equals(""))
            switch (strClubbing) {
                case "Yes":
                    cbClubbingYes.setChecked(true);
                    break;
                case "No":
                    cbClubbingNo.setChecked(true);
                    break;
            }
        if (strOedema != null && !strOedema.equals(""))
            switch (strOedema) {
                case "Yes":
                    cbOedemaYes.setChecked(true);

                    break;
                case "No":
                    cbOedemaNo.setChecked(true);
                    break;
            }
        if (strCalfTenderness != null && !strCalfTenderness.equals(""))
            switch (strCalfTenderness) {
                case "Yes":
                    cbCalfTendernessYes.setChecked(true);

                    break;
                case "No":
                    cbCalfTendernessNo.setChecked(true);
                    break;
            }

        if (strLymphadenopathy != null && !strLymphadenopathy.equals(""))
            switch (strLymphadenopathy) {
                case "Yes":
                    cbLymphadenopathyYes.setChecked(true);

                    break;
                case "No":
                    cbLymphadenopathyNo.setChecked(true);

                    break;
            }

        cbPalloreYes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (cbPalloreYes.isChecked()) {
                    strPallore = "Yes";
                    cbPalloreNo.setChecked(false);
                } else {
                    cbPalloreYes.setChecked(false);
                    strPallore = "";
                }
            }
        });
        cbPalloreNo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (cbPalloreNo.isChecked()) {
                    strPallore = "No";
                    cbPalloreYes.setChecked(false);
                } else {
                    cbPalloreNo.setChecked(false);
                    strPallore = "";

                }
            }
        });

        cbCyanosiYes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (cbCyanosiYes.isChecked()) {

                    strCyanosis = "Yes";
                    cbCyanosisNo.setChecked(false);
                } else {
                    cbCyanosiYes.setChecked(false);
                    strCyanosis = "";

                }
            }
        });
        cbCyanosisNo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (cbCyanosisNo.isChecked()) {

                    strCyanosis = "No";
                    cbCyanosiYes.setChecked(false);
                } else {
                    cbCyanosisNo.setChecked(false);
                    strCyanosis = "";

                }
            }
        });

        cbTremorsYes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (cbTremorsYes.isChecked()) {

                    strTremors = "Yes";
                    cbTremorsNo.setChecked(false);
                } else {
                    cbTremorsYes.setChecked(false);
                    strTremors = "";

                }
            }
        });
        cbTremorsNo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (cbTremorsNo.isChecked()) {

                    strTremors = "No";
                    cbTremorsYes.setChecked(false);
                } else {
                    cbTremorsNo.setChecked(false);
                    strTremors = "";

                }
            }
        });
        cbIcterusYes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (cbIcterusYes.isChecked()) {

                    strIcterus = "Yes";
                    cbIcterusNo.setChecked(false);
                } else {
                    cbIcterusYes.setChecked(false);
                    strIcterus = "";

                }
            }
        });
        cbIcterusNo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (cbIcterusNo.isChecked()) {

                    strIcterus = "No";
                    cbIcterusYes.setChecked(false);
                } else {
                    cbIcterusNo.setChecked(false);
                    strIcterus = "";

                }
            }
        });

        cbClubbingYes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (cbClubbingYes.isChecked()) {

                    strClubbing = "Yes";
                    cbClubbingNo.setChecked(false);
                } else {
                    cbClubbingYes.setChecked(false);
                    strClubbing = "";

                }
            }
        });
        cbClubbingNo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (cbClubbingNo.isChecked()) {

                    strClubbing = "No";
                    cbClubbingYes.setChecked(false);
                } else {
                    cbClubbingNo.setChecked(false);
                    strClubbing = "";

                }
            }
        });

        cbOedemaYes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (cbOedemaYes.isChecked()) {

                    strOedema = "Yes";
                    cbOedemaNo.setChecked(false);
                } else {
                    cbOedemaYes.setChecked(false);
                    strOedema = "";

                }
            }
        });
        cbOedemaNo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (cbOedemaNo.isChecked()) {

                    strOedema = "No";
                    cbOedemaYes.setChecked(false);
                } else {
                    cbOedemaNo.setChecked(false);
                    strOedema = "";

                }
            }
        });

        cbCalfTendernessYes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (cbCalfTendernessYes.isChecked()) {

                    strCalfTenderness = "Yes";
                    cbCalfTendernessNo.setChecked(false);
                } else {
                    cbCalfTendernessYes.setChecked(false);
                    strCalfTenderness = "";

                }
            }
        });
        cbCalfTendernessNo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (cbCalfTendernessNo.isChecked()) {

                    strCalfTenderness = "No";
                    cbCalfTendernessYes.setChecked(false);
                } else {
                    cbCalfTendernessNo.setChecked(false);
                    strCalfTenderness = "";

                }
            }
        });

        cbLymphadenopathyYes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (cbLymphadenopathyYes.isChecked()) {

                    strLymphadenopathy = "Yes";
                    cbLymphadenopathyNo.setChecked(false);
                } else {
                    cbLymphadenopathyYes.setChecked(false);
                    strLymphadenopathy = "";

                }
            }
        });
        cbLymphadenopathyNo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (cbLymphadenopathyNo.isChecked()) {

                    strLymphadenopathy = "No";
                    cbLymphadenopathyYes.setChecked(false);
                } else {
                    cbLymphadenopathyNo.setChecked(false);
                    strLymphadenopathy = "";

                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String flag = "0";

                strPallorDescription = pallorDescription.getText().toString();
                strCyanosisDescription = cyanosisDescription.getText().toString();
                strTremorsDescription = tremorsDescription.getText().toString();
                strIcterusDescription = icterusDescription.getText().toString();
                strClubbingDescription = clubbingDescription.getText().toString();
                strOedemaDescription = oedemaDescription.getText().toString();
                strCalfTendernessDescription = calfTendernessDescription.getText().toString();
                strLymphadenopathyDescription = lymphadenopathyDescription.getText().toString();

                showObservationsData();

                if (sbObservations != null && sbObservations.length() > 1) {
                    //  labelshowObservationsData.setVisibility(View.VISIBLE);
                }


                dbController.addObservations(strPatientId, strVisitId, strPallore, strPallorDescription, strCyanosis, strCyanosisDescription, strTremors, strTremorsDescription, strIcterus, strIcterusDescription
                        , strClubbing, strClubbingDescription, strOedema, strOedemaDescription, strCalfTenderness, strCalfTendernessDescription, strLymphadenopathy, strLymphadenopathyDescription, new AppController().getDateTimeddmmyyyy(), flag);

                dialog.dismiss();
            }
        });
        dialog.show();

    }

    private void showObservationsData() {

        if (strPallore != null && strPallore.length() > 0) {
            sbObservations.append("Pallor - ").append(strPallore);
            if (strPallorDescription != null && !strPallorDescription.equals(""))
                sbObservations.append(" (").append(strPallorDescription).append(")").append("  ;  ");
            else sbObservations .append("  ;  ");
        }

        if (strCyanosis != null && strCyanosis.length() > 0) {
            sbObservations.append("Cyanosis - ").append(strCyanosis);
            if (strCyanosisDescription != null && !strCyanosisDescription.equals(""))
                sbObservations.append(" (").append(strCyanosisDescription).append(")").append("  ;  ");
            else sbObservations .append("  ;  ");
        }

        if (strTremors != null && strTremors.length() > 0) {
            sbObservations.append("Tremors - ").append(strTremors);
            if (strTremorsDescription != null && !strTremorsDescription.equals(""))
                sbObservations.append(" (").append(strTremorsDescription).append(")").append("  ;  ");
            else sbObservations .append("  ;  ");
        }

        if (strIcterus != null && strIcterus.length() > 0) {
            sbObservations.append("Icterus - ").append(strIcterus);
            if (strIcterusDescription != null && !strIcterusDescription.equals(""))
                sbObservations.append(" (").append(strIcterusDescription).append(")").append("  ;  ");
            else sbObservations .append("  ;  ");
        }
        if (strClubbing != null && strClubbing.length() > 0) {
            sbObservations.append("Clubbing - ").append(strClubbing);
            if (strClubbingDescription != null && !strClubbingDescription.equals(""))
                sbObservations.append(" (").append(strClubbingDescription).append(")").append("  ;  ");
            else sbObservations .append("  ;  ");
        }
        if (strOedema != null && strOedema.length() > 0) {
            sbObservations.append("Oedema - ").append(strOedema);
            if (strOedemaDescription != null && !strOedemaDescription.equals(""))
                sbObservations.append(" (").append(strOedemaDescription).append(")").append("  ;  ");
            else sbObservations .append("  ;  ");
        }
        if (strCalfTenderness != null && strCalfTenderness.length() > 0) {
            sbObservations.append("Tenderness - ").append(strCalfTenderness);
            if (strCalfTendernessDescription != null && !strCalfTendernessDescription.equals(""))
                sbObservations.append(" (").append(strCalfTendernessDescription).append(")").append("  ;  ");
            else sbObservations .append("  ;  ");
        }
        if (strLymphadenopathy != null && strLymphadenopathy.length() > 0) {
            sbObservations.append("Lymphadenopathy - ").append(strLymphadenopathy);
            if (strLymphadenopathyDescription != null && !strLymphadenopathyDescription.equals(""))
                sbObservations.append(" (").append(strLymphadenopathyDescription).append(")").append("  ;  ");
            else sbObservations .append("  ;  ");
        }

        if (sbObservations != null && sbObservations.length() > 1) {
            showObservationsDataLayout.setVisibility(View.VISIBLE);
            showObservationsDataLayout.setVisibility(View.VISIBLE);
        } else {
            showObservationsDataLayout.setVisibility(View.GONE);
        }

        showObservationsData.setText(sbObservations);

    }

    private void setOnClickListner() {


        txthemogram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counttxthemogram == 1) {
                    hemogramlayout.setVisibility(View.VISIBLE);
                    txthemogram.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.up_arrow, 0); //set drawable right to text view
                    counttxthemogram = 2;
                } else {
                    hemogramlayout.setVisibility(View.GONE);
                    txthemogram.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.down_arrow, 0); //set drawable right to text view
                    counttxthemogram = 1;
                }
                //  txtRecord.setBackground(R.drawable.);
            }
        });
        txtLiverFunctionTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counttxtLiverFunctionTest == 1) {
                    liverFunctionlayout.setVisibility(View.VISIBLE);
                    txtLiverFunctionTest.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.up_arrow, 0); //set drawable right to text view
                    counttxtLiverFunctionTest = 2;
                } else {
                    liverFunctionlayout.setVisibility(View.GONE);
                    txtLiverFunctionTest.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.down_arrow, 0); //set drawable right to text view
                    counttxtLiverFunctionTest = 1;
                }
            }
        });
        txtLipidProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counttxtLipidProfile == 1) {
                    lipidProfilelayout.setVisibility(View.VISIBLE);
                    txtLipidProfile.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.up_arrow, 0); //set drawable right to text view
                    counttxtLipidProfile = 2;
                } else {
                    lipidProfilelayout.setVisibility(View.GONE);
                    txtLipidProfile.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.down_arrow, 0); //set drawable right to text view
                    counttxtLipidProfile = 1;
                }
            }
        });
        txtDiabeticProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counttxtDiabeticProfile == 1) {
                    diabeticlayout.setVisibility(View.VISIBLE);
                    txtDiabeticProfile.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.up_arrow, 0); //set drawable right to text view
                    counttxtDiabeticProfile = 2;
                } else {
                    diabeticlayout.setVisibility(View.GONE);
                    txtDiabeticProfile.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.down_arrow, 0); //set drawable right to text view
                    counttxtDiabeticProfile = 1;
                }
            }
        });
        txtUrineRoutineExamination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counttxtUrineRoutineExamination == 1) {
                    urineRoutineExaminationlayout.setVisibility(View.VISIBLE);
                    txtUrineRoutineExamination.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.up_arrow, 0); //set drawable right to text view
                    counttxtUrineRoutineExamination = 2;
                } else {
                    urineRoutineExaminationlayout.setVisibility(View.GONE);
                    txtUrineRoutineExamination.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.down_arrow, 0); //set drawable right to text view
                    counttxtUrineRoutineExamination = 1;
                }
            }
        });
        txtRft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counttxtRft == 1) {
                    rftlayout.setVisibility(View.VISIBLE);
                    txtRft.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.up_arrow, 0); //set drawable right to text view
                    counttxtRft = 2;
                } else {
                    rftlayout.setVisibility(View.GONE);
                    txtRft.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.down_arrow, 0); //set drawable right to text view
                    counttxtRft = 1;
                }
            }
        });
        txtThyroidProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counttxtThyroidProfile == 1) {
                    thyroidProfilelayout.setVisibility(View.VISIBLE);
                    txtThyroidProfile.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.up_arrow, 0); //set drawable right to text view
                    counttxtThyroidProfile = 2;
                } else {
                    thyroidProfilelayout.setVisibility(View.GONE);
                    txtThyroidProfile.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.down_arrow, 0); //set drawable right to text view
                    counttxtThyroidProfile = 1;
                }
            }
        });
    }

}
