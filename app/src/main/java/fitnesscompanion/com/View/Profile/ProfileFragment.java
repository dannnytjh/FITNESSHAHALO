package fitnesscompanion.com.View.Profile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fitnesscompanion.com.Adapter.MenuAdapter;
import fitnesscompanion.com.Database.UserDB;
import fitnesscompanion.com.Model.User;
import fitnesscompanion.com.R;

/**
 * Created by Soon Kok Fung
 */
@SuppressLint("ValidFragment")
public class ProfileFragment extends Fragment {

    @BindView(R.id.listViewMenu) ListView listViewMenu;
    @BindView(R.id.txtName) TextView txtName;
    @BindView(R.id.imageProfile) ImageView imageProfile;
    @BindView(R.id.btnEdit) ImageButton btnEdit;

    private User user;

    private Context context;
    private View.OnClickListener onClickEdit = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getActivity().finish();
            startActivity(new Intent(context,ProfileActivity.class));
        }
    };
    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            getActivity().finish();
            switch (position) {
                case 0:
                    startActivity(new Intent(context,AchievementActivity.class));
                    break;
                case 1:
                    startActivity(new Intent(context,RankingActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(context,ReminderActivity.class));
                    //startActivity(new Intent(context,SettingActivity.class));
                    //reminder
                    break;
                case 3:
                    startActivity(new Intent(context,TimeLineActivity.class));
                    //startActivity(new Intent(context,SettingActivity.class));
                    //graph
                    break;
                case 4:
                    startActivity(new Intent(context,SettingActivity.class));
                    break;
            }
        }
    };

    public ProfileFragment(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = new UserDB(context).getData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, rootView);
        txtName.setText(user.getName());

        if (user.getImage().length() > 10) {
            imageProfile.setImageBitmap(user.getImageFromJSon());
        }
        btnEdit.setOnClickListener(onClickEdit);
        imageProfile.setOnClickListener(onClickEdit);
        listViewMenu.setAdapter(new MenuAdapter(context, context.getResources().getStringArray(R.array.menu_profile)));
        listViewMenu.setOnItemClickListener(onItemClickListener);

        return rootView;
    }
}
