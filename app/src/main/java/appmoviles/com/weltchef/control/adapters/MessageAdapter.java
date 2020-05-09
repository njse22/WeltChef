package appmoviles.com.weltchef.control.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.storage.FirebaseStorage;

import java.io.File;
import java.util.ArrayList;

import appmoviles.com.weltchef.R;
import appmoviles.com.weltchef.entity.Message;
import appmoviles.com.weltchef.util.Constants;
import appmoviles.com.weltchef.util.HTTPSWebUtilDomi;

public class MessageAdapter extends BaseAdapter {

    private ArrayList<Message> messages;
    private String userID;

    public MessageAdapter() {
        messages = new ArrayList<>();
        userID = " ";
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root;

        if (userID.equals(messages.get(position).getUserId())) {
            root = inflater.inflate(R.layout.messages_row_mine, null);
        }else{
            root = inflater.inflate(R.layout.messages_row_others, null);
        }

        TextView messageRow = root.findViewById(R.id.message_row);
        messageRow.setText(messages.get(position).getBody());

        if(messages.get(position).getType() == Message.TYPE_IMAGE) {
            ImageView imageRow = root.findViewById(R.id.imageRow);
            imageRow.setVisibility(View.VISIBLE);
            String nameImage = messages.get(position).getId();
            File imageFile = new File(parent.getContext().getExternalFilesDir(null) + "/"+ nameImage);

            // 1. preguntar si nameImage esta descargado
            if(imageFile.exists()){
                loadImage(imageRow, imageFile);
            }
            else {
                FirebaseStorage storage = FirebaseStorage.getInstance();
                storage.getReference().child(Constants.FIREBASE_CHATS_BRANCH).child(nameImage)
                        .getDownloadUrl().addOnSuccessListener(
                        uri -> {
                            //Almecenar en archivo
                            File f = new File( parent.getContext().getExternalFilesDir(null) + "/" + nameImage );
                            new Thread(
                                    () -> {
                                        HTTPSWebUtilDomi utilDomi = new HTTPSWebUtilDomi();
                                        utilDomi.saveURLImageOnFile(uri.toString(),f);
                                        //runOnUiThread --> Activity
                                        // post -> view
                                        root.post(
                                                () -> {
                                                    // ejecuta en el principal
                                                    loadImage(imageRow, f);
                                                }
                                        );
                                    }
                            ).start();
                        }
                );
            }
        }
        return root;
    }

    private void loadImage(ImageView imageRow, File f) {
        Bitmap bitmap = BitmapFactory.decodeFile(f.toString());
        imageRow.setImageBitmap(bitmap);
    }

    public void addMessage(Message message){
        messages.add(message);
        notifyDataSetChanged();
    }

    public void setUserId(String userID) {
        this.userID = userID;
        notifyDataSetChanged();
    }

}
