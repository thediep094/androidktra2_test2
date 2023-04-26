package com.example.thuchanh2;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.thuchanh2.database.Database;
import com.example.thuchanh2.model.CongViec;

import java.util.Calendar;

public class UpdateActivity extends AppCompatActivity {
    Button updateButton, removeButton, cancelButton, dateButton;
    EditText txtTen, txtHocPhi;
    TextView txtNgay;
    RadioGroup chuyenNganhRadioGroup;
    CheckBox kichHoatCheckBox;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);

        updateButton = findViewById(R.id.updateButton);
        cancelButton = findViewById(R.id.cancelButton);
        removeButton = findViewById(R.id.deleteButton);
        txtTen = findViewById(R.id.editTextTen);
        txtHocPhi = findViewById(R.id.editTextHocPhi);
        txtNgay = findViewById(R.id.textViewNgay);
        dateButton = findViewById(R.id.dateButton);
        chuyenNganhRadioGroup = findViewById(R.id.radioGroup);
        kichHoatCheckBox = findViewById(R.id.checkBox);

        Intent intent = getIntent();
        CongViec congViec = (CongViec) intent.getSerializableExtra("congviec");
        txtTen.setText(congViec.getTen());
        txtHocPhi.setText(congViec.getHocPhi());
        txtNgay.setText(congViec.getNgayBatDau());
        switch (congViec.getChuyenNganh()) {
            case 0:
                chuyenNganhRadioGroup.check(R.id.radioButton);
                break;
            case 1:
                chuyenNganhRadioGroup.check(R.id.radioButton1);
                break;
            case 2:
                chuyenNganhRadioGroup.check(R.id.radioButton2);
                break;
            case 3:
                chuyenNganhRadioGroup.check(R.id.radioButton4);
                break;
        }
        kichHoatCheckBox.setChecked(congViec.isKichHoat());

        Database db = new Database(this);

        //Date Button
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int cy = c.get(Calendar.YEAR);
                int cm = c.get(Calendar.MONTH);
                int cd = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(UpdateActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                                txtNgay.setText(d + "/" + m + "/" + y);
                            }
                        }, cy, cm, cd);
                dialog.show();
            }
        });

        //Cancel Button
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Remove Button
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteCV(congViec.getMa());
                finish();
            }
        });

        //Update Button
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = txtTen.getText().toString();
                String hocphi = txtHocPhi.getText().toString();
                String ngay = txtNgay.getText().toString();
                int chuyenNganh = 0;
                boolean kichHoat = kichHoatCheckBox.isChecked();

                switch (chuyenNganhRadioGroup.getCheckedRadioButtonId()) {
                    case R.id.radioButton:
                        chuyenNganh = 0;
                        break;
                    case R.id.radioButton1:
                        chuyenNganh = 1;
                        break;
                    case R.id.radioButton2:
                        chuyenNganh = 2;
                        break;
                    case R.id.radioButton4:
                        chuyenNganh = 3;
                        break;
                }

                if (ten.isEmpty() || hocphi.isEmpty() || ngay.isEmpty()) {
                    Toast.makeText(UpdateActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    congViec.setTen(ten);
                    congViec.setHocPhi(hocphi);
                    congViec.setNgayBatDau(ngay);
                    congViec.setChuyenNganh(chuyenNganh);
                    congViec.setKichHoat(kichHoat);
                    db.updateCV(congViec);

                    finish();
                }
            }
        });


    }
}
