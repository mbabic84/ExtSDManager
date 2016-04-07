package cz.kralicinora.extsdmanager;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.provider.DocumentFile;
import android.widget.TextView;

public class Main extends Activity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        textView = (TextView) findViewById(R.id.main_textview);
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        startActivityForResult(intent, 42);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri uriTree = data.getData();
            DocumentFile pickedDir = DocumentFile.fromTreeUri(this, uriTree);
            StringBuilder builder = new StringBuilder();
            for (DocumentFile file : pickedDir.listFiles()) {
                builder.append(file.getName() + " -> " + file.length() + "\n");
            }
            if (textView != null) {
                textView.setText(builder.toString());
            }
        }
    }
}
