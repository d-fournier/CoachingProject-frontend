package fr.sims.coachingproject;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.activeandroid.ActiveAndroid;

import java.util.List;

import fr.sims.coachingproject.model.CoachingRelation;
import fr.sims.coachingproject.model.UserProfile;
import fr.sims.coachingproject.util.Const;

/**
 * Created by dfour on 10/02/2016.
 */
public class CoachingProjectApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);

        // TODO Remove when connection is ready
        List<UserProfile> list = UserProfile.getAllUserProfile();
        if(list.size() == 0) {
            UserProfile me = new UserProfile();
            me.mId = 1;
            me.mName = "John Doe";
            me.mBirthday = "01/01/1990";
            me.mCity = "Villeurbanne (69100)";
            me.mIsCoach = true;
            me.mMail = "j.doe@example.com";
            me.mPicture = "https://i1.wp.com/www.techrepublic.com/bundles/techrepubliccore/images/icons/standard/icon-user-default.png";
            me.save();

            UserProfile User = new UserProfile();
            User.mId = 2;
            User.mName = "Johnny Cash";
            User.mBirthday = "01/01/1990";
            User.mCity = "Villeurbanne (69100)";
            User.mIsCoach = false;
            User.mMail = "j.doe@example.com";
            User.mPicture = "https://i1.wp.com/www.techrepublic.com/bundles/techrepubliccore/images/icons/standard/icon-user-default.png";
            User.save();

            UserProfile coach = new UserProfile();
            coach.mId = 3;
            coach.mName = "Bernard Louis";
            coach.mBirthday = "11/11/1990";
            coach.mCity = "Villeurbanne (69100)";
            coach.mIsCoach = true;
            coach.mMail = "trainer@example.com";
            coach.mPicture = "https://i1.wp.com/www.techrepublic.com/bundles/techrepubliccore/images/icons/standard/icon-user-default.png";
            coach.save();

            UserProfile coach2 = new UserProfile();
            coach2.mId = 4;
            coach2.mName = "Jean Benoit";
            coach2.mBirthday = "11/11/1990";
            coach2.mCity = "Lyon (69000)";
            coach2.mIsCoach = true;
            coach2.mMail = "trainer@example.com";
            coach2.mPicture = "https://i1.wp.com/www.techrepublic.com/bundles/techrepubliccore/images/icons/standard/icon-user-default.png";
            coach2.save();

            CoachingRelation cr = new CoachingRelation();
            cr.mId = 1;
            cr.mCoach = coach;
            cr.mUser = me;
            cr.mDate = "10/02/2016";
            cr.mIsPending = true;
            cr.mComment = "Je suis à la recherche non pas de la vérité, mais simplement d'une aventure qui sorte un peu de la banalité !";
            cr.save();

            CoachingRelation cr1 = new CoachingRelation();
            cr1.mId = 2;
            cr1.mCoach = coach2;
            cr1.mUser = me;
            cr1.mDate = "10/02/2016";
            cr1.mIsPending = true;
            cr1.mComment = "Je suis à la recherche non pas de la vérité, mais simplement d'une aventure qui sorte un peu de la banalité !";
            cr1.save();

            CoachingRelation cr2 = new CoachingRelation();
            cr2.mId = 3;
            cr2.mCoach = me;
            cr2.mUser = User;
            cr2.mDate = "10/02/2016";
            cr2.mIsPending =true;
            cr2.mComment = "Je suis à la recherche non pas de la vérité, mais simplement d'une aventure qui sorte un peu de la banalité !";
            cr2.save();

            CoachingRelation cr5 = new CoachingRelation();
            cr5.mId = 6;
            cr5.mCoach = coach;
            cr5.mUser = me;
            cr5.mDate = "10/02/2016";
            cr5.mIsPending =true;
            cr5.mComment = "Je suis à la recherche non pas de la vérité, mais simplement d'une aventure qui sorte un peu de la banalité !";
            cr5.save();

            CoachingRelation cr3 = new CoachingRelation();
            cr3.mId = 4;
            cr3.mCoach = me;
            cr3.mUser = User;
            cr3.mDate = "10/02/2016";
            cr3.mIsPending = false;
            cr3.mComment = "Je suis à la recherche non pas de la vérité, mais simplement d'une aventure qui sorte un peu de la banalité !";
            cr3.save();

            CoachingRelation cr4 = new CoachingRelation();
            cr4.mId = 5;
            cr4.mCoach = coach2;
            cr4.mUser = me;
            cr4.mDate = "10/02/2016";
            cr4.mIsPending = false;
            cr4.mComment = "Je suis à la recherche non pas de la vérité, mais simplement d'une aventure qui sorte un peu de la banalité !";
            cr4.save();


            SharedPreferences.Editor sharedPrefEditor = getSharedPreferences(Const.SharedPref.SHARED_PREF_NAME, Context.MODE_PRIVATE).edit();
            sharedPrefEditor.putLong(Const.SharedPref.CURRENT_USER_ID, 1);
            sharedPrefEditor.apply();
        }
    }
}
