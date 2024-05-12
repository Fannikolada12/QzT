package space.lobanov.quiz_test;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.ads.consent.ConsentForm;
import com.google.ads.consent.ConsentFormListener;
import com.google.ads.consent.ConsentInfoUpdateListener;
import com.google.ads.consent.ConsentInformation;
import com.google.ads.consent.ConsentStatus;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.net.MalformedURLException;
import java.net.URL;

public class Finish extends AppCompatActivity {

    //private long backPressedTime;
    //private Toast backToast;
    private ConsentForm form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish);

        MobileAds.initialize(this, "Идентификатор приложения admob");

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        checkForConsent();




        Button back_gamelevels = (Button)findViewById(R.id.back_gamelevels);
        back_gamelevels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Finish.this, GameLevels.class);startActivity(intent);finish();
                } catch (Exception e) {
                }
            }
        });

    }
    //Start code Consent Form
    private void checkForConsent() {
        ConsentInformation consentInformation = ConsentInformation.getInstance(Finish.this);
        String[] publisherIds = {"pub-7978309932487699"};
        consentInformation.requestConsentInfoUpdate(publisherIds, new ConsentInfoUpdateListener() {
            @Override
            public void onConsentInfoUpdated(ConsentStatus consentStatus) {
                Toast.makeText(Finish.this, consentStatus.toString(), Toast.LENGTH_LONG).show();
                // User's consent status successfully updated.
                switch (consentStatus) {
                    case PERSONALIZED: showPersonalizedAds(); break;
                    case NON_PERSONALIZED: showNonPersonalizedAds(); break;
                    case UNKNOWN: if (ConsentInformation.getInstance(getBaseContext())
                                        .isRequestLocationInEeaOrUnknown()) {
                                  requestConsent();
                                  } else {
                                        showPersonalizedAds();
                                  } break;
                    default: break;
                }
            }
            @Override
            public void onFailedToUpdateConsentInfo(String errorDescription) {
                // User's consent status failed to update.
            }
        });
    }
    private void requestConsent() {
        URL privacyUrl = null;
        try {
            // TODO: Replace with your app's privacy policy URL.
            privacyUrl = new URL("http://lobanov.space/privacyQuiz.html");
        } catch (MalformedURLException e) {
            // Handle error.
        }
        form = new ConsentForm.Builder(Finish.this, privacyUrl)
                .withListener(new ConsentFormListener() {
                    @Override
                    public void onConsentFormLoaded() {
                        // Consent form loaded successfully.
                        //Requesting Consent: onConsentFormLoaded
                        showForm();
                    }

                    @Override
                    public void onConsentFormOpened() {
                        // Consent form was displayed.
                        //Requesting Consent: onConsentFormOpened
                    }

                    @Override
                    public void onConsentFormClosed(
                            ConsentStatus consentStatus, Boolean userPrefersAdFree) {
                        //Requesting Consent: onConsentFormClosed
                        if (userPrefersAdFree) {
                            // Buy or Subscribe
                            //Requesting Consent: User prefers AdFree
                        } else {
                            //Requesting Consent: Requesting consent again
                            switch (consentStatus) {
                                case PERSONALIZED: showPersonalizedAds();break;
                                case NON_PERSONALIZED: showNonPersonalizedAds();break;
                                case UNKNOWN: showNonPersonalizedAds();break;
                            }

                        }
                        // Consent form was closed.
                    }

                    @Override
                    public void onConsentFormError(String errorDescription) {
                        // Consent form error.
                    }
                })
                .withPersonalizedAdsOption()
                .withNonPersonalizedAdsOption()
                //.withAdFreeOption()
                .build();
        form.load();
    }

    private void showPersonalizedAds() {
        /*
        this line code save consent status if you want to show your ads in next activty just get getConsentStatus and load ads accouding to status e.g.
        MainActivty2    ConsentStatus consentStatus = ConsentInformation.getInstance(this).getConsentStatus();
        if (consentStatus.toString().equals("NON_PERSONALIZED")) loadNonPersonalizedAds();
        */
        ConsentInformation.getInstance(this).setConsentStatus(ConsentStatus.PERSONALIZED);
        //MobileAds.initialize(this, "ca-app-pub-7978309932487699~3737943881");
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // if you want to show interstitial
        /*
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("Add_unit");
        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(DEVICE_TEST_ID).build());
       */
    }

    private void showNonPersonalizedAds() {
        ConsentInformation.getInstance(this).setConsentStatus(ConsentStatus.NON_PERSONALIZED);
        //MobileAds.initialize(this, "ca-app-pub-7978309932487699~3737943881");
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addNetworkExtrasBundle(AdMobAdapter.class, getNonPersonalizedAdsBundle())
                .build();
        mAdView.loadAd(adRequest);

        /* if you want show interstitial ad also
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("Add_unit");
        mInterstitialAd.loadAd(new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, getNonPersonalizedAdsBundle()).addTestDevice(DEVICE_TEST_ID).build());
        */

    }
    public Bundle getNonPersonalizedAdsBundle() {
        Bundle extras = new Bundle();
        extras.putString("npa", "1");

        return extras;
    }
    private void showForm() {
        if (form == null) {
            //Consent form is null
        }
        if (form != null) {
            //Showing consent form
            form.show();
        } else {
            //Not Showing consent form
        }
    }
    //End code Consent Form

    //Системная кнопка "Назад" - начало

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Finish.this, GameLevels.class);startActivity(intent);finish();


        /*
        if (backPressedTime + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }else{
            backToast = Toast.makeText(getBaseContext(),"Нажмите еще раз, чтобы выйти", Toast.LENGTH_SHORT);
            backToast.show();
        }

        backPressedTime = System.currentTimeMillis();
        */
    }

    //Системная кнопка "Назад" - конец






}
