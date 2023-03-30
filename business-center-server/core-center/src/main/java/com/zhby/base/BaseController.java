package com.zhby.base;

import com.zhby.utils.core.ZhDateUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {

        binder.registerCustomEditor(Date.class, new DateEditor());

        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(ZhDateUtils.parseDate(text));
            }
        });

        // Timestamp 类型转换
        binder.registerCustomEditor(Timestamp.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                Date date = ZhDateUtils.parseDate(text);
                setValue(date==null?null:new Timestamp(date.getTime()));
            }
        });
    }
}

class DateEditor extends PropertyEditorSupport {
    private static final DateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateFormat TIMEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private DateFormat dateFormat;
    private boolean allowEmpty = true;

    public DateEditor() {
    }

    public DateEditor(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public DateEditor(DateFormat dateFormat, boolean allowEmpty) {
        this.dateFormat = dateFormat;
        this.allowEmpty = allowEmpty;
    }

    public void setAsText(String text) throws IllegalArgumentException {
        if (this.allowEmpty && !StringUtils.hasText(text)) {
            this.setValue((Object)null);
        } else {
            try {
                if (this.dateFormat != null) {
                    this.setValue(this.dateFormat.parse(text));
                } else if (text.contains(":")) {
                    this.setValue(TIMEFORMAT.parse(text));
                } else {
                    this.setValue(DATEFORMAT.parse(text));
                }
            } catch (ParseException var3) {
                throw new IllegalArgumentException("Could not parse date: " + var3.getMessage(), var3);
            }
        }

    }

    public String getAsText() {
        Date value = (Date)this.getValue();
        DateFormat dateFormat = this.dateFormat;
        if (dateFormat == null) {
            dateFormat = TIMEFORMAT;
        }

        return value != null ? dateFormat.format(value) : "";
    }
}
