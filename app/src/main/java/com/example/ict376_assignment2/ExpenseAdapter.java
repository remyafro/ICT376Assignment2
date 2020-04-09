package com.example.ict376_assignment2;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class ExpenseAdapter extends ArrayAdapter<Expense>{
    private Context mContext;
    private List<Expense> expenseList = new ArrayList<>();

    public ExpenseAdapter(@NonNull Context context,ArrayList<Expense> list){
        super(context, 0 ,list);
        mContext = context;
        expenseList = list;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItem = convertView;
        if(listItem == null){
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);

        }
        Expense currentExpense = expenseList.get(position);

        ImageView image = (ImageView) listItem.findViewById(R.id.imageView_image);
        byte[] img = currentExpense.getExpenseImg();
        if (img != null){
            Bitmap bm = BitmapFactory.decodeByteArray(img, 0 , img.length);
            Bitmap resized = Bitmap.createScaledBitmap(bm, (int) (bm.getWidth() * 5), (int) (bm.getHeight() * 5), true);
            image.setImageBitmap(resized);
        }
        else{
            image.setImageResource(android.R.drawable.ic_menu_camera);
        }


        TextView type = (TextView) listItem.findViewById(R.id.textView_listType);
        type.setText(currentExpense.getExpenseType());

        TextView desc = (TextView) listItem.findViewById(R.id.textView_listDesc);
        desc.setText(currentExpense.getExpenseDesc());

        TextView amt = (TextView) listItem.findViewById(R.id.textView_listAmt);
        String amt1 = Double.toString(currentExpense.getExpenseAmt());
        amt.setText("$ " + amt1);
        System.out.println(amt);

        TextView date = (TextView) listItem.findViewById(R.id.textView_listDate);
        date.setText(currentExpense.getExpenseDate());




        return listItem;
    }
}
