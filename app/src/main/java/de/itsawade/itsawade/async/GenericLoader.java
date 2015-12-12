package de.itsawade.itsawade.async;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import de.itsawade.itsawade.util.Function;


/**
 * Created by Steve on 23.11.2015.
 */
public class GenericLoader<T> extends AsyncTaskLoader<T> {

    private Function<T> function;

    public GenericLoader(Context context, Function<T> function) {
        super(context);
        if(function == null)
            throw new IllegalArgumentException("function must not be null");

        this.function = function;
    }

    @Override
    public T loadInBackground() {
        return function.apply();
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
