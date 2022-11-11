package custom;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ChaoDatePicker extends DatePickerDialog {
    public ChaoDatePicker(@NonNull Context context) {
        super(context);
    }

    public ChaoDatePicker(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public ChaoDatePicker(@NonNull Context context, @Nullable OnDateSetListener listener, int year, int month, int dayOfMonth) {
        super(context, listener, year, month, dayOfMonth);
    }
//    public ChaoDatePicker(Context context) {
//        super(context);
//    }
//
//    public ChaoDatePicker(Context context, AttributeSet attrs) {
//        super(context, attrs);
//
//    }
//
//    public ChaoDatePicker(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }





}
