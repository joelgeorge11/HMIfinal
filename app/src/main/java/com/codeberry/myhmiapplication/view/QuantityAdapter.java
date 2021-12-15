package com.codeberry.myhmiapplication.view;

import static com.codeberry.myhmiapplication.constants.Constants.MAX_DISPLAY_BRIGHTNESS;
import static com.codeberry.myhmiapplication.constants.Constants.MIN_DISPLAY_BRIGHTNESS;
import static com.codeberry.myhmiapplication.constants.Constants.VIEW_TYPE_CHECKBOX;
import static com.codeberry.myhmiapplication.constants.Constants.VIEW_TYPE_NUMBERPICKER;
import static com.codeberry.settingsinterfacemanager.SettingsInterfaceConstants.FUEL_SAVER_DISPLAY_IN_CLUSTER;
import static com.codeberry.settingsinterfacemanager.SettingsInterfaceConstants.THEME_MODE_MANUAL;
import static com.codeberry.settingsinterfacemanager.SettingsInterfaceConstants.TOUCH_SCREEN_BEEP;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codeberry.myhmiapplication.R;
import com.codeberry.settingsinterfacemanager.SettingsLineItem;

import java.util.ArrayList;
import java.util.List;

public class QuantityAdapter extends RecyclerView.Adapter {

    Context context;
    List<SettingsLineItem> mSettingsLineItem;


    public QuantityAdapter(Context context, List<SettingsLineItem> settingsLineItem) {
        this.context = context;
        this.mSettingsLineItem = settingsLineItem;
    }

    @Override
    public int getItemViewType(int position) {
        int settingsId=mSettingsLineItem.get(position).getSettingId();
        if (settingsId==TOUCH_SCREEN_BEEP||settingsId==FUEL_SAVER_DISPLAY_IN_CLUSTER||settingsId==THEME_MODE_MANUAL) {
            return VIEW_TYPE_CHECKBOX;
        } else{
            return VIEW_TYPE_NUMBERPICKER;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;
        if (viewType == VIEW_TYPE_CHECKBOX) {
            view = layoutInflater.inflate(R.layout.first_row, parent, false);
            return new ViewHolderOne(view);
        } else {
            view = layoutInflater.inflate(R.layout.second_row, parent, false);
            return new ViewHolderTwo(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        int settingsId=mSettingsLineItem.get(position).getSettingId();
        if (holder.getItemViewType() == VIEW_TYPE_CHECKBOX) {
            ViewHolderOne viewHolderOne = (ViewHolderOne) holder;
            viewHolderOne.checkboxOptions.setText(mSettingsLineItem.get(position).getSettingName());
        } else {
            ViewHolderTwo viewHolderTwo = (ViewHolderTwo) holder;
            viewHolderTwo.displayBrightnessOptions.setText(mSettingsLineItem.get(position).getSettingName());
            viewHolderTwo.displayCurrentBrightness.setText(String.valueOf(mSettingsLineItem.get(position).getSettingValue()));
            if(mSettingsLineItem.get(VIEW_TYPE_CHECKBOX).getSettingValue()==1){
                viewHolderTwo.displayBrightnessOptions.setEnabled(true);
                viewHolderTwo.decreaseBrightness.setEnabled(true);
                viewHolderTwo.increaseBrightness.setEnabled(true);
                viewHolderTwo.displayCurrentBrightness.setEnabled(true);
            }
            else {
                viewHolderTwo.displayBrightnessOptions.setEnabled(false);
                viewHolderTwo.decreaseBrightness.setEnabled(false);
                viewHolderTwo.increaseBrightness.setEnabled(false);
                viewHolderTwo.displayCurrentBrightness.setEnabled(false);
            }
        }
        if(settingsId==THEME_MODE_MANUAL){
            final ViewHolderOne viewHolderOne1=(ViewHolderOne) holder;
            viewHolderOne1.checkboxOptions.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(compoundButton.isChecked()){
                        mSettingsLineItem.get(VIEW_TYPE_CHECKBOX).setSettingValue(1);
                        notifyDataSetChanged();
                    }
                    else {
                        mSettingsLineItem.get(VIEW_TYPE_CHECKBOX).setSettingValue(2);
                        notifyDataSetChanged();
                    }
                }
            });
        }
    }




    @Override
    public int getItemCount() {
    return mSettingsLineItem.size();
    }


    class ViewHolderOne extends RecyclerView.ViewHolder {
        CheckBox checkboxOptions;

        public ViewHolderOne(@NonNull View itemView) {
            super(itemView);
            checkboxOptions = itemView.findViewById(R.id.checkbox);
        }
    }

    class ViewHolderTwo extends RecyclerView.ViewHolder {
        TextView displayBrightnessOptions, displayCurrentBrightness;
        Button decreaseBrightness, increaseBrightness;
        int currentBrightness=3;

        public ViewHolderTwo(@NonNull View itemView) {
            super(itemView);

            displayBrightnessOptions = itemView.findViewById(R.id.textView);
            displayCurrentBrightness = itemView.findViewById(R.id.textView2);
            decreaseBrightness = itemView.findViewById(R.id.button2);
            increaseBrightness = itemView.findViewById(R.id.button4);

            increaseBrightness.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (currentBrightness == MAX_DISPLAY_BRIGHTNESS) {
                        currentBrightness = MAX_DISPLAY_BRIGHTNESS;
                    } else {
                        currentBrightness++;
                        displayCurrentBrightness.setText("" + currentBrightness);
                    }
                }
            });
            decreaseBrightness.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (currentBrightness == MIN_DISPLAY_BRIGHTNESS) {
                        currentBrightness = MIN_DISPLAY_BRIGHTNESS;
                    } else {
                        currentBrightness--;
                    }
                    displayCurrentBrightness.setText("" + currentBrightness);
                }
            });
        }
    }
}


