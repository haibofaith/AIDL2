package com.example.user.learnaidl2;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.learnaidl.IBook;

public class MainActivity extends Activity {

    private EditText numberText;
    private TextView resultView;
    private Button query;
    private IBook bookQuery;
    private BookConnection bookConn = new BookConnection();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        numberText = (EditText) this.findViewById(R.id.number);
        resultView = (TextView) this.findViewById(R.id.resultView);
        query = (Button)findViewById(R.id.query);
        Intent service = new Intent();
        service.setAction("com.example.user.learnaidl.BookService");
        service.setPackage("com.example.user.learnaidl");
        bindService(service, bookConn, BIND_AUTO_CREATE);
        query.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String number = numberText.getText().toString();
                int num = Integer.valueOf(number);
                try {
                    resultView.setText(bookQuery.queryBook(num));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        unbindService(bookConn);
        super.onDestroy();
    }

    private final class BookConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // TODO Auto-generated method stub
            bookQuery = IBook.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub
            bookQuery = null;
        }

    }
}
