package id.sheendev.qrbarcodescanner.shared.util;

import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rizki Maulana on 11/03/18.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */

public class SheenValidator {
    public static final int NOT_EMPTY = 1;

    private List<TextView> formView = new ArrayList<>();
    private OnValidationListener onValidationListener;
    private int                  type;

    public SheenValidator(int type) {
        this.type = type;
    }

    public void registerRule(TextView view) {
        if (!formView.contains(view)) {
            formView.add(view);
        }
    }


    public void removeRule(TextView view) {
        if (formView.contains(view)) {
            formView.remove(view);
        }
    }

    public void validate() {
        List<TextView> formViewError = new ArrayList<>();
        for (TextView view : formView) {
            if (view.getText().toString().isEmpty() && view.isShown()) {
                formViewError.add(view);
            }
        }

        if (formViewError.size() == 0) {
            onValidationListener.onValid();
        } else {
            onValidationListener.onError(formViewError);
        }

    }

    public void setOnValidationListener(OnValidationListener onValidationListener) {
        this.onValidationListener = onValidationListener;
    }

    public interface OnValidationListener {
        void onValid();

        void onError(List<TextView> errorForm);
    }


}
