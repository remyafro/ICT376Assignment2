package com.example.ict376_assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Fragment;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DisplayExpenseDetails extends Fragment {
    private ExpenseDBHelper mydb ;

    View mLayoutView;
    TextView amount ;
    TextView description;
    TextView date;
    TextView category;
    ImageView receipt;
    int id_To_Update = 0;

    Button mCloseButton;
    Button mEditButton;
    Button mDeleteButton;

    public static DisplayExpenseDetails newInstance(int index){
        DisplayExpenseDetails fragment = new DisplayExpenseDetails();
        Bundle args = new Bundle();
        args.putInt("index", index);
        fragment.setArguments(args);
        return fragment;
    }

    public int getShownIndex() {

        return getArguments().getInt("index", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container == null) {
            return null;
        }
        mLayoutView = inflater.inflate(R.layout.activity_display_expense_details, null);
        return mLayoutView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        if (mLayoutView == null){
            return;
        }
        mydb = new ExpenseDBHelper(getActivity());

        amount = (TextView) mLayoutView.findViewById(R.id.editText_amount);
        description = (TextView) mLayoutView.findViewById(R.id.editText_description);
        date = (TextView) mLayoutView.findViewById(R.id.editText_Dates);
        category = (TextView) mLayoutView.findViewById(R.id.editText_category);
        receipt = (ImageView) mLayoutView.findViewById(R.id.imgReceipt);
        mCloseButton = (Button)mLayoutView.findViewById(R.id.button_back);

        int Value = getShownIndex();

        if(Value>0){

            Cursor rs = mydb.getDetails(Value);
            id_To_Update = Value;
            rs.moveToFirst();


            String amt   = rs.getString(rs.getColumnIndex(ExpenseDBHelper.EXPENSE_COLUMN_AMOUNT));
            String desc  = rs.getString(rs.getColumnIndex(ExpenseDBHelper.EXPENSE_COLUMN_DESCRIPTION));
            String dates  = rs.getString(rs.getColumnIndex(ExpenseDBHelper.EXPENSE_COLUMN_DATE));
            String type = rs.getString(rs.getColumnIndex(ExpenseDBHelper.EXPENSE_COLUMN_TYPE));
            byte[] img = rs.getBlob(rs.getColumnIndex(ExpenseDBHelper.EXPENSE_COLUMN_RECEIPT));
            if (img != null){
                Bitmap bm = BitmapFactory.decodeByteArray(img, 0 , img.length);
                Bitmap resized = Bitmap.createScaledBitmap(bm, (int) (bm.getWidth() * 5), (int) (bm.getHeight() * 5), true);
                receipt.setImageBitmap(resized);
            }
            else{
                receipt.setImageResource(android.R.drawable.ic_menu_camera);
            }

            if (!rs.isClosed()) {
                rs.close();
            }

            amount.setText((CharSequence)amt);
            amount.setFocusable(false);
            amount.setClickable(false);

            description.setText((CharSequence)desc);
            description.setFocusable(false);
            description.setClickable(false);

            date.setText((CharSequence)dates);
            date.setFocusable(false);
            date.setClickable(false);

            category.setText((CharSequence)type);
            category.setFocusable(false);
            category.setClickable(false);

        }

        mCloseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                getActivity().onBackPressed();
            }
        });

    }

}
