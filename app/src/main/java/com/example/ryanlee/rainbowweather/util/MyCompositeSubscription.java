package com.example.ryanlee.rainbowweather.util;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Ryanlee on 2016/8/17 0017.
 */

/**
 *
 */
public class MyCompositeSubscription {

    public static void unsubscribeIfNotNull(Subscription subscription){
        if(subscription != null){
            subscription.unsubscribe();
        }
    }

    public static CompositeSubscription getNewCompositeSubIfUnsubscribed(CompositeSubscription subscription){
        if(subscription == null || subscription.isUnsubscribed()){
            return new CompositeSubscription();
        }
        return subscription;
    }


}
