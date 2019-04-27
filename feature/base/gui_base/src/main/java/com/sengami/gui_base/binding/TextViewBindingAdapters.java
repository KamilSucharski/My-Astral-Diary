package com.sengami.gui_base.binding;

import android.os.Build;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

public final class TextViewBindingAdapters {

    @BindingAdapter("app:html")
    public static void setHtml(final TextView textView, final String html) {
        if (html == null || html.isEmpty()) {
            textView.setText(null);
            return;
        }

        textView.setMovementMethod(LinkMovementMethod.getInstance());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY));
        } else {
            textView.setText(Html.fromHtml(html));
        }
    }
}