package com.example.mytestapp.Base.presenter;

import androidx.annotation.NonNull;
import androidx.lifecycle.GenericLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.example.mytestapp.Base.View.BaseView;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class PresenterProviders {

    /**
     * 管理presenter attach state
     */
    private static final HashMap<String, BasePresenter> mPresenterMap = new HashMap<>();

    /**
     * 产生presenter
     *
     * @param view   view
     * @param pClass presenter class
     * @return presenter
     */
    @NonNull
    public static <P extends BasePresenter> P of(final BaseView view, final Class<P> pClass) {
        if (null == view || null == pClass) return null;
        try {
            final Lifecycle lifecycle = view.getLifecycle();
            if (lifecycle.getCurrentState().equals(Lifecycle.State.DESTROYED)) {
                throw new RuntimeException("cannot call this method outside lifecycle");
            }
            final String presenterMapKey = getPresenterMapKey(view, pClass);
//            P presenter = (P) mPresenterMap.get(presenterMapKey);
//            if (presenter != null) {
//                return presenter;
//            }
            final P instance = pClass.getDeclaredConstructor().newInstance();
            instance.attachToView(view);
//            mPresenterMap.put(presenterMapKey, instance);

            lifecycle.addObserver(new GenericLifecycleObserver() {
                @Override
                public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
                    if (event == Lifecycle.Event.ON_DESTROY) {
                        lifecycle.removeObserver(this);
//                        mPresenterMap.remove(presenterMapKey);
                        instance.detachFromView();
                    }
                }
            });
            return instance;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static <P extends BasePresenter> String getPresenterMapKey(@NonNull BaseView view, @NonNull Class<P> pClass) {
        return view.getClass().getSimpleName() + pClass.getSimpleName();
    }


}
